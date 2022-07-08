package echoserver.client;

import java.io.IOException;
import java.net.Socket;

public class EchoClient {
  private final ClientSocketInterface clientSocketInterface;

  public EchoClient(ClientSocketInterface clientSocketInterface) {
    this.clientSocketInterface = clientSocketInterface;
  }

  public void start(Socket clientSocket) throws IOException {
    clientSocketInterface.connect(clientSocket);
    clientSocketInterface.close(clientSocket);
  }
}