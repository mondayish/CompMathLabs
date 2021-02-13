package ru.mondayish.itmo.readers;

import ru.mondayish.itmo.models.Matrix;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;

/**
 * Класс для чтения матрицы из файла
 */
public class MatrixFileReader extends MatrixReader {

    private final static String PATH_TO_MATRIX_FILES = "lab1/src/main/resources/";

    public Optional<Matrix> readFromFile(String path) {
        try {
            Scanner scanner = new Scanner(Paths.get(PATH_TO_MATRIX_FILES + path));
            int size = scanner.nextInt();
            return Optional.of(readMatrixFromScanner(scanner, size));
        } catch (RuntimeException | IOException e) {
            return Optional.empty();
        }
    }
}
