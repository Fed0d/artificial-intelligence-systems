import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        String table = "Вильнюс Брест 531\n" +
                "Витебск Брест 638\n" +
                "Витебск Вильнюс 360\n" +
                "Воронеж Витебск 869\n" +
                "Воронеж Волгоград 581\n" +
                "Волгоград Витебск 1455\n" +
                "Витебск Ниж.Новгород 911\n" +
                "Вильнюс Даугавпилс 211\n" +
                "Калининград Брест 699\n" +
                "Калиниград Вильнюс 333\n" +
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
        String[] strs = table.split("\n");
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
        graph.bfs(graph.getVertexIdByName("Рига"), graph.getVertexIdByName("Одесса"));
        System.out.println();
        graph.dfs(graph.getVertexIdByName("Рига"), graph.getVertexIdByName("Одесса"));
        System.out.println();
        graph.dfs(graph.getVertexIdByName("Рига"), graph.getVertexIdByName("Одесса"), 8);
    }
}
