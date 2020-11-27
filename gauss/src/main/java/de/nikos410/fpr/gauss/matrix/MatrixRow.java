package de.nikos410.fpr.gauss.matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A simple immutable matrix row with useful helper methods.
 *
 * Specific values are addressed by zero-based indices.
 *
 * @author Nikos Epping
 */
public class MatrixRow {
    private final List<Double> values;

    /**
     * Creates a new instance using the given values.
     *
     * @param values The values of this row
     */
    public MatrixRow(Double... values) {
        this(Arrays.asList(values));
    }

    /**
     * Creates a new instance using the given values.
     *
     * @param values The values of this row
     */
    public MatrixRow(List<Double> values) {
        if (values.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("null values are not allowed.");
        }

        this.values = new LinkedList<>(values);
    }

    /**
     * Multiplies this row by a value.
     *
     * @param by The value to multiply this row by
     * @return The multiplication result as a new instance
     */
    public MatrixRow multiply(double by) {
        return new MatrixRow(values.stream()
                .map(value -> value * by)
                .collect(Collectors.toList()));
    }

    /**
     * Multiplies this row by another row.
     *
     * @param by The other row to multiply this row by
     * @return The multiplication result as a new instance
     */
    public MatrixRow multiply(MatrixRow by) {
        if (values.size() != by.values.size()) {
            throw new IllegalArgumentException("Rows must have the same length");
        }

        final List<Double> result = new LinkedList<>();
        for (int i = 0; i < values.size(); i++) {
            result.add(values.get(i) * by.values.get(i));
        }

        return new MatrixRow(result);
    }

    /**
     * Subtracts another row from this one.
     *
     * @param other The row to substract from this one
     * @return The subtraction result as a new instance
     */
    public MatrixRow subtract(MatrixRow other) {
        if (values.size() != other.values.size()) {
            throw new IllegalArgumentException("Rows must have the same length");
        }

        final List<Double> result = new LinkedList<>();
        for (int i = 0; i < values.size(); i++) {
            result.add(values.get(i) - other.values.get(i));
        }

        return new MatrixRow(result);
    }

    /**
     * Get the value at a specified index
     *
     * @param index The index of the value to return
     * @return The multiplication result as a new instance
     */
    public double getValue(int index) {
        return values.get(index);
    }

    /**
     * {@inheritDoc}
     *
     * Returns all values in this row, separated by pipes ({@code |}).
     */
    @Override
    public String toString() {
        return values.stream().map(value -> String.format("%1$6s", value)).collect(Collectors.joining(" | "));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        MatrixRow matrixRow = (MatrixRow) other;
        return values.equals(matrixRow.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}
