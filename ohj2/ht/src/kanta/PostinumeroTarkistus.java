package kanta;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Herman Lätti
 * @version Vaihe 7 24.7.2020
 *
 */
public class PostinumeroTarkistus {
    
    private final static int    PITUUS   = 5;
    private final static String FORMAT   = "%0" + PITUUS + "d";
    private final static String SALLITUT = "0123456789";
    
    /**
     * Tarkistaa postinumeron
     * @param postinumero Postinumero jota tarkastetaan merkkijonona
     * @return null jos oikein, muuten virheitä kuvaava teksti
     * @example
     * <pre name="test">
     * tarkista("00010") === null;
     * tarkista("000010") === "liian pitkä";
     * tarkista("0010") === "liian lyhyt";
     * tarkista("a0010") === "vain numeroita";
     * </pre>
     */
    public static String tarkista(String postinumero) {
        if (!postinumero.matches("[" + SALLITUT + "]+")) 
            return "vain numeroita";
        int ppituus = postinumero.length();
        if (ppituus < PITUUS) return "liian lyhyt";
        if (ppituus > PITUUS) return "liian pitkä";
        return null;
    }
    
    /**
     * Palauttaa postinumeron kokonaislukuna
     * @param postinumero Postinumero merkkijonona
     * @return Postinumero kokonaislukuna, < 0 jos ei validi
     * @example
     * <pre name="test">
     * parsePostinumero("00010") === 10; // etunollat: ei saa muuttua binääriluvuksi
     * parsePostinumero("10000") === 10000;
     * parsePostinumero("89342") === 89342;
     * parsePostinumero("10k") === 10;
     * parsePostinumero("k") === -1; // virheellinen syöte
     * parsePostinumero("") === -1; // tyhjä postinumero
     * parsePostinumero("00000") === 0;
     * parsePostinumero("0") === 0;
     * </pre>
     */
    public static int parsePostinumero(String postinumero) {
        return Mjonot.erotaInt(postinumero, -1);
    }
    
    /**
     * Palauttaa postinumeron merkkijonona
     * @param nro Postinumero, joka halutaan muuttaa merkkijonoksi
     * @return Annettu postinumero merkkijonona
     * @example
     * <pre name="test">
     * postinumeroJonona(10) === "00010";
     * postinumeroJonona(10000) === "10000";
     * postinumeroJonona(00010) === "00008"; // ei oteta kantaa binäärisyötteisiin
     * postinumeroJonona(123) === "00123";
     * postinumeroJonona(12345) === "12345";
     * postinumeroJonona(1234567) === "1234567"; // ei oteta kantaa liian pitkiin
     * postinumeroJonona(-1) === ""; // negatiivinen postinumero merkki virheestä, palautetaan tyhjä merkkijono
     * </pre>
     */
    public static String postinumeroJonona(int nro) {
        if (nro < 0) return "";
        return String.format(FORMAT, nro);
    }
    
    /**
     * Arvotaan satunnainen kokonaisluku välille [ala, yla]
     * @param ala Arvonnan alaraja
     * @param yla Arvonnan yläraja
     * @return Satunnainen luku väliltä [ala, yla]
     */
    public static int rand(int ala, int yla) {
        double n = (yla - ala) * Math.random() + ala;
        return (int)Math.round(n);
    }
    
    /**
     * Arpoo satunnaisen postinumeron väliltä 00000 - 99999
     * @return Palauttaa satunnaisen postinumeron
     */
    public static int satunnainenPostinumero() {
        return rand(0, 99999);
    }
    
    /**
     * Arpoo satunnaisen postinumeron annetulta väliltä
     * @param ala Postinumeron alaraja kokonaislukuna
     * @param yla Postinumeron yläraja kokonaislukuna
     * @return Palauttaa satunnaisen postinumeron
     */
    public static int satunnainenPostinumero(int ala, int yla) {
        return rand(ala, yla);
    }
    
    /**
     * Arpoo satunnaisen postinumeron annetulta väliltä
     * @param ala Postinumeron alaraja
     * @param yla Postinumeron yläraja
     * @return Palauttaa satunnaisen postinumeron
     */
    public static int satunnainenPostinumero(String ala, String yla) {
        return rand(parsePostinumero(ala), parsePostinumero(yla));
    }
}
