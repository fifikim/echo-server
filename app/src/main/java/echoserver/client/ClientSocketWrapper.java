package echoserver.client;

import echoserver.ConsoleIo;
import echoserver.SocketIo;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocketWrapper implements ClientSocketInterface {
  private final Socket clientSocket;
  private final SocketIo socketIo;

  public ClientSocketWrapper(Socket clientSocket, SocketIo socketIo) {
    this.clientSocket = clientSocket;
    this.socketIo = socketIo;
  }

  public int verifyConnection() throws IOException {
    InetAddress host = clientSocket.getInetAddress();
    int port = clientSocket.getPort();

    ConsoleIo.print(
            "Successfully connected to EchoServer at " + host + ":" + port + "!");

    socketIo.createSocketInput(clientSocket);
    socketIo.createSocketOutput(clientSocket);

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

  public void sendMessage(String message) {
    socketIo.send(message);
    ConsoleIo.print("You sent: " + message);
  }

  public String receiveResponse() throws IOException {
    String message = socketIo.receive();
    ConsoleIo.print("Response from EchoServer: " + message);
    return message;
  }

  public void closeSocket() throws IOException {
    clientSocket.close();
    socketIo.close();
    ConsoleIo.print("EchoClient disconnected.");
  }
}
