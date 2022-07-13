package echoserver.client;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class EchoClientTest {
  private final String testMessage = "test message";
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
  public void sendsMessageIfInputNotNull() throws IOException {
    when(clientSocketInterface.getMessage()).thenReturn(testMessage);
    echoClient.start();

    verify(clientSocketInterface).sendMessage(testMessage);
  }

  @Test
  public void doesNotSendMessageIfInputIsNull() throws IOException {
    when(clientSocketInterface.getMessage()).thenReturn(null);
    echoClient.start();

    verify(clientSocketInterface, never()).sendMessage(null);
  }

  @Test
  public void receivesResponseIfInputNotNull() throws IOException {
    when(clientSocketInterface.getMessage()).thenReturn(testMessage);
    echoClient.start();

    verify(clientSocketInterface).receiveResponse();
  }

  @Test
  public void doesNotReceiveResponseIfInputIsNull() throws IOException {
    when(clientSocketInterface.getMessage()).thenReturn(null);
    echoClient.start();

    verify(clientSocketInterface, never()).receiveResponse();
  }

  @Test
  public void closesConnection() throws IOException {
    echoClient.start();

    verify(clientSocketInterface).closeSocket();
  }
}
