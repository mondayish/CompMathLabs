package ru.mondayish.itmo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mondayish.itmo.math.DiagonalDominanceTransformer;
import ru.mondayish.itmo.math.RankChecker;
import ru.mondayish.itmo.math.SimpleIterationCalculator;
import ru.mondayish.itmo.models.CalculationResult;
import ru.mondayish.itmo.models.Matrix;
import ru.mondayish.itmo.utils.ConsoleWriter;
import ru.mondayish.itmo.utils.DiagonalDominanceMatrixGenerator;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class SimpleIterationTest {

    private static final int GENERATE_SIZE = 1000;
    private static final double GENERATE_ACCURACY = 0.001;
    private static final int MAX_X_VALUE = 10;
    private static final int MIN_X_VALUE = -10;

    @Test
    public void testGenerator() {
        System.out.println("Генерирование вектора неизвестных с длиной " + GENERATE_SIZE + "...");
        Random random = new Random();
        double[] xVector = IntStream.range(0, GENERATE_SIZE).mapToDouble(i -> random.nextInt(MAX_X_VALUE - MIN_X_VALUE) + MIN_X_VALUE).toArray();
        System.out.println("Ожидаемый вектор неизвестных: " + Arrays.toString(xVector));
        System.out.println("Генерирование матрицы " + GENERATE_SIZE + "x" + GENERATE_SIZE + "...");
        Matrix matrix = DiagonalDominanceMatrixGenerator.generateMatrix(GENERATE_SIZE, xVector);
        double[][] koefs = matrix.getKoefs();
        double[] freeMembers = matrix.getFreeMembers();
        IntStream.range(0, koefs.length).forEach(i -> System.out.println(Arrays.toString(koefs[i]) + " * X = " + freeMembers[i]));
        System.out.println("Используется точность = " + GENERATE_ACCURACY);
        matrix = new DiagonalDominanceTransformer(matrix).transform();
        new RankChecker(matrix).checkRank();
        CalculationResult calculationResult = new SimpleIterationCalculator(matrix, GENERATE_ACCURACY).calculate();
        ConsoleWriter.writeResultsToConsole(calculationResult);
        Assertions.assertArrayEquals(xVector, calculationResult.getXVector(), GENERATE_ACCURACY);
    }

    @Test
    public void simpleMatrixTest() {
        Matrix matrix = new Matrix(new double[][]{{2, 2, 10}, {10, 1, 1}, {2, 10, 1}}, new double[]{14, 12, 13});
        matrix = new DiagonalDominanceTransformer(matrix).transform();
        CalculationResult calculationResult = new SimpleIterationCalculator(matrix, GENERATE_ACCURACY).calculate();
        double[] xVector = calculationResult.getXVector();
        Assertions.assertArrayEquals(new double[]{1.0, 1.0, 1.0}, xVector, GENERATE_ACCURACY);
    }
}
