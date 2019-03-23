/**
 * 17683 Data Structures for Application Programmers.
 *
 * Lab 6. Comparing BST with Ordered Array and Linked List
 *
 * Very Simple Binary Search Tree Implementation
 * @author Terry Lee
 */
public class BST implements BSTInterface {
    /**
     * Root node.
     */
    private Node root;

    /**
     * no-arg constructor that set root to null.
     */
    public BST() {
        root = null;
    }

    /**
     * Finds key in the tree.
     * @param key to find
     * @return boolean value (true when found)
     */
    @Override
    public boolean find(int key) {
        // tree is empty
        if (root == null) {
            return false;
        }

        Node curr = root;
        // while not found
        while (curr.key != key) {
            if (curr.key < key) {
                // go right
                curr = curr.right;
            } else {
                // go left
                curr = curr.left;
            }
            // not found
            if (curr == null) {
                return false;
            }
        }
        // found
        return true;
    }

    /**
     * Inserts a new key into the tree.
     * @param key key to add
     */
    @Override
    public void insert(int key) {
        Node newNode = new Node(key);
        // empty tree
        if (root == null) {
            root = newNode;
            return;
        }

        Node parent = root;
        Node curr = root;
        while (true) {
            // no duplicates allowed
            if (curr.key == key) {
                return;
            }
            parent = curr;
            if (curr.key < key) {
                // go right
                curr = curr.right;
                if (curr == null) {
                    // found a spot
                    parent.right = newNode;
                    return;
                }
            } else {
                // go left
                curr = curr.left;
                if (curr == null) {
                    // found a spot
                    parent.left = newNode;
                    return;
                }
            } // end of if-else to go right or left
        } // end of while
    } // end of insert method

    /**
     * Deletes a key (node) from the tree.
     * @param key key to delete
     */
    @Override
    public void delete(int key) {
        // the tree is empty
        if (root == null) {
            return;
        }

        Node parent = root;
        Node curr = root;
        boolean isLeftChild = true; // flag to check left child

        while (curr.key != key) {
            parent = curr;
            if (curr.key < key) {
                // go right
                isLeftChild = false;
                curr = curr.right;
            } else {
                isLeftChild = true;
                curr = curr.left;
            }

            // case 1: not found
            if (curr == null) {
                return;
            }
        }

        if (curr.left == null && curr.right == null) {
            // case 2: leaf
            if (curr == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (curr.right == null) {
            // case 3: no right child
            if (curr == root) {
                root = curr.left;
            } else if (isLeftChild) {
                parent.left = curr.left;
            } else {
                parent.right = curr.left;
            }
        } else if (curr.left == null) {
            // case 3: no left child
            if (curr == root) {
                root = curr.right;
            } else if (isLeftChild) {
                parent.left = curr.right;
            } else {
                parent.right = curr.right;
            }
        } else {
            // case 4: with two children
            Node successor = getSuccessor(curr);

            if (curr == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }

            // need to take care of final connection with curr's left
            successor.left = curr.left;
        }

    }

    /**
     * Traverses the tree in an ascending order based on keys.
     */
    @Override
    public void traverse() {
        StringBuilder sb = new StringBuilder();
        inOrderHelper(root, sb);
        System.out.println(sb);
    }

    /**
     * Recursive method to traverse the tree using inorder.
     * @param toVisit the node to visit
     */
    private void inOrderHelper(Node toVisit, StringBuilder sb) {
        if (toVisit != null) {
            inOrderHelper(toVisit.left, sb);
            sb.append(toVisit.key).append(" ");
            inOrderHelper(toVisit.right, sb);
        }
    }

    /**
     * helper method to find the successor the the delNode.
     * This tries to find the smallest value of the right subtree of the delNode.
     * It goes down to the far left node of the right subtree.
     * @param keyNode the node for which the search to perform
     * @return the successor node
     */
    private Node getSuccessor(Node keyNode) {
        Node successorParent = keyNode;
        Node successor = keyNode;
        Node curr = keyNode.right;

        // move to left as far as possible in the right subtree
        while (curr != null) {
            successorParent = successor;
            successor = curr;
            curr = curr.left;
        }

        /*
         * if successor is not the right child of the node to be deleted Then
         * need to take care of two connections
         */
        if (successor != keyNode.right) {
            successorParent.left = successor.right;
            successor.right = keyNode.right;
        }

        return successor;
    }

    /**
     * Checks whether the tree is empty or not.
     * @return true if empty and false if not
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Private static nested class for node.
     */
    private static class Node {
        /**
         * int key.
         */
        private int key;
        /**
         * referenced to left and right nodes.
         */
        private Node left, right;

        /**
         * Constructor with key.
         * @param k int key
         */
        Node(int k) {
            key = k;
        }
    }

}
