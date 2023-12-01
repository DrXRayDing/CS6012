package assignment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class SelfBalancingExperiment {
    private static final int ITER_COUNT = 10;

    public static void main(String[] args) {
        // Warm up
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000)
            ;

        try (FileWriter fw = new FileWriter(new File("SelfBalancingExperiment.tsv"))) { // open up a file writer so we can write
            // to file.
            for (int exp = 1; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
                //int size = (int) Math.pow(2, exp); // or ..
                int size = exp * 10000;

                // Do the experiment multiple times, and average out the results
                long totalTimeTreeSet = 0;
                long totalTimeBST = 0;

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    //SET UP for TreeSet
                    ArrayList<Integer> numbers = new ArrayList<>();
                    for (int i = 0; i < size; i++){
                        numbers.add(i);
                    }
                    Collections.shuffle(numbers);

                    TreeSet<Integer> treeSet = new TreeSet<>();
                    treeSet.addAll(numbers);

                    // TIME IT!
                    long startTreeSet = System.nanoTime();
                    for (int num: numbers){
                        treeSet.contains(num);
                    }
                    long stopTreeSet = System.nanoTime();
                    totalTimeTreeSet += stopTreeSet - startTreeSet;


                    //SET UP for random BST

                    BinarySearchTree randomBST = new BinarySearchTree();
                    randomBST.addAll(numbers);

                    // TIME IT!
                    long startRandom = System.nanoTime();
                    for (int num: numbers){
                        randomBST.contains(num);
                    }
                    long stopRandom = System.nanoTime();
                    totalTimeBST += stopRandom - startRandom;

                }

                double averageTimeTreeSet = totalTimeTreeSet / (double) ITER_COUNT;
                double averageTimeBST = totalTimeBST / (double) ITER_COUNT;
                System.out.println(size + "\t" + averageTimeTreeSet + "\t" + averageTimeBST); // print to console

                fw.write(size + "\t" + averageTimeTreeSet + "\t" + averageTimeBST+ "\n"); // write to file.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
