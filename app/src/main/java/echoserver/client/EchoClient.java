package echoserver.client;

import java.io.IOException;
import java.net.InetAddress;

public class EchoClient {
  private final EchoClientSocket clientSocket;

  public EchoClient(EchoClientSocket echoClientSocket) {
    this.clientSocket = echoClientSocket;
  }

  public void start(InetAddress host, int port) throws IOException {
    clientSocket.connect(host, port);
    clientSocket.close();
  }
}