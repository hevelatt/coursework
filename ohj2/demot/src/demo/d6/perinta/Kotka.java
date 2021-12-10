package demo.d6.perinta;

/**
 * Kotka perinnällä
 * @author Herman
 * @version 19.6.2020
 *
 */
public class Kotka extends Elain {
    
    /**
     * Kotkan muodostaja
     * @param nimi Kotkan nimi
     * @param paino Kotkan paino
     */
    public Kotka(String nimi, double paino) {
        super(nimi, paino);
    }
    
    /**
     * Kotkan muodostaja
     * @param nimi Kotkan nimi
     */
    public Kotka(String nimi) {
        super(nimi, 0);
    }
    
    /**
     * Kotkan oletusmuodostaja
     */
    public Kotka() {
        super("", 0);
    }
    
    /**
     * Kotkan ääntely
     */
    @Override
    public void aantele() {
        System.out.println("Kriäääääk");
    }
}
