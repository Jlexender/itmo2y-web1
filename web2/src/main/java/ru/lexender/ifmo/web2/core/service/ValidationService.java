package ru.lexender.ifmo.web2.core.service;

public class ValidationService {
    public boolean validateX(double x) {
        return x >= -4 && x <= 4;
    }

    public boolean validateY(double y) {
        return y >= -3 && y <= 5;
    }

    public boolean validateR(double r) {
        return r >= 1 && r <= 4;
    }
}
