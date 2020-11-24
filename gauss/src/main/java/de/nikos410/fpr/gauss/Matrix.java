package de.nikos410.fpr.gauss;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Matrix {
    private final List<MatrixRow> rows;

    public Matrix(List<MatrixRow> rows) {
        this.rows = new ArrayList<>(rows);
    }

    public MatrixRow getRow(int rowIndex) {
        return rows.get(rowIndex);
    }

    public int getHeight() {
        return rows.size();
    }

    public Matrix setRow(int index, MatrixRow newRow) {
        final List<MatrixRow> newRows = new ArrayList<>(rows);
        newRows.set(index, newRow);

        return new Matrix(newRows);
    }

    public Matrix swapRows(int a, int b) {
        final List<MatrixRow> newRows = new ArrayList<>(rows);

        final MatrixRow temp = newRows.get(a);
        newRows.set(a, newRows.get(b));
        newRows.set(b, temp);

        return new Matrix(newRows);
    }

    @Override
    public String toString() {
        return rows.stream().map(MatrixRow::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Matrix matrix = (Matrix) other;
        return rows.equals(matrix.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows);
    }
}
