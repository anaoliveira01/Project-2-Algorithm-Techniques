import java.util.Scanner;

public class Project2 {
    static void main(String[] args){
        Project2 proj = new Project2();
    }

    Project2(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter filename with graph information: ");
        String filename = scanner.nextLine();
        Graph graph = new Graph(filename);
        System.out.print("Please enter source and destination vertices: ");
        int source = scanner.nextInt();
        int target = scanner.nextInt();

    }

}
