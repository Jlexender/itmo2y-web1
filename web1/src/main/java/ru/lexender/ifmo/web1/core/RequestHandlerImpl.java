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
            } catch (IOException | NullPointerException e) {
                error("Can't read request body");
                return;
            }

            CoordinatesDto coordinates;
            try {
                coordinates = ObjectMapperHolder
                        .getInstance().convertValue(requestBody, CoordinatesDto.class);
            } catch (Exception e) {
                error("Can't parse request body");
                return;
            }


            content = String.format(content, contourService.isInsideContour(coordinates));

            var response = """
                    HTTP/2 200 OK
                    Content-Type: text/plain
                    Content-Length: %d
                    
                    %s
                    """.formatted(content.length(), content);

            System.out.println(response);
        }
    }

    @Override
    public void error(String message) {
        while (fcgiInterface.FCGIaccept() >= 0) {
            var response = """
                    HTTP/2 400 Bad Request
                    Content-Type: text/plain
                    Content-Length: %d
                    
                    %s
                    """.formatted(message.length(), message);

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
