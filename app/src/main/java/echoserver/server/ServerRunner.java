package echoserver.server;

import echoserver.ConsoleIo;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRunner {
  static ServerSocket serverSocket;

  public static void main(String[] args) throws IOException {
    int port = (args.length == 1) ? Integer.parseInt(args[0]) : 8080;

    try {
      serverSocket = new ServerSocket(port);
      serverSocket.setReuseAddress(true);
      ConsoleIo.print("EchoServer listening on port " + port + "...");

      while (true) {
        Socket clientSocket = serverSocket.accept();
        ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(clientSocket);
        EchoServer echoServer = new EchoServer(serverSocketWrapper);

        new Thread(echoServer).start();
      }
    } catch (IOException e) {
      ConsoleIo.err("Unable to open server socket", e);
    } finally {
      serverSocket.close();
    }
  }
}


