package hw4;


public class Test {

    public static void main(String[] args) {
        Test t = new Test();
        System.out.println(t.hashFunc("as"));

    }

    public int hashFunc(String input) {
        int hashVal = 0;
        //        for (int i = input.length() - 1; i >= 0; i--) {
        for (int i = 0; i < input.length() - 1; i++) {
            int character = input.charAt(i);
            character = character % 96;
            hashVal = ((hashVal + character) * 27);
        }
        int tmp = input.charAt(input.length() - 1) % 96;
        hashVal = (hashVal + tmp);
        return (hashVal % 23);
    }
}
