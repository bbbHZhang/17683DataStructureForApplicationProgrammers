
public class NoLatencyArray {
    private Object[] array1;
    private Object[] array2;
    private final static int INITIALCAP = 10;
    
    public NoLatencyArray() {
        this(INITIALCAP);
    }
    
    public NoLatencyArray(int capicity) {
        array1 = new Object[capicity];
        array2 = new Object[capicity * 2];
    }
    

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    

}
