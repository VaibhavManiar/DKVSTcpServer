package org.dkvs.tcp.config;

import org.dkvs.tcp.message.parser.MessageParser;
import org.dkvs.tcp.message.parser.StringMessageParser;
import org.dkvs.tcp.server.ServerProperties;

public abstract class StringMessageConfiguration implements Configuration<String, String> {

    private final ServerProperties serverProperties;

    public StringMessageConfiguration(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Override
    public MessageParser<String> getRequestParser() {
        return new StringMessageParser();
    }

    @Override
    public MessageParser<String> getResponseParser() {
        return new StringMessageParser();
    }

    @Override
    public int port() {
        return Integer.parseInt(serverProperties.getPortIfConfigured().orElse("8666"));
    }
}
