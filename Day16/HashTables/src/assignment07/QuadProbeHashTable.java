package assignment07;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

public class QuadProbeHashTable implements Set<String> {
    private String[] storage;
    private HashFunctor functor;
    private int size;
    private int capacity;
    private final double MAX_LOAD_FACTOR = 0.5;

    public QuadProbeHashTable(int capacity, HashFunctor functor) {
        // If the given capacity is not prime, find the next largest prime number
        this.capacity = BigInteger.valueOf(capacity).nextProbablePrime().intValue();
        storage = new String[this.capacity];
        this.functor = functor;
        this.size = 0;
    }

    private int getIndex(String item, int probeCount) {
        int initialIndex = Math.abs(functor.hash(item) % capacity);
        return (initialIndex + probeCount * probeCount) % capacity;
    }

    @Override
    public boolean add(String item) {
        if (size >= capacity * MAX_LOAD_FACTOR) {
            resize();
        }

        for (int probeCount = 0; probeCount < storage.length; probeCount++) {
            int index = getIndex(item, probeCount);
            if (storage[index] == null) { // Empty slot found
                storage[index] = item;
                size++;
                return true;
            } else if (storage[index].equals(item)) {
                return false; // Item already in table
            }
        }
        return false; // Table is full
    }

    private void resize() {
        // Double the size and find the next prime number
        capacity = BigInteger.valueOf(capacity * 2).nextProbablePrime().intValue();
        String[] oldStorage = storage;
        storage = new String[capacity];
        size = 0;

        for (String item : oldStorage) {
            if (item != null) {
                add(item); // Rehash the items
            }
        }
    }

    @Override
    public boolean addAll(Collection<? extends String> items) {
        boolean isChanged = false;
        for (String item : items) {
            if (add(item)) { // Add each item and check if the set changed.
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, null); // Clear all elements
        size = 0; // Reset size
    }

    @Override
    public boolean contains(String item) {
        for (int probeCount = 0; probeCount < storage.length; probeCount++) {
            int index = getIndex(item, probeCount);
            if (storage[index] == null) {
                return false; // Empty slot means item not present
            } else if (storage[index].equals(item)) {
                return true; // Item found
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<? extends String> items) {
        for (String item : items) {
            if (!contains(item)) { // Check each item; return false if any are not contained.
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(String item) {
        for (int probeCount = 0; probeCount < storage.length; probeCount++) {
            int index = getIndex(item, probeCount);
            if (storage[index] == null) {
                return false; // Empty slot means item not present
            } else if (storage[index].equals(item)) {
                storage[index] = null; // Remove item
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<? extends String> items) {
        boolean isRemoved = false;
        for (String item : items) {
            if (remove(item)) {
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    @Override
    public int size() {
        return size;
    }


}
