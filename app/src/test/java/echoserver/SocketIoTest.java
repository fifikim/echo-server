package echoserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.junit.Before;
import org.junit.Test;

public class SocketIoTest {
  private final String testMessage = "test message";
  private Socket clientSocket;
  private SocketIo socketIo;
  private ByteArrayInputStream inputStream;
  private ByteArrayOutputStream outputStream;

  @Before
  public void initialize() {
    socketIo = new SocketIo();
    clientSocket = mock(Socket.class);
    inputStream = new ByteArrayInputStream(testMessage.getBytes());
    outputStream = new ByteArrayOutputStream();
  }

  @Test
  public void createsSocketInputStream() throws IOException {
    when(clientSocket.getInputStream()).thenReturn(inputStream);

    assertNotNull(socketIo.createSocketInput(clientSocket));
  }

  @Test
  public void createsSocketOutputStream() throws IOException {
    when(clientSocket.getOutputStream()).thenReturn(outputStream);

    assertNotNull(socketIo.createSocketOutput(clientSocket));
  }

  @Test
  public void receivesSocketStreamInput() throws IOException {
    when(clientSocket.getInputStream()).thenReturn(inputStream);

    socketIo.createSocketInput(clientSocket);
    String actualReceived = socketIo.receive();

    assertEquals(testMessage, actualReceived);
  }

  @Test
  public void sendsSocketStreamOutput() throws IOException {
    when(clientSocket.getOutputStream()).thenReturn(outputStream);

    socketIo.createSocketOutput(clientSocket);
    socketIo.send(testMessage);

    assertEquals(testMessage, outputStream.toString().trim());
  }
}
