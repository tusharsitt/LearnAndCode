package com.assignment.domain;

public final class DivisorCalculator {

    private static final int MINIMUM_VALID_NUMBER = 1;

    private DivisorCalculator() {
    }

    public static int countDivisors(final int number) {
        if (number < MINIMUM_VALID_NUMBER) {
            throw new IllegalArgumentException(
                    "Number must be positive, but was: " + number);
        }

        int divisorCount = 0;
        final int squareRoot = (int) Math.sqrt(number);

        for (int candidate = 1; candidate <= squareRoot; candidate++) {
            if (number % candidate == 0) {
                divisorCount++;

                if (candidate != number / candidate) {
                    divisorCount++;
                }
            }
        }

        return divisorCount;
    }
}
