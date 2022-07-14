package echoserver;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConsoleIoTest {
  private final ByteArrayOutputStream mockOut = new ByteArrayOutputStream();

  @Before
  public void setStreamOut() {
    System.setOut(new PrintStream(mockOut));
  }

  @After
  public void tearDown() {
    TestHelpers.restoreInitialStreams();
  }

  @Test
  public void printOutputsMessageToTerminal() {
    String message = "Test message";
    ConsoleIo.print(message);
    assertEquals(message, mockOut.toString().strip());
  }

  @Test
  public void inputGetsMessageFromTerminal() throws IOException {
    ByteArrayInputStream in = new ByteArrayInputStream("My test string".getBytes());
    System.setIn(in);

    String message = "My test string";
    assertEquals(message, ConsoleIo.input());
  }
}
