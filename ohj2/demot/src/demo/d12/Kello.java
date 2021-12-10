package demo.d12;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Luokka kellonaikaa varten
 * @author Herman
 * @version 26.7.2020
 *
 */
public class Kello {

    private int tunnit;
    private int minuutit;
    
    /**
     * Oletusmuodostaja 00:00
     * @example
     * <pre name="test">
     * Kello kello = new Kello();
     * kello.toString() === "00:00";
     * </pre>
     */
    public Kello() {
        //
    }
    
    /**
     * Kellon muodostaja
     * @param aika Aika, joka laitetaan kelloon
     * Kello kello = new Kello("12:00");
     * kello.toString() === "12:00";
     */
    public Kello(String aika) {
        if (tarkista(aika) != null) return;
        parse(aika);
    }
    
    /**
     * Parsii annetusta merkkijonosta ajan kelloon.
     * @param aika Merkkijono josta tehdään aika
     * @return Virheilmoitus jos ei pystytä parsimaan.
     * @example
     * <pre name="test">
     * Kello kello = new Kello();
     * kello.toString() === "00:00";
     * kello.parse("12.00");
     * kello.toString() === "12:00";
     * </pre>
     */
    public String parse(String aika) {
        if (tarkista(aika) != null) return tarkista(aika);
        String taika = aika.trim();
        StringBuilder sb = new StringBuilder(taika);
        int h, m;
        h = Mjonot.erotaInt(sb, tunnit);
        m = Mjonot.erotaInt(sb, minuutit);
        if (aika.matches("[0-9]+")) {
            m = Mjonot.erotaInt(taika.substring(2), tunnit);
            h = Mjonot.erotaInt(taika.substring(0, 2), minuutit);
        }
        tunnit = h;
        minuutit = m;
        return null;
    }
    
    /**
     * Muuttaa ajan merkkijonoksi
     * @example
     * <pre name="test">
     * Kello kello = new Kello("0202");
     * kello.toString() === "02:02";
     * </pre>
     */
    @Override
    public String toString() {
        return String.format("%02d", tunnit) + ":" + String.format("%02d", minuutit); 
    }
    
    /**
     * Tarkistaa onko annettu jono oikeaa muotoa kellonajaksi
     * @param jono Jono, jota tarkistetaan
     * @return null jos oikeaa muotoa, muussa tapauksessa kuvaava virheilmoitus
     * @example
     * <pre name="test">
     * tarkista("12:00") === null;
     * tarkista("12.00") === null;
     * tarkista("1200") === null;
     * tarkista("12") === null;
     * tarkista("1:00") === null;
     * tarkista("1.0") === null;
     * tarkista("12") === null;
     * tarkista("130:00") === "Anna aika muodossa hh:mm";
     * tarkista("12.001") === "Anna aika muodossa hh.mm";
     * tarkista("12001") === "Anna aika muodossa hhmm";
     * tarkista("120") === "Anna aika muodossa hh";
     * tarkista("kääk") === "Ajassa ei saa esiintyä kirjaimia tai muita erikoismerkkejä kuin . ja :";
     * tarkista("25:60") === "Tunnit täytyy olla välillä 0-24";
     * tarkista("2460") === "Minuutit täytyy olla välillä 0-59";
     * </pre>
     */
    public static String tarkista(String jono) {
        String tjono = jono.trim();
        if (!tjono.matches("[0-9.:]+"))
            return "Ajassa ei saa esiintyä kirjaimia tai muita erikoismerkkejä kuin . ja :";
        char[] valit = {':', '.'};
        String digits = "[0-9]{1,2}";
        for (int i = 0; i < valit.length; i++) {
            char vali = valit[i];
            if (tjono.indexOf(vali) >= 0 && !tjono.matches(digits + vali + digits))
                return "Anna aika muodossa hh" + vali + "mm";
        }
        int h, m;
        StringBuilder sb = new StringBuilder(tjono);
        h = Mjonot.erotaInt(sb, 0);
        m = Mjonot.erotaInt(sb, 0);
        if (tjono.matches("[0-9]+")) {
            if (tjono.length() == 3) return "Anna aika muodossa hh";
            if (tjono.length() > 4) return "Anna aika muodossa hhmm";
            m = Mjonot.erotaInt(tjono.substring(2), 0);
            h = Mjonot.erotaInt(tjono.substring(0, 2), 0);
        }
        if (h > 24)
            return "Tunnit täytyy olla välillä 0-24";
        if (m > 59)
            return "Minuutit täytyy olla välillä 0-59";
        return null;
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        System.out.println("12:00 Virhe: " + tarkista("12:00"));
        System.out.println("25:00 Virhe: " + tarkista("25:00"));
        System.out.println("1200 Virhe: " + tarkista("1200"));
        System.out.println("1.0 Virhe: " + tarkista("1.0"));
        System.out.println("120.0 Virhe: " + tarkista("120.0"));
    }

}
