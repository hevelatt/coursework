package demo.d3;

import fi.jyu.mit.ohj2.Mjonot;


/**
 * Testiohjelma etu -ja sukunimen vaihtamiseksi keskenään.
 * @author Vesa Lappalainen
 * @version 1.0, 10.01.2002
 */
public class Etusuku {

   /**
    * Erottaa jonosta valitun merkin kohdalta alkuosan ja loppuosan.
    * Alkuosa palautetaan funktion nimessä ja loppuosa jätetään
    * jonoon.  Merkin etsimissuunta voidaan valita (oletuksena alusta päin).
    * Jos merkkiä ei löydy, palautetaan koko jono ja tyhjennetään jono.
    * @param jono           jono jota pilkotaan
    * @param merkki         merkki jonka kohdalta katkaistaan
    * @param etsitakaperin  etsitäänkö merkki oikealta vasemmalle
    * @return               merkkiä edeltävä jono
    * @example
    * <pre name="test">
    *   StringBuilder jono = new StringBuilder("123 456"); String s;
    *   s = erota(jono,' ',false); jono.toString() === "456"; s === "123";
    *   s = erota(jono,' ',false); jono.toString() === "";    s === "456";
    *   jono = new StringBuilder("1 2 3");
    *   erota(jono,' ',false) === "1";
    *   erota(jono,' ',false) === "2";
    *   erota(jono,' ',false) === "3";
    *   erota(jono,' ',false) === "";
    *   erota(jono,' ',false) === "";
    *   jono = new StringBuilder("123 456 789");
    *   erota(jono,' ',true) === "123 456"
    *   erota(jono,' ',true) === "789"
    * </pre>
    */
   public static String erota(StringBuilder jono, char merkki, boolean etsitakaperin) {
       int p;
       if (!etsitakaperin)
           p = jono.indexOf("" + merkki); // NOPMD
       else
           p = jono.lastIndexOf("" + merkki);
       String alku;
       if (p < 0) {
           alku = jono.toString();
           jono.delete(0, jono.length());
           return alku;
       }
       alku = jono.substring(0, p);
       jono.delete(0, p + 1);
       return alku;
   }


   /**
    * Erottaa jonosta merkin kohdalta seuraavan palasen.
    * @param jono käsiteltävä jono
    * @param merkki merkki jonka kohdalta erotetaan
    * @return seuraava palanen
    * @example
    * <pre name="test">
    *   StringBuilder jono = new StringBuilder("1 2 3");
    *   erota(jono,' ') === "1";
    *   erota(jono,' ') === "2";
    *   erota(jono,' ') === "3";
    *   erota(jono,' ') === "";
    * </pre>
    */
   public static String erota(StringBuilder jono, char merkki) {
       return erota(jono, merkki, false);
   }


   /**
    * Erottaa jonosta välilyönnin kohdalta seuraavan palasen.
    * @param jono käsiteltävä jono
    * @return seuraava palanen
    * @example
    * <pre name="test">
    *   StringBuilder jono = new StringBuilder("1 2 3");
    *   erota(jono) === "1";
    *   erota(jono) === "2";
    *   erota(jono) === "3";
    *   erota(jono) === "";
    * </pre>
    */
   public static String erota(StringBuilder jono) {
       return erota(jono, ' ', false);
   }


