package echoserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerSocket {
  private ServerSocket serverSocket;
  private Socket clientSocket;

  public void open(int port) throws IOException {
    serverSocket = new ServerSocket(port);
  }

  public void acceptClient() throws IOException {
    clientSocket = serverSocket.accept();
  }

  public void close() throws IOException {
    clientSocket.close();
    serverSocket.close();
  }
}
