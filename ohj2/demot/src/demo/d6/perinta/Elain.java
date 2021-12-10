package demo.d6.perinta;

/**
 * Elain-luokka.  Kantaluokka eläimille.
 * @author Vesa Lappalainen
 * @version 1.0, 06.02.2003
 *
*/
public class Elain {
    private String nimi;
    private double paino;

    /**
     * Eläimen alustus
     * @param nimi eläimen nimi
     * @param paino eläimen paino g
     */
    public Elain(String nimi, double paino) {
        this.nimi = nimi;
        this.paino = paino;
    }
 
    
    /**
     * Eläimen ääntely
     */
    public void aantele() {
        System.out.println("???");
    }

    
    /**
     * @return eläimen tiedot muodossa "Miuku 18000"
     */
    @Override
    public String toString() {
        return nimi + " " + paino;
    }
}
