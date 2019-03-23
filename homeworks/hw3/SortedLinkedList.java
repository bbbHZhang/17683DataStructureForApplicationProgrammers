package hw3;
/**
 * DSAP Homework3.
 * AndrewID: hanzhan2.
 * @author Han Zhang
 *
 */
public class SortedLinkedList implements MyListInterface {
    /**
     * Node (static nested class).
     */
    private static class Node {
        /**
         * element data of node.
         */
        private String data;
        /**
         * reference to next node.
         */
        private Node next;

        /**
         * constructor a new node with data and next node reference.
         * @param newData string element of the node
         * @param newNode next node reference
         */
        Node(String newData, Node newNode) {
            data = newData;
            next = newNode;

        }
    }

    /**
     * head node variable.
     */
    private Node head;

    /**
     * no-arg constructor.
     */
    public SortedLinkedList() {
        head = null;
    }

    /**
     * Take unsorted String Array to build a new Sorted LinkedList.
     * @param unsorted The unsorted String Array.
     */
    public SortedLinkedList(String[] unsorted) {
        if (unsorted.length <= 0) {
            head = null;
        }
        addAll(unsorted, 0);
    }

    /**
     * Helper method of non-default constructor.
     * Recursive method. add() each string until the end of unsorted.
     * Base case is stop adding new item and return when all elements are added.
     * Recursive case is adding next string and calling itself.
     *
     * @param unsorted The unsorted String Array.
     * @param pos The index of unsorted to add element.
     */
    private void addAll(String[] unsorted, int pos) {
        if (pos < unsorted.length) {
            add(unsorted[pos]);
            addAll(unsorted, pos + 1);
        }
        return;
    }

    /**
     * Helper method to check the validation of String.
     * @param key String to be checked.
     * @return True/False. True for valid string.
     */
    private boolean wordValid(String key) {
        boolean validKey = true;
        if (key == null || key.length() == 0) {  // ignore empty input
            validKey = false;
        } else { // validate key String
            for (int i = 0; i < key.length(); i++) {
                if (!((key.charAt(i) >= 'a' && key.charAt(i) <= 'z') || ((key.charAt(i) >= 'A' && key.charAt(i) <= 'Z')))) {
                    validKey = false;
                }
            }
        }
        return validKey;
    }

    /**
     * Inserts a new String.
     * Do not throw exceptions if invalid word is added (Gently ignore it).
     * No duplicates allowed and maintain the order in ascending order.
     *
     * @param value String to be added.
     */
    @Override
    public void add(String value) {     //ignore the invalid word
        if (!wordValid(value)) {
            return;
        }
        //check whether head is null or not
        if (head == null) {
            head = new Node(value, null);
            return;
        }
        //check duplicates
        if (contains(value)) {
            return;
        }
        if (head.data.compareTo(value) >= 0) {
            head = new Node(value, head);
        } else {
            addOne(head, value);
        }
    }

    /**
     * Helper method of add().Recursive method.
     * Two base cases.
     * First is when next node is null which means the end of current Linked List, add the new one to the end;
     * Second is when cur's next node's data is bigger than value, add the new one before current.
     * Recursive case: If the cur is not null and small or equal to value, check the next Node, recursion.
     *
     * @param prev The staring Node to compare with value.
     * @param value The string we want to added.
     */
    private void addOne(Node prev, String value) {
        if (prev.next == null) {
            prev.next = new Node(value, null);
            return;
        } else {
            if (prev.next.data.compareTo(value) > 0) {
                prev.next = new Node(value, prev.next);
                return;
            }
            addOne(prev.next, value);
        }
    }

    /**
     * Checks the size (number of data items) of the list.
     *
     * @return the size of the list
     */
    @Override
    public int size() {
        if (head == null) {
            return 0;
        } else {
            return getSize(head);
        }
    }

    /**
     * Helper method of size(). Recursive method.
     * Base case is when arriving the end of the LinkedList, return 1
     * Recursive case is when start still has next, recurse it.
     *
     * @param start passes the head to be checked.
     * @return Base case returns 1; recursive case returns 1 + getSize(nextNode)
     */
    private int getSize(Node start) {
        if (start.next != null) {
            return (1 + getSize(start.next));
        } else {
            return 1;
        }
    }

    /**
     * Displays the values of the list.
     */
    @Override
    public void display() {
        if (isEmpty()) {
            System.out.println("[]");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            displayAll(sb, head);
        }
    }

    /**
     * Helper method of display(). Recursive method.
     * Base case is when current node has no next, append the last one and "]". Print them out
     * Recursive case is when current node has next, append current and call it again with next node.
     *
     * @param sb StringBuilder used to store all the strings.
     * @param start The start node.
     */
    private void displayAll(StringBuilder sb, Node start) {
        if (start.next == null) {
            sb.append(start.data).append("]");
            System.out.println(sb.toString());
        } else {
            sb.append(start.data).append(", ");
            start = start.next;
            displayAll(sb, start);
        }
    }

    /**
     * Returns true if the key value is in the list.
     *
     * @param key String key to search
     * @return true if found, false if not found
     */
    @Override
    public boolean contains(String key) {
        if (!wordValid(key)) {
            return false;
        }
        if (isEmpty()) {
            return false;
        } else {
            return checkContain(key, head);
        }
    }

    /**
     * Help method of contains(). Recursive method.
     * Two base cases. First is when LinkedList contains key, return true.
     * Second is when arriving the end of LinkedList, no key found, return false.
     * Recursive case is when current node is not the end and is not equals to key.
     *
     * @param key String to be checked.
     * @param start The starting node .
     * @return True/False. Only return True when current LinkedList contains key.
     */
    private boolean checkContain(String key, Node start) {
        if (start.data.equals(key)) {
            return true;
        } else if (start.next != null) {
            start = start.next;
            return checkContain(key, start);
        } else {
            return false;
        }
    }

    /**
     * Returns true is the list is empty.
     * @return true if it is empty, false if it is not empty
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Removes and returns the first String object of the list.
     * @return String object that is removed. If the list is empty, returns null
     */
    @Override
    public String removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            String result = head.data;
            head = head.next;
            return result;
        }
    }

    /**
     * Removes and returns String object at the specified index.
     * @param index index to remove String object
     * @return String object that is removed
     * @throws RuntimeException for invalid index value (index < 0 || index >= size())
     */
    @Override
    public String removeAt(int index) {
        if (index < 0 || index >= size()) {
            throw new RuntimeException();
        }
        return removeAtIndex(null, head, index);
    }

    /**
     * Helper method of remvoeAt(). Recursive method.
     * Base cases, when index == 0, reaching the position we want to remove.
     * Recursive case: check next nodes pair
     *
     * @param prev The previous node, default value is null.
     * @param start The current node, default value is head.
     * @param index The removal index.
     * @return The string of the node that is going to be removed.
     */
    private String removeAtIndex(Node prev, Node start, int index) {
        if (index == 0) {
            if (prev == null) {
                return removeFirst();
            } else {
                prev.next = start.next;
                return start.data;
            }
        } else {
            prev = start;
            start = start.next;
            return removeAtIndex(prev, start, index - 1);
        }
    }
}
