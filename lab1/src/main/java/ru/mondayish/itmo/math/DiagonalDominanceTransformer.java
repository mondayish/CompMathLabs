package ru.mondayish.itmo.math;

import lombok.AllArgsConstructor;
import ru.mondayish.itmo.models.Matrix;
import ru.mondayish.itmo.models.Row;
import ru.mondayish.itmo.utils.MatrixRowsAdapter;

import java.util.*;
import java.util.stream.IntStream;

import static java.lang.Math.abs;

@AllArgsConstructor
public class DiagonalDominanceTransformer {

    private static final String UNATTAINABLE_DOMINANCE_MESSAGE = "В введенной системе невозможно достичь диагонального превосходства!";

    private final Matrix initialMatrix;

    public Matrix transform() {
        if (checkDominance(initialMatrix.getKoefs())) return initialMatrix;
        List<Row> rows = MatrixRowsAdapter.fromMatrixToRows(initialMatrix);

        // if size == 2 just swap it, special case
        if (rows.size() == 2) {
            Matrix swappedMatrix = MatrixRowsAdapter.fromRowsToMatrix(Arrays.asList(rows.get(1), rows.get(0)));
            if (!checkDominance(swappedMatrix.getKoefs()))
                throw new IllegalArgumentException(UNATTAINABLE_DOMINANCE_MESSAGE);
            return swappedMatrix;
        }

        Map<Integer, Row> rowsWithDominanceKeyElement = new HashMap<>();
        rows.forEach(row -> {
            double[] koefs = row.getKoefs();
            IntStream.range(0, koefs.length).forEach(i -> {
                if (2 * abs(koefs[i]) - Arrays.stream(koefs).map(Math::abs).sum() >= 0) {
                    rowsWithDominanceKeyElement.put(i, row);
                }
            });
        });
        Row[] swappedRows = new Row[rows.size()];
        for (int i = 0; i < rows.size(); i++) {
            swappedRows[i] = Optional.ofNullable(rowsWithDominanceKeyElement.get(i))
                    .orElseThrow(() -> new IllegalArgumentException(UNATTAINABLE_DOMINANCE_MESSAGE));
        }
        return MatrixRowsAdapter.fromRowsToMatrix(Arrays.asList(swappedRows));
    }

    private boolean checkDominance(double[][] koefs) {
        for (int i = 0; i < koefs.length; i++) {
            double otherKoefsInRowSum = Arrays.stream(koefs[i]).map(Math::abs).sum() - abs(koefs[i][i]);
            if (abs(koefs[i][i]) < otherKoefsInRowSum) return false;
        }
        return true;
    }
}

