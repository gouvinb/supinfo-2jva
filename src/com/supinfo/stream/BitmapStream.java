package com.supinfo.stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BitmapStream {

  public BitmapStream() {
    System.out.println("Initialisation de BitmapStream");
  }

  public void runBitmapStream() {
    try {
      List<Integer> integers = readFile(new FileInputStream("bitmap.bmp"));

      List<Integer> subList = integers.subList(138, integers.size());
      addToPixel(subList, 200);

      FileOutputStream outputFile = new FileOutputStream("bitmap_new.bmp");
      writeToFile(outputFile, integers.subList(0, 138));
      writeToFile(outputFile, subList);

      try {
        outputFile.flush();
        if (outputFile != null) {
          outputFile.close();
        }
      } catch (IOException e) {

        e.printStackTrace();

      }

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private List<Integer> readFile(FileInputStream inputStream) {
    List<Integer> integers = new ArrayList<>();
    try {
      int buff;
      while (-1 != (buff = inputStream.read())) {
        integers.add(buff);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    if (inputStream != null) {
      try {
        inputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return integers;
  }

  private void addToPixel(List<Integer> pixels, int value) {
    for (int i = 0; i < pixels.size(); i++) {
      int pixel = pixels.get(i);
      pixel = (pixel + value % 255);
      pixels.set(i, pixel);
    }
  }

  private void writeToFile(FileOutputStream fileOutputStream, List<Integer> pixels) {
    try {
      for (Integer pixel : pixels) {
        fileOutputStream.write(pixel);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
