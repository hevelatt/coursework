package example;
/** Kokeillaan testejä */
public class Laskuja {
    /**
     * @param a eka summattava 
     * @param b toka summattava
     * @return a+b
     * @example
     * <pre name="test">
     *   summa(2,3) === 5;
     *   summa(-5,5) === 0;
     *  </pre>
     * @example
     * <pre name="test">
     * 
     * </pre>
     */
    public static int summa(int a, int b) {
        return a + b;
    }

    /** @param args ei käytössä */
    public static void main(String[] args) {
        System.out.println("Summa = " + summa(3,2));
    }
}