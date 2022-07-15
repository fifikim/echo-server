package echoserver.server;

import echoserver.SocketIo;
import java.io.IOException;

public interface ServerSocketInterface {
  String assignId() throws IOException;

  String receiveMessage() throws IOException;

  String sendEcho(String message) throws IOException;

  void closeSocket() throws IOException;

  SocketIo createSocketStreams() throws IOException;
}
