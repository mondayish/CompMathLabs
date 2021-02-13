package ru.mondayish.itmo.utils;

import ru.mondayish.itmo.models.Matrix;
import ru.mondayish.itmo.models.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MatrixRowsAdapter {

    public static Matrix fromRowsToMatrix(List<Row> rows) {
        double[][] koefs = new double[rows.size()][rows.size()];
        double[] freeMembers = new double[rows.size()];
        IntStream.range(0, rows.size()).forEach(i -> {
            koefs[i] = rows.get(i).getKoefs();
            freeMembers[i] = rows.get(i).getFreeMember();
        });
        return new Matrix(koefs, freeMembers);
    }

    public static List<Row> fromMatrixToRows(Matrix matrix) {
        List<Row> rows = new ArrayList<>();
        IntStream.range(0, matrix.getKoefs().length).forEach(i ->
                rows.add(new Row(matrix.getKoefs()[i], matrix.getFreeMembers()[i])));
        return rows;
    }
}
