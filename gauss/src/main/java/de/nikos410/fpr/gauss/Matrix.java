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

    public int getWidth() {
        return rows.get(0).getWidth();
    }

    public int getHeight() {
        return rows.size();
    }

    @Override
    public String toString() {
        return rows.stream().map(MatrixRow::toString).collect(Collectors.joining("\n"));
    }
}
