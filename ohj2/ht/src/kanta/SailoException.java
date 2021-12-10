package kanta;

/**
 * Poikkeusluokka tietorakenteesta aiheutuville poikkeuksille
 * @author Herman Lätti
 * @version Vaihe 7 24.7.2020
 *
 */
public class SailoException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Poikkeuksen muodostaja, jolle tuodaan poikkeusviesti parametrina
     * @param viesti Poikkeuksen viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }

}
