package ru.lexender.ifmo.web1;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.lexender.ifmo.web1.json.ObjectMapperHolder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HealthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> body = new HashMap<>();
        body.put("status", "UP");
        response.getWriter().write(ObjectMapperHolder.getInstance().writeValueAsString(body));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
