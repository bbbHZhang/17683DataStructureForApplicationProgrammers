package hw5;
/**
 * 17683 Data Structures for Application Programmers.
 *
 * Homework Assignment 5
 * Document Similarity Acceptance Test (case 1)
 *
 * No need to worry about number of lines for this case.
 *
 * DO NOT MODIFY THIS CLASS!
 * @author Terry Lee
 */
public class SimilarityTest1 {

    /**
     * Test program to check Similarity.
     * @param args arguments
     */
    public static void main(String[] args) {
        Similarity text1 = new Similarity("hello there nice to meet you you look nice a2 a3a a45 a a a a a bbbbb bb 12342n sfdk8333ksfj");
        printOutput(text1);
        System.out.println(text1.toString());

        Similarity text2 = new Similarity("time to say hello nice seeing you should meet again");
        printOutput(text2);

        System.out.println(text1.dotProduct(text2.getMap()) + " dot product.");
        System.out.println(text1.distance(text2.getMap()) + " distance.");
    }

    /**
     * Prints some values of Similarity object.
     * @param sim Similarity object to deal with
     */
    private static void printOutput(Similarity sim) {
        System.out.println(sim.numOfWords() + " words.");
        System.out.println(sim.numOfWordsNoDups() + " distinct words.");
        System.out.println(sim.euclideanNorm() + " Euclidean norm.\n");
    }
}

/*
 * <EXPECTED OUTPUT>
 *
 * 9 words.
 * 7 distinct words.
 * 3.605551275463989 Euclidean norm.
 *
 * 10 words.
 * 10 distinct words.
 * 3.1622776601683795 Euclidean norm.
 *
 * 7.0 dot product.
 * 0.9097531579442097 distance.
 */
