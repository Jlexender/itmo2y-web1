package ru.lexender.ifmo.web2.core.service;

public class AreaCheckService {
    public boolean checkArea(double x, double y, double r) {
        if (x >= 0 && y >= 0) {
            return x <= r && y <= r/2;
        } else if (x <= 0 && y >= 0) {
            return x * x + y * y <= r * r;
        } else if (x <= 0 && y <= 0) {
            return y >= -2 * x - r;
        } else {
            return false;
        }
    }
}
