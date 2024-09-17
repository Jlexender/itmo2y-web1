package ru.lexender.ifmo.web1.core;

import com.fastcgi.FCGIInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.lexender.ifmo.web1.core.dto.CoordinatesDto;
import ru.lexender.ifmo.web1.core.service.ContourService;
import ru.lexender.ifmo.web1.core.validation.ValidationConfiguration;
import ru.lexender.ifmo.web1.core.validation.ValidationService;
import ru.lexender.ifmo.web1.fcgi.FcgiInterfaceHolder;
import ru.lexender.ifmo.web1.json.ObjectMapperHolder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * Implementation of the {@link RequestHandler} interface.
 * Handles requests from the FastCGI interface.
 *
 * @author Jlexender
 * @see RequestHandler
 * @see FCGIInterface
 * @see FcgiInterfaceHolder
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RequestHandlerImpl implements RequestHandler {
    ContourService contourService;
    ValidationService validationService;

    @Override
    public void handle() {
        var fcgiInterface = FcgiInterfaceHolder.getInstance();

        while (fcgiInterface.FCGIaccept() >= 0) {
            try {
                Properties requestParams = readRequestParams();
                if (requestParams.containsKey("xSelector")) {
                    StringBuilder sb = new StringBuilder();
                    for (var x: ValidationConfiguration.validX) {
                        sb.append("<option value=\"%f\">%f</option>".formatted(x, x));
                    }

                    String content = sb.toString();

                    String response = """
                        HTTP/2 200 OK
                        Content-Type: text/html
                        Content-Length: %d
                        
                        %s
                        
                        """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);

                    System.out.println(response);
                    continue;
                }

                if (requestParams.containsKey("rSelector")) {
                    StringBuilder sb = new StringBuilder();
                    for (Double z: ValidationConfiguration.validR) {
                        sb.append("<option value=\"%f\">%f</option>".formatted(z, z));
                    }
                    String content = sb.toString();

                    String response = """
                        HTTP/2 200 OK
                        Content-Type: text/html
                        Content-Length: %d
                        
                        %s
                        
                        """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);

                    System.out.println(response);
                    continue;
                }

                var start = System.nanoTime();

                String content = """
                        <td>%s</td>
                        <td>%d</td>
                        """;

                String requestBody = readRequestBody();

                CoordinatesDto coordinates = ObjectMapperHolder
                        .getInstance().readValue(requestBody, CoordinatesDto.class);

                if (!validationService.validate(coordinates)) {
                    error("Request data is invalid");
                    continue;
                }

                String result = contourService.isInsideContour(coordinates) ? "true" : "false";
                var allTime = System.nanoTime() - start;
                content = content.formatted(result, allTime);

                String response = """
                        HTTP/2 200 OK
                        Content-Type: text/html
                        Content-Length: %d
                        
                        %s
                        
                        """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);

                System.out.println(response);
            } catch (Exception e) {
                error("Can't process request");
            }
        }
    }


    @Override
    public void error(String message) {
        message = message.replace("\n", " ");

        String content = """
                {
                    "error": "%s"
                }
                """.formatted(message);


        var response = """
                HTTP/2 400 Bad Request
                Content-Type: application/json
                Content-Length: %d
                
                %s
                
                """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);

        System.out.println(response);
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

    private Properties readRequestParams() {
        final String paramsString = FCGIInterface.request.params.getProperty("REQUEST_URI", "?").split("\\?")[1];
        return paramsString.isBlank() ? new Properties() : parseParams(paramsString);
    }

    private Properties parseParams(String paramsString) {
        StringTokenizer tokenizer = new StringTokenizer(paramsString, "&");
        Properties params = new Properties();
        while (tokenizer.hasMoreTokens()) {
            String[] pair = tokenizer.nextToken().split("=");
            params.setProperty(pair[0], pair[1]);
        }
        return params;
    }
}
