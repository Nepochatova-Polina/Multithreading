package com.company.Lab4.ThreadLab4C;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class mainThread {
    private static final Random random = new Random();
    public static int[][] matrix = new int[5][5];
    public static List<Integer> trip = new ArrayList<>();
    public static List<List<Integer>> PathBtwnCities = new ArrayList<>();
    public static AtomicBoolean ReadMutex = new AtomicBoolean(false);
    public static AtomicBoolean WriteMutex = new AtomicBoolean(false);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    int x = random.nextInt(10);
                    matrix[i][j] = x;
                    matrix[j][i] = x;
                    trip.add(x);
                }
            }
            PathBtwnCities.add(trip);
        }


        Thread ChangePrice = new Thread(() -> {
            List<Integer> currentList;
            while (true) {
                if (!WriteMutex.get() && !ReadMutex.get()) {
                    WriteMutex.set(true);
                    try {
                        System.out.println(Thread.currentThread().getName() + " :Changing price info...");
                        int x = random.nextInt(PathBtwnCities.size());
                        int y = random.nextInt(PathBtwnCities.size());
                        int newPrice = random.nextInt(3);

                        currentList = PathBtwnCities.get(x);
                        currentList.set(y, newPrice);
                        PathBtwnCities.set(x, currentList);

                        currentList = PathBtwnCities.get(y);
                        currentList.set(x, newPrice);
                        PathBtwnCities.set(y, currentList);

                        WriteMutex.set(false);
                        System.out.println(Thread.currentThread().getName() + " :Work Done!");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread AddDeleteCity = new Thread(() -> {
            while (true) {
                if (!WriteMutex.get() && !ReadMutex.get()) {
                    WriteMutex.set(true);
                    try {
                        System.out.println(Thread.currentThread().getName() + " :Add new city info...");
                        AddCity();
                        System.out.println(Thread.currentThread().getName() + " :Work Done!");
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName() + " :Delete city info...");
                        DeleteCity();
                        System.out.println(Thread.currentThread().getName() + " :Work Done!");
                        WriteMutex.set(false);
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread AddDeletePath = new Thread(() -> {
            while (true) {
                if (!WriteMutex.get() && !ReadMutex.get()) {
                    WriteMutex.set(true);
                    try {
                        System.out.println(Thread.currentThread().getName() + " :Add a new route...");
                        AddPath();
                        System.out.println(Thread.currentThread().getName() + " :New route added");
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName() + " :Deleting a non-existent route...");
                        DeletePath();
                        WriteMutex.set(false);
                        System.out.println(Thread.currentThread().getName() + " :Route deleted");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread CountPrice = new Thread(() -> {
            List<Integer> currentList;
            while (true) {
                if (!WriteMutex.get() ) {
                    ReadMutex.set(true);
                    try {
                        System.out.println(Thread.currentThread().getName() + " :Counting price info...");
                        int x = random.nextInt(PathBtwnCities.size());
                        int y = random.nextInt(PathBtwnCities.size());
                        currentList = PathBtwnCities.get(x);
                        if (currentList.get(y) != 0) {
                            System.out.println(Thread.currentThread().getName() + " :Your ticket costs " + currentList.get(y));
                        }
                        ReadMutex.set(false);
                        System.out.println(Thread.currentThread().getName() + " :Work Done!");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        AddDeleteCity.start();
        ChangePrice.start();
        AddDeletePath.start();
        CountPrice.start();


    }


    public static void AddCity() {
        List<Integer> currentList;
        List<Integer> newCity = new ArrayList<>();
        for (int i = 0; i < PathBtwnCities.size(); i++) {
            int x = random.nextInt(3);
            assert false;
            newCity.add(x);
            if (x != 0) {
                currentList = PathBtwnCities.get(i);
                currentList.add(x);
                PathBtwnCities.set(i, currentList);
            }
        }
    }

    public static void DeleteCity() {
        List<Integer> currentList;
        int x = random.nextInt(PathBtwnCities.size());
        PathBtwnCities.remove(x);
        for (int i = 0; i < PathBtwnCities.size(); i++) {
            currentList = PathBtwnCities.get(i);
            currentList.remove(x);
            PathBtwnCities.set(i, currentList);
        }
    }
    public static void AddPath(){
        List<Integer> currentList;
        int x, y, newPath;
        x = random.nextInt(PathBtwnCities.size());
        y = random.nextInt(PathBtwnCities.size());
        newPath = random.nextInt(PathBtwnCities.size());

        currentList = PathBtwnCities.get(x);
        currentList.set(y, newPath);
        PathBtwnCities.set(x, currentList);

        currentList = PathBtwnCities.get(y);
        currentList.set(x, newPath);
        PathBtwnCities.set(y, currentList);
    }
    public static void DeletePath(){
        List<Integer> currentList;
        int x, y;
        boolean isDeleted = false;
        while (!isDeleted) {
            x = random.nextInt(PathBtwnCities.size());
            y = random.nextInt(PathBtwnCities.size());
            currentList = PathBtwnCities.get(x);
            if (currentList.get(y) != 0 && x != y) {
                currentList.set(y, 0);
                PathBtwnCities.set(x, currentList);
                currentList = PathBtwnCities.get(y);
                currentList.set(x, 0);
                PathBtwnCities.set(y, currentList);
                isDeleted = true;
            }
        }
    }

}