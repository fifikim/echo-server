package echoserver;

import java.io.IOException;
import java.net.ServerSocket;

public class EchoServer {
  ServerSocket serverSocket;

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(8080);
  }

  public int getPort() {
    return serverSocket.getLocalPort();
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