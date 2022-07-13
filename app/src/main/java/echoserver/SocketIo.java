package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketIo {
  private final Socket clientSocket;
  public BufferedReader in;
  public PrintWriter out;

  public SocketIo(Socket clientSocket) throws IOException {
    this.clientSocket = clientSocket;
    in = createSocketInput();
    out = createSocketOutput();
  }

  public String send(String message) {
    out.println(message);
    return message;
  }

  public String receive() throws IOException {
    return in.readLine();
  }

  public boolean closeStreams() throws IOException {
    in.close();
    out.close();
    return true;
  }

  private BufferedReader createSocketInput() throws IOException {
    return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  }

  private PrintWriter createSocketOutput() throws IOException {
    return new PrintWriter(clientSocket.getOutputStream(), true);
  }
}
