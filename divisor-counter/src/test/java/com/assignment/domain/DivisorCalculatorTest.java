package com.assignment.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("DivisorCalculator")
class DivisorCalculatorTest {

    @Nested
    @DisplayName("when given valid numbers")
    class ValidNumbers {

        @Test
        @DisplayName("1 has exactly one divisor")
        void oneDivisorForOne() {
            assertEquals(1, DivisorCalculator.countDivisors(1));
        }

        @ParameterizedTest(name = "prime {0} has exactly 2 divisors")
        @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29})
        @DisplayName("prime numbers have exactly 2 divisors")
        void primesHaveTwoDivisors(final int prime) {
            assertEquals(2, DivisorCalculator.countDivisors(prime));
        }

        @ParameterizedTest(name = "countDivisors({0}) == {1}")
        @CsvSource({
                "4, 3",
                "6, 4",
                "12, 6",
                "14, 4",
                "15, 4",
                "16, 5",
                "28, 6",
                "100, 9"
        })
        @DisplayName("composite numbers return the correct divisor count")
        void compositeNumbersReturnCorrectCount(final int number,
                                                final int expectedCount) {
            assertEquals(expectedCount,
                    DivisorCalculator.countDivisors(number));
        }

        @Test
        @DisplayName("perfect square (36) counts its root only once")
        void perfectSquareCountsRootOnce() {
            assertEquals(9, DivisorCalculator.countDivisors(36));
        }

        @Test
        @DisplayName("large number computes correctly")
        void largeNumberComputesCorrectly() {
            assertEquals(49, DivisorCalculator.countDivisors(1_000_000));
        }
    }

    @Nested
    @DisplayName("when given invalid numbers")
    class InvalidNumbers {

        @Test
        @DisplayName("zero throws IllegalArgumentException")
        void zeroThrowsException() {
            assertThrows(IllegalArgumentException.class,
                    () -> DivisorCalculator.countDivisors(0));
        }

        @Test
        @DisplayName("negative number throws IllegalArgumentException")
        void negativeNumberThrowsException() {
            assertThrows(IllegalArgumentException.class,
                    () -> DivisorCalculator.countDivisors(-5));
        }

        @Test
        @DisplayName("exception message contains the invalid number")
        void exceptionMessageContainsInvalidNumber() {
            final IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> DivisorCalculator.countDivisors(-10));

            assertEquals("Number must be positive, but was: -10",
                    exception.getMessage());
        }
    }
}
