package lab5;
import java.util.Random;

/**
 * 17683 Data Structures for Application Programmers.
 *
 * Lab 5 Comparing MergeSort with QuickSort
 *
 * A simple implementation of improved QuickSort
 * @author Terry Lee
 */
public class QuickSortImproved {
    /**
     * constant for SIZE of an array to sort.
     */
    private static final int SIZE = 10;
    /**
     * Random numbers.
     */
    private static Random rand = new Random();

    /**
     * Test program to check a version of quick sort and its running time.
     * @param args arguments
     */
    public static void main(String[] args) {
        int[] array = new int[SIZE];


        for (int i = 0; i < SIZE; i++) array[i] = rand.nextInt();
//        for (int i = 0; i < SIZE; i++) array[i] = SIZE - i;

        StringBuilder sb = new StringBuilder();
        for (int i: array) {
            sb.append(i).append(", ");
        }
        System.out.println(sb.toString());
        
        Stopwatch timer = new Stopwatch();
        quickSort(array);
        System.out.println("Time taken to sort " + SIZE + " elements (Quick Sort Improved) : " + timer.elapsedTime()
                + " milliseconds");
        
        sb = new StringBuilder();
        for (int i: array) {
            sb.append(i).append(", ");
        }
        System.out.println(sb.toString());

        // to make sure sorting works.
        // add "-ea" vm argument
        assert isSorted(array);
    }

    /**
     * A version of quick sort.
     * @param array array to sort
     */
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Recursive method to sort an array using quick sort.
     * This is the core algorithm.
     * @param array array to sort
     * @param left left boundary
     * @param right right boundary
     */
    private static void quickSort(int[] array, int left, int right) {
        int leftPointer = left;
        int rightPointer = right;

        // Get the pivot value
        int pivot = array[left + (right - left) / 2];

        while (leftPointer <= rightPointer) {
            // inner loops are simpler
            while (array[leftPointer] < pivot) {
                leftPointer++;
            }
            while (array[rightPointer] > pivot) {
                rightPointer--;
            }
            if (leftPointer <= rightPointer) {
                swap(array, leftPointer, rightPointer);
                leftPointer++;
                rightPointer--;
            }
        }

        // Recursion
        if (left < rightPointer) {
            quickSort(array, left, rightPointer);
        }
        if (leftPointer < right) {
            quickSort(array, leftPointer, right);
        }
    }

    /**
     * helper method to swap two values in an array.
     * @param array array to modify
     * @param one first index value
     * @param two second index value
     */
    private static void swap(int[] array, int one, int two) {
        int tmp = array[one];
        array[one] = array[two];
        array[two] = tmp;
    }

    /**
     * A simple debugging program to check if array is sorted.
     * @param array array to check
     * @return true if sorted and false if not
     */
    private static boolean isSorted(int[] array) {
        return isSorted(array, 0, array.length - 1);
    }

    /**
     * Helper method to check if array is sorted.
     * @param array array to check
     * @param lo low boundary
     * @param hi high boundary
     * @return true if sorted and false if not
     */
    private static boolean isSorted(int[] array, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }

}
