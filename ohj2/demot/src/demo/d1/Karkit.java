package demo.d1;

import java.util.Arrays;

/**
 * Lasketaan karkinheittojen keskiarvoja.
 * @author vesal
 * @version 10.1.2016
 *
 */
@SuppressWarnings("unused")
public class Karkit
{
    /**
     * Pieni testiluokka keskiarvo-aliohjelmalle.
     * @param args ei käytössä
     */
    public static void main(String[] args)
    {
        int[] karkkimaarat = new int[] { 3, 0, 7, 6, 5, 99, 5 };
        double ka = keskiarvo(karkkimaarat, 0, 99);
        System.out.printf("%1.3f\n",ka);
    }


    // BYCODEBEGIN
    /**
     * Lasketaan lukujen keskiarvo niin että vialliset tai pienemmät
     * hylätään. Mikäli tulee lopetus tai suurempi, laskeminen
     * lopetetaan.
     * @param taulukko luvut jotka lasketaan keskiarvoon
     * @param vialliset raja josta hylätään
     * @param lopetus raja jonka jälkeen lopetetaan
     * @return lukujen keskiarvo, vialliset jos ei yhtään lukua laskettu mukaan
     * @example
     * <pre name="test">
     *    keskiarvo(new int[]{ 12, 0, 42, 14, 99, 12, 55 }, 0, 99 ) ~~~ 22.666666;
     *    keskiarvo(new int[]{ -5 }, -5, 99 ) ~~~ -5;
     * </pre>
     */
    public static double keskiarvo(int[] taulukko, int vialliset, int lopetus) {
        double summa = 0;
        int lkm = 0;
        for (int luku: taulukko) {
            if (luku >= lopetus) break;
            if (luku <= vialliset) continue;
            summa += luku;
            lkm++;
        }
        if (lkm == 0) return vialliset;
        return summa / lkm;
    }
    // BYCODEEND

}

