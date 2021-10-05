package com.company.Lab5.ThreadLab5A;

import java.util.Random;

public class ThreadMain {
    public static int[] array = new int[20];
    public static Random random = new Random();
    public static boolean mainFlag = false;
    public static boolean runAgainFlag = false;
    public static int counter = 4;

    public static void main(String[] args) throws InterruptedException {
        StringBuilder x = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            array[i] = random.nextInt(2);
            x.append(array[i]);
        }
        System.out.println(x);
        x.delete(0, 20);
        ThreadClass first = new ThreadClass(0, 4);
        ThreadClass second = new ThreadClass(5, 9);
        ThreadClass third = new ThreadClass(9, 14);
        ThreadClass fourth = new ThreadClass(14, 19);
        first.start();
        second.start();
        third.start();
        fourth.start();
        while (!mainFlag) {
            Thread.sleep(1000);
            if (counter == 0) {
                compareResult();
                for (int i = 0; i < 20; i++) {
                    x.append(array[i]);
                }
                System.out.println(x);
                x.delete(0, 20);
            }
        }
    }

    public static void compareResult() {
        for (int i = 0; i < 20; i++) {
            if (array[i] != array[++i]) {
                mainFlag = false;
                runAgainFlag = true;
                counter = 4;
                break;
            }
            mainFlag = true;
        }
    }
}
