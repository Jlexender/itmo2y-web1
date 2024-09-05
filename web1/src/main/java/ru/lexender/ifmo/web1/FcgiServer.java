package ru.lexender.ifmo.web1;

import com.fastcgi.FCGIInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import ru.lexender.ifmo.web1.core.RequestHandler;
import ru.lexender.ifmo.web1.core.RequestHandlerImpl;
import ru.lexender.ifmo.web1.core.service.ContourServiceImpl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FcgiServer {
    static FCGIInterface fcgiInterface = new FCGIInterface();

    public static void main(String[] args) throws IOException {
        String content = readRequestBody();


        while (fcgiInterface.FCGIaccept() >= 0) {
            var response = """
                    HTTP/1.1 200 OK
                    Content-Type: application/json
                    Content-Length: %d
                    
                    %s
                    """.formatted(content.length(), content);

            System.out.println(response);
        }
    }

    private static String readRequestBody() throws IOException {
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
