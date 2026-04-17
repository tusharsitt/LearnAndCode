package com.assignment.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ResultPrinter")
class ResultPrinterTest {

    @Test
    @DisplayName("prints each result on a separate line")
    void printsEachResultOnSeparateLine() {
        final ByteArrayOutputStream outputStream =
                new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(outputStream);

        ResultPrinter.printResults(List.of(2, 5, 10), printStream);

        final String expectedOutput = "2" + System.lineSeparator()
                + "5" + System.lineSeparator()
                + "10" + System.lineSeparator();

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    @DisplayName("empty result list produces no output")
    void emptyResultListProducesNoOutput() {
        final ByteArrayOutputStream outputStream =
                new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(outputStream);

        ResultPrinter.printResults(List.of(), printStream);

        assertEquals("", outputStream.toString());
    }

    @Test
    @DisplayName("single result prints one line")
    void singleResultPrintsOneLine() {
        final ByteArrayOutputStream outputStream =
                new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(outputStream);

        ResultPrinter.printResults(List.of(42), printStream);

        assertEquals("42" + System.lineSeparator(),
                outputStream.toString());
    }
}
