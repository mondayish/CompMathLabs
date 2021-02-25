package ru.mondayish.itmo;

import ru.mondayish.itmo.math.DiagonalDominanceTransformer;
import ru.mondayish.itmo.math.RankChecker;
import ru.mondayish.itmo.math.SimpleIterationCalculator;
import ru.mondayish.itmo.models.CalculationResult;
import ru.mondayish.itmo.models.Matrix;
import ru.mondayish.itmo.readers.MatrixConsoleReader;
import ru.mondayish.itmo.readers.MatrixFileReader;
import ru.mondayish.itmo.utils.AccuracyUtils;
import ru.mondayish.itmo.utils.ConsoleWriter;

public class Runner {
    public static void main(String[] args) {
        try {
            Matrix matrix = args.length > 0 ?
                    new MatrixFileReader().readFromFile(args[0]).orElseThrow(() ->
                            new IllegalArgumentException("Убедитесь, что ваш файл существует, или исправьте ваш файл!")) :
                    new MatrixConsoleReader().readFromConsole().orElseThrow(() ->
                            new IllegalArgumentException("Будьте аккуратны при вводе матрицы!"));
            double accuracy = AccuracyUtils.getAccuracy();
            matrix = new DiagonalDominanceTransformer(matrix).transform();
            new RankChecker(matrix).checkRank();
            CalculationResult calculationResult = new SimpleIterationCalculator(matrix, accuracy).calculate();
            ConsoleWriter.writeResultsToConsole(calculationResult);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
