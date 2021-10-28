package com.company.Module1.Lab5.ThreadLab5C;

public class CompareSum implements Runnable {
    @Override
    public void run() {
        if (CompareSum(ThreadLab5C.firstThread.sum, ThreadLab5C.secondThread.sum, ThreadLab5C.thirsThread.sum)) {
            ThreadLab5C.firstThread.interrupt();
            ThreadLab5C.secondThread.interrupt();
            ThreadLab5C.thirsThread.interrupt();
            System.out.println("Total amount: "+ThreadLab5C.firstThread.sum);
        }
    }
    public static boolean CompareSum(int sum1,int sum2, int sum3){
        return sum1 == sum2 && sum1 == sum3;
    }
}
