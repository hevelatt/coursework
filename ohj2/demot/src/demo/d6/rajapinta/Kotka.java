package demo.d6.rajapinta;

/**
 * Kotka rajapinnalla toteutettuna
 * @author Herman
 * @version 19.6.2020
 *
 */
public class Kotka implements ElainRajapinta {

    private String nimi;
    private double paino;
    
    /**
     * Muodostaja
     * @param nimi Kotkan nimi
     * @param paino Kotkan paino
     */
    public Kotka(String nimi, double paino) {
        this.nimi = nimi;
        this.paino = paino;
    }

    @Override
    public void aantele() {
        System.out.println("Kriäääääk");    
    }
    
    @Override
    public String toString() {
        return nimi + " " + paino;
    }

}
