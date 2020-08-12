package org.dkvs;

import org.dkvs.tcp.config.Configuration;
import org.dkvs.tcp.config.util.ConfigFactory;
import org.dkvs.tcp.server.ServerProperties;
import org.dkvs.tcp.server.TCPServer;
import org.junit.Test;

public class TestTcpServer {

    private TCPServer<String, String> tcpServer;

    @Test
    public void test() {
        ServerProperties serverProperties = new ServerProperties();
        Configuration<?, ?> configuration = ConfigFactory.create(serverProperties);
        tcpServer = new TCPServer(configuration.getMessageProcessor(), configuration.getRequestParser(), configuration.getResponseParser());
        tcpServer.start(configuration.port());
    }
}
