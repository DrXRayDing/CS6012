package assignment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class InsertingItemsExperiment {
    private static final int ITER_COUNT = 10;

    public static void main(String[] args) {
        // Warm up
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000)
            ;

        try (FileWriter fw = new FileWriter(new File("InsertingItemsExperiment0.tsv"))) { // open up a file writer so we can write
            // to file.
            for (int exp = 1; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
                //int size = (int) Math.pow(2, exp); // or ..
                int size = exp * 10000;

                // Do the experiment multiple times, and average out the results
                long totalTimeSorted = 0;
                long totalTimeRandom = 0;

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    //SET UP for sorted BST
                    ArrayList<Integer> numbers = new ArrayList<>();
                    for (int i = 0; i < size; i++){
                        numbers.add(i);
                    }
//                    BinarySearchTree sortedBST = new BinarySearchTree();
//                    sortedBST.addAll(numbers);
//
//                    // TIME IT!
//                    long startSorted = System.nanoTime();
//                    for (int num: numbers){
//                        sortedBST.contains(num);
//                    }
//                    long stopSorted = System.nanoTime();
//                    totalTimeSorted += stopSorted - startSorted;


                    //SET UP for random BST
                    Collections.shuffle(numbers);
                    BinarySearchTree randomBST = new BinarySearchTree();
                    randomBST.addAll(numbers);

                    // TIME IT!
                    long startRandom = System.nanoTime();
                    for (int num: numbers){
                        randomBST.contains(num);
                    }
                    long stopRandom = System.nanoTime();
                    totalTimeRandom += stopRandom - startRandom;

                }

                double averageTimeSorted = totalTimeSorted / (double) ITER_COUNT;
                double averageTimeRandom = totalTimeRandom / (double) ITER_COUNT;
                System.out.println(size + "\t" + averageTimeSorted + "\t" + averageTimeRandom); // print to console

                fw.write(size + "\t" + averageTimeSorted + "\t" + averageTimeRandom+ "\n"); // write to file.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
