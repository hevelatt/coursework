/**
 * 
 */
package demo.d3;

import fi.jyu.mit.ohj2.Mjonot;
import fi.jyu.mit.ohj2.Syotto;

/**
 * @author Herman
 * @version 7.6.2020
 *
 */
public class Jarjestys {

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        String s = Syotto.kysy("Anna jono");
        System.out.println(jarjesta1ja2(s));  
    }

    /**
     * Järjestää merkkijonon ensimmäisen ja toisen sanan aakkosjärjestykseen
     * @param sanat Merkkijono, jonka ensimmäinen ja toinen sana laitetaan aakkosjärjestykseen
     * @return Palauttaa sanat, siten että ensimmäinen ja toinen sana ovat aakkosjärjestyksessä
     */
    public static String jarjesta1ja2(String sanat) {
        StringBuilder loppuosa = new StringBuilder(sanat);
        String eka = Mjonot.erota(loppuosa);
        String toka = Mjonot.erota(loppuosa);
        if (eka.compareToIgnoreCase(toka) <= 0) return  (eka + " " + toka).trim();
        return (toka + " " + eka).trim();
    }
}
