package echoserver;

import java.io.IOException;
import java.net.Socket;

public class EchoClient {
  public void startConnection(String ip, int port) throws IOException {
    Socket clientSocket = new Socket(ip, port);
  }
}

/*
client steps
  {step 1a on server side}
1b - open a connection
  {steps 2-3 on server side}
4 - send message to the server
5 - receive message back from the server
6 - disconnects
  {step 7 on server end}
 */