package echoserver.client;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class EchoClientTest {
  private ClientSocketInterface clientSocketInterface;
  private EchoClient echoClient;

  @Before
  public void initialize() {
    clientSocketInterface = mock(ClientSocketWrapper.class);
    echoClient = new EchoClient(clientSocketInterface);
  }

  @Test
  public void connectsToServer() throws IOException {
    echoClient.start();

    verify(clientSocketInterface).verifyConnection();
  }

  @Test
  public void closesConnection() throws IOException {
    echoClient.start();

    verify(clientSocketInterface).close();
  }
}
