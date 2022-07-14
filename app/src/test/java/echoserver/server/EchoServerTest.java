//package echoserver.server;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.core.StringContains.containsString;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import echoserver.SocketIo;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.PrintStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//import org.junit.After;
//import org.junit.Test;
//
//public class EchoServerTest {
//  private ServerSocket serverSocket;
//  private Socket clientSocket;
//  private SocketIo socketIo;
//  private ByteArrayOutputStream mockOut;
//  private String output;
//
//  public void initializeTest(String message) throws IOException {
//    setConsoleOutput();
//
//    serverSocket = mock(ServerSocket.class);
//    clientSocket = mock(Socket.class);
//    when(serverSocket.accept()).thenReturn(clientSocket);
//
//    setSocketIo(message);
//
//    ServerSocketInterface serverSocketInterface = new ServerSocketWrapper(serverSocket);
//    EchoServer echoServer = new EchoServer(serverSocketInterface);
//    echoServer.start();
//
//    output = mockOut.toString().strip();
//  }
//
//  @After public void tearDown() {
//    restoreInitialStreams();
//  }
//
//  private void setConsoleOutput() {
//    mockOut = new ByteArrayOutputStream();
//    System.setOut(new PrintStream(mockOut));
//  }
//
//  private void setSocketIo(String message) throws IOException {
//    String testMessage = "test message \n second message \n quit";
//    InputStream inputStream = new ByteArrayInputStream(testMessage.getBytes());
//    OutputStream outputStream = new ByteArrayOutputStream();
//    when(clientSocket.getInputStream()).thenReturn(inputStream);
//    when(clientSocket.getOutputStream()).thenReturn(outputStream);
//
//    socketIo = mock(SocketIo.class);
//    when(socketIo.send(testMessage)).thenReturn(testMessage);
//    when(socketIo.closeStreams()).thenReturn(true);
//  }
//
//  private void restoreInitialStreams() {
//    System.setOut(System.out);
//    System.setIn(System.in);
//  }
//
//  @Test
//  public void startsServerSocket() throws IOException {
//    assertThat(output, containsString("EchoServer listening"));
//  }
//
//  @Test
//  public void acceptsClientConnection() throws IOException {
//    assertThat(output, containsString("EchoClient now connected!"));
//  }
//
//  @Test
//  public void echoesResponseIfMessageReceived() throws IOException {
//    assertThat(output, containsString("Message echoed!"));
//  }
//
//  @Test
//  public void doesNotEchoIfMessageIsNull() throws IOException {
//    //verify(serverSocketInterface.sendEcho(null);
//  }
//
//  @Test
//  public void canReceiveMultipleMessages() throws IOException {
//    assertThat(output, containsString("test message"));
//    assertThat(output, containsString("second message"));
//  }
//
//  @Test
//  public void canSendMultipleEchoes() throws IOException {
//    int echoCount = (output.split("Message echoed!").length) - 1;
//
//    assertEquals(2, echoCount);
//  }
//
//  @Test
//  public void inputOutputLoopClosesWhenUserInputsQuit() throws IOException {
//    assertThat(output, containsString("Session terminated"));
//  }
//
//  @Test
//  public void closesServerConnection() throws IOException {
//    verify(serverSocket).close();
//    verify(clientSocket).close();
//    assertTrue(socketIo.closeStreams());
//    assertThat(output, containsString("connection closed"));
//  }
//}
