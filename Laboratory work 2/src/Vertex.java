public class Vertex {
    private String name;
    private boolean isVisited;

    public Vertex(final String name) {
        this.name = name;
        isVisited = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean isVisited() {
        return this.isVisited;
    }

    public void setVisited(final boolean isVisited) {
        this.isVisited = isVisited;
    }
}
