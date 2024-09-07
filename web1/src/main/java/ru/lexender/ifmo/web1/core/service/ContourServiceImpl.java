package ru.lexender.ifmo.web1.core.service;

import ru.lexender.ifmo.web1.core.dto.CoordinatesDto;

/**
 * Implementation of the ContourService interface.
 * Used to check if the point is inside the contour.
 *
 * @see ContourService
 * @see CoordinatesDto
 * @since 1.0
 */
public class ContourServiceImpl implements ContourService {
    @Override
    public boolean isInsideContour(CoordinatesDto coordinates) {
        double x = coordinates.x(), y = coordinates.y(), r = coordinates.r();
        if (x >= 0) {
            if (y <= 0) {
                return x <= r / 2 && y >= -r;
            } else {
                return x * x + y * y <= r * r / 4;
            }
        } else {
            return y <= 0 && -x / 2 - r / 2 <= y;
        }
    }
}
