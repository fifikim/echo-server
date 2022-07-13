package echoserver.server;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import echoserver.SocketIo;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Before;
import org.junit.Test;

public class ServerSocketTest {
  private final String testMessage = "test message";
  private ServerSocket serverSocket;
  private ServerSocketInterface serverSocketInterface;
  private Socket clientSocket;
  private SocketIo socketIo;

  @Before
  public void initialize() {
    serverSocket = mock(ServerSocket.class);
    socketIo = mock(SocketIo.class);
    serverSocketInterface = new ServerSocketWrapper(serverSocket, socketIo);
    clientSocket = mock(Socket.class);
  }

  @Test
  public void verifiesConnectedToCorrectPort() throws IOException {
    int expectedPort = 1234;
    when(serverSocket.getLocalPort()).thenReturn(expectedPort);
    int actualPort = serverSocketInterface.verifyConnection();

    assertEquals(expectedPort, actualPort);
  }

  @Test
  public void acceptsClientConnection() throws IOException {
    when(serverSocket.accept()).thenReturn(clientSocket);

    assertEquals(clientSocket, serverSocketInterface.acceptClient());
  }

  @Test
  public void receivesClientMessage() throws IOException {
    when(socketIo.receive()).thenReturn(testMessage);
    String actualReceived = serverSocketInterface.receiveMessage();

    assertEquals(testMessage, actualReceived);
  }

  @Test
  public void echoesClientMessage() throws IOException {
    serverSocketInterface.sendEcho(testMessage);

    verify(socketIo).send(testMessage);
  }

  @Test
  public void closesConnectionAndStreams() throws IOException {
    when(serverSocket.accept()).thenReturn(clientSocket);
    serverSocketInterface.acceptClient();
    serverSocketInterface.closeSocket();

    verify(serverSocket).close();
    verify(clientSocket).close();
    verify(socketIo).close();
  }
}
