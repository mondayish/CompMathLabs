package ru.mondayish.itmo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mondayish.itmo.math.DiagonalDominanceTransformer;
import ru.mondayish.itmo.math.SimpleIterationCalculator;
import ru.mondayish.itmo.models.CalculationResult;
import ru.mondayish.itmo.models.Matrix;
import ru.mondayish.itmo.utils.MatrixGenerator;

import java.util.Arrays;
import java.util.stream.IntStream;

public class GeneratorTest {

    @Test
    public void testGenerator() {
        Matrix matrix = new MatrixGenerator().generateMatrixWithDiagonalDominance(5);
        double[][] koefs = matrix.getKoefs();
        double[] freeMembers = matrix.getFreeMembers();
        IntStream.range(0, koefs.length).forEach(i -> System.out.println(Arrays.toString(koefs[i]) + " " + freeMembers[i]));
        CalculationResult calculationResult = new SimpleIterationCalculator(matrix, 0.01).calculate();
        System.out.println(calculationResult.getIterationCount());
        System.out.println(Arrays.toString(calculationResult.getXVector()));
        System.out.println(Arrays.toString(calculationResult.getFaultVector()));
    }

    @Test
    public void simpleMatrixTest() {
        Matrix matrix = new Matrix(new double[][]{{2, 2, 10}, {10, 1, 1}, {2, 10, 1}}, new double[]{14, 12, 13});
        matrix = new DiagonalDominanceTransformer(matrix).transform();
        CalculationResult calculationResult = new SimpleIterationCalculator(matrix, 0.01).calculate();
        double[] xVector = calculationResult.getXVector();
        double[] roundedXVector = Arrays.stream(xVector).map(x -> (Math.round(x * 100)) / 100.0).toArray();
        Assertions.assertArrayEquals(new double[]{1.0, 1.0, 1.0}, roundedXVector);
    }
}
