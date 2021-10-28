package com.company.Module1.Lab4.ThreadLab4A;


import java.io.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ThreadMain {
    public static final ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws IOException {
        FindByNameThread threadByName = new FindByNameThread();
        threadByName.startThread();
        FindByNumberThread threadByNum = new FindByNumberThread();
        threadByNum.startThread();
        AddInFileThread addThread = new AddInFileThread();
        addThread.startThread();
        DeleteThread deleteThread = new DeleteThread();
        deleteThread.startThread();
    }
}