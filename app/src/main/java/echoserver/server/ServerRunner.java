package echoserver.server;

import echoserver.SocketIo;
import java.io.IOException;
import java.net.ServerSocket;

public class ServerRunner {
  public static void main(String[] args) throws IOException {
    int port = (args.length == 1) ? Integer.parseInt(args[0]) : 8080;

    ServerSocket serverSocket = new ServerSocket(port);
    SocketIo socketIo = new SocketIo();
    ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(serverSocket, socketIo);
    EchoServer echoServer = new EchoServer(serverSocketWrapper);

    echoServer.start();
  }
}


