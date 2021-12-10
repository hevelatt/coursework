package demo.d1;

import java.util.ArrayList;
// import java.util.Arrays;
import java.util.List;

/**
 * EtsitÃ¤Ã¤n mikÃ¤ on todennÃ¤kÃ¶isyys voittaa seuraavassa pelissÃ¤:
 * On 3:n vÃ¤risiÃ¤ palloja.  NiistÃ¤ poimitaan 4 kappaletta.
 * Sitten poimitaan vielÃ¤ 2 kappaletta. PelissÃ¤ voittaa
 * mikÃ¤li nÃ¤mÃ¤ 2 palloa lÃ¶ytyvÃ¤t 4 pallon joukosta.
 * Eli jos molemmat pallot ovat punaisia, pitÃ¤Ã¤ neljÃ¤n pallon
 * joukossa olla myÃ¶s olla 2 punaista (yksi ei riitÃ¤).
 * 
 * Ratkaisu tehdÃ¤Ã¤n etsimÃ¤llÃ¤ ensin kaikki kuuden "pallon"
 * mahdolliset joukot. Tietorakenteessa voi joukon alkiot
 * olla mitÃ¤ tahansa olioita.  
 * NÃ¤istÃ¤ kuuden joukoista lasketaan monellako
 * kaksi ensimmÃ¤istÃ¤ kuuluu loppuun joukkoon.
 * @author vesal
 * @version 2.1.2012
 */
public class Kombinaatiot {

    /**
     * Lasketaan ja tulostetaan kaikki 6 pallon kombinaatiot
     * @param args ei kÃ¤ytÃ¶ssÃ¤
     */
    public static void main(String[] args) {
        int vareja = 3;
        int ekakoko = 4;
        int tokakoko = 2;
        
        List<Integer> varienJoukko = new ArrayList<Integer>();
        for (int i=0; i<vareja; i++) varienJoukko.add(i);
        List<List<Integer>> kaikkiJoukot = etsiKombinaatiot(ekakoko+tokakoko,varienJoukko);
        for (List<Integer> kombi : kaikkiJoukot)
            System.out.println(kombi);
            
        /*
        List<String> varienJoukko = Arrays.asList("pun","vih","sin");
        List<List<String>> kaikkiJoukot = etsiKombinaatiot(ekakoko+tokakoko,varienJoukko);
        for (List<String> kombi : kaikkiJoukot)
            System.out.println(kombi);
        */    
    }

    
    /**
     * Etsii kaikki n kokoiset joukot, joissa on alkioita joukosta valinnat
     * @param n kuinka suuria joukkoja etsitÃ¤Ã¤n
     * @param valinnat joukko alkioita joita voidaan valita
     * @return kaikkien kombinaatioiden joukko
     * @example
     * <pre name="test">
     * #import java.util.List;
     *   List<String> valinnat = Arrays.asList("a","b","c");
     *   List<List<String>> kombit = etsiKombinaatiot(2,valinnat);
     *   int i=0;
     *   kombit.get(i++).toString() === "[a, a]";
     *   kombit.get(i++).toString() === "[a, b]";
     *   kombit.get(i++).toString() === "[a, c]";
     *   kombit.get(i++).toString() === "[b, a]";
     *   kombit.get(i++).toString() === "[b, b]";
     *   kombit.get(i++).toString() === "[b, c]";
     *   kombit.get(i++).toString() === "[c, a]";
     *   kombit.size() === 9;
     * </pre>
     */
    public static<T> List<List<T>> etsiKombinaatiot(int n, List<T> valinnat) {
        List<List<T>> tulos = new ArrayList<List<T>>();
        int[] indeksit = new int[n]; // alustettu 0:illa
        int m = valinnat.size();
        while ( true ) {
            List<T> rivi = new ArrayList<T>();
            for (int i:indeksit)
                rivi.add(valinnat.get(i));
            tulos.add(rivi);
            if ( !kasvata(indeksit,m) ) break;
        }
        return tulos;
    }

    
    /**
     * Kasvattaa numerotaulukon "arvoa" yhdellÃ¤.
     * Kukin numero voi olla luku 0-(m-1).
     * @param numerot taulukko, jota "kasvatetaan"
     * @param m mikÃ¤ on rajana yhdelle luvulle
     * @return true jos pystyi kasvattamaan, false jos tuli takaisin 0:aan
     * @example
     * <pre name="test">
     * #import java.util.Arrays;
     *   int[] num = new int[2];
     *   Arrays.toString(num) === "[0, 0]"; kasvata(num,3)=== true; 
     *   Arrays.toString(num) === "[0, 1]"; kasvata(num,3)=== true; 
     *   Arrays.toString(num) === "[0, 2]"; kasvata(num,3)=== true; 
     *   Arrays.toString(num) === "[1, 0]"; kasvata(num,3)=== true; 
     *   Arrays.toString(num) === "[1, 1]"; kasvata(num,3)=== true; 
     *   Arrays.toString(num) === "[1, 2]"; kasvata(num,3)=== true; 
     *   Arrays.toString(num) === "[2, 0]"; kasvata(num,3)=== true; 
     *   Arrays.toString(num) === "[2, 1]"; kasvata(num,3)=== true; 
     *   Arrays.toString(num) === "[2, 2]"; kasvata(num,3)=== false; 
     *   Arrays.toString(num) === "[0, 0]"; 
     * </pre>
     */
    public static boolean kasvata(int[] numerot, int m) {
        int n = numerot.length;
        for (int i=n-1; i>=0; i--) {
            numerot[i]++;
            if ( numerot[i] < m ) return true;
            numerot[i] = 0;
        }
        return numerot[0] != 0;
    }
}