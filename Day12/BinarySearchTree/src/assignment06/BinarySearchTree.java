package assignment06;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

    // Inner class for binary tree node
    private class Node{
        T data;
        Node left;
        Node right;

        // Node constructor with T data as parameter
        Node(T data){
            this.data = data;
            left = null;
            right = null;
        }
    }

    // Root node for BST
    private Node root;
    // Store the size of the tree and update it whenever nodes are added or removed
    private int size;

    // Empty constructor
    public BinarySearchTree(){
        root = null;
    }

    @Override
    public boolean add(T item) {
        if (item == null){
            throw new NullPointerException("Adding null to BST is not allowed.");
        }

        if (root == null){
            root = new Node(item);
            size = 1;
            return true;
        } else {
            boolean added = addRecursive(root, item);
            if(added) size++;
            return added;
        }
    }

    private boolean addRecursive(Node current, T item) {
        int comp = item.compareTo(current.data);

        if (comp < 0){ // Add to left node
            if (current.left == null){
                current.left = new Node(item);
                return true;
            } else {
                return addRecursive(current.left, item);
            }
        } else if (comp > 0){ // Add to right node
            if (current.right == null){
                current.right = new Node(item);
                return true;
            } else {
                return addRecursive(current.right, item);
            }
        } else {
            return false; // Item already exits
        }

    }

    @Override
    public boolean addAll(Collection<? extends T> items) {
        if (items == null){
            throw new NullPointerException("Adding null collection to BST is not allowed.");
        }

        boolean isAdded = false;
        for (T item: items){
            if (add(item)){
                isAdded = true;
            }
        }
        return isAdded;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean contains(T item) {

        if (item == null){
            throw new NullPointerException("Null item cannot be checked.");
        }
        Node current = root;
        while (current != null) {
            int comp = item.compareTo(current.data);
            if (comp < 0) {
                current = current.left;
            } else if (comp > 0) {
                current = current.right;
            } else {
                return true; // Item found
            }
        }
        return false; // Item not found
//        return containsRecursive(root, item);
//    }
//
//    private boolean containsRecursive(Node current, T item) {
//        /*
//        If the contains method is not correctly handling the case
//        when a traversal reaches a null node (i.e., the item is not found in the tree),
//        it will throw a NullPointerException.
//        */
//        if (current == null){
//            return false;
//        }
//
//        int comp = item.compareTo(current.data);
//
//        if (comp < 0){ // Continue ot compare with its left node
//            return containsRecursive(current.left, item);
//        } else if (comp > 0){ // Continue ot compare with its right node
//            return containsRecursive(current.right, item);
//        } else {
//            return true; // Item found
//        }
    }

    @Override
    public boolean containsAll(Collection<? extends T> items) {

        for(T item: items){

            if (!contains(item)){
                return false;
            }
        }
        return true;
    }

    @Override
    public T first() throws NoSuchElementException {
        if (root == null){
            throw new NoSuchElementException("BST is null");
        }
        return findMostLeft(root).data;
    }

    private Node findMostLeft(Node current) { // This is a recursive method
        return current.left == null ? current : findMostLeft(current.left); // The ternary conditional operator
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public T last() throws NoSuchElementException {
        if (root == null){
            throw new NoSuchElementException("BST is null");
        }
        return findMostRight(root).data;
    }

    private Node findMostRight(Node current) {
        return current.right == null ? current : findMostRight(current.right);
    }

    @Override
    public boolean remove(T item) {
        int oldSize = size;
        root = removeRecursive(root,item); // The recursive method returns the updated node
        return oldSize > size;
    }

    private Node removeRecursive(Node current, T item) {
        if (current == null){ //Didn't find the item
            return null;
        }

        int comp = item.compareTo(current.data);
        if (comp < 0){
            current.left = removeRecursive(current.left, item);
        } else if (comp > 0){
            current.right = removeRecursive(current.right, item);
        } else { // This is the node we want to delete
            // Node with only one child or no child
            if (current.left == null || current.right == null){
                size--; // Decrement size here, where actual removal happens
                return (current.left != null) ? current.left : current.right;
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            current.data = findMostLeft(current.right).data;
            // Remove the inorder successor in current's right subtree
            current.right = removeRecursive(current.right, current.data);
        }
        return current;
    }


    @Override
    public boolean removeAll(Collection<? extends T> items) {
        if (items == null){
            throw new NullPointerException("Null collection cannot be removed.");
        }

        boolean isRemoved = false;
        for(T item: items){
            if (remove(item)){
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    @Override
    public int size() {
        return size;
    }

//    {
//        return sizeRecursive(root) ;
//    }
//
//    private int sizeRecursive(Node current) {
//        return current == null ? 0 : 1 + sizeRecursive(current.left) + sizeRecursive(current.right);
//    }

    @Override
    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list;
    }

    private void inorderTraversal(Node current, ArrayList<T> list) { //Recursive call and the call stack
        if (current != null){
            inorderTraversal(current.left, list); // Traverse left subtree
            list.add(current.data); // Visit node
            inorderTraversal(current.right, list); // Traverse right subtree
        }
    }

    public void writeDot(String filename) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            out.println("digraph BST {");
            out.println("    node [shape=circle];"); // Set the shape of nodes to circles
            if (root != null) {
                writeDotRecursive(root, out);
            }
            out.println("}");
        } catch (IOException e) {
            System.err.println("Error creating/writing to the file: " + e.getMessage());
        }
    }

    private void writeDotRecursive(Node node, PrintWriter out) {
        // If the node has a left child, draw an edge to it and recurse
        if (node.left != null) {
            out.println("    \"" + node.data + "\" -> \"" + node.left.data +  "\" [label=\"L\"];");
            writeDotRecursive(node.left, out);
        }
        // If the node has a right child, draw an edge to it and recurse
        if (node.right != null) {
            out.println("    \"" + node.data + "\" -> \"" + node.right.data + "\" [label=\"R\"];");
            writeDotRecursive(node.right, out);
        }
        // If the node is a leaf, draw it as an isolated node
        if (node.left == null && node.right == null) {
            out.println("    \"" + node.data + "\";");
        }
    }

}
