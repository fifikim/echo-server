package echoserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import echoserver.client.ClientSocketInterface;
import echoserver.client.ClientSocketWrapper;
import echoserver.client.EchoClient;
import echoserver.server.EchoServer;
import echoserver.server.ServerSocketInterface;
import echoserver.server.ServerSocketWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestHelpers {
  public static void restoreInitialStreams() {
    System.setOut(System.out);
    System.setIn(System.in);
  }

  public static ByteArrayOutputStream setConsoleOutput() {
    ByteArrayOutputStream mockOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(mockOut));

    return mockOut;
  }

  public static SocketIo socketIo(String message) throws IOException {
    SocketIo socketIo = mock(SocketIo.class);
    when(socketIo.receive()).thenReturn(message);
    when(socketIo.send(message)).thenReturn(message);

    return socketIo;
  }

  public static Socket socketWithStreams(InputStream in, OutputStream out) throws IOException {
    Socket clientSocket = mock(Socket.class);
    when(clientSocket.getInputStream()).thenReturn(in);
    when(clientSocket.getOutputStream()).thenReturn(out);

    return clientSocket;
  }

  public static void initializeClient(Socket clientSocket) throws IOException {
    ClientSocketInterface clientSocketInterface = new ClientSocketWrapper(clientSocket);
    EchoClient echoClient = new EchoClient(clientSocketInterface);
    echoClient.start();
  }

  public static void initializeServer(ServerSocket serverSocket) throws IOException {
    ServerSocketInterface serverSocketInterface = new ServerSocketWrapper(serverSocket);
    EchoServer echoServer = new EchoServer(serverSocketInterface);
    echoServer.start();
  }

}
