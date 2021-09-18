package com.company.Lab3;

;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;


public class ThreadLab3A {
    public static boolean potIsFull = false;
    public static int[] array = new int[50];
    public static int currentID = 0;

    public static void main(String[] args) {
        Thread bearThread = new Thread(() -> {
            while (!potIsFull) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("\n The pot was devastated by bear\n");
                System.out.println(Arrays.toString(array));
            array = null;

        });
        Runnable fillThePot = () -> {

            Random random = new Random();
            while (currentID < 49) {
                        String name = Thread.currentThread().getName();
                        int cellID = getNextID();
                        System.out.println(name + " is working with the cell #: " + cellID);
                        array[cellID] = random.nextInt(50);
            }
            potIsFull = true;
        };
        bearThread.start();
        new Thread(fillThePot, "Thread1").start();
        new Thread(fillThePot, "Thread2").start();
        new Thread(fillThePot, "Thread3").start();
        new Thread(fillThePot, "Thread4").start();

    }

    static synchronized int getNextID() {return ++currentID;}

}