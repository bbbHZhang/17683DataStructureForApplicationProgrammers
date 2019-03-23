package hw6;
import java.util.Comparator;

/**
 * Homework 6 Comparator Class that use to compare ignore case.
 * @author Han Zhang
 * AndrewID hanzhan2
 *
 */
public class IgnoreCase implements Comparator<Word> {

    /**
     * Compare with alpha ignore case.
     * @param o1 first word to compare
     * @param o2 second 2ord to compare
     * @return int indicates the order
     */
    @Override
    public int compare(Word o1, Word o2) {
        return o1.getWord().toLowerCase().compareTo(o2.getWord().toLowerCase());
    }

}
