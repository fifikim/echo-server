package echoserver;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConsoleTest {
  private final ByteArrayOutputStream out = new ByteArrayOutputStream();
  private final InputStream originalIn = System.in;
  private final PrintStream originalOut = System.out;

  @Before
  public void setStreams() {
    System.setOut(new PrintStream(out));
  }

  @After
  public void restoreInitialStreams() {
    System.setOut(originalOut);
    System.setIn(originalIn);
  }

  @Test
  public void printOutputsMessageToTerminal() {
    String message = "Test message";
    Console.print(message);
    assertEquals(message, out.toString().strip());
  }

  @Test
  public void inputGetsMessageFromTerminal() throws IOException {
    ByteArrayInputStream in = new ByteArrayInputStream("My test string".getBytes());
    System.setIn(in);

    String message = "My test string";
    assertEquals(message, Console.input());
  }
}
