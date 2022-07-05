//package echoserver;
//
//import static org.junit.Assert.assertTrue;
//
//import echoserver.server.ServerRunner;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.PrintStream;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//public class ServerRunnerTest {
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
//  public void startsServerAtGivenPort() throws IOException {
//    String[] args = {"1234"};
//    ServerRunner.main(args);
//    assertTrue(out.toString().contains("listening on port 1234"));
//  }
//
//  @Test
//  public void startsServerAtDefaultPortIfNoneGiven() throws IOException {
//    String[] args = {};
//    ServerRunner.main(args);
//    assertTrue(out.toString().contains("listening on port 8080"));
//  }
//}
