package echoserver;

import java.io.IOException;

public class App {
  public String getGreeting() {
    return "Hello World!";
  }

  public static void main(String[] args) throws IOException {
    System.out.println(new App().getGreeting());

    EchoServer echoServer = new EchoServer();
  }
}
