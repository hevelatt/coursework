package demo.d2;

/**
 * @author Herman
 * @version 3.6.2020
 *
 */
public class KertomaWhile
{
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args)
    {
        System.out.printf("Luvun 5 kertoma on: %d\n", kertoma(5));
        System.out.printf("Luvun 10 kertoma on: %d\n", kertoma(10));
    }

    // BYCODEBEGIN
    /**
     * Aliohjelma, joka laskee luvun kertoman
     * @param n luku, jonka kertoma lasketaan
     * @return kertoma
     * <pre name="test">
     *  kertoma(0) === 1;
     *  kertoma(1) === 1;
     *  kertoma(2) === 2;
     *  kertoma(3) === 6;
     *  kertoma(4) === 24;
     *  kertoma(5) === 120;
     *  kertoma(6) === 720;
     *  kertoma(7) === 5040;
     *  kertoma(8) === 40320;
     *  kertoma(9) === 362880;
     *  kertoma(10) === 3628800;
     * </pre>
     */
    public static int kertoma(int n)
    {
        if (n == 0 || n==1) return 1;
        
        int kertoma = 1;
        int i = n;
        
        while (i > 0) {
            kertoma *= i;
            i--;
        }
        return kertoma;
    }
    // BYCODEEND
}


