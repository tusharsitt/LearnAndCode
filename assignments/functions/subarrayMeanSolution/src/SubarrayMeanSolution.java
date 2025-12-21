import java.util.Scanner;

public class SubarrayMeanSolution {

    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        executeSolution();
    }


    private static void executeSolution() {

        if (!scanner.hasNextInt()) return;

        int elementCount = scanner.nextInt();
        int queryCount = scanner.nextInt();

        long[] cumulativeSums = buildCumulativeSumArray(elementCount);
        processAndDisplayQueries( queryCount, cumulativeSums);

        scanner.close();
    }


    private static long[] buildCumulativeSumArray( int size) {
        long[] cumulativeSums = new long[size + 1];
        for (int index = 1; index <= size; index++) {
            long currentElement = scanner.nextLong();
            cumulativeSums[index] = cumulativeSums[index - 1] + currentElement;
        }
        return cumulativeSums;
    }


    private static void processAndDisplayQueries( int totalQueries, long[] cumulativeSums) {
        StringBuilder outputBuffer = new StringBuilder();

        for (int index = 0; index < totalQueries; index++) {
            int startIndex = scanner.nextInt();
            int endIndex = scanner.nextInt();

            long meanFloor = calculateSubarrayMeanFloor(cumulativeSums, startIndex, endIndex);
            outputBuffer.append(meanFloor).append("\n");
        }

        System.out.print(outputBuffer);
    }


    private static long calculateSubarrayMeanFloor(long[] cumulativeSums, int start, int end) {
        long rangeSum = cumulativeSums[end] - cumulativeSums[start - 1];
        int elementCountInRange = end - start + 1;

        return rangeSum / elementCountInRange;
    }
}