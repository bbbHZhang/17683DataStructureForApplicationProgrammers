package hw4;
/**
 * 17683 Data Structures for Application Programmers.
 *
 * Homework Assignment 4
 * HashTable Implementation with linear probing
 *
 * Andrew ID: hanzhan2
 * @author Han Zhang
 */
public class MyHashTable implements MyHTInterface {

    /**
     * The DataItem array of the table.
     */
    private DataItem[] hashArray;
    /**
     * The default initial capicity is 10.
     */
    private static final int INTITIALCAP = 10;
    /**
     * the default load factor is 0.5.
     */
    private static final float LOADFACTOR = 0.5f;

    /**
     * the static default flag for deleted item.
     */
    private static final DataItem DELETED = new DataItem("#DEL#");
    /**
     * integer used to store the number of items inside the hashTable.
     */
    private int size;
    /**
     * integer used to store the number of collision inside the hashTable.
     */
    private int collision;

    /**
     *  Constructor with no initial capacity. Set the array length as initialcap.
     */
    public MyHashTable() {
        hashArray = new DataItem[INTITIALCAP];
        size = 0;
        collision = 0;
    }

    /**
     * Constructor with initial capacity.
     * @param cap Integer given as the initial capacity.
     */
    public MyHashTable(int cap) {
        if (cap <= 0) {
            throw new RuntimeException();
        } else {
            hashArray = new DataItem[cap];
            size = 0;
            collision = 0;
        }
    }

