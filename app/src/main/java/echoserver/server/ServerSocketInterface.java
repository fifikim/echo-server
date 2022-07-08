package echoserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public interface ServerSocketInterface {
  ServerSocket open(int port) throws IOException;

  Socket acceptClient(ServerSocket serverSocket) throws IOException;

  void close(ServerSocket serverSocket, Socket clientSocket) throws IOException;
}
