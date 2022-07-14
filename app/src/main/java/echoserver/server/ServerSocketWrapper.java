package echoserver.server;

import echoserver.ConsoleIo;
import echoserver.SocketIo;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class ServerSocketWrapper implements ServerSocketInterface {
  private final ServerSocket serverSocket;
  private Socket clientSocket = null;
  private SocketIo socketIo = null;

  public ServerSocketWrapper(ServerSocket serverSocket) {
    this.serverSocket = serverSocket;
  }

  public int verifyConnection() {
    int port = serverSocket.getLocalPort();
    ConsoleIo.print("EchoServer listening on port " + port + "...");

    return port;
  }

  public Socket acceptClient() throws IOException {
    clientSocket = serverSocket.accept();
    ConsoleIo.print("EchoClient now connected!");

    socketIo = createSocketStreams();

    return clientSocket;
  }

  public String receiveMessage() throws IOException {
    String message = socketIo.receive();

    if (message != null) {
      ConsoleIo.print("Incoming message from EchoClient: " + message);
    }

    return message;
  }

  public String sendEcho(String message) {
    socketIo.send(message);
    ConsoleIo.print("Message echoed!");
    return message;
  }

  public boolean quit(String message) {
    boolean quitStatus = "quit".equalsIgnoreCase(message.strip());

    if (quitStatus) {
      ConsoleIo.print("Session terminated by EchoClient.");
    }

    return quitStatus;
  }

  public void closeSocket() throws IOException {
    clientSocket.close();
    serverSocket.close();
    socketIo.closeStreams();
    ConsoleIo.print("EchoServer connection closed.");
  }

  public SocketIo createSocketStreams() throws IOException {
    return new SocketIo(clientSocket);
  }
}
