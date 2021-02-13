package ru.mondayish.itmo.readers;

import ru.mondayish.itmo.models.Matrix;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Абстрактный класс для чтения матрицы
 */
public abstract class MatrixReader {

    protected Matrix readMatrixFromScanner(Scanner scanner, int size) throws RuntimeException {
        double[][] koefs = new double[size][size];
        double[] freeMembers = new double[size];
        IntStream.range(0, size).forEach(i -> {
            IntStream.range(0, size).forEach(j -> koefs[i][j] = scanner.nextDouble());
            freeMembers[i] = scanner.nextDouble();
        });
        return new Matrix(koefs, freeMembers);
    }
}
