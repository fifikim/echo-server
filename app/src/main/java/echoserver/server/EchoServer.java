package echoserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
  public void start(int port) throws IOException {
    ServerSocket serverSocket = new ServerSocket(port);
    System.out.println(
            "EchoServer initialized and listening to Port "
                    + serverSocket.getLocalPort() + "...");

    Socket clientSocket = serverSocket.accept();
    System.out.println("Connection successfully established!");

    serverSocket.close();
    System.out.println("Closing connection... Goodbye.");
  }
}
