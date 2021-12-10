package demo.d6.rajapinta;

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
public class Koira implements ElainRajapinta {

    private String nimi;
    private double paino;
    
    /**
     * Alustetaan koiran tiedot
     * @param nimi koiran nimi
     * @param paino koiran paino
     */
    public Koira(String nimi, double paino) {
        this.nimi = nimi;
        this.paino = paino;
    }


    /**
     * Ääntelee kuin koira
     */
    @Override
    public void aantele() {
        System.out.println("Hau hau");
    }
    
    @Override
    public String toString() {
        return nimi + " " + paino;
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        ElainRajapinta elukat[] = {
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
