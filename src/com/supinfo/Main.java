package com.supinfo;

import com.supinfo.exercises.FizzBuzz;
import com.supinfo.exercises.Sorter;
import com.supinfo.gameoflife.GameOfLife;
import com.supinfo.quizz.QuizzClass;

/**
 * Main
 */
public class Main {

  public static void main(String[] args) {
    System.out.println("Hello Dude!");

    // QuizzClass quizz = new QuizzClass();
    // quizz.questionUn();
    // quizz.questionDeux();
    // quizz.questionTrois();
    // quizz.questionQuatre();

    // FizzBuzz fizzBuzz = new FizzBuzz();
    // fizzBuzz.runFizzBuzz(1, 100);

    // Sorter sorter = new Sorter();
    // sorter.runSorter();

    GameOfLife gameOfLife = new GameOfLife();
    gameOfLife.runGameOfLife1();
  }
}
