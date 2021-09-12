package com.company.Lab2;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLab {
    private static final int[][] matrix = new int[50][50];
    private static final AtomicInteger resultLine = new AtomicInteger(-1);
    private static final AtomicInteger currentLine = new AtomicInteger(-1);
    private static final AtomicBoolean isRunning = new AtomicBoolean(true);

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                matrix[i][j] = 0;
            }
        }
        Random random = new Random();
        matrix[random.nextInt(50)][random.nextInt(50)] = 1;

        Runnable findValue = () -> {
            long threadId = Thread.currentThread().getId();
            while (isRunning.get()) {
                int currentID = getNextLine();
                System.out.println("Thread # " + threadId + " is working with the line #: " + currentID);
                for (int i = 0; i < 50; i++) {
                    if (matrix[currentID][i] == 1) {
                        isRunning.set(false);
                        resultLine.set(currentID);
                        System.out.println("Line is found: " + currentID);
                        break;
                    }
                }
            }
            System.out.println("Stopped.");
        };

        new Thread(findValue).start();
        new Thread(findValue).start();
        new Thread(findValue).start();
    }

    static synchronized int getNextLine() {
        return currentLine.incrementAndGet();
    }
}
