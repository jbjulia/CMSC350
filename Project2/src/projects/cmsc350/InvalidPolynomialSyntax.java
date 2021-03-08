/*
  Name:  Joseph Julian
  Project:  Project 2
  Date:  09 Feb 2021
  Description:  Defines unchecked exception thrown by Polynomial class constructor.
 */

package projects.cmsc350;

public class InvalidPolynomialSyntax extends RuntimeException {

    InvalidPolynomialSyntax(String message) {
        super(message);
    }
}
