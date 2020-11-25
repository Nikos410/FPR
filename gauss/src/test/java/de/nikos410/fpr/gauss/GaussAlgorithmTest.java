package de.nikos410.fpr.gauss;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GaussAlgorithmTest {

    @Test
    public void testElimination() {
        Assertions.assertThat(GaussAlgorithm.eliminate(createInputMatrix())).isEqualTo(createExpectedResultMatrix());
    }

    private static Matrix createInputMatrix() {
        final List<MatrixRow> inputRows = new ArrayList<>();
        inputRows.add(new MatrixRow(0d, 2d, 3d, 4d));
        inputRows.add(new MatrixRow(1d, 1d, 1d, 2d));
        inputRows.add(new MatrixRow(3d, 3d, 1d, 0d));
        return new Matrix(inputRows);
    }

    private static Matrix createExpectedResultMatrix() {
        final List<MatrixRow> inputRows = new ArrayList<>();
        inputRows.add(new MatrixRow(3d, 3d, 1d, 0d));
        inputRows.add(new MatrixRow(0d, 2d, 3d, 4d));
        inputRows.add(new MatrixRow(0d, 0d, 0.6666666666666667, 2d));
        return new Matrix(inputRows);
    }
}
