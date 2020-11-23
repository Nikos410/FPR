package de.nikos410.fpr.gauss;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LinearEquationToMatrixRowParser {

    private final List<String> inputParts;

    public LinearEquationToMatrixRowParser(String input) {
        final String cleanInput = input.replaceAll("\\s", "");
        this.inputParts = Arrays.asList(cleanInput.split("[+=|]"));
    }

    public MatrixRow parse() {
        final List<Double> matrixRowValues = inputParts
                .stream()
                .map(part -> part.replaceAll("[a-zA-Z]", ""))
                .map(Double::valueOf)
                .collect(Collectors.toList());

        return new MatrixRow(matrixRowValues);
    }
}
