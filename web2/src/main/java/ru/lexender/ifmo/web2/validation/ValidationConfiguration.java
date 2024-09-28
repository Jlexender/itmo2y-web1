package ru.lexender.ifmo.web2.validation;

import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ru.lexender.ifmo.web2.config.ConfigLoader;

import java.util.Properties;

@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ValidationConfiguration {
    @Getter
    private static final ValidationConfiguration instance = new ValidationConfiguration();

    double minX, maxX, minY, maxY, minR, maxR;

    private ValidationConfiguration() {
        Properties properties = ConfigLoader.getInstance().load("validation.properties");
        minX = Double.parseDouble(properties.getProperty("minX"));
        maxX = Double.parseDouble(properties.getProperty("maxX"));
        minY = Double.parseDouble(properties.getProperty("minY"));
        maxY = Double.parseDouble(properties.getProperty("maxY"));
        minR = Double.parseDouble(properties.getProperty("minR"));
        maxR = Double.parseDouble(properties.getProperty("maxR"));
    }
}
