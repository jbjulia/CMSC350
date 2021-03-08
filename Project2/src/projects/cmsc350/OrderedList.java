/*
  Name:  Joseph Julian
  Project:  Project 2
  Date:  09 Feb 2021
  Description:  A utility class that contains two overloaded implementations of checkSorted.
 */

package projects.cmsc350;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class OrderedList {

    public static <T extends Comparable<? super T>> boolean checkSorted(List<T> list) {
        boolean isSorted = true;

        for (int i = list.size() - 1; i > 0; i--) {
            T current = list.get(i);
            if (!checkSorted(list, current)) {
                isSorted = false;
            }
        }
        return isSorted;
    }

    private static <T extends Comparable<? super T>> boolean checkSorted(@NotNull List<T> list, T current) {
        T currentValue = list.get(list.indexOf(current));
        T nextValue = list.get(list.indexOf(current) - 1);

        if (nextValue != null) {
            return currentValue.compareTo(nextValue) >= 0;
        }
        return true;
    }
}
