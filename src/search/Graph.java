package search;

public class Graph {
    private int[][] adjMatrix;
    
    private Graph() {
        // Hard code stuff makes awesome an developer
        adjMatrix = new int [20][20];
        addEdge(Cities.Oradea, Cities.Zerind, 71);
        addEdge(Cities.Oradea, Cities.Sibiu, 151);
        addEdge(Cities.Zerind, Cities.Arad, 75);
        addEdge(Cities.Arad, Cities.Sibiu, 140);
        addEdge(Cities.Arad, Cities.Timisoara, 118);
        addEdge(Cities.Timisoara, Cities.Lugoj, 111);
        addEdge(Cities.Lugoj, Cities.Mehadia, 70);
        addEdge(Cities.Mehadia, Cities.Drobeta, 75);
        addEdge(Cities.Drobeta, Cities.Craiova, 120);
        addEdge(Cities.Craiova, Cities.RimnicuVilcea, 146);
        addEdge(Cities.Sibiu, Cities.RimnicuVilcea, 80);
        addEdge(Cities.Sibiu, Cities.Fagaras, 99);
        addEdge(Cities.RimnicuVilcea, Cities.Pitesti, 97);
        addEdge(Cities.Craiova, Cities.Pitesti, 138);
        addEdge(Cities.Fagaras, Cities.Bucharest, 211);
        addEdge(Cities.Pitesti, Cities.Bucharest, 101);
        addEdge(Cities.Giurgiu, Cities.Bucharest, 90);
        addEdge(Cities.Bucharest, Cities.Urziceni, 85);
        addEdge(Cities.Urziceni, Cities.Hirsova, 98);
        addEdge(Cities.Hirsova, Cities.Eforie, 86);
        addEdge(Cities.Urziceni, Cities.Vaslui, 142);
        addEdge(Cities.Vaslui, Cities.Iasi, 92);
        addEdge(Cities.Iasi, Cities.Neamt, 87);
    }

    /**
     * @param value of cityA
     * @param value of cityB
     * @param weight
     */
    private void addEdge(Cities cityA, Cities cityB, int weight) {
        adjMatrix[cityA.getValue()][cityB.getValue()] = weight;
        adjMatrix[cityB.getValue()][cityA.getValue()] = weight;
    }

    public static Graph getInstance() {
        return Singleton.INSTANCE;
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    private static class Singleton {
        private static final Graph INSTANCE = new Graph();
    }
}