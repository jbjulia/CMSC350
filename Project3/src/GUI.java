/*
  Name:  Joseph Julian
  Project:  Project 3
  Date:  23 Feb 2021
  Description:  Main class that constructs and displays GUI.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static BinaryTree inputBinaryTree;
    private final JTextField txtInput = new JTextField(20);
    private final JTextField txtOutput = new JTextField(20);

    // GUI Components
    JComponent[] lblInput = {new JLabel("Enter Tree: "), txtInput};
    JComponent[] lblOutput = {new JLabel("Output: "), txtOutput};
    JButton[] buttons = {
            new JButton("Make Tree [ ! ]"),
            new JButton("Is Balanced [ ? ]"),
            new JButton("Is Full [ ? ]"),
            new JButton("Is Proper [ ? ]"),
            new JButton("Height [ ... ]"),
            new JButton("Nodes [ ... ]"),
            new JButton("Inorder [ ... ]"),
            new JButton("Reset [ * ]")
    };

    /**
     * Set txtOutput based on returned String
     */
    private final ActionListener treeListener = event -> {
        try {
            switch ((event.getActionCommand())) {
                case "Make Tree [ ! ]" -> {
                    inputBinaryTree = new BinaryTree(txtInput.getText());
                    txtOutput.setText(inputBinaryTree.toString());
                    toggleButtons(buttons, true);
                }
                case "Is Balanced [ ? ]" -> txtOutput.setText(String.valueOf(inputBinaryTree.isBalanced()));
                case "Is Full [ ? ]" -> txtOutput.setText(String.valueOf(inputBinaryTree.isFull()));
                case "Is Proper [ ? ]" -> txtOutput.setText(String.valueOf(inputBinaryTree.isProper()));
                case "Height [ ... ]" -> txtOutput.setText(String.valueOf(inputBinaryTree.height()));
                case "Nodes [ ... ]" -> txtOutput.setText(String.valueOf(inputBinaryTree.nodes()));
                case "Inorder [ ... ]" -> txtOutput.setText(inputBinaryTree.inOrder());
                case "Reset [ * ]" -> {
                    dispose();
                    new GUI();
                }
            }
        } catch (InvalidTreeSyntax e) {
            JOptionPane.showMessageDialog(getParent(), e.getMessage());
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            JOptionPane.showMessageDialog(getParent(), "Error: Please enter a valid binary tree.");
        }
    };

    /**
     * Creates and configures a GUI for the user to input a binary tree
     */
    public GUI() {
        super("Project 3 - Binary Tree Categorizor");

        makeFlowPanel(lblInput);
        makeFlowPanel(lblOutput);
        makeFlowPanel(buttons);
        addActionListeners(buttons);
        toggleButtons(buttons, false);
        setLayout(new GridLayout(3, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        txtOutput.setEditable(false);
    }

    /**
     * Main method
     *
     * @param args -
     */
    public static void main(String[] args) {
        new GUI();
    }

    /**
     * Creates a flow panel from array of components
     *
     * @param components JComponent[]
     */
    private void makeFlowPanel(JComponent[] components) {
        JPanel panel = new JPanel(new FlowLayout());
        for (Component component : components) {
            panel.add(component);
        }
        add(panel);
    }

    /**
     * Add ActionListener to buttons that are passed to it
     *
     * @param buttons JButton[]
     */
    private void addActionListeners(JButton[] buttons) {
        for (JButton button : buttons) {
            button.addActionListener(treeListener);
        }
    }

    /**
     * Toggle button ability according to event
     *
     * @param buttons JButtons[]
     * @param enable  enable all buttons if true, disable if else
     */
    private void toggleButtons(JButton[] buttons, boolean enable) {
        for (JButton button : buttons) {
            if (enable) {
                button.setEnabled(true);
            } else {
                if (!(button.getActionCommand().equals("Make Tree [ ! ]"))
                        && !(button.getActionCommand().equals("Reset [ * ]"))) {
                    button.setEnabled(false);
                }
            }
        }
    }
}