/*
  Name:  Joseph Julian
  Project:  Project 4
  Date:  09 Mar 2021
  Description:
 */

public interface DFSActions<V> {
    void processVertex(V vertex);

    void descendVertex(V vertex);

    void ascendVertex(V vertex);

    void cycleDetected();
}