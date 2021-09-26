package com.company.Lab4.ThreadLab4A;


import java.io.*;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ThreadMain {

    public static void main(String[] args) throws IOException {
        ReadWriteLock lock = new ReentrantReadWriteLock();

//        thread1.AddIntoFile("Курочкина Ольга Ивановна +380000000012",lock);
        WriterThread thread1 = (WriterThread) new Thread("ThreadW1");
        WriterThread thread2 = (WriterThread) new Thread("ThreadW2");
    }
}