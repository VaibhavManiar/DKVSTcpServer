package org.dkvs.tcp.config.exception;

public class ConfigCreationException extends RuntimeException {
    public ConfigCreationException(String message) {
        super(message);
    }

    public ConfigCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
