package echoserver.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientRunner {
  public static void main(String[] args) throws IOException {
    InetAddress host = InetAddress.getByName("localhost");
    int port = 8080;

    if (args.length == 2) {
      host = InetAddress.getByName(args[0]);
      port = Integer.parseInt(args[1]);
    }

    Socket clientSocket = new Socket(host, port);
    ClientSocketWrapper clientSocketWrapper = new ClientSocketWrapper(clientSocket);

    EchoClient echoClient = new EchoClient(clientSocketWrapper);
    echoClient.start(clientSocket);
  }
}

