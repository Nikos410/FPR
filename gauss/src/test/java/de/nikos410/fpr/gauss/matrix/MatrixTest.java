package de.nikos410.fpr.gauss.matrix;

import de.nikos410.fpr.gauss.matrix.Matrix;
import de.nikos410.fpr.gauss.matrix.MatrixRow;
import org.junit.Test;

import java.util.ArrayList;
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
        final Matrix initialMatrix = createTestMatrix();

        final MatrixRow row = new MatrixRow(0d, 1d, 2d);

        final Matrix updatedMatrix = initialMatrix.setRow(1, row);
        assert updatedMatrix.getRow(1) == row;
    }

    @Test
    public void testGetHeight() {
        assertThat(createTestMatrix().getHeight()).isEqualTo(2);
    }

    @Test
    public void testEquals() {
        final Matrix matrix1 = createTestMatrix();
        final Matrix matrix2 = createTestMatrix();
        final Matrix matrix3 = matrix2.setRow(1, new MatrixRow(1d, 2d, 3d));

        assertThat(matrix1).isEqualTo(matrix2);
        assertThat(matrix1).isNotEqualTo(matrix3);
        assertThat(matrix1).isNotEqualTo(null);
        assertThat(matrix1).isNotEqualTo("Not a MatrixRow");
        assert matrix1.equals(matrix1);
    }

    @Test
    public void testHashCode() {
        final Matrix matrix1 = createTestMatrix();
        final Matrix matrix2 = createTestMatrix();
        final Matrix matrix3 = matrix2.setRow(1, new MatrixRow(1d, 2d, 3d));

        assertThat(matrix1.hashCode()).isEqualTo(matrix1.hashCode());
        assertThat(matrix1.hashCode()).isEqualTo(matrix2.hashCode());
        assertThat(matrix1.hashCode()).isNotEqualTo(matrix3.hashCode());
    }

    private static Matrix createTestMatrix() {
        final List<MatrixRow> rows = new ArrayList<>();
        rows.add(new MatrixRow(1d, 42d, 410d));
        rows.add(new MatrixRow(12d, 0d, 0d));
        return new Matrix(rows);
    }
}
