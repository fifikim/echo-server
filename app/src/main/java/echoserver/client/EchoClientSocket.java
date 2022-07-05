package echoserver.client;

import echoserver.Console;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class EchoClientSocket {
  private Socket clientSocket;

  public void connect(InetAddress host, int port) throws IOException {
    Console.print(
            "EchoClient attempting to connect to " + host + " on port " + port + "...");
    clientSocket = new Socket(host, port);
    Console.print("Successfully connected to EchoServer!");
  }

  public void close() throws IOException {
    clientSocket.close();
    Console.print("EchoClient disconnected.");
  }
}
