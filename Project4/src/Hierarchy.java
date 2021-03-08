/*
  Name:  Joseph Julian
  Project:  Project 4
  Date:  09 Mar 2021
  Description:
 */

import java.util.LinkedList;
import java.util.Queue;

public class Hierarchy implements DFSActions<Vertex> {

    Queue<String> res = new LinkedList<>();

    @Override
    public void processVertex(Vertex vertex) {
        res.add(vertex.toString());
    }

    @Override
    public void descendVertex(Vertex vertex) {
        res.add("(");
    }

    @Override
    public void ascendVertex(Vertex vertex) {
        res.add(")");

    }

    @Override
    public void cycleDetected() {
        res.add("*");
    }

    @Override
    public String toString() {

        StringBuilder ans = new StringBuilder();
        int sz = 0;

        while (res.size() > 0) {
            String c = res.peek();
            res.remove();

            if (c.equals("(")) {
                assert res.peek() != null;
                if (res.peek().equals(")")) {
                    res.remove();
                    continue;
                } else if (res.peek().equals("*")) {
                    ans.append(res.peek()).append(" ");
                    res.remove();
                    res.remove();
                    continue;
                }
            }

            if (c.equals("("))
                sz++;
            else if (c.equals(")"))
                --sz;

            if (c.equals("(") || c.equals(")"))
                continue;

            if (!c.equals("*"))
                ans.append("\n");

            ans.append("\t".repeat(Math.max(0, sz)));


            ans.append(c).append(" ");

        }
        ans.append("\n");

        return ans.toString();
    }
}