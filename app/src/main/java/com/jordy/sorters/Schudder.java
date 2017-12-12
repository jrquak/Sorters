package com.jordy.sorters;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Schud waardes van een array door elkaar.
 */

public class Schudder {

    public static <T> void schud(T[] waardes) {
        List<T> waardesAlsLijst = Arrays.asList(waardes);
        Collections.shuffle(waardesAlsLijst);
        waardesAlsLijst.toArray(waardes);
    }
}
