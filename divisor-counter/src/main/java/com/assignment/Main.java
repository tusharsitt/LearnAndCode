package com.assignment;

import com.assignment.domain.EqualDivisorPairCounter;
import com.assignment.io.InputParser;
import com.assignment.io.ResultPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Main {

    private Main() {
    }

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final List<Integer> upperBounds = InputParser
                .parseTestCases(scanner);

        final List<Integer> results = new ArrayList<>(upperBounds.size());

        for (final int upperBound : upperBounds) {
            results.add(EqualDivisorPairCounter
                    .countMatchingPairs(upperBound));
        }

        ResultPrinter.printResults(results, System.out);

        scanner.close();
    }
}
