import java.util.*;

public class Graph {
    public Vertex[] vertexList;
    private final int[][] adjMat;
    private int nVerts;

    public Graph() {
        int MAX_VERTS = 27;
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }
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
        Stack<Integer> stack = new Stack<>();
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
        display_dfs(stack);
    }

    public void bfs(int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
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

    public Boolean dfs(int start, int end, int depth) {
        Stack<Integer> stack = new Stack<>();
        vertexList[start].setVisited(true);
        stack.push(start);
        int vertex;

        while (!stack.isEmpty()) {
            vertex = getAdjUnvisitedVertex(stack.peek());

            if (vertex == -1 || stack.size() - 1 == depth) {
                stack.pop();
            } else {
                vertexList[vertex].setVisited(true);
                stack.push(vertex);
                if (vertex == end) {
                    break;
                }
            }
        }

        display_dfs(stack);

        return stack.size() != 0;
    }

    public void iterative_dfs(int start, int end) {
        for (int i = 1; i < nVerts; i++) {
            if (dfs(start, end, i)) {
                break;
            }
        }
    }

    private void display_dfs(Stack<Integer> stack) {
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

    public void bidirectional_search(int start, int end) {
        int vertex_2_start = -1, vertex_start = -1, vertex_2_end = -1, vertex_end = -1, intersection = -1;
        Queue<Integer> queue_start = new LinkedList<>();
        Queue<Integer> queue_end = new LinkedList<>();
        Map<Integer, Integer> parents_start = new HashMap();
        Map<Integer, Integer> parents_end = new HashMap();
        Set<Integer> cities_start = new HashSet<>();
        Set<Integer> cities_end = new HashSet<>();
        vertexList[start].setVisited(true);
        vertexList[end].setVisited(true);
        queue_start.add(start);
        queue_end.add(end);
        cities_start.add(start);
        cities_end.add(end);

        while (!queue_start.isEmpty() && !queue_end.isEmpty()) {
            if (cities_start.contains(vertex_2_end) || cities_end.contains(vertex_2_start)) {
                break;
            }

            vertex_start = queue_start.remove();
            vertex_end = queue_end.remove();

            while ((vertex_2_start = getAdjUnvisitedVertex(vertex_start)) != -1 &&
                    (vertex_2_end = getAdjUnvisitedVertex(vertex_end)) != -1) {
                vertexList[vertex_2_start].setVisited(true);
                parents_start.put(vertex_2_start, vertex_start);
                cities_start.add(vertex_2_start);
                queue_start.add(vertex_2_start);
                vertexList[vertex_2_end].setVisited(true);
                parents_end.put(vertex_2_end, vertex_end);
                cities_end.add(vertex_2_end);
                queue_end.add(vertex_2_end);

                if (cities_start.contains(vertex_2_end)) {
                    intersection = vertex_2_end;
                    break;
                }

                if (cities_end.contains(vertex_2_start)) {
                    intersection = vertex_2_start;
                    break;
                }
            }
        }

        List<Integer> route = new ArrayList<>();
        int purpose = intersection;
        route.add(purpose);

        while (purpose != start) {
            route.add(parents_start.get(purpose));
            purpose = parents_start.get(purpose);
        }

        for (int i = route.size() - 1; i >= 0; i--) {
            displayVertex(route.get(i));

            if (i != 0) {
                System.out.print(" -> ");
            }
        }

        int count = route.size();
        purpose = parents_end.get(intersection);
        route.add(purpose);

        while (purpose != end) {
            route.add(parents_end.get(purpose));
            purpose = parents_end.get(purpose);
        }

        System.out.print(" -> ");

        for (int i = count; i < route.size(); i++) {
            displayVertex(route.get(i));

            if (i != route.size() - 1) {
                System.out.print(" -> ");
            }
        }

        for (int i = 0; i < nVerts; i++) {
            vertexList[i].setVisited(false);
        }
    }

    public void greedy_search(int start, int end) {
        int distance = 0;
        Stack<Integer> stack = new Stack<>();
        vertexList[start].setVisited(true);
        stack.push(start);

        while (!stack.isEmpty()) {
            int min = 10000;
            int vertex = -1;

            for (int i = 0; i < nVerts; i++) {
                if(adjMat[stack.peek()][i] > 0) {
                    if(vertexList[i].getDistance() < min) {
                        min = vertexList[i].getDistance();
                        vertex = i;
                    }
                }
            }

            if (vertex != -1) {
                stack.push(vertex);
                vertexList[vertex].setVisited(true);
                distance += vertexList[vertex].getDistance();
            } else {
                distance -= vertexList[stack.peek()].getDistance();
                stack.pop();
            }

            if(vertex == end) {
                break;
            }
        }

        display_dfs(stack);
        System.out.print(" | Расстояние по трассе: " + distance + " км");

        for (int i = 0; i < nVerts; i++) {
            vertexList[i].setVisited(false);
        }
    }
}
