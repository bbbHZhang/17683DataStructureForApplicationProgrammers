package hw6;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
/**
 * Homework 6 Index class that use to build and re-build the Binary Search Tree.
 * @author Han Zhang
 * AndrewID hanzhan2
 *
 */
public class Index {
    /**
     * Build Index binary search tree with a file.
     * @param fileName the name of the file used to build BST
     * @return the built BST
     */
    public BST<Word> buildIndex(String fileName) {
        return buildIndex(fileName, null);
    }

    /**
     * Build Index binary search tree with a file and the given comparator.
     * @param fileName the name of the file used to build BST
     * @param comparator used to compare
     * @return the built BST
     */
    public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
        Scanner scanner = null;
        BST<Word> index = new BST<Word>(comparator);
        try {
            scanner = new Scanner(new File(fileName), "latin1");
            int lineNum = 0;
            //keep read lines if there is still a line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;

                String[] inputs = line.split("\\W");
                for (String word: inputs) {
                    //Test the validation of word
                    if (word != null && word.matches("[a-zA-Z]+")) {
                        if (comparator instanceof IgnoreCase) {
                            //Conversion to lower case if comparator is IgenoreCase
                            Word searchResult = index.search(new Word(word.toLowerCase()));
                            if (searchResult != null) {
                                //Update frequency and line number if the word exist
                                int f = searchResult.getFrequency();
                                searchResult.setFrequency(f + 1);
                                searchResult.addToIndex(lineNum);
                            } else {
                                //Add line number and insert the new word
                                Word w = new Word(word.toLowerCase());
                                w.addToIndex(lineNum);
                                index.insert(w);
                            }
                        } else {
                            //Do nothing if comparator is not IgenoreCase
                            Word searchResult = index.search(new Word(word));
                            if (searchResult != null) {
                                int f = searchResult.getFrequency();
                                searchResult.setFrequency(f + 1);
                                searchResult.addToIndex(lineNum);
                            } else {
                                Word w = new Word(word);
                                w.addToIndex(lineNum);
                                index.insert(w);
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return index;
    }

    /**
     * Build Index binary search tree with a ArrayList and the given comparator.
     * @param list ArrayList used to build BST
     * @param comparator used to compare
     * @return the built BST
     */
    public BST<Word> buildIndex(ArrayList<Word> list, Comparator<Word> comparator) {
        if (list == null) {
            return null;
        }
        BST<Word> index = new BST<Word>(comparator);
        for (Word word: list) {
            //while list has words. Keep get and check
            if (word != null && word.getWord().matches("[a-zA-Z]+")) {
                //only insert if the word doesn't exist
                //do nothing if the word exist
                if (comparator instanceof IgnoreCase) {
                    word.setWord(word.getWord().toLowerCase());
                    index.insert(word);
                } else {
                    index.insert(word);
                }
            }
        }
        return index;
    }

    /**
     * Sort by alpha Method to sort all the node in alpha order.
     * @param tree Tree to be sorted
     * @return ArrayList which stores the sorted result
     */
    public ArrayList<Word> sortByAlpha(BST<Word> tree) {
        /*
         * Even though there should be no ties with regard to words in BST,
         * in the spirit of using what you wrote,
         * use AlphaFreq comparator in this method.
         */
        if (tree.getRoot() == null) {
            return new ArrayList<Word>();
        }
        ArrayList<Word> result = new ArrayList<Word>();
        Iterator<Word> itr = tree.iterator();
        while (itr.hasNext()) {
            result.add(itr.next());
        }
        Collections.sort(result, new AlphaFreq());
        return result;
    }

    /**
     * Sort by frequency Method to sort all the node in frequency order descedening.
     * @param tree Tree to be sorted
     * @return ArrayList which stores the sorted result
     */
    public ArrayList<Word> sortByFrequency(BST<Word> tree) {
        if (tree.getRoot() == null) {
            return new ArrayList<Word>();
        }
        ArrayList<Word> result = new ArrayList<Word>();
        Iterator<Word> itr = tree.iterator();
        while (itr.hasNext()) {
            result.add(itr.next());
        }
        Collections.sort(result, new Frequency());
        return result;
    }

    /**
     * Get all the hightest frequency Nodes of a specific tree.
     * @param tree Tree to be gone through
     * @return ArrayList which stores the result
     */
    public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
        if (tree.getRoot() == null) {
            return new ArrayList<Word>();
        }
        ArrayList<Word> tmp = sortByFrequency(tree);
        ArrayList<Word> result = new ArrayList<Word>();
        //The fisrt would be the one with highest frequency
        result.add(tmp.get(0));
        for (int i = 1; i < tmp.size(); i++) {
            if (tmp.get(i).getFrequency() == tmp.get(0).getFrequency()) {
                result.add(tmp.get(i));
            } else {
                break;
            }
        }
        return result;
    }

}
