package com.company.Module1.Lab4.ThreadLab4A;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;

class ReadFileThread implements Runnable  {

    public void startThread() {
        Thread thr = new Thread(this, "ReadThread1");
        Thread thr2 = new Thread(this, "ReadThread2");
        thr.start();
        thr2.start();
    }

    public void ReadFile(ReadWriteLock lock) {
        lock.readLock().lock();
        try {
            File DatabaseFile = new File("Database.txt");
            Scanner currentFile = new Scanner(DatabaseFile);
            while (currentFile.hasNextLine()) {
                System.out.println(currentFile.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }


    @Override
    public void run() {
        ReadFile(ThreadMain.lock);
    }
}
