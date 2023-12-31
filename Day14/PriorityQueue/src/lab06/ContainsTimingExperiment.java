package lab06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ContainsTimingExperiment {

    private static final int ITER_COUNT = 100;

    public static void main(String[] args) {
        // you spin me round baby, right round
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000)
            ;

        try (FileWriter fw = new FileWriter(new File("Heap_In_Order_Remove.tsv"))) { // open up a file writer so we can write
            // to file.
            Random random = new Random();
            for (int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
                int size = (int) Math.pow(2, exp); // or ..

                // Do the experiment multiple times, and average out the results
                long totalTime = 0;


                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    //SET UP!
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int i = 0; i < size; i++){
                        list.add(i);
                    }
//                    Collections.shuffle(list);

//                    TreeSetPriorityQueue pqueue = new TreeSetPriorityQueue();
//                    for (int i: list){
//                        pqueue.add(i);
//                    }
                    PriorityQueue<Integer> pqueue = new HeapPriorityQueue<>(list);



                    // TIME IT!
                    long start = System.nanoTime();
                    while(!pqueue.isEmpty()){ pqueue.removeMin(); }
                    long stop = System.nanoTime();
                    totalTime += stop - start;
                }
                double averageTime = totalTime / (double) ITER_COUNT;
                System.out.println(size + "\t" + averageTime); // print to console
                fw.write(size + "\t" + averageTime + "\n"); // write to file.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}