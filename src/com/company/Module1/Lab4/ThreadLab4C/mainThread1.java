package com.company.Module1.Lab4.ThreadLab4C;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;


public class mainThread1 {
    private static final Random random = new Random();
    public static int[][] matrix = new int[5][5];
    public static List<List<Integer>> PathBtwnCities = new ArrayList<>();
    public static boolean ReadMutex = false;
    public static boolean WriteMutex = false;

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == 0 && i != j) {
                    int x = random.nextInt(10);
                    matrix[i][j] = x;
                    matrix[j][i] = x;
                }
            }
            List<Integer> trip = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                trip.add(matrix[i][j]);
            }
            PathBtwnCities.add(trip);
        }
        Thread ChangePrice = new Thread(() -> {
            List<Integer> currentList;
            while (true) {
                if (!getWriteMutex() && !getReadMutex()) {
                    setWriteMutex(true);
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

                        setWriteMutex(false);
                        System.out.println(Thread.currentThread().getName() + " :Work Done!");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread AddDeleteCity = new Thread(() -> {
            int city;
            while (true) {
                if (!getWriteMutex() && !getReadMutex()) {
                    setWriteMutex(true);
                    try {
                        System.out.println(Thread.currentThread().getName() + " :Add new city info...");
                        addCity();
                        System.out.println(Thread.currentThread().getName() + " :Work Done!");
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName() + " :Delete city info...");
                        city = random.nextInt(PathBtwnCities.size());
                        deleteCity(city);
                        System.out.println(Thread.currentThread().getName() + " :Work Done!");
                        setWriteMutex(false);
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread AddDeletePath = new Thread(() -> {
            int x, y;
            while (true) {
                if (!getWriteMutex() && !getReadMutex()) {
                    setWriteMutex(true);
                    try {
                        System.out.println(Thread.currentThread().getName() + " :Add a new route...");
                        addPath();
                        System.out.println(Thread.currentThread().getName() + " :New route added");
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName() + " :Deleting a non-existent route...");
                        x = random.nextInt(PathBtwnCities.size());
                        y = random.nextInt(PathBtwnCities.size());
                        deletePath(x, y);
                        setWriteMutex(false);
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
                if (!getWriteMutex()) {
                    setReadMutex(true);
                    try {
                        System.out.println(Thread.currentThread().getName() + " :Counting price info...");
                        int x = random.nextInt(PathBtwnCities.size());
                        int y = random.nextInt(PathBtwnCities.size());
                        currentList = PathBtwnCities.get(x);
                        if (currentList.get(y) != 0) {
                            System.out.println(Thread.currentThread().getName() + " :Your ticket costs " + currentList.get(y));
                        }
                        setReadMutex(false);
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

    public static void addCity() {
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
        PathBtwnCities.add(newCity);
    }

    public static void deleteCity(int city) {
        List<Integer> currentList;
        PathBtwnCities.remove(city);
        for (int i = 0; i < PathBtwnCities.size(); i++) {
            currentList = PathBtwnCities.get(i);
            currentList.set(city,0);
            PathBtwnCities.set(i, currentList);
        }
    }

    public static void addPath() {
        int x = random.nextInt(PathBtwnCities.size());
        int y = random.nextInt(PathBtwnCities.size());
        int newPath = random.nextInt(PathBtwnCities.size());
        List<Integer> currentList;
        currentList = PathBtwnCities.get(x);
        currentList.set(y, newPath);
        PathBtwnCities.set(x, currentList);

        currentList = PathBtwnCities.get(y);
        currentList.add(newPath);
        PathBtwnCities.set(y, currentList);
    }

    public static void deletePath(int x, int y) {
        List<Integer> currentList;
        currentList = PathBtwnCities.get(x);
        currentList.set(y, 0);
        PathBtwnCities.set(x, currentList);
        currentList = PathBtwnCities.get(y);
        currentList.set(x, 0);
        PathBtwnCities.set(y, currentList);
    }

    public static void show() {
        List<Integer> currentList;
        StringBuilder array = new StringBuilder();
        for (List<Integer> pathBtwnCity : PathBtwnCities) {
            currentList = pathBtwnCity;
            for (Integer integer : currentList) {
                array.append(integer.toString());
            }
            System.out.println(array);
            array = new StringBuilder();
        }
    }

    public static synchronized boolean getWriteMutex() {return WriteMutex;}
    public static synchronized boolean getReadMutex() {return ReadMutex;}
    public static synchronized void setWriteMutex(boolean exp) {WriteMutex = exp;}
    public static synchronized void setReadMutex(boolean exp) {ReadMutex = exp;}
}