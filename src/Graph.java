
import java.util.ArrayList;

public class Graph {

    private int vertices_no;
    private ArrayList<ArrayList<Integer>> adj_list;
    private int[][] adj_matrix;

    // Constructor
    public Graph(int vertices_no) {
        this.vertices_no = vertices_no;

        // Initialize adjacency list
        adj_list = new ArrayList<>();

        for (int i = 0; i < vertices_no; i++) {
            adj_list.add(new ArrayList<>());
        }

        // Initialize adjacency matrix
        adj_matrix = new int[vertices_no][vertices_no];
    }

    // Add an undirected edge to both representations
    public void add_edges(int from, int to) {

        if (from < 0 || from >= vertices_no ||
                to < 0 || to >= vertices_no) {
            throw new IllegalArgumentException("Invalid vertex.");
        }

        adj_list.get(from).add(to);
        adj_list.get(to).add(from);

        adj_matrix[from][to] = 1;
        adj_matrix[to][from] = 1;
    }

    // DFS using Adjacency List
    public void DFS_AdjList(int start_node) {

        boolean[] visited = new boolean[vertices_no];
        ArrayList<Integer> order = new ArrayList<>();

        // Visit the starting vertex first
        DFS_List_Helper(start_node, visited, order);

        // Visit any remaining disconnected vertices
        for (int i = 0; i < vertices_no; i++) {
            if (!visited[i]) {
                DFS_List_Helper(i, visited, order);
            }
        }

        System.out.println("DFS using Adjacency List:");
        System.out.println(order);
    }

    // Recursive helper for adjacency list
    private void DFS_List_Helper(int vertex,
                                 boolean[] visited,
                                 ArrayList<Integer> order) {

        visited[vertex] = true;
        order.add(vertex);

        for (int neighbor : adj_list.get(vertex)) {

            if (!visited[neighbor]) {
                DFS_List_Helper(neighbor, visited, order);
            }
        }
    }

    // DFS using Adjacency Matrix
    public void DFS_AdjMatrix(int start_node) {

        boolean[] visited = new boolean[vertices_no];
        ArrayList<Integer> order = new ArrayList<>();

        // Visit the starting vertex first
        DFS_Matrix_Helper(start_node, visited, order);

        // Visit any remaining disconnected vertices
        for (int i = 0; i < vertices_no; i++) {
            if (!visited[i]) {
                DFS_Matrix_Helper(i, visited, order);
            }
        }

        System.out.println("DFS using Adjacency Matrix:");
        System.out.println(order);
    }

    // Recursive helper for adjacency matrix
    private void DFS_Matrix_Helper(int vertex,
                                   boolean[] visited,
                                   ArrayList<Integer> order) {

        visited[vertex] = true;
        order.add(vertex);

        for (int neighbor = 0; neighbor < vertices_no; neighbor++) {

            if (adj_matrix[vertex][neighbor] == 1 &&
                    !visited[neighbor]) {

                DFS_Matrix_Helper(neighbor, visited, order);
            }
        }
    }
}
