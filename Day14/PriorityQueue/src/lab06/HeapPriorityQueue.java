package lab06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class HeapPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {
    private ArrayList<T> heap;

    public HeapPriorityQueue(){
        heap = new ArrayList<>();
    }

    public HeapPriorityQueue(ArrayList<T> elements){
        heap = new ArrayList<>(elements);
        heapify();
    }

    private void heapify() {
        // Start at the last non-leaf node
        for (int i = parent(heap.size() - 1); i >= 0; i--){
            percolateDown(i);
        }
    }

    private void percolateDown(int i) {
        int smallest = i;
        int leftChild = leftChild(i);
        int rightChild = rightChild(i);

        if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(smallest)) < 0) {
            smallest = leftChild;
        }

        if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(smallest)) < 0) {
            smallest = rightChild;
        }

        if(smallest != i){
            Collections.swap(heap, i, smallest);
            percolateDown(smallest);
        }
    }

    private void percolateUp(int i){
        while(i > 0 && heap.get(parent(i)).compareTo(heap.get(i)) > 0){
            Collections.swap(heap, i, parent(i));
            i = parent(i);
        }
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int parent(int i) {
        return (i - 1)/2;
    }

    @Override
    public void add(T element) {
        if (element == null){
            throw new IllegalArgumentException("Null element cannot be added to priority queue.");
        }

        heap.add(element);
        percolateUp(heap.size() - 1);
    }

    @Override
    public T removeMin() {
        if(heap.isEmpty()){
            throw new NoSuchElementException("Priority queque is empty.");
        }

        T min = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        if(!heap.isEmpty()){
            percolateDown(0);
        }
        return min;
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
