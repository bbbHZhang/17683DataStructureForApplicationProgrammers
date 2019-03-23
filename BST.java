import java.util.Comparator;
import java.util.Iterator;

public class BST<T extends Comparable<T>> implements Iterable<T>, BSTInterface<T> {
    private Node<T> root;
    private Comparator<T> comparator;

    public BST() {
        this(null);
    }

    public BST(Comparator<T> comp) {
        comparator = comp;
        root = null;
    }

    public Comparator<T> comparator() {
        return comparator;
    }

    public T getRoot() {
        // TODO implement this
    }

    public int getHeight() {
        // TODO implement this recursively
    }

    public int getNumberOfNodes() {
        // TODO implement this recursively
    }

    @Override
    public T search(T toSearch) {
        // TODO implement this recursively
    }

    @Override
    public void insert(T toInsert) {
        // TODO implement this recursively
    }

    @Override
    public Iterator<T> iterator() {
        // TODO implement this
    }

    private static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        Node(T d) {
            this(d, null, null);
        }

        Node(T d, Node<T> l, Node<T> r) {
            data = d;
            left = l;
            right = r;
        }
    }

}
