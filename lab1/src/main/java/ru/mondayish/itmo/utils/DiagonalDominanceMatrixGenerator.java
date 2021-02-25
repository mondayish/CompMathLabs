package ru.mondayish.itmo.utils;

import ru.mondayish.itmo.models.Matrix;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class DiagonalDominanceMatrixGenerator {

    private static final int MAX_ELEMENT_VALUE = 500;
    private static final int MIN_ELEMENT_VALUE = -500;

    public static Matrix generateMatrix(int size, double[] xVector) {
        if (xVector.length != size)
            throw new IllegalArgumentException("Размер вектора неизвестных должен совпадать с размерностью матрицы");
        Random random = new Random();
        double[][] koefs = new double[size][size];
        double[] freeMembers = new double[size];
        IntStream.range(0, size).forEach(i -> {
            IntStream.range(0, size).forEach(j -> koefs[i][j] = random.nextInt(MAX_ELEMENT_VALUE - MIN_ELEMENT_VALUE) + MIN_ELEMENT_VALUE);
            koefs[i][i] = random.nextInt(MAX_ELEMENT_VALUE - MIN_ELEMENT_VALUE) + Arrays.stream(koefs[i]).map(Math::abs).sum();
            freeMembers[i] = IntStream.range(0, size).mapToDouble(j -> koefs[i][j] * xVector[j]).sum();
        });
        return new Matrix(koefs, freeMembers);
    }
}
