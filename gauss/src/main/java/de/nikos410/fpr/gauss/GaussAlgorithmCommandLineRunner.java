package de.nikos410.fpr.gauss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class GaussAlgorithmCommandLineRunner {
    public static void main(String[] args) {
        final Matrix inputMatrix = readInputMatrix();

        final GaussAlgorithm gaussAlgorithm = new GaussAlgorithm(inputMatrix);
        final Matrix resultMatrix = gaussAlgorithm.eliminate();

        System.out.println("Matrix after elimination:\n" + resultMatrix);
    }

    private static Matrix readInputMatrix() {
        final List<MatrixRow> rows = new LinkedList<>();

        try (final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             final BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            while (true) {
                System.out.print("Enter a row, or press enter if you are done.\n> ");

                final String input = bufferedReader.readLine();
                if (input.isBlank()) {
                    return new Matrix(rows);
                }

                rows.add(new MatrixRowParser(input).parse());
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
