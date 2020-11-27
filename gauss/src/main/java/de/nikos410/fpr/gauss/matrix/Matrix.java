package de.nikos410.fpr.gauss.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A simple immutable row-based matrix with useful helper methods.
 *
 * Specific rows are addressed by zero-based indices.
 *
 * @see MatrixRow
 * @author Nikos Epping
 */
public class Matrix {
    private final List<MatrixRow> rows;

    /**
     * Creates a new instance based on the given rows.
     *
     * @param rows The rows of the new matrix
     */
    public Matrix(List<MatrixRow> rows) {
        this.rows = new ArrayList<>(rows);
    }

    /**
     * Get the row at the given index.
     *
     * @param rowIndex The index of the row to return
     * @return The row at the given index
     */
    public MatrixRow getRow(int rowIndex) {
        return rows.get(rowIndex);
    }

    /**
     * Get the height of this matrix, or in other words the number of rows.
     *
     * @return The height of this matrix
     */
    public int getHeight() {
        return rows.size();
    }

    /**
     * Replaces the row at the given index with the given row.
     *
     * @param index The index of the row to replace
     * @param newRow The new row
     * @return A new instance in which the row is replaced
     */
    public Matrix setRow(int index, MatrixRow newRow) {
        final List<MatrixRow> newRows = new ArrayList<>(rows);
        newRows.set(index, newRow);

        return new Matrix(newRows);
    }

    /**
     * Swap two rows
     *
     * @param a The index of the first row
     * @param b The index of the second row
     * @return A new instance in which the rows are swapped
     */
    public Matrix swapRows(int a, int b) {
        final List<MatrixRow> newRows = new ArrayList<>(rows);

        final MatrixRow temp = newRows.get(a);
        newRows.set(a, newRows.get(b));
        newRows.set(b, temp);

        return new Matrix(newRows);
    }

    /**
     * {@inheritDoc}
     *
     * Returns all matrix rows, each formatted using {@link MatrixRow#toString()}.
     */
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
