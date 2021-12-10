package demo.d6;
//package Pohja.Tauno;

import java.util.Arrays;

/**
 * Pohja Tauno-testeille
 * @author vesal
 * @version 8.1.2015
 *
 */
@SuppressWarnings("all")
public class KasitteleReturn
{
    public static void main(String args[])
    {
        int[] t = { 23, 45, 12, 9, 3, 7 };
        System.out.println("Aluksi : " + Arrays.toString(t));
        int tulos = summaaAlkiot(t);
        System.out.println("Lopuksi: " + Arrays.toString(t));
        System.out.println("Tulos: " + tulos);
        int[] t2 = { 23, 45, 12, 9, 3, 7 };
        System.out.println("Aluksi : " + Arrays.toString(t2));
        int tulos2 = summaaParilliset(t2);
        System.out.println("Lopuksi: " + Arrays.toString(t2));
        System.out.println("Tulos: " + tulos2);
    }


    /**
     * Laskee yhteen taulukon luvut
     * @param t taulukko jonka lukuja lasketaan yhteen
     * @return taulukon alkioiden summa
     * @example
     * #import java.util.Arrays;
     * <pre name="test">
     *  int[] t; int tulos; 
     *  t = new int[]{$t1}; tulos=summaaAlkiot(t); 
     *  Arrays.toString(t) === "[$t2]"; tulos === $tulos;
     *
     *      $t1                |   $t2              | $tulos
     * ------------------------------------------------------------
     *     2, 3, 1, 4, 6, 5    | 2, 3, 1, 4, 6, 5   | 21
     *     10, 5, 1, 9, 4, 2   | 10, 5, 1, 9, 4, 2  | 31
     *     1, 2, 3, 4, 5       | 1, 2, 3, 4, 5      | 15
     *     0                   | 0                  | 0
     *     1, 0                | 1, 0               | 1
     * </pre>
     */
    public static int summaaAlkiot(int[] t) {
        int tulos = 0;
        for (int i = 0; i < t.length; i++) {
                tulos += t[i];
        }
        return tulos;
    }
    
    /**
     * Laskee yhteen taulukon parillisissa indekseissä olevat luvut
     * @param t taulukko jonka lukuja lasketaan yhteen
     * @return parillisissa indekseissä olevien lukujen summa
     * @example
     * #import java.util.Arrays;
     * <pre name="test">
     *  int[] t; int tulos; 
     *  t = new int[]{$t1}; tulos=summaaParilliset(t); 
     *  Arrays.toString(t) === "[$t2]"; tulos === $tulos;
     *
     *      $t1                |   $t2              | $tulos
     * ------------------------------------------------------------
     *     2, 3, 1, 4, 6, 5    | 2, 3, 1, 4, 6, 5   | 9
     *     10, 5, 1, 9, 4, 2   | 10, 5, 1, 9, 4, 2  | 15
     *     1, 2, 3, 4, 5       | 1, 2, 3, 4, 5      | 9
     *     0                   | 0                  | 0
     *     1                   | 1                  | 1
     *     1, 0                | 1, 0               | 1
     *     0, 1                | 0, 1               | 0
     * </pre>
     */
    public static int summaaParilliset(int[] t) {
        int tulos = 0;
        for (int i = 0; i < t.length; i+=2) {
            tulos = tulos + t[i];
        }
        return tulos;
    }
}

