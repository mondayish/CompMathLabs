package ru.mondayish.itmo.math;

import lombok.RequiredArgsConstructor;
import ru.mondayish.itmo.models.CalculationResult;
import ru.mondayish.itmo.models.Matrix;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * Решает СЛАУ методом простых итераций
 * и возвращает вектор погрешностей, неизвестных и кол-во итераций
 */
@RequiredArgsConstructor
public class SimpleIterationCalculator {

    private final Matrix matrix;
    private final double neededAccuracy;
    private double[] approximationVector;

    public CalculationResult calculate() {
        prepareMatrix();
        prepareApproximations();
        double[] faultVector = new double[matrix.getKoefs().length];
        int iterationCount = 0;
        do {
            iterationCount++;
            double[] nextXVector = new double[approximationVector.length];
            IntStream.range(0, nextXVector.length).forEach(i -> {
                nextXVector[i] = IntStream.range(0, nextXVector.length)
                        .mapToDouble(j -> matrix.getKoefs()[i][j] * approximationVector[j]).sum() + matrix.getFreeMembers()[i];
                faultVector[i] = Math.abs(nextXVector[i] - approximationVector[i]);
            });
            approximationVector = nextXVector;
        } while (DoubleStream.of(faultVector).max().orElse(0) > neededAccuracy);
        return new CalculationResult(approximationVector, faultVector, iterationCount);
    }

    private void prepareMatrix() {
        double[][] koefs = matrix.getKoefs();
        double[] freeMembers = matrix.getFreeMembers();
        for (int i = 0; i < koefs.length; i++) {
            double mainKoef = koefs[i][i];
            for (int j = 0; j < koefs[i].length; j++) {
                koefs[i][j] = -koefs[i][j] / mainKoef;
            }
            freeMembers[i] = freeMembers[i] / mainKoef;
            koefs[i][i] = 0;
        }
    }

    private void prepareApproximations() {
        approximationVector = new double[matrix.getFreeMembers().length];
        System.arraycopy(matrix.getFreeMembers(), 0, approximationVector, 0, matrix.getFreeMembers().length);
    }
}
