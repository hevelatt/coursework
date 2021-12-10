package demo.d8;

import fi.jyu.mit.ohj2.Mjonot;
import fi.jyu.mit.ohj2.Syotto;

/**
 * @author Herman
 * @version 27.6.2020
 * TODO: korjaa vanhat testit
 */
public class KomentoriviAstiaPeli {
    // #import fi.jyu.mit.ohj2.Suuntaaja;

    private AstiaPeli peli = new AstiaPeli();
    
    /**
     * KomentoriviAstiaPelin muodostaja
     * @param peli Peli jota pelataan
     * TODO: testit
     */
    public KomentoriviAstiaPeli(AstiaPeli peli) {
        this.peli = peli;
    }
    
    /**
     * Tulostetaan pelin ohje
     * 
     * @example
     * <pre name="test">
     *  Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     *  AstiaPeli peli = new AstiaPeli();  peli.lisaaAstia("5",5);  peli.lisaaAstia("8",8);
     *  peli.tulostaOhje();
     *  so.ero("Käytössäsi on 5.0 sekä 8.0 litran astiat ja Ämpari (100.0 l)\n") === null;
     *  so.palauta();
     * </pre>
     */
    public void tulostaOhje() {
        if ( peli.getLkm() <= 0 ) return;
        String ja = "";
        System.out.print("Käytössäsi on ");
        for (int i = 1; i < peli.getLkm(); i++) {
            System.out.print(ja + peli.anna(i).getTilavuus());
            ja = " sekä ";
        }
        System.out.println(" litran astiat ja Ämpari (" +
                peli.getAmpari().getTilavuus() + " l)");
    }


    /**
     * Tulostetaan astioissa olevat nestemäärät
     * @example
     * <pre name="test">
     * Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     * 
     * String tulos =
     *   "5.0 litran astiassa on 5.0 litraa nestettä\n"       +
     *   "8.0 litran astiassa on 3.0 litraa nestettä\n";
     * 
     *   AstiaPeli peli = new AstiaPeli();
     *   Astia ampari = peli.getAmpari(); 
     *   Astia a5 = peli.lisaaAstia("5",5);
     *   Astia a8 = peli.lisaaAstia("8",8);
     *   peli.kaada(ampari,a8);
     *   peli.kaada(a8,a5);
     *   peli.tulostaMaarat();
     * 
     * so.ero(tulos) === null; 
     * so.palauta();
     * </pre>
     */
    public void tulostaMaarat() {
        for (int i = 1; i < peli.getLkm(); i++)
            System.out.println(peli.anna(i).getTilavuus() + " litran astiassa on " +
                    peli.anna(i).getMaara() + " litraa nestettä");
    }
    
    /**
     * Tulostetaan ohjeet nimistä.
     * @param mista mistä yritettiin
     * @param mihin mihin yritettiin
     * 
     * @example
     * <pre name="test">
     *  Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     *  AstiaPeli peli = new AstiaPeli(); peli.lisaaAstia("5",5); peli.lisaaAstia("8",8);
     *  peli.nimiOhje("4","6");
     *  String tulos = "Nimeä ei tunneta: 4 tai 6\n" +
     *                 "Tunnetaan nimet: \n"         +
     *                 "ä 5 8 \n";
     *  so.ero(tulos) === null;
     *  peli.nimiOhje("?","6");
     *         tulos = "Tunnetaan nimet: \nä 5 8 \n";
     *  so.ero(tulos) === null;
     *  so.palauta();
     * </pre>
     */
    public void nimiOhje(String mista, String mihin) {
        if ( !"?".equals(mista) )
            System.out.println("Nimeä ei tunneta: " + mista + " tai " + mihin);
        System.out.println("Tunnetaan nimet: ");
        for (int i = 0; i < peli.getLkm(); i++)
            System.out.println(peli.anna(i).getNimi() + " ");
        System.out.println("");
    }

    /**
     * Tulostaa löytämistilanteen
     * @return joka jokaista määrää on löydetty
     * @example
     * <pre name="test">
     * Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     * 
     * String tulos =
     *   "Olet ratkaissut tilavuudet: 3 5 8\n"  +
     *   "Ratkaisematta on: 1 2 4 6 7 9 10 11 12 13\n";  
     * 
     *   AstiaPeli peli = new AstiaPeli();
     *   Astia ampari = peli.getAmpari(); 
     *   Astia a5 = peli.lisaaAstia("5",5);
     *   Astia a8 = peli.lisaaAstia("8",8);
     *   peli.kaada(ampari,a8);
     *   peli.kaada(a8,a5);
     *   peli.tulostaEsiintymat();
     * 
     * so.ero(tulos) === null; 
     * so.palauta();
     * </pre>
     */
    public boolean tulostaEsiintymat() {
        System.out.println("Olet ratkaissut tilavuudet: " + peli.getEsiintymat().loydetyt());
        if ( peli.getEsiintymat().getLoydettyja() >= peli.getEsiintymat().getYla() ) return true;
        System.out.println("Ratkaisematta on: " + peli.getEsiintymat().eiLoydetyt());
        return false;

    }

