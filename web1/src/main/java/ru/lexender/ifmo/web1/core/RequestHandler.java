package ru.lexender.ifmo.web1.core;

/**
 * Interface for handling requests.
 *
 * @see RequestHandlerImpl
 * @author Jlexender
 * @since 1.0
 */
public interface RequestHandler {
    /**
     * Handles requests.
     * If the request is valid, sends the result with 200 OK.
     * Otherwise, sends an error message.
     *
     * @see #error(String)
     */
    void handle();

    /**
     * Wrapper for sending error messages.
     * Hardcoded for 400 Bad Request.
     * @param message error message
     */
    void error(String message);
}
