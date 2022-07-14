package echoserver;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.junit.Test;

public class SocketIoTest {
  private final String testMessage = "test message";
  private SocketIo socketIo;
  private ByteArrayOutputStream outputStream;
  private ByteArrayInputStream inputStream;

  public void initialize() throws IOException {
    inputStream = new ByteArrayInputStream(testMessage.getBytes());
    outputStream = new ByteArrayOutputStream();

    Socket clientSocket = mock(Socket.class);
    when(clientSocket.getInputStream()).thenReturn(inputStream);
    when(clientSocket.getOutputStream()).thenReturn(outputStream);

    socketIo = new SocketIo(clientSocket);
  }

  public void initializeWithMockStreams() throws IOException {
    inputStream = mock(ByteArrayInputStream.class);
    outputStream = mock(ByteArrayOutputStream.class);

    Socket clientSocket = mock(Socket.class);
    when(clientSocket.getInputStream()).thenReturn(inputStream);
    when(clientSocket.getOutputStream()).thenReturn(outputStream);

    socketIo = new SocketIo(clientSocket);
  }

  @Test
  public void receivesSocketStreamInput() throws IOException {
    initialize();
    String actualReceived = socketIo.receive();

    assertEquals(testMessage, actualReceived);
  }

  @Test
  public void sendsSocketStreamOutput() throws IOException {
    initialize();
    socketIo.send(testMessage);

    assertEquals(testMessage, outputStream.toString().trim());
  }

  @Test
  public void closesIoStreams() throws IOException {
    initializeWithMockStreams();
    socketIo.closeStreams();

    verify(inputStream).close();
    verify(outputStream).close();
  }
}
