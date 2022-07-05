//package echoserver;
//
//import static org.junit.Assert.assertTrue;
//
//import echoserver.client.EchoClientSocket;
//import echoserver.server.EchoServerSocket;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.PrintStream;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//public class EchoClientSocketTest {
//  private final ByteArrayOutputStream out = new ByteArrayOutputStream();
//  private final PrintStream originalOut = System.out;
//  private InetAddress host = InetAddress.getByName("127.0.0.1");
//  private int port = 1234;
//  private EchoClientSocket testClientSocket = new EchoClientSocket();
//  private EchoServerSocket testServerSocket = new EchoServerSocket();
//
//  public EchoClientSocketTest() throws UnknownHostException {
//  }
//
//  @Before
//  public void setStreams() {
//    System.setOut(new PrintStream(out));
//  }
//
//  @After
//  public void restoreInitialStreams() {
//    System.setOut(originalOut);
//  }
//
//  @Test
//  public void connectsToGivenAddress() throws IOException {
//    testServerSocket.open(port);
//    testClientSocket.connect(host, port);
//    assertTrue(out.toString().contains("127.0.0.1 on port 1234"));
//    assertTrue(out.toString().contains("Successfully connected"));
//  }
//
//  @Test
//  public void closesSocket() throws IOException {
//    testClientSocket.close();
//    assertTrue(out.toString().contains("EchoClient disconnected."));
//  }
//}