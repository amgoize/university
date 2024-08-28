package amgoize.university.road;

public class Edge {
    int from;
    int to;
    int capacity;
    Edge reverse;
    protected int flow; // Добавленное поле для хранения текущего потока

    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Edge getReverse() {
        return reverse;
    }

    public void setReverse(Edge reverse) {
        this.reverse = reverse;
    }
    public int getRemainingCapacity() {
        return capacity - flow; // Остаточная пропускная способность
    }
}
