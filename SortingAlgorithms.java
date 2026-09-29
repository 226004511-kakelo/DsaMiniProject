public class SortingAlgorithms {

    // Selection Sort
    public static SortResult selectionSort(int[] array) {

        long comparisons = 0;
        long swaps = 0;

        for (int i = 0; i < array.length - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < array.length; j++) {

                comparisons++;

                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {

                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;

                swaps++;
            }
        }

        return new SortResult(comparisons, swaps);
    }

    // Insertion Sort
    public static SortResult insertionSort(int[] array) {

        long comparisons = 0;
        long shifts = 0;

        for (int i = 1; i < array.length; i++) {

            int key = array[i];
            int j = i - 1;

            while (j >= 0) {

                comparisons++;

                if (array[j] > key) {

                    array[j + 1] = array[j];
                    shifts++;
                    j--;

                } else {
                    break;
                }
            }

            array[j + 1] = key;
        }

        return new SortResult(comparisons, shifts);
    }

    // Merge Sort
    public static SortResult mergeSort(int[] array) {

        SortResult result = new SortResult(0, 0);

        if (array.length > 1) {
            mergeSortRecursive(array, 0, array.length - 1, result);
        }

        return result;
    }

    private static void mergeSortRecursive(
            int[] array, int left, int right, SortResult result) {

        if (left >= right) {
            return;
        }

        int middle = (left + right) / 2;

        mergeSortRecursive(array, left, middle, result);
        mergeSortRecursive(array, middle + 1, right, result);

        merge(array, left, middle, right, result);
    }

    private static void merge(
            int[] array,
            int left,
            int middle,
            int right,
            SortResult result) {

        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = array[left + i];
        }

        for (int i = 0; i < rightSize; i++) {
            rightArray[i] = array[middle + 1 + i];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftSize && j < rightSize) {

            result.comparisons++;

            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }

            k++;
        }

        while (i < leftSize) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // Quick Sort
    public static SortResult quickSort(int[] array) {

        SortResult result = new SortResult(0, 0);

        if (array.length > 1) {
            quickSortRecursive(array, 0, array.length - 1, result);
        }

        return result;
    }

    private static void quickSortRecursive(
            int[] array, int low, int high, SortResult result) {

        if (low < high) {

            int pivotIndex =
                    partition(array, low, high, result);

            quickSortRecursive(
                    array, low, pivotIndex - 1, result);

            quickSortRecursive(
                    array, pivotIndex + 1, high, result);
        }
    }

    // Pivot rule: last element
    private static int partition(
            int[] array,
            int low,
            int high,
            SortResult result) {

        int pivot = array[high];

        int i = low - 1;

        for (int j = low; j < high; j++) {

            result.comparisons++;

            if (array[j] <= pivot) {

                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                result.swaps++;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        result.swaps++;

        return i + 1;
    }

    // Display an array
    public static void displayArray(int[] array) {

        System.out.print("[");

        for (int i = 0; i < array.length; i++) {

            System.out.print(array[i]);

            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }
}


// Stores statistics from a sorting algorithm
class SortResult {

    long comparisons;
    long swaps;

    SortResult(long comparisons, long swaps) {
        this.comparisons = comparisons;
        this.swaps = swaps;
    }
}