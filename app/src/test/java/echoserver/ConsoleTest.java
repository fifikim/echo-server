package echoserver;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConsoleTest {
  private final ByteArrayOutputStream out = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @Before
  public void setStreams() {
    System.setOut(new PrintStream(out));
  }

  @After
  public void restoreInitialStreams() {
    System.setOut(originalOut);
  }

  @Test
  public void printOutputsMessageToTerminal() {
    String message = "Test message";
    Console.print(message);
    assertEquals(message, out.toString().strip());
  }
}
