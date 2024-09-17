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



        return ValidationConfiguration.validX.contains(x) &&
                ValidationConfiguration.validR.contains(r) && y >= -3 && y <= 5;
    }
}
