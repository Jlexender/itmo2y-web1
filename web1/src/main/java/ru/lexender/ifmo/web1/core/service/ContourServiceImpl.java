package ru.lexender.ifmo.web1.core.service;

public class ContourServiceImpl implements ContourService {
    @Override
    public boolean isInsideContour(double x, double y, double r) {
        if (x >= 0) {
            if (y <= 0) {
                return x <= r/2 && y >= -r;
            } else {
                return x*x + y*y <= r*r/4;
            }
        } else {
            return y <= 0 && -x/2 - r/2 <= y;
        }
    }
}
