package org.dkvs.tcp.handler;

import org.dkvs.tcp.exception.ConnectionException;
import org.dkvs.tcp.exception.MessageReadingException;
import org.dkvs.tcp.exception.SocketCloseException;
import org.dkvs.tcp.message.parser.MessageParser;
import org.dkvs.tcp.message.processor.MessageProcessor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpClientHandler<Req, Res> implements ClientHandler<Req, Res> {
    private final Socket clientSocket;
    private final MessageProcessor<Req, Res> messageProcessor;
    private final MessageParser<Req> requestMessageParser;
    private final MessageParser<Res> responseMessageParser;

    public TcpClientHandler(Socket socket,
                            MessageProcessor<Req, Res> messageProcessor,
                            MessageParser<Req> requestMessageParser,
                            MessageParser<Res> responseMessageParser) {
        try {
            this.clientSocket = socket;
            this.messageProcessor = messageProcessor;
            this.requestMessageParser = requestMessageParser;
            this.responseMessageParser = responseMessageParser;
            run();
        } catch (Exception e) {
            throw new ConnectionException(e.getMessage(), e);
        } finally {
            closeSocket();
        }
    }

    @Override
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            StringBuilder reqString = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                if (".".equals(inputLine)) {
                    break;
                }
                reqString.append(inputLine);
            }
            Req request = requestMessageParser.deSerialize(reqString.toString());
            Res response = this.messageProcessor.process(request);
            out.print(this.responseMessageParser.serialize(response));
        } catch (Exception e) {
            throw new MessageReadingException("Error while reading message : " + e.getMessage(), e);
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void closeSocket() {
        try {
            clientSocket.close();
        } catch (Exception e) {
            throw new SocketCloseException("Error while closing socket : " + e.getMessage(), e);
        }
    }
}
