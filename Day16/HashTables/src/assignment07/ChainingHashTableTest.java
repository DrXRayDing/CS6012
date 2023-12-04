package assignment07;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.HashSet;

class ChainingHashTableTest {

    @org.junit.jupiter.api.Test
    void add() {
        ChainingHashTable table = new ChainingHashTable(10, new GoodHashFunctor());
        assertTrue(table.add("test"), "Should return true when adding a new element.");
        assertFalse(table.add("test"), "Should return false when adding a duplicate element.");
        assertEquals(1, table.size(), "Size should be 1 after adding a single element.");
    }

    @org.junit.jupiter.api.Test
    void addAll() {
        ChainingHashTable table = new ChainingHashTable(10, new GoodHashFunctor());
        assertTrue(table.addAll(Arrays.asList("test1", "test2")), "Should return true when adding multiple unique elements.");
        assertFalse(table.addAll(Arrays.asList("test1", "test2")), "Should return false when adding duplicate elements.");
        assertEquals(2, table.size(), "Size should be 2 after adding two unique elements.");
    }

    @org.junit.jupiter.api.Test
    void clear() {
        ChainingHashTable table = new ChainingHashTable(10, new GoodHashFunctor());
        table.add("test");
        table.clear();
        assertEquals(0, table.size(), "Size should be 0 after clear operation.");
    }

    @org.junit.jupiter.api.Test
    void contains() {
        ChainingHashTable table = new ChainingHashTable(10, new GoodHashFunctor());
        table.add("test");
        assertTrue(table.contains("test"), "Should return true for a contained element.");
        assertFalse(table.contains("nonexistent"), "Should return false for a non-contained element.");
    }

    @org.junit.jupiter.api.Test
    void containsAll() {
        ChainingHashTable table = new ChainingHashTable(10, new GoodHashFunctor());
        table.addAll(Arrays.asList("test1", "test2"));
        assertTrue(table.containsAll(Arrays.asList("test1", "test2")), "Should return true when all elements are contained.");
        assertFalse(table.containsAll(Arrays.asList("test1", "nonexistent")), "Should return false if any element is not contained.");
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        ChainingHashTable table = new ChainingHashTable(10, new GoodHashFunctor());
        assertTrue(table.isEmpty(), "Should return true when the table is empty.");
        table.add("test");
        assertFalse(table.isEmpty(), "Should return false when the table is not empty.");
    }

    @org.junit.jupiter.api.Test
    void remove() {
        ChainingHashTable table = new ChainingHashTable(10, new GoodHashFunctor());
        table.add("test");
        assertTrue(table.remove("test"), "Should return true when removing an existing element.");
        assertFalse(table.remove("test"), "Should return false when trying to remove a non-existing element.");
    }

    @org.junit.jupiter.api.Test
    void removeAll() {
        ChainingHashTable table = new ChainingHashTable(10, new GoodHashFunctor());
        table.addAll(Arrays.asList("test1", "test2", "test3"));
        assertTrue(table.removeAll(new HashSet<>(Arrays.asList("test1", "test2"))), "Should return true when removing existing elements.");
        assertFalse(table.removeAll(new HashSet<>(Arrays.asList("test4"))), "Should return false when trying to remove non-existing elements.");
        assertEquals(1, table.size(), "Size should be 1 after removing two elements.");
    }

    @org.junit.jupiter.api.Test
    void size() {
        ChainingHashTable table = new ChainingHashTable(10, new GoodHashFunctor());
        assertEquals(0, table.size(), "Size should be 0 for a new table.");
        table.add("test");
        assertEquals(1, table.size(), "Size should be 1 after adding one element.");
        table.add("anotherTest");
        assertEquals(2, table.size(), "Size should be 2 after adding another element.");
    }
}
