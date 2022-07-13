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
  private SocketIo socketIo;
  private ByteArrayOutputStream outputStream;

  @Before
  public void initialize() throws IOException {
    ByteArrayInputStream inputStream = new ByteArrayInputStream(testMessage.getBytes());
    outputStream = new ByteArrayOutputStream();

    Socket clientSocket = mock(Socket.class);
    when(clientSocket.getInputStream()).thenReturn(inputStream);
    when(clientSocket.getOutputStream()).thenReturn(outputStream);

    socketIo = new SocketIo(clientSocket);
  }

  @Test
  public void createsSocketInputStream() {
    assertNotNull(socketIo.in);
  }

  @Test
  public void createsSocketOutputStream() {
    assertNotNull(socketIo.out);
  }

  @Test
  public void receivesSocketStreamInput() throws IOException {
    String actualReceived = socketIo.receive();

    assertEquals(testMessage, actualReceived);
  }

  @Test
  public void sendsSocketStreamOutput() {
    socketIo.send(testMessage);

    assertEquals(testMessage, outputStream.toString().trim());
  }
}
