package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
  public static void print(String output) {
    System.out.println(output);
  }

  public static void err(String message, IOException e) {
    System.out.println(message);
    System.out.println(e);
    System.exit(1);
  }

  public static String input() throws IOException {
    BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
    return consoleIn.readLine();
  }
}
