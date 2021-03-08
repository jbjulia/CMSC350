/*
  Name:  Joseph Julian
  Project:  Project 1
  Date:  26 Jan 2021
  Description:  Class that converts expressions from prefix <--> postfix.
 */

package projects.cmsc350;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;


class Convert {

    private final Stack<String> tokenStack = new Stack<>();
    private final Stack<String> operands = new Stack<>();

    /**
     * Attempt to evaluate and convert an expression from prefix <--> postfix.
     *
     * @param expression User's input expression
     * @param selection  User's conversion type selection
     * @return prefixExpression, postfixExpression
     * @throws InvalidCharacter    When expression does not contain 0-9 or +, -, *, /
     * @throws EmptyStackException When stack is empty
     */
    public String evaluate(String expression, String selection)
            throws InvalidCharacter, EmptyStackException {

        // Patterns for acceptable operators and operands
        Pattern validOperators = Pattern.compile("[*/+\\-]");
        Pattern validOperands = Pattern.compile("[\\d.?]+");

        // List for created tokens
        List<String> tokens = tokenize(expression);

        // Check for conversion selection
        if (selection.equals("Prefix to Postfix")) {
            // Push tokens to stack
            for (String token : tokens) {
                tokenStack.push(token);
            }
            while (!tokenStack.isEmpty()) {
                String nextChar = tokenStack.pop();
                String op1, op2;
                // Check for invalid characters
                if (!nextChar.matches(String.valueOf(validOperands))
                        && !nextChar.matches(String.valueOf(validOperators))) {
                    throw new InvalidCharacter();
                } else if (nextChar.matches(String.valueOf(validOperands))) {
                    operands.push(nextChar);
                } else {
                    if (!operands.isEmpty()) {
                        op2 = operands.pop();
                        op1 = operands.pop();
                        operands.push(String.format("%s %s %s", op2, op1, nextChar));
                    }
                }
            }

            // Construct new StringBuilder
            StringBuilder postfixExpression = new StringBuilder();

            // Build new expression
            for (String operand : operands) {
                postfixExpression.insert(0, operand + " ");
            }
            // Return new postfix expression
            return postfixExpression.toString();
        } else if (selection.equals("Postfix to Prefix")) {
            // Construct new StringBuilder
            StringBuilder prefixExpression = new StringBuilder();
            int i = 0;
            while (!tokens.isEmpty() && i < tokens.size()) {
                String nextChar = tokens.get(i);
                String op1, op2;

                //if (nextChar.equals(" ")) {
                //continue;
                // Check for invalid characters
                if (!nextChar.matches(String.valueOf(validOperands))
                        && !nextChar.matches(String.valueOf(validOperators))) {
                    throw new InvalidCharacter();
                } else if (nextChar.matches(String.valueOf(validOperands))) {
                    operands.push(nextChar);
                } else if (nextChar.matches(String.valueOf(validOperators))) {
                    op2 = operands.pop();
                    op1 = operands.pop();
                    operands.push(String.format("%s %s %s", nextChar, op1, op2));
                }
                i += 1;
            }
            // Build new expression
            for (String operand : operands) {
                prefixExpression.insert(0, operand + " ");
            }
            // Return new prefix expression
            return prefixExpression.toString();
        }
        return null;
    }

    /**
     * Creates a token list from the elements of the expression.
     *
     * @param expression User's input expression
     * @return tokens
     */
    private List<String> tokenize(String expression) {
        // ArrayList for tokens
        List<String> tokens = new ArrayList<>();

        // Tokenizes first character
        tokens.add(Character.toString(expression.charAt(0)));

        // Iterate string and tokenize
        for (int i = 1; i < expression.length(); i++) {
            char c = expression.charAt(i);
            char lastChar = expression.charAt(i - 1);

            // Continue to next character if space
            if (c == ' ') {
                continue;
            }

            // Checks if c and lastChar are digits, combines characters into single token if true
            if (Character.isDigit(c) && Character.isDigit(lastChar)) {
                int lastIndex = (tokens.size() - 1);
                tokens.set(lastIndex, tokens.get(lastIndex) + c);
            } else {
                tokens.add(Character.toString(c));
            }
        }
        return tokens;
    }
}

