public class PartBTest {

    public static void main(String[] args) {

        int[] array = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};

        System.out.println("==========================================");
        System.out.println("             PART B - QUICK SORT");
        System.out.println("==========================================");

        System.out.print("Original array: ");
        SortingAlgorithms.displayArray(array);

        System.out.println();
        System.out.println("Pivot rule: Last element of each partition");
        System.out.println();

        quickSortTrace(array, 0, array.length - 1, 1);

        System.out.println();
        System.out.print("Final sorted array: ");
        SortingAlgorithms.displayArray(array);
    }

    public static void quickSortTrace(
            int[] array, int low, int high, int stage) {

        if (low < high) {

            int pivot = array[high];

            System.out.println(
                    "Partition stage " + stage);

            System.out.println(
                    "Sub-array: " + range(array, low, high));

            System.out.println(
                    "Pivot: " + pivot);

            int i = low - 1;

            for (int j = low; j < high; j++) {

                if (array[j] <= pivot) {

                    i++;

                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }

            int temp = array[i + 1];
            array[i + 1] = array[high];
            array[high] = temp;

            int pivotIndex = i + 1;

            System.out.println(
                    "Left partition: "
                    + range(array, low, pivotIndex - 1));

            System.out.println(
                    "Pivot in position: " + array[pivotIndex]);

            System.out.println(
                    "Right partition: "
                    + range(array, pivotIndex + 1, high));

            System.out.println();

            // Only show the first two partition stages
            if (stage < 2) {

                quickSortTrace(
                        array, low, pivotIndex - 1, stage + 1);

                quickSortTrace(
                        array, pivotIndex + 1, high, stage + 1);

            } else {

                // Finish sorting without displaying more stages
                finishQuickSort(
                        array, low, pivotIndex - 1);

                finishQuickSort(
                        array, pivotIndex + 1, high);
            }
        }
    }

    public static void finishQuickSort(
            int[] array, int low, int high) {

        if (low < high) {

            int pivot = array[high];

            int i = low - 1;

            for (int j = low; j < high; j++) {

                if (array[j] <= pivot) {

                    i++;

                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }

            int temp = array[i + 1];
            array[i + 1] = array[high];
            array[high] = temp;

            int pivotIndex = i + 1;

            finishQuickSort(
                    array, low, pivotIndex - 1);

            finishQuickSort(
                    array, pivotIndex + 1, high);
        }
    }

    public static String range(
            int[] array, int low, int high) {

        if (low > high) {
            return "[]";
        }

        String result = "[";

        for (int i = low; i <= high; i++) {

            result += array[i];

            if (i < high) {
                result += ", ";
            }
        }

        result += "]";

        return result;
    }
}