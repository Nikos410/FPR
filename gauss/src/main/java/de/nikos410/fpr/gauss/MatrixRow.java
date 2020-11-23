package de.nikos410.fpr.gauss;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MatrixRow {
    private List<Double> values;

    public MatrixRow(Double... values) {
        this(Arrays.asList(values));
    }

    public MatrixRow(List<Double> values) {
        if (values.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("null values are not allowed.");
        }

        this.values = values;
    }

    public void multiply(double by) {
        values = values.stream()
                .map(value -> value * by)
                .collect(Collectors.toList());
    }

    public void multiply(MatrixRow by) {
        if (values.size() != by.values.size()) {
            throw new IllegalArgumentException("Rows must have the same length");
        }

        for (int i = 0; i < values.size(); i++) {
            final Double result = values.get(i) * by.values.get(i);
            values.set(i, result);
        }
    }

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
