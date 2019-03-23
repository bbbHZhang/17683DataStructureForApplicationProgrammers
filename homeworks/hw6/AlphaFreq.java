package hw6;
import java.util.Comparator;

/**
 * Homework 6 Comparator Class that use to compare with alpha and frequencies.
 * @author Han Zhang
 * AndrewID hanzhan2
 *
 */
public class AlphaFreq implements Comparator<Word> {

    /**
     * Compare with alpha and frequencies.
     * @param arg0 first word to compare
     * @param arg1 second 2ord to compare
     * @return integer indicates the order
     */
    @Override
    public int compare(Word arg0, Word arg1) {
        if (arg0.getWord().compareTo(arg1.getWord()) != 0) {
            return arg0.getWord().compareTo(arg1.getWord());
        }
        return Integer.compare(arg0.getFrequency(), arg1.getFrequency());
    }

}
