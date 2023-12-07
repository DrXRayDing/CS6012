package assignment09;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BSPTreeTimingExperiment {
    private static final int ITER_COUNT = 100;

    public static void main(String[] args) {
        // you spin me round baby, right round
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000)
            ;

        try (FileWriter fw = new FileWriter(new File("BSPTreeExperimentResults.tsv"))) {
            // Header for the TSV (Tab-Separated Values) file
            fw.write("Size\tBulkConstruction\tSequentialInsertion\n");

            for (int exp = 10; exp <= 20; exp++) {
                int size = (int) Math.pow(2, exp);

                long totalTimeBulk = 0;
                long totalTimeSequential = 0;

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    ArrayList<Segment> segments = generateVerticalSegments(size);

                    // Time for bulk construction
                    long startBk = System.nanoTime();
                    new BSPTree(new ArrayList<>(segments)); // Bulk constructor
                    long endBk = System.nanoTime();
                    totalTimeBulk += (endBk - startBk);

                    // Time for sequential insertion
                    long startSq = System.nanoTime();
                    BSPTree tree = new BSPTree();
                    for (Segment segment : segments) {
                        tree.insert(segment);
                    }
                    long endSq = System.nanoTime();
                    totalTimeSequential += (endSq - startSq);
                }

                // Calculate average time
                long averageTimeBulk = totalTimeBulk / ITER_COUNT;
                long averageTimeSequential = totalTimeSequential / ITER_COUNT;

                // Output results
                System.out.println(size + "\t" + averageTimeBulk + "\t" + averageTimeSequential);
                fw.write(size + "\t" + averageTimeBulk + "\t" + averageTimeSequential + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Generate a list of vertical segments.
    private static ArrayList<Segment> generateVerticalSegments(int size) {
        ArrayList<Segment> segments = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            segments.add(new Segment(i, 0, i, 1)); // Vertical segment
        }
        return segments;
    }
}
