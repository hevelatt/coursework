package demo.d6.rajapinta;

/**
 * Rajapinta eläimille
 * @author Herman
 * @version 19.6.2020
 *
 */
public interface ElainRajapinta {
    
    /**
     * Eläin ääntelee eli tulostaa jotain
     */
    public void aantele();
    
    /**
     * Muuttaa eläimen tiedot merkkijonoksi
     * @return Eläimen tiedot muodossa "nimi paino"
     */
    @Override
    public String toString();
}
