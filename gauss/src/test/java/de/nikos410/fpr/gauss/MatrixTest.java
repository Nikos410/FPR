package de.nikos410.fpr.gauss;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MatrixTest {

    @Test
    public void testToString() {
        assertThat(createTestMatrix().toString()).isEqualTo("   1.0 |   42.0 |  410.0\n  12.0 |    0.0 |    0.0");
    }

    @Test
    public void testGetRow() {
        assertThat(createTestMatrix().getRow(1)).isEqualTo(new MatrixRow(12d, 0d, 0d));
    }

    @Test
    public void testSetRow() {
        final Matrix matrix = createTestMatrix();

        final MatrixRow row = new MatrixRow(0d, 1d, 2d);
        matrix.setRow(1, row);

        assert matrix.getRow(1) == row;
    }

    @Test
    public void testGetHeight() {
        assertThat(createTestMatrix().getHeight()).isEqualTo(2);
    }

    private static Matrix createTestMatrix() {
        final List<MatrixRow> rows = new LinkedList<>();
        rows.add(new MatrixRow(1d, 42d, 410d));
        rows.add(new MatrixRow(12d, 0d, 0d));
        return new Matrix(rows);
    }
}
