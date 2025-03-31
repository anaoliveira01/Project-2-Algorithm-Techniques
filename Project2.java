import java.util.Scanner;
import java.util.Arrays;

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

        int[] path = graph.getPath(source, target);

        System.out.print("Shortest path: ");
        for (int i = 0; i < path.length; i++) {
            System.out.print(path[i]);
            if (i < path.length - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();

        int cost = 0;
        for (int i = 0; i < path.length-1; i++) {
            cost += graph.getWeight(path[i], path[i+1]);
        }
        System.out.println("Cost: " + cost);

        scanner.close();

    }

}
