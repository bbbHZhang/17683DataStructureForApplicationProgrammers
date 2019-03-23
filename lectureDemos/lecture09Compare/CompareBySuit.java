package lecture09Compare;

import java.util.Comparator;

/**
 * 17683 Data Structures for Application Programmers.
 * Lecture 9 Sorting in Java
 *
 * Simple Card class with Comparable and Comparator
 * @author Terry Lee
 */
public class CompareBySuit implements Comparator<Card> {

    /**
     * Implementation of compare method to compare card objects by suit.
     * @return positive, 0 or negative values
     */
    @Override
    public int compare(Card x, Card y) {
        return x.getSuit().compareTo(y.getSuit());
    }

}
