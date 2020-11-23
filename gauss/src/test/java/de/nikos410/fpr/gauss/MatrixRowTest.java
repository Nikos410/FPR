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
        testRow.multiply(2d);
        assertThat(testRow).isEqualTo(new MatrixRow(94.22, 0d, 820d));
    }

    @Test
    public void testMultiplyByOtherRow() {
        final MatrixRow row1 = new MatrixRow(47.11, 0d, 410d, 1d);
        final MatrixRow row2 = new MatrixRow(2d, 3d, 0d, 5d);

        row1.multiply(row2);

        assertThat(row1).isEqualTo(new MatrixRow(94.22, 0d, 0d, 5d));
        assertThat(row2).isEqualTo(new MatrixRow(2d, 3d, 0d, 5d));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyByOtherRowDifferentLength() {
        final MatrixRow row1 = new MatrixRow(47.11, 0d, 410d, 1d);
        final MatrixRow row2 = new MatrixRow(2d, 3d);

        row1.multiply(row2);
    }
}
