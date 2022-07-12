package echoserver.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.Socket;
import org.junit.Before;
import org.junit.Test;

public class ClientSocketTest {
  private Socket clientSocket;
  private ClientSocketInterface socketInterface;

  @Before
  public void initialize() {
    clientSocket = mock(Socket.class);
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
  public void closesSocket() throws IOException {
    socketInterface.close();
    verify(clientSocket).close();
  }
}
