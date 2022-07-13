package echoserver.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import echoserver.SocketIo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.junit.Before;
import org.junit.Test;

public class ClientSocketTest {
  private final String testMessage = "test message";
  private Socket clientSocket;
  private SocketIo socketIo;
  private ClientSocketInterface socketInterface;

  @Before
  public void initialize() throws IOException {
    clientSocket = mock(Socket.class);
    ByteArrayInputStream inputStream = new ByteArrayInputStream(testMessage.getBytes());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    when(clientSocket.getInputStream()).thenReturn(inputStream);
    when(clientSocket.getOutputStream()).thenReturn(outputStream);

    socketIo = mock(SocketIo.class);
    when(socketIo.send(testMessage)).thenReturn(testMessage);
    when(socketIo.closeStreams()).thenReturn(true);

    socketInterface = new ClientSocketWrapper(clientSocket);
  }

  @Test
  public void verifiesConnectedToServerPort() throws IOException {
    int expectedPort = 1234;
    when(clientSocket.getPort()).thenReturn(expectedPort);
    int actualPort = socketInterface.verifyConnection();

    assertEquals(expectedPort, actualPort);
  }

  @Test
  public void getsUserInputtedMessage() {
    ByteArrayInputStream in = new ByteArrayInputStream(testMessage.getBytes());
    System.setIn(in);

    String actualReceived = socketInterface.getMessage();

    assertEquals(testMessage, actualReceived);
    System.setIn(System.in);
  }

  @Test
  public void sendsMessageToServer() throws IOException {
    socketInterface.sendMessage(testMessage);

    assertEquals(testMessage, socketInterface.sendMessage(testMessage));
  }

  @Test
  public void receivesResponseFromServer() throws IOException {
    when(socketIo.receive()).thenReturn(testMessage);
    String actualReceived = socketInterface.receiveResponse();

    assertEquals(testMessage, actualReceived);
  }

  @Test
  public void closesSocketAndStreams() throws IOException {
    socketInterface.closeSocket();

    verify(clientSocket).close();
    assertTrue(socketIo.closeStreams());
  }
}
