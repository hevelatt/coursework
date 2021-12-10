package demo.d3.fx;

import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author Herman
 * @version 7.6.2020
 *
 */
public class HuoneGUIController {
    /*Attribuutit*/
    @FXML private TextField textLeveys;
    @FXML private TextField textPituus;
    @FXML private TextField textKorkeus;
    @FXML private TextField textAla;
    @FXML private TextField textTilavuus;

    @FXML
    void keyReleased() {
        laske();
    }
    
    //========================================
    
    /**
     * Lasketaan uudet arvot
     */
    private void laske() {
        double leveys = haeLuku(textLeveys, 0);
        double pituus = haeLuku(textPituus, 0);
        double korkeus = haeLuku(textKorkeus, 0);
        double ala = leveys*pituus;
        double tilavuus = ala*korkeus;
        laitaTulos(textAla, ala);
        laitaTulos(textTilavuus, tilavuus);
    }

    /**
     * Haetaan luku tekstikentästä
     * @param text Tekstikenttä, josta haetaan lukua
     * @param oletus Luku, joka on teksikentässä oletuksena
     * @return Palauttaa luvun, joka on tekstikentässä
     */
    public static double haeLuku(TextField text, double oletus) {
        return Mjonot.erotaDouble(text.getText(), oletus);
    }
    
    /**
     * Sijoitetaan luku tekstikenttään
     * @param text Tekstikenttä, johon sijoitetaan
     * @param luku Luku, joka sijoitetaan tekstikenttään
     */
    public static void laitaTulos(TextField text, double luku) {
        text.setText(Double.toString(luku));
    }
}
