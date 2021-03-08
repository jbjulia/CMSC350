/*
  Name:  Joseph Julian
  Project:  Project 1
  Date:  26 Jan 2021
  Description:  Main file that constructs and displays GUI.
 */

package cmsc350.project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

    private String expression, result;

    private GUI() {
        super("Week 2 - Project 1");
        // Main window
        JPanel main = new JPanel();

        // Set layout
        main.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Create components
        JLabel lblExpression = new JLabel("Enter Expression:");
        JLabel lblResult = new JLabel("Result:");
        JTextField txtExpression = new JTextField("");
        JTextField txtResult = new JTextField("");
        JRadioButton radPrefixToPostfix = new JRadioButton("Prefix to Postfix");
        JRadioButton radPostfixToPrefix = new JRadioButton("Postfix to Prefix");
        JButton btnConvert = new JButton("Convert");
        JButton btnReset = new JButton("Reset");

        // Set action commands
        radPrefixToPostfix.setActionCommand("Prefix to Postfix");
        radPostfixToPrefix.setActionCommand("Postfix to Prefix");

        // Configure components
        c.fill = GridBagConstraints.HORIZONTAL;

        // lblExpression
        c.insets = new Insets(0, 0, 10, 0);
        c.gridx = 0;
        c.gridy = 0;
        main.add(lblExpression, c);

        // txtExpression
        c.insets = new Insets(0, 0, 10, 0);
        c.gridx = 1;
        c.gridy = 0;
        main.add(txtExpression, c);

        // lblResult
        c.insets = new Insets(0, 0, 10, 0);
        c.gridx = 0;
        c.gridy = 2;
        main.add(lblResult, c);

        // txtResult
        c.insets = new Insets(0, 0, 10, 0);
        c.gridx = 1;
        c.gridy = 2;
        main.add(txtResult, c);

        // radPrefixToPostfix
        c.insets = new Insets(0, 0, 5, 5);
        c.gridx = 0;
        c.gridy = 3;
        main.add(radPrefixToPostfix, c);

        // radPostfixToPrefix
        c.insets = new Insets(0, 5, 5, 0);
        c.gridx = 1;
        c.gridy = 3;
        main.add(radPostfixToPrefix, c);

        // btnConvert
        c.insets = new Insets(5, 0, 0, 5);
        c.gridx = 0;
        c.gridy = 4;
        main.add(btnConvert, c);

        // btnReset
        c.insets = new Insets(5, 5, 0, 0);
        c.gridx = 1;
        c.gridy = 4;
        main.add(btnReset, c);

        add(main);

        // Create ButtonGroup for JRadioButtons
        ButtonGroup group = new ButtonGroup();
        group.add(radPrefixToPostfix);
        group.add(radPostfixToPrefix);

        // Mute txtResult
        txtResult.setEditable(false);

        // Set default JRadioButton
        radPrefixToPostfix.doClick();

        // Configure window
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // btnConvert_Click
        btnConvert.addActionListener((ActionEvent e) -> {
            Convert convert = new Convert();
            expression = txtExpression.getText();

            try {
                if (expression.isEmpty()) {
                    throw new NullPointerException();
                } else {
                    result = (convert.evaluate(expression, group.getSelection().getActionCommand()));
                    txtResult.setText(result);
                }
            } catch (NullPointerException nullPointer) {
                JOptionPane.showMessageDialog(main, "A valid expression is required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (InvalidCharacter invalidCharacter) {
                JOptionPane.showMessageDialog(main,
                        "Invalid Characters. Only 0-9 and +, -, *, / characters allowed.", "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // btnReset_Click
        btnReset.addActionListener((ActionEvent e) -> {
            txtExpression.setText("");
            txtResult.setText("");
            radPrefixToPostfix.doClick();
        });
    }

    // Main method
    public static void main(String[] args) {
        new GUI();
    }
}
