package demo.d6.perinta;

/**
 * Koira joka laajentaa eläimen ominaisuudet
 * @author vesal
 * @version 25.1.2011
 * @example
 * <pre name="test">
 *   Koira miuku = new Koira("Musti",2300);
 *   miuku.toString() === "Musti 2300.0";
 * </pre>
 */
public class Koira extends Elain {

    /**
     * Alustetaan koiran tiedot
     * @param nimi koiran nimi
     * @param paino koiran paino
     */
    public Koira(String nimi, double paino) {
        super(nimi, paino);
    }


    /**
     * Ääntelee kuin koira
     */
    @Override
    public void aantele() {
        System.out.println("Hau hau");
    }


    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Elain elukat[] = {
                new Koira("Musti", 18005),
                new Kissa("Mirri", 18000),
                new Koira("Pluto", 1930),
                new Kissa("Miuku", 980),
                new Kotka("Kalle", 2400)
        };

        for (int i = 0; i < elukat.length; i++) {
            elukat[i].aantele();
            System.out.println(elukat[i].toString());
            // tai elukat[i].tulosta(); riippuen versiosta josta teet
        }
    }
}
