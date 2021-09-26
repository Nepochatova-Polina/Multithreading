package com.company.Lab4.ThreadLab4A;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReaderThread {

    public void FindInFileByName(String secondNname,ReadWriteLock lock) {
        lock.readLock().lock();
        try {
            //code
        } finally {
            lock.readLock().unlock();
        }
    }

    public void FindInFileByNumber(int number,ReadWriteLock lock){
        lock.readLock().lock();
        try {
            //code
        } finally {
            lock.readLock().unlock();
        }
    }
}
