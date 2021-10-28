package com.company.Module1.Lab4.ThreadLab4B;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class ThreadMain {
    private static final int[][] matrix = new int[20][20];
    private static final Random random = new Random();
    private static boolean readLock = false,writeLock = false;

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                matrix[i][j] = random.nextInt(2);
            }

        }
        Thread Nature = new Thread(() -> {
            while (true) {
                if(!readLock && !writeLock) {
                    writeLock = true;
                    System.out.println("Nature is growing plants...");
                    try {
                        for(int i = 0; i < 10;i++) {
                            matrix[random.nextInt(20)][random.nextInt(20)] = 0;
                        }
                        writeLock = false;
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread Gardener = new Thread(() -> {
            while (true) {
                if(!readLock && !writeLock) {
                    writeLock = true;
                    System.out.println("Gardener is watering plants...");
                    try {
                        for (int i = 0; i < 20; i++) {
                                if (matrix[random.nextInt(20)][random.nextInt(20)] == 0) {
                                    matrix[random.nextInt(20)][random.nextInt(20)] = 1;
                                }
                        }
                        writeLock = false;
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread writeIntoFile = new Thread(() -> {
            while (true) {
                if ( !writeLock) {
                    readLock = true;
                    System.out.println("Updating file");
                    try {
                        FileWriter writer = new FileWriter("Garden.txt");
                        writer.write(Arrays.deepToString(matrix));
                        writer.close();
                        readLock = false;
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread consoleOutput = new Thread(() -> {
            StringBuilder str = new StringBuilder();
            while (true) {
                if (!writeLock) {
                    readLock = true;
                    try {
                        for (int i = 0; i < 20; i++) {
                            for (int j = 0; j < 20; j++) {
                                str.append(matrix[i][j]);
                            }
                            System.out.println(str);
                            str.delete(0, 20);
                        }
                        readLock = false;
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Nature.start();
        Gardener.start();
        consoleOutput.start();
        writeIntoFile.start();
    }
}
