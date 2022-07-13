package echoserver.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import echoserver.SocketIo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Before;
import org.junit.Test;

public class ServerSocketTest {
  private final String testMessage = "test message";
  private ServerSocket serverSocket;
  private Socket clientSocket;
  private SocketIo socketIo;
  private ServerSocketInterface serverSocketInterface;

  @Before
  public void initialize() throws IOException {
    serverSocket = mock(ServerSocket.class);
    clientSocket = mock(Socket.class);
    when(serverSocket.accept()).thenReturn(clientSocket);

    ByteArrayInputStream inputStream = new ByteArrayInputStream(testMessage.getBytes());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    when(clientSocket.getInputStream()).thenReturn(inputStream);
    when(clientSocket.getOutputStream()).thenReturn(outputStream);

    socketIo = mock(SocketIo.class);
    when(socketIo.send(testMessage)).thenReturn(testMessage);
    when(socketIo.receive()).thenReturn(testMessage);
    when(socketIo.closeStreams()).thenReturn(true);

    serverSocketInterface = new ServerSocketWrapper(serverSocket);
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
    assertEquals(clientSocket, serverSocketInterface.acceptClient());
  }

  @Test
  public void receivesClientMessage() throws IOException {
    serverSocketInterface.acceptClient();
    String actualReceived = serverSocketInterface.receiveMessage();

    assertEquals(testMessage, actualReceived);
  }

  @Test
  public void echoesClientMessage() throws IOException {
    serverSocketInterface.acceptClient();
    serverSocketInterface.sendEcho(testMessage);

    assertEquals(testMessage, serverSocketInterface.sendEcho(testMessage));
  }

  @Test
  public void closesConnectionAndStreams() throws IOException {
    serverSocketInterface.acceptClient();
    serverSocketInterface.closeSocket();

    verify(serverSocket).close();
    verify(clientSocket).close();
    assertTrue(socketIo.closeStreams());
  }
}
