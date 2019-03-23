package hw6;
import java.util.Comparator;

/**
 * Homework 6 Comparator Class that use to compare with frequencies.
 * @author Han Zhang
 * AndrewID hanzhan2
 *
 */
public class Frequency implements Comparator<Word> {

    /**
     * Compare with frequencies.
     * @param o1 first word to compare
     * @param o2 second 2ord to compare
     * @return int indicates the order
     */
    @Override
    public int compare(Word o1, Word o2) {
        return Integer.compare(o2.getFrequency(), o1.getFrequency());
    }

}
