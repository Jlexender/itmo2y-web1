package ru.lexender.ifmo.web2.config;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    @Getter
    private static final ConfigLoader instance = new ConfigLoader();

    public Properties load(String fileName) {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IOException("File not found: " + fileName);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties from file: " + fileName, e);
        }
        return properties;
    }
}
