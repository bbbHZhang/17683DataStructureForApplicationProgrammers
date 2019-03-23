package hw3;
/**
 * 17683 Data Structures for Applications Programmers.
 *
 * Homework 3 SortedLinkedList Implementation with Recursion
 *
 * Basic List interface (Do not change this interface)
 *
 * @author Terry Lee
 */
public interface MyListInterface {
    /**
     * Inserts a new String.
     * Do not throw exceptions if invalid word is added (Gently ignore it).
     * No duplicates allowed and maintain the order in ascending order.
     * @param value String to be added.
     */
    void add(String value);

    /**
     * Checks the size (number of data items) of the list.
     * @return the size of the list
     */
    int size();

    /**
     * Displays the values of the list.
     */
    void display();

    /**
     * Returns true if the key value is in the list.
     * @param key String key to search
     * @return true if found, false if not found
     */
    boolean contains(String key);

    /**
     * Returns true is the list is empty.
     * @return true if it is empty, false if it is not empty
     */
    boolean isEmpty();

    /**
     * Removes and returns the first String object of the list.
     * @return String object that is removed. If the list is empty, returns null
     */
    String removeFirst();

    /**
     * Removes and returns String object at the specified index.
     * @param index index to remove String object
     * @return String object that is removed
     * @throws RuntimeException for invalid index value (index < 0 || index >= size())
     */
    String removeAt(int index);

}
