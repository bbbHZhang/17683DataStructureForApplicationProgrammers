import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Huffman {
    TreeMap<Character, Integer> freq = new TreeMap<Character, Integer>();
    private static final char FLAG = '#';

    public static void main(String[] args) {
        Huffman h = new Huffman();
        String input = "abbcccddddeeeeeffffff";
        Node root = h.buildFreqTree(input);
        for (Entry<Character, Integer> e: h.freq.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
//        StringBuilder sb = new StringBuilder();
        String s = "";
        if (root.isLeaf()) {
            
        }
        h.printHuffman(root, s);
        
    }

    public void printHuffman(Node root, String s) {
        if (root.left == null && root.right == null
                && Character.isLetter(root.ch)) { 
            // c is the character in the node 
            //            System.out.println(root.ch + ":" + s); 

            System.out.println(root.ch + s);
            return;
        } 

        // if we go to left then add "0" to the code. 
        // if we go to the right add"1" to the code. 

        // recursive calls for left and 
        // right sub-tree of the generated tree. 
        printHuffman(root.left, s + "0"); 
        printHuffman(root.right, s + "1"); 
    } 


    public Node buildFreqTree (String input) {
        char[] chars = input.toCharArray();

        //put all characters into hash map
        for (char c:chars) {
            if (freq.containsKey(c)) {
                freq.put(c, 1 + freq.get(c).intValue());
            } else {
                freq.put(c, 1);
            }
        }
        PriorityQueue<Node> pq = new PriorityQueue<Node>();

        for (Entry<Character, Integer> e: freq.entrySet()) {
            pq.add(new Node(e.getKey(), e.getValue(), null, null));
        }

        System.out.println(pq.peek().ch);
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            System.out.println("left " + left.toString() + " right " + right.toString());
            pq.add(new Node(FLAG, left.freq + right.freq, left, right));
        }
        System.out.println(pq.peek().ch);
        return pq.poll();


    }

    private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        // is the node a leaf node?
        private boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
        
        @Override
        public String toString() {
            return "[ " + ch + ", " + freq + " ]";
            
        }
    }

}
