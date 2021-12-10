package demo.d9;

/**
 * Luokka taulukosta poistamista varten
 * @author Herman
 * @version 4.7.2020
 *
 */
public class PoistaTaulukosta {

    /**
     * Poistaa taulukosta t kaikki poista esiintymät
     * @param t taulukko josta poistetaan
     * @param lkm taulukon pituus
     * @param poista poistettava luku
     * @return palauttaa kuinka paljon (oikeita) lukuja taulukossa nyt on, -1 jos annettiin virheellinen lkm
     * @example
     * <pre name="test">
     * #import java.util.Arrays;
     * int[] t = {4,7,9,3,9,2};
     * poista(t, 6, 9) === 4;  Arrays.toString(t) =R= "\\[4, 7, 3, 2, ., .\\]";
     * poista(t, 4, 2) === 3;  Arrays.toString(t) =R= "\\[4, 7, 3, ., ., .\\]";
     * t = new int[]{0,0};
     * poista(t, 4, 0) === -1; Arrays.toString(t) === "[0, 0]";
     * poista(t, 2, 0) === 0;  Arrays.toString(t) =R= "\\[., .\\]";
     * t = new int[]{9,2,9,9,9,9,1,2,3};
     * poista(t, 9, 9) === 4;  Arrays.toString(t) =R= "\\[2, 1, 2, 3, ., ., ., ., .\\]";
     * poista(t, 4, 9) === 4;  Arrays.toString(t) =R= "\\[2, 1, 2, 3, ., ., ., ., .\\]";
     * poista(t, 4, 2) === 2;  Arrays.toString(t) =R= "\\[1, 3, ., ., ., ., ., ., .\\]";
     * </pre>
     */
    public static int poista(int[] t, int lkm, int poista) {
        if (lkm > t.length)
            return -1;
        int uusilkm = lkm;

        for (int i = 0, j = -1; i < lkm; i++) {
            if (t[i] == poista) {
                if (j < 0)
                    j = i;
                uusilkm--;
            } else if (j >= 0) {
                t[j] = t[i];
                j++;
            }
        }

        return uusilkm;
    }


    /**
     * Tulostetaan taulukosta lkm kappaletta lukuja
     * @param t   käsiteltävä taulukko
     * @param lkm käsitelteltävien alkioiden lkm
     */
    public static void tulosta(int t[], int lkm) {
        int tlkm = lkm;
        if (tlkm > t.length)
            tlkm = t.length;
        for (int i = 0; i < tlkm; i++)
            System.out.print(t[i] + " ");
        System.out.println();
    }


    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        int t[] = { 4, 7, 6, 3, 6, 2 };
        int lkm = 6;

        lkm = poista(t, lkm, 6); /* => t = {4,7,3,2}, lkm = 4 */
        tulosta(t, lkm);
    }
}