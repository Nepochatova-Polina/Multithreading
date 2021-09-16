package com.company.Lab2;

import java.util.Random;

public class ThreadLabA {
    private static final int[][] matrix = new int[50][50];
    private static int currentLine = -1;
    private static boolean isRunning = true;

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                matrix[i][j] = 0;
            }
        }
        Random random = new Random();
        matrix[random.nextInt(50)][random.nextInt(50)] = 1;

        Runnable findValue = () -> {
            String name = Thread.currentThread().getName();
            while (isRunning) {
                int currentID = getNextLine();
                System.out.println(name + " is working with the line #: " + currentID);
                for (int i = 0; i < 50; i++) {
                    if (matrix[currentID][i] == 1) {
                        isRunning = false;
                        System.out.println("Line is found: " + currentID);
                        break;
                    }
                }
            }
            System.out.println(name + " stopped.");

        };

        new Thread(findValue, "Thread1").start();
        new Thread(findValue, "Thread2").start();
        new Thread(findValue, "Thread3").start();
    }

    static synchronized int getNextLine() {
        return ++currentLine;
    }
}
