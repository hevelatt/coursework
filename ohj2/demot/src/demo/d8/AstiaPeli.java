package demo.d8;

import java.util.ArrayList;
import java.util.List;

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
 *  joko siten, että astian koko sisältö tyhjennetään jos se mahtuu
 *  tai astista kaadetaan sen verran että toinen astia tulee täyteen.
 *<br>
 *  Tee ohjelma jota voitasiin käyttää seuraavasti:
 *<br>
 *<pre>
 *  Käytössäsi on 8 ja 5 litran astia ja Ämpäri (100 l)
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
 * Toteutettu peli-luokaksi, jossa käytetään hyväksi Astia2-luokkaa.
 * Tarkistaa myös pelin aikana löydetyt kombinaatiot
 * Valitettavasti AstiaPeliin piti lisätä metodit getTilavuus ja getMaara
 * @author  Vesa Lappalainen
 * @version 1.0, 09.02.2003
 * @version 1.1, 14.02.2003
 * @version 1.2, 15.02.2003
 * @version 1.3, 13.02.2011
 */
public class AstiaPeli {
    // #import fi.jyu.mit.ohj2.Suuntaaja;

    private ArrayList<Astia> astiat = new ArrayList<Astia>();
    private Esiintymat esiintymat;


    /**
     * Astiapelin alustus.  Laitetaan aina ämpäri mukaan.
     * @example
     * <pre name="test">
     *    AstiaPeli peli = new AstiaPeli();
     *    peli.etsi("ä").getTilavuus() ~~~ 100.0;
     * </pre>
     */
    public AstiaPeli() {
        lisaaYksiAstia("ä", 100);
        getAmpari().tayta();
    }


    /**
     * Palauttaa ämpärin
     * @return viite ämpäriin
     * @example
     * <pre name="test">
     *    AstiaPeli peli = new AstiaPeli();
     *    peli.getAmpari().getTilavuus() ~~~ 100.0;
     * </pre>
     */
    public final Astia getAmpari() {
        return anna(0);
    }
    
    
    /**
     * Palautetaan astioiden lukumäärä pelissä
     * @return astioiden lukumäärä
     * @example
     * <pre name="test">
     *    AstiaPeli peli = new AstiaPeli();
     *    peli.getLkm() === 1;
     * </pre>
     */
    public int getLkm() {
        return astiat.size();
    }


    /**
     * Lisätään uusi astia peliin.  Luodaan tätä varten uusi astia.
     * Apumetodi muodostajasta kutsuttavaksi koska muodostajasta saa kutsua
     * vain final metodeja.
     * @param nimi lisättävän astian nimi
     * @param tilavuus lisättävän astian tilavuus
     * @return lisätty astia
     */
    private final Astia lisaaYksiAstia(String nimi, double tilavuus) {
        Astia astia = new Astia(nimi, tilavuus); 
        astiat.add(astia);
        return astia;
    }


    /**
     * Lisätään uusi astia peliin.  Luodaan tätä varten uusi astia.
     * @param nimi lisättävän astian nimi
     * @param tilavuus lisättävän astian tilavuus
     * @return lisätty astia
     * @example
     * <pre name="test">
     *  AstiaPeli peli = new AstiaPeli();
     *  peli.getLkm() === 1;
     *  peli.lisaaAstia("5",5);
     *  peli.getLkm() === 2;
     *  peli.lisaaAstia("8",8);
     *  peli.getLkm() === 3;
     * </pre>
     */
    public Astia lisaaAstia(String nimi, double tilavuus) {
        Astia astia = lisaaYksiAstia(nimi, tilavuus); 
        alusta();
        return astia;
    }


    /**
     * Palautetaan i:s astia joukosta
     * @param i monesko astia
     * @return i's astia
     * @example
     * <pre name="test">
     *  AstiaPeli peli = new AstiaPeli();
     *  peli.anna(0).getNimi() === "ä";
     *  peli.lisaaAstia("5",5);
     *  peli.anna(1).getNimi() === "5";
     * </pre>
     */
    public Astia anna(int i) {
        return astiat.get(i);
    }


    /**
     * Palautetaan astioissa olevat nestemäärät ämpäriä lukuunottamatta
     * @return astioissa olevat nestemäärät merkkijonona
     * @example
     * <pre name="test">
     *  AstiaPeli peli = new AstiaPeli();  peli.lisaaAstia("5",5);  peli.lisaaAstia("8",8);
     *  peli.getMaarat() === "0.0 0.0";
     *  peli.kaada(peli.etsi("ä"),peli.etsi("8")); peli.getMaarat() === "0.0 8.0";
     *  peli.kaada(peli.etsi("8"),peli.etsi("5")); peli.getMaarat() === "5.0 3.0";
     * </pre>
     */
    public String getMaarat() {
        StringBuilder tulos = new StringBuilder();
        for (int i = 1; i < getLkm(); i++)
            tulos.append(anna(i).getMaara() + " ");
        return tulos.toString().trim();
    }

    /**
     * Saantimetodi esiintymille
     * @return Esiintymät
     * TODO: testit
     */
    public Esiintymat getEsiintymat() {
        return esiintymat;
    }

