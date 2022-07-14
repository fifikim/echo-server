package echoserver.client;

import java.io.IOException;

public interface ClientSocketInterface {
  int verifyConnection() throws IOException;

  String getMessage();

  String sendMessage(String message) throws IOException;

  String receiveResponse() throws IOException;

  boolean quit(String message) throws IOException;

  void closeSocket() throws IOException;
}