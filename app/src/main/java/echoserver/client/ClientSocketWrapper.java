package echoserver.client;

import echoserver.ConsoleIo;
import echoserver.SocketIo;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocketWrapper implements ClientSocketInterface {
  private final Socket clientSocket;
  private final SocketIo socketIo;

  public ClientSocketWrapper(Socket clientSocket) throws IOException {
    this.clientSocket = clientSocket;
    socketIo = createSocketStreams();
  }

  public int verifyConnection() {
    InetAddress host = clientSocket.getInetAddress();
    int port = clientSocket.getPort();

    ConsoleIo.print(
            "Successfully connected to EchoServer at " + host + ":" + port + "!");

    return port;
  }

  public String getMessage() {
    ConsoleIo.print("Enter your message: ");
    try {
      String message = ConsoleIo.input();
      return message;
    } catch (IOException e) {
      ConsoleIo.err("Unable to get message ", e);
    }
    return null;
  }

  public String sendMessage(String message) {
    socketIo.send(message);
    ConsoleIo.print("You sent: " + message);
    return message;
  }

  public String receiveResponse() throws IOException {
    String message = socketIo.receive();
    ConsoleIo.print("Response from EchoServer: " + message);
    return message;
  }

  public boolean quit(String message) {
    boolean quitStatus = "quit".equalsIgnoreCase(message.strip());

    if (quitStatus) {
      ConsoleIo.print("Terminating session...");
    }

    return quitStatus;
  }

  public void closeSocket() throws IOException {
    clientSocket.close();
    socketIo.closeStreams();
    ConsoleIo.print("EchoClient disconnected.");
  }

  private SocketIo createSocketStreams() throws IOException {
    return new SocketIo(clientSocket);
  }
}
