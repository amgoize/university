package amgoize.university.road;

import java.util.List;

public class RunDinicAlgorithm {
    private Graph graph;

    public RunDinicAlgorithm(int numVertices, List<Edge> edges) {
        this.graph = new Graph(numVertices);

        for (Edge edge : edges) {
            int from = edge.getFrom();
            int to = edge.getTo();
            int capacity = edge.getCapacity();
            this.graph.addEdge(from, to, capacity);
        }
    }

    public int calculateMaxFlow(int source, int sink) {
        DinicAlgorithm dinic = new DinicAlgorithm(graph);
        return dinic.maxFlow(source, sink);
    }
}
