package assignment07;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CollisionsExperiment {
    private static final int ITER_COUNT = 10;

    public static void main(String[] args) {
        // Warm up
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000);

        try (FileWriter fw = new FileWriter(new File("CollisionsExperimentResults.tsv"))) {
            // Header for the TSV (Tab-Separated Values) file
            fw.write("Size\tBadHashFunctor\tMediocreHashFunctor\tGoodHashFunctor\n");

            for (int exp = 10; exp <= 20; exp++) {
                int size = (int) Math.pow(2,exp);
                String[] randomStrings = new String[size];
                for (int i = 0; i < size; i++) {
                    randomStrings[i] = generateRandomString(new Random());
                }

                long totalCollisionsBad = 0;
                long totalCollisionsMediocre = 0;
                long totalCollisionsGood = 0;

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    totalCollisionsBad += countCollisions(new BadHashFunctor(), randomStrings);
                    totalCollisionsMediocre += countCollisions(new MediocreHashFunctor(), randomStrings);
                    totalCollisionsGood += countCollisions(new GoodHashFunctor(), randomStrings);
                }

                // Calculate average collisions
                long averageCollisionsBad = totalCollisionsBad / ITER_COUNT;
                long averageCollisionsMediocre = totalCollisionsMediocre / ITER_COUNT;
                long averageCollisionsGood = totalCollisionsGood / ITER_COUNT;

                // Output results
                System.out.println(size + "\t" + averageCollisionsBad + "\t" + averageCollisionsMediocre + "\t" + averageCollisionsGood);
                fw.write(size + "\t" + averageCollisionsBad + "\t" + averageCollisionsMediocre + "\t" + averageCollisionsGood + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int countCollisions(HashFunctor functor, String[] elements) {
        Set<Integer> hashResults = new HashSet<>();
        for (String element : elements) {
            int hash = functor.hash(element);
            hashResults.add(hash);
        }
        return elements.length - hashResults.size();
    }

    private static String generateRandomString(Random random) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        // Define the range for the length of the string
        int minLength = 5;
        int maxLength = 25;
        // Generate a random length within the specified range
        int length = random.nextInt(maxLength - minLength + 1) + minLength;

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

}
