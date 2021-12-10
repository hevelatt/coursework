package fxTilaajat;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Tuotteen tietojen luomista ja lukemista ohjaava luokka
 * @author Herman Lätti
 * @version Vaihe 7 24.7.2020
 *
 */
public class TuoteNimiController implements ModalControllerInterface<String> {

    @FXML private TextField textVastaus;
    private String vastaus = null;
    
    @FXML void handleOK() {
        vastaus = textVastaus.getText(); // Jos painetaan ok asetetaan vastaukseksi tekstikentän teksti
        ModalController.closeStage(textVastaus);
    }
    
    @FXML void handleCancel() {
        ModalController.closeStage(textVastaus);
    }
    
    @Override
    public String getResult() {
        return vastaus; // Palautetaan kun suljetaan dialogi
    }

    @Override
    public void handleShown() {
        textVastaus.requestFocus(); // Nimeä kysyvä tekstilaatikko aktiiviseksi
    }

    @Override
    public void setDefault(String oletus) {
        textVastaus.setText(oletus); // Asetetaan oletusarvo
    }
    
    /**
     * Luo dialogin joka kysyy tuotteen nimeä ja palauttaa siihen kirjoitetun nimen tai null
     * @param modalityStage Mille ollaan modaalisia, null niin sovellukselle
     * @param oletus Mitä nimeä käytetään oletuksena
     * @return Null jos painetaan Cancel, muuten kirjoitettu nimi
     */
    public static String kysyNimi(Stage modalityStage, String oletus) {
        return ModalController.showModal(
                TuoteNimiController.class.getResource("TuoteNimiView.fxml"),
                "Tuote",
                modalityStage, oletus);
    }
}