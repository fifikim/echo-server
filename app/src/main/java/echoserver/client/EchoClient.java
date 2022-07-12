package echoserver.client;

import java.io.IOException;

public class EchoClient {
  private final ClientSocketInterface clientSocketInterface;

  public EchoClient(ClientSocketInterface clientSocketInterface) {
    this.clientSocketInterface = clientSocketInterface;
  }

  public void start() throws IOException {
    clientSocketInterface.verifyConnection();

    String userInput;

    if ((userInput = clientSocketInterface.getMessage()) != null) {
      clientSocketInterface.sendMessage(userInput);
      clientSocketInterface.receiveResponse();
    }

    clientSocketInterface.closeSocket();
  }
}