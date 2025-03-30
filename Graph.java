import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class Graph {

    int verticesNumber;
    int[][] matrix; //adjacency matrix

    public Graph()
    {
        verticesNumber = 5;
        matrix = new int[verticesNumber][verticesNumber];
    }

    public Graph(int n)
    {
        verticesNumber = n;
        matrix = new int[verticesNumber][verticesNumber];
    }

    public Graph(String filename)
    {
        File input = new File(filename);

        Scanner in = null;
        try
        {
            in = new Scanner(input);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found!");
            System.exit(0);
        }

        while (in.hasNextLine())
        {
            verticesNumber = in.nextInt();
            matrix = new int[verticesNumber][verticesNumber];

            for(int i=0; i<verticesNumber; i++)
            {
                for(int j=0; j<verticesNumber; j++)
                {
                    matrix[i][j] = in.nextInt();
                }
            }
        }

        in.close();
    }

    public void addEdge(int v, int w)
    {
        matrix[v][w] = 1;
        matrix[w][v] = 1;
    }

    public int[] findAdjacencyVertices(int v)
    {
        int[] vert = new int[verticesNumber];
        int total = 0;

        for (int i=0; i<verticesNumber; i++)
        {
            if (matrix[v][i] != 0)
            {
                vert[total] = i;
                total++;
            }
        }

        return Arrays.copyOf(vert, total);
    }

    //int getNumberOfVertices()

    //int[] getPath(int s, int t)

    //int getWeight(int v, int w)

    //void removeEdge(int v, int w);

    //String toString()

    /**
     * Calculates the shortest paths from a given vertex to all vertices.
     * @param p paths (p[i] contains previous vertex in the shortest path from v)
     * @param d distances (d[i] contains the shortest distance from v)
     * @param v given vertex
     */
    public void allShortestPaths(int[] p, int[]d, int v) {
        boolean[] visited = new boolean[verticesNumber];
        for (int i=0; i<verticesNumber; i++) {
            visited[i] = false; //not yet visited
            p[i] = -1; //previous vertex in unknown
            d[i] = Integer.MAX_VALUE; //Pli] = INFINITY
        }
        d[v] = 0;

        for (int i=0; i<verticesNumber-1; i++)
        {
            int w = minDistance(visited, d);
            visited[w] = true;

            int[] adj = findAdjacencyVertices(w);

            for (int u : adj)
            {
                if (!visited[u])
                {
                    if (d[w]+matrix[w][u] < d[u])
                    {
                        d[u] = d[w]+matrix[w][u];
                        p[u] = w;
                    }
                }
            }
        }
    }

        	/*Returns shortest path between given source and target vertices.
	* @param s source vertex
	* @param t target vertex
	* @param p paths (p[total] contains previous vertex in the shortest path from
	source vertex
	* Return shortest path stored in array; s is the first and t is the last
	*/

    public int[] getPath(int s, int t, int[] p)
    {
        int[] shortestPath = new int[p.length];
        int current = t;
        int total=0;
        while (current != s)
        {
            shortestPath[total] = current;
            current = p[current];
            total++;
        }
        shortestPath[total++] = s;
        shortestPath = Arrays.copyOf(shortestPath, total);
        //reverses array
        for (int i=0; i<total/2; i++)
        {
            int temp = shortestPath[i];
            shortestPath[i] = shortestPath[total-1-i];
            shortestPath[total-1-i] = temp;
        }
        return shortestPath;
    }

    /* Returns smallest element in given array d, out of those that have not
     * been visited (see allShortestPaths method).
     * @param visited visited elements
     * @param d array of distances
     * @return index of smallest element in d
     */
    private int minDistance(boolean[] visited, int[] distance) {
        int index = -1;
        int min = Integer.MAX_VALUE;
        for (int i=0; i<verticesNumber; i++) {
            if (!visited[i]) {
                if (distance[i] <= min) {
                    min = distance[i];
                    index = 1;
                }
            }
        }
        return index;
    }
}
