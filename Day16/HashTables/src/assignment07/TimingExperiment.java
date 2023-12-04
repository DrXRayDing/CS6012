package assignment07;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class TimingExperiment {
    private static final int ITER_COUNT = 10;

    public static void main(String[] args) {
        try (FileWriter fw = new FileWriter(new File("AddExperimentResults0.tsv"))) {
            // Header for the TSV (Tab-Separated Values) file
            fw.write("Size\tBadHashFunctor\tMediocreHashFunctor\tGoodHashFunctor\n");

            for (int exp = 10; exp <= 18; exp++) {
                int size = (int) Math.pow(2,exp);
                //int size = exp * 1000;
                String[] randomStrings = new String[size];
                for (int i = 0; i < size; i++) {
                    randomStrings[i] = generateRandomString(new Random());
                }

                // Test for each operation

                long totalBad = 0, totalMediocre = 0, totalGood = 0;

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    totalBad += timeOperation(new BadHashFunctor(), "add", size, randomStrings);
                    totalMediocre += timeOperation(new MediocreHashFunctor(), "add", size, randomStrings);
                    totalGood += timeOperation(new GoodHashFunctor(), "add", size, randomStrings);
                }

                // Calculate average time
                long averageTimeBad = totalBad / ITER_COUNT;
                long averageTimeMediocre = totalMediocre / ITER_COUNT;
                long averageTimeGood = totalGood / ITER_COUNT;

                // Output results
                System.out.println(size + "\t" + averageTimeBad + "\t" + averageTimeMediocre + "\t" + averageTimeGood);
                fw.write(size + "\t" + averageTimeBad + "\t" + averageTimeMediocre + "\t" + averageTimeGood + "\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long timeOperation(HashFunctor functor, String operation, int size, String[] elements) {
        ChainingHashTable table = new ChainingHashTable(size, functor);

        // Measure the time of the specified operation
        long startTime = System.nanoTime();
        switch (operation) {
            case "add":
                table.addAll(List.of(elements));
                break;
            case "contains":
                for (String element : elements) {
                    table.contains(element);
                }
                break;
            case "remove":
                // Ensure elements are added before removal
                for (String element : elements) {
                    table.add(element);
                }
                for (String element : elements) {
                    table.remove(element);
                }
                break;
        }
        return System.nanoTime() - startTime;
    }

    private static String generateRandomString(Random random) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int minLength = 5;
        int maxLength = 15;
        int length = random.nextInt(maxLength - minLength + 1) + minLength;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}
