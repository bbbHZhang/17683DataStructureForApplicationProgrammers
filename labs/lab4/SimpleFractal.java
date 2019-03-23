package lab4;
/**
 * 17683 Data Structures for Application Programmers.
 *
 * Lab 4 Number of Collisions Comparison and
 * Drawing a simple fractal of n squares with recursion
 *
 * Andrew ID: hanzhan2 
 * @author Han Zhang
 */
public class SimpleFractal {

    /**
     * Test program to draw n squares.
     * @param args arguments
     */
    public static void main(String[] args) {
        /*
         * canvas width and height are both 1.0.
         * initial call to create 10 squares.
         * Do not change the following initial call
         */
        fractal(10, 0, 0, 0.5);
    }

    /**
     * recursive method to draw a fractal of n number of squares.
     * @param n number of squares
     * @param x the x-coordinate of the left-bottom corner of the square
     * @param y the y-coordinate of the left-bottom corner of the square
     * @param length side length of a square
     */
    public static void fractal(int n, double x, double y, double length) {
        // TODO implement this method
        /*
         * Note: call filledSquare static method in StdDraw class
         * which draws a filled square of side length 2*r, centered on (x, y).
         */
        StdDraw.filledSquare(x + length / 2, y + length / 2, length / 2);
        while (n > 0) { fractal(n - 1, x + length, x + length, length / 2);}
        return;
    }

}
