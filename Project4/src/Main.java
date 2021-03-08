/*
  Name:  Joseph Julian
  Project:  Project 4
  Date:  09 Mar 2021
  Description:  Main class that allows user to select file.
 */

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static DirectedGraph graph = new DirectedGraph();

    public static void main(String[] args) {
        new Main().readGraph();

        graph.depthFirstSearch();
        System.out.println(graph.parenthesizedList.toString());
        System.out.println(graph.hierarchy.toString());
        graph.displayUnreachableClasses();
    }

    /**
     * Get user input from JFileChooser and build a Directed Graph
     */
    public void readGraph() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int option = fileChooser.showOpenDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                Scanner input = new Scanner(fileChooser.getSelectedFile());
                while (input.hasNextLine()) {
                    String edgeString = input.nextLine();
                    String[] edge = edgeString.split(" ");
                    if (graph.startingNode == null)
                        graph.startingNode = graph.getVertex(edge[0]);
                    for (int i = 1; i < edge.length; i++) {
                        graph.addEdge(edge[0], edge[i]);
                        // System.out.println(edge[0] + "\t" + edge[i]);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}