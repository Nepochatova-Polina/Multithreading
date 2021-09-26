package com.company.Lab4.ThreadLab4A;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;

public class ReaderThread extends Thread {

    public void FindInFileByName(String secondNname, ReadWriteLock lock) {
        lock.readLock().lock();
        try {
            File DatabaseFile = new File("Database.txt");
            Scanner currentFile = new Scanner(DatabaseFile);
            while (currentFile.hasNextLine()) {
                String str = currentFile.nextLine();
                if (str.contains(secondNname)) {
                    System.out.println(str);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
    public void FindInFileByNumber(String number, ReadWriteLock lock)  {
        lock.readLock().lock();
        try {
            File DatabaseFile = new File("Database.txt");
            Scanner currentFile = new Scanner(DatabaseFile);
            while (currentFile.hasNextLine()) {
                String str = currentFile.nextLine();
                if (str.contains(number)) {
                    System.out.println(str);
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
        super.run();
    }
}
