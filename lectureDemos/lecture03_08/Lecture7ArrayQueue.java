package lecture03_08;

import java.util.NoSuchElementException;

/**
 * 17683 Data Structures for Application Programmers.
 * Lecture 7 Queue
 * A very simple Queue Class using array
 * Concept of wrapping around using modulus operation (Circular Queue)
 *
 * @author Terry Lee
 * @param <AnyType> type of element objects
 */
public class Lecture7ArrayQueue<AnyType> implements Lecture7QueueInterface<AnyType> {
    /**
     * Constant for default capacity of the underlying array.
     */
    private static final int DEFAULT_CAPACITY = 6;
    /**
     * Array of elements.
     */
    private Object[] elements;
    /**
     * location of front element.
     */
    private int front;
    /**
     * location of back element.
     */
    private int back;
    /**
     * number of items in the queue.
     */
    private int nItems;

    /**
     * Constructs a new queue with default capacity.
     */
    public Lecture7ArrayQueue() {
        elements = new Object[DEFAULT_CAPACITY];
        front = 0;
        back = -1; // similar to top variable in ArrayStack
        nItems = 0;
    }

    /**
     * Enqueues a new item to the back of the queue in O(1).
     * @param item a new item to add
     * @throws RuntimeException indicates that queue is full
     */
    @Override
    public void enqueue(AnyType item) {
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        }
        // back moves up
        back++;
        // need to wrap around
        elements[back % elements.length] = item;
        nItems++;
    }

    /**
     * Gets and deletes an item from the front of the queue in O(1).
     * @return the first item in the queue
     * @throws NoSuchElementException indicates that queue is empty
     */
    @Override
    public AnyType dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // need to wrap around
        int index = front % elements.length;
        @SuppressWarnings("unchecked")
        AnyType item = (AnyType) elements[index];
        // make sure to delete the item
        elements[index] = null;
        // front moves up to the right
        front++;
        nItems--;
        return item;
    }

    /**
     * Gets an item from the front of the queue in O(1) but does not delete it.
     * @return the first item in the queue
     * @throws NoSuchElementException indicates that queue is empty
     */
    @SuppressWarnings("unchecked")
    @Override
    public AnyType peekFront() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (AnyType) elements[front % elements.length];
    }

    /**
     * Checks if the queue is empty or not in O(1).
     * @return true if the queue is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return nItems == 0;
    }

    /**
     * Private helper method to check if queue is full or not.
     * @return true if it is full and false if not
     */
    private boolean isFull() {
        return nItems == elements.length;
    }

    /**
     * A few test cases.
     * @param args arguments
     */
    public static void main(String[] args) {
        Lecture7ArrayQueue<Integer> queue = new Lecture7ArrayQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(3);
        System.out.println(queue.dequeue()); // 1
        System.out.println(queue.peekFront()); // 3
        queue.enqueue(2);
        queue.enqueue(10);
        queue.enqueue(5);
        queue.enqueue(4);
        queue.enqueue(8);
        System.out.println(queue.peekFront()); // 3
        System.out.println(queue.dequeue()); // 3
        System.out.println(queue.peekFront()); // 2
        queue.enqueue(9);
        queue.enqueue(11); // exception (Queue is full)
    }

}
