package assignment09;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class CollisionDetectionExperiment {
    private static final int ITER_COUNT = 100;

    public static void main(String[] args) {
        // you spin me round baby, right round
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000)
            ;

        try (FileWriter fw = new FileWriter(new File("CollisionDetectionExperimentResults.tsv"))) {
            // Header for the TSV (Tab-Separated Values) file
            fw.write("Size\tOptimizedCollision\tNaiveCollision\n");

            for (int exp = 10; exp <= 20; exp++) {
                int size = (int) Math.pow(2, exp);
                long totalTimeOptimized = 0;
                long totalTimeNaive = 0;


                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    // Generate a BSP tree with a set number of segments
                    ArrayList<Segment> segments = generateSegments(size);
                    BSPTree tree = new BSPTree(segments);
                    Segment query = pickQuerySegment();

                    // Time the naive collision detection
                    long startTimeOptimized = System.nanoTime();
                    tree.collision(query);
                    long endTimeOptimized = System.nanoTime();
                    totalTimeOptimized += (endTimeOptimized - startTimeOptimized);


                    // Time the optimized collision detection
                    final boolean[] collisionFound = {false};
                    long startTimeNaive = System.nanoTime();
                    tree.traverseFarToNear(1, 2, (segment) -> {
                        if (segment.intersects(query)) {
                            collisionFound[0] = true;
                        }
                    });
                    long endTimeNaive = System.nanoTime();
                    totalTimeNaive += (endTimeNaive - startTimeNaive);
                }

                // Calculate average times
                long avgTimeNaive = totalTimeNaive / ITER_COUNT;
                long avgTimeOptimized = totalTimeOptimized / ITER_COUNT;



                // Output the results
                System.out.println(size + "\t" + avgTimeOptimized + "\t" + avgTimeNaive);
                fw.write(size + "\t" + avgTimeOptimized + "\t" + avgTimeNaive + "\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // This method should generate a set of segments to build the BSP tree
    private static ArrayList<Segment> generateSegments(int size) {
        ArrayList<Segment> segments = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            segments.add(new Segment(i, 0, i, 1)); // Vertical segment
        }
        return segments;
    }

    // This method should pick a query segment to test for collisions
    private static Segment pickQuerySegment() {

        return new Segment(Math.random(),Math.random(),Math.random(),Math.random());
    }
}
