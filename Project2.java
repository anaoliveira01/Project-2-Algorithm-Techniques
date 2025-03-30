import java.util.Scanner;

public class Project2 {
    public static void main(String[] args){
        Project2 proj = new Project2();
    }

    Project2(){
        Scanner scanner = new Scanner(System.in);

        //get file name
        System.out.print("Please enter filename with graph information: ");
        String file = scanner.nextLine();
        Graph graph = new Graph(file);

        //get source and destination vertices
        System.out.print("Please enter source and destination vertices: ");
        int source = scanner.nextInt();
        int target = scanner.nextInt();

        // Arrays to store shortest path distances and predecessors
        int[] distances = new int[graph.verticesNumber];
        int[] predecessors = new int[graph.verticesNumber];

        // Compute shortest paths from source
        graph.allShortestPaths(predecessors, distances, source);

        // Get the shortest path from source to target
        int[] path = graph.getPath(source, target, predecessors);

        // Print the shortest path in the desired format
        System.out.print("Shortest path: ");
        for (int i = 0; i < path.length; i++) {
            System.out.print(path[i]);
            if (i < path.length - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();

        // Print the cost of the shortest path
        System.out.println("Cost of the shortest path: " + distances[target]);

        scanner.close();

    }

}
