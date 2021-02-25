package ru.mondayish.itmo.math;

import lombok.AllArgsConstructor;
import ru.mondayish.itmo.models.Matrix;

import java.util.Arrays;

@AllArgsConstructor
public class RankChecker {

    private static final String INFINITE_SOLUTIONS_NUMBER_ERROR_MESSAGE = "У данной системы бесконечное множество решений!";

    private final Matrix matrix;

    public void checkRank() {
        double[][] koefsCopy = Arrays.stream(this.matrix.getKoefs()).map(double[]::clone).toArray(double[][]::new);
        if (calculateRankOfMatrix(koefsCopy) != this.matrix.getKoefs().length) {
            throw new IllegalArgumentException(INFINITE_SOLUTIONS_NUMBER_ERROR_MESSAGE);
        }
    }

    private int calculateRankOfMatrix(double[][] matrix) {
        int rank = matrix.length;
        for (int row = 0; row < rank; row++) {
            // Diagonal element is not zero
            if (matrix[row][row] != 0) {
                for (int col = 0; col < matrix.length; col++) {
                    if (col != row) {
                        double mult = matrix[col][row] / matrix[row][row];
                        for (int i = 0; i < rank; i++)
                            matrix[col][i] -= mult * matrix[row][i];
                    }
                }
            } else {
                boolean reduce = true;

                for (int i = row + 1; i < matrix.length; i++) {
                    // Swap the row with non-zero element
                    // with this row.
                    if (matrix[i][row] != 0.0) {
                        double[] temp = matrix[i];
                        matrix[i] = matrix[row];
                        matrix[row] = temp;
                        reduce = false;
                        break;
                    }
                }

                if (reduce) {
                    rank--;
                    // Copy tha last column
                    for (int i = 0; i < matrix.length; i++)
                        matrix[i][row] = matrix[i][rank];
                }
                row--;
            }
        }
        return rank;
    }
}
