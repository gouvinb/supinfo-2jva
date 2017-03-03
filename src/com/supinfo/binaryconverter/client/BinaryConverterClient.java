package com.supinfo.binaryconverter.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by gouvinb on 01/03/2017.
 */
public class BinaryConverterClient {

  public BinaryConverterClient() {
    System.out.println("Initialisation de BinaryConverterClient");
  }

  public void runBinaryConverterClient() {
    try (Socket socket = new Socket("localhost", 6666);
         Writer writer = new PrintWriter(socket.getOutputStream());
         Reader reader = new InputStreamReader(socket.getInputStream())) {

      System.out.println("Give me a number: ");
      Scanner scanner = new Scanner(System.in);
      int number = scanner.nextInt();
      System.out.println("Number is: " + number);

      writer.write(number);
      writer.flush();

      String result = getResult(reader);
      System.out.println(result);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static String getResult(Reader reader) throws IOException {
    int read;
    StringBuilder builder = new StringBuilder();
    while (-1 != (read = reader.read())) {
      builder.append((char) read);
    }
    return builder.toString();
  }
}
