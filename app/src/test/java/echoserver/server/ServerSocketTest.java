package echoserver.server;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Before;
import org.junit.Test;

public class ServerSocketTest {
  private ServerSocket serverSocket;
  private ServerSocketInterface serverSocketInterface;
  private Socket clientSocket;

  @Before
  public void initialize() {
    serverSocket = mock(ServerSocket.class);
    serverSocketInterface = new ServerSocketWrapper(serverSocket);
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
  public void closesConnection() throws IOException {
    when(serverSocket.accept()).thenReturn(clientSocket);
    serverSocketInterface.acceptClient();
    serverSocketInterface.close();

    verify(serverSocket).close();
    verify(clientSocket).close();
  }
}
