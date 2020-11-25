package de.nikos410.fpr.gauss;


public class GaussAlgorithm {

    private GaussAlgorithm() {
    }

    public static Matrix eliminate(Matrix initialMatrix) {
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
}
