package echoserver.server;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Test;

public class EchoServerTest {
  ServerSocketInterface serverSocketInterface = mock(ServerSocketWrapper.class);
  EchoServer echoServer = new EchoServer(serverSocketInterface);
  ServerSocket mockServerSocket = mock(ServerSocket.class);
  Socket mockClientSocket = mock(Socket.class);
  int port = 1234;

  @Test
  public void startsSocketAtGivenPort() throws IOException {
    echoServer.start(port);
    verify(serverSocketInterface).open(port);
  }

  @Test
  public void serverAcceptsClientConnection() throws IOException {
    when(serverSocketInterface.open(port)).thenReturn(mockServerSocket);
    echoServer.start(port);
    verify(serverSocketInterface).acceptClient(mockServerSocket);
  }

  @Test
  public void serverClosesConnection() throws IOException {
    when(serverSocketInterface.open(port)).thenReturn(mockServerSocket);
    when(serverSocketInterface.acceptClient(mockServerSocket)).thenReturn(mockClientSocket);
    echoServer.start(port);
    verify(serverSocketInterface).close(mockServerSocket, mockClientSocket);
  }
}
