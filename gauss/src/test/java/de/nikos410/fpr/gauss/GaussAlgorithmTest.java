package de.nikos410.fpr.gauss;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class GaussAlgorithmTest {

    @Test
    public void testElimination() {
        final GaussAlgorithm gaussAlgorithm = new GaussAlgorithm(createInputMatrix());
        Assertions.assertThat(gaussAlgorithm.eliminate()).isEqualTo(createExpectedResultMatrix());
    }

    private static Matrix createInputMatrix() {
        final List<MatrixRow> inputRows = new LinkedList<>();
        inputRows.add(new MatrixRow(2d, -1d, 3d, 1d));
        inputRows.add(new MatrixRow(3d, 1d, -2d, 0d));
        inputRows.add(new MatrixRow(1d, 1d, 1d, 3d));
        return new Matrix(inputRows);
    }

    private static Matrix createExpectedResultMatrix() {
        final List<MatrixRow> inputRows = new LinkedList<>();
        inputRows.add(new MatrixRow(2d, -1d, 3d, 1d));
        inputRows.add(new MatrixRow(0d, 2.5d, -6.5d, -1.5d));
        inputRows.add(new MatrixRow(0d, 0d, 3.4d, 3.4d));
        return new Matrix(inputRows);
    }
}
