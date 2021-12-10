package demo.d8;
import java.util.Arrays;

/**
 * Lasketaan yhteen taulukon alkioita
 *
 * @author vesal
 * @version 17.1.2014
 */
public class TaulukonSummia {

    /**
     * Kokeillaan summa-funktiota
     * @param args  ei käytössä
     */
    public static void main(String[] args) {
        int[] t = { 23, 45, 12, 9, 3, 7 };
        System.out.println("Aluksi : " + Arrays.toString(t));
        lisaaArvoihin(t, 5);
        System.out.println("Lopuksi: " + Arrays.toString(t));
        int[] t2 = { 2, 4, 6, 8, 10, 12 };
        System.out.println("Aluksi : " + Arrays.toString(t));
        laskePerakkaiset(t2);
        System.out.println("Lopuksi: " + Arrays.toString(t));
    }

    // BYCODEBEGIN
    /**
     * Lisätään kuhunkin taulukon alkiooon lisättävä arvo
     * @param t taulukko johon lisätään
     * @param lisattava mikä luku lisätään kuhunkin alkioon
     * @example
     * <pre name="test">
     * #import java.util.Arrays;
     *   int[] t = {1,2,3,4,5,6};
     *   lisaaArvoihin(t,5); Arrays.toString(t) === "[6, 7, 8, 9, 10, 11]";
     *   lisaaArvoihin(t,0); Arrays.toString(t) === "[6, 7, 8, 9, 10, 11]";
     *   lisaaArvoihin(t,-1); Arrays.toString(t) === "[5, 6, 7, 8, 9, 10]";
     *   int[] t2 = {1};
     *   int[] t3 = {};
     *   lisaaArvoihin(t2,-1); Arrays.toString(t2) === "[0]";
     *   lisaaArvoihin(t3,-1); Arrays.toString(t3) === "[]";
     * </pre>
     */
    public static void lisaaArvoihin(int[] t, int lisattava) {
        for (int i = 0; i < t.length; i++)
            t[i] += lisattava;
    }
    // BYCODEEND

    /**
     * Lasketaan yhteen perÃ¤kkÃ¤isissÃ¤ paikoissa olevat taulukon alkiot ja 
     * laitetaan tulos aina parilliseen (0,2,4) paikaan. 
     * Vastaavaan parittomaan paikkaan laitetaan arvo 0. 
     * Eli taulukon summa pysyy samana, mutta parittomissa (1,3,5) 
     * paikoissa olevat alkiot nollautuvat.
     * 
     * @param t taulukko jonka luvut lasketaan (vain 6 paikkainen)
     * @example
     * <pre name="test">
     * #import java.util.Arrays;
     *   int[] t = {1,2,3,4,5,6};
     *   laskePerakkaiset(t);
     *   Arrays.toString(t) === "[3, 0, 7, 0, 11, 0]";
     *   int[] t2 = {1,2,3,4,5,6,7};
     *   laskePerakkaiset(t2);
     *   Arrays.toString(t2) === "[3, 0, 7, 0, 11, 0, 7]";
     *   int[] t3 = {};
     *   laskePerakkaiset(t3);
     *   Arrays.toString(t3) === "[]";
     * </pre>
     */
    public static void laskePerakkaiset(int[] t) {
        for(int i = 0, j = 1; j < t.length; i += 2, j +=2) {
            t[i] += t[j];
            t[j] = 0;
        }
    }
}


