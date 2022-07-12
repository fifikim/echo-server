package echoserver.server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerRunner {
  public static void main(String[] args) throws IOException {
    int port = (args.length == 1) ? Integer.parseInt(args[0]) : 8080;

    ServerSocket serverSocket = new ServerSocket(port);
    ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(serverSocket);
    EchoServer echoServer = new EchoServer(serverSocketWrapper);

    echoServer.start();
  }
}


