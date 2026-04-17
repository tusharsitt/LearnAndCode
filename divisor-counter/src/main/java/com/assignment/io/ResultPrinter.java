package com.assignment.io;

import java.io.PrintStream;
import java.util.List;

public final class ResultPrinter {

    private ResultPrinter() {
    }

    public static void printResults(final List<Integer> results,
                                    final PrintStream output) {
        for (final int result : results) {
            output.println(result);
        }
    }
}
