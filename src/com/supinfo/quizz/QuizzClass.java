package com.supinfo.quizz;

/**
 * QuizzClass
 */
public class QuizzClass {

    public QuizzClass() {
        System.out.println("initialisation du quizz");
    }

    @SuppressWarnings("ConstantConditions")
    public void questionUn() {
        System.out.println("Question 1 :");
        long aLong = 0;
        System.out.println(aLong);

        aLong = 3;
        System.out.println(aLong);

        boolean aBoolean = false;
        System.out.println(aBoolean);

        // double aDouble;
        double aDouble = 1;
        System.out.println(aDouble);
    }

    @SuppressWarnings("ConstantConditions")
    public void questionDeux() {
        System.out.println("Question 2 :");
        int a = 5;
        boolean b;
        b = a < 10;

        int i = 4;
        int j = 5 + 9 * i;
        int k = 124 % 10;

        System.out.println(a);
        System.out.println(b);
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
    }

    public void questionTrois() {
        System.out.println("Question 3 :");
        int v = 0;
        v++;
        int amount = v++;
        System.out.println(++v + " " + amount);
        System.out.println(v);
    }

    public void questionQuatre() {
        System.out.println("Question 4 :");
        boolean canITakeHisMoney;
        int hisBalance = 5;
        long myBalance = 4;
        hisBalance += 8;
        canITakeHisMoney = hisBalance > myBalance;
        canITakeHisMoney = canITakeHisMoney && (hisBalance >= 3);
        System.out.println(canITakeHisMoney);
    }
}
