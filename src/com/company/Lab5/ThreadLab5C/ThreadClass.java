package com.company.Lab5.ThreadLab5C;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

public class ThreadClass extends Thread {
    private static final int[] array = new int[20];
    public int sum = 0;
    public static Random random = new Random();

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Run");
        for (int i = 0; i < 10; i++) {
            array[i] = random.nextInt(2);
            sum += array[i];
        }
        System.out.println(Thread.currentThread().getName() +" " + sum);
        try {
            while (!currentThread().isInterrupted()) {
                int i = random.nextInt(20);
                if (array[i] > 1) {
                    array[i]--;
                    sum = countSum(array);
                } else if(array[i] <= 1){
                    array[i]++;
                    sum = countSum(array);
                }
                ThreadLab5C.barrier.await();
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
    public static int countSum(int []array){
        int sum = 0;
        for (int j : array) {
            sum += j;
        }
        return sum;
    }

}
