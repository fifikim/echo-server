package echoserver.client;

import echoserver.Utils;
import java.io.IOException;

public class EchoClient {
  private EchoClientSocket clientSocket;

  public void start(String hostName, int port) throws IOException {
    clientSocket = new EchoClientSocket();
    Utils.print(
            "EchoClient initialized. Attempting to connect to server at "
                    + hostName + ":" + port + "...");

    clientSocket.connect(hostName, port);
    Utils.print("Connected to EchoServer!");

    clientSocket.close();
    Utils.print("Disconnecting... Goodbye.");
  }
}