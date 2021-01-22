import de.nikos410.fpr.employeemanagement.util.BubbleSortHelper;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BubbleSortHelperTest {

    @Test
    public void testSorting() {
        final List<BigInteger> testData = testData();
        final var bubbleSortHelper = new BubbleSortHelper<>(testData);

        Assertions.assertThat(bubbleSortHelper.sorted()).containsExactlyElementsOf(testDataSorted());
    }

    @Test
    public void testSortingTwice() {
        final List<BigInteger> testData = testData();
        final var bubbleSortHelper = new BubbleSortHelper<>(testData);

        Assertions.assertThat(bubbleSortHelper.sorted()).containsExactlyElementsOf(testDataSorted());
        Assertions.assertThat(bubbleSortHelper.sorted()).containsExactlyElementsOf(testDataSorted());
    }

    @Test
    public void testOriginalListNotModified() {
        final List<BigInteger> testData = testData();
        new BubbleSortHelper<>(testData).sorted();

        Assertions.assertThat(testData).containsExactlyElementsOf(testData());
    }

    private List<BigInteger> testDataSorted() {
        final var testData = testData();
        Collections.sort(testData);
        return testData;
    }

    private List<BigInteger> testData() {
        return new ArrayList<>(List.of(BigInteger.TEN, BigInteger.ONE, BigInteger.ZERO, BigInteger.valueOf(4711), BigInteger.TWO, BigInteger.ONE));
    }
}
