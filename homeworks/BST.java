
public class BST {
    Node root;
    
    public boolean search() {
        return false;
    }

    public void insert() {
        
    }
    public Node delete(String key, Node x) {
        if (x == null) {
            return null;
        }

        int compare = x.data.compareTo(key);
        if (compare < 0) {
            x.right = delete(key, x.right);
        } else if (compare > 0) {
            x.left = delete(key, x.left);
        } else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node t = x;
            x = min(x.right);
            x.right = deleteMin(x.right);
            x.left = t.left;
            
        }
        return x;
        
    }
    
    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return x;
    }
    
    private Node min(Node x) {
        //to find successor
        if (x.left == null) {
            return x;
        }
        x = min(x.left);
        return x;
    }
    
    public void deleted(String key) {
        if (root == null) {
            //this bst is emptey
            return;
        }
        
        Node parent = root;
        Node current = root;
        boolean isLeft = false;
        
        //find the key first
        while (!current.data.equals(key)) {
            //terry's have no current != null
            parent = current;
            if (current.data.compareTo(key) < 0) {
                isLeft = false;
                current = current.right;
            } else {
                isLeft = true;
                current = current.left;
            }
            //not found
            if (current == null) {
                return;
            }
        }

        
        //found
        if(current.left == null && current.right == null) {
            //current is a leaf
            if (current == root ) { //don't forget to deal with root
                root = null;
            } else if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.left == null) {
            //current has only right child
            if (current == root) {
                root = root.right;
            }
            if (isLeft) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.right == null) {
            //current has only left child
            if (current == root ) {
                root = current.left;
            }
            if (isLeft) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else {
            //current has 2 children
            Node successor = successor(current);
            successor.left = current.left;
            if (current == root) {
                root = successor;
            }
            if (isLeft) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            
            
            
        }
        
    }
    
    private Node successor(Node toDelete) {
        Node sParent = toDelete;
        Node cur = toDelete.right;
        if (cur.left == null) {
            //if right one is successor; return it
            return cur;
        }
        
        while (cur.left != null) {
            sParent = cur;
            cur = cur.left;
        }//now cur is the successor
        
        //put successor's right to sParent's left
        sParent.left = cur.right;
        cur.right = toDelete.right;
        
        return cur;
    }
    

    private class Node{
        String data;
        Node left;
        Node right;
        Node(String data){
            this(data, null, null);
        }
        
        Node(String data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
            
        }
    }
}
