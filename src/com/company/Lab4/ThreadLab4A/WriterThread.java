package com.company.Lab4.ThreadLab4A;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriterThread {


    public void ReadFile(ReadWriteLock lock) {
        lock.readLock().lock();
        try {
            //code
        } finally {
            lock.readLock().unlock();
        }
    }
    public void AddIntoFile(String info,ReadWriteLock lock) {
        lock.writeLock().lock();
        try {
            //code
        } finally {
            lock.writeLock().unlock();
        }
    }
    public void DeleteFromFile(String info,ReadWriteLock lock) {
        lock.writeLock().lock();
        try {
            //code
        } finally {
            lock.writeLock().unlock();
        }
    }

}
