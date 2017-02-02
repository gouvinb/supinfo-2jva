package com.supinfo.gameoflife;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameOfLife {

  public GameOfLife() {
    System.out.println("Initialization du GOL");
  }

  public void runGameOfLife1() {
    int numberOfColumns = 10;
    int numberOfLines = 10;
    World world = new World(numberOfColumns, numberOfLines);
    System.out.println(world);
    for (int i = 0; i < 10; i++) {
      clear();
      world.newGeneration();
      System.out.println(world);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void runGameOfLife2() {
    Scanner scanner = new Scanner(System.in);
    boolean retry = true;
    do {
      try {
        System.out.println("Please enter the number of columns of the world");
        int numberOfColumns = scanner.nextInt();
        System.out.println("Please enter the number of lines of the world");
        int numberOfLines = scanner.nextInt();
        retry = false;
        World world = new World(numberOfColumns, numberOfLines);
        System.out.println(world);
        for (int i = 0; i < 10; i++) {
          clear();
          world.newGeneration();
          System.out.println(world);
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      } catch (InputMismatchException e) {
        System.out.println("Care full you gave a letter");
        scanner = new Scanner(System.in);
      }
    } while (retry);
  }

  private void clear() {
    System.out.print("\u001b[2J" + "\u001b[H");
    System.out.flush();
  }
}