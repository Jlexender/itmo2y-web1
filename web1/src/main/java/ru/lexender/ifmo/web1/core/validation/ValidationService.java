package ru.lexender.ifmo.web1.core.validation;

import ru.lexender.ifmo.web1.core.dto.CoordinatesDto;

public interface ValidationService {
    boolean validate(CoordinatesDto coordinates);
}
