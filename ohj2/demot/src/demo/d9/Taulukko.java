package demo.d9;

/**
 * Esimerkki dynaamisesta taulukosta
 * @author Vesa Lappalainen @version 1.0, 02.03.2002
 * @author Santtu Viitanen @version 1.1, 21.07.2011
 * @example
 * <pre name="test">
 *  #THROWS Taulukko.TaulukkoTaysiException
 *  Taulukko luvut = new Taulukko();
 *  luvut.lisaa(0); luvut.lisaa(2); luvut.lisaa(99);
 *  luvut.toString() === "0,2,99";
 *  luvut.set(1,4); luvut.toString() === "0,4,99";
 *  int luku = luvut.get(2);
 *  luku === 99;
 *  luvut.get(2) === 99;
 *  luvut.set(21, 4); #THROWS IndexOutOfBoundsException
 *  luvut.lisaa(0); luvut.lisaa(0); //taulukko täyteen
 *  luvut.lisaa(0);  #THROWS Taulukko.TaulukkoTaysiException
 * </pre>
 */
public class Taulukko {
    /** Luokka täyden taulukon poikkeusta varten  */
    public static class TaulukkoTaysiException extends Exception {
        private static final long serialVersionUID = 1L;


        TaulukkoTaysiException(String viesti) {
            super(viesti);
        }
    }

    private int alkiot[];
    private int lkm;


    /**
     * Alustetaan 5 kokoinen taulukko
     */
    public Taulukko() {
        alkiot = new int[5];
    }


    /**
     * Alutetaan valitun kokoinen taulukko
     * @param koko taulukon koko
     */
    public Taulukko(int koko) {
        alkiot = new int[koko];
    }


    /**
     * Lisätään taulukkoon yksi alkio
     * @param luku lisättävä alkio
     * @throws TaulukkoTaysiException jos taulukko täysi
     */
    public void lisaa(int luku) throws TaulukkoTaysiException {
        if (lkm >= alkiot.length) throw new TaulukkoTaysiException("Tila loppu");
        alkiot[lkm++] = luku;
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
     * Asetetaan taulukon i's alkio
     * @param i mihin paikkaan astetaan
     * @param luku mikä arvo laitetaan
     * @throws IndexOutOfBoundsException jos väärä indeksi
     */
    public void set(int i, int luku) throws IndexOutOfBoundsException {
        if ((i < 0) || (lkm <= i)) throw new IndexOutOfBoundsException("i = " + i);
        alkiot[i] = luku;
    }


    /**
     * Palautetana paikasssa i oleva luku
     * @param i mistä paikasta luku otetaan
     * @return paikassa i olevan luvun arvo
     * @throws IndexOutOfBoundsException jos indeksi väärin
     */
    public int get(int i) throws IndexOutOfBoundsException {
        if ((i < 0) || (lkm <= i)) throw new IndexOutOfBoundsException("i = " + i);
        return alkiot[i];
    }


// BYCODEBEGIN
    /**
     * Poistaa Taulukosta kaikki poista esiintymät
     * @param poista poistettava luku
     * @example
     * <pre name="test">
     * #THROWS Taulukko.TaulukkoTaysiException
     * #import java.util.Arrays;
     * Taulukko luvut = new Taulukko(7); 
     * luvut.lisaa(4); luvut.lisaa(7); luvut.lisaa(9); 
     * luvut.lisaa(3); luvut.lisaa(9); luvut.lisaa(2);
     * luvut.poista(9); luvut.toString() === "4,7,3,2";
     * luvut.poista(2); luvut.toString() === "4,7,3";
     * luvut.poista(9); luvut.toString() === "4,7,3";
     * luvut = new Taulukko(9);
     * luvut.lisaa(9); luvut.lisaa(2); luvut.lisaa(9); 
     * luvut.lisaa(9); luvut.lisaa(9); luvut.lisaa(9); 
     * luvut.lisaa(1); luvut.lisaa(2); luvut.lisaa(3); 
     * luvut.poista(9); luvut.toString() === "2,1,2,3";
     * luvut.poista(9); luvut.toString() === "2,1,2,3";
     * luvut.poista(2); luvut.toString() === "1,3";
     * </pre>
     */
    public void poista(int poista) {
        int uusilkm = lkm;
        for (int i = 0, j = -1; i < lkm; i++) {
            if (get(i) == poista) {
                if (j < 0)
                    j = i;
                uusilkm--;
            } else if (j >= 0) {
                set(j, get(i));
                j++;
            }
        }
        lkm = uusilkm;
    }
// BYCODEEND


    /**
     * Testataan Taulukko-luokkaa
     * @param args ei käytössä
     * @throws TaulukkoTaysiException jos tila loppuu
     */
    public static void main(String args[]) throws TaulukkoTaysiException {
        Taulukko luvut = new Taulukko(7);
        luvut.lisaa(1); luvut.lisaa(2); luvut.lisaa(1);
        System.out.println(luvut); // Tulostaa "1,2,1"
        luvut.poista(1);
        System.out.println(luvut);  // tulostaa "2"
    }

}


