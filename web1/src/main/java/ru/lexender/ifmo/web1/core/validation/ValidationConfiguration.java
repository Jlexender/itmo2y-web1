package ru.lexender.ifmo.web1.core.validation;

import java.util.List;

public class ValidationConfiguration {
    public static volatile List<Double> validX = List.of(-3.0, -2.0, -1.0, 0.0, 1.0, 2.0, 3.0, 4.0, 5.0);
    public static volatile List<Double> validR = List.of(1.0, 1.5, 2.0, 2.5, 3.0);
}
