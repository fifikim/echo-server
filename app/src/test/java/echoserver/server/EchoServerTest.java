package echoserver.server;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.Socket;
import org.junit.Before;
import org.junit.Test;

public class EchoServerTest {
  private ServerSocketInterface serverSocketInterface;
  private EchoServer echoServer;
  private Socket clientSocket;

  @Before public void initialize() {
    serverSocketInterface = mock(ServerSocketWrapper.class);
    echoServer = new EchoServer(serverSocketInterface);
    clientSocket = mock(Socket.class);
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
  public void closesServerConnection() throws IOException {
    echoServer.start();

    verify(serverSocketInterface).closeSocket();
  }
}
