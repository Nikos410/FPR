package de.nikos410.fpr.gauss;

import de.nikos410.fpr.gauss.matrix.Matrix;
import de.nikos410.fpr.gauss.matrix.MatrixRow;
import de.nikos410.fpr.gauss.matrix.MatrixRowParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * A small utility class to provide a simple command-line UI to use {@link GaussAlgorithm}.
 *
 * @author Nikos Epping
 */
public class GaussAlgorithmCommandLineRunner {
    public static void main(String[] args) {
        final Matrix initialMatrix = readInputMatrix();

        final List<Double> result = GaussAlgorithm.solve(initialMatrix);

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
                System.out.println(new Matrix(rows));
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
