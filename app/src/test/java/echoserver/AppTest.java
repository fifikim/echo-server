package echoserver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest {
  @Test public void appHasCorrectGreeting() {
    App classUnderTest = new App();
    assertEquals("Hello World!", classUnderTest.getGreeting());
  }
}
