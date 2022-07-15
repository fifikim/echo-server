package echoserver.server;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import echoserver.SocketIo;
import echoserver.TestHelpers;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.After;
import org.junit.Test;

public class EchoServerTest {
  private SocketIo socketIo;
  private String consoleOutput;
  private final String testMsg = "test message \n second message \n third message";
  private final String nullMsg = "";

  public void initialize(String message) throws IOException {
    InputStream inputStream = new ByteArrayInputStream(message.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();

    ServerSocket serverSocket = mock(ServerSocket.class);
    Socket clientSocket = TestHelpers.socketWithStreams(inputStream, outputStream);
    when(serverSocket.accept()).thenReturn(clientSocket);

    socketIo = TestHelpers.socketIo(message);

    ByteArrayOutputStream consoleOut = TestHelpers.setConsoleOutput();
    TestHelpers.initializeServer(serverSocket);
    consoleOutput = consoleOut.toString();
  }

  @After
  public void tearDown() {
    TestHelpers.restoreInitialStreams();
  }

  @Test
  public void startsServerSocket() throws IOException {
    initialize(nullMsg);
    assertThat(consoleOutput, containsString("EchoServer listening"));
  }

  @Test
  public void acceptsClientConnection() throws IOException {
    initialize(nullMsg);
    assertThat(consoleOutput, containsString("EchoClient now connected!"));
  }

  @Test
  public void echoesResponseIfMessageReceived() throws IOException {
    initialize(testMsg);
    assertThat(consoleOutput, containsString("Message echoed!"));
  }

  @Test
  public void doesNotEchoIfMessageIsNull() throws IOException {
    initialize(nullMsg);
    verify(socketIo, never()).send(null);
  }

  @Test
  public void canReceiveMultipleMessages() throws IOException {
    initialize(testMsg);
    assertThat(consoleOutput, containsString("test message"));
    assertThat(consoleOutput, containsString("second message"));
    assertThat(consoleOutput, containsString("third message"));
  }

  @Test
  public void canSendMultipleEchoes() throws IOException {
    initialize(testMsg);
    int echoCount = (consoleOutput.split("Message echoed!").length) - 1;

    assertEquals(3, echoCount);
  }

  @Test
  public void closesServerConnection() throws IOException {
    initialize(nullMsg);

    assertThat(consoleOutput, containsString("connection closed"));
  }
}
