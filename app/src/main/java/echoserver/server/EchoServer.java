package echoserver.server;

import java.io.IOException;

public class EchoServer {
  private final EchoServerSocket serverSocket;

  public EchoServer(EchoServerSocket echoServerSocket) {
    this.serverSocket = echoServerSocket;
  }

  public void start(int port) throws IOException {
    serverSocket.open(port);
    serverSocket.acceptClient();
    serverSocket.close();
  }
}
