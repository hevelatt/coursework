package astia;

/**
 *  John McLane (Bruce Willis) joutui elokuvassa Die Hard 3 ongelman eteen,
 *  jossa piti kahdella vesikanisterilla mitata jonkin muun suuruinen
 *  vesimäärä "tarkasti" ja näin estää pommin räjähtäminen.
 *  Koska aika meni varsin tiukalle, on vastaavien tilanteiden harjoittelemiseksi
 *  nyt tehty uusi menestysohjelma "Astia".
 *<br>
 *  Pelillä demonstroidaan/harjoitellaan miten voidaan kahdella astialla,
 *  5 l ja 8 l,  muodostaa kaikki mahdolliset tilavuudet 1-13 l.
 *  Pelissä on kaksi varsinaista astiaa: a5 ja a8, sekä kolmas apuastia
 *  ampari.  Nestettä voidaan kerrallaan kaataa yhdestä astiasta toiseen
 *  joko siten, että astian koko sisältä tyhjennetän jos se mahtuu
 *  tai astiasta kaadetaan sen verran että toinen astia tulee täyteen.
 *<br>
 *  Tee ohjelma jota voitasiin käyttää seuraavasti:
 *<br>
 *<pre>
 *  Käytössäsi on 8 ja 5 litran astia ja ämpäri (100 l)
 *
 *  8 litran astiassa on 0 litraa
 *  5 litran astiassa on 0 litraa
 *  Mistä kaadetaan ja mihin >ä 8[ret]
 *  8 litran astiassa on 8 litraa
 *  5 litran astiassa on 0 litraa
 *  Mistä kaadetaan ja mihin >8 5[ret]
 *  8 litran astiassa on 3 litraa
 *  5 litran astiassa on 5 litraa
 *  Mistä kaadetaan ja mihin >5 ä[ret]
 *  8 litran astiassa on 3 litraa
 *  5 litran astiassa on 0 litraa
 *  Mistä kaadetaan ja mihin >8 5[ret]
 *  8 litran astiassa on 0 litraa
 *  5 litran astiassa on 3 litraa
 *  Mistä kaadetaan ja mihin >[ret]
 *</pre>
 * @author  Vesa Lappalainen
 * @version 1.0, 09.02.2003
 */
public class Astia {
    private final String nimi;
    private final double tilavuus;
    private double maara;


    /**
     * Alustaa uuden astian tietyn kokoiseksi ja luonnollisetsi tyhjäksi
     * @param nimi uuden astian nimi
     * @param tilavuus uuden astian tilavuus
     * @example
     * <pre name="test">
     * Astia astia5l = new Astia("5l",5);
     * astia5l.getNimi()     === "5l";
     * astia5l.getMaara()    ~~~ 0;
     * astia5l.getTilavuus() ~~~ 5;
     * </pre>
     */
    public Astia(String nimi, double tilavuus) {
        this.nimi = nimi;
        this.tilavuus = tilavuus;
        this.maara = 0;
    }


    /**
     * Täyttää astian piripintaan asti
     * @example
     * <pre name="test">
     * Astia astia5l = new Astia("5l",5);
     * astia5l.getMaara()    ~~~ 0;
     * astia5l.tayta();
     * astia5l.getMaara()    ~~~ 5;
     * </pre>
     */
    public void tayta() {
        maara = tilavuus;
    }


    /**
     * Kaataa astiaan joko lisää tai vähentää, yli ei saa mennä eikä
     * pakkasen puolelle
     * @param lisa paljonko nestettä laitetaan
     * @return paljonko jäi mahtumatta
     * @example
     * <pre name="test">
     *    Astia a5 = new Astia("5",5);
     *    a5.kaada(3) ~~~ 0.0;     a5.getMaara() ~~~ 3.0;
     *    a5.kaada(5) ~~~ 3.0;     a5.getMaara() ~~~ 5.0;
     *    a5.kaada(-3) ~~~ 0.0;    a5.getMaara() ~~~ 2.0;
     *    a5.kaada(-3) ~~~ -1.0;   a5.getMaara() ~~~ 0.0;
     * </pre>
     */
    public double kaada(double lisa) {
        if ( lisa < 0 ) {
            double eiRiita = getMaara() + lisa;
            if ( eiRiita < 0 ) maara = 0;
            else { maara += lisa; eiRiita = 0; }
            return eiRiita;
        }
        
        double eiMahu = lisa - getTyhjaa();
        if ( eiMahu < 0 ) { maara += lisa; eiMahu = 0; }
        else { maara = tilavuus; }
        return eiMahu;
        
    }


