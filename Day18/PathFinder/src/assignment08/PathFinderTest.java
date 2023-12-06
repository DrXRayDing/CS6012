package assignment08;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class PathFinderTest {

    @org.junit.jupiter.api.Test
    public void testTinyMazeAreEqual() throws Exception {

        // tinyMaze
        String pathToFile1 = "./mazes/tinyMazeSol.txt";
        String pathToFile2 = "tinyMazeOutput.txt";
        // Read the contents of both files
        List<String> file1Content = Files.readAllLines(Paths.get(pathToFile1));
        List<String> file2Content = Files.readAllLines(Paths.get(pathToFile2));
        // Assert that the contents are equal
        assertEquals(file1Content, file2Content);
    }

    @org.junit.jupiter.api.Test
    public void testUnsolvableAreEqual() throws Exception {
        // unsolvable
        String pathToFile1 = "./mazes/unsolvableSol.txt";
        String pathToFile2 = "unsolvableOutput.txt";
        // Read the contents of both files
        List<String> file1Content = Files.readAllLines(Paths.get(pathToFile1));
        List<String> file2Content = Files.readAllLines(Paths.get(pathToFile2));
        // Assert that the contents are equal
        assertEquals(file1Content, file2Content);
    }

    @org.junit.jupiter.api.Test
    public void testClassicAreEqual() throws Exception {
        // classic
        String pathToFile1 = "./mazes/classicSol.txt";
        String pathToFile2 = "classicOutput.txt";
        // Read the contents of both files
        List<String> file1Content = Files.readAllLines(Paths.get(pathToFile1));
        List<String> file2Content = Files.readAllLines(Paths.get(pathToFile2));
        // Assert that the contents are equal
        assertEquals(file1Content, file2Content);
    }

    @org.junit.jupiter.api.Test
    public void testBigMazeAreEqual() throws Exception {
        // bigMaze
        String pathToFile1 = "./mazes/bigMazeSol.txt";
        String pathToFile2 = "bigMazeOutput.txt";
        // Read the contents of both files
        List<String> file1Content = Files.readAllLines(Paths.get(pathToFile1));
        List<String> file2Content = Files.readAllLines(Paths.get(pathToFile2));
        // Assert that the contents are equal
        assertEquals(file1Content, file2Content);
    }

    @org.junit.jupiter.api.Test
    public void testMediumMazeAreEqual() throws Exception {
        // mediumMaze
        String pathToFile1 = "./mazes/mediumMazeSol.txt";
        String pathToFile2 = "mediumMazeOutput.txt";
        // Read the contents of both files
        List<String> file1Content = Files.readAllLines(Paths.get(pathToFile1));
        List<String> file2Content = Files.readAllLines(Paths.get(pathToFile2));
        // Assert that the contents are equal
        assertEquals(file1Content, file2Content);
    }

    @org.junit.jupiter.api.Test
    public void testStraightAreEqual() throws Exception {
        // straight
        String pathToFile1 = "./mazes/straightSol.txt";
        String pathToFile2 = "straightOutput.txt";
        // Read the contents of both files
        List<String> file1Content = Files.readAllLines(Paths.get(pathToFile1));
        List<String> file2Content = Files.readAllLines(Paths.get(pathToFile2));
        // Assert that the contents are equal
        assertEquals(file1Content, file2Content);
    }

    @org.junit.jupiter.api.Test
    public void testTurnAreEqual() throws Exception {
        // straight
        String pathToFile1 = "./mazes/turnSol.txt";
        String pathToFile2 = "turnOutput.txt";
        // Read the contents of both files
        List<String> file1Content = Files.readAllLines(Paths.get(pathToFile1));
        List<String> file2Content = Files.readAllLines(Paths.get(pathToFile2));
        // Assert that the contents are equal
        assertEquals(file1Content, file2Content);
    }

    @org.junit.jupiter.api.Test
    public void demoMazeAreEqual() throws Exception {
        // demoMaze
        String pathToFile1 = "./mazes/demoMazeSol.txt";
        String pathToFile2 = "demoMazeOutput.txt";
        // Read the contents of both files
        List<String> file1Content = Files.readAllLines(Paths.get(pathToFile1));
        List<String> file2Content = Files.readAllLines(Paths.get(pathToFile2));
        // Assert that the contents are equal
        //assertEquals(file1Content, file2Content);

        /*
        Count the number of "." in each file

        file1Content.stream():
        file1Content is a List<String>, which is a collection of strings (each string representing a line in the file).
        .stream() converts this list into a Stream<String>. Streams are a Java feature that allows you to process collections of objects in a functional style.

        flatMapToInt(String::chars):
        flatMapToInt is a method used to "flatten" complex structures into a simpler IntStream. In this case, it's transforming a Stream<String> into an IntStream (a stream of primitive int values).
        String::chars is a method reference. For each string in the stream, String::chars is called, which returns an IntStream representing all the characters in the string.
        By applying flatMapToInt, you transform a stream of strings into a stream of integers, where each integer represents a character in one of the strings.

        filter(ch -> ch == '.'):
        filter is used to select only certain elements from the stream based on a given predicate.
        ch -> ch == '.' is a lambda expression that defines the predicate. It checks if each character (represented as an integer) is equal to the character '.'.
        Only the characters that match this condition (i.e., are '.') will be passed through the filter.
         */
        long dotCountFile1 = file1Content.stream().flatMapToInt(String::chars).filter(ch -> ch == '.').count();
        long dotCountFile2 = file2Content.stream().flatMapToInt(String::chars).filter(ch -> ch == '.').count();

        // Assert that the dot counts are equal
        assertEquals(dotCountFile1, dotCountFile2);
    }

    @org.junit.jupiter.api.Test
    public void testRandomMazeAreEqual() throws Exception {
        // randomMaze
        String pathToFile1 = "./mazes/randomMazeSol.txt";
        String pathToFile2 = "randomMazeOutput.txt";
        // Read the contents of both files
        List<String> file1Content = Files.readAllLines(Paths.get(pathToFile1));
        List<String> file2Content = Files.readAllLines(Paths.get(pathToFile2));
        // Assert that the contents are equal
        //assertEquals(file1Content, file2Content);

        // Count the number of "." in each file
        long dotCountFile1 = file1Content.stream().flatMapToInt(String::chars).filter(ch -> ch == '.').count();
        long dotCountFile2 = file2Content.stream().flatMapToInt(String::chars).filter(ch -> ch == '.').count();

        // Assert that the dot counts are equal
        assertEquals(dotCountFile1, dotCountFile2);
    }

    @org.junit.jupiter.api.Test
    public void testTinyOpenAreEqual() throws Exception {
        // tinyOpen
        String pathToFile1 = "./mazes/tinyOpenSol.txt";
        String pathToFile2 = "tinyOpenOutput.txt";
        // Read the contents of both files
        List<String> file1Content = Files.readAllLines(Paths.get(pathToFile1));
        List<String> file2Content = Files.readAllLines(Paths.get(pathToFile2));
        // Assert that the contents are equal
        //assertEquals(file1Content, file2Content);

        // Count the number of "." in each file
        long dotCountFile1 = file1Content.stream().flatMapToInt(String::chars).filter(ch -> ch == '.').count();
        long dotCountFile2 = file2Content.stream().flatMapToInt(String::chars).filter(ch -> ch == '.').count();

        // Assert that the dot counts are equal
        assertEquals(dotCountFile1, dotCountFile2);
    }



}
