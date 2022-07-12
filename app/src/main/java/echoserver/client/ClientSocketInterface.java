package echoserver.client;

import java.io.IOException;

public interface ClientSocketInterface {
  int verifyConnection() throws IOException;

  void close() throws IOException;
}