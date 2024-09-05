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
    static RequestHandler requestHandler = new RequestHandlerImpl(new ContourServiceImpl());

    public static void main(String[] args) {
        requestHandler.handle();
    }

}
