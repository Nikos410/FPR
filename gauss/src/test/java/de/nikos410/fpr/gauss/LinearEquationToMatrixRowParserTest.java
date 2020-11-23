package de.nikos410.fpr.gauss;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LinearEquationToMatrixRowParserTest {

    @Test
    public void testParse() {
        final MatrixRow result = new LinearEquationToMatrixRowParser("1x + 2y = 3").parse();
        assertThat(result).isEqualTo(new MatrixRow(1d, 2d, 3d));
    }
}
