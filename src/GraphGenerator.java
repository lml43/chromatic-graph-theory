import java.util.Random;

public class GraphGenerator {

    // Set a maximum limit to the vertices
    private static final int MAX_VERTICES_NUMBER = 15;
    private static Random random = new Random();

    static int verticesNo;
    static int[][] adjacencyMat;

    public static void main(String[] args) {
        verticesNo = random.nextInt(MAX_VERTICES_NUMBER) + 5;

        adjacencyMat = new int[verticesNo][verticesNo];

        for (int i = 0; i < verticesNo - 1; i++) {
            for (int j = i + 1; j < verticesNo; j++) {
                adjacencyMat[i][j] = random.nextInt(2);
            }
        }

        printMat(adjacencyMat);
    }

    private static void printMat(int[][] mat) {

        System.out.print(String.format("%6s", "|"));
        for (int i = 0; i < mat[0].length; i++) {
            System.out.print(String.format("%6s", (i+1)));
        }
        System.out.println();
        for (int i = 0; i <= mat[0].length; i++) {
            System.out.print("______");
        }
        for (int i = 0; i < mat.length; i++) {
            System.out.println();
            System.out.print(String.format("%6s", (i+1)+" |"));
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(String.format("%6s", mat[i][j]));
            }
        }
        System.out.println();
    }

}