    /**
     * Kaataa tästä astiasta (this) toiseen astiaan niin paljon kuin toiseen
     * mahtuu tai tästä astiasta on antaa.
     * @param astia astia johon kaadetaan
     * 
     * @example
     * <pre name="test">
     *  Astia ampari = new Astia("ä",100);
     *  Astia a8     = new Astia("8",8);
     *  Astia a5     = new Astia("5",5);
     *  
     *  ampari.kaada(a8);  a8.getMaara() ~~~ 0;     ampari.getMaara() ~~~ 0.0;
     *  ampari.tayta();
     *  ampari.kaada(a8);  a8.getMaara() ~~~ 8.0;   ampari.getMaara() ~~~ 92.0;
     *  
     *  a8.kaada(a5);      a8.getMaara() ~~~ 3.0;   a5.getMaara() ~~~ 5.0;
     *  a5.kaada(ampari);  a5.getMaara() ~~~ 0.0;   ampari.getMaara() ~~~ 97.0;
     *  a8.kaada(a5);      a8.getMaara() ~~~ 0.0;   a5.getMaara() ~~~ 3.0;
     *  ampari.kaada(a8);
     *  a8.kaada(a5);      a8.getMaara() ~~~ 6.0;   a5.getMaara() ~~~ 5.0;
     * </pre>
     */
    public void kaada(Astia astia) {
        double siirrettava = astia.getTyhjaa();
        if ( getMaara() < siirrettava ) siirrettava = getMaara();
        astia.kaada(siirrettava);
        kaada(-siirrettava);
    }


    /**
     * Palautetaan nsteen määrä astiassa 
     * @return nesteen määrä
     * @example
     * <pre name="test">
     * Astia astia5l = new Astia("5l",5);
     * astia5l.getMaara()    ~~~ 0;
     * astia5l.tayta();
     * astia5l.getMaara()    ~~~ 5;
     * </pre>
     */
    public double getMaara() {
        return maara;
    }


    /**
     * Palautetaan astion tilavuus
     * @return astian tilavuus
     * @example
     * <pre name="test">
     * Astia astia5l = new Astia("5l",5);
     * astia5l.getTilavuus() ~~~ 5;
     * </pre>
     */
    public double getTilavuus() {
        return tilavuus;
    }


    /**
     * Palautetaan astiossa olevan vapaan tilan määrä.
     * @return vapaan tilan määrä.
     * @example
     * <pre name="test">
     * Astia astia5l = new Astia("5l",5);
     * astia5l.getTyhjaa()   ~~~ 5;
     * astia5l.tayta();
     * astia5l.getTyhjaa()   ~~~ 0;
     * </pre>
     */
    public double getTyhjaa() {
        return getTilavuus() - getMaara();
    }


    /**
     * Palautetaan astian nimi
     * @return astian nimi
     * @example
     * <pre name="test">
     * Astia astia5l = new Astia("5l",5);
     * astia5l.getNimi()     === "5l";
     * </pre>
     */
    public String getNimi() {
        return nimi;
    }


    /**
     * Tutkitaan onko astian nimi etsitty
     * @param mika verrattava nimi
     * @return true jos on samat, false muuten
     * 
     * @example
     * <pre name="test">
     * Astia ampari  = new Astia("Ämpäri",100);
     * ampari.oletko("Ä") === false;
     * ampari.oletko("ämpäri") === true;
     * ampari.oletko("ÄMPÄRI") === true;
     * </pre>
     */
    public boolean oletko(String mika) {
        return getNimi().equalsIgnoreCase(mika);
    }
    
}