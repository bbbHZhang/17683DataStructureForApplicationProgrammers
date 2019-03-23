package lab5;
import java.util.Random;

/**
 * 17683 Data Structures for Application Programmers.
 *
 * Lab 5 Comparing MergeSort with QuickSort
 *
 * A simple implementation of QuickSort
 * @author Terry Lee
 */
public class QuickSort {
    /**
     * constant for SIZE of an array to sort.
     */
    private static final int SIZE = 100000;
    /**
     * Random numbers.
     */
    private static Random rand = new Random();

    /**
     * Test program to check quick sort and its running time.
     * @param args arguments
     */
    public static void main(String[] args) {
        int[] array = new int[SIZE];

//        for (int i = 0; i < SIZE; i++) array[i] = rand.nextInt();

        for (int i = 0; i < SIZE; i++) array[i] = SIZE - i;

        Stopwatch timer = new Stopwatch();
        quickSort(array);
        System.out.println(
                "Time taken to sort " + SIZE + " elements (Quick Sort) : " + timer.elapsedTime() + " milliseconds");

        // to make sure sorting works.
        // add "-ea" vm argument
        assert isSorted(array);
    }

    /**
     * Rudimentary (arguably canonical) quick sort algorithm.
     * @param arr array to sort
     */
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * Recursive quick sort method.
     * @param arr array to sort
     * @param left left boundary
     * @param right right boundary
     */
    private static void quickSort(int[] arr, int left, int right) {
        // base case
        if (right - left <= 0) {
            return;
        }
        // Note: selection of pivot value makes a difference!!
        int pivot = arr[right];
        int partition = partition(arr, left, right, pivot);
        quickSort(arr, left, partition - 1);
        quickSort(arr, partition + 1, right);
    }

    /**
     * private helper method to partition the array.
     * @param arr array to partition
     * @param left left boundary
     * @param right right boundary
     * @param pivot pivot value (not index)
     * @return the index value where pivot value ends up 
     */
    private static int partition(int[] arr, int left, int right, int pivot) {
        int leftPointer = left - 1;
        int rightPointer = right;

        while (true) {
            while (arr[++leftPointer] < pivot);
            while (rightPointer > 0 && arr[--rightPointer] > pivot);
            if (leftPointer >= rightPointer) {
                break;
            }
            swap(arr, leftPointer, rightPointer);
        }
        swap(arr, leftPointer, right);
        return leftPointer;
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
