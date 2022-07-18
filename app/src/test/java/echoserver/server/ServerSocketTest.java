package echoserver.server;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import echoserver.SocketIo;
import echoserver.TestHelpers;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Test;

public class ServerSocketTest {
  private final String testMessage = "test message";
  private ServerSocket serverSocket;
  private Socket clientSocket;
  private SocketIo socketIo;
  private ServerSocketInterface serverSocketInterface;
  private ByteArrayInputStream inputStream;
  private ByteArrayOutputStream outputStream;

  public void initialize() throws IOException {
    inputStream = new ByteArrayInputStream(testMessage.getBytes());
    outputStream = new ByteArrayOutputStream();

    serverSocket = mock(ServerSocket.class);
    clientSocket = TestHelpers.socket(inputStream, outputStream, 90210);
    when(serverSocket.accept()).thenReturn(clientSocket);

    socketIo = TestHelpers.socketIo(testMessage);
    serverSocketInterface = new ServerSocketWrapper(clientSocket);
  }

  public void initializeWithMockStreams() throws IOException {
    inputStream = mock(ByteArrayInputStream.class);
    outputStream = mock(ByteArrayOutputStream.class);

    serverSocket = mock(ServerSocket.class);
    clientSocket = TestHelpers.socket(inputStream, outputStream, 90210);
    when(serverSocket.accept()).thenReturn(clientSocket);

    socketIo = mock(SocketIo.class);
    serverSocketInterface = new ServerSocketWrapper(clientSocket);
  }

  @Test
  public void assignsClientId() throws IOException {
    initialize();
    assertEquals("EchoClient90210", serverSocketInterface.assignId());
  }

  @Test
  public void receivesClientMessage() throws IOException {
    initialize();
    String actualReceived = serverSocketInterface.receiveMessage();

    assertEquals(testMessage, actualReceived);
  }

  @Test
  public void echoesClientMessage() throws IOException {
    initialize();
    serverSocketInterface.sendEcho(testMessage);

    assertEquals(testMessage, serverSocketInterface.sendEcho(testMessage));
  }

  @Test
  public void closesConnectionAndStreams() throws IOException {
    initializeWithMockStreams();
    serverSocketInterface.closeSocket();

    verify(clientSocket).close();
    verify(inputStream).close();
    verify(outputStream).close();
  }
}
