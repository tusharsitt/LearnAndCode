package com.assignment.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("EqualDivisorPairCounter")
class EqualDivisorPairCounterTest {

    @Nested
    @DisplayName("when given valid upper bounds")
    class ValidUpperBounds {

        @Test
        @DisplayName("k=15 returns 2 (pairs at n=2 and n=14)")
        void fifteenReturnsTwoMatchingPairs() {
            assertEquals(2, EqualDivisorPairCounter.countMatchingPairs(15));
        }

        @Test
        @DisplayName("k=3 returns 1 (pair at n=2, since div(2)=div(3)=2)")
        void threeReturnsOneMatchingPair() {
            assertEquals(1, EqualDivisorPairCounter.countMatchingPairs(3));
        }

        @ParameterizedTest(name = "k={0} → {1} matching pairs")
        @CsvSource({
                "4, 1",
                "5, 1",
                "10, 1",
                "20, 2"
        })
        @DisplayName("various upper bounds return correct counts")
        void variousUpperBoundsReturnCorrectCounts(final int upperBound,
                                                   final int expected) {
            assertEquals(expected,
                    EqualDivisorPairCounter.countMatchingPairs(upperBound));
        }

        @Test
        @DisplayName("k=100 returns a consistent result on repeated calls")
        void repeatedCallsReturnSameResult() {
            final int firstCall = EqualDivisorPairCounter
                    .countMatchingPairs(100);
            final int secondCall = EqualDivisorPairCounter
                    .countMatchingPairs(100);

            assertEquals(firstCall, secondCall,
                    "Repeated calls must produce identical results");
        }
    }

    @Nested
    @DisplayName("when given invalid upper bounds")
    class InvalidUpperBounds {

        @ParameterizedTest(name = "k={0} throws IllegalArgumentException")
        @ValueSource(ints = {0, 1, 2, -1, -100, Integer.MIN_VALUE})
        @DisplayName("upper bounds below 3 throw IllegalArgumentException")
        void belowMinimumThrowsException(final int invalidBound) {
            assertThrows(IllegalArgumentException.class,
                    () -> EqualDivisorPairCounter
                            .countMatchingPairs(invalidBound));
        }

        @Test
        @DisplayName("exception message contains the invalid upper bound")
        void exceptionMessageContainsInvalidBound() {
            final IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> EqualDivisorPairCounter.countMatchingPairs(1));

            assertEquals(
                    "Upper bound must be at least 3 for a non-empty range,"
                            + " but was: 1",
                    exception.getMessage());
        }
    }

    @Nested
    @DisplayName("verifying specific pairs")
    class SpecificPairVerification {

        @Test
        @DisplayName("n=2 and n+1=3 both have 2 divisors (both primes)")
        void pairTwoAndThreeMatch() {
            assertEquals(
                    DivisorCalculator.countDivisors(2),
                    DivisorCalculator.countDivisors(3));
        }

        @Test
        @DisplayName("n=14 and n+1=15 both have 4 divisors")
        void pairFourteenAndFifteenMatch() {
            assertEquals(
                    DivisorCalculator.countDivisors(14),
                    DivisorCalculator.countDivisors(15));
        }

        @Test
        @DisplayName("n=4 and n+1=5 do NOT have the same divisor count")
        void pairFourAndFiveDoNotMatch() {
            final int divisorsOfFour = DivisorCalculator.countDivisors(4);
            final int divisorsOfFive = DivisorCalculator.countDivisors(5);

            assertEquals(false, divisorsOfFour == divisorsOfFive,
                    "4 and 5 should have different divisor counts");
        }
    }
}
