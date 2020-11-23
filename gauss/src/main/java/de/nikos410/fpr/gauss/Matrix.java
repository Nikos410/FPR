package de.nikos410.fpr.gauss;

import java.util.List;
import java.util.Objects;
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

    public int getHeight() {
        return rows.size();
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
