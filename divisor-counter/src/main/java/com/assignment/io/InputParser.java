package com.assignment.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class InputParser {

    private InputParser() {
    }

    public static List<Integer> parseTestCases(final Scanner scanner) {
        final int testCaseCount = scanner.nextInt();

        if (testCaseCount < 0) {
            throw new IllegalArgumentException(
                    "Number of test cases cannot be negative: "
                            + testCaseCount);
        }

        final List<Integer> upperBounds = new ArrayList<>(testCaseCount);

        for (int index = 0; index < testCaseCount; index++) {
            upperBounds.add(scanner.nextInt());
        }

        return upperBounds;
    }
}
