/*
  Name:  Joseph Julian
  Project:  Project 4
  Date:  09 Mar 2021
  Description:
 */

import java.util.ArrayList;

public class DirectedGraph extends Graph<Vertex> {
    /**
     * Creates a directed edge and adds it to graph
     *
     * @param u source node
     * @param v destination node
     */
    public void addEdge(String u, String v) {
        ArrayList<Vertex> list = adjacencyList.get(getVertex(u));

        if (list == null) {
            list = new ArrayList<>();
        }

        list.add(getVertex(v));
        adjacencyList.put(getVertex(u), list);
    }

    /**
     * Checks if node is already mapped
     *
     * @param u node to be mapped
     * @return mapped vertex of node
     */
    public Vertex getVertex(String u) {
        if (!vertices.containsKey(u)) {
            vertices.put(u, new Vertex(u));
        }

        return vertices.get(u);
    }
}