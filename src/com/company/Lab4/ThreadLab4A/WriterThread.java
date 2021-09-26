package com.company.Lab4.ThreadLab4A;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriterThread  extends Thread  {


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

    public void AddIntoFile(String info, ReadWriteLock lock) throws FileNotFoundException {
        lock.writeLock().lock();
        StringBuilder newFile = new StringBuilder();
        File DatabaseFile = new File("Database.txt");
        Scanner currentFile = new Scanner(DatabaseFile);

        try {
            while (currentFile.hasNextLine()) {
                if (Objects.equals(currentFile.nextLine(), info)) {
                    System.out.println("This person exists");
                    break;
                } else {
                    newFile.append(currentFile.nextLine());
                    newFile.append("\n");
                }
            }
            newFile.append(info);
            FileWriter writer = new FileWriter("Database.txt");
            writer.write(String.valueOf(newFile));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
        ReadFile(lock);
    }

    public void DeleteFromFile(String info, ReadWriteLock lock) throws IOException {
        lock.writeLock().lock();
        StringBuilder newFile = new StringBuilder();

        File DatabaseFile = new File("Database.txt");
        Scanner currentFile = new Scanner(DatabaseFile);
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
        ReadFile(lock);
    }

    @Override
    public void run() {
//        this.AddIntoFile("Курочкина Ольга Ивановна +380000000012",lock);
//        this.DeleteFromFile("Курочкина Ольга Ивановна +380000000012",lock);
    }
}
