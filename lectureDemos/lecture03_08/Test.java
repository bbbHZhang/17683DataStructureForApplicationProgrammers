package lecture03_08;

//import java.util.ArrayDeque;

public class Test {
    public static void main(String[] args) {
        //        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        System.out.println(fibna(9));
        System.out.println(fibnacci(9));

    }
    public static int fibna(int index) {
        return fibn(index, 0, 1);
    }

    public static int fibn(int index, int prev, int curr) {
        if (index == 2) {
            return 1;
        }
        if (index == 1) {
            return 0;
        }
        if (index == 3) {
            return prev + curr;
        }
        return fibn(--index, curr, prev + curr);
    }
    
    //    public static int fibn(int index, int prev, int curr) {
    //        if (index == 0) {
    //            return Math.max(curr, prev);
    //        }
    //
    //        if (prev > curr) {
    //            System.out.println(index +"prev" + prev + " curr " +curr + " " + (curr + prev));
    //            return fibn(--index, prev, curr + prev);
    //        } else {
    //            System.out.println(index + " " + (curr + prev)+" prev" + prev + " curr " + curr);
    //            return fibn(--index, prev + curr, prev);
    //        }
    //
    //    }
    //
    //    public static int fib(int i) {
    //        return fib(i, new int[i + 1]);
    //
    //    }
    //
    static int fibnacci(int n) 
    { 
        if (n <= 1) 
            return n; 
        return fibnacci(n-1) + fibnacci(n-2); 
    } 

    public static int fib(int index, int[]result) {
        if (index == 0) {
            result[index] = 0;
        } else if (index == 1) {
            result[index] = 1;
        } else {
            fib(index - 1, result);
            result[index] = result[index - 1] + result[index - 2];
        }
        return result[index];
    }

}
