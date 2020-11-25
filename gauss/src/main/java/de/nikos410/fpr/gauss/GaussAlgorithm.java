package de.nikos410.fpr.gauss;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GaussAlgorithm {

    private GaussAlgorithm() {
    }

    public static Matrix eliminate(Matrix initialMatrix) {
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

    public static List<Double> getResults(Matrix matrix) {
        return getResults(matrix, 0);
    }


    private static List<Double> getResults(Matrix matrix, int step) {
        final int matrixHeight = matrix.getHeight();
        if (step >= matrixHeight) {
            return Collections.emptyList();
        }

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
}
