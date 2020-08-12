package org.dkvs;

import org.dkvs.tcp.config.StringMessageConfiguration;
import org.dkvs.tcp.message.processor.MessageProcessor;
import org.dkvs.tcp.server.ServerProperties;

public class TestMessageConfiguration extends StringMessageConfiguration {

    public TestMessageConfiguration(ServerProperties serverProperties) {
        super(serverProperties);
    }

    @Override
    public MessageProcessor<String, String> getMessageProcessor() {
        return new StringMessageProcessor();
    }
}
