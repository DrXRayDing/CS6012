package assignment07;

import java.util.Collection;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String> {
    private LinkedList<String>[] storage;
    private HashFunctor functor;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public ChainingHashTable(int capacity, HashFunctor functor) {
        // Initialize storage array of LinkedLists with the specified capacity.
        this.capacity = capacity;
        storage = (LinkedList<String>[]) new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            storage[i] = new LinkedList<>();
        } // Initialize each element of the storage array with a new LinkedList instance to resolve collisions.
        this.functor = functor;
        this.size = 0;
    }

    private int getIndex(String item) {
        // Compute the index for the item based on its hash code.
        // Math.abs() is used for avoiding integer overflow which would lead to a negative hash value.
        return Math.abs(functor.hash(item) % capacity);
    }

    @Override
    public boolean add(String item) {
        int index = getIndex(item); // Get index for the item.
        LinkedList<String> bucket = storage[index];

        // Check if item is already in the bucket to avoid duplicates.
        if (!bucket.contains(item)) {
            bucket.add(item); // Add item to the bucket.
            size++; // Increment size.
            return true;
        }
        return false;
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
        for (LinkedList<String> bucket : storage) {
            bucket.clear(); // Clear each bucket.
        }
        size = 0; // Reset size to 0.
    }

    @Override
    public boolean contains(String item) {
        // Return true if the item is in the appropriate bucket.
        return storage[getIndex(item)].contains(item);
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
        return size == 0; // Return true if size is 0.
    }

    @Override
    public boolean remove(String item) {
        int index = getIndex(item); // Get index for the item.
        LinkedList<String> bucket = storage[index];

        // Remove the item if it exists in the bucket.
        if (bucket.remove(item)) {
            size--; // Decrement size.
            return true;
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
