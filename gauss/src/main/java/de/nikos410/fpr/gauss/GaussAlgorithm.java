package de.nikos410.fpr.gauss;


public class GaussAlgorithm {
    private Matrix matrix;

    public GaussAlgorithm(Matrix matrix) {
        this.matrix = matrix;
    }

    public Matrix eliminate() {
        for (int step = 0; step < matrix.getHeight() - 1; step++) {
            pivotStep(step);
            eliminateStep(step);
        }

        return matrix;
    }

    private void pivotStep(int step) {
        int rowToSwap = step;
        for (int i = step + 1; i < matrix.getHeight(); i++) {
            if (matrix.getRow(i).getValue(step) > matrix.getRow(rowToSwap).getValue(step)) {
                rowToSwap = i;
            }
        }

        matrix = matrix.swapRows(step, rowToSwap);
    }

    private void eliminateStep(int step) {
        final MatrixRow stepRow = matrix.getRow(step);

        for (int currentRowIndex = step + 1; currentRowIndex < matrix.getHeight(); currentRowIndex++) {
            final MatrixRow currentRow = matrix.getRow(currentRowIndex);

            final double ratio = currentRow.getValue(step) / stepRow.getValue(step);
            final MatrixRow newRow = currentRow.subtract(stepRow.multiply(ratio));

            matrix = matrix.setRow(currentRowIndex, newRow);
        }
    }


}
