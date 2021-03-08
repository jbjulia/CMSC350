/*
  Name:  Joseph Julian
  Project:  Project 4
  Date:  09 Mar 2021
  Description:
 */

import java.util.*;

public class Graph<V> {

    public V startingNode = null;

    Map<String, V> vertices = new HashMap<>();

    Map<V, ArrayList<V>> adjacencyList = new HashMap<>();

    Set<V> visited = new HashSet<>();

    ParenthesizedList hierarchy = new ParenthesizedList();
    Hierarchy parenthesizedList = new Hierarchy();

    boolean cycle;

    Set<V> discovered = new HashSet<>();

    /**
     * Initialize the DFS with all other related attributes
     */
    public void depthFirstSearch() {
        cycle = false;
        dfs(startingNode);
    }

    /**
     * Search in the adjacency list in Depth-First-Order
     *
     * @param node node to discover with all of it's children
     */
    private void dfs(V node) {
        if (discovered.contains(node)) {
            cycle = true;
            hierarchy.cycleDetected();
            parenthesizedList.cycleDetected();
            return;
        }

        hierarchy.processVertex((Vertex) node);
        parenthesizedList.processVertex((Vertex) node);

        hierarchy.descendVertex((Vertex) node);
        parenthesizedList.descendVertex((Vertex) node);

        discovered.add(node);

        visited.add(node);

        ArrayList<V> list = adjacencyList.get(node);

        if (list != null) {
            for (V u : list)
                dfs(u);
        }

        hierarchy.ascendVertex((Vertex) node);
        parenthesizedList.ascendVertex((Vertex) node);

        discovered.remove(node);
    }

    /**
     * Prints all the unvisited nodes/classes
     */
    public void displayUnreachableClasses() {
        for (Map.Entry<V, ArrayList<V>> entry : adjacencyList.entrySet()) {
            if (entry.getValue().size() > 0) {
                if (!visited.contains(entry.getKey())) {
                    System.out.println(entry.getKey() + " is unreachable");
                    visited.add(entry.getKey());
                }
                for (V vertex : entry.getValue()) {
                    if (!visited.contains(vertex)) {
                        System.out.println(vertex + " is unreachable");
                        visited.add(vertex);
                    }
                }
            }
        }
    }
}