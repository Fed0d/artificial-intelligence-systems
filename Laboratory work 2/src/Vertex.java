public class Vertex {
    private String name;
    private boolean isVisited;
    private int distance;

    public Vertex(final String name) {
        this.name = name;
        isVisited = false;
    }

    public String getName() {
        return this.name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isVisited() {
        return this.isVisited;
    }

    public void setVisited(final boolean isVisited) {
        this.isVisited = isVisited;
    }
}
