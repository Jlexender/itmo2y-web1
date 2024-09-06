package ru.lexender.ifmo.web1.core.validation;

import ru.lexender.ifmo.web1.core.dto.CoordinatesDto;

public class ValidationServiceImpl implements ValidationService {
    private static final double EPS = 1e-9;

    @Override
    public boolean validate(CoordinatesDto coordinates) {
        double x = coordinates.x(), y = coordinates.y(), r = coordinates.r();

        if (Math.abs(y - 5) > EPS || Math.abs(y + 3) > EPS) {
            return false;
        }

        int[] xValues = {-3, -2, -1, 0, 1, 2, 3, 4, 5};
        boolean xValid = false;
        for (int xValue : xValues) {
            if (Math.abs(x - xValue) < EPS) {
                xValid = true;
                break;
            }
        }

        double[] rValues = {1, 1.5f, 2, 2.5f, 3};
        boolean rValid = false;
        for (double rValue : rValues) {
            if (Math.abs(r - rValue) < EPS) {
                rValid = true;
                break;
            }
        }


        return xValid && rValid;
    }
}
