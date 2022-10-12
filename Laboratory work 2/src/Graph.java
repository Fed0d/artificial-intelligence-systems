import java.util.*;

public class Graph {
    private final Vertex[] vertexList;
    private final int[][] adjMat;
    private int nVerts;
    private final Stack<Integer> stack;
    private final Queue<Integer> queue;

    public Graph() {
        int MAX_VERTS = 28;
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }
        stack = new Stack<>();
        queue = new LinkedList<>();
    }

    private int getAdjUnvisitedVertex(int vertex) {
        for (int i = 0; i < nVerts; i++) {
            if (adjMat[vertex][i] != 0 && !vertexList[i].isVisited()) {
                return i;
            }
        }
        return -1;
    }

    public int getVertexIdByName(String name) {
        for (int i = 0; i < nVerts; i++) {
            if (Objects.equals(vertexList[i].getName(), name)) {
                return i;
            }
        }
        return -1;
    }

    public void addVertex(String name) {
        vertexList[nVerts++] = new Vertex(name);
    }

    public void addEdge(int start, int end, int distance) {
        adjMat[start][end] = distance;
        adjMat[end][start] = distance;
    }

    public void displayVertex(int vertex) {
        System.out.print(vertexList[vertex].getName());
    }

    public void dfs(int start, int end) {
        vertexList[start].setVisited(true);
        stack.push(start);
        int vertex;

        while (!stack.isEmpty()) {
            vertex = getAdjUnvisitedVertex(stack.peek());
            if (vertex == -1) {
                stack.pop();
            } else {
                vertexList[vertex].setVisited(true);
                stack.push(vertex);
                if (vertex == end) {
                    break;
                }
            }
        }
        for (int id : stack) {
            displayVertex(id);
            if (id != stack.peek()) {
                System.out.print(" -> ");
            }
        }

        for (int i = 0; i < nVerts; i++) {
            vertexList[i].setVisited(false);
        }
    }

    public void bfs(int start, int end) {
        int vertex_2 = -1, vertex = -1;
        Map<Integer, Integer> parents = new HashMap();
        vertexList[start].setVisited(true);
        queue.add(start);

        while (!queue.isEmpty() && vertex_2 != end) {
            vertex = queue.remove();

            while ((vertex_2 = getAdjUnvisitedVertex(vertex)) != -1) {
                vertexList[vertex_2].setVisited(true);
                parents.put(vertex_2, vertex);
                if (vertex_2 == end) {
                    break;
                }
                queue.add(vertex_2);
            }
        }

        List<Integer> route = new ArrayList<>();
        int purpose = end;
        route.add(purpose);

        while (purpose != start) {
            route.add(parents.get(purpose));
            purpose = parents.get(purpose);
        }

        for (int i = route.size() - 1; i >= 0; i--) {
            displayVertex(route.get(i));
            if (i != 0) {
                System.out.print(" -> ");
            }
        }

        for (int i = 0; i < nVerts; i++) {
            vertexList[i].setVisited(false);
        }
    }
}
