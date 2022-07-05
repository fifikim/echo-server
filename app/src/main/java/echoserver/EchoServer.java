package echoserver;

public class EchoServer {
  public EchoServer() {
  }

  public static void main(String[] args) {
    EchoServer server = new EchoServer();
  }
}

/*
server steps
1a - open a connection (create server socket & accept connection)
  {step 1b on client side}
2 - open input and output streams (set up flag to decide if the connection should stay open)
3 - start a do while input loop
  {steps 4 -6 on client side}
7 - close the connection
 */