package ru.lexender.ifmo.web1;

import com.fastcgi.FCGIInterface;
import ru.lexender.ifmo.web1.core.RequestHandler;
import ru.lexender.ifmo.web1.core.RequestHandlerImpl;
import ru.lexender.ifmo.web1.core.service.ContourServiceImpl;
import ru.lexender.ifmo.web1.core.validation.ValidationServiceImpl;
import ru.lexender.ifmo.web1.fcgi.FcgiInterfaceHolder;


/**
 * Main class of the application. It starts the FastCGI server and handles requests.
 *
 * @author Jlexender
 * @see FCGIInterface
 * @see FcgiInterfaceHolder
 * @see RequestHandler
 * @see RequestHandlerImpl
 * @see ContourServiceImpl
 * @see ValidationServiceImpl
 * @since 1.0
 */

public class FcgiServer {

    public static void main(String[] args) {
        new RequestHandlerImpl(new ContourServiceImpl(), new ValidationServiceImpl()).handle();
    }
}
