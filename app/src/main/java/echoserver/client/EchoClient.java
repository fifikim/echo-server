package echoserver.client;

import java.io.IOException;
import java.net.Socket;

public class EchoClient {
  public void start(String hostName, int port) throws IOException {
    System.out.println(
            "EchoClient attempting to connect to "
                    + hostName + ":" + port + "...");

    Socket clientSocket = new Socket(hostName, port);
    System.out.println("Connected!");

    clientSocket.close();
    System.out.println("Disconnecting... Goodbye.");
  }
}