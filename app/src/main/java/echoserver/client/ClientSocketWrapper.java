package echoserver.client;

import echoserver.Console;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocketWrapper implements ClientSocketInterface {
  private final Socket clientSocket;

  public ClientSocketWrapper(Socket clientSocket) {
    this.clientSocket = clientSocket;
  }

  public int verifyConnection() {
    InetAddress host = clientSocket.getInetAddress();
    int port = clientSocket.getPort();

    Console.print(
            "Successfully connected to EchoServer at " + host + ":" + port + "!");

    return port;
  }

  public void close() throws IOException {
    clientSocket.close();
    Console.print("EchoClient disconnected.");
  }
}
