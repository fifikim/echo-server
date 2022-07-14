package echoserver.server;

import java.io.IOException;

public class EchoServer {
  private final ServerSocketInterface serverSocketInterface;

  public EchoServer(ServerSocketInterface serverSocketInterface) {
    this.serverSocketInterface = serverSocketInterface;
  }

  public void start() throws IOException {
    serverSocketInterface.verifyConnection();
    serverSocketInterface.acceptClient();

    String clientMessage;

    while ((clientMessage = serverSocketInterface.receiveMessage()) != null) {
      if (serverSocketInterface.quit(clientMessage)) {
        break;
      }
      serverSocketInterface.sendEcho(clientMessage);
    }

    serverSocketInterface.closeSocket();
  }
}
