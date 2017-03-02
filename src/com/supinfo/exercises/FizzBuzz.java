package com.supinfo.exercises;

public class FizzBuzz {

  public FizzBuzz() {
    System.out.println("Initialization du FizzBuzz");
  }

  @SuppressWarnings("SameParameterValue")
  public void runFizzBuzz(int start, int end) {
    if (start >= end) {
      return;
    }

    String result = "";
    while (start < end) {
      if (start % 3 == 0 && start % 5 == 0) {
        result += "fizzbuzz";
      } else if (start % 3 == 0) {
        result += "fizz";
      } else if (start % 5 == 0) {
        result += "buzz";
      } else {
        result += start;
      }

      start++;
      if (start % 10 == 0) {
        result += "\n";
      } else {
        result += " ";
      }
    }

    System.out.println(result);
  }
}








