import java.util.*;
import java.util.stream.Collectors;

public class GraphGenerator {

    private static final int MAX_VERTICES_NUMBER = 15;
    private static final int MAX_WEIGHT = 100;
    private static final Random random = new Random();

    private static List<Vertex> vertices;
    private static int verticesNo;

    public static void main(String[] args) {
        init();

    }

    private static void init() {
        verticesNo = random.nextInt(MAX_VERTICES_NUMBER) + 5;
        vertices = new ArrayList<>();
        for (int i = 0; i < verticesNo; i++) {
            vertices.add(new Vertex(i));
        }

        vertices = vertices.stream()
                .peek(GraphGenerator::randomConnect)
                .peek(GraphGenerator::randomWeight)
                .collect(Collectors.toList());

        testPrintVertices();
    }

    private static void randomWeight(Vertex vertex) {
        vertex.weight = random.nextInt(MAX_WEIGHT) + 1;
    }

    private static void randomConnect(Vertex vertex) {

        boolean isConnected;
        for (int i = 0; i < verticesNo; i++) {

            if (i == vertex.index) {
                continue;
            }

            isConnected = random.nextBoolean();
            if (isConnected) {
                Vertex neighbor = vertices.get(i);
                vertex.neighbors.add(neighbor);
                neighbor.neighbors.add(vertex);
            }
        }
    }

    private static class Vertex {
        int index;
        int weight;
        int color;
        Set<Vertex> neighbors = new HashSet<>();

        public Vertex(int index) {
            this.index = index;
        }
    }

    private static void testPrintVertices() {
        vertices.forEach(vertex -> {
            System.out.print(vertex.index + ": ");
            for (Vertex v : vertex.neighbors) {
                System.out.print(v.index + ", ");
            }
            System.out.println();
        });
    }


}
