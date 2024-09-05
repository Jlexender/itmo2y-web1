package ru.lexender.ifmo.web1;

import com.fastcgi.FCGIInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.lexender.ifmo.web1.core.RequestHandler;
import ru.lexender.ifmo.web1.core.RequestHandlerImpl;
import ru.lexender.ifmo.web1.core.service.ContourServiceImpl;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FcgiServer {

    public static void main(String[] args) {
        String content = """
                {
                    "result": true
                }
                """;

        var fcgiInterface = new FCGIInterface();
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

}
