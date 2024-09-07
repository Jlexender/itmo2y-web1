package ru.lexender.ifmo.web1.core.validation;

import ru.lexender.ifmo.web1.core.dto.CoordinatesDto;

/**
 * Interface for validating coordinates. Used to check if the coordinates are valid.
 *
 * @see ValidationServiceImpl
 * @see CoordinatesDto
 * @author Jlexender
 * @since 1.0
 */
public interface ValidationService {
    /**
     * Validates the coordinates.
     *
     * @param coordinates coordinates to validate
     * @return true if the coordinates are valid, false otherwise
     */
    boolean validate(CoordinatesDto coordinates);
}
