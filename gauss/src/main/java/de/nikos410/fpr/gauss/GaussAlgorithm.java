package de.nikos410.fpr.gauss;


public class GaussAlgorithm {
    private final Matrix matrix;

    public GaussAlgorithm(Matrix matrix) {
        this.matrix = matrix;
    }

    public Matrix eliminate() {
        for (int step = 0; step < matrix.getHeight() - 1; step++) {
            eliminate(step);
        }

        return matrix;
    }

    private void eliminate(int step) {
        for (int currentRowIndex = step + 1; currentRowIndex < matrix.getHeight(); currentRowIndex++) {
            final MatrixRow stepRow = matrix.getRow(step);
            final MatrixRow currentRow = matrix.getRow(currentRowIndex);

            final double ratio = currentRow.getValue(step) / stepRow.getValue(step);
            final MatrixRow newRow = currentRow.subtract(stepRow.multiply(ratio));

            matrix.setRow(currentRowIndex, newRow);
        }
    }


}
