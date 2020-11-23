package de.nikos410.fpr.gauss;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MatrixRowTest {

    @Test
    public void testToString() {
        final Double[] values = {47.11, 410d, 42d};
        final MatrixRow testRow = new MatrixRow(Arrays.asList(values));
        assertThat(testRow.toString()).isEqualTo("47.11 | 410.0 | 42.0");
    }

    @Test
    public void testToStringWithNull() {
        final Double[] values = {47.11, null, 42d};
        final MatrixRow testRow = new MatrixRow(Arrays.asList(values));
        assertThat(testRow.toString()).isEqualTo("47.11 | null | 42.0");
    }
}
