package echoserver.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import org.junit.Test;

public class ServerSocketTest {
  private final String testMessage = "test message";
  private ServerSocket serverSocket;
  private Socket clientSocket;
  private SocketIo socketIo;
  private ServerSocketInterface serverSocketInterface;
  private ByteArrayInputStream inputStream;
  private ByteArrayOutputStream outputStream;

  public void initialize() throws IOException {
    serverSocket = mock(ServerSocket.class);
    clientSocket = mock(Socket.class);
    when(serverSocket.accept()).thenReturn(clientSocket);

    inputStream = new ByteArrayInputStream(testMessage.getBytes());
    outputStream = new ByteArrayOutputStream();
    when(clientSocket.getInputStream()).thenReturn(inputStream);
    when(clientSocket.getOutputStream()).thenReturn(outputStream);

    socketIo = mock(SocketIo.class);
    when(socketIo.send(testMessage)).thenReturn(testMessage);
    when(socketIo.receive()).thenReturn(testMessage);

    serverSocketInterface = new ServerSocketWrapper(serverSocket);
  }

  public void initializeWithMockStreams() throws IOException {
    serverSocket = mock(ServerSocket.class);
    clientSocket = mock(Socket.class);
    when(serverSocket.accept()).thenReturn(clientSocket);

    inputStream = mock(ByteArrayInputStream.class);
    outputStream = mock(ByteArrayOutputStream.class);
    when(clientSocket.getInputStream()).thenReturn(inputStream);
    when(clientSocket.getOutputStream()).thenReturn(outputStream);

    socketIo = mock(SocketIo.class);
    serverSocketInterface = new ServerSocketWrapper(serverSocket);
  }

  @Test
  public void verifiesConnectedToCorrectPort() throws IOException {
    initialize();
    int expectedPort = 1234;
    when(serverSocket.getLocalPort()).thenReturn(expectedPort);
    int actualPort = serverSocketInterface.verifyConnection();

    assertEquals(expectedPort, actualPort);
  }

  @Test
  public void acceptsClientConnection() throws IOException {
    initialize();
    assertEquals(clientSocket, serverSocketInterface.acceptClient());
  }

  @Test
  public void receivesClientMessage() throws IOException {
    initialize();
    serverSocketInterface.acceptClient();
    String actualReceived = serverSocketInterface.receiveMessage();

    assertEquals(testMessage, actualReceived);
  }

  @Test
  public void echoesClientMessage() throws IOException {
    initialize();
    serverSocketInterface.acceptClient();
    serverSocketInterface.sendEcho(testMessage);

    assertEquals(testMessage, serverSocketInterface.sendEcho(testMessage));
  }

  @Test
  public void quitReturnsTrueIfMessageEqualsQuit() throws IOException {
    boolean result = serverSocketInterface.quit("quit");
    boolean negativeResult = serverSocketInterface.quit("quiet");

    assertTrue(result);
    assertFalse(negativeResult);
  }

  @Test
  public void quitHandlesUpperCaseInputAndWhiteSpace() throws IOException {
    boolean upperCase = serverSocketInterface.quit("QUIT");
    boolean whiteSpace = serverSocketInterface.quit(" quit    ");

    assertTrue(upperCase);
    assertTrue(whiteSpace);
  }

  @Test
  public void closesConnectionAndStreams() throws IOException {
    initializeWithMockStreams();
    serverSocketInterface.acceptClient();
    serverSocketInterface.closeSocket();

    verify(serverSocket).close();
    verify(clientSocket).close();
    verify(inputStream).close();
    verify(outputStream).close();
  }
}