    /**
     * Käynistetään peli.  Peli loppuu kun käyttäjä syöttää tyhjän rivin.
     * @example
     * <pre name="test">
     * Suuntaaja.StringInput si = new Suuntaaja.StringInput("");  
     * Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     * 
     * si.input("ä 8\n8 5\n4 5\n\n");  
     * String tulos =
     *   "Käytössäsi on 5.0 sekä 8.0 litran astiat ja Ämpari (100.0 l)\n"+
     *   "5.0 litran astiassa on 0.0 litraa nestettä\n"       +
     *   "8.0 litran astiassa on 0.0 litraa nestettä\n"       +
     *   "Olet ratkaissut tilavuudet: \n"                     +
     *   "Ratkaisematta on: 1 2 3 4 5 6 7 8 9 10 11 12 13\n"  +  
     *   "Mistä kaadetaan ja mihin >"                         + 
     *   "5.0 litran astiassa on 0.0 litraa nestettä\n"       +
     *   "8.0 litran astiassa on 8.0 litraa nestettä\n"       +
     *   "Olet ratkaissut tilavuudet: 8\n"                    +
     *   "Ratkaisematta on: 1 2 3 4 5 6 7 9 10 11 12 13\n"    +  
     *   "Mistä kaadetaan ja mihin >"                         +
     *   "5.0 litran astiassa on 5.0 litraa nestettä\n"       +
     *   "8.0 litran astiassa on 3.0 litraa nestettä\n"       +
     *   "Olet ratkaissut tilavuudet: 3 5 8\n"                +
     *   "Ratkaisematta on: 1 2 4 6 7 9 10 11 12 13\n"        +  
     *   "Mistä kaadetaan ja mihin >"                         +
     *   "Nimeä ei tunneta: 4 tai 5\n"                        +
     *   "Tunnetaan nimet: \n"                                + 
     *   "ä 5 8 \n"                                           +
     *   "5.0 litran astiassa on 5.0 litraa nestettä\n"       +
     *   "8.0 litran astiassa on 3.0 litraa nestettä\n"       +
     *   "Olet ratkaissut tilavuudet: 3 5 8\n"                +
     *   "Ratkaisematta on: 1 2 4 6 7 9 10 11 12 13\n"        +  
     *   "Mistä kaadetaan ja mihin >";
     * 
     *   AstiaPeli peli = new AstiaPeli();
     *   peli.lisaaAstia("5",5);
     *   peli.lisaaAstia("8",8);
     *   peli.tulostaOhje();
     *   peli.pelaa();
     * 
     * so.ero(tulos) === null; 
     * si.palauta(); so.palauta();
     * </pre>
     */
    public void pelaa() {

        while ( true ) {
            tulostaMaarat();
            if ( tulostaEsiintymat() ) return; // ver.1.2

            String rivi = Syotto.kysy("Mistä kaadetaan ja mihin");
            if ( rivi.length() == 0 ) break;
            StringBuilder sb = new StringBuilder(rivi); 
            String smista = Mjonot.erota(sb);
            String smihin = Mjonot.erota(sb);
            Astia mista = peli.etsi(smista);
            Astia mihin = peli.etsi(smihin);

            if ( (mista == null) || (mihin == null) )
                nimiOhje(smista, smihin);
            else peli.kaada(mista,mihin);
        }
    }
    
    /**
     * Käynnistää pelin komentoriviversion
     * @param peli viite peliin, jossa astiat paikallaan
     * TODO: testit
     */
    public static void pelaa(AstiaPeli peli) {
        KomentoriviAstiaPeli liittyma = new KomentoriviAstiaPeli(peli);
        liittyma.tulostaOhje();
        liittyma.pelaa();
    }


    /**
     * Testataan astiapeliä
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        AstiaPeli peli = new AstiaPeli();

        peli.lisaaAstia("8", 8);
        peli.lisaaAstia("5", 5);
        
        pelaa(peli);
    }
}
