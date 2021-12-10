package demo.d10;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.*;
/**
 * Esimerkki linkitetystä listasta.
 * @author Vesa Lappalainen
 * @version 1.0, 15.03.2003
 */
public class Koulu {

  /**
   * Luokan yksi oppilas
   */
  public static class Oppilas {
    private final String nimi;
    private final double keskiarvo;
    private Oppilas seuraava;

    /**
     * Alustetaan oppilaan tiedot
     * @param nimi      oppilaan nimi
     * @param keskiarvo oppilaan keskiarvo
     */
    public Oppilas(String nimi, double keskiarvo) {
      this.nimi = nimi; this.keskiarvo = keskiarvo;
    }


    /**
     * Palautetaan oppilaan tiedot merkkijonona
     * @return oppilaan tiedot merkkijonona
     */
    @Override
    public String toString() {
      return Mjonot.fmt(nimi,-22) + " keskiarvo: " + Mjonot.fmt(keskiarvo,5,2);
    }
  }

  private final String luokka;
  private int oppilaita;
  private Oppilas ensimmainen;
  private Oppilas viimeinen;


  /**
   * Alustetaan luokka
   * @param luokka luokannimi
   */
  public Koulu(String luokka) { this.luokka = luokka; }


  /**
   * Lisätään uusi oppilas
   * @param oppilas lisättävä oppilas
   * @example
   * <pre name="test">
   * #import java.io.ByteArrayOutputStream;
   * #CLASSIMPORT
   *  ByteArrayOutputStream bs  = new ByteArrayOutputStream();
   *  String alku =  "%n%nLuokka: 1b oppilaita: %d%n" +
   *                 "==========================================%n";
   *  String loppu = "==========================================%n";
   *  String t = "";
   *
   *  Koulu luokka = new Koulu("1b");
   *
   *  luokka.lisaa(new Koulu.Oppilas($nimi,$ka));
   *  luokka.tulosta(bs);
   *  bs.toString() === String.format(alku+($oppilaat)+loppu,$maara); bs.reset();
   *
   *    $nimi       | $ka | $maara | $oppilaat
   *  -------------------------------------------------------------------------------
   *    ---         | --- |   0    | t=""
   *  "Ankka Aku"   | 5.0 |   1    | t+="Ankka Aku              keskiarvo:  5.00%n"
   *  "Ankka Tupu"  | 7.0 |   2    | t+="Ankka Tupu             keskiarvo:  7.00%n"
   *  "Hiiri Mikki" | 9.0 |   3    | t+="Hiiri Mikki            keskiarvo:  9.00%n"
   *
   *
   *  luokka.poistaKaikki();
   *
   *  luokka.lisaa(new Koulu.Oppilas($nimi,$ka));
   *  luokka.tulosta(bs);
   *  bs.toString() === String.format(alku+($oppilaat)+loppu,$maara);  bs.reset();

   *    $nimi       | $ka | $maara | $oppilaat
   *  -------------------------------------------------------------------------------
   *    ---         | --- |   0    | t=""
   *  "Hiiri Mikki" | 9.0 |   1    | t+="Hiiri Mikki            keskiarvo:  9.00%n"
   *
   * </pre>
   */
    // BYCODEBEGIN
    public void lisaa(Oppilas oppilas) {
        oppilaita++;
        if (ensimmainen == null) ensimmainen = oppilas;
        else viimeinen.seuraava = oppilas;
        viimeinen = oppilas;
    }

    /**
     * Poistaa kaikki oppilaat
     * @example
     * <pre name="test">
     * #import java.io.ByteArrayOutputStream;
     * #CLASSIMPORT
     * ByteArrayOutputStream bs  = new ByteArrayOutputStream();
     * String alku =  "%n%nLuokka: 7a oppilaita: %d%n" +
     *                "==========================================%n";
     * String loppu = "==========================================%n";
     * String t = "";
     *
     * Koulu luokka = new Koulu("7a");
     *
     * luokka.lisaa(new Koulu.Oppilas($nimi,$ka));
     * luokka.tulosta(bs);
     * bs.toString() === String.format(alku+($oppilaat)+loppu,$maara); bs.reset();
     *
     *   $nimi       | $ka | $maara | $oppilaat
     * -------------------------------------------------------------------------------
     *   ---         | --- |   0    | t=""
     * "Ankka Taavi" | 9.9 |   1    | t+="Ankka Taavi            keskiarvo:  9.90%n"
     * "Ankka Roope" | 8.5 |   2    | t+="Ankka Roope            keskiarvo:  8.50%n"
     *
     *
     * luokka.poistaKaikki();
     *  
     * luokka.tulosta(bs);
     * bs.toString() === String.format(alku+loppu,0);  bs.reset();
     * </pre>
     */
    public void poistaKaikki() {
        oppilaita = 0;
        ensimmainen = null;
    }
    // BYCODEEND

    /**
     * Tulostetaan luokan tiedot tietovirtaan.
     * @param os tietovirta, johon tulostetaan
     * @example
     * <pre name="test">
     * #import java.io.ByteArrayOutputStream;
     * #CLASSIMPORT
     * ByteArrayOutputStream bs  = new ByteArrayOutputStream();
     * String alku =  "%n%nLuokka: 5c oppilaita: %d%n" +
     *                "==========================================%n";
     * String loppu = "==========================================%n";
     * String t = "";
     *
     * Koulu luokka = new Koulu("5c");
     *
     * luokka.lisaa(new Koulu.Oppilas($nimi,$ka));
     * luokka.tulosta(bs);
     * bs.toString() === String.format(alku+($oppilaat)+loppu,$maara); bs.reset();
     *
     *   $nimi       | $ka  | $maara | $oppilaat
     * -------------------------------------------------------------------------------
     *   ---         | ---- |   0    | t=""
     * "Ankka Leenu" | 7.75 |   1    | t+="Ankka Leenu            keskiarvo:  7.75%n"
     * "Ankka Liinu" | 8.25 |   2    | t+="Ankka Liinu            keskiarvo:  8.25%n"
     * </pre>
     */
    public void tulosta(OutputStream os)  {
        PrintStream out = new PrintStream(os);
        out.println();
        out.println();
        out.println("Luokka: " + luokka + " oppilaita: " + oppilaita);
        out.println("==========================================");
        for (Oppilas oppilas = ensimmainen; oppilas != null; oppilas = oppilas.seuraava)
            out.println(oppilas);
        out.println("==========================================");
    }

  /**
   * Testataan luokkaa
   * @param args ei käytössä
   */
  public static void main(String[] args)  {
    Koulu luokka = new Koulu("1b");

    luokka.lisaa(new Oppilas("Ankka Aku",5.0));
    luokka.lisaa(new Oppilas("Ankka Tupu",7.0));
    luokka.lisaa(new Oppilas("Hiiri Mikki",9.0));

    luokka.tulosta(System.out);
    luokka.poistaKaikki();
    luokka.tulosta(System.out);
  }
}

