package lab2;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 17683 Data Structures for Application Programmers.
 * Lab 2 LinkedList (Singly) Operation Implementation
 *
 * @param <AnyType> data type to insert into list
 *
 * Andrew ID: hanzhan2
 * @author Han Zhang
 */
public class LinkedListLab<AnyType> implements Iterable<AnyType> {
    /**
     * head node variable.
     */
    private Node<AnyType> head;

    /**
     * no-arg constructor.
     */
    public LinkedListLab() {
        head = null;
    }

    /**
     * Inserts a new item to the end.
     * @param item data item to be inserted
     */
    public void insert(AnyType item) {
        if (head == null) {
            head = new Node<AnyType>(item, head);
            return;
        }
        Node<AnyType> tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = new Node<AnyType>(item, null);
    }

    /**
     * Finds object that is kth to the last node of linkedlist.
     * @param k kth position to the last. 1 means the last node
     * @return Object that is located at kth to the last
     */
    public AnyType kthToLast(int k) {
        if (head == null) throw new RuntimeException("implement this!");
        Node<AnyType> frontAT = head;
        Node<AnyType> backAT = head;
        int front = 1;
        if (k <= 0) { return null;}
        while (front < k) {
            frontAT = frontAT.next;
            if (frontAT == null) { return null; }
            front++;
        }
        
        while (frontAT.next != null) {
            frontAT = frontAT.next;
            backAT = backAT.next;
        }
        return backAT.data;
    }

    /**
     * Returns a string representation.
     * @return String representation of the list
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Object x : this) {
            result.append(x).append(" ");
        }
        return result.toString();
    }

    /**
     * Iterator implementation.
     * @return Iterator object to go through elements in the list
     */
    @Override
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    /**
     * non-static nested class for Iterator implementation.
     */
    private class LinkedListIterator implements Iterator<AnyType> {
        /**
         * node class to reference to next node.
         */
        private Node<AnyType> nextNode;

        /**
         * no-arg constructor.
         */
        LinkedListIterator() {
            nextNode = head;
        }
        /**
         * Checks whether there is next node or not.
         * @return true if there is or false if not
         */
        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        /**
         * Returns the next node's data.
         * @return AnyType data of the next node
         */
        @Override
        public AnyType next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            AnyType result = nextNode.data;
            nextNode = nextNode.next;
            return result;
        }

    }

    /**
     * Node (static nested class).
     * @param <AnyType> data type of node class
     */
    private static class Node<AnyType> {
        /**
         * data type of node.
         */
        private AnyType data;
        /**
         * reference to next node.
         */
        private Node<AnyType> next;

        /**
         * constructor a new node with data and next node reference.
         * @param newData data element of the node
         * @param newNode next node reference
         */
        Node(AnyType newData, Node<AnyType> newNode) {
            data = newData;
            next = newNode;

        }
    }

    /**
     * A few simple test cases.
     * @param args arguments
     */
    public static void main(String[] args) {
        LinkedListLab<String> theList = new LinkedListLab<String>();
        theList.insert("data");
        theList.insert("strutures");
        theList.insert("rock");
        theList.insert("the");
        theList.insert("world");
        theList.insert("way");
        theList.insert("to");
        theList.insert("go");
        theList.insert("dude");
        System.out.println("values:" + theList);
        // should print null
        System.out.println("0:" + theList.kthToLast(0));
        // should print "dude"
        System.out.println("1:" + theList.kthToLast(1));
        // should print "go"
        System.out.println("2:" + theList.kthToLast(2));
        // should print "to"
        System.out.println("3:" + theList.kthToLast(3));
        // should print data
        System.out.println("9:" + theList.kthToLast(9));
        // should print null
        System.out.println("10:" + theList.kthToLast(10));
    }

}
