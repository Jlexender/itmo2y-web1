package ru.lexender.ifmo.web1.core.validation;

import ru.lexender.ifmo.web1.core.dto.CoordinatesDto;

public class ValidationServiceImpl implements ValidationService {
    private static final double EPS = 1e-7;

    @Override
    public boolean validate(CoordinatesDto coordinates) {
        return true;
    }
}
