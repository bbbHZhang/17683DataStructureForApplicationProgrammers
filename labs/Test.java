import finalpart2.AlphaCounter;
import finalpart2.SpecialCharCounter;
import finalpart2.WordCounter;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int a = 0;
        int b= 3;
        int c = a + (b -a) /2;
        System.out.println(c);
        
        
        
        //praper for the output
        StringBuilder spaces = new StringBuilder();
        if (this instanceof WordCounter) {
            spaces = new StringBuilder();
            for (int i = 1; i < 0; i++) {
                spaces.append("\t\t");
            }
            spaces.append("Line").append(line).append(" by ").append("WordCounter: [");
            for (int j = 0; j < lineOfText.words.length; j++) {
                spaces.append(lineOfText.words[j]);
                if (j != lineOfText.words.length - 1) {
                    spaces.append(", ");
                }
            }
            spaces.append("]");
            System.out.println(spaces.toString());
            lineOfText.readFlags[0] = true;
        } else if (this instanceof AlphaCounter) {
            spaces = new StringBuilder();
            for (int i = 0; i < 2; i++) {
                spaces.append("\t\t");
            }
            spaces.append("Line").append(line).append(" by ").append("AlphaCounter: [");
            for (int j = 0; j < lineOfText.words.length; j++) {
                spaces.append(lineOfText.words[j]);
                if (j != lineOfText.words.length - 1) {
                    spaces.append(", ");
                }
            }
            spaces.append("]");
            System.out.println(spaces.toString());
            lineOfText.readFlags[1] = true;
        } else if (this instanceof SpecialCharCounter) {
            spaces = new StringBuilder();
            for (int i = 0; i < 1; i++) {
                spaces.append("\t\t");
            }
            spaces.append("Line").append(line).append(" by ").append("SpecialCharCounter: [");
            for (int j = 0; j < lineOfText.words.length; j++) {
                spaces.append(lineOfText.words[j]);
                if (j != lineOfText.words.length - 1) {
                    spaces.append(", ");
                }
            }
            spaces.append("]");
            System.out.println(spaces.toString());
            lineOfText.readFlags[2] = true;
        } else {
            spaces = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                spaces.append("\t\t");
            }
            spaces.append("Line").append(line).append(" by ").append("NumberCounter: [");
            for (int j = 0; j < lineOfText.words.length; j++) {
                spaces.append(lineOfText.words[j]);
                if (j != lineOfText.words.length - 1) {
                    spaces.append(", ");
                }
            }
            spaces.append("]");
            System.out.println(spaces.toString());
            lineOfText.readFlags[3] = true;
        }
    }

}
