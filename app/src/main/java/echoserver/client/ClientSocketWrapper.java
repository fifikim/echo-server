package echoserver.client;

import echoserver.ConsoleIo;
import echoserver.SocketIo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocketWrapper implements ClientSocketInterface {
  private final Socket clientSocket;
  private final SocketIo socketIo;
  private BufferedReader consoleIn;

  public ClientSocketWrapper(Socket clientSocket, SocketIo socketIo) {
    this.clientSocket = clientSocket;
    this.socketIo = socketIo;
    consoleIn = new BufferedReader(new InputStreamReader(System.in));
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
      String message = consoleIn.readLine();
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

  public boolean requestsQuit(String message) {
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
}
