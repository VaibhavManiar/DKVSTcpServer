package org.dkvs.tcp.config;

import org.dkvs.tcp.message.processor.MessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringMessageProcessor implements MessageProcessor<String, String> {
    private static final Logger logger = LoggerFactory.getLogger(StringMessageProcessor.class);
    @Override
    public String process(String request) {
        logger.info("Processing request : " + request);
        return request;
    }
}
