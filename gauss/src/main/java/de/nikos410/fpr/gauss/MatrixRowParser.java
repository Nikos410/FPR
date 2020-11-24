package de.nikos410.fpr.gauss;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixRowParser {

    private final String input;

    public MatrixRowParser(String input) {
        this.input = input;
    }

    public MatrixRow parse() {
        final List<Double> matrixRowValues = Arrays.stream(input.split("\\s"))
                .map(Double::valueOf)
                .collect(Collectors.toList());

        return new MatrixRow(matrixRowValues);
    }
}
