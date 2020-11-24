package de.nikos410.fpr.gauss;


public class GaussAlgorithm {
    private final Matrix matrix;

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
        int rowWithBiggestValueInStepColumn = step;
        for (int i = step + 1; i < matrix.getHeight(); i++) {
            if (matrix.getRow(i).getValue(step) > matrix.getRow(rowWithBiggestValueInStepColumn).getValue(step)) {
                rowWithBiggestValueInStepColumn = i;
            }
        }

        final MatrixRow temp = matrix.getRow(step);
        matrix.setRow(step, matrix.getRow(rowWithBiggestValueInStepColumn));
        matrix.setRow(rowWithBiggestValueInStepColumn, temp);
    }

    private void eliminateStep(int step) {
        final MatrixRow stepRow = matrix.getRow(step);

        for (int currentRowIndex = step + 1; currentRowIndex < matrix.getHeight(); currentRowIndex++) {
            final MatrixRow currentRow = matrix.getRow(currentRowIndex);

            final double ratio = currentRow.getValue(step) / stepRow.getValue(step);
            final MatrixRow newRow = currentRow.subtract(stepRow.multiply(ratio));

            matrix.setRow(currentRowIndex, newRow);
        }
    }


}
