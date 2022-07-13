package echoserver.server;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class EchoServerTest {
  private ServerSocketInterface serverSocketInterface;
  private EchoServer echoServer;

  @Before public void initialize() {
    serverSocketInterface = mock(ServerSocketWrapper.class);
    echoServer = new EchoServer(serverSocketInterface);
  }

  @Test
  public void startsServerSocket() throws IOException {
    echoServer.start();

    verify(serverSocketInterface).verifyConnection();
  }

  @Test
  public void acceptsClientConnection() throws IOException {
    echoServer.start();

    verify(serverSocketInterface).acceptClient();
  }

  @Test
  public void echoesResponseIfMessageReceived() throws IOException {
    when(serverSocketInterface.receiveMessage()).thenReturn("test message");
    echoServer.start();

    verify(serverSocketInterface).sendEcho("test message");
  }

  @Test
  public void doesNotEchoIfMessageIsNull() throws IOException {
    when(serverSocketInterface.receiveMessage()).thenReturn(null);
    echoServer.start();

    verify(serverSocketInterface, never()).sendEcho(null);
  }

  @Test
  public void closesServerConnection() throws IOException {
    echoServer.start();

    verify(serverSocketInterface).closeSocket();
  }
}
