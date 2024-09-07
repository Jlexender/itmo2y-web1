package ru.lexender.ifmo.web1.core.service;

import ru.lexender.ifmo.web1.core.dto.CoordinatesDto;

/**
 * Interface for checking if the point is inside the contour.
 *
 * @see ContourServiceImpl
 * @see CoordinatesDto
 * @author Jlexender
 * @since 1.0
 */
public interface ContourService {
    /**
     * Checks if the point is inside the contour.
     * @param coordinates coordinates to check
     * @return true if the point is inside the contour, false otherwise
     */
    boolean isInsideContour(CoordinatesDto coordinates);
}
