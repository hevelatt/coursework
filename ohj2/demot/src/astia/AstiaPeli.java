package astia;

import fi.jyu.mit.ohj2.Mjonot;
import fi.jyu.mit.ohj2.Syotto;

/**
 * @author Herman
 * @version 17.6.2020
 *
 */
public class AstiaPeli {
    // #PACKAGEIMPORT    
    // #import fi.jyu.mit.ohj2.Suuntaaja;

    private Astia[] astiat = new Astia[3];
    private int index = 0;
    
    /**
     * Astiapelin oletusmuodostaja
     */
    public AstiaPeli() {
        lisaa_astia("ä", 100);
        Astia ampari = anna(0);
        ampari.tayta();
    }
    
    /**
     * Antaa astian kysytystä indeksistä
     * @param i Minkä indeksin astia annetaan
     * @return Astian annetusta indeksistä (virhetapauksessa ensimmäisen astian)
     */
    public Astia anna(int i) {
        if (i < 0 || astiat.length <= i)
            return astiat[0];
        return astiat[i];
    }
    
    /**
     * Lisää peliin astian
     * @param nimi Uuden astian nimi
     * @param tilavuus Uuden astian tilavuus
     */
    public void lisaa_astia(String nimi, double tilavuus) {
        if (index >= astiat.length) {
            Astia[] astiat2 = new Astia[index + 1];
            for (int i = 0; i < astiat.length; i++) 
                astiat2[i] = astiat[i];
            astiat = astiat2;      
        }
        astiat[index] = new Astia(nimi, tilavuus);
        index++;
    }
    
    /**
     * Etsii astian indeksin annetun nimen perusteella
     * @param mista Mitä astiaa etsitään
     * @return Palauttaa astian indeksin astiat-taulukossa, -1 jos astiaa ei löydy
     */
    public int etsi(String mista) {
        for (int i = 0; i < astiat.length; i++) {
            if (anna(i).oletko(mista)) return i;
        }
        return -1;
    }
    
    /**
     * Tulostetaan käytössä olevat nimet
     * @param mista Nimi, jonka pitäisi löytyä astiakokoelmasta
     * @param mihin Nimi, jonka pitäisi löytyä astiakokoelmasta
     * @example
     * <pre name="test">
     *  AstiaPeli peli = new AstiaPeli();
     *  peli.lisaa_astia("5",5);
     *  Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     *  peli.nimiOhje("5", "ä"); // Ei tarpeeksi astioita pelaamiseen
     *  so.ero("") === null; 
     *  peli.nimiOhje("a", "b"); // Ei tarpeeksi astioita, väärät nimet
     *  so.ero("") === null; 
     *  peli.lisaa_astia("8",8);
     *  peli.nimiOhje("ä", "8"); // Normaali tapaus
     *  so.ero("Käytössä olevat nimet: ä, 5, 8.\n") === null;
     *  peli.nimiOhje("a", "8"); // Väärä nimi
     *  String tulos = "Käytössä olevat nimet: ä, 5, 8.\n" +
     *                 "a ei ole validi nimi.\n";
     *  so.ero(tulos) === null;
     *  peli.nimiOhje("a", "kasi"); // Kummatkin nimet vääriä
     *  tulos = "Käytössä olevat nimet: ä, 5, 8.\n" +
     *          "a ei ole validi nimi.\n" +
     *          "kasi ei ole validi nimi.\n";
     *  so.ero(tulos) === null;
     *  so.palauta();
     * </pre>
     */
    public void nimiOhje(String mista, String mihin) {
        if (anna(astiat.length - 1) == null)
            return;
        System.out.print("Käytössä olevat nimet: ");
        for (int i = 0; i < astiat.length; i++) {
            System.out.print(anna(i).getNimi());
            if (i < astiat.length - 1) System.out.print(", ");
        }
        System.out.println(".");
        if (etsi(mista) < 0) System.out.println(mista + " ei ole validi nimi.");
        if (etsi(mihin) < 0) System.out.println(mihin + " ei ole validi nimi.");
    }

