package echoserver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestHelpers {
  public static void restoreInitialStreams() {
    System.setOut(System.out);
    System.setIn(System.in);
  }

  public static ByteArrayOutputStream setConsoleOutput() {
    ByteArrayOutputStream mockOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(mockOut));

    return mockOut;
  }

  public static void setConsoleInput(String message) {
    ByteArrayInputStream mockIn = new ByteArrayInputStream(message.getBytes());
    System.setIn(mockIn);
  }

  public static SocketIo setSocketIo(Socket clientSocket, String message) throws IOException {
    InputStream inputStream = new ByteArrayInputStream(message.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    when(clientSocket.getInputStream()).thenReturn(inputStream);
    when(clientSocket.getOutputStream()).thenReturn(outputStream);

    SocketIo socketIo = mock(SocketIo.class);
    when(socketIo.receive()).thenReturn(message);
    when(socketIo.send(message)).thenReturn(message);

    return socketIo;
  }

}
