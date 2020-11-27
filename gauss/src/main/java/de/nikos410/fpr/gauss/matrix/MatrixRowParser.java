package de.nikos410.fpr.gauss.matrix;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixRowParser {

    public static MatrixRow parse(String input) {
        final List<Double> matrixRowValues = Arrays.stream(input.split("\\s+"))
                .filter(e -> !e.isBlank())
                .map(Double::valueOf)
                .collect(Collectors.toList());

        return new MatrixRow(matrixRowValues);
    }
}
