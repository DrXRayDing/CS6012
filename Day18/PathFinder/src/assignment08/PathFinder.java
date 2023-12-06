package assignment08;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PathFinder {

    // Inner class to represent a node in the graph (each position in the maze)
    private static class Node {
        int x, y; // Coordinates in the maze
        Node parent; // To keep track of the path from the start

        Node(int x, int y, Node parent) {
            this.x = x;
            this.y = y;
            this.parent = parent; // Used to backtrack the path once the goal is found
        }
    }

    // Method to solve the maze
    public static void solveMaze(String inputFile, String outputFile) {
        try {
            inputFile = "./mazes/" + inputFile;
            File file = new File(inputFile);
            Scanner input = new Scanner(file);

            // Reading maze dimensions from the first line of the file
            String[] dimensions = input.nextLine().split(" ");
            int height = Integer.parseInt(dimensions[0]);
            int width = Integer.parseInt(dimensions[1]);

            // Initializing the maze matrix
            char[][] maze = new char[height][width];
            Node start = null; // Starting position of Pacman

            // Filling the maze matrix with the layout and finding the start position
            for (int i = 0; i < height; i++) {
                String line = input.nextLine();
                for (int j = 0; j < width; j++) {
                    maze[i][j] = line.charAt(j);
                    if (maze[i][j] == 'S') {
                        start = new Node(i, j, null); // Assigning start position, it has no parent
                    }
                }
            }

            // Perform breadth-first search to find the shortest path
            boolean pathExists = bfs(maze, start);

            // Writing the solved maze to the output file
            try (PrintWriter output = new PrintWriter(new FileWriter(outputFile))) {
                output.println(height + " " + width);
                for (char[] row : maze) {
                    output.println(new String(row));
                }
            }

            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method for breadth-first search
    private static boolean bfs(char[][] maze, Node start) {
        // Directions for movement: up, down, left, right
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        boolean[][] visited = new boolean[maze.length][maze[0].length]; // To keep track of visited nodes

        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true; // Mark the start as visited

        while (!queue.isEmpty()) {
            Node current = queue.poll(); // current node has a parent, except for the start node

            // Check if the current node is the goal
            if (maze[current.x][current.y] == 'G') {
                markPath(maze, current); // Trace and mark the path
                return true; // Path found
            }

            // Explore adjacent cells (up, down, left, right)
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                // Check bounds and if the cell is open and not visited
                if (nx >= 0 && ny >= 0 && nx < maze.length && ny < maze[0].length &&
                        maze[nx][ny] != 'X' && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Node(nx, ny, current)); // Current becomes parent of the new node, add to queue for further exploration
                }
            }
        }

        // If no path is found
        return false;
    }

    // Method to backtrack and mark the path in the maze
    private static void markPath(char[][] maze, Node end) {
        for (Node n = end; n.parent != null; n = n.parent) {
            if (maze[n.x][n.y] != 'S' && maze[n.x][n.y] != 'G') {
                maze[n.x][n.y] = '.'; // Marking the shortest path with dots
            }
        }
    }

}
