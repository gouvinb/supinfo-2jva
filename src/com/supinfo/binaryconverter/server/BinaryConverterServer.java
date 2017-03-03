package com.supinfo.binaryconverter.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gouvinb on 01/03/2017.
 */
public class BinaryConverterServer {

  private static final ExecutorService service = Executors.newFixedThreadPool(10);

  public BinaryConverterServer() {
    System.out.println("Initialisation de BinaryConverterSever");
  }

  @SuppressWarnings("InfiniteLoopStatement")
  public void runBinaryConverterServer() {
    try {
      ServerSocket listener = new ServerSocket(6666);
      while (true) {
        System.out.println("Waiting connections");
        Socket socket = listener.accept();
        System.out.println("New connection !");
        service.execute(new BinaryConverterService(socket));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
