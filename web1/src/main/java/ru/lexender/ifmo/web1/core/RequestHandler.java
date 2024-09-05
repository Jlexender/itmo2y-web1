package ru.lexender.ifmo.web1.core;

public interface RequestHandler {
    void handle();
    void error(String message);
}
