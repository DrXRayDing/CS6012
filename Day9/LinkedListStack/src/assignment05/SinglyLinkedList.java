package assignment05;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E>, Iterable<E>{

    private Node<E> head;
    private int size;

    public SinglyLinkedList(){ //Default constructor
        head = null;
        size = 0;
    }

    // Create inner class for nodes
    private static class Node<E>{
        E element;
        Node<E> next;

        Node(E element, Node<E> next){
            this.element = element;
            this.next = next;
        }
    }


    @Override
    public void insertFirst(E element) { // operate in O(1) time
        head = new Node<>(element, head); // New node's next field is set to the current head, and then head is updated to point to this new node
        size++;
    }

    @Override
    public void insert(int index, E element) throws IndexOutOfBoundsException { // operate in O(N) time
        if (index < 0 || index > size){ // The iteration below only iterates to index - 2
            throw new IndexOutOfBoundsException();
        }

        if (index == 0){
            insertFirst(element); // operate in O(1) time
            return;
        }

        Node<E> current = head;
        for (int i = 0; i < index - 1; i++){ // Iterate from the head to index - 2, the iteration takes O(N) in the worst case
            current = current.next; // In a singly-linked list, there is no direct reference to any node except the head
        } // By the end of iteration, current points to the node at index - 1;
        // Each node has a reference to the next node in th list


        current.next = new Node<>(element, current.next); // Create a new Node with element and current.next as its parameter
        // So the new Node has the reference to the node at the original index
        // current.next is updated to the new Node so the node at index - 1 is pointing to the new Node now
        // The new Node is connected to both nodes at original index - 1 and index

        size++;
    }

    @Override
    public E getFirst() throws NoSuchElementException { // operate in O(1) time
        if (head == null){
            throw new NoSuchElementException("List is empty.");
        }
        return head.element;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException { // operate in O(N) time
        if (index < 0 || index > size -1){
            throw new IndexOutOfBoundsException();
        }

        if ( index == 0){
            return getFirst();
        }

        Node<E> current = head;
        for (int i = 0; i < index; i++){
            current = current.next;
        } // By the end of iteration, the loop reaches the target

        return current.element;
    }

    @Override
    public E deleteFirst() throws NoSuchElementException { // operate in O(1) time
        if (head == null){
            throw new NoSuchElementException("List is empty.");
        }

        E element = head.element;
        head = head.next;
        size--;
        return element;
    }

    @Override
    public E delete(int index) throws IndexOutOfBoundsException { // operate in O(N) time
        if (index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException();
        }

        if (index == 0){
            return deleteFirst();
        }

        Node<E> current = head;
        for (int i = 0; i < index - 1; i++){
            current = current.next; // traverse the list
        } // By the end of iteration, the loop reaches the node just before the target

        E element = current.next.element;
        current.next = current.next.next; // This skips over current.next and links current directly to current.next.next
        size--;

        return element;
    }

    @Override
    public int indexOf(E element) { // operate in O(N) time
        Node<E> current = head;
        for (int i = 0; i < size; i++){
            if(current.element.equals(element)){
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int size() { // operate in O(1) time
        return size;
    }

    @Override
    public boolean isEmpty() { // operate in O(1) time
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public void clear() { // operate in O(1) time
        head = null;
        size = 0;
    }

    @Override
    public Object[] toArray() { // operate in O(N) time
        Object[] array = new Object[size];
        Node<E> current = head;
        for (int i = 0; i < size; i++){
            array[i] = current.element;
            current = current.next;
        }
        return array;
    }

    @Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator();
    }

    // Create inner class
    private class SinglyLinkedListIterator implements Iterator<E> {
        private Node<E> current;
        private Node<E> previous = null;

        private Node<E> previousToPrevious = null;
        private boolean callNext = false;

        public SinglyLinkedListIterator(){
            this.current = head;
        }
        @Override
        public boolean hasNext() { // operate in O(1) time
            return current != null;
        }

        @Override
        public E next() { // operate in O(1) time
            if(!hasNext()) throw new NoSuchElementException("Iterator is empty.");

            if (previous != null){
                previousToPrevious = previous; // Retrieving previous node before traversing
            }
            previous = current; // Retrieving current node before traversing
            current = current.next; // Advance the iterator to the next node
            callNext = true;
            return previous.element;
        }

        @Override
        public void remove(){ // operate in O(1) time
            if(!callNext){
                throw new IllegalStateException("next() should be called before calling remove().");
            }

            if(previousToPrevious == null){ // previous was null before calling next()
                head = current; // Remove the first element
            }
            else{
                previousToPrevious.next = current;
            }
            previous = null;
            callNext = false; // Only remove once
            size--;

        }
    }
}
