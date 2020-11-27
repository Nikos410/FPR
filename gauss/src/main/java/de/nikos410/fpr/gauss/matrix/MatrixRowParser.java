package de.nikos410.fpr.gauss.matrix;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple parser that converts an input string into a {@link MatrixRow}
 *
 * @author Nikos Epping
 */
public class MatrixRowParser {

    /**
     * Converts an input string into a {@link MatrixRow}.
     *
     * The input must contain a complete matrix row, separated only by whitespace.
     *
     * @param input The input to parse
     * @return The parsed row
     */
    public static MatrixRow parse(String input) {
        final List<Double> matrixRowValues = Arrays.stream(input.split("\\s+"))
                .filter(e -> !e.isBlank())
                .map(Double::valueOf)
                .collect(Collectors.toList());

        return new MatrixRow(matrixRowValues);
    }
}
