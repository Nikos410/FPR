package de.nikos410.fpr.gauss;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MatrixRow {
    private final List<Double> values;

    public MatrixRow(Double... values) {
        this(Arrays.asList(values));
    }

    public MatrixRow(List<Double> values) {
        this.values = values;
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
