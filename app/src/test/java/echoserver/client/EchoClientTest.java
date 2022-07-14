//package echoserver.client;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.core.StringContains.containsString;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.never;
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
//import java.net.Socket;
//import org.junit.After;
//import org.junit.Test;
//
//public class EchoClientTest {
//  private ByteArrayOutputStream mockOut;
//  private SocketIo socketIo;
//  private Socket clientSocket;
//  private String output;
//  private final String testMsg = "test message";
//  private final String nullMsg = "";
//  private final String multipleMsgs = testMsg.concat(System.lineSeparator())
//                                                      .concat("second message")
//                                                      .concat(System.lineSeparator())
//                                                      .concat("quit");
//
//  private void initializeTest(String message) throws IOException {
//    setConsoleIo(message);
//
//    clientSocket = mock(Socket.class);
//    setSocketIo(message);
//
//    ClientSocketInterface clientSocketInterface = new ClientSocketWrapper(clientSocket);
//    EchoClient echoClient = new EchoClient(clientSocketInterface);
//    echoClient.start();
//
//    output = mockOut.toString().strip();
//  }
//
//  @After public void restoreInitialStreams() {
//    System.setOut(System.out);
//    System.setIn(System.in);
//  }
//
//  private void setConsoleIo(String message) {
//    ByteArrayInputStream mockIn = new ByteArrayInputStream(message.getBytes());
//    mockOut = new ByteArrayOutputStream();
//    System.setIn(mockIn);
//    System.setOut(new PrintStream(mockOut));
//  }
//
//  private void setSocketIo(String message) throws IOException {
//    InputStream inputStream = new ByteArrayInputStream(message.getBytes());
//    OutputStream outputStream = new ByteArrayOutputStream();
//    when(clientSocket.getInputStream()).thenReturn(inputStream);
//    when(clientSocket.getOutputStream()).thenReturn(outputStream);
//
//    socketIo = mock(SocketIo.class);
//    when(socketIo.receive()).thenReturn(message);
//    when(socketIo.send(message)).thenReturn(message);
//    when(socketIo.closeStreams()).thenReturn(true);
//  }
//
//  @Test
//  public void connectsToServer() throws IOException {
//    initializeTest(nullMsg);
//    assertThat(output, containsString("Successfully connected"));
//  }
//
//  @Test
//  public void sendsMessageIfInputNotNull() throws IOException {
//    initializeTest(testMsg);
//    assertThat(output, containsString("You sent:"));
//  }
//
//  @Test
//  public void doesNotSendMessageIfInputIsNull() throws IOException {
//    initializeTest(nullMsg);
//    verify(socketIo, never()).send(null);
//  }
//
//  @Test
//  public void receivesResponseIfInputNotNull() throws IOException {
//    initializeTest(testMsg);
//    assertThat(output, containsString("Response from EchoServer"));
//  }
//
//  @Test
//  public void doesNotReceiveResponseIfInputIsNull() throws IOException {
//    initializeTest(nullMsg);
//    verify(socketIo, never()).receive();
//  }
//
//  @Test
//  public void canSendMultipleMessages() throws IOException {
//    initializeTest(multipleMsgs);
//    int echoCount = (output.split("You sent").length) - 1;
//
//    assertEquals(2, echoCount);
//  }
//
//  @Test
//  public void canReceiveMultipleEchoes() throws IOException {
//    initializeTest(multipleMsgs);
//    int echoCount = (output.split("Response").length) - 1;
//
//    assertEquals(2, echoCount);
//  }
//
//  @Test
//  public void closesConnection() throws IOException {
//    initializeTest(nullMsg);
//    verify(clientSocket).close();
//    assertTrue(socketIo.closeStreams());
//    assertThat(output, containsString("disconnected"));
//  }
//}
