package com.company.Module1.Lab4.ThreadLab4A;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;

public class FindByNumberThread extends Thread {
    private static String[] array = new String[] {" +38000000001","+38000000004","+38000000006","+38000000010","+38000000009"};
    private static final AtomicInteger index =  new AtomicInteger(-1);

    public void startThread() {
        Thread thr = new Thread(this, "ThreadByNumb1");
        Thread thr2 = new Thread(this, "ThreadByNumb2");
        thr.start();
        thr2.start();
    }

    public void FindByNumber(String number, ReadWriteLock lock)  {
        lock.readLock().lock();
        try {
            File DatabaseFile = new File("Database.txt");
            Scanner currentFile = new Scanner(DatabaseFile);
            while (currentFile.hasNextLine()) {
                String str = currentFile.nextLine();
                if (str.contains(number)) {
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
            FindByNumber(array[index.incrementAndGet()],ThreadMain.lock);
        }
        }

}
