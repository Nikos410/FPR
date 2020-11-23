package de.nikos410.fpr.gauss;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LinearEquationToMatrixRowParserTest {

    @Test
    public void testParse() {
        final String result = new LinearEquationToMatrixRowParser("1x + 2y = 3").parse().toString();
        assertThat(result).isEqualTo("1.0 | 2.0 | 3.0");
    }
}
