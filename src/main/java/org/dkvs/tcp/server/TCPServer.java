package org.dkvs.tcp.server;

import org.dkvs.tcp.message.parser.MessageParser;
import org.dkvs.tcp.message.processor.MessageProcessor;
import org.dkvs.tcp.exception.SocketCreationException;
import org.dkvs.tcp.handler.TcpClientHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;

public class TCPServer<Req, Res> implements Server {

    private ServerSocket serverSocket;
    private final MessageProcessor<Req, Res> messageProcessor;
    private final MessageParser<Req> requestMessageParser;
    private final MessageParser<Res> responseMessageParser;

    private static final Logger logger = LoggerFactory.getLogger(TCPServer.class);

    public TCPServer(MessageProcessor<Req, Res> messageProcessor,
                     MessageParser<Req> requestMessageParser,
                     MessageParser<Res> responseMessageParser) {
        this.messageProcessor = messageProcessor;
        this.requestMessageParser = requestMessageParser;
        this.responseMessageParser = responseMessageParser;
    }

    @Override
    public void start(int port) {
        logger.debug("starting server socket");
        try {
            serverSocket = new ServerSocket(port);
            logger.info("Tcp Server started");
            do {
                logger.debug("Creating Tcp client handler");
                new TcpClientHandler<>(serverSocket.accept(), this.messageProcessor, this.requestMessageParser, this.responseMessageParser);
            } while (true);
        } catch (Exception e) {
            throw new SocketCreationException("Error while creating server socket : " + e.getMessage(), e);
        }
    }

    @Override
    public boolean stop() {
        logger.debug("closing server socket");
        try {
            serverSocket.close();
            return true;
        } catch (Exception e) {
            logger.error("Error while closing connection.");
            return false;
        }
    }
}
