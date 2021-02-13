package ru.mondayish.itmo.readers;

import ru.mondayish.itmo.models.Matrix;

import java.util.Scanner;

public abstract class MatrixReader {

    protected Matrix readMatrixFromScanner(Scanner scanner, int size) throws RuntimeException {
        double[][] koefs = new double[size][size];
        double[] freeMembers = new double[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                koefs[i][j] = scanner.nextDouble();
            }
            freeMembers[i] = scanner.nextDouble();
        }
        return new Matrix(koefs, freeMembers);
    }
}
