package com.company.Lab4.ThreadLab4A;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;

public class FindByNameThread implements Runnable{
    private static final String[] array = new String[] {"Сидоров","Петрова","Петров","Королева","Коровкин"};
    private static final AtomicInteger index = new AtomicInteger(-1);
    public void startThread() {
        Thread thr = new Thread(this, "ThreadByName1");
        Thread thr2 = new Thread(this, "ThreadByName2");
        thr.start();
        thr2.start();
    }
    private static void FindByName(String secondNname, ReadWriteLock lock) {
        lock.readLock().lock();
        try {
            File DatabaseFile = new File("Database.txt");
            Scanner currentFile = new Scanner(DatabaseFile);
            while (currentFile.hasNextLine()) {
                String str = currentFile.nextLine();
                if (str.contains(secondNname)) {
                    System.out.println("Thread " + Thread.currentThread().getName() +":"+str);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void run() {
        while (index.get() < 4 ){
            FindByName(array[index.incrementAndGet()],ThreadMain.lock);
        }
    }
}
