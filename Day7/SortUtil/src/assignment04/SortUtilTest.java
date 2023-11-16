package assignment04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

public class SortUtilTest {
    private Comparator<Integer> comparator;

    @BeforeEach
    public void setUp() {
        comparator = Integer::compareTo;
    }

    @Test
    public void testMergesortEmptyList() {
        ArrayList<Integer> list = new ArrayList<>();
        SortUtil.mergesort(list, comparator);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testMergesortSingleElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        SortUtil.mergesort(list, comparator);
        assertEquals(1, list.size());
        assertEquals(1, list.get(0));
    }

    @Test
    public void testMergesortMultipleElements() {
        ArrayList<Integer> list = SortUtil.generateAverageCase(10);
        SortUtil.mergesort(list, comparator);
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i) <= list.get(i + 1));
        }
    }

    @Test
    public void testQuicksortEmptyList() {
        ArrayList<Integer> list = new ArrayList<>();
        SortUtil.quicksort(list, comparator);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testQuicksortSingleElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        SortUtil.quicksort(list, comparator);
        assertEquals(1, list.size());
        assertEquals(1, list.get(0));
    }

    @Test
    public void testQuicksortMultipleElements() {
        ArrayList<Integer> list = SortUtil.generateAverageCase(10);
        SortUtil.quicksort(list, comparator);
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i) <= list.get(i + 1));
        }
    }

    @Test
    public void testGenerateBestCase() {
        ArrayList<Integer> list = SortUtil.generateBestCase(10);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(i + 1, list.get(i));
        }
    }

    @Test
    public void testGenerateWorstCase() {
        ArrayList<Integer> list = SortUtil.generateWorstCase(10);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(10 - i, list.get(i));
        }
    }
}
