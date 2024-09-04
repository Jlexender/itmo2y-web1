package ru.lexender.ifmo.web1;

import com.fastcgi.FCGIInterface;

public class FcgiServer {

    public static void main(String[] args) {

        var fcgiInterface = new FCGIInterface();
        while (fcgiInterface.FCGIaccept() >= 0) {
            var content = """
                    <!DOCTYPE html>
                    <html>
                        <head>
                            <title>FastCGI Server</title>
                        </head>
                        <body>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        </body>
                    </html>
                    """;

            var response = """
                    HTTP/1.1 200 OK
                    Content-Type: text/html
                    Content-Length: %d
                    
                    %s
                    """.formatted(content.length(), content);

            System.out.println(response);
        }
    }

}
