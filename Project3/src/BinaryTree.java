/*
  Name:  Joseph Julian
  Project:  Project 3
  Date:  23 Feb 2021
  Description:  Performs operations based on user input from GUI class.
 */

import java.util.EmptyStackException;
import java.util.Stack;

public class BinaryTree {

    Node parent, child;

    /**
     * Creates array from String based on parenthesis and adds children
     *
     * @param input User input
     * @throws InvalidTreeSyntax Invalid binary tree syntax
     */
    public BinaryTree(String input) throws InvalidTreeSyntax {
        Stack<Node> nodeStack = new Stack<>();
        String[] inputArray = input.substring(1, input.length() - 1).split("(?<=\\()|(?=\\()|(?<=\\))|(?=\\))");
        parent = new Node(inputArray[0]);

        for (int i = 1; i < inputArray.length - 1; i++) {
            if (inputArray[i].equals("(")) {
                nodeStack.push(parent);
                if (child != null) {
                    parent = child;
                }
            } else if (inputArray[i].equals(")")) {
                try {
                    child = parent;
                    parent = nodeStack.pop();
                } catch (EmptyStackException emptyStack) {
                    throw new InvalidTreeSyntax("Error: Incorrect parenthesis.");
                }
            } else {
                child = new Node(inputArray[i]);
                if (parent != null) {
                    parent.addChild(child);
                }
            }
        }
        if (this.recNodes(parent) * 3 != input.length()) throw new InvalidTreeSyntax("Error: Invalid syntax.");
    }

    /**
     * Determine absolute difference between branches
     *
     * @return boolean true if absolute difference is not greater than 1
     */
    public boolean isBalanced() {
        return recIsBalanced(this.parent);
    }

    private boolean recIsBalanced(Node root) {
        if (root == null) {
            return true;
        }
        return (Math.abs(recHeight(root.left) - recHeight(root.right)) <= 1) &&
                (recIsBalanced(root.left) && recIsBalanced(root.right));
    }

    /**
     * Determines if tree has maximum nodes for the height
     *
     * @return boolean true / false
     */
    public boolean isFull() {
        return recIsFull(this.parent, recHeight(this.parent), 0);
    }

    private boolean recIsFull(Node root, int height, int index) {
        if (root == null) {
            return true;
        }

        if (root.left == null && root.right == null) {
            return (height == index + 1);
        }

        if (root.left == null || root.right == null) {
            return false;
        }

        return recIsFull(root.left, height, index + 1) && recIsFull(root.right, height, index + 1);

    }

    /**
     * Determine if all nodes in tree have either 2 or 0 children
     *
     * @return boolean true if 2 children, false if 0
     */
    public boolean isProper() {
        return recIsProper(this.parent);
    }

    private boolean recIsProper(Node root) {
        if (root == null) {
            return true;
        }
        return ((root.left != null || root.right == null) && (root.left == null || root.right != null))
                && (recIsProper(root.left) && recIsProper(root.right));
    }

    /**
     * Determine the height of the binary tree where root node is 0
     *
     * @return int height + 1 : 0 if null
     */
    public int height() {
        return recHeight(this.parent) - 1;
    }

    private int recHeight(Node root) {
        return (root == null) ? 0 : 1 + Math.max(recHeight(root.left), recHeight(root.right));
    }

    /**
     * Determines number of nodes in the binary tree
     *
     * @return int number of nodes + 1 : 0 if null
     */
    public int nodes() {
        return recNodes(this.parent);
    }

    private int recNodes(Node root) {
        return (root == null) ? 0 : 1 + recNodes(root.left) + recNodes(root.right);
    }

    /**
     * Prints the ordered node info of the binary tree
     *
     * @return String ordered node info : "" if null
     */
    public String inOrder() {
        return recInOrder(this.parent);
    }

    private String recInOrder(Node root) {
        return (root == null) ? "" : "(" + recInOrder(root.left) + root.info + recInOrder(root.right) + ")";
    }

    @Override
    public String toString() {
        return parent.toString();
    }

    /**
     * Helper methods for nodes
     */
    public static class Node {
        private final String info;
        private Node left;
        private Node right;

        public Node(String info) {
            this.info = info;
        }

        private static String toString(Node root) {
            return (root == null) ? "" : "(" + root.info + toString(root.left) + toString(root.right) + ")";
        }

        private void addChild(Node child) throws InvalidTreeSyntax {
            if (this.left == null) {
                this.setLeft(child);
            } else if (this.right == null) {
                this.setRight(child);
            } else {
                throw new InvalidTreeSyntax("Error: Nodes may only have 2 children.");
            }
        }

        private void setLeft(Node newLeft) {
            left = newLeft;
        }

        private void setRight(Node newRight) {
            right = newRight;
        }

        @Override
        public String toString() {
            return toString(this);
        }
    }
}