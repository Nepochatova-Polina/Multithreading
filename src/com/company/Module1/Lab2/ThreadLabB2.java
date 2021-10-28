package com.company.Module1.Lab2;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLabB2 {
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
            String name = Thread.currentThread().getName();
            while (isRunning.get()) {
                int currentID = getNextLine();
                System.out.println(name + " is working with the line #: " + currentID);
                for (int i = 0; i < 50; i++) {
                    if (matrix[currentID][i] == 1) {
                        isRunning.set(false);
                        resultLine.set(currentID);
                        System.out.println("Line is found: " + currentID);
                        break;
                    }
                }
            }
            System.out.println(name + " stopped.");
        };

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.submit(findValue);
        }
        executorService.shutdown();
    }

    static int getNextLine() {
        return currentLine.incrementAndGet();
    }
}
