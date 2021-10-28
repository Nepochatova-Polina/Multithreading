package com.company.Module1.Lab6;

import java.util.Arrays;
import java.util.Random;

public class first {
    public static int n = 3000;
    public static int[][] matrixA = new int[n][n];
    public static int[][] matrixB = new int[n][n];
    public static int[][] matrixResult = new int[n][n];
    public static Random rand = new Random();

    public static void main(String[] args) {
        generateMatrix();
        long startTime = System.currentTimeMillis();
        multiplyMatrix();
        long endTime = System.currentTimeMillis();
//        System.out.println("Matrix A");
//        printMatrix(matrixA);
//        System.out.println("Matrix B");
//        printMatrix(matrixB);
//        System.out.println("Matrix result");
//        printMatrix(matrixResult);
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");

    }


    public static void generateMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrixA[i][j] = rand.nextInt(100);
                matrixB[i][j] = rand.nextInt(100);
            }
        }
    }

    public static void multiplyMatrix() {
        for (int i = 0; i < n; i++) {
            for (int u = 0; u < n; u++) {
                for (int j = 0; j < n; j++) {
                    matrixResult[i][u] += matrixA[i][j] * matrixB[j][u];
                }
            }
        }
    }

    public static void printMatrix(int [][]matrix) {
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}

