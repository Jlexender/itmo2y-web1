package ru.lexender.ifmo.web1;

import com.fastcgi.FCGIInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import ru.lexender.ifmo.web1.core.RequestHandler;
import ru.lexender.ifmo.web1.core.RequestHandlerImpl;
import ru.lexender.ifmo.web1.core.service.ContourServiceImpl;
import ru.lexender.ifmo.web1.fcgi.FcgiInterfaceHolder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class FcgiServer {
    public static FCGIInterface fcgiInterface = FcgiInterfaceHolder.getInstance();

    public static void main(String[] args) {
        RequestHandler requestHandler = new RequestHandlerImpl(new ContourServiceImpl());
        requestHandler.handle();
    }

}
