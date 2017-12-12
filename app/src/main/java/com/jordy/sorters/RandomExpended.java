package com.jordy.sorters;

import java.util.Random;

/**
 * A class that expends {@see java.util.Random}.
 */

public class RandomExpended {

    private static final Random RANDOM_NUMBER_HELPER = new Random();

    /**
     * Gives back a random number between the lower and upper limit (
     * upperLimit is inclusive). WATCH OUT, wrong numbers are easily returned
     * because there are no controls.
     *
     * (This method is used for proving correct randoms)
     *
     * @param lowerLimit must be smaller than {@code bovenGrens}.
     * @param upperLimit must be larger than {@code onderGrens}.
     * @return
     */
    public static double randomNumberInsideBoundaries(final int lowerLimit,
                                                      final int upperLimit) {
        return (double) (RANDOM_NUMBER_HELPER.nextInt((upperLimit - lowerLimit) + 1)
                + lowerLimit);
    }

    /**
     *
     * @param lowerLimit
     * @param upperLimit
     * @return [lowerLimit, upperLimit]
     */
    public static int randomNumberInsideBoundariesInteger(final int lowerLimit,
                                                          final int upperLimit) {
        return (RANDOM_NUMBER_HELPER.nextInt((upperLimit - lowerLimit) + 1)
                + lowerLimit);
    }
}
