package echoserver.server;

import echoserver.Console;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements ServerSocketInterface {
  private final ServerSocket serverSocket;
  private Socket clientSocket;

  public ServerSocketWrapper(ServerSocket serverSocket) {
    this.serverSocket = serverSocket;
    this.clientSocket = null;
  }

  public int verifyConnection() {
    int port = serverSocket.getLocalPort();
    Console.print("EchoServer listening on port " + port + "...");
    return port;
  }

  public Socket acceptClient() throws IOException {
    clientSocket = serverSocket.accept();
    Console.print("EchoClient now connected!");
    return clientSocket;
  }

  public void close() throws IOException {
    clientSocket.close();
    serverSocket.close();
    Console.print("EchoServer connection closed.");
  }
}
