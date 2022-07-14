package echoserver.server;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import echoserver.SocketIo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.After;
import org.junit.Test;

public class EchoServerTest {
  private ServerSocket serverSocket;
  private Socket clientSocket;
  private SocketIo socketIo;
  private ByteArrayOutputStream mockOut;
  private String output;
  private final String testMsg = "test message \n second message \n third message";
  private final String nullMsg = "";

  public void initialize(String message) throws IOException {
    setConsoleOutput();

    serverSocket = mock(ServerSocket.class);
    clientSocket = mock(Socket.class);
    when(serverSocket.accept()).thenReturn(clientSocket);

    setSocketIo(message);

    ServerSocketInterface serverSocketInterface = new ServerSocketWrapper(serverSocket);
    EchoServer echoServer = new EchoServer(serverSocketInterface);
    echoServer.start();

    output = mockOut.toString().strip();
  }

  private void setConsoleOutput() {
    mockOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(mockOut));
  }

  private void setSocketIo(String message) throws IOException {
    InputStream inputStream = new ByteArrayInputStream(message.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    when(clientSocket.getInputStream()).thenReturn(inputStream);
    when(clientSocket.getOutputStream()).thenReturn(outputStream);

    socketIo = mock(SocketIo.class);
    when(socketIo.receive()).thenReturn(message);
    when(socketIo.send(message)).thenReturn(message);
  }

  @After
  public void restoreInitialStreams() {
    System.setOut(System.out);
    System.setIn(System.in);
  }

  @Test
  public void startsServerSocket() throws IOException {
    initialize(nullMsg);
    assertThat(output, containsString("EchoServer listening"));
  }

  @Test
  public void acceptsClientConnection() throws IOException {
    initialize(nullMsg);
    assertThat(output, containsString("EchoClient now connected!"));
  }

  @Test
  public void echoesResponseIfMessageReceived() throws IOException {
    initialize(testMsg);
    assertThat(output, containsString("Message echoed!"));
  }

  @Test
  public void doesNotEchoIfMessageIsNull() throws IOException {
    initialize(nullMsg);
    verify(socketIo, never()).send(null);
  }

  @Test
  public void canReceiveMultipleMessages() throws IOException {
    initialize(testMsg);
    assertThat(output, containsString("test message"));
    assertThat(output, containsString("second message"));
    assertThat(output, containsString("third message"));
  }

  @Test
  public void canSendMultipleEchoes() throws IOException {
    initialize(testMsg);
    int echoCount = (output.split("Message echoed!").length) - 1;

    assertEquals(3, echoCount);
  }

  @Test
  public void closesServerConnection() throws IOException {
    initialize(nullMsg);

    assertThat(output, containsString("connection closed"));
  }
}
