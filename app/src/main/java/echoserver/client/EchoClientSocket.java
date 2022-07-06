package echoserver.client;

import java.io.IOException;
import java.net.Socket;

public class EchoClientSocket {
  private Socket clientSocket;

  public void connect(String hostName, int port) throws IOException {
    clientSocket = new Socket(hostName, port);
  }

  public void close() throws IOException {
    clientSocket.close();
  }
}
