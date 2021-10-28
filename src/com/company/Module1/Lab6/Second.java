package com.company.Module1.Lab6;

import java.util.Random;
import java.util.Scanner;

public class Second {
    public static Random random = new Random();
    public static Scanner scanner = new Scanner(System.in);

    // Function for simple initialization of matrix elements
    public static void DummyDataInitialization(double[] pAMatrix, double[] pBMatrix, int Size) {
        int i, j; // Loop variables
        for (i = 0; i < Size; i++)
            for (j = 0; j < Size; j++) {
                pAMatrix[i * Size + j] = random.nextInt();
                pBMatrix[i * Size + j] = random.nextInt();
            }
    }


    // Function for formatted matrix output
    public static void PrintMatrix(double[] pMatrix, int RowCount, int ColCount) {
        int i, j; // Loop variables
        StringBuilder x = new StringBuilder();
        for (i = 0; i < RowCount; i++) {
            for (j = 0; j < ColCount; j++)
                x.append(pMatrix[i * RowCount + j]);
                x.append("  ");
        }
        System.out.println(x);
        x.reverse();
    }

    // Function for matrix multiplication
    public static void SerialResultCalculation(double[] pAMatrix, double[] pBMatrix,
                                               double[] pCMatrix, int Size) {
        int i, j, k; // Loop variables
        for (i = 0; i < Size; i++) {
            for (j = 0; j < Size; j++)
                for (k = 0; k < Size; k++)
                    pCMatrix[i * Size + j] += pAMatrix[i * Size + k] * pBMatrix[k * Size + j];
        }
    }

    // Function for computational process termination
    public static void main(String[] args) {
        System.out.println("Serial matrix multiplication program\n");
        System.out.println("Set size:\n");
        int Size = scanner.nextInt();
        double[] pAMatrix = new double[Size * Size];
        double[] pBMatrix = new double[Size * Size];
        double[] pCMatrix = new double[Size * Size];
        long start, finish;
        double duration;

        DummyDataInitialization(pAMatrix, pBMatrix, Size);
//        System.out.println("Initial A Matrix \n");
//        PrintMatrix(pAMatrix, Size, Size);
//        System.out.println("Initial B Matrix \n");
//        PrintMatrix(pBMatrix, Size, Size);
        start = System.nanoTime();
        SerialResultCalculation(pAMatrix, pBMatrix, pCMatrix, Size);
        finish = System.nanoTime();
        duration = (finish - start);

//        System.out.println("\n Result Matrix: \n");
//        PrintMatrix(pCMatrix, Size, Size);
        System.out.println("Time of execution:\n " + duration);
    }
}
