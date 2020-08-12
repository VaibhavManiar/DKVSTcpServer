package org.dkvs.tcp.exception;

public class SocketCloseException extends RuntimeException {
    public SocketCloseException(String message) {
        super(message);
    }

    public SocketCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