    /**
     * Tulostetaan astioiden ohjeet
     * @example
     * <pre name="test">
     *  AstiaPeli peli = new AstiaPeli();
     *  peli.lisaa_astia("5",5);
     *  Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     *  peli.tulosta_ohje();
     *  so.ero("Ei tarpeeksi astioita pelaamiseen. Tarvitaan vähintään 2 astiaa.\n") === null; 
     *  peli.lisaa_astia("8",8);
     *  peli.tulosta_ohje();
     *  so.ero("Käytössäsi on 8 ja 5 litran astia ja Ämpäri (100.0 l)\n") === null;
     *  peli.lisaa_astia("4",4.5);
     *  peli.tulosta_ohje();
     *  so.ero("Käytössäsi on 4.5, 8 ja 5 litran astia ja Ämpäri (100.0 l)\n") === null;
     *  so.palauta();
     * </pre>
     */
    public void tulosta_ohje() {
        if (anna(astiat.length - 1) == null) {
            System.out.println("Ei tarpeeksi astioita pelaamiseen. Tarvitaan vähintään 2 astiaa.");
            return;
        }
        System.out.print("Käytössäsi on "); // Käytössäsi on
        for (int i = astiat.length - 1; i > 0; i--) { // 8 ja 5
            if (anna(i).getTilavuus() % 1 == 0)
                System.out.print(String.format("%.0f",anna(i).getTilavuus()));
            else
                System.out.print(anna(i).getTilavuus());
            if (i > 2) System.out.print(", ");
            if (i == 2) System.out.print(" ja ");
        }
        System.out.println(" litran astia ja Ämpäri (" + anna(0).getTilavuus() + " l)" ); //litran astia ja Ämpäri (100 l)
    }

    /**
     * Käynnistää pelisilmukan
     * @example
     * <pre name="test">
     * Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     * AstiaPeli peli = new AstiaPeli();
     * peli.lisaa_astia("5",5);
     * peli.lisaa_astia("8",8);
     * peli.pelaa();
     * String tulos =
     *   "5.0 litran astiassa on 0.0 litraa nestettä\n" +
     *   "8.0 litran astiassa on 0.0 litraa nestettä\n" +
     *   "Mistä kaadetaan ja mihin >\n";
     * so.ero(tulos) === null; 
     * so.palauta();
     * </pre>
     */
    public void pelaa() {
        if (anna(astiat.length - 1) == null)
            return;
        while ( true ) {
            for (int i = 1; i < astiat.length; i++)
                System.out.println(anna(i).getTilavuus()+" litran astiassa on "+
                                   anna(i).getMaara() + " litraa nestettä");
            String rivi = Syotto.kysy("Mistä kaadetaan ja mihin"); // >ä 8
            if ( rivi.length() == 0 ) break;

            StringBuilder sb = new StringBuilder(rivi);
            String mista = Mjonot.erota(sb); // ä
            String mihin = Mjonot.erota(sb); // 8
            int imista = etsi(mista); // 0
            int imihin = etsi(mihin); // 2

            if ( (imista < 0) || (imihin < 0) )
                nimiOhje(mista, mihin); // ei löydy jompaakumpaa, tulosta oikeat vaihtoehdot
            else anna(imista).kaada(anna(imihin)); //
        }
    }
    
    /**
     * Testaan astioita
     * @param args ei käytössä
     * @example
     * <pre name="test">
     * Suuntaaja.StringInput si = new Suuntaaja.StringInput("");  
     * Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     * 
     * si.input("ä 8\n8 5\n4 5\n\n");  
     * main(null);
     * String tulos =
     *   "Käytössäsi on 8 ja 5 litran astia ja Ämpäri (100.0 l)\n"+
     *   "5.0 litran astiassa on 0.0 litraa nestettä\n" +
     *   "8.0 litran astiassa on 0.0 litraa nestettä\n" +
     *   "Mistä kaadetaan ja mihin >"                   + 
     *   "5.0 litran astiassa on 0.0 litraa nestettä\n" +
     *   "8.0 litran astiassa on 8.0 litraa nestettä\n" +
     *   "Mistä kaadetaan ja mihin >"                   +
     *   "5.0 litran astiassa on 5.0 litraa nestettä\n" +
     *   "8.0 litran astiassa on 3.0 litraa nestettä\n" +
     *   "Mistä kaadetaan ja mihin >"                   +
     *   "Käytössä olevat nimet: ä, 5, 8.\n"            +
     *   "4 ei ole validi nimi.\n"                      +
     *   "5.0 litran astiassa on 5.0 litraa nestettä\n" +
     *   "8.0 litran astiassa on 3.0 litraa nestettä\n" +
     *   "Mistä kaadetaan ja mihin >";
     * 
     * so.ero(tulos) === null; 
     * si.palauta(); so.palauta();
     * </pre>
     */
    public static void main(String[] args) {
        AstiaPeli peli = new AstiaPeli();
        peli.lisaa_astia("5",5);
        peli.lisaa_astia("8",8);
        peli.tulosta_ohje();
        peli.pelaa();
    }
}
