package ru.mondayish.itmo.utils;

import ru.mondayish.itmo.models.Matrix;

import java.util.Random;

public class MatrixGenerator {

    private static final int MAX_ELEMENT_VALUE = 10000;
    private static final int MIN_ELEMENT_VALUE = -10000;

    public Matrix generateRandomMatrix(int size) {
        Random random = new Random();
        double[][] koefs = new double[size][size];
        double[] freeMembers = new double[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size + 1; j++) {
                koefs[i][j] = random.nextInt(MAX_ELEMENT_VALUE) + MIN_ELEMENT_VALUE;
            }
        }
        return new Matrix(koefs, freeMembers);
    }
}
