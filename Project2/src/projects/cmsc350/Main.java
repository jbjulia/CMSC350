/*
  Name:  Joseph Julian
  Project:  Project 2
  Date:  09 Feb 2021
  Description:  Main class file for selecting polynomial text file.
 */

package projects.cmsc350;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Polynomial> lstPolynomials = new ArrayList<>();

    /**
     * Main method to read file and check sort order of polynomial list
     *
     * @param args -
     */
    public static void main(String[] args) {
        try {
            ArrayList<String> checkList = readFile();
            for (String element : checkList) {
                Polynomial polynomial = new Polynomial(element);
                System.out.println(polynomial);
                lstPolynomials.add(polynomial);
            }
        } catch (InvalidPolynomialSyntax e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), e.getMessage());
        }
        if (OrderedList.checkSorted(lstPolynomials)) {
            System.out.println("\nThis polynomial list is strong sorted order.");
        } else if (checkWeakOrder()) {
            System.out.println("\nThis polynomial list is weak sorted order.");
        } else {
            System.out.println("\nThis polynomial list is neither strong nor weak sorted order.");
        }
    }

    /**
     * Opens text file and scans in polynomials into an ArrayList
     *
     * @return lstPolynomials
     */
    public static ArrayList<String> readFile() {
        ArrayList<String> lstPolynomials = new ArrayList<>();
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                Scanner inFile = new Scanner(file);
                if (file.isFile()) {
                    while (inFile.hasNextLine()) {
                        String polynomial = inFile.nextLine();
                        lstPolynomials.add(polynomial);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Error: Please try again.");
            }
        }
        return lstPolynomials;
    }

    /**
     * Checks if polynomial list is in weak order
     *
     * @return isWeakOrder
     */
    public static boolean checkWeakOrder() {
        boolean isWeakOrder = true;
        Polynomial previous = lstPolynomials.get(lstPolynomials.size() - 1);
        for (int i = lstPolynomials.size() - 2; i > 0; i--) {
            if (previous.compareExponents(lstPolynomials.get(i)) < 0) {
                isWeakOrder = false;
            }
        }
        return isWeakOrder;
    }
}
