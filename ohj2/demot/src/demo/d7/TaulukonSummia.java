package demo.d7;
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
        int tulos = summaPerattaistenErotuksista(t);
        System.out.println("Lopuksi: " + Arrays.toString(t));
        System.out.println("Tulos: " + tulos);
        System.out.println("Aluksi : " + Arrays.toString(t));
        tulos = summaVastaavienErotuksista(t);
        System.out.println("Lopuksi: " + Arrays.toString(t));
        System.out.println("Tulos: " + tulos);
    }

    // BYCODEBEGIN
    /**
     * Lasketaan taulukon parillisissa paikoissa olevien lukujen summa miinus
     * parittomissa paikoissa olevien summa
     *
     * @param t taulukko jonka luvut lasketaan
     * @return erotusten summa
     * @example <pre name="test">
     *   int[] t = {1,2,3,4,5,6};
     *   summaPerattaistenErotuksista(t) === -3;
     *   summaPerattaistenErotuksista(new int[]{1,1,1,1,1,1}) === 0;
     *   summaPerattaistenErotuksista(new int[]{1,1,1,1,1}) === 1;
     *   summaPerattaistenErotuksista(new int[]{1}) === 1;
     *   summaPerattaistenErotuksista(new int[]{}) === 0;
     * </pre>
     */
    public static int summaPerattaistenErotuksista(int[] t) {
        int tulos = 0;
        for (int i = 0; i < t.length; i+=2) tulos += t[i];
        for (int j = 1; j < t.length; j+=2) tulos -= t[j];
        return tulos;
    }
    // BYCODEEND
    
    /**
     * Lasketaan taulukon summa (1. - viimeinen) + (2. - toiseksi viimeinen)
     * jne...
     *
     * @param t taulukko jonka luvut lasketaan
     * @return erotusten summa
     * @example <pre name="test">
     *   int[] t = {1,2,3,4,5,6};
     *   summaVastaavienErotuksista(t) === -9;
     *   summaVastaavienErotuksista(new int[]{1,1,1,1,1,1}) === 0;
     *   summaVastaavienErotuksista(new int[]{1,1,1,1,1}) === 0; // Keskimmäinen ei tule laskuun
     *   summaVastaavienErotuksista(new int[]{2,3}) === -1;
     *   summaVastaavienErotuksista(new int[]{1}) === 0;
     *   summaVastaavienErotuksista(new int[]{}) === 0;
     * </pre>
     */
    public static int summaVastaavienErotuksista(int[] t) {
        int tulos = 0;
        for (int i = 0, j = t.length - 1; i < j; i++, j--) {
            tulos += t[i] - t[j];
        }
        return tulos;
    }

}


