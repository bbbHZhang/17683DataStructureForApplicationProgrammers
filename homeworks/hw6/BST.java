package hw6;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;
/**
 * Homework 6 BST Class used to create and manage the BST.
 * @author Han Zhang
 * AndrewID hanzhan2
 *
 * @param <T> Type T
 */
public class BST<T extends Comparable<T>> implements Iterable<T>, BSTInterface<T> {
    /**
     * Private Node<T> to store root.
     */
    private Node<T> root;
    /**
     * Private Comparator<T> to store comparator.
     */
    private Comparator<T> comparator;

    /**
     * The default constructor to build a tree without comparator.
     */
    public BST() {
        this(null);
    }

    /**
     * Constructor to build a tree with the given comparator.
     * @param comp Comparator used to compare.
     */
    public BST(Comparator<T> comp) {
        comparator = comp;
        root = null;
    }

    /**
     * Return the current comparator.
     * @return Comparator<T> Current comparator used in the class.
     */
    public Comparator<T> comparator() {
        return comparator;
    }

    /**
     * Getter of root.
     * @return The root.
     */
    public T getRoot() {
        if (root == null) {
            return null;
        }
        return root.data;
    }

    /**
     * Getter of height.
     * @return int of the height.
     */
    public int getHeight() {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        return getHeightHelper(root);
    }

    /**
     * The helper method of getHeight() to get height recursive.
     * Base case: current == null which means the end of one brunch.
     * Recursive case: current is not null so add 1, go both left and right and return the max.
     * @param current The node to be compared.
     * @return the int
     */
    private int getHeightHelper(Node<T> current) {
        //base case, return 0 if current is null.
        if (current == null) {
            return -1;
        }
        //recursive case
        return 1 + Math.max(getHeightHelper(current.left), getHeightHelper(current.right));

    }

    /**
     * Getter of number of nodes.
     * @return int, the total of the nodes in BST
     */
    public int getNumberOfNodes() {
        if (root == null) {
            return 0;
        }
        return getNumOfNodesHelper(root);
    }

    /**
     * The helper method of getNumberOfNodes() to get nodes' number recursive.
     * Base case: when current == null, return 0, which means the end of one brunch.;
     * Recursive case: current is not null so add 1, go both left and right
     * @param current The node to be compared.
     * @return the int
     */
    private int getNumOfNodesHelper(Node<T> current) {
        //base case
        if (current == null) {
            return 0;
        }
        //recursive case
        return 1 + getNumOfNodesHelper(current.left) + getNumOfNodesHelper(current.right);
    }

    /**
     * Search method used to search.
     * @param toSearch to Search
     * @return T Null if not found.
     */
    @Override
    public T search(T toSearch) {
        if (toSearch == null) {
            return null;
        }
        if (root == null) {
            return null;
        }
        return searchHelper(root, toSearch);
    }

    /**
     * search helper method to search recursively.
     * Base case
     * @param current T is passed to the method
     * @param toSearch T to be searched
     * @return T null if not found or return the found T
     */
    private T searchHelper(Node<T> current, T toSearch) {
        //Base case 1: not found -- return null
        if (current == null) {
            return null;
        }

        //calculate compare value based on different comparator situation
        int compareVal = 0;
        if (comparator == null) {
            compareVal = toSearch.compareTo(current.data);
        } else {
            compareVal = comparator.compare(toSearch, current.data);
        }

        //Base case 2: find and return;
        if (compareVal == 0) {
            return current.data;
        }
        //Recursive case: go left if toSearch is smaller, go right otherwise
        if (compareVal < 0) {
            return searchHelper(current.left, toSearch);
        } else {
            return searchHelper(current.right, toSearch);
        }

    }


    /**
     * Insert method.
     * @param toInsert T to be inserted
     */
    @Override
    public void insert(T toInsert) {
        if (toInsert == null) {
            return;
        }
        if (root == null) {
            root = new Node<T>(toInsert);
            return;
        }
        insertHelper(root, toInsert);
    }

    /**
     * Insert helper method to insert recursively.
     * Base case 1: if reach the end of branch, insert.
     * Base case 2: if toInsert exists, do nothing.
     * Recursive case: go left if toInsert is smaller, go right otherwise
     * @param current Current Node<T>
     * @param toInsert T to be inserted
     */
    private void insertHelper(Node<T> current, T toInsert) {
        if (current == null) {
            return;
        }
        //calculate compare value based on comparator
        int compareVal = 0;
        if (comparator == null) {
            compareVal = toInsert.compareTo(current.data);
        } else {
            compareVal = comparator.compare(toInsert, current.data);
        }

        Node<T> parent = current;
        if (compareVal < 0) {
            current = current.left;
            if (current == null) {
                parent.left = new Node<T>(toInsert);
                return;
            } else {
                insertHelper(current, toInsert);
            }
        } else if (compareVal > 0) {
            current = parent.right;
            if (current == null) {
                parent.right = new Node<T>(toInsert);
                return;
            } else {
                insertHelper(current, toInsert);
            }
        } else {
            return;
        }
    }


    /**
     * Nested private NewIterator class.
     */
    private class NewIterator implements Iterator<T> {
        /**
         * Stack<Node<T>> that used to store all the node to realize iterator iteratively.
         */
        private Stack<Node<T>> nodes = new Stack<Node<T>>();
        /**
         * Node<T> current started from root.
         */
        private Node<T> current = root;

        /**
         * Constructor.
         * Put all nodes on the left side into stack
         */
        NewIterator() {
            if (root == null) {
                return;
            }
            while (current != null) {
                nodes.push(current);
                current = current.left;
            }
        }

        /**
         * hasNext() method used to check next.
         * @return boolean True if stack is not empty
         */
        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        /**
         * next() method to get next T.
         * Pop and check whether the new first has right node every time,
         * If yes, add the right node and go to the leftest node.
         *
         * @return T T of next Node
         */
        @Override
        public T next() {
            current = nodes.pop();
            Node<T> tmp = current;
            current = current.right;
            while (current != null) {
                nodes.push(current);
                current = current.left;
            }
            return tmp.data;
        }
    }

    /**
     * Iterator method of BST.
     * @return Iterator<T>
     */
    @Override
    public Iterator<T> iterator() {
        return new NewIterator();
    }

    /**
     * static nested Node class for Node.
     *
     * @param <T> T data of the node
     */
    private static class Node<T> {
        /**
         * Key data T.
         */
        private T data;
        /**
         * references to left children nodes.
         */
        private Node<T> left;
        /**
         * references to right children nodes.
         */
        private Node<T> right;

        /**
         * Constructor Node.
         * @param d save as data
         */
        Node(T d) {
            this(d, null, null);
        }

        /**
         * Constructs a new node with T and left right children.
         * @param d T
         * @param l left Node
         * @param r right Node
         */
        Node(T d, Node<T> l, Node<T> r) {
            data = d;
            left = l;
            right = r;
        }
    }

}