    /**
     * Instead of using String's hashCode, you are to implement your own here.
     * You need to take the table length into your account in this method.
     *
     * In other words, you are to combine the following two steps into one step.
     * 1. converting Object into integer value
     * 2. compress into the table using modular hashing (division method)
     *
     * Helper method to hash a string for English lowercase alphabet and blank,
     * we have 27 total. But, you can assume that blank will not be added into
     * your table. Refer to the instructions for the definition of words.
     *
     * For example, "cats" : 3*27^3 + 1*27^2 + 20*27^1 + 19*27^0 = 60,337
     *
     * But, to make the hash process faster, Horner's method should be applied as follows;
     *
     * var4*n^4 + var3*n^3 + var2*n^2 + var1*n^1 + var0*n^0 can be rewritten as
     * (((var4*n + var3)*n + var2)*n + var1)*n + var0
     *
     * Note: You must use 27 for this homework.
     *
     * However, if you have time, I would encourage you to try with other
     * constant values than 27 and compare the results but it is not required.
     * @param input input string for which the hash value needs to be calculated
     * @return int hash value of the input string
     */
    private int hashFunc(String input) {
        int hashVal = 0;
        for (int i = 0; i < input.length(); i++) {
            int character = input.charAt(i);
            character = character % 96;
            hashVal = ((hashVal * 27) + character) % hashArray.length;
        }
        return hashVal;
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
            //String.matches("[a-z]+")
            for (int i = 0; i < key.length(); i++) {
                if (!((key.charAt(i) >= 'a' && key.charAt(i) <= 'z'))) {
                    validKey = false;
                }
            }
        }
        return validKey;
    }

    /**
     * doubles array length and rehash items whenever the load factor is reached.
     * Note: do not include the number of deleted spaces to check the load factor.
     * Remember that deleted spaces are available for insertion.
     */
    private void rehash() {
        System.out.printf("Rehashing %d items, new length is %d%n", size, nextPrime(hashArray.length * 2));
        size = 0;
        collision = 0;
        DataItem[] tmp = hashArray;
        hashArray = new DataItem[nextPrime(tmp.length * 2)];
        for (DataItem d: tmp) {
            if (d == null || d == DELETED) {
                continue;
            } else {
                int hashVal = hashFunc(d.value);
                boolean tmpCollision = false;
                while (hashArray[hashVal] != null) {
                    if (hashArray[hashVal].value.equals(d.value)) {
                        hashArray[hashVal].frequency++;
                        break;
                    } else {
                        if (hashFunc(hashArray[hashVal].value) == hashFunc(d.value)) {
                            tmpCollision = true;
                        }
                        hashVal = (hashVal + 1) % hashArray.length;
                    }
                }
                if (tmpCollision) {
                    collision++;
                }
                hashArray[hashVal] = new DataItem(d.value);
                hashArray[hashVal].frequency = d.frequency;
                size++;
            }
        }
    }

    /**
     * helper method for calculating prime number.
     * @param current the start point for prime number search
     * @return an prime integer calculated based on the input
     */
    int nextPrime(int current) {
        boolean isPrime = false;
        while (!isPrime) {
            current = current + 1;
            isPrime = true;
            for (int i = 2; i < Math.sqrt(current); i++) {
                if (current % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        return current;
    }

    /**
     * Insert method which takes care of rehashing, size and collision counting.
     * @param value to be inserted into the current hashTable
     */
    @Override
    public void insert(String value) {
        if (!wordValid(value)) {
            return;
        }
        int hashVal = hashFunc(value);
        boolean tmpCollision = false;

        if (hashArray[hashVal] == DELETED) {
            int tmp = hashVal;
            hashVal++;
            int index = 0;
            while (hashArray[hashVal] != null && hashArray[hashVal] != DELETED && index++ < hashArray.length) {
                if (!hashArray[hashVal].value.equals(value) && hashFunc(hashArray[hashVal].value) == hashFunc(value)) {
                    tmpCollision = true;
                }
                hashVal = (hashVal + 1) % hashArray.length;
            }
            hashVal = tmp;
        }

        while (hashArray[hashVal] != null && hashArray[hashVal] != DELETED) {

            if (hashArray[hashVal].value.equals(value)) {
                hashArray[hashVal].frequency++;
                return;
            } else {
                if (hashFunc(hashArray[hashVal].value) == hashFunc(value)) {
                    tmpCollision = true;
                }
                hashVal = (hashVal + 1) % hashArray.length;
            }
        }
        if (tmpCollision) {
            collision++;
        }
        hashArray[hashVal] = new DataItem(value);
        size++;
        if (1.0 * size / hashArray.length > LOADFACTOR) { //if rehash first, the order in array may be not right
            rehash();
        }
    }

    /**
     * Returns the size, number of items, of the table.
     * @return the number of items in the table
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Displays the values of the table.
     * If an index is empty, it shows **
     * If previously existed data item got deleted, then it should show #DEL#
     */
    @Override
    public void display() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i] == DELETED) {
                sb.append(hashArray[i].value).append(" ");
            } else if (hashArray[i] != null) {
                sb.append("[" + hashArray[i].value + ", " + hashArray[i].frequency + "]").append(" ");
            } else {
                sb.append("**").append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * Returns true if value is contained in the table.
     * @param key String key value to search
     * @return true if found, false if not found.
     */
    @Override
    public boolean contains(String key) {
        if (!wordValid(key)) {
            return false;
        }
        int hashVal = hashFunc(key);
        int i = 0;
        while (hashArray[hashVal] != null && i < hashArray.length) {
            i++;
            if (hashArray[hashVal].value.equals(key)) {
                return true;
            } else {
                hashVal++;
                hashVal = hashVal % hashArray.length;
            }
        }
        return false;
    }

    @Override
    public int numOfCollisions() {
        return collision;
    }

    @Override
    public int hashValue(String value) {
        return hashFunc(value);
    }

    @Override
    public int showFrequency(String key) {
        if (!wordValid(key)) {
            return 0;
        }
        if (!contains(key)) {
            return 0;
        }
        int hashVal = hashFunc(key);
        while (!hashArray[hashVal].value.equals(key)) {
            hashVal = (hashVal + 1) % hashArray.length;
        }
        return hashArray[hashVal].frequency;
    }

    /**
     * Removes and returns removed value.
     * @param key String to remove
     * @return value that is removed. If not found, return null
     */
    @Override
    public String remove(String key) {
        if (!wordValid(key)) {
            return null;
        }
        int hashVal = hashFunc(key);
        String tmp = null;
        int i = 0;
        while (hashArray[hashVal] != null && i < hashArray.length) {
            i++;
            if (hashArray[hashVal].value.equals(key)) {
                tmp = hashArray[hashVal].value;
                hashArray[hashVal] = DELETED;
                size--;
                return tmp;
            }
            hashVal = (hashVal + 1) % hashArray.length;
        }
        return null;
    }


    /**
     * private static data item nested class.
     */
    private static class DataItem {
        /**
         * String value.
         */
        private String value;
        /**
         * String value's frequency.
         */
        private int frequency;

        /**
         * DataItem constructor, default frequency is 1.
         * @param v value store in this data item
         */
        DataItem(String v) {
            value = v;
            frequency = 1;
        }
    }

}
