package echoserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import echoserver.client.EchoClient;
import echoserver.client.EchoClientSocket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.Test;

public class EchoClientTest {
  EchoClientSocket mockSocket = mock(EchoClientSocket.class);
  EchoClient testClient = new EchoClient(mockSocket);
  InetAddress host = InetAddress.getByName("localhost");
  int port = 1234;

  public EchoClientTest() throws UnknownHostException {
  }

  @Test
  public void connectsToServerAtCorrectAddress() throws IOException {
    testClient.start(host, port);
    verify(mockSocket).connect(host, port);
  }

  @Test
  public void closesConnection() throws IOException {
    testClient.start(host, port);
    verify(mockSocket).close();
  }
}
