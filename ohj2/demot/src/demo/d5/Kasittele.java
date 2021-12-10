package demo.d5;

import java.util.Arrays;

/**
 * Tauno 1 silmukoilla
 * @author Herman
 * @version 15.6.2020
 *
 */
@SuppressWarnings("all")
public class Kasittele
{
    public static void main(String args[])
    {
        int[] t = { 23, 45, 12, 9, 3, 7 };
        System.out.println("Aluksi : " + Arrays.toString(t));
        KasitteleTaulukko(t);
        System.out.println("Lopuksi: " + Arrays.toString(t));
        int[] t2 = { 23, 45, 12, 9, 3, 7 };
        System.out.println("Aluksi : " + Arrays.toString(t2));
        KasitteleTaulukko2(t2);
        System.out.println("Lopuksi: " + Arrays.toString(t2));
    }


    /**
     * Aliohjelmalla käsitellään taulukko sovitulla tavalla
     * @param t taulukko joka käsitellään
     * @example
     * #import java.util.Arrays;
     * <pre name="test">
     *  int[] t; 
     *  t = new int[]{$t1}; KasitteleTaulukko(t); Arrays.toString(t) === "[$t2]";
     *
     *  $t1                |   $t2
     * ------------------------------------------------
     * 55, 44, 33, 2, 5, 9 | 9, 5, 2, 33, 44, 55
     * 23, 45, 12, 9, 3, 7 | 7, 3, 9, 12, 45, 23
     * 1, 2, 3, 4, 5       | 5, 4, 3, 2, 1
     * 1                   | 1
     * 1, 2                | 2, 1
     * </pre>
     */
    public static void KasitteleTaulukko(int[] t) {
        int varasto;
        for (int i = 0; i < t.length/2; i++) {
            varasto = t[i];
            t[i] = t[t.length - 1 - i];
            t[t.length - 1 - i] = varasto;
        }
    }
    /**
     * Aliohjelmalla käsitellään taulukko sovitulla tavalla
     * @param t taulukko joka käsitellään
     * @example
     * #import java.util.Arrays;
     * <pre name="test">
     *  int[] t; 
     *  t = new int[]{$t1}; KasitteleTaulukko2(t); Arrays.toString(t) === "[$t2]";
     *
     *  $t1                |   $t2
     * ------------------------------------------------
     * 55, 44, 33, 2, 5, 9 | 44, 55, 2, 33, 9, 5
     * 23, 45, 12, 9, 3, 7 | 45, 23, 9, 12, 7, 3
     * 1, 2, 3, 4, 5       | 2, 1, 4, 3, 5
     * 1                   | 1
     * 1, 2                | 2, 1
     * </pre>
     */
    public static void KasitteleTaulukko2(int[] t)
    {
        int varasto;
        for (int i = 0; i < t.length-1; i+=2) {
            varasto = t[i];
            t[i] = t[i+1];
            t[i+1] = varasto;
        }
    }
}

