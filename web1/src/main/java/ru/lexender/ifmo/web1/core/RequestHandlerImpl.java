package ru.lexender.ifmo.web1.core;

import com.fastcgi.FCGIInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.lexender.ifmo.web1.core.dto.CoordinatesDto;
import ru.lexender.ifmo.web1.core.service.ContourService;
import ru.lexender.ifmo.web1.json.ObjectMapperHolder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RequestHandlerImpl implements RequestHandler {
    ContourService contourService;
    FCGIInterface fcgiInterface = new FCGIInterface();

    @Override
    public void handle() {
        String content = """
                {
                    "result": %s
                }
                """;

        while (fcgiInterface.FCGIaccept() >= 0) {
            String requestBody;
            try {
                requestBody = readRequestBody();
            } catch (IOException e) {
                throw new RuntimeException("Can't read request body");
            }

            CoordinatesDto coordinates = ObjectMapperHolder
                    .getInstance().convertValue(requestBody, CoordinatesDto.class);

            content = String.format(content, contourService
                    .isInsideContour(coordinates.x(), coordinates.y(), coordinates.r())
            );

            var response = """
                    HTTP/1.1 200 OK
                    Content-Type: application/json
                    Content-Length: %d
                    
                    %s
                    """.formatted(requestBody.length(), requestBody);

            System.out.println(response);
        }
    }

    private String readRequestBody() throws IOException {
        FCGIInterface.request.inStream.fill();

        var contentLength = FCGIInterface.request.inStream.available();
        var buffer = ByteBuffer.allocate(contentLength);
        var readBytes = FCGIInterface.request.inStream.read(buffer.array(), 0, contentLength);

        var requestBodyRaw = new byte[readBytes];
        buffer.get(requestBodyRaw);
        buffer.clear();

        return new String(requestBodyRaw, StandardCharsets.UTF_8);
    }
}
