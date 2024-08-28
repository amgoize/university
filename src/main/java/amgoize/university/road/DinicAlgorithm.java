package amgoize.university.road;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DinicAlgorithm {
    private final Graph graph;
    private int[] level;
    private int[] ptr;
    private int source;
    private int sink;

    public DinicAlgorithm(Graph graph) {
        this.graph = graph;
    }

    public int maxFlow(int source, int sink) {
        this.source = source;
        this.sink = sink;
        int flow = 0;
        while (true) {
            if (!bfs()) break;
            ptr = new int[graph.n+1];
            while (true) {
                int pushed = dfs(source, Integer.MAX_VALUE);
                if (pushed == 0) break;
                flow += pushed;
            }
        }
        return flow;
    }

    private boolean bfs() {
        level = new int[graph.n+1];
        Arrays.fill(level, -1);
        level[source] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Edge edge : graph.getAdjEdges(u)) {
                if (edge.getRemainingCapacity() > 0 && level[edge.to] == -1) {
                    level[edge.to] = level[u] + 1;
                    queue.add(edge.to);
                }
            }
        }
        return level[sink] != -1;
    }

    private int dfs(int u, int flow) {
        if (u == sink) return flow;
        for (; ptr[u] < graph.getAdjEdges(u).size(); ptr[u]++) {
            Edge edge = graph.getAdjEdges(u).get(ptr[u]);
            if (edge.getRemainingCapacity() > 0 && level[edge.to] == level[u] + 1) {
                int pushed = dfs(edge.to, Math.min(flow, edge.getRemainingCapacity()));
                if (pushed > 0) {
                    edge.flow += pushed;
                    edge.reverse.flow -= pushed;
                    return pushed;
                }
            }
        }
        return 0;
    }
}
