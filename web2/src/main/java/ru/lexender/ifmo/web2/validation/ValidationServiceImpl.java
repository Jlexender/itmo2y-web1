package ru.lexender.ifmo.web2.validation;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ValidationServiceImpl implements ValidationService {
    ValidationConfiguration configuration = ValidationConfiguration.getInstance();

    @Override
    public boolean validateX(double x) {
        return x >= configuration.getMinX() && x <= configuration.getMaxX();
    }

    @Override
    public boolean validateY(double y) {
        return y >= configuration.getMinY() && y <= configuration.getMaxY();
    }

    @Override
    public boolean validateR(double r) {
        return r >= configuration.getMinR() && r <= configuration.getMaxR();
    }
}
