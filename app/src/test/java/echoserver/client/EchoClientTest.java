package echoserver.client;


import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import echoserver.SocketIo;
import echoserver.TestHelpers;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.junit.After;
import org.junit.Test;

public class EchoClientTest {
  private SocketIo socketIo;
  private String consoleOutput;
  private final String testMsg = "test message";
  private final String nullMsg = "";
  private final String[] inputs = new String[]{"first", "second", "quit", "ignore me"};
  private final String multipleMsgs = String.join(System.lineSeparator(), inputs);

  private void initialize(String message) throws IOException {
    InputStream inputStream = new ByteArrayInputStream(message.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();

    Socket clientSocket = TestHelpers.socketWithStreams(inputStream, outputStream);

    System.setIn(inputStream);
    ByteArrayOutputStream consoleOut = TestHelpers.setConsoleOutput();

    socketIo = new SocketIo(clientSocket);
    TestHelpers.initializeClient(clientSocket);
    consoleOutput = consoleOut.toString();
  }

  private void initializeWithMockIo(String message) throws IOException {
    System.setIn(new ByteArrayInputStream(message.getBytes()));

    InputStream inputStream = new ByteArrayInputStream(message.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();

    Socket clientSocket = TestHelpers.socketWithStreams(inputStream, outputStream);
    socketIo = TestHelpers.socketIo(message);

    ByteArrayOutputStream consoleOut = TestHelpers.setConsoleOutput();
    TestHelpers.initializeClient(clientSocket);
    consoleOutput = consoleOut.toString();
  }

  @After public void tearDown() {
    TestHelpers.restoreInitialStreams();
  }

  @Test
  public void connectsToServer() throws IOException {
    initialize(nullMsg);
    assertThat(consoleOutput, containsString("Successfully connected"));
  }

  @Test
  public void sendsMessageIfInputNotNull() throws IOException {
    initialize(testMsg);
    assertThat(consoleOutput, containsString("You sent:"));
  }

  @Test
  public void doesNotSendMessageIfInputIsNull() throws IOException {
    initializeWithMockIo(nullMsg);
    verify(socketIo, never()).send(null);
  }

  @Test
  public void receivesResponseIfInputNotNull() throws IOException {
    initialize(testMsg);
    assertThat(consoleOutput, containsString("Response from EchoServer"));
  }

  @Test
  public void doesNotReceiveResponseIfInputIsNull() throws IOException {
    initializeWithMockIo(nullMsg);
    verify(socketIo, never()).receive();
  }

  @Test
  public void canSendMultipleMessages() throws IOException {
    initialize(multipleMsgs);

    int messageCount = (consoleOutput.split("You sent").length) - 1;

    assertEquals(2, messageCount);
  }

  @Test
  public void canReceiveMultipleEchoes() throws IOException {
    initialize(multipleMsgs);
    int echoCount = (consoleOutput.split("Response").length) - 1;

    assertEquals(2, echoCount);
  }

  @Test
  public void inputOutputLoopClosesWhenUserInputsQuit() throws IOException {
    initialize(multipleMsgs);

    assertThat(consoleOutput, containsString("Terminating session"));
  }

  @Test
  public void ignoresInputEnteredAfterQuit() throws IOException {
    initialize(multipleMsgs);

    assertThat(consoleOutput, not(containsString("ignore me")));
  }

  @Test
  public void closesConnection() throws IOException {
    initialize(nullMsg);

    assertThat(consoleOutput, containsString("disconnected"));
  }
}
