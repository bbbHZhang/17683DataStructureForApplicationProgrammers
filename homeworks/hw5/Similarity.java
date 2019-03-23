package hw5;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Homework 5 for Data Structure for Application Programmers.
 * This class is used to store the unique words and their frequencies occurred,
 * and to calculate the distance between inside map and another map.
 *
 * AndrewID: hanzhan2
 * @author Han Zhang
 *
 */
public class Similarity {
    /**
     * Private BigInteger that is used to count the number of words that have been read.
     */
    private BigInteger numOfWords = BigInteger.ZERO;
    /**
     * Private integer that used to count number of lines that have been read.
     */
    private int numOfLines;
    /**
     * Private HashMap used to store all the unique words and their frequencies.
     */
    private Map<String, BigInteger> similarityMap = new HashMap<String, BigInteger>();

    /**
     * Constructor used to read a given string.
     * Change words to lower cases.
     * Put all unique word and add frequencies when necessary
     * @param string The string to be read.
     */
    public Similarity(String string) {
        if (string == null || string.length() == 0) {
            return;
        }
        String[] strings = string.split("\\W");
        for (String word: strings) {
            if (!word.matches("[a-zA-Z]+")) {
                continue;
            }
            word = word.toLowerCase();
            numOfWords = numOfWords.add(BigInteger.ONE);
            if (similarityMap.containsKey(word)) {
                similarityMap.put(word, similarityMap.get(word).add(BigInteger.ONE));
            } else {
                similarityMap.put(word, BigInteger.ONE);
            }
        }
    }

    /**
     * Constructor used to read file word by word and line by line.
     * Change words to lower cases.
     * Put all unique word and add frequencies when necessary
     * @param file A File object to be read.
     */
    public Similarity(File file) {
        if (file == null) {
            return;
        }
        Scanner scanner = null;
        try {
            scanner = new Scanner(file, "latin1");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // is it necessary to test?
                numOfLines++;
                if (line.length() == 0) {
                    continue;
                }
                String[] inputs = line.split("\\W");
                for (String word: inputs) {
                    if (word.matches("[a-zA-Z]+")) {
                        word = word.toLowerCase();
                        numOfWords = numOfWords.add(BigInteger.ONE);
                        if (similarityMap.containsKey(word)) {
                            similarityMap.put(word, similarityMap.get(word).add(BigInteger.ONE));
                        } else {
                            similarityMap.put(word, BigInteger.ONE);
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
    }

    /**
     * Displays all words and frequencies of the current inside map.
     * @return A string that used Map.toString() method.
     */
    public String toString() {
        return similarityMap.toString();
    }

    /**
     * Calculate the euclidean norm of current inside map.
     * @return A double represents the sum of squares of all frequencies.
     */
    public double euclideanNorm() {
        BigInteger sum = BigInteger.ZERO;
        for (BigInteger bi: similarityMap.values()) {
            sum = sum.add(bi.pow(2));
        }
        return Math.sqrt(sum.doubleValue());
    }
    /**
     * Calculate dot production of two maps.
     * Multiple the frequencies of all the overlapped keys and sum them up.
     * @param map Another map is used to calculate dot production wiht current map.
     * @return A double represents the dot production of two maps.
     * Return 0 if any of the two maps is empty.
     */
    public double dotProduct(Map<String, BigInteger> map) {
        //Collection FrameWork has isEmpty() method
        //Return 0 if any of the two maps is empty
        if (map == null || map.isEmpty()) {
            return 0;
        }
        if (similarityMap == null || similarityMap.isEmpty()) {
            return 0;
        }
        BigInteger result = BigInteger.ZERO;
        if (map.size() > similarityMap.size()) {
            for (String s: similarityMap.keySet()) {
                if (map.containsKey(s)) {
                    result = result.add(map.get(s).multiply(similarityMap.get(s)));
                }
            }
        } else {
            for (String s: map.keySet()) {
                if (similarityMap.containsKey(s)) {
                    result = result.add(map.get(s).multiply(similarityMap.get(s)));
                }
            }
        }
        return result.doubleValue();
    }
    /**
     * The method used to calculate the distance between two map.
     *
     * @param map will be used to compare with current inside map.
     * @return A double indicates their distance.
     * Return PI/2 if any of the map is empty; 0 if the two maps are the same;
     * Otherwise, return the acos of dot production and the product of their euclidean norms.
     */
    public double distance(Map<String, BigInteger> map) {
        if (map == null || map.isEmpty()) {
            return Math.PI / 2;
        }
        if (similarityMap == null || similarityMap.isEmpty()) {
            return Math.PI / 2;
        }
        double dotP = dotProduct(map);
        BigInteger sum = BigInteger.ZERO;
        for (BigInteger l: map.values()) {
            sum = sum.add(l.pow(2));
        }
        //return 0 if two maps are the same
        if (map.equals(similarityMap)) {
            return 0;
        }
        return Math.acos(dotP / (euclideanNorm() * Math.sqrt(sum.doubleValue())));
    }

    /**
     * Helper method used to call getNumOfLines() method to get number of words.
     * @return the integer that represents the number of lines.
     */
    public int numOfLines() {
        return numOfLines;
    }
    /**
     * Helper method used to call getNumOfWords() to get number of words.
     * @return BigInteger represents the number of words have been insert.
     */
    public BigInteger numOfWords() {
        return numOfWords;
    }
    /**
     * Helper method to get numbers of words that are not duplicated using size() method of map.
     * @return the size of the inside map.
     */
    public int numOfWordsNoDups() {
        return similarityMap.size();
    }
    /**
     * Helper method to call getSimilarityMap().
     * @return the inside map.
     */
    public Map<String, BigInteger> getMap() {
        Map<String, BigInteger> tmp = new HashMap<String, BigInteger>();
        for (Map.Entry<String, BigInteger> entry: similarityMap.entrySet()) {
            tmp.put(entry.getKey(), entry.getValue());
        }
        return tmp;
    }
}
