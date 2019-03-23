package hw1;
/** Classname MyArray.
 * Data Structure for Application Programmer Homework1.
 * Date 2018-Oct-29
 * andrewID hanzhan2
 * @author Han Zhang
 */
public class MyArray {
    /**
     * Constructor.
     * Initialize myArray as a String Array whose length is 0.
     */
    public MyArray() {
        myArray = new String[0];
    }

    /**
     * Constructor.
     * @param initialCapacity (required) length of initial String array.
     */
    public MyArray(int initialCapacity) {
        myArray = new String[initialCapacity];
    }
    /** Use myArray as underline String[]. */
    private String[] myArray;
    /** count the number of valid strings in myArray. */
    private int sizeCount = 0;

    /**
     * Accessor method of myArray.
     * @return myArray
     */
    public String[] getMyArray() {
        return myArray;
    }

    /**
     * Accessor method of sizeCount.
     * @return sizeCount
     */
    public int getSizeCount() {
        return sizeCount;
    }

    /**
     * Validate input string, double array length if necessary,
     * and add String key to myArray.
     * @param key String that should contain and should only contain char 'a..zA..Z'
     */
    public void add(String key) {
        String[] temp;
        boolean validKey = true;
        if (key == null || key.length() == 0) {  // ignore empty input
            validKey = false;
        } else {                                // validate input String
            for (int i = 0; i < key.length(); i++) {
                if (!((key.charAt(i) >= 'a' && key.charAt(i) <= 'z') || ((key.charAt(i) >= 'A' && key.charAt(i) <= 'Z')))) {
                    validKey = false;
                }
            }
            if (validKey) {                    // only add valid input string
                if (myArray.length == 0) {
                    temp = new String[1];
                    myArray = temp;
                } else if (myArray[myArray.length - 1] != null) {
                    temp = new String[2 * myArray.length];
                    System.arraycopy(myArray, 0, temp, 0, myArray.length);
                    myArray = temp;
                }                             //end of array length adjustment
                myArray[sizeCount] = key;
                sizeCount++;
            }
        }
    }
    /**
     * Validate input string and search for valid input.
     * @param key input string for validating and searching
     * @return <code>true</code> only if key is in myArray
     */
    public boolean search(String key) {
        boolean searchResult = false;
        if (key == null || key.length() == 0) {  // ignore empty input
            searchResult = false;
        } else {                                 // validate input String
            boolean validKey = true;
            for (int i = 0; i < key.length(); i++) {
                if (!((key.charAt(i) >= 'a' && key.charAt(i) <= 'z') || ((key.charAt(i) >= 'A' && key.charAt(i) <= 'Z')))) {
                    validKey = false;
                }
            }
            if (validKey) {                      // only search for valid string
                for (int a = 1; a < sizeCount; a++) {
                    if (myArray[a].equals(key)) {
                        searchResult = true;
                        break;
                    }
                }
            }
        }
        return searchResult;
    }
    /**
     * Get the true size of myArray.
     * @return sizeCount which is the number of all non null strings
     */
    public int size() {
        return sizeCount;
    }

    /**
     * Get the capacity of myArray.
     * @return <code>myArray.length</code> which is the length of myArray
     */
    public int getCapacity() {
        return myArray.length;
    }
    /** Show all strings in myArray and separate using space. */
    public void display() {
        for (String s : myArray) {
            if (!(s == null)) {      // only display non-null strings
                System.out.printf("%s ", s);
            }
        }
        System.out.println();
    }
    /**
     * Remove specific string with index.
     * @param index which starts from 0
     */
    public void remove(int index) {
        if (index < sizeCount) {
            System.arraycopy(myArray, index + 1, myArray, index, myArray.length - 1 - index);
            myArray[myArray.length - 1] = null;
            sizeCount--;
        }
    }

    /** Check and remove all duplicate strings in myArray. */
    public void removeDups() {
        for (int a = 1; a < sizeCount; a++) {
            for (int b = 0; b < a; b++) {
                if (myArray[a] != null && myArray[a].equals(myArray[b])) {
                    remove(a);
                    a--;
                }
            }
        }
    }

}
