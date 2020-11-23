package de.nikos410.fpr.gauss;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MatrixRowTest {

    @Test
    public void testEquals() {
        final MatrixRow row1 = new MatrixRow(47.11, 410d, 42d);
        final MatrixRow row2 = new MatrixRow(47.11, 410d, 42d);
        final MatrixRow row3 = new MatrixRow(4711d, 410d, 42d);

        assertThat(row1).isEqualTo(row2);
        assertThat(row1).isNotEqualTo(row3);
        assertThat(row1).isNotEqualTo(null);
        assertThat(row1).isNotEqualTo("Not a MatrixRow");
        assert row1.equals(row1);
    }

    @Test
    public void testHashCode() {
        final MatrixRow row1 = new MatrixRow(47.11, 410d, 42d);
        final MatrixRow row2 = new MatrixRow(47.11, 410d, 42d);
        final MatrixRow row3 = new MatrixRow(4711d, 410d, 42d);

        assertThat(row1.hashCode()).isEqualTo(row2.hashCode());
        assertThat(row1.hashCode()).isNotEqualTo(row3.hashCode());
    }

    @Test
    public void testToString() {
        final MatrixRow testRow = new MatrixRow(47.11, 410d, 42d);
        assertThat(testRow.toString()).isEqualTo(" 47.11 |  410.0 |   42.0");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToStringWithNull() {
        new MatrixRow(47.11, null, 42d);
    }

    @Test
    public void testMultiplyByValue() {
        final MatrixRow testRow = new MatrixRow(47.11, 0d, 410d);

        final MatrixRow result = testRow.multiply(2d);

        assertThat(result).isEqualTo(new MatrixRow(94.22, 0d, 820d));
        assertThat(testRow).isEqualTo(new MatrixRow(47.11, 0d, 410d));
    }

    @Test
    public void testMultiplyByOtherRow() {
        final MatrixRow row1 = new MatrixRow(47.11, 0d, 410d, 1d);
        final MatrixRow row2 = new MatrixRow(2d, 3d, 0d, 5d);

        final MatrixRow result = row1.multiply(row2);

        assertThat(result).isEqualTo(new MatrixRow(94.22, 0d, 0d, 5d));
        assertThat(row1).isEqualTo(new MatrixRow(47.11, 0d, 410d, 1d));
        assertThat(row2).isEqualTo(new MatrixRow(2d, 3d, 0d, 5d));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyByOtherRowDifferentLength() {
        final MatrixRow row1 = new MatrixRow(47.11, 0d, 410d, 1d);
        final MatrixRow row2 = new MatrixRow(2d, 3d);

        row1.multiply(row2);
    }

    @Test
    public void testSubtraction() {
        final MatrixRow row1 = new MatrixRow(47.11, 0d, 410d, 1d);
        final MatrixRow row2 = new MatrixRow(2d, 3d, 0d, 5d);

        final MatrixRow result = row1.subtract(row2);

        assertThat(result).isEqualTo(new MatrixRow(45.11, -3d, 410d, -4d));
        assertThat(row1).isEqualTo(new MatrixRow(47.11, 0d, 410d, 1d));
        assertThat(row2).isEqualTo(new MatrixRow(2d, 3d, 0d, 5d));
    }
}
