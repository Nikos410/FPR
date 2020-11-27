package de.nikos410.fpr.gauss;


import de.nikos410.fpr.gauss.exception.NoSolutionException;
import de.nikos410.fpr.gauss.matrix.Matrix;
import de.nikos410.fpr.gauss.matrix.MatrixRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of the Gauss elimination algorithm with column pivoting.
 * This algorithm can be used to solve a set of linear equations.
 *
 * @author Nikos Epping
 */
public class GaussAlgorithm {

    private GaussAlgorithm() {
    }

    /**
     * Applies the Gauss elimination and performs re-substitution to find the results for every unknown variable.
     *
     * @param initialMatrix A matrix representing a set of linear equations.
     * @return A list containing the results for every unknown variable.
     */
    public static List<Double> solve(Matrix initialMatrix) {
        final Matrix eliminatedMatrix = eliminate(initialMatrix);
        return getResults(eliminatedMatrix);
    }

    static Matrix eliminate(Matrix initialMatrix) {
        // TODO: This looks like it could be done cleaner recursively
        Matrix currentMatrix = initialMatrix;

        for (int step = 0; step < initialMatrix.getHeight() - 1; step++) {
            currentMatrix = pivotStep(currentMatrix, step);
            currentMatrix = eliminateStep(currentMatrix, step);
        }

        return currentMatrix;
    }

    private static Matrix pivotStep(Matrix matrix, int step) {
        int rowToSwap = step;
        for (int i = step + 1; i < matrix.getHeight(); i++) {
            if (matrix.getRow(i).getValue(step) > matrix.getRow(rowToSwap).getValue(step)) {
                rowToSwap = i;
            }
        }

        return matrix.swapRows(step, rowToSwap);
    }

    private static Matrix eliminateStep(Matrix matrix, int step) {
        // TODO: This looks like it could be done cleaner recursively
        final MatrixRow stepRow = matrix.getRow(step);

        Matrix currentMatrix = matrix;
        for (int currentRowIndex = step + 1; currentRowIndex < matrix.getHeight(); currentRowIndex++) {
            final MatrixRow currentRow = currentMatrix.getRow(currentRowIndex);

            final double ratio = currentRow.getValue(step) / stepRow.getValue(step);
            final MatrixRow newRow = currentRow.subtract(stepRow.multiply(ratio));

            currentMatrix = currentMatrix.setRow(currentRowIndex, newRow);
        }

        return currentMatrix;
    }

    static List<Double> getResults(Matrix matrix) {
        return getResults(matrix, 0);
    }

    private static List<Double> getResults(Matrix matrix, int step) {
        final int matrixHeight = matrix.getHeight();
        if (step >= matrixHeight) {
            return Collections.emptyList();
        }

        requireSolvable(matrix, step);

        final MatrixRow currentRow = matrix.getRow(step);
        final List<Double> resultsBelow = getResults(matrix, step + 1);

        final double left = currentRow.getValue(step);

        double right = currentRow.getValue(matrixHeight);
        for (int i = 1; i < matrixHeight - step; i++) {
            right -= currentRow.getValue(step + i) * resultsBelow.get(i - 1);
        }

        double result = right / left;

        final List<Double> results = new ArrayList<>();
        results.add(result);
        results.addAll(resultsBelow);
        return results;
    }

    private static void requireSolvable(Matrix matrix, int rowIndex) {
        if (matrix.getRow(rowIndex).getValue(rowIndex) == 0d) {
            throw new NoSolutionException();
        }
    }
}
