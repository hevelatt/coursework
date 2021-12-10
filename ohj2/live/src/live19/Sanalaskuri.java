/**
 * 
 */
package live19;

import java.io.IOException;

/**
 * @author Herman
 * @version 3.7.2020
 *
 */
public class Sanalaskuri {

    /**
     * Lasketaan tiedostossa olevat sanat
     * @param args ei käytössä
     * @throws IOException jos ei toimi
     */
    public static void main(String[] args) throws IOException {
        Sanat sanat = new Sanat();
        sanat.lueTiedostoJaLaske("sanat.txt");
        sanat.tulosta(System.out); 
    }

}
