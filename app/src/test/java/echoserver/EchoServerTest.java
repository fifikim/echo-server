package echoserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import echoserver.server.EchoServer;
import echoserver.server.EchoServerSocket;
import java.io.IOException;
import org.junit.Test;

public class EchoServerTest {
  EchoServerSocket mockSocket = mock(EchoServerSocket.class);
  EchoServer testServer = new EchoServer(mockSocket);
  int port = 1234;

  @Test
  public void startsSocketAtGivenPort() throws IOException {
    testServer.start(port);
    verify(mockSocket).open(port);
  }

  @Test
  public void serverAcceptsClientConnection() throws IOException {
    testServer.start(port);
    verify(mockSocket).acceptClient();
  }

  @Test
  public void serverClosesConnection() throws IOException {
    testServer.start(port);
    verify(mockSocket).close();
  }
}
