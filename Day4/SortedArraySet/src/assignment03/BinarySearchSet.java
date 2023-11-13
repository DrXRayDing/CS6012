package assignment03;

import java.util.*;

public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {
    private E[] data;
    private int size;
    private Comparator<? super E> comparator; // Comparator field

    // First Constructor
    public BinarySearchSet(){
        data = (E[]) new Object[10]; // Capacity is set to 10, which is data.length
        size = 0; // The new instance starts with no elements, so size is set to 0
        this.comparator = null; // Natural ordering, so comparator is null
    }

    // Second Constructor
    public BinarySearchSet(Comparator<? super E> comparator){
        this();
        this.comparator = comparator;
    }

    // Helper method to perform comparison
    @SuppressWarnings("unchecked")
    private int compare(E a, E b) {
        if (comparator != null) {
            return comparator.compare(a, b);
        } else {
            Comparable<? super E> compA = (Comparable<? super E>) a;
            return compA.compareTo(b);
        }
    }

    /**
     * @return The comparator used to order the elements in this set, or null if
     * this set uses the natural ordering of its elements (i.e., uses
     * Comparable).
     */
    @Override
    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    /**
     * @return the first (lowest, smallest) element currently in this set
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public E first() throws NoSuchElementException {
        if (isEmpty()) {throw new NoSuchElementException("Set is empty");} // Throw exception for empty set
        return data[0];
    }

    /**
     * @return the last (highest, largest) element currently in this set
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public E last() throws NoSuchElementException {
        if (isEmpty()) {throw new NoSuchElementException("Set is empty");} // Throw exception for empty set
        return data[size - 1];
    }

    private void ensureCapacity(){
        if(size == data.length){
            E[] newData = (E[]) new Object[data.length * 2]; //Create new array with double the length of this array
            System.arraycopy(data, 0, newData, 0, size); // System arraycopy method to copy this data to new data
            data = newData; // assign the new data to this data
        }
    }

    /**
     * Adds the specified element to this set if it is not already present and
     * not set to null.
     *
     * @param element element to be added to this set
     * @return true if this set did not already contain the specified element
     */
    @Override
    public boolean add(E element) {
        if(element == null || contains(element)) return false;

        ensureCapacity(); // This method is used to resize the array if needed
        int insertIndex = binarySearchInsertIndex(element); // Find the insert index using the method below
        System.arraycopy(data, insertIndex, data, insertIndex + 1, size - insertIndex);
        data[insertIndex] = element; // Insert the element to the insert position
        size++; // Increase data size by one
        return true;
    }

    private int binarySearchInsertIndex(E element) {
        int low = 0, high = size - 1;
        while(low <= high){
            int mid = (low + high)/2;
            int cmp = compare(data[mid], element);

            if(cmp < 0){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return low;
    }

    /**
     * Adds all of the elements in the specified collection to this set if they
     * are not already present and not set to null.
     *
     * @param elements collection containing elements to be added to this set
     * @return true if this set changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends E> elements) {
        boolean modified = false; // Set boolean modified to false, if no new elements were added
        for (E element: elements){
            if(add(element)){ // if add(element) returns true,
                modified = true; // the modified variable keep track of the change
            }
        }
        return modified;
    }

    /**
     * Removes all of the elements from this set. The set will be empty after
     * this call returns.
     */
    @Override
    public void clear() {
        size = 0; // Clear the set by resetting the size to zero
    }

    /**
     * @param element element whose presence in this set is to be tested
     * @return true if this set contains the specified element
     */
    @Override
    public boolean contains(E element) {
        // Use equals would work but this is linear search with a time complexity of O(n)
//        for (E elem: data){
//            if(elem.equals(element)){
//                return true;
//            }
//        }
//        return false;
        if (element == null) return false;
        return binarySearchContains(element, 0, size - 1);
    }

    private boolean binarySearchContains(E element, int low, int high) {
        while(low <= high) { // Binary search with a time complexity of O(log n)
            int mid = (low + high)/2;
            int cmp = compare(data[mid], element);

            if(cmp < 0){
                low = mid + 1;
            }
            else if(cmp > 0){
                high = mid - 1;
            }
            else{
                return true;  // element found
            }
        }
        return false; // element not found
    }

    /**
     * @param elements collection to be checked for containment in this set
     * @return true if this set contains all of the elements of the specified
     * collection
     */
    @Override
    public boolean containsAll(Collection<? extends E> elements) {
        for (E element: elements){
            if(!contains(element)){ //use contains() method to check for each element in the elements
                return false;
            }
        }
        return true;
    }

    /**
     * @return true if this set contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return an iterator over the elements in this set, where the elements are
     * returned in sorted (ascending) order
     */
    @Override
    public Iterator<E> iterator() {
        return new BinarySearchSetIterator();
    }

    //Inner class for the iterator
    private class BinarySearchSetIterator implements Iterator<E> {
        private int currentIndex = 0;
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            if(!hasNext()) throw new NoSuchElementException();
            return data[currentIndex++];
        }
    }

    /**
     * Removes the specified element from this set if it is present.
     *
     * @param element element to be removed from this set, if present
     * @return true if this set contained the specified element
     */
    @Override
    public boolean remove(E element) {
        if(element == null || !contains(element)) return false;

        int index = Arrays.binarySearch(data, 0, size, element, comparator);; // Find the index
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--; // Decrease data size by one
        return true;
    }

    /**
     * Removes from this set all of its elements that are contained in the
     * specified collection.
     *
     * @param elements collection containing elements to be removed from this set
     * @return true if this set changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<? extends E> elements) {
        boolean modified = false;
        for(E element: elements){
            if(remove(element)) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * @return the number of elements in this set
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return an array containing all of the elements in this set, in sorted
     * (ascending) order.
     */
    @Override
    public E[] toArray() {
        return Arrays.copyOf(data, size);
    }
}
