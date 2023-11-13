package assignment03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchSetTest {
    private BinarySearchSet<Integer> set;

    @BeforeEach
    public void setUp() {
        set = new BinarySearchSet<Integer>();
    }

    @Test
    public void testComparator(){
        assertNull(set.comparator());
    }


    @Test
    public void testFirstAndLast() {
        set.add(6);
        set.add(3);
        set.add(18);
        assertEquals( 3, set.first()); // First element should be the smallest
        assertEquals( 18, set.last()); // Last element should be the largest

        set.add(-3);
        assertEquals(-3,set.first()); // Test of negative integer
    }

    @Test
    public void testSize() {
        set.add(11);
        set.add(22);
        set.add(33);
        set.add(-44);
        assertEquals(4, set.size()); //Size should reflect number of elements

        set.addAll(Arrays.asList(1,2,3,4));
        assertEquals(8,set.size()); //Size doubled after calling addAll()
    }


    @Test
    public void testRemove() {
        set.add(11);
        set.add(22);
        set.add(33);
        set.add(-44);
        assertEquals(4, set.size());
        assertTrue(set.remove(22)); // Remove should return true for existing element
        assertEquals(3, set.size());
        assertFalse(set.remove(2)); // Remove should return false for non-existing element
    }

    @Test
    public void testRemoveAll(){
        set.add(11);
        set.add(22);
        set.add(33);
        set.add(-44);
        set.removeAll(Arrays.asList(22,-44));
        assertTrue(set.contains(11));
        assertTrue(set.containsAll(Arrays.asList(11,33))); //test of containsAll
        assertFalse(set.contains(22));
        assertFalse(set.containsAll(Arrays.asList(11,22)));
        assertEquals(2, set.size());
    }

    @Test
    public void testClear(){
        set.add(11);
        set.add(22);
        set.add(33);
        set.add(-44);
        set.clear();
        assertEquals(0, set.size());
    }


    @Test
    public void testContains(){
        set.add(1);
        set.add(3);
        assertFalse(set.contains(2)); // Set doesn't contain 2
        assertTrue(set.contains(3)); // Set contains 3
    }

    @Test
    public void testIterator() {
        set.add(1);
        set.add(3);
        set.add(2);
        Integer[] expectedOrder = {1, 2, 3};
        Integer[] actualOrder = new Integer[3];
        int i = 0;
        for (Integer elem : set) { //Iterator should iterate in sorted order
            actualOrder[i++] = elem;
        }
        assertArrayEquals(expectedOrder, actualOrder);
    }

    @Test
    public void testToArray() {
        set.add(1);
        set.add(3);
        set.add(2);
        Integer[] expectedArray = {1, 2, 3};
        //Integer[] actualArray = set.toArray();
        assertArrayEquals(expectedArray, set.toArray()); //toArray should return elements in sorted order
    }

}