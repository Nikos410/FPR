package de.nikos410.fpr.gauss.matrix;

import de.nikos410.fpr.gauss.matrix.MatrixRow;
import de.nikos410.fpr.gauss.matrix.MatrixRowParser;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MatrixRowParserTest {

    @Test
    public void testParse() {
        final MatrixRow result = MatrixRowParser.parse("1 0 3");
        assertThat(result).isEqualTo(new MatrixRow(1d, 0d, 3d));
    }

    @Test
    public void testParseWithNegativeValues() {
        final MatrixRow result = MatrixRowParser.parse("1 -2 3");
        assertThat(result).isEqualTo(new MatrixRow(1d, -2d, 3d));
    }
}
