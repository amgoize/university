package amgoize.university.road;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    int n;
    List<Edge>[] adjList;

    public Graph(int n) {
        this.n = n;
        adjList = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to, int capacity) {
        Edge e1 = new Edge(from, to, capacity);
        Edge e2 = new Edge(to, from, 0);
        e1.reverse = e2;
        e2.reverse = e1;
        adjList[from].add(e1);
        adjList[to].add(e2);
    }

    public List<Edge> getAdjEdges(int u) {
        return adjList[u];
    }
}
