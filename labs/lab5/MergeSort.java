package lab5;
import java.util.Random;

/**
 * 17683 Data Structures for Application Programmers.
 *
 * Lab 5 Comparing MergeSort with QuickSort
 *
 * A simple MergeSort implementation
 *
 * Andrew ID: hanzhan2
 * @author Han Zhang
 */
public class MergeSort {
    /**
     * constant for SIZE of an array to sort.
     */
    private static final int SIZE = 100000;
    /**
     * Random numbers.
     */
    private static Random rand = new Random();

    /**
     * Test program to check merge sort and its running time.
     * @param args arguments
     */
    public static void main(String[] args) {
        int[] array = new int[SIZE];
        for (int i = 0; i < SIZE; i++) array[i] = rand.nextInt();

        Stopwatch timer = new Stopwatch();
        mergeSort(array);
        System.out.println(
                "Time taken to sort " + SIZE + " elements (Merge Sort): " + timer.elapsedTime() + " milliseconds");
        // to make sure sorting works
        // add "-ea" vm argument
        assert isSorted(array);
    }

    /**
     * merge sort method.
     * @param from input array to sort
     */
    public static void mergeSort(int[] from) {
        // create a new array
        int[] to = new int[from.length];
        mergeSort(from, to, 0, from.length - 1);
    }

    /**
     * helper method to merge sort from (input) array using an auxiliary array.
     * @param from input array to sort
     * @param to auxiliary array to use
     * @param left left boundary
     * @param right right boundary
     */
    private static void mergeSort(int[] from, int[] to, int left, int right) {
        // base case
        if (left >= right) {
            return;
        }
        // recursive case
        // find midpoint
        int mid = left + (right - left) / 2;
        // sort left half recursively
        mergeSort(from, to, left, mid);
        // sort right half recursively
        mergeSort(from, to, mid + 1, right);
        // merge them
        merge(from, to, left, mid + 1, right);
    }

    /**
     * Instead of creating multiple arrays, this merge works with only two arrays.
     * @param from input array
     * @param to auxiliary array to use during merge process
     * @param leftPos starting point of left half
     * @param rightPos starting point of right half
     * @param rightBound upper bound of right half
     */
    public static void merge(int[] from, int[] to, int leftPos, int rightPos, int rightBound) {
        int toIndex = leftPos;
        int leftBound = rightPos - 1;
        int numOfItems = rightBound - leftPos + 1;
        while (leftPos <= leftBound && rightPos <= rightBound) {
            if (from[leftPos] < from[rightPos]) {
                to[toIndex++] = from[leftPos++];
            } else {
                to[toIndex++] = from[rightPos++];
            }
        }
        while(leftPos <= leftBound) {
            to[toIndex++] = from[leftPos++];
        }
        while(rightPos <= rightBound) {
            to[toIndex++] = from[rightPos++];
        }
        for (int i = 0; i < numOfItems; i++, rightBound--) {
            from[rightBound] = to[rightBound];
        }
        /**
         * 
         * compare(A,B){
         * A-B asc
         *
         * B-A des
         */
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
