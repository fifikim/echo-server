package echoserver.server;

import java.io.IOException;

public class ServerRunner {
  public static void main(String[] args) throws IOException {
    int port = (args.length == 1) ? Integer.parseInt(args[0]) : 8080;

    EchoServer echoServer = new EchoServer();
    echoServer.start(port);
  }
}


