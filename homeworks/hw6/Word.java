package hw6;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
/**
 * Homework 6 Comparator Class that use to compare with frequencies.
 * @author Han Zhang
 * AndrewID hanzhan2
 *
 */
public class Word implements Comparable<Word> {
    /**
     * Private String word.
     */
    private String word;
    /**
     * Private Set<Integer> to store line numbers.
     */
    private Set<Integer> index;
    /**
     * Frequency.
     */
    private int frequency;

    /**
     * Constructor Word, store input into word.
     * @param input String to create the word
     */
    public Word(String input) {
        if (input != null && input.matches("[a-zA-Z]+")) {
            word = input;
            frequency = 1;
            index = new TreeSet<Integer>();
        }
    }
    /**
     * Add to index method.
     * @param line the line number to added.
     */
    public void addToIndex(Integer line) {
        index.add(line);
    }
    /**
     * Get index.
     * @return Set<Integer>.
     */
    public Set<Integer> getIndex() {
        return index;
    }

    /**
     * comparteTo method based on word.
     * @param arg0 used to compare with current word
     * @return integer Negative if word comes before arg0.
     */
    @Override
    public int compareTo(Word arg0) {
        return word.compareTo(arg0.word);
    }
    /**
     * toString method.
     * @return String of each word.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> itr = index.iterator();
        sb.append("[");
        while (itr.hasNext()) {
            sb.append(itr.next());
            if (itr.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return String.format("%s %d %s", word, frequency, sb.toString());
    }
    /**
     * get word method.
     * @return String the word.
     */
    public String getWord() {
        return word;
    }
    /**
     * set word method.
     * @param w String to be store as word.
     */
    public void setWord(String w) {
        word = w;
    }
    /**
     * get frequency method.
     * @return int frequency.
     */
    public int getFrequency() {
        return frequency;
    }
    /**
     * set frequency method.
     * @param f integer to be set.
     */
    public void setFrequency(int f) {
        frequency = f;
    }


}
