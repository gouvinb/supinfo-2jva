package com.supinfo;

import com.supinfo.jzipper.Launcher;
import com.supinfo.stream.FileStream;

/**
 * ==> https://github.com/gouvinb/supinfo-2jva
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

    // GameOfLife gameOfLife = new GameOfLife();
    // gameOfLife.runGameOfLife2();

    // BitmapStream bitmapStream = new BitmapStream();
    // bitmapStream.runFileStream();

    // FileStream fileStream = new FileStream();
    // fileStream.runFileStream();

    Launcher launcher = new Launcher();
    launcher.runJZipper(args);
  }
}
