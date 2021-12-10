package demo.d5;

import java.io.*;
import fi.jyu.mit.ohj2.*;

/**
 * Henkilo-luokka.  Henkilöllä on nimi ja syntymävuosi.
 * @author Vesa Lappalainen
 * @version 1.0, 07.02.2003
 */
public class Henkilo {
    private String etunimi;
    private String sukunimi;
    private int syntymavuosi;


    /**
     * Alustetaan henkilön tiedot annetuilla arvoilla
     * @param etunimi henkilön uusi etunimi
     * @param sukunimi henkilön uusi sukunimi
     * @param syntymavuosi henkilön uusi syntymävuosi
     */
    public Henkilo(String etunimi, String sukunimi, int syntymavuosi) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.syntymavuosi = syntymavuosi;
    }

    /**
     * @return tiedot | erotetussa muodossa
     * @example
     * <pre name="test">
     *   Henkilo aku = new Henkilo("Aku","Ankka",1934);
     *   aku.toString() === "Aku|Ankka|1934";
     *   Henkilo pele = new Henkilo("Pele","",1940);
     *   pele.toString() === "Pele||1940";
     * </pre>
     */
    @Override
    public String toString() {
        return etunimi + "|" + sukunimi + "|" + syntymavuosi;
    }


    /**
     * Palautetaan henkilön koko nimi
     * @return henkilön nimi muodossa etunimi sukunimi
     * @example
     * <pre name="test">
     *   Henkilo aku = new Henkilo("Aku","Ankka",1934);
     *   aku.getNimi() === "Aku Ankka";
     *   Henkilo pele = new Henkilo("Pele","",1940);
     *   pele.getNimi() === "Pele";
     * </pre>
     */
    public String getNimi() {
        return (etunimi + " " + sukunimi).trim();
    }


    /**
     * @return henkilön syntymävuosi
     * @example
     * <pre name="test">
     *   Henkilo aku = new Henkilo("Aku","Ankka",1934);
     *   aku.getSyntymavuosi() === 1934;
     * </pre>
     */
    public int getSyntymavuosi() {
        return syntymavuosi;
    }


    /**
     * Tulostetaan henkilön tiedot paramterina tuotuu tietovirtaan
     * @param os tietovirta johon tiedot tulostetaan
     * @example
     * <pre name="test">
     * #import java.io.*;
     * ByteArrayOutputStream byteoutput = new ByteArrayOutputStream();
     * Henkilo hlo = new Henkilo("Aku","Ankka",1934);
     * hlo.tulosta(byteoutput);
     * byteoutput.toString() =R= "Aku Ankka 1934\\s*"
     * </pre>
     */
    public void tulosta(OutputStream os) {
        PrintStream out = new PrintStream(os);
        out.println(etunimi + " " + sukunimi + " " + syntymavuosi);
    }

    
    // BYCODEBEGIN
    /**
     * Oletusmuodostaja, alustetaan kaikki oletusarvoon 
     */
    public Henkilo() {
        this("","",0);
    }

    /**
     * Muodostaja, yksi nimi tai etu- ja sukunimi erotettuna välimerkillä, alustetaan vuosi oletusarvoon
     * @param nimi Henkilön nimi
     */
    public Henkilo(String nimi) {
        parse(nimi);
    }

    /**
     * Selvittää henkilön tiedot välimerkeillä erotetusta merkkijonosta
     * @param tiedot Henkilön tiedot merkkijonossa erotettuna välimerkillä
     * @example
     * <pre name="test">
     * Henkilo aku = new Henkilo();
     * aku.parse("Aku|Ankka|1934");
     * aku.toString() === "Aku|Ankka|1934";
     * aku.parse("Aku|Ankka|");
     * aku.toString() === "Aku|Ankka|0";
     * aku.parse("Ak");
     * aku.toString() === "Ak||0";
     * aku.parse("");
     * aku.toString() === "||0";
     * </pre>
     */
    public void parse(String tiedot) {
        StringBuilder s = new StringBuilder(tiedot);
        etunimi = Mjonot.erota(s, '|');
        sukunimi = Mjonot.erota(s, '|');
        syntymavuosi = Mjonot.erotaInt(s, 0);
    }
    // BYCODEEND

    /**
     * Testataan henkilöluokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Henkilo hlo = new Henkilo();
        Henkilo aku = new Henkilo("Aku", "Ankka", 1934);
        System.out.println(hlo);
        hlo.parse("Sepe|Susi|1933");
        hlo.tulosta(System.out);
        aku.tulosta(System.out);
        Henkilo mikki = new Henkilo("Mikki|Hiiri");
        System.out.println(mikki);
    }
}


