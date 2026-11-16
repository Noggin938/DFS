
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Enter 'Edges.txt' to access the text file
        System.out.print("Enter the name of the input file: ");
        String fileName = input.nextLine();

        ArrayList<int[]> edges = new ArrayList<>();
        int maxVertex = -1;

        // Read edges from the text file
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {

                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] values = line.split("\\s+");

                if (values.length < 2) {
                    continue;
                }

                try {
                    int source = Integer.parseInt(values[0]);
                    int target = Integer.parseInt(values[1]);

                    edges.add(new int[]{source, target});

                    maxVertex = Math.max(maxVertex,
                            Math.max(source, target));

                } catch (NumberFormatException e) {
                    // Skip the header (Source Target)
                    continue;
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            input.close();
            return;
        }

        if (maxVertex < 0) {
            System.out.println("No valid edges found in the file.");
            input.close();
            return;
        }

        // Determine the number of vertices
        int vertices = maxVertex + 1;

        // Create the graph
        Graph g = new Graph(vertices);

        // Add all edges to the graph
        for (int[] edge : edges) {
            g.add_edges(edge[0], edge[1]);
        }

        // Get starting vertex
        System.out.print("Enter the starting vertex: ");
        int start = input.nextInt();

        if (start < 0 || start >= vertices) {
            System.out.println("Invalid starting vertex.");
            input.close();
            return;
        }

        System.out.println("\nNumber of vertices: " + vertices);

        // Perform DFS using adjacency list
        g.DFS_AdjList(start);

        System.out.println();

        // Perform DFS using adjacency matrix
        g.DFS_AdjMatrix(start);

        input.close();
        //Assisted by ChatGPT
    }
}
