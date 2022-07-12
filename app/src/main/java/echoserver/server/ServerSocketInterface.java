package echoserver.server;

import java.io.IOException;
import java.net.Socket;

public interface ServerSocketInterface {
  int verifyConnection() throws IOException;

  Socket acceptClient() throws IOException;

  void close() throws IOException;
}
