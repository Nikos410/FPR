package de.nikos410.fpr.gauss;

import java.util.List;
import java.util.stream.Collectors;

public class Matrix {
    private final List<MatrixRow> rows;

    public Matrix(List<MatrixRow> rows) {
        this.rows = rows;
    }

    public MatrixRow getRow(int rowIndex) {
        return rows.get(rowIndex);
    }

    public void setRow(int rowIndex, MatrixRow row) {
        rows.set(rowIndex, row);
    }

    @Override
    public String toString() {
        return rows.stream().map(MatrixRow::toString).collect(Collectors.joining("\n"));
    }
}
