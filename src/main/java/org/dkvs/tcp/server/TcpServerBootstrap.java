package org.dkvs.tcp.server;

import org.dkvs.tcp.config.Configuration;
import org.dkvs.tcp.config.util.ConfigFactory;

public class TcpServerBootstrap {

    private static TCPServer<?, ?> tcpServer;
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        ServerProperties serverProperties = new ServerProperties();
        Configuration<?, ?> configuration = ConfigFactory.create(serverProperties);
        tcpServer = new TCPServer(configuration.getMessageProcessor(), configuration.getRequestParser(), configuration.getResponseParser());
        tcpServer.start(configuration.port());
    }

    @Override
    public void finalize() {
        tcpServer.stop();
    }

}
