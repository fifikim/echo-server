package echoserver.server;

import echoserver.SocketIo;
import java.io.IOException;
import java.net.Socket;

public interface ServerSocketInterface {
  int verifyConnection() throws IOException;

  Socket acceptClient() throws IOException;

  String receiveMessage() throws IOException;

  String sendEcho(String message) throws IOException;

  void closeSocket() throws IOException;

  SocketIo createSocketStreams() throws IOException;
}
