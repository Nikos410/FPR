package de.nikos410.fpr.gauss;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MatrixRowTest {

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
