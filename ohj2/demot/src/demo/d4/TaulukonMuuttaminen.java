package demo.d4;

import java.util.Arrays;

/**
 * Muutetaan taulkoita
 * @author vesal
 * @version 17.1.2014 
 */
public class TaulukonMuuttaminen {

    /**
     * Kokeillaan summa-funktiota
     * @param args ei kÃ¤ytÃ¶ssÃ¤
     */
    public static void main(String[] args) {
        int[] t = {32, 32, 76, 62, 31, 86};
        System.out.println(Arrays.toString(t));
        lisaaArvoihin6(t,5);
        System.out.println(Arrays.toString(t));

        laskePerkakkaiset6(t);
        System.out.println(Arrays.toString(t));
    }

    
    /**
     * LisÃ¤tÃ¤Ã¤n kuhunkin taulukon alkiooon lisÃ¤ttÃ¤vÃ¤ arvo
     * @param t taulukko johon lisÃ¤tÃ¤Ã¤n (vain 6 paikkainen)
     * @param lisattava mikÃ¤ luku lisÃ¤tÃ¤Ã¤n kuhunkin alkioon
     * @example
     * <pre name="test">
     * #import java.util.Arrays;
     *   int[] t = {1,2,3,4,5,6};
     *   lisaaArvoihin6(t,5);
     *   Arrays.toString(t) === "[6, 7, 8, 9, 10, 11]";
     * </pre>
     */
    public static void lisaaArvoihin6(int[] t,int lisattava) {
        for (int i = 0; i < t.length; i++) t[i] += lisattava;
    }


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
     *   laskePerkakkaiset6(t);
     *   Arrays.toString(t) === "[3, 0, 7, 0, 11, 0]";
     *   int[] t2 = {1,2,3,4,5,6,7};
     *   laskePerkakkaiset6(t2);
     *   Arrays.toString(t2) === "[3, 0, 7, 0, 11, 0, 7]";
     * </pre>
     */
    public static void laskePerkakkaiset6(int[] t) {
        int j = 1;
        for(int i = 0; j < t.length; i += 2) {
            t[i] += t[j];
            t[j] = 0;
            j += 2;
        }
    }
}
