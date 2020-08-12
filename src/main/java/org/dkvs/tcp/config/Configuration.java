package org.dkvs.tcp.config;

import org.dkvs.tcp.message.parser.MessageParser;
import org.dkvs.tcp.message.processor.MessageProcessor;

public interface Configuration<Req, Res> {
    MessageProcessor<Req, Res> getMessageProcessor();
    MessageParser<Req> getRequestParser();
    MessageParser<Res> getResponseParser();
    default int port() {
        return 8666;
    }
}