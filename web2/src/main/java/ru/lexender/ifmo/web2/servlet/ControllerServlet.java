package ru.lexender.ifmo.web2.servlet;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.lexender.ifmo.web2.validation.ValidationService;
import ru.lexender.ifmo.web2.validation.ValidationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String r = request.getParameter("r");

        if (x.isEmpty() || y.isEmpty() || r.isEmpty()) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        response.getWriter().println("you typed " + x + " " + y + " " + r);
    }

}
