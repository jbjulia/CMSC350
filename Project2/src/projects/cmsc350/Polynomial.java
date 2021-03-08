/*
  Name:  Joseph Julian
  Project:  Project 2
  Date:  09 Feb 2021
  Description:  Defines polynomials and creates linked list.
 */

package projects.cmsc350;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Scanner;


public class Polynomial implements Iterable<Polynomial.Term>, Comparable<Polynomial> {

    private Term head;

    /**
     * Creates a linked list of individual term nodes
     *
     * @param inFile User-selected file
     */
    public Polynomial(String inFile) {
        head = null;
        Scanner termScanner = new Scanner(inFile);

        try {
            while (termScanner.hasNext()) {
                addTerm(termScanner.nextDouble(), termScanner.nextInt());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidPolynomialSyntax("Error: Incorrect polynomial syntax.");
        }
    }

    /**
     * Checks for negative exponents and adds terms
     *
     * @param coefficient -
     * @param exponent    -
     */
    public void addTerm(double coefficient, int exponent) {
        if (exponent < 0) {
            throw new InvalidPolynomialSyntax("Error: Negative exponents found.");
        }
        Term current = head;
        if (current == null) {
            head = new Term(coefficient, exponent);
            head.next = null;
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Term(coefficient, exponent);
        }

    }

    /**
     * Compares the exponents / coefficients of two polynomials
     *
     * @param argPolynomial the argument polynomial
     * @return -1, +1
     */
    @Override
    public int compareTo(Polynomial argPolynomial) {
        Term thisCurrent = this.head;
        Term otherCurrent = argPolynomial.head;

        while (thisCurrent != null && otherCurrent != null) {
            if (thisCurrent.getExponent() != otherCurrent.getExponent()) {
                return thisCurrent.getExponent() - otherCurrent.getExponent();
            } else if (thisCurrent.getCoefficient() != otherCurrent.getCoefficient()) {
                if (otherCurrent.getCoefficient() > thisCurrent.getCoefficient()) {
                    return -1;
                } else if (otherCurrent.getCoefficient() < thisCurrent.getCoefficient()) {
                    return +1;
                }
            }
            thisCurrent = thisCurrent.getNext();
            otherCurrent = otherCurrent.getNext();
        }
        if (thisCurrent == null && otherCurrent == null) {
            return 0;
        }
        if (thisCurrent == null) {
            return -1;
        } else {
            return +1;
        }
    }

    /**
     * Compares the exponents of two polynomials
     *
     * @param secondPolynomial the second polynomial
     * @return +1, -1
     */
    public int compareExponents(Polynomial secondPolynomial) {
        Term thisPolyTerm = this.head;
        Term otherPolyTerm = secondPolynomial.head;
        while (thisPolyTerm != null && otherPolyTerm != null) {
            if (thisPolyTerm.getExponent() != otherPolyTerm.getExponent()) {
                return thisPolyTerm.getExponent() - otherPolyTerm.getExponent();
            } else {
                thisPolyTerm = thisPolyTerm.getNext();
                otherPolyTerm = otherPolyTerm.getNext();
            }
        }
        if (thisPolyTerm == null && otherPolyTerm == null) {
            return 0;
        }
        if (otherPolyTerm == null) {
            return +1;
        } else {
            return -1;
        }
    }

    /**
     * Produces iterator to traverse polynomial terms
     *
     * @return data
     */
    @Override
    public @NotNull
    Iterator<Term> iterator() {
        return new Iterator() {

            private Term current = getHead();

            @Override
            public boolean hasNext() {
                return current != null && current.getNext() != null;
            }

            @Override
            public Term next() {
                Term data = current;
                current = current.next;
                return data;
            }
        };
    }

    /**
     * Converts polynomial to a string
     *
     * @return strPolynomial.toString()
     */
    @Override
    public String toString() {
        StringBuilder strPolynomial = new StringBuilder();
        if (head.coefficient > 0) {
            strPolynomial.append(head.toString());
        } else {
            strPolynomial.append(" - ").append(head.toString());
        }
        for (Term temp = head.next; temp != null; temp = temp.next) {
            if (temp.coefficient < 0) {
                strPolynomial.append(" - ").append(temp.toString());
            } else {
                strPolynomial.append(" + ").append(temp.toString());
            }
        }
        return strPolynomial.toString();

    }

    private Term getHead() {
        return head;
    }

    /**
     * Nodes of the polynomial objects that carry reference to the next node
     */
    static class Term {
        private final double coefficient;
        private final int exponent;
        private Term next;

        private Term(double c, int e) {
            coefficient = c;
            exponent = e;
            next = null;
        }

        private int getExponent() {
            return this.exponent;
        }

        private double getCoefficient() {
            return this.coefficient;
        }

        private Term getNext() {
            return next;
        }

        @Override
        public String toString() {
            String strTerm = String.format("%.1f", Math.abs(coefficient));
            if (exponent == 0) {
                return strTerm;
            } else if (exponent == 1) {
                return strTerm + "x";
            } else {
                return strTerm + "x^" + exponent;
            }
        }
    }
}