    /**
     * Etsii sen astian, jolla on annettu nimi.
     * @param nimi etsittävän astian nimi
     * @return astian viite tai null jos ei löydy
     * 
     * @example
     * <pre name="test">
     *  AstiaPeli peli = new AstiaPeli();  peli.lisaaAstia("5",5);  peli.lisaaAstia("8",8);
     *  peli.etsi("ä").oletko("ä") === true;
     *  peli.etsi("Ä").oletko("ä") === true;
     *  peli.etsi("5").getTilavuus() ~~~ 5.0;
     *  peli.etsi("8").getTilavuus() ~~~ 8.0;
     *  peli.etsi("9") === null;
     * </pre>
     */
    public Astia etsi(String nimi) {
        for (Astia astia : astiat)
            if ( astia.oletko(nimi) ) return astia;
        return null;
    }


    
    /**
     * Kaataa astiasta toiseen
     * @param mista mistä astiasta kaadetaan
     * @param mihin mihin astiaan kaadetaan.
     * @example
     * <pre name="test">
     *  AstiaPeli peli = new AstiaPeli();  peli.lisaaAstia("5",5);  peli.lisaaAstia("8",8);
     *  peli.getMaarat() === "0.0 0.0";
     *  peli.kaada(peli.etsi("ä"),peli.etsi("8")); peli.getMaarat() === "0.0 8.0";
     *  peli.kaada(peli.etsi("8"),peli.etsi("5")); peli.getMaarat() === "5.0 3.0";
     * </pre>
     */
    public void kaada(Astia mista, Astia mihin) {
        mista.kaada(mihin);
        lisaaEsiintymat(); // ver.1.2
    }
    
    /**
     * Alustetaan peli astioiden määrän mukaan.
     */
    private void alusta() {
        int yla = (int)(astioidenSumma()); // ver.1.2
        esiintymat = new Esiintymat(1, yla); // ver.1.2
    }

    /**
     * @param i minkä astia määrä halutaan
     * @return i:n astian määrän
     * @example
     * <pre name="test">
     *  AstiaPeli peli = new AstiaPeli();
     *  peli.getMaara(0) ~~~ 100;
     *  Astia a5 = peli.lisaaAstia("5",5);
     *  peli.getMaara(1) ~~~ 0;
     *  peli.kaada(peli.getAmpari(),a5); peli.getMaara(1) ~~~ 5;
     * </pre>
     */
    public double getMaara(int i) {
        return anna(i).getMaara();
    }


    /**
     * @param i minkä astian tilavuus
     * @return i:n astian tilavuus
     * @example
     * <pre name="test">
     *  AstiaPeli peli = new AstiaPeli();
     *  peli.getTilavuus(0) ~~~ 100;
     *  peli.lisaaAstia("5",5);
     *  peli.getTilavuus(1) ~~~ 5;
     * </pre>
     */
    public double getTilavuus(int i) {
        return anna(i).getTilavuus();
    }


    /* Version 1.2 tarvitsemat lisäykset */

    /**
     * Laskee kaikkien käytössä olevien astioiden yhteistilavuuden
     * lukuunottamatta ämpäriä
     * @return yhteistilavuus
     * @example
     * <pre name="test">
     *  AstiaPeli peli = new AstiaPeli();
     *  peli.astioidenSumma() ~~~ 0;
     *  peli.lisaaAstia("5",5); peli.astioidenSumma() ~~~ 5;
     *  peli.lisaaAstia("8",8); peli.astioidenSumma() ~~~ 13;
     * </pre>
     */
    public double astioidenSumma() {
        double summa = 0;
        for (int i = 1; i < getLkm(); i++)
            summa += getTilavuus(i);
        return summa;
    }


    /**
     * Lisää esiintymat-taulukkoon kaikki ne astioiden summakombinaatiot,
     * joita niistä saa tällä hetkellä.
     * @example
     * <pre name="test">
     *  AstiaPeli peli = new AstiaPeli();
     *  Astia ampari = peli.getAmpari();
     *  Astia a1 = peli.lisaaAstia("1",1); 
     *  Astia a2 = peli.lisaaAstia("2",2);
     *  peli.lisaaEsiintymat();  
     *  peli.getLoydetyt().toString() === "[]";
     *  ampari.kaada(a1); peli.lisaaEsiintymat();
     *  peli.getLoydetyt().toString() === "[1]";
     *  ampari.kaada(a2); peli.lisaaEsiintymat();
     *  peli.getLoydetyt().toString() === "[1, 2, 3]";
     * </pre>
     */
    public void lisaaEsiintymat() {
        Kombinaatiot kombi = new Kombinaatiot();
        for (int i = 1; i < getLkm(); i++)
            kombi.lisaa((int)(Math.round(getMaara(i))));

        for (int summa : kombi)
            esiintymat.lisaa(summa);
    }

    /**
     * Palautetaan tieto siitä, onko peli mennyt läpi.
     * @return true jos peli on mennyt läpi.
     * @example
     * <pre name="test">
     *  AstiaPeli peli = new AstiaPeli();
     *  Astia ampari = peli.getAmpari();
     *  Astia a1 = peli.lisaaAstia("1",1); 
     *  Astia a2 = peli.lisaaAstia("2",2);
     *  peli.kaada(ampari,a1); peli.lapi() === false;
     *  peli.kaada(ampari,a2); peli.lapi() === true;
     * </pre>
     */
    public boolean lapi() {
        return esiintymat.onkoKaikki(); 
    }
    
    
    /**
     * Palautetaan löydettyjen lista.
     * @return löydetyjen tilavuuksien lista
     * @example
     * <pre name="test">
     *  AstiaPeli peli = new AstiaPeli();
     *  Astia ampari = peli.getAmpari();
     *  Astia a1 = peli.lisaaAstia("1",1); 
     *  Astia a2 = peli.lisaaAstia("2",2);
     *  peli.getLoydetyt().toString() === "[]";
     *  peli.kaada(ampari,a1); peli.getLoydetyt().toString() === "[1]";
     *  peli.kaada(ampari,a2); peli.getLoydetyt().toString() === "[1, 2, 3]";
     * </pre>
     */
    public List<Integer> getLoydetyt() {
        return esiintymat.getLoydetyt();
    }
}