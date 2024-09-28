package ru.lexender.ifmo.web2.core.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ControllerServlet extends HttpServlet {
    AreaCheckServlet areaCheckServlet = new AreaCheckServlet();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI().substring(request.getContextPath().length());

        // Serve static resources
        if (path.startsWith("/static/")) {
            String filePath = getServletContext().getRealPath(path);
            if (Files.exists(Paths.get(filePath))) {
                Files.copy(Paths.get(filePath), response.getOutputStream());
                return;
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        }

        if (path.equals("/query")) {
            areaCheckServlet.doGet(request, response);
            return;
        }

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
