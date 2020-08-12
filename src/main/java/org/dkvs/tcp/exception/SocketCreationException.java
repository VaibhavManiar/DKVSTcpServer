package org.dkvs.tcp.exception;

public class SocketCreationException extends RuntimeException {
    public SocketCreationException(String message) {
        super(message);
    }

    public SocketCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
