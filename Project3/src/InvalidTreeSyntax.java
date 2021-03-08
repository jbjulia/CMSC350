/*
  Name:  Joseph Julian
  Project:  Project 3
  Date:  23 Feb 2021
  Description:  Defines unchecked exception thrown by GUI.
 */

public class InvalidTreeSyntax extends Exception {

    public InvalidTreeSyntax(String message) {
        super(message);
    }
}