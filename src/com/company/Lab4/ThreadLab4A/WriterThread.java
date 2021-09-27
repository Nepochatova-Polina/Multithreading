package com.company.Lab4.ThreadLab4A;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;

class ReadFileThread implements Runnable  {

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

    }
}
