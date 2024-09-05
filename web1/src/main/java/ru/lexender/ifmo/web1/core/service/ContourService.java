package ru.lexender.ifmo.web1.core.service;

import ru.lexender.ifmo.web1.core.dto.CoordinatesDto;

public interface ContourService {
    boolean isInsideContour(CoordinatesDto coordinates);
}
