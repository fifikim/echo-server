package echoserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
  private final ServerSocketInterface serverSocketInterface;

  public EchoServer(ServerSocketInterface serverSocketInterface) {
    this.serverSocketInterface = serverSocketInterface;
  }

  public void start(int port) throws IOException {
    ServerSocket serverSocket = serverSocketInterface.open(port);
    Socket clientSocket = serverSocketInterface.acceptClient(serverSocket);
    serverSocketInterface.close(serverSocket, clientSocket);
  }
}
