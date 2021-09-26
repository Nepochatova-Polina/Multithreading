package com.company.Lab4.ThreadLab4A;


import java.io.*;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ThreadMain {
    ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws IOException {
        try {
            File DatabaseFile = new File("Database.txt");
            Scanner myReader = new Scanner(DatabaseFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    Thread thread = new Thread();
}