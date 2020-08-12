package org.dkvs.tcp.server;

public interface Server {
    String serverType = "TCPServer";
    void start(int port);
    boolean stop();
}
