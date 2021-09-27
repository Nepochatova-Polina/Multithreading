package com.company.Lab4.ThreadLab4A;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;

public class DeleteThread implements Runnable{

    private static final String[] array = new String[] {" +38000000001","Королев","Иванова Иванна","Сергеевна","Коровкин Василий Петрович +38000000001"};
    private static final AtomicInteger index =  new AtomicInteger(0);

    public void startThread() {
        Thread thr = new Thread(this, "DeleteThread1");
        Thread thr2 = new Thread(this, "DeleteThread2");
        thr.start();
        thr2.start();
    }
    public void DeleteFromFile(String info, ReadWriteLock lock) throws IOException {
        lock.writeLock().lock();
        StringBuilder newFile = new StringBuilder();
        File DatabaseFile = new File("Database.txt");
        Scanner currentFile = new Scanner(DatabaseFile);
        String deletedInfo = null;
        try {
            while (currentFile.hasNextLine()) {
                String s = currentFile.nextLine();
                if (!Objects.equals(s, info)) {
                    newFile.append(s);
                    newFile.append("\n");
                }
            }
            FileWriter writer = new FileWriter("Database.txt");
            writer.write(String.valueOf(newFile));
            writer.close();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void run() {
        while (index.get() < 4 ){
            try {
                DeleteFromFile(array[index.incrementAndGet()],ThreadMain.lock);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
