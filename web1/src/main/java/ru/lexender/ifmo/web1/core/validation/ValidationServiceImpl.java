package ru.lexender.ifmo.web1.core.validation;

import ru.lexender.ifmo.web1.core.dto.CoordinatesDto;

import java.util.List;

/**
 * Implementation of the ValidationService interface.
 * Used to check if the coordinates are valid.
 *
 * @see ValidationService
 * @see CoordinatesDto
 * @since 1.0
 */
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(CoordinatesDto coordinates) {
        double x = coordinates.x(), y = coordinates.y(), r = coordinates.r();

        List<Double> validX = List.of(-3.0, -2.0, -1.0, 0.0, 1.0, 2.0, 3.0, 4.0, 5.0);
        List<Double> validR = List.of(1.0, 1.5, 2.0, 2.5, 3.0);

        return validX.contains(x) && validR.contains(r) && y >= -3 && y <= 5;
    }
}
