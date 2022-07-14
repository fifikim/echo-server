//package echoserver.client;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.core.StringContains.containsString;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.verify;
//
//import echoserver.TestHelpers;
//import echoserver.SocketIo;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.net.Socket;
//import org.junit.After;
//import org.junit.Test;
//
//public class EchoClientTest {
//  private ByteArrayOutputStream consoleOut;
//  private SocketIo socketIo;
//  private String consoleOutput;
//  private final String testMsg = "test message";
//  private final String nullMsg = "";
//  private final String multipleMsgs = testMsg.concat(System.lineSeparator())
//                                                      .concat("second message")
//                                                      .concat(System.lineSeparator())
//                                                      .concat("quit");
//
//  private void initialize(String message) throws IOException {
//    consoleOut = TestHelpers.setConsoleOutput();
//    TestHelpers.setConsoleInput(message);
//
//    Socket clientSocket = mock(Socket.class);
//    socketIo = TestHelpers.setSocketIo(clientSocket, message);
//
//    ClientSocketInterface clientSocketInterface = new ClientSocketWrapper(clientSocket);
//    EchoClient echoClient = new EchoClient(clientSocketInterface);
//    echoClient.start();
//
//    consoleOutput = consoleOut.toString().strip();
//  }
//
//  @After public void tearDown() {
//    TestHelpers.restoreInitialStreams();
//  }
//
//  @Test
//  public void connectsToServer() throws IOException {
//    initialize(nullMsg);
//    assertThat(consoleOutput, containsString("Successfully connected"));
//  }
//
//  @Test
//  public void sendsMessageIfInputNotNull() throws IOException {
//    initialize(testMsg);
//    assertThat(consoleOutput, containsString("You sent:"));
//  }
//
//  @Test
//  public void doesNotSendMessageIfInputIsNull() throws IOException {
//    initialize(nullMsg);
//    verify(socketIo, never()).send(null);
//  }
//
//  @Test
//  public void receivesResponseIfInputNotNull() throws IOException {
//    initialize(testMsg);
//    assertThat(consoleOutput, containsString("Response from EchoServer"));
//  }
//
//  @Test
//  public void doesNotReceiveResponseIfInputIsNull() throws IOException {
//    initialize(nullMsg);
//    verify(socketIo, never()).receive();
//  }
//
//  @Test
//  public void canSendMultipleMessages() throws IOException {
//    initialize(multipleMsgs);
//    int echoCount = (consoleOutput.split("You sent").length) - 1;
//
//    assertEquals(2, echoCount);
//  }
//
//  @Test
//  public void canReceiveMultipleEchoes() throws IOException {
//    initialize(multipleMsgs);
//    int echoCount = (consoleOutput.split("Response").length) - 1;
//
//    assertEquals(2, echoCount);
//  }
//
//  @Test
//  public void inputOutputLoopClosesWhenUserInputsQuit() throws IOException {
//    initialize(testMsg);
//    assertThat(consoleOutput, containsString("Terminating session"));
//  }
//
//  @Test
//  public void closesConnection() throws IOException {
//    initialize(nullMsg);
//
//    assertThat(consoleOutput, containsString("disconnected"));
//  }
//}