   // BYCODEBEGIN
   /**
    * Vaihtaa annetun merkkijonon alku- ja loppuosat keskenään.
    * @param jono Merkkijono, jonka alku- ja loppuosa vaihdetaan.
    * @param katkaisu Merkki, josta jono katkaistaan alku- ja loppuosaan.
    * @param lopusta Lähdetäänkö katkaisumerkkiä etsimään lopusta? 
    * @return Jono, josta on vaihdettu alku- ja loppuosa keskenään.
    * @example
    * <pre name="test">
    *   vaihdaAlkuLoppu("", ' ', false) === "";
    *   vaihdaAlkuLoppu(" ", ' ', false) === "";
    *   vaihdaAlkuLoppu("Pele", ' ', false) === "Pele";
    *   vaihdaAlkuLoppu("Aku Ankka" , ' ', true) === "Ankka Aku"
    *   vaihdaAlkuLoppu("Jukka Pekka Palo", ' ', true) === "Palo Jukka Pekka"
    *   vaihdaAlkuLoppu("Ankka Aku", ' ', false) === "Aku Ankka"
    *   vaihdaAlkuLoppu("Palo Jukka Pekka", ' ', false) === "Jukka Pekka Palo"
    * </pre>
    */
   public static String vaihdaAlkuLoppu(String jono, char katkaisu, boolean lopusta) {
       StringBuilder osa1 = new StringBuilder(jono);
       String osa2 = Mjonot.erota(osa1, katkaisu, lopusta);
       if (osa1.length() == 0) return osa2;
       return osa1.toString() + katkaisu + osa2;
   }


   /**
    * Vaihtaa etunimen ja sukunimen keskenään,
    * Jukka Pekka Palo => Palo Jukka Pekka
    * @param nimi Etunimi Sukunimi jota vaihdetaan
    * @return Sukunimi Etunimi
    * @example
    * <pre name="test">
    *   vaihdaEtuSuku("") === ""
    *   vaihdaEtuSuku(" ") === ""
    *   vaihdaEtuSuku("Pele") === "Pele"
    *   vaihdaEtuSuku("Aku Ankka") === "Ankka Aku"
    *   vaihdaEtuSuku("Jukka Pekka Palo") === "Palo Jukka Pekka"
    * </pre>
    */
   public static String vaihdaEtuSuku(String nimi) {
       return vaihdaAlkuLoppu(nimi, ' ', true);
   }


   /**
    * Vaihtaa sukunimen ja etunimen keskenään,
    * Palo Jukka Pekka => Jukka Pekka Palo
    * @param nimi Sukunimi Etunimi jota vaihdetaan
    * @return Etunimi Sukunimi
    * @example
    * <pre name="test">
    *   vaihdaSukuEtu("") === ""
    *   vaihdaSukuEtu("Pele") === "Pele"
    *   vaihdaSukuEtu("Ankka Aku") === "Aku Ankka"
    *   vaihdaSukuEtu("Palo Jukka Pekka") === "Jukka Pekka Palo"
    * </pre>
    */
   public static String vaihdaSukuEtu(String nimi) {
       return vaihdaAlkuLoppu(nimi, ' ', false);
   }
   // BYCODEEND


    /**
     * Testaa sukunimen ja etunimen vaihtamista molempiin suuntiin
     *  @param nimi tukittava nimi
     *  @param tulos mikä pitäisi tulla tulokseksi
     *  @return 0 jos oikein, 1 tai 2 jos väärin
     */
    private static int nimitesti(String nimi, String tulos) {
        String vaihdettu = vaihdaEtuSuku(nimi);
        String takaisin = vaihdaSukuEtu(tulos);
        String tulos2 = nimi.trim();
        int virheita = 0;
        if (!vaihdettu.equals(tulos)) {
            System.out.printf("'%s' => '%s'! Pitäisi olla: '%s'\n", nimi, vaihdettu, tulos);
            virheita++;
        }
        if (!takaisin.equals(tulos2)) {
            System.out.printf("'%s' => '%s'! Pitäisi olla: '%s'\n", tulos, takaisin, tulos2);
            virheita++;
        }
        return virheita;
    }


    /**
     * Testaan etu- ja sukunimen vaihtamista.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        int v = 0;
        v += nimitesti("Jukka Pekka Palo", "Palo Jukka Pekka");
        v += nimitesti("Pele", "Pele");
        v += nimitesti("Aku Ankka", "Ankka Aku");
        v += nimitesti("", "");
        v += nimitesti(" ", "");
        if (v == 0)
            System.out.println("OK");
        else
            System.out.println("Virheitä: " + v);
    }
}