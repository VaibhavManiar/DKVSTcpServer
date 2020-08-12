package org.dkvs.tcp.message.processor;

public interface MessageProcessor<Req, Res> {
    Res process(Req request);
}
