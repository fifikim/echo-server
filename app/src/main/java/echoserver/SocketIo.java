package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketIo {
  BufferedReader in;
  PrintWriter out;

  public SocketIo() {
    this.in = null;
    this.out = null;
  }

  public BufferedReader createSocketInput(Socket clientSocket) throws IOException {
    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    return in;
  }

  public PrintWriter createSocketOutput(Socket clientSocket) throws IOException {
    out = new PrintWriter(clientSocket.getOutputStream(), true);
    return out;
  }

  public void send(String message) {
    out.println(message);
  }

  public String receive() throws IOException {
    return in.readLine();
  }

  public void close() throws IOException {
    in.close();
    out.close();
  }
}
