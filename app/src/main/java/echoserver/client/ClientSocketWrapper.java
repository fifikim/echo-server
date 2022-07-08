package echoserver.client;

import echoserver.Console;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocketWrapper implements ClientSocketInterface {
  Socket clientSocket;
  InetAddress host;
  int port;

  public ClientSocketWrapper(Socket clientSocket) {
    this.clientSocket = clientSocket;
  }

  public int connect(Socket clientSocket) {
    host = clientSocket.getInetAddress();
    port = clientSocket.getPort();

    Console.print(
            "EchoClient attempting to connect to " + host + " on port " + port + "...");
    Console.print("Successfully connected to EchoServer!");

    return port;
  }

  public void close(Socket clientSocket) throws IOException {
    clientSocket.close();
    Console.print("EchoClient disconnected.");
  }
}
