package echoserver;

public class EchoClient {
  public EchoClient() {

  }

  public static void main(String[] args) {
    EchoClient client = new EchoClient();
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