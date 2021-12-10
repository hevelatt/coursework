package demo.d10;
import demo.d9.*;
/**
 * Esimerkki dynaamisesta taulukosta Java 1.5:n geneerisyyttä
 * ja "autoboxingia" käyttäen.
 * @author Vesa Lappalainen @version 1.0, 02.03.2002
 * @version 1.1, 01.03.2005
 * @author Santtu Viitanen @version 1.2, 21.07.2011
 * @param <TYPE> Tyyppi jota talletetaan
 */
public class TaulukkoGen<TYPE extends Int> implements Cloneable  {
    /** Luokka täyden taulukon poikkeusta varten  */
    public static class TaulukkoTaysiException extends Error {
        private static final long serialVersionUID = 1L;


        TaulukkoTaysiException(String viesti) {
            super(viesti);
        }
    }

    private TYPE alkiot[];
    private int lkm;


    /**
     * Alustetaan 5 kokoinen taulukko
     */
    public TaulukkoGen() {
        this(5);
    }


    /**
     * Alutetaan valitun kokoinen taulukko
     * @param koko taulukon koko
     */
    @SuppressWarnings("unchecked")
    public TaulukkoGen(int koko) {
        // alkiot = (TYPE[]) new Object[koko];
        alkiot = (TYPE[])new Int[koko];  // Jos rajattu geneerisyys
        // alkiot = new TYPE[koko];
    }


    /**
     * Lisätään taulukkon uusi alkio
     * @param alkio lisättävän alkion viite
     * @throws TaulukkoTaysiException jos taulukko jo täysi
     */
    public void lisaa(TYPE alkio) throws TaulukkoTaysiException {
        if (lkm >= alkiot.length) throw new TaulukkoTaysiException("Tila loppu");
        alkiot[lkm++] = alkio;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        String erotin = "";
        for (int i = 0; i < lkm; i++) {
            s.append(erotin + alkiot[i]);
            erotin = ",";
        }
        return s.toString();
    }


    /**
     * Muutetaan paikan i arvo
     * @param i mihin paikkaa asetetaan
     * @param alkio uuden alkion viite
     * @throws IndexOutOfBoundsException jos indeksiväärin
     */
    public void set(int i, TYPE alkio) throws IndexOutOfBoundsException {
        if ((i < 0) || (lkm <= i)) throw new IndexOutOfBoundsException("i = " + i);
        alkiot[i] = alkio;
    }


    /**
     * Paikassa i olevan alkion viite
     * @param i mistä paikasta
     * @return paikassa i oleva viite
     * @throws IndexOutOfBoundsException jos indeksi väärin
     */
    public TYPE get(int i) throws IndexOutOfBoundsException {
        if ((i < 0) || (lkm <= i)) throw new IndexOutOfBoundsException("i = " + i);
        return alkiot[i];
    }

    /**
     * Kloonaa olion
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * #import demo.d9.Int;
     *  TaulukkoGen<Int> luvut = new TaulukkoGen<Int>(20);
     *  luvut.lisaa(new Int(0)); luvut.lisaa(new Int(2));
     *  luvut.lisaa(new Int(99));
     *  TaulukkoGen<Int> taul = luvut.clone();
     *  luvut.toString() === "0,2,99";
     *  taul.toString()  === "0,2,99";
     *  luvut.get(1).set(3);
     *  luvut.toString() === "0,3,99";
     *  taul.toString()  === "0,2,99";
     *  luvut.lisaa(new Int(2)); luvut.lisaa(new Int(5));
     *  luvut.lisaa(new Int(2)); luvut.lisaa(new Int(6));
     *  luvut.toString() === "0,3,99,2,5,2,6";
     *  taul.toString()  === "0,2,99";
     *  taul.get(3).intValue() === 2; #THROWS IndexOutOfBoundsException
     *  luvut.poista(new Int(2));
     *  luvut.toString() === "0,3,99,5,6";
     *  taul.toString()  === "0,2,99";
     * </pre>
     * @throws CloneNotSupportedException jos kloonausta ei tueta
     */

