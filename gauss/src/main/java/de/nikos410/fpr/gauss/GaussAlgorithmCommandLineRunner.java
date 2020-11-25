package de.nikos410.fpr.gauss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GaussAlgorithmCommandLineRunner {
    public static void main(String[] args) {
        final Matrix initialMatrix = readInputMatrix();

        final Matrix eleminatedMatrix = GaussAlgorithm.eliminate(initialMatrix);
        System.out.println("Matrix after elimination:\n" + eleminatedMatrix);

        final List<Double> result = GaussAlgorithm.getResults(eleminatedMatrix);
        System.out.println("\nResult:\n" + result);
    }

    private static Matrix readInputMatrix() {
        System.out.println("Please enter the matrix rows, or press enter again if you are done.");
        System.out.println("Please enter the complete matrix rows, separated by spaces. For example, the formula '2x +3y -4z = 2' should be entered as '2 3 -4 2'");

        final List<MatrixRow> rows = new LinkedList<>();

        try (final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             final BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            while (true) {
                System.out.print("\n> ");

                final String input = bufferedReader.readLine();
                if (input.isBlank()) {
                    return new Matrix(rows);
                }

                rows.add(MatrixRowParser.parse(input));
                System.out.println(rows.stream().map(MatrixRow::toString).collect(Collectors.joining("\n")));
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
