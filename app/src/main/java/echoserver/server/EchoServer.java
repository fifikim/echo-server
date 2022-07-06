package echoserver.server;

import echoserver.Utils;
import java.io.IOException;

public class EchoServer {
  private EchoServerSocket serverSocket;

  public void start(int port) throws IOException {
    serverSocket = new EchoServerSocket();

    serverSocket.open(port);
    Utils.print(
            "EchoServer initialized. Listening for clients on Port " + port + "...");

    serverSocket.acceptClient();
    Utils.print("Connected to EchoClient!");

    serverSocket.close();
    Utils.print("Closing connection... Goodbye.");
  }
}
