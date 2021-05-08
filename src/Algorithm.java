import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {

    private static List<Vertex> vertices;

    static Comparator<Vertex> byWeightDesc = Comparator
            .comparing(Vertex::getWeight)
            .reversed();

    public static void main(String[] args) {
        init();
        List<Vertex> remainingVertices = vertices.stream()
                .sorted(byWeightDesc)
                .collect(Collectors.toList());

        int currentColor = 0;

        do {
            Set<Vertex> colored = new HashSet<>();
            Set<Vertex> uncolorable = new HashSet<>();

            Vertex currentHeaviest = remainingVertices.get(0);

            currentHeaviest.color = currentColor;
            colored.add(currentHeaviest);
            uncolorable.add(currentHeaviest);
            uncolorable.addAll(currentHeaviest.neighbors);

            for (int i = 1; i < remainingVertices.size(); i++) {
                Vertex currentVertex = remainingVertices.get(i);
                if (!uncolorable.contains(currentVertex)) {
                    currentVertex.color = currentColor;
                    colored.add(currentVertex);
                    uncolorable.add(currentVertex);
                    uncolorable.addAll(currentVertex.neighbors);
                }
            }

            remainingVertices.removeAll(colored);
            currentColor++;
        } while (!remainingVertices.isEmpty());

    }

    private static void init() {
        vertices = new ArrayList<>();

        Vertex v0 = new Vertex(0, 3);
        Vertex v1 = new Vertex(1, 5);
        Vertex v2 = new Vertex(2, 2);
        Vertex v3 = new Vertex(3, 3);
        Vertex v4 = new Vertex(4, 7);
        Vertex v5 = new Vertex(5, 1);
        Vertex v6 = new Vertex(6, 3);
        Vertex v7 = new Vertex(7, 4);

        v0.neighbors.addAll(Arrays.asList(v1, v2, v3, v4));
        v1.neighbors.addAll(Arrays.asList(v0, v2, v3));
        v2.neighbors.addAll(Arrays.asList(v0, v1, v4));
        v3.neighbors.addAll(Arrays.asList(v0, v1, v5));
        v4.neighbors.addAll(Arrays.asList(v0, v2, v5));
        v5.neighbors.addAll(Arrays.asList(v3, v4, v6, v7));
        v6.neighbors.addAll(Arrays.asList(v5));
        v7.neighbors.addAll(Arrays.asList(v5));

        vertices.add(v0);
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        vertices.add(v6);
        vertices.add(v7);
    }
}
