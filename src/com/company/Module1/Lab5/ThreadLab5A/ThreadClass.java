package com.company.Module1.Lab5.ThreadLab5A;

public class ThreadClass extends Thread {
private final int firstIndex;
private final int lastIndex;

    public ThreadClass(int first,int second) {
        firstIndex = first;
        lastIndex = second;
    }

    @Override
    public void run() {
        while (getCounter()){
            int j = firstIndex + 1;
//            System.out.println(Thread.currentThread().getName() + " run");
        for (int i = firstIndex; i < lastIndex ; i++) {
            if (ThreadMain.array[i] == 1 && ThreadMain.array[i] != ThreadMain.array[j]) {
                ThreadMain.array[i] = 0;
                ThreadMain.array[j] = 1;
                j++;
            } else if (ThreadMain.array[i] == 0 && ThreadMain.array[i] != ThreadMain.array[j]) {
                ThreadMain.array[i] = 1;
                ThreadMain.array[j] = 0;
                j++;
            }else {
                j++;
            }
        }
        changeCounter();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void changeCounter(){
        ThreadMain.counter--;
    }
    public static synchronized boolean getCounter(){
        return ThreadMain.counter != 0;
    }
}
