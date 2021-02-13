package ru.mondayish.itmo.math;

import lombok.AllArgsConstructor;
import ru.mondayish.itmo.models.Matrix;
import ru.mondayish.itmo.models.Row;
import ru.mondayish.itmo.utils.MatrixRowsAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;

@AllArgsConstructor
public class DiagonalDominanceTransformer {

    private final Matrix initialMatrix;

    public Matrix transform() {
        if (checkDominance(initialMatrix.getKoefs())) return initialMatrix;
        List<Row> rows = MatrixRowsAdapter.fromMatrixToRows(initialMatrix);
        Map<Integer, List<Row>> rowsWithDominanceKey = new HashMap<>();

        return null;
    }

    private boolean checkDominance(double[][] koefs) {
        for (int i = 0; i < koefs.length; i++) {
            double otherKoefsInRowSum = Arrays.stream(koefs[i]).map(Math::abs).sum() - abs(koefs[i][i]);
            if (abs(koefs[i][i]) < otherKoefsInRowSum) return false;
        }
        return true;
    }
}

