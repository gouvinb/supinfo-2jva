package com.supinfo.exercises;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class Sorter {

  public Sorter() {
    System.out.println("Initialization du Sorter");
  }

  // methode main
  public void runSorter() {
    int[] randomArray = new int[10];
    Random random = new Random();

    for (int i = 0; i < randomArray.length; i++) {
      randomArray[i] = random.nextInt(100);
    }

    System.out.println("Random Array");
    this.displayArray(randomArray);
    System.out.println("\nBubble Sort");
    displayArray(bubbleSort(randomArray));

    System.out.println("\nSelection Sort");
    displayArray(selectionSort(randomArray));

    System.out.println("\nInsert Sort");
    displayArray(insertSort(randomArray));
  }

  private int[] bubbleSort(int[] randomArray) {
    int[] array = Arrays.copyOf(randomArray, randomArray.length);
    boolean swapped;
    do {
      swapped = false;
      for (int i = 1; i < array.length; i++) {
        if (array[i - 1] > array[i]) {
          int tmp = array[i - 1];
          array[i - 1] = array[i];
          array[i] = tmp;
          swapped = true;
        }
      }
    } while (swapped);
    return array;
  }

  private int[] selectionSort(int[] randomArray) {
    int[] array = Arrays.copyOf(randomArray, randomArray.length);
    for (int i = 0; i < array.length; i++) {
      int minIndex = i;

      for (int j = i + 1; j < array.length - 1; j++) {
        if (array[j] < array[minIndex]) {
          minIndex = j;
        }
      }
      if (i != minIndex) {
        int tmp = array[i];
        array[i] = array[minIndex];
        array[minIndex] = tmp;
      }
    }
    return array;
  }

  private int[] insertSort(int[] randomArray) {
    int[] array = Arrays.copyOf(randomArray, randomArray.length);
    for (int i = 1; i < array.length; i++) {
      int valueToInsert = array[i];
      int holdPosition = i;

      while (holdPosition > 0 && valueToInsert < array[holdPosition - 1]) {
        array[holdPosition] = array[holdPosition - 1];
        holdPosition--;
      }
      array[holdPosition] = valueToInsert;
    }
    return array;
  }

  /**
   * equivalent de Arrays.toString(randomArray).
   *
   * @param array tableau de int
   */
  private void displayArray(int[] array) {
    for (int i = 0; i < array.length; i++) {
      if (i == 0) {
        System.out.print("[" + array[i] + ", ");
      } else if (i == array.length - 1) {
        System.out.print(array[i] + "]");
      } else {
        System.out.print(array[i] + ", ");
      }
    }
  }
}
