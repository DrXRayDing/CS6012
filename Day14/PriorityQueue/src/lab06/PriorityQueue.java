package lab06;

public interface PriorityQueue<T extends Comparable<T>>{
    /**
     * Adds an element to the priority queue.
     * @param element The element to be added.
     */
    void add(T element);

    /**
     * Removes and returns the minimum element from the priority queue.
     * If the priority queue is empty, behavior may depend on the implementation
     * (e.g., it might return null or throw an exception).
     * @return The minimum element in the priority queue.
     */
    T removeMin();

    /**
     * Checks if the priority queue is empty.
     * @return true if the priority queue is empty, false otherwise.
     */
    boolean isEmpty();
}
