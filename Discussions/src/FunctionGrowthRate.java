// CMSC 350 Data Structures and Analysis
// Week 1 Discussion
// Joseph Julian
// January 9, 2021

// This program demonstrates the growth rate of two functions where
// one function starts off small but eventually outgrows the other.


public class FunctionGrowthRate {

    public static void main(String[] args) {
        int x = 0;
        double f1, f2;
        String ANSI_GREEN = "\u001B[32m";

        System.out.println("\nx \t\tf(x) = x^2 + x + 1000\tg(x) = x^3\n");

        while (true) {
            x += 1;
            f1 = Math.pow(x, 2) + x + 1000;
            f2 = Math.pow(x, 3);

            if (f2 < f1) {
                System.out.printf("%d \t\t%.2f\t\t\t\t\t%.2f\n", x, f1, f2);
            } else {
                System.out.printf(ANSI_GREEN + "%d \t\t%.2f\t\t\t\t\t%.2f\t\t<-- g(x) has outgrown f(x)\n", x, f1, f2);
                break;
            }
        }
    }
}
