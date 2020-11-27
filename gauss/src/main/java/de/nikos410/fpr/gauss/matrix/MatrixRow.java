package de.nikos410.fpr.gauss.matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MatrixRow {
    private final List<Double> values;

    public MatrixRow(Double... values) {
        this(Arrays.asList(values));
    }

    public MatrixRow(List<Double> values) {
        if (values.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("null values are not allowed.");
        }

        this.values = new LinkedList<>(values);
    }

    public MatrixRow multiply(double by) {
        return new MatrixRow(values.stream()
                .map(value -> value * by)
                .collect(Collectors.toList()));
    }

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

    public double getValue(int index) {
        return values.get(index);
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
