package assignment09;

import java.util.ArrayList;
import java.util.Random;


// Class representing a Binary Space Partitioning tree for storing line segments.
public class BSPTree {
    // Inner class representing a node in the BSP tree.
    private class Node {
        Segment segment; // The segment stored at this node.
        Node left;       // Left child node.
        Node right;      // Right child node.

        // Constructor for Node
        Node(Segment segment) {
            this.segment = segment;
            this.left = null;
            this.right = null;
        }
    }

    // Root of the BSP tree.
    private Node root;

    // Default constructor for creating an empty BSP tree.
    public BSPTree() {
        this.root = null;
    }

    // bulk constructor
    public BSPTree(ArrayList<Segment> segments) {
        root = makeNode(segments);
    }

    private Node makeNode(ArrayList<Segment> segments) {
        if (segments.isEmpty()) {
            return null;
        }

        Segment s = randomSegment(segments);
        Node node = new Node(s);
        ArrayList<Segment> leftSegments = new ArrayList<>();
        ArrayList<Segment> rightSegments = new ArrayList<>();

        for (Segment segment : segments) {
            if (segment.equals(s)) {
                continue; // Skip the partitioning segment
            }

            int side = segment.whichSide(s);
            if (side < 0) {
                leftSegments.add(segment);
            } else if (side > 0) {
                rightSegments.add(segment);
            } else {
                // Handle segment splitting if necessary
                Segment[] splitSegments = s.split(segment);
                for (Segment splitSegment : splitSegments) {
                    if (splitSegment != null) {
                        if (s.whichSide(splitSegment) < 0) {
                            leftSegments.add(splitSegment);
                        } else {
                            rightSegments.add(splitSegment);
                        }
                    }
                }
            }
        }

        node.left = makeNode(leftSegments);
        node.right = makeNode(rightSegments);

        return node;
    }

    private Segment randomSegment(ArrayList<Segment> segments) {
        // Select a random segment from the list
        Random random = new Random();
        int randomIndex = random.nextInt(segments.size());
        return segments.get(randomIndex);
    }




    // Constructor for creating a BSP tree with a list of segments.
//    public BSPTree(ArrayList<Segment> segments) {
//        for (Segment segment : segments) {
//            insert(segment);
//        }
//    }

    // Method to insert a new segment into the BSP tree.
    public void insert(Segment segment) {
        root = insertRec(root, segment);
    }

    // Helper method for recursive insertion.
    private Node insertRec(Node node, Segment segment) {
        if (node == null) {
            return new Node(segment);
        }

        if (segment.whichSide(node.segment) < 0) {
            node.left = insertRec(node.left, segment);
        } else {
            node.right = insertRec(node.right, segment);
        }

        return node;
    }

    // Method to traverse the tree in far-to-near order relative to a point (x, y)
    public void traverseFarToNear(double x, double y, SegmentCallback callback) {
        traverseRec(root, x, y, callback);
    }

    private void traverseRec(Node node, double x, double y, SegmentCallback callback) {
        if (node == null) {
            return;
        }

        if (node.segment.whichSidePoint(x, y) < 0) {
            traverseRec(node.left, x, y, callback);
            callback.callback(node.segment);
            traverseRec(node.right, x, y, callback);
        } else {
            traverseRec(node.right, x, y, callback);
            callback.callback(node.segment);
            traverseRec(node.left, x, y, callback);
        }
    }

    // Method to detect collision with a given segment.
    public Segment collision(Segment query) {
        return collisionRec(root, query);
    }

    // Helper method for recursive collision detection.
    private Segment collisionRec(Node node, Segment query) {
        if (node == null) {
            return null;
        }

        if (node.segment.intersects(query)) {
            return node.segment;
        }

        // Continue searching in both branches
        if (query.whichSide(node.segment) < 0) {
            return collisionRec(node.left, query);
        } else {
            return collisionRec(node.right, query);
        }
    }


}
