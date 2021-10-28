package com.company.Module1.Lab3;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ThreadLab3B {
    public  static  CountDownLatch potIsFull = new CountDownLatch(49);
    public static int[] array = new int[50];
    public static int currentID = 0;

    public static void main(String[] args) {
        Thread bearThread = new Thread(() -> {
                try {
                    potIsFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
                potCountDown();
            }
        };
        bearThread.start();
        new Thread(fillThePot, "Thread1").start();
        new Thread(fillThePot, "Thread2").start();
        new Thread(fillThePot, "Thread3").start();
        new Thread(fillThePot, "Thread4").start();

    }

    static synchronized int getNextID() {return ++currentID;}
    static synchronized void potCountDown() {potIsFull.countDown();}

}