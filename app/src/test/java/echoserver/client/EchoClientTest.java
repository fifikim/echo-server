package echoserver.client;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.junit.Test;

public class EchoClientTest {
  ClientSocketInterface mockClientSocketInterface = mock(ClientSocketInterface.class);
  EchoClient testClient = new EchoClient(mockClientSocketInterface);
  Socket mockClientSocket = mock(Socket.class);

  @Test
  public void connectsToServer() throws IOException {
    testClient.start(mockClientSocket);
    verify(mockClientSocketInterface).connect(mockClientSocket);
  }

  @Test
  public void closesConnection() throws IOException {
    testClient.start(mockClientSocket);
    verify(mockClientSocketInterface).close(mockClientSocket);
  }
}
