package com.jordy.sorters;

/**
 * Bubblesorter
 */

public class Bubblesorter {

    public <T extends Object> Student[] bubbleSort(final Student[] list) {
        final int n = list.length;
        Student temp;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (0 < list[j - 1].compareTo((Student) list[j])) {
                    //swap elements
                    temp = list[j - 1];
                    list[j - 1] = list[j];
                    list[j] = temp;
                }

            }
        }
        return list;
    }
}
