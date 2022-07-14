package echoserver.client;

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
import java.net.Socket;
import org.junit.Test;

public class ClientSocketTest {
  private final String testMessage = "test message";
  private Socket clientSocket;
  private SocketIo socketIo;
  private ClientSocketInterface socketInterface;
  private ByteArrayInputStream inputStream;
  private ByteArrayOutputStream outputStream;

  public void initialize() throws IOException {
    inputStream = new ByteArrayInputStream(testMessage.getBytes());
    outputStream = new ByteArrayOutputStream();

    clientSocket = mock(Socket.class);
    when(clientSocket.getInputStream()).thenReturn(inputStream);
    when(clientSocket.getOutputStream()).thenReturn(outputStream);

    socketIo = mock(SocketIo.class);
    when(socketIo.receive()).thenReturn(testMessage);
    when(socketIo.send(testMessage)).thenReturn(testMessage);

    socketInterface = new ClientSocketWrapper(clientSocket);
  }

  public void initializeWithMockStreams() throws IOException {
    inputStream = mock(ByteArrayInputStream.class);
    outputStream = mock(ByteArrayOutputStream.class);

    clientSocket = mock(Socket.class);
    when(clientSocket.getInputStream()).thenReturn(inputStream);
    when(clientSocket.getOutputStream()).thenReturn(outputStream);

    socketIo = mock(SocketIo.class);
    socketInterface = new ClientSocketWrapper(clientSocket);
  }

  @Test
  public void verifiesConnectedToServerPort() throws IOException {
    initialize();
    int expectedPort = 1234;
    when(clientSocket.getPort()).thenReturn(expectedPort);
    int actualPort = socketInterface.verifyConnection();

    assertEquals(expectedPort, actualPort);
  }

  @Test
  public void getsUserInputtedMessage() throws IOException {
    initialize();
    ByteArrayInputStream in = new ByteArrayInputStream(testMessage.getBytes());
    System.setIn(in);

    String actualReceived = socketInterface.getMessage();

    assertEquals(testMessage, actualReceived);
    System.setIn(System.in);
  }

  @Test
  public void sendsMessageToServer() throws IOException {
    initialize();
    socketInterface.sendMessage(testMessage);

    assertEquals(testMessage, socketInterface.sendMessage(testMessage));
  }

  @Test
  public void receivesResponseFromServer() throws IOException {
    initialize();

    String actualReceived = socketInterface.receiveResponse();

    assertEquals(testMessage, actualReceived);
  }

  @Test
  public void quitReturnsTrueIfMessageEqualsQuit() throws IOException {
    initialize();
    boolean result = socketInterface.quit("quit");
    boolean negativeResult = socketInterface.quit("quiet");

    assertTrue(result);
    assertFalse(negativeResult);
  }

  @Test
  public void quitHandlesUpperCaseInputAndWhiteSpace() throws IOException {
    initialize();
    boolean upperCase = socketInterface.quit("QUIT");
    boolean whiteSpace = socketInterface.quit(" quit    ");

    assertTrue(upperCase);
    assertTrue(whiteSpace);
  }

  @Test
  public void closesSocketAndStreams() throws IOException {
    initializeWithMockStreams();
    socketInterface.closeSocket();

    verify(clientSocket).close();
    verify(inputStream).close();
    verify(outputStream).close();
  }
}
