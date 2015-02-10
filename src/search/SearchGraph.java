package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class SearchGraph {
    private int[][] matrix;

    public SearchGraph() {
        matrix = Graph.getInstance().getAdjMatrix();
    }

    public void run() {
        ArrayList<Integer> start = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> visitorQueue = new ArrayList<ArrayList<Integer>>();

        //If the provided source city is not in the graph, default to Oradea
        Cities sourceCity = Config.INSTANCE.getSourceCity()
                                .or(Cities.Oradea);
        
        //If the destination city is not in the graph, default to dummy value
        Cities destinationCity = Config.INSTANCE.getDestinationCity()
                                .or(Cities.Eforie);
        
        //If the algorithm is other than BFS, DFS, UCS default to BFS
        Algorithms algorithm = Config.INSTANCE.getAlgorithm().or(Algorithms.BFS);

        start.add(sourceCity.getValue());
        visitorQueue.add(start);

        ArrayList<Integer> path = null;

        switch (algorithm) {
            case BFS:
                path = breadFirstSearch(visitorQueue, destinationCity.getValue());
                break;
            case DFS:
                path = depthFirstSearch(visitorQueue, destinationCity.getValue());
                break;
            case UCS:
                path = uniformCostSearch(visitorQueue, destinationCity.getValue());
                break;
            default:
                System.out.println("Can not reconginze the algorithm soecified.");
                break;
        }
        printPath(path);
    }

    private ArrayList<Integer> breadFirstSearch(
            ArrayList<ArrayList<Integer>> visitorQueue, int destination) {
        if (visitorQueue.size() == 0) {
            //We searched the all the graph
            return null;
        } else if (visitorQueue.get(0).get(0) == destination) {
            //We reached the destination
            return visitorQueue.get(0);
        } else {
            ArrayList<ArrayList<Integer>> expandedVisitorQueue = expandNode(visitorQueue.get(0));
            visitorQueue.remove(0);
            return breadFirstSearch(Lists.
                    newArrayList(Iterables.concat(visitorQueue, expandedVisitorQueue)), destination);
        }
    }

    private ArrayList<Integer> depthFirstSearch(
            ArrayList<ArrayList<Integer>> visitorQueue, int destination) {
        if (visitorQueue.size() == 0) {
            return null;
        } else if (visitorQueue.get(0).get(0) == destination) {
            return visitorQueue.get(0);
        } else {
            ArrayList<ArrayList<Integer>> expandedVisitorQueue = expandNode(visitorQueue.get(0));
            visitorQueue.remove(0);
            return depthFirstSearch(Lists.
                    newArrayList(Iterables.concat(expandedVisitorQueue, visitorQueue)), destination);
        }
    }

    private ArrayList<Integer> uniformCostSearch(
            ArrayList<ArrayList<Integer>> visitorQueue, int destination) {
        if (visitorQueue.size() == 0) {
            return null;
        } else if (visitorQueue.get(0).get(0) == destination)
            return visitorQueue.get(0);
        else {
            ArrayList<ArrayList<Integer>> expandedVisitorQueue = expandNode(visitorQueue.get(0));
            visitorQueue.remove(0);
            ArrayList<ArrayList<Integer>> newVisitorQueue = 
                    Lists.newArrayList(Iterables.concat(visitorQueue, expandedVisitorQueue));
            sort(newVisitorQueue);
            return uniformCostSearch(newVisitorQueue, destination);
        }
    }
    
    private void printPath(ArrayList<Integer> path) {
        Iterable<String> intToName = Iterables.transform(path,
                new Function<Integer, String>() {

                    @Override
                    public String apply(Integer arg0) {
                        return Cities.idToCityName.get(arg0);
                    }
                });
        System.out.println(Joiner
                .on(" -> ")
                .join(
                        Lists.reverse(Lists.newArrayList(intToName)
                )));
    }

    private int pathCost(ArrayList<Integer> path) {
        int cost = 0;
        for (int i = 0; i < path.size() - 1; i++)
            cost = cost + matrix[path.get(i + 1)][path.get(i)];
        return cost;
    }

    private ArrayList<ArrayList<Integer>> expandNode(ArrayList<Integer> path) {
        ArrayList<ArrayList<Integer>> newVisitorQueue = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < matrix.length; i++)
            if (matrix[path.get(0)][i] > 0 && !path.contains(i)) {
                @SuppressWarnings("unchecked")
                ArrayList<Integer> temp = (ArrayList<Integer>) path.clone();
                temp.add(0, i);
                newVisitorQueue.add(temp);
            }
        return newVisitorQueue;
    }

    private void sort(ArrayList<ArrayList<Integer>> visitorQueue) {
        Collections.sort(visitorQueue, new Comparator<ArrayList<Integer>>() {

            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return Integer.compare(pathCost(o1), pathCost(o2));
            }
        });
    }

    public static void main(String[] args) {
        SearchGraph sg = new SearchGraph();
        sg.run();
    }
}