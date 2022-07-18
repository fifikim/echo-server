package echoserver.server;

import echoserver.ConsoleIo;
import java.io.IOException;

public class EchoServer implements Runnable {
  private final ServerSocketInterface socketInterface;

  public EchoServer(ServerSocketInterface socketInterface) {
    this.socketInterface = socketInterface;
  }

  @Override
  public void run() {
    try {
      String message;
      while ((message = socketInterface.receiveMessage()) != null) {
        socketInterface.sendEcho(message);
      }
    } catch (IOException e) {
      ConsoleIo.err("Unable to accept client connection", e);
    } finally {
      try {
        socketInterface.closeSocket();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
