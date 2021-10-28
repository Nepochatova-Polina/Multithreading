package com.company.Module1.Lab4.ThreadLab4A;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;

public class AddInFileThread implements Runnable {
    private static final String[] array = new String[] {"Сидоров Михаил Иванович +38000000012",
                                                        "Питякова Ирина Викторовна +38000000013",
                                                        "Паровая Инна Игоревна +38000000014",
                                                        "Куличикова Ольга Степановна +38000000015",
                                                        "Курочкнин Исаак Ихтанбекович +38000000016"};
    private static final AtomicInteger index =  new AtomicInteger(-1);

    public void startThread() {
        Thread thr = new Thread(this, "AddInFileThread1");
        Thread thr2 = new Thread(this, "AddInFileThread2");
        thr.start();
        thr2.start();
    }

    public void AddIntoFile(String info, ReadWriteLock lock){
        lock.writeLock().lock();
        try {
            Files.write(Paths.get("Database.txt"),info.getBytes(), StandardOpenOption.APPEND);
            Files.write(Paths.get("Database.txt"),"\n".getBytes(), StandardOpenOption.APPEND);
            System.out.println("Thread " + Thread.currentThread().getName() +" add into file : " + info);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void run() {
        while (index.get() < 4 ){
            AddIntoFile(array[index.incrementAndGet()],ThreadMain.lock);
        }
    }

}
