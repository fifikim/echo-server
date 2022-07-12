package echoserver.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import echoserver.SocketIo;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Socket;
import org.junit.Before;
import org.junit.Test;

public class ClientSocketTest {
  private Socket clientSocket;
  private SocketIo socketIo;
  private ClientSocketInterface socketInterface;

  @Before
  public void initialize() {
    clientSocket = mock(Socket.class);
    socketIo = mock(SocketIo.class);
    socketInterface = new ClientSocketWrapper(clientSocket, socketIo);
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
    String testMessage = "this is a test message";
    ByteArrayInputStream in = new ByteArrayInputStream(testMessage.getBytes());
    System.setIn(in);

    String actualReceived = socketInterface.getMessage();

    assertEquals(testMessage, actualReceived);
    System.setIn(System.in);
  }

  @Test
  public void sendsMessageToServer() throws IOException {
  }

  @Test
  public void receivesResponseFromServer() throws IOException {
  }

  @Test
  public void closesSocketAndStreams() throws IOException {
    socketInterface.closeSocket();

    verify(clientSocket).close();
    verify(socketIo).close();
  }
}
