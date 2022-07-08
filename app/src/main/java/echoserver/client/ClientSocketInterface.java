package echoserver.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public interface ClientSocketInterface {
  int connect(Socket clientSocket) throws IOException;

  void close(Socket clientSocket) throws IOException;
}