import java.util.Random;

public class SortingExperiment {

    public static void runExperiment() {

        int[] sizes = {20, 50, 100, 500};

        Random random = new Random(521);

        System.out.println("\n==============================================");
        System.out.println("         SORTING ALGORITHM EXPERIMENT");
        System.out.println("==============================================");

        System.out.printf(
            "%-18s %-10s %-15s %-20s%n",
            "Algorithm",
            "Size",
            "Comparisons",
            "Execution Time (ns)"
        );

        System.out.println(
            "--------------------------------------------------------------"
        );

        for (int size : sizes) {

            int[] original = generateArray(size, random);

            runAlgorithm("Selection Sort", 1, original);
            runAlgorithm("Insertion Sort", 2, original);
            runAlgorithm("Merge Sort", 3, original);
            runAlgorithm("Quick Sort", 4, original);
        }

        System.out.println(
            "\nAlmost-sorted 100-element array experiment:"
        );

        runAlmostSortedExperiment();
    }

    private static int[] generateArray(int size, Random random) {

        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000) + 1;
        }

        return array;
    }

    private static int[] copyArray(int[] original) {

        int[] copy = new int[original.length];

        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }

        return copy;
    }

    private static void runAlgorithm(
            String name,
            int algorithm,
            int[] original) {

        int[] array = copyArray(original);

        long start = System.nanoTime();

        SortResult result;

        if (algorithm == 1) {
            result = SortingAlgorithms.selectionSort(array);
        } else if (algorithm == 2) {
            result = SortingAlgorithms.insertionSort(array);
        } else if (algorithm == 3) {
            result = SortingAlgorithms.mergeSort(array);
        } else {
            result = SortingAlgorithms.quickSort(array);
        }

        long end = System.nanoTime();

        long executionTime = end - start;

        System.out.printf(
            "%-18s %-10d %-15d %-20d%n",
            name,
            original.length,
            result.comparisons,
            executionTime
        );
    }

    private static void runAlmostSortedExperiment() {

        Random random = new Random(521);

        int[] array = generateArray(100, random);

        // First sort the array
        SortingAlgorithms.selectionSort(array);

        // Swap five pairs of neighbouring values
        for (int i = 0; i < 10; i += 2) {

            int temp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temp;
        }

        System.out.println(
            "\nTesting the same almost-sorted array with all algorithms:"
        );

        runAlgorithm("Selection Sort", 1, array);
        runAlgorithm("Insertion Sort", 2, array);
        runAlgorithm("Merge Sort", 3, array);
        runAlgorithm("Quick Sort", 4, array);
    }
}