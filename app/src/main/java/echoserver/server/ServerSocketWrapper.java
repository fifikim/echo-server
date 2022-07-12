package echoserver.server;

import echoserver.ConsoleIo;
import echoserver.SocketIo;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements ServerSocketInterface {
  private final ServerSocket serverSocket;
  private Socket clientSocket;
  private final SocketIo socketIo;

  public ServerSocketWrapper(ServerSocket serverSocket, SocketIo socketIo) {
    this.serverSocket = serverSocket;
    this.socketIo = socketIo;
    this.clientSocket = null;
  }

  public int verifyConnection() {
    int port = serverSocket.getLocalPort();
    ConsoleIo.print("EchoServer listening on port " + port + "...");

    return port;
  }

  public Socket acceptClient() throws IOException {
    clientSocket = serverSocket.accept();
    ConsoleIo.print("EchoClient now connected!");

    socketIo.createSocketInput(clientSocket);
    socketIo.createSocketOutput(clientSocket);

    return clientSocket;
  }

  public String receiveMessage() throws IOException {
    String message = socketIo.receive();

    if (message != null) {
      ConsoleIo.print("Incoming message from EchoClient: " + message);
    }

    return message;
  }

  public void sendEcho(String message) {
    socketIo.send(message);
    ConsoleIo.print("Message echoed!");
  }

  public void closeSocket() throws IOException {
    clientSocket.close();
    serverSocket.close();
    socketIo.close();
    ConsoleIo.print("EchoServer connection closed.");
  }
}
