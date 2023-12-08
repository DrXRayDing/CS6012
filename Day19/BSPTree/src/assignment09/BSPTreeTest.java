package assignment09;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BSPTreeTest {
    @org.junit.jupiter.api.Test
    public void testBulkConstructor() {
        // Generate segments
        ArrayList<Segment> segments = new ArrayList<>();
        segments.add(new Segment(0, 0, 1, 0));
        segments.add(new Segment(1, 0, 1, 1));
        segments.add(new Segment(1, 1, 0, 1));
        segments.add(new Segment(0, 1, 0, 0));

        // Create BSP tree using bulk constructor
        BSPTree tree = new BSPTree(segments);

        // Check if tree is not null
        assertNotNull(tree);

    }
    @org.junit.jupiter.api.Test
    void insert() {
        BSPTree tree = new BSPTree();
        Segment insertedSegment = new Segment(0, 0, 1, 1);

        tree.insert(insertedSegment);

        // Use a list to collect segments during traversal
        ArrayList<Segment> traversedSegments = new ArrayList<>();
        tree.traverseFarToNear(0.5, 0.5, traversedSegments::add);

        // Check if the inserted segment is included in the traversal
        assertTrue(traversedSegments.contains(insertedSegment));
    }
    @org.junit.jupiter.api.Test
    void traverseFarToNear() {
        BSPTree tree = new BSPTree();
        tree.insert(new Segment(0, 0, 1, 0));
        tree.insert(new Segment(1, 0, 1, 1));
        tree.insert(new Segment(1, 1, 0, 1));

        ArrayList<Segment> traversed = new ArrayList<>();
        tree.traverseFarToNear(0.5, 0.5, traversed::add);

        assertEquals(3, traversed.size());
    }

    @org.junit.jupiter.api.Test
    void collision() {
        BSPTree tree = new BSPTree();
        tree.insert(new Segment(0, 0, 1, 0));
        tree.insert(new Segment(-1, 0, 1, 1));
        tree.insert(new Segment(1, 1, 0, 1));

        Segment query = new Segment(-0.5, 0.5, 0.5, 0.5);
        Segment collidingSegment = tree.collision(query);

        assertNotNull(collidingSegment);
        assertTrue(collidingSegment.intersects(query));
    }
}