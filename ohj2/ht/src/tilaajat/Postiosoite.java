package tilaajat;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;
import kanta.PostinumeroTarkistus;

/**
 * Tietää postiosoitteiden kentät. Osaa tarkistaa tietyn kentän oikeellisuuden.
 * Osaa muuttaa "00010|HELSINKI" -merkkijonon postiosoitteeksi.
 * Osaa antaa merkkijonona tietonsa, sekä asettaa uuden postitoimipaikan
 * @author Herman Lätti
 * @version Vaihe 7 24.7.2020
 *
 */
public class Postiosoite {
    
    private int postinumero; // vrt Jasen tunnusNro
    private String postitoimipaikka = ""; // vrt Jasen nimi
    
    /**
     * Postiosoitteen muodostaja
     * @param postinumero Postiosoitteen postinumero merkkijonona
     * @param postitoimipaikka Postiosoitteen postitoimipaikka
     */
    public Postiosoite(String postinumero, String postitoimipaikka) {
        this.postinumero = PostinumeroTarkistus.parsePostinumero(postinumero);
        this.postitoimipaikka = postitoimipaikka;
    }
    
    /**
     * Postiosoitteen muodostaja
     * @param postinumero Postiosoitteen postinumero
     * @param postitoimipaikka Postiosoitteen postitoimipaikka
     */
    public Postiosoite(int postinumero, String postitoimipaikka) {
        this.postinumero = postinumero;
        this.postitoimipaikka = postitoimipaikka;
    }
    
    /**
     * Postiosoitteen muodostaja
     * @param postinumero Postiosoitteen postinumero
     */
    public Postiosoite(int postinumero) {
        this.postinumero = postinumero;
    }
    
    /**
     * Oletusmuodostaja, postinumero 00000
     */
    public Postiosoite() {
        // Oletusalustus, postinumero = 00000
    }
    
    /**
     * Saantimetodi postinumerolle
     * @return Palauttaa postinumeron
     * @example
     * <pre name="test">
     * Postiosoite helsinki = new Postiosoite();
     * helsinki.getPostinumero() === 0;
     * helsinki = new Postiosoite(10);
     * helsinki.getPostinumero() === 10;
     * </pre>
     */
    public int getPostinumero() {
        return postinumero;
    }
    
    /**
     * Palauttaa postiosoitteen postitoimipaikan
     * @return Palauttaa postitoimipaikan
     * @example
     * <pre name="test">
     * Postiosoite helsinki = new Postiosoite();
     * helsinki.getPostitoimipaikka() === "";
     * helsinki.taytaHelsinkiTiedoilla();
     * helsinki.getPostitoimipaikka() =R= "HELSINKI.*";
     * </pre>
     */
    public String getPostitoimipaikka() {
        return postitoimipaikka;
    }
    
    /**
     * Asetusmetodi postitoimipaikkaa varten
     * @param postitoimipaikka Postiosoitteelle asetettava postitoimipaikka
     */
    public void setPostitoimipaikka(String postitoimipaikka) {
        this.postitoimipaikka = postitoimipaikka;
    }
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot postiosoitteelle
     */
    public void taytaHelsinkiTiedoilla() {
        postitoimipaikka = "HELSINKI" + PostinumeroTarkistus.rand(0, 100);
    }
    
    /**
     * Tulostetaan postiosoite
     * @param out Tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(PostinumeroTarkistus.postinumeroJonona(postinumero) + " " + postitoimipaikka);
    }
    
    /**
     * Tulostetaan tilaajan tiedot
     * @param os Tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    /**
     * Palauttaa postiosoitteen tiedot merkkijonona, jonka voi tallettaa tiedostoon
     * @return Postiosoitteen tiedot '|' -erotellussa merkkijonossa
     * @example
     * <pre name="test">
     * Postiosoite helsinki = new Postiosoite("00010", "HELSINKI");
     * helsinki.toString() === "00010|HELSINKI";
     * </pre>
     */
    @Override
    public String toString() {
        return PostinumeroTarkistus.postinumeroJonona(getPostinumero()) + "|" + getPostitoimipaikka();
    }
    
    /**
     * Selvittää postiosoitteen tiedot '|' -erotellusta merkkijonosta
     * @param rivi Merkkijono, josta postiosoitteen tiedot erotetaan
     * @example
     * <pre name="test">
     * Postiosoite postiosoite = new Postiosoite();
     * postiosoite.parse("   02020   |  METSÄ   ");
     * postiosoite.getPostinumero() === 2020;
     * postiosoite.getPostitoimipaikka() === "METSÄ";
     * postiosoite.toString() === "02020|METSÄ";
     * 
     * postiosoite.parse("500|");
     * postiosoite.toString() === "00500|METSÄ"; // Postitoimipaikka ei vaihdu tyhjäksi
     * </pre>
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        postinumero = PostinumeroTarkistus.parsePostinumero(Mjonot.erota(sb, '|', PostinumeroTarkistus.postinumeroJonona(postinumero)));
        postitoimipaikka = Mjonot.erota(sb, '|', postitoimipaikka);
    }
    
    /**
     * @param args Ei käytössä
     */
    public static void main(String args[]) {
        var helsinki = new Postiosoite(PostinumeroTarkistus.satunnainenPostinumero(0, 990)); 
        var helsinki2 = new Postiosoite(PostinumeroTarkistus.satunnainenPostinumero(0, 990));
        
        helsinki.tulosta(System.out);
        helsinki.taytaHelsinkiTiedoilla();
        helsinki.tulosta(System.out);
        
        helsinki2.tulosta(System.out);
        helsinki2.taytaHelsinkiTiedoilla();
        helsinki2.tulosta(System.out);
    }
}