package com.company.Lab5.ThreadLab5C;

import java.util.concurrent.CyclicBarrier;

public class ThreadLab5C {
    public static CyclicBarrier barrier = new CyclicBarrier(3, new CompareSum());
    public static ThreadClass firstThread = new ThreadClass();
    public static ThreadClass secondThread = new ThreadClass();
    public static ThreadClass thirsThread = new ThreadClass();

    public static void main(String[] args) {
        firstThread.start();
        secondThread.start();
        thirsThread.start();
    }

}
