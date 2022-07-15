package echoserver.server;

import echoserver.ConsoleIo;
import echoserver.SocketIo;
import java.io.IOException;
import java.net.Socket;

public class ServerSocketWrapper implements ServerSocketInterface {
  private final Socket clientSocket;
  private final SocketIo socketIo;
  private String clientId;

  public ServerSocketWrapper(Socket clientSocket) throws IOException {
    this.clientSocket = clientSocket;
    socketIo = createSocketStreams();
    clientId = assignId();
  }

  public String assignId() {
    String id = "EchoClient" + clientSocket.getPort();
    ConsoleIo.print(clientId + " now connected at "
            + clientSocket.getInetAddress().getHostAddress() + "!");

    return id;
  }

  public String receiveMessage() throws IOException {
    String message = socketIo.receive();

    if (message != null) {
      ConsoleIo.print("Incoming message from " + clientId + ": " + message);
    }

    return message;
  }

  public String sendEcho(String message) {
    socketIo.send(message);
    ConsoleIo.print("Message echoed back to " + clientId + ".");
    return message;
  }

  public void closeSocket() throws IOException {
    clientSocket.close();
    socketIo.closeStreams();
    ConsoleIo.print(clientId + " disconnected.");
  }

  public SocketIo createSocketStreams() throws IOException {
    return new SocketIo(clientSocket);
  }
}