    @SuppressWarnings("unchecked")
    // BYCODEBEGIN
    @Override
    public TaulukkoGen<TYPE> clone() throws CloneNotSupportedException {
        TaulukkoGen<TYPE> alkiotkopio = new TaulukkoGen<TYPE>(lkm);
        alkiotkopio.lkm = this.lkm;
        for (int i = 0; i < lkm; i++)
            alkiotkopio.set(i, (TYPE)alkiot[i].clone());
        return alkiotkopio;
    }

    /**
     * "Poistetaan" taulukosta kaikki tietyn luvun esiintymät.
     * Taulukon oikea pituus ei muutu, mutta palautetaan
     * se pituus, joka jää taulukkoon jäljelle.
     * @param n poistettava luku
     * @return jäljelle jääneiden alkioiden lukumäärä
     * 
     * @example
     * <pre name="test">
     * #import demo.d9.Int;
     *  TaulukkoGen<Int> luvut = new TaulukkoGen<Int>(20);
     *  luvut.lisaa(new Int(4)); luvut.lisaa(new Int(7)); luvut.lisaa(new Int(6));
     *  luvut.lisaa(new Int(3)); luvut.lisaa(new Int(6)); luvut.lisaa(new Int(2));
     *  luvut.poista($alkio) === $uusilkm; 
     *  
     *    $alkio    | $uusilkm 
     *  ---------------------
     *   new Int(8) |   6      
     *   new Int(6) |   4      
     *   new Int(4) |   3      
     *   new Int(2) |   2      
     *   new Int(7) |   1      
     *   new Int(3) |   0      
     *   new Int(3) |   0      
     * </pre>
     * 
     * @example
     * <pre name="test">
     * #import demo.d9.Int;
     * #import java.util.Arrays;
     *  TaulukkoGen<Int> luvut = new TaulukkoGen<Int>(20);
     *  luvut.lisaa(new Int(4)); luvut.lisaa(new Int(7)); luvut.lisaa(new Int(6));
     *  luvut.lisaa(new Int(3)); luvut.lisaa(new Int(6)); luvut.lisaa(new Int(2));
     *  luvut.poista(new Int(8)) === 6;  luvut.toString() === "4,7,6,3,6,2"; 
     *  luvut.poista(new Int(6)) === 4;  luvut.toString() === "4,7,3,2"; 
     *  luvut.poista(new Int(4)) === 3;  luvut.toString() === "7,3,2"; 
     *  luvut.poista(new Int(2)) === 2;  luvut.toString() === "7,3"; 
     *  luvut.poista(new Int(7)) === 1;  luvut.toString() === "3"; 
     *  luvut.poista(new Int(3)) === 0;   
     *  luvut.poista(new Int(3)) === 0;   
     * </pre>
     */
    public int poista(TYPE n) {
        int luku, kirj;
        int poistetut = 0;
        for (luku = 0, kirj = 0; luku < lkm; luku++) {
            if (!alkiot[luku].equals(n)) 
                alkiot[kirj++] = alkiot[luku];
            else
                poistetut++;
        }
        lkm -= poistetut;
        return lkm;
    }

    // BYCODEEND

    /**
     * Testataan taulukkoa
     * @param args ei käytössä
     * @throws TaulukkoTaysiException jos ei mahu
     * @throws CloneNotSupportedException jos kloonausta ei tueta
     */
    public static void main(String[] args) throws  TaulukkoTaysiException, CloneNotSupportedException  {
        TaulukkoGen<Int> luvut = new TaulukkoGen<Int>(20);
        luvut.lisaa(new Int(0)); luvut.lisaa(new Int(2));
        luvut.lisaa(new Int(99));
        System.out.println(luvut);

        TaulukkoGen<Int> taul = luvut.clone();
        System.out.println(taul);
        luvut.get(1).set(3);
        System.out.println(taul);

        luvut.lisaa(new Int(2)); luvut.lisaa(new Int(5));
        luvut.lisaa(new Int(2)); luvut.lisaa(new Int(6));
        System.out.println(luvut);
        luvut.poista(new Int(2));
        System.out.println(luvut);
        System.out.println(taul);
    }
}


