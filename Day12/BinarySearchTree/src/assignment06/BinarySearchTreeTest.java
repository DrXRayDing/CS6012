package assignment06;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @org.junit.jupiter.api.Test
    void add() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(); // Test of integer
        assertTrue(bst.add(5));
        assertTrue(bst.add(3));
        assertTrue(bst.add(7));
        assertFalse(bst.add(5)); // Duplicate
    }

    @org.junit.jupiter.api.Test
    void addAll() {
        BinarySearchTree<String> bst = new BinarySearchTree<>(); // Test of string
        List<String> numbers = Arrays.asList("xia", "er", "7");
        assertTrue(bst.addAll(numbers));
        assertFalse(bst.addAll(Arrays.asList("er"))); // Duplicate
        assertTrue(bst.addAll(Arrays.asList("xia", "xin"))); // One duplicate and one new item
    }

    @org.junit.jupiter.api.Test
    void clear() {
        BinarySearchTree<Double> bst = new BinarySearchTree<>(); // Test of Double
        List<Double> numbers = Arrays.asList(3.3, 4.4, 5.5, -7.7);
        bst.addAll(numbers);
        bst.clear();
        assertTrue(bst.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void contains() {
        BinarySearchTree<Double> bst = new BinarySearchTree<>(); // Test of Double
        List<Double> numbers = Arrays.asList(3.3, 4.4, 5.5, -7.7);
        bst.addAll(numbers);
        assertTrue(bst.contains(-7.7));
        assertFalse(bst.contains(4.3));
    }

    @org.junit.jupiter.api.Test
    void containsAll() {
        BinarySearchTree<Double> bst = new BinarySearchTree<>();
        List<Double> numbers = Arrays.asList(3.3, 4.4, 5.5, -7.7);
        bst.addAll(numbers);
        assertTrue(bst.containsAll(numbers));
        assertFalse(bst.containsAll(Arrays.asList(3.2,4.4,-7.8)));
    }

    @org.junit.jupiter.api.Test
    void first() {
        BinarySearchTree<Double> bst = new BinarySearchTree<>();
        List<Double> numbers = Arrays.asList(3.3, 4.4, 5.5, -7.7);
        bst.addAll(numbers);
        assertEquals(-7.7, bst.first());
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        assertTrue(bst.isEmpty());
        bst.add("ding");
        assertFalse(bst.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void last() {
        BinarySearchTree<Double> bst = new BinarySearchTree<>();
        List<Double> numbers = Arrays.asList(3.3, 4.4, 5.5, -7.7);
        bst.addAll(numbers);
        assertEquals(5.5, bst.last());
        bst.add(7.7);
        assertEquals(7.7, bst.last());
    }

    @org.junit.jupiter.api.Test
    void remove() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.addAll(Arrays.asList(5, 3, 7));
        assertTrue(bst.remove(5));
        assertFalse(bst.remove(10)); // Non-existent
    }

    @org.junit.jupiter.api.Test
    void removeAll() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.addAll(Arrays.asList(5, 3, 7));
        assertTrue(bst.removeAll(Arrays.asList(5, 3)));
        assertFalse(bst.removeAll(Arrays.asList(10))); // Non-existent
    }

    @org.junit.jupiter.api.Test
    void size() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        assertEquals(0, bst.size());
        bst.addAll(Arrays.asList(5, 3, 7, 10));
        assertEquals(4, bst.size());
    }

    @org.junit.jupiter.api.Test
    void toArrayList() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.addAll(Arrays.asList(5, 3, 7, 10, 1));
        List<Integer> expected = Arrays.asList(1, 3, 5, 7, 10);
        assertEquals(expected, bst.toArrayList());
    }

}