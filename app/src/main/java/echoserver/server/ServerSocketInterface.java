package echoserver.server;

import java.io.IOException;
import java.net.Socket;

public interface ServerSocketInterface {
  int verifyConnection() throws IOException;

  Socket acceptClient() throws IOException;

  String receiveMessage() throws IOException;

  void sendEcho(String message) throws IOException;

  void closeSocket() throws IOException;
}
