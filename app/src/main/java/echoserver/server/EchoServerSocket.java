package echoserver.server;

import echoserver.Console;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerSocket {
  private ServerSocket serverSocket;
  private Socket clientSocket;

  public void open(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    Console.print("EchoServer listening on port " + port + "...");
  }

  public void acceptClient() throws IOException {
    clientSocket = serverSocket.accept();
    Console.print("EchoClient now connected!");
  }

  public void close() throws IOException {
    serverSocket.close();
    Console.print("EchoServer connection closed.");
  }
}
