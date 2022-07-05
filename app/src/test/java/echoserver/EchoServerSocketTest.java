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
//public class EchoServerSocketTest {
//  private final ByteArrayOutputStream out = new ByteArrayOutputStream();
//  private final PrintStream originalOut = System.out;
//  private InetAddress host = InetAddress.getByName("127.0.0.1");
//  private int port = 1234;
//  private EchoServerSocket testServerSocket = new EchoServerSocket();
//  private EchoClientSocket testClientSocket = new EchoClientSocket();
//
//  public EchoServerSocketTest() throws UnknownHostException {
//  }
//
//  @Before
//  public void setStreams() throws IOException {
//    System.setOut(new PrintStream(out));
//  }
//
//  @After
//  public void restoreInitialStreams() throws IOException {
//    System.setOut(originalOut);
//  }
//
//  @Test
//  public void startsServerAtGivenPort() throws IOException {
//    testServerSocket.open(port);
//    assertTrue(out.toString().contains("listening on port 1234"));
//  }
//
//  @Test
//  public void acceptsClientConnection() throws IOException {
//    testClientSocket.connect(host, port);
//
//    testServerSocket.acceptClient();
//    assertTrue(out.toString().contains("now connected"));
//  }
//
//  @Test
//  public void closesConnection() throws IOException {
//    testServerSocket.close();
//
//    assertTrue(out.toString().contains("connection closed"));
//  }
//}
