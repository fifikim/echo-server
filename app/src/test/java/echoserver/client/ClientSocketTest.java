package echoserver.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.Socket;
import org.junit.Test;

public class ClientSocketTest {
  int port = 1234;
  Socket mockClientSocket = mock(Socket.class);
  private final ClientSocketInterface socketInterface = new ClientSocketWrapper(mockClientSocket);

  @Test
  public void connectsToGivenAddress() throws IOException {
    when(mockClientSocket.getPort()).thenReturn(port);
    int expectedPort = socketInterface.connect(mockClientSocket);
    assertEquals(port, expectedPort);
  }

  @Test
  public void closesSocket() throws IOException {
    socketInterface.close(mockClientSocket);
    verify(mockClientSocket).close();
  }
}
