package hw2;
import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * 17683 Data Structures for Application Programmers.
 *
 * Homework Assignment 2 Solve Josephus problem
 * with different data structures
 * and different algorithms and compare running times
 *
 * Generally, the last method performs the best
 * The three methods have the same time complexity, O(n^2) but there are some differences.
 * ArrayDeque and LinkedList are similar and both do a lot of "copy and paste" when calling addFirst() and removeLast().
 * ArrayDeque is a little bit better because it is circular.
 * LinkedListAT do a lot of finding next node before remove(index) but only copy and paste once when calling remove(index).
 * Finding next is faster than copying and pasting, so the last one is the best.
 *
 * Andrew ID: hanzhan2
 * @author Han Zhang
 */
public class Josephus {

    /**
     * Uses ArrayDeque class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithAD(int size, int rotation) {
        if (size <= 0) {
            throw new RuntimeException("size should be greater than 0");
        }
        if (rotation <= 0) {
            throw new RuntimeException("rotation should be greater than 0");
        }
        ArrayDeque<Integer> line1 = new ArrayDeque<Integer>();
        for (int pos = 1; pos <= size; pos++) {
            line1.addLast(pos);
            }
        while (size != 1) {
            for (int a = 1; a < rotation; a++) {
                line1.addLast(line1.removeFirst());
            }
            line1.removeFirst();
            size--;
        }
        return line1.getFirst();
    }

    /**
     * Uses LinkedList class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLL(int size, int rotation) {
        if (size <= 0) {
            throw new RuntimeException("size should be greater than 0");
        }
        if (rotation <= 0) {
            throw new RuntimeException("rotation should be greater than 0");
        }
        LinkedList<Integer> line = new LinkedList<Integer>();
        for (int pos = 1; pos <= size; pos++) {
            line.addLast(pos);
            }
        while (size != 1) {
            for (int i = 1; i < rotation; i++) {
                line.addLast(line.removeFirst());
            }
            line.removeFirst();
            size--;
        }
        return line.getFirst();
    }

    /**
     * Uses LinkedList class to find the survivor's position.
     * However, do NOT use the LinkedList as Queue/Deque
     * Instead, use the LinkedList as "List"
     * That means, it uses index value to find and remove a person to be executed in the circle
     *
     * Note: Think carefully about this method!!
     * When in doubt, please visit one of the office hours!!
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLLAt(int size, int rotation) {
        if (size <= 0) {
            throw new RuntimeException("size should be greater than 0");
        }
        if (rotation <= 0) {
            throw new RuntimeException("rotation should be greater than 0");
        }
        LinkedList<Integer> line = new LinkedList<Integer>();
        int index = 0;
        for (int pos = 1; pos <= size; pos++) {
            line.add(pos);
            }
        while (line.size() != 1) {
            index = (index + rotation - 1) % line.size();
            line.remove(index);
        }
        return line.get(0);
    }

}
