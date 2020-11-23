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

    @Test
    public void testToStringWithNull() {
        final MatrixRow testRow = new MatrixRow(47.11, null, 42d);
        assertThat(testRow.toString()).isEqualTo(" 47.11 |   null |   42.0");
    }
}
