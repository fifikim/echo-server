package echoserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AppTest {
  @Test public void appHasGreeting() {
    App classUnderTest = new App();
    assertNotNull("app should have a greeting", classUnderTest.getGreeting());
  }

  @Test public void appHasCorrectGreeting() {
    App classUnderTest = new App();
    assertEquals("Hello World!", classUnderTest.getGreeting());
  }
}
