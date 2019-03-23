package lecture09Compare;

/*
import org.apache.commons.lang3.builder.*;
import com.google.common.base.*;
import com.google.common.collect.*;
*/

/**
 * 17683 Data Structures for Application Programmers.
 * Lecture 9 Sorting in Java
 *
 * Simple Card class with Comparable implementation
 * Also, includes examples of using Apache Commons Lang and Guava Libraries
 *
 * @author Terry Lee
 */
public class Card implements Comparable<Card> {
    /**
     * Suit field.
     */
    private String suit;
    /**
     * Rank field.
     */
    private int rank;

    /**
     * Constructs a card with suit and rank.
     * @param suit suit of a card
     * @param rank rank of a card
     */
    public Card(String s, int r) {
        suit = s;
        rank = r;
    }

    /**
     * Returns suit of a card.
     * @return returns string value of suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Returns rank of a card.
     * @return returns rank of a card
     */
    public int getRank() {
        return rank;
    }

    /**
     * Returns String representation of card objects.
     * @return String representation of card objects
     */
    @Override
    public String toString() {
        return suit + ", " + rank;
    }

    /**
     * Uses rank to compare cards
     * @return positive, 0 or negative
     */
    @Override
    public int compareTo(Card other) {
        return Integer.compare(rank, other.rank);
    }
    

    /******************************************************************************
     *
     * Example of compareTo, equals and hashCode methods using Apache Commons Lang
     *
     ******************************************************************************/
    /*
    @Override
    public int compareTo(Card other) {
        return new CompareToBuilder()
                .append(this.suit, other.suit)
                .append(this.rank, other.rank)
                .toComparison();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Card)) {
            return false;
        }
        Card castOther = (Card) other;
        return new EqualsBuilder()
                .append(this.suit, castOther.suit)
                .append(this.rank, castOther.rank)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.suit).append(this.rank).toHashCode();
    }
    */
    

    /************************************************************************
     *
     *  Example of compareTo, equals and hashCode methods using Google Guava
     *
     *  Note: When using Java 7 or later version, you can use Objects class
     *  in java.util instead of using Guava
     *
     ************************************************************************/
    /*
    @Override
    public int compareTo(Card other) {
        return ComparisonChain.start()
                .compare(this.suit, other.suit)
                .compare(this.rank, other.rank)
                .result();
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Card)){
            return false;
        }
        Card other = (Card) obj;
        return Objects.equal(this.suit, other.suit)
                && Objects.equal(this.rank, other.rank);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(this.suit, this.rank);
    }
    */

}
