package com.assignment.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("InputParser")
class InputParserTest {

    @Nested
    @DisplayName("when given valid input")
    class ValidInput {

        @Test
        @DisplayName("single test case is parsed correctly")
        void singleTestCase() {
            final Scanner scanner = new Scanner("1\n15\n");

            final List<Integer> result = InputParser.parseTestCases(scanner);

            assertEquals(1, result.size());
            assertEquals(15, result.get(0));
        }

        @Test
        @DisplayName("multiple test cases are parsed correctly")
        void multipleTestCases() {
            final Scanner scanner = new Scanner("3\n10\n20\n30\n");

            final List<Integer> result = InputParser.parseTestCases(scanner);

            assertEquals(List.of(10, 20, 30), result);
        }

        @Test
        @DisplayName("zero test cases returns an empty list")
        void zeroTestCasesReturnsEmptyList() {
            final Scanner scanner = new Scanner("0\n");

            final List<Integer> result = InputParser.parseTestCases(scanner);

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("when given invalid input")
    class InvalidInput {

        @Test
        @DisplayName("negative test case count throws exception")
        void negativeCountThrowsException() {
            final Scanner scanner = new Scanner("-1\n");

            assertThrows(IllegalArgumentException.class,
                    () -> InputParser.parseTestCases(scanner));
        }

        @Test
        @DisplayName("fewer values than declared throws exception")
        void fewerValuesThanDeclaredThrows() {
            final Scanner scanner = new Scanner("3\n10\n");

            assertThrows(NoSuchElementException.class,
                    () -> InputParser.parseTestCases(scanner));
        }
    }
}
