package echoserver.client;

import java.io.IOException;

public class ClientRunner {
  public static void main(String[] args) throws IOException {
    String hostName = "localhost";
    int port = 8080;

    if (args.length == 2) {
      hostName = args[0];
      port = Integer.parseInt(args[1]);
    }

    EchoClient echoClient = new EchoClient();
    echoClient.start(hostName, port);
  }
}

