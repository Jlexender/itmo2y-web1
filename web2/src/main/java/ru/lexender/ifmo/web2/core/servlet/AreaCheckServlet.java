package ru.lexender.ifmo.web2.core.servlet;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.json.JSONObject;
import ru.lexender.ifmo.web2.core.service.AreaCheckService;
import ru.lexender.ifmo.web2.core.service.ValidationService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AreaCheckServlet extends HttpServlet {
    AreaCheckService areaCheckService = new AreaCheckService();
    ValidationService validationService = new ValidationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        String xParam = request.getParameter("x");
        String yParam = request.getParameter("y");
        String rParam = request.getParameter("r");

        if (xParam == null || yParam == null || rParam == null) {
            response.getWriter().write("Invalid request: Missing parameters");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            double x = Double.parseDouble(xParam.trim());
            double y = Double.parseDouble(yParam.trim());
            double r = Double.parseDouble(rParam.trim());

            if (!validationService.validateX(x) || !validationService.validateY(y) || !validationService.validateR(r)) {
                response.getWriter().write("Invalid request: Invalid parameters");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            boolean result = areaCheckService.checkArea(x, y, r);

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("result", result);
            jsonResponse.put("executionTime", System.currentTimeMillis() - startTime);

            response.setContentType("application/json");
            response.getWriter().write(jsonResponse.toString());
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException exception) {
            response.getWriter().write("Invalid request: " + exception.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
