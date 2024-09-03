package ru.lexender.ifmo.web1.health;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.lexender.ifmo.web1.json.ObjectMapperHolder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HealthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> body = new HashMap<>();
        body.put("status", "UP");
        response.setContentType("application/json");
        response.getWriter().write(ObjectMapperHolder.getInstance().writeValueAsString(body));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
