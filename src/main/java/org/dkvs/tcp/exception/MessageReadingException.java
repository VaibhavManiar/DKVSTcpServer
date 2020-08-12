package org.dkvs.tcp.exception;

public class MessageReadingException extends RuntimeException {
    public MessageReadingException(String message) {
        super(message);
    }

    public MessageReadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
