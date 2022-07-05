package echoserver;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class EchoClientTest {
  @Test
  public void appHasCorrectGreeting() {
    EchoClient testClient = new EchoClient();
    assertNotNull("!", testClient);
  }
}
