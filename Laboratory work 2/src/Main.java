import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        String table_1 = "Вильнюс Брест 531\n" +
                "Витебск Брест 638\n" +
                "Витебск Вильнюс 360\n" +
                "Воронеж Витебск 869\n" +
                "Воронеж Волгоград 581\n" +
                "Волгоград Витебск 1455\n" +
                "Витебск Ниж.Новгород 911\n" +
                "Вильнюс Даугавпилс 211\n" +
                "Калининград Брест 699\n" +
                "Калининград Вильнюс 333\n" +
                "Каунас Вильнюс 102\n" +
                "Киев Вильнюс 734\n" +
                "Киев Житомир 131\n" +
                "Житомир Донецк 863\n" +
                "Житомир Волгоград 1493\n" +
                "Кишинев Киев 467\n" +
                "Кишинев Донецк 812\n" +
                "С.Петербург Витебск 602\n" +
                "С.Петербург Калининград 739\n" +
                "С.Петербург Рига 641\n" +
                "Москва Казань 815\n" +
                "Москва Ниж.Новгород 411\n" +
                "Москва Минск 690\n" +
                "Москва Донецк 1084\n" +
                "Москва С.Петербург 664\n" +
                "Мурманск С.Петербург 1412\n" +
                "Мурманск Минск 2238\n" +
                "Орел Витебск 522\n" +
                "Орел Донецк 709\n" +
                "Орел Москва 368\n" +
                "Одесса Киев 487\n" +
                "Рига Каунас 267\n" +
                "Таллинн Рига 308\n" +
                "Харьков Киев 471\n" +
                "Харьков Симферополь 639\n" +
                "Ярославль Воронеж 739\n" +
                "Ярославль Минск 940\n" +
                "Уфа Казань 525\n" +
                "Уфа Самара 461 ";

        String[] strs = table_1.split("\n");
        Set<String> cities = new HashSet<>();

        for (String str : strs) {
            String[] edges = str.split(" ");

            if (!cities.contains(edges[0])) {
                graph.addVertex(edges[0]);
            }

            if (!cities.contains(edges[1])) {
                graph.addVertex(edges[1]);
            }

            graph.addEdge(graph.getVertexIdByName(edges[0]), graph.getVertexIdByName(edges[1]), Integer.parseInt(edges[2]));
            cities.add(edges[0]);
            cities.add(edges[1]);
        }

        String table_2 = "Вильнюс 989\n" +
                "Брест 806\n" +
                "Витебск 969\n" +
                "Воронеж 843\n" +
                "Волгоград 1061\n" +
                "Ниж.Новгород 1426\n" +
                "Даугавпилс 1085\n" +
                "Калининград 1163\n" +
                "Каунас 1051\n" +
                "Киев 441\n" +
                "Житомир 447\n" +
                "Донецк 560\n" +
                "Кишинев 157\n" +
                "С.Петербург 1497\n" +
                "Рига 1249\n" +
                "Москва 1136\n" +
                "Казань 1640\n" +
                "Минск 849\n" +
                "Мурманск 2504\n" +
                "Орел 816\n" +
                "Таллинн 1494\n" +
                "Харьков 670\n" +
                "Симферополь 312\n" +
                "Ярославль 1386\n" +
                "Уфа 1987\n" +
                "Самара 1571";

        strs = table_2.split("\n");

        for (String str : strs) {
            String[] distance = str.split(" ");
            graph.vertexList[graph.getVertexIdByName(distance[0])].setDistance(Integer.parseInt(distance[1]));
        }

        graph.bfs(graph.getVertexIdByName("Рига"), graph.getVertexIdByName("Одесса"));
        System.out.println();
        graph.dfs(graph.getVertexIdByName("Рига"), graph.getVertexIdByName("Одесса"));
        System.out.println();

        if (!graph.dfs(graph.getVertexIdByName("Рига"), graph.getVertexIdByName("Одесса"), 9)) {
            System.out.print("Невозможно найти маршрут. ");
        }

        System.out.println();
        graph.iterative_dfs(graph.getVertexIdByName("Рига"), graph.getVertexIdByName("Одесса"));
        System.out.println();
        graph.bidirectional_search(graph.getVertexIdByName("Рига"), graph.getVertexIdByName("Одесса"));
        System.out.println();
        graph.greedy_search(graph.getVertexIdByName("Рига"), graph.getVertexIdByName("Одесса"));
        System.out.println();
        graph.a_asterisk(graph.getVertexIdByName("Рига"), graph.getVertexIdByName("Одесса"));
    }
}
