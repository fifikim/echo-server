package echoserver.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Test;

public class ServerSocketTest {
  final int port = 1234;
  private final ServerSocketInterface serverSocketInterface = new ServerSocketWrapper();

  @Test
  public void bindsSocketToGivenPort() throws IOException {
    ServerSocket serverSocket = serverSocketInterface.open(port);
    assertEquals(port, serverSocket.getLocalPort());
    assertTrue(serverSocket.isBound());
  }

  @Test
  public void acceptsClientConnection() throws IOException {
    ServerSocket mockServerSocket = mock(ServerSocket.class);
    when(mockServerSocket.accept()).thenReturn(new Socket());

    assertNotNull(serverSocketInterface.acceptClient(mockServerSocket));
  }

  @Test
  public void closesConnection() throws IOException {
    ServerSocket mockServerSocket = mock(ServerSocket.class);
    Socket mockClientSocket = mock(Socket.class);

    serverSocketInterface.close(mockServerSocket, mockClientSocket);
    verify(mockServerSocket).close();
    verify(mockClientSocket).close();
  }
}
