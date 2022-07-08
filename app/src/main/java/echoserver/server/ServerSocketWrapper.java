package echoserver.server;

import echoserver.Console;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements ServerSocketInterface {
  ServerSocket serverSocket;

  public ServerSocket open(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    Console.print("EchoServer listening on port " + port + "...");
    return serverSocket;
  }

  public Socket acceptClient(ServerSocket serverSocket) throws IOException {
    Socket clientSocket = serverSocket.accept();
    Console.print("EchoClient now connected!");
    return clientSocket;
  }

  public void close(ServerSocket serverSocket, Socket clientSocket) throws IOException {
    clientSocket.close();
    serverSocket.close();
    Console.print("EchoServer connection closed.");
  }
}
