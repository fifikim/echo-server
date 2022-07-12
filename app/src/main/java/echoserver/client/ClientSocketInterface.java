package echoserver.client;

import java.io.IOException;

public interface ClientSocketInterface {
  int verifyConnection() throws IOException;

  String getMessage();

  void sendMessage(String message) throws IOException;

  void receiveResponse() throws IOException;

  void closeSocket() throws IOException;
}