package com.company.Lab5.ThreadLab5B;

import java.util.Random;

public class ThreadLab5B {
    public static String [] arr1 = {"A","B","C","D","B","C","A","C","D","B"};
    public static String [] arr2 = {"B","C","D","A","B","D","B","A","C","D"};
    public static String [] arr3 = {"B","C","B","A","A","D","B","D","A","B"};
    public static String [] arr4 = {"B","C","B","A","A","D","D","B","D","B"};
    public static void main(String[] args) {
        Thread first = new Thread(()->{
                int countA = 0;
                int countB = 0;
            for (int i = 0; i < arr1.length; i++) {
                if(arr1[i] == "A"){
                    countA++;
                } else if(arr1[i] == "B"){
                    countB++;
                }
            }

        });
    }
}
