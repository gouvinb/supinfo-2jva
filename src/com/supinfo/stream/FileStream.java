package com.supinfo.stream;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class FileStream {

  public FileStream() {
    System.out.println("Initialisation de FileStream");
  }

  public void runFileStream() {
    try (Reader reader = new FileReader("src/com/supinfo/Main.java")) {
      Map<Character, Integer> result = new HashMap<>();
      int current;
      while (-1 != (current = reader.read())) {
        if (result.get((char) current) == null) {
          result.put((char) current, 1);
        } else {
          result.put((char) current, result.get((char) current) + 1);
        }
      }
      for (Map.Entry<Character, Integer> entry : result.entrySet()) {
        System.out.println("Key: '" + entry.getKey() + "', Value: " + entry.getValue());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
