package ru.mondayish.itmo;

import ru.mondayish.itmo.math.DiagonalDominanceTransformer;
import ru.mondayish.itmo.readers.MatrixConsoleReader;
import ru.mondayish.itmo.readers.MatrixFileReader;
import ru.mondayish.itmo.models.Matrix;
import ru.mondayish.itmo.utils.ConsoleWriter;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

        double[][] koefs = {{10, 1, 1}, {2, 7, 6}, {2, 2, 10}};
        Matrix matrix = new Matrix(koefs, new double[]{1, 2, 3});
        DiagonalDominanceTransformer transformer = new DiagonalDominanceTransformer(matrix);
        Matrix transform = transformer.transform();
        System.out.println(transform);

//        try {
//            Matrix matrix = args.length > 0 ?
//                    new MatrixFileReader().readFromFile(args[0]).orElseThrow(() ->
//                            new IllegalArgumentException("Убедитесь, что ваш файл существует, или исправьте ваш файл!")) :
//                    new MatrixConsoleReader().readFromConsole().orElseThrow(() ->
//                            new IllegalArgumentException("Будьте аккуратны при вводе матрицы!"));
//            double accuracy = getAccuracy();
//
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
    }

    private static double getAccuracy() {
        ConsoleWriter consoleWriter = new ConsoleWriter();
        consoleWriter.write("Введите точность: ");
        return new Scanner(System.in).nextDouble();
    }
}
