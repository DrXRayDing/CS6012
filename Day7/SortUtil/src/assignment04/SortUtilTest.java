package assignment04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

public class SortUtilTest {
    private Comparator<Integer> comp;

    @BeforeEach
    public void setUp() {
        comp = Comparator.naturalOrder();
    }

    @Test
    public void testMergesortEmptyList() {
        ArrayList<Integer> list = new ArrayList<>();
        SortUtil.mergesort(list, comp);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testMergesortSingleElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(11);
        SortUtil.mergesort(list, comp);
        assertEquals(1, list.size());
        assertEquals(11, list.get(0));
    }

    @Test
    public void testMergesortMultipleElements() {
        ArrayList<Integer> list = SortUtil.generateAverageCase(20);
        SortUtil.mergesort(list, comp);
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i) <= list.get(i + 1));
        }
    }

    @Test
    public void testMergesortListOfString() {

        ArrayList<String> list = new ArrayList<>(Arrays.asList("banana", "apple", "cherry", "date"));

        Comparator<String> stringComparator = Comparator.naturalOrder();

        SortUtil.mergesort(list, stringComparator);

        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i).compareTo(list.get(i + 1)) <= 0);
        }

        assertEquals("banana", list.get(1));
    }

    @Test
    public void testMergesortListOfPhoneNumber() {
        // create a list of PhoneNumber objects
        ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>(Arrays.asList(
                new PhoneNumber("555-489-7110"),
                new PhoneNumber("787-654-3450"),
                new PhoneNumber("555-505-5522")
        ));

        //mergesort the list with comparator OrderByNumber
        SortUtil.mergesort(phoneNumbers, new PhoneNumber.OrderByNumber());

        assertEquals(phoneNumbers.get(1).toString(),"(555) 505-5522");
    }


    @Test
    public void testQuicksortEmptyList() {
        ArrayList<Integer> list = new ArrayList<>();
        SortUtil.quicksort(list, comp);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testQuicksortSingleElement() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(11.11);

        SortUtil.quicksort(list, Comparator.naturalOrder());
        assertEquals(1, list.size());
        assertEquals(11.11, list.get(0));
    }

    @Test
    public void testQuicksortMultipleElements() {
        ArrayList<Integer> list = SortUtil.generateAverageCase(20);
        SortUtil.quicksort(list, comp);
        for (int i = 0; i < list.size() -1; i++) {
            assertTrue(list.get(i) <= list.get(i + 1));
        }
    }

    @Test
    public void testGenerateBestCase() {
        ArrayList<Integer> list = SortUtil.generateBestCase(20);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(i + 1, list.get(i));
        }
    }

    @Test
    public void testGenerateWorstCase() {
        ArrayList<Integer> list = SortUtil.generateWorstCase(20);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(20 - i, list.get(i));
        }
    }
}
