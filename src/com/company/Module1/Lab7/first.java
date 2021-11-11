package com.company.Module1.Lab7;


public class first {
//    void MatrixMultiplicationMPI(double[] A, double[] B, double[] C, int size) {
//
//        int dim = size, i, j, k, p, ind;
//
//        double temp;
//
//        MPI_Status Status;
//
//        int ProcPartsize = dim / ProcNum;
//
//        int ProcPartElem = ProcPartsize * dim;
//
//        double []bufA = new double[ProcPartElem];
//
//        double []bufB = new double[ProcPartElem];
//
//        double []bufC = new double[ProcPartElem];
//
//
//        if (ProcRank == 0)
//
//            Transposition(B, size);
//
//
//        MPI_Scatter(A, ProcPartElem, MPI_DOUBLE, bufA, ProcPartElem, MPI_DOUBLE, 0, MPI_COMM_WORLD);
//
//        MPI_Scatter(B, ProcPartElem, MPI_DOUBLE, bufB, ProcPartElem, MPI_DOUBLE, 0, MPI_COMM_WORLD);
//
//
//        temp = 0.0;
//
//        for (i = 0; i < ProcPartsize; i++) {
//
//            for (j = 0; j < ProcPartsize; j++) {
//
//                for (k = 0; k < dim; k++)
//
//                    temp += bufA[i * dim + k] * bufB[j * dim + k];
//
//
//                bufC[i * dim + j + ProcPartsize * ProcRank] = temp;
//
//                temp = 0.0;
//
//            }
//
//        }
//
//
//        int NextProc;
//        int PrevProc;
//        for (p = 1; p < ProcNum; p++) {
//            NextProc = ProcRank + 1;
//            if (ProcRank == ProcNum - 1)
//                NextProc = 0;
//            PrevProc = ProcRank - 1;
//            if (ProcRank == 0)
//               PrevProc = ProcNum - 1;
//            MPI_Sendrecv_replace(bufB, ProcPartElem, MPI_DOUBLE, NextProc, 0, PrevProc, 0, MPI_COMM_WORLD, & Status);
//            temp = 0.0;
//            for (i = 0; i < ProcPartsize; i++)
//                for (j = 0; j < ProcPartsize; j++) {
//                    for (k = 0; k < dim; k++)
//                        temp += bufA[i * dim + k] * bufB[j * dim + k];
//                    if (ProcRank - p >= 0)
//                        ind = ProcRank - p;
//                    else
//
//                        ind = ProcNum - p + ProcRank;
//
//                    bufC[i * dim + j + ind * ProcPartsize] = temp;
//
//                    temp = 0.0;
//
//                }
//
//        }
//
//
//        MPI_Gather(bufC, ProcPartElem, MPI_DOUBLE, C, ProcPartElem, MPI_DOUBLE, 0, MPI_COMM_WORLD);
//
//
//    }


}
