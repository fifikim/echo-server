//package echoserver;
//
//import static org.junit.Assert.assertTrue;
//
//import echoserver.client.ClientRunner;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.PrintStream;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//public class ClientRunnerTest {
//  private final ByteArrayOutputStream out = new ByteArrayOutputStream();
//  private final PrintStream originalOut = System.out;
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
//    String[] args = {"127.0.0.1", "1234"};
//    ClientRunner.main(args);
//    assertTrue(out.toString().contains("127.0.0.1 on port 1234"));
//    assertTrue(out.toString().contains("Successfully connected"));
//  }
//
//  @Test
//  public void connectsToDefaultAddressIfNoneGiven() throws IOException {
//    String[] args = {};
//    ClientRunner.main(args);
//    assertTrue(out.toString().contains("localhost on port 8080"));
//    assertTrue(out.toString().contains("Successfully connected"));
//  }
//}