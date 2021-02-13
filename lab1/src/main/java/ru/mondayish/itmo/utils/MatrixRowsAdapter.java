package ru.mondayish.itmo.utils;

import ru.mondayish.itmo.models.Matrix;
import ru.mondayish.itmo.models.Row;

import java.util.ArrayList;
import java.util.List;

public class MatrixRowsAdapter {

    public static Matrix fromRowsToMatrix(List<Row> rows) {
        double[][] koefs = new double[rows.size()][rows.size()];
        double[] freeMembers = new double[rows.size()];
        for (int i = 0; i < rows.size(); i++) {
            koefs[i] = rows.get(i).getKoefs();
            freeMembers[i] = rows.get(i).getFreeMember();
        }
        return new Matrix(koefs, freeMembers);
    }

    public static List<Row> fromMatrixToRows(Matrix matrix) {
        List<Row> rows = new ArrayList<>();
        for (int i = 0; i < matrix.getKoefs().length; i++) {
            rows.add(new Row(matrix.getKoefs()[i], matrix.getFreeMembers()[i]));
        }
        return rows;
    }
}
