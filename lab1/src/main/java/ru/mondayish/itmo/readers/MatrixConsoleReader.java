package ru.mondayish.itmo.readers;

import ru.mondayish.itmo.utils.ConsoleWriter;
import ru.mondayish.itmo.models.Matrix;

import java.util.Optional;
import java.util.Scanner;

/**
 * Класс для чтения матрицы из консоли
 */
public class MatrixConsoleReader extends MatrixReader {

    public Optional<Matrix> readFromConsole() {
        try {
            Scanner scanner = new Scanner(System.in);
            ConsoleWriter writer = new ConsoleWriter();
            writer.write("Введите размерность матрицы: ");
            int size = scanner.nextInt();
            writer.writeln("Введите матрицу: ");
            return Optional.of(readMatrixFromScanner(scanner, size));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
