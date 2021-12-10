package fxTilaajat;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.CheckBoxChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tilaajat.Tilaaja;

/**
 * Tulostusvalintaikkunaa ja tulostusta hoitava luokka
 * @author Herman Lätti
 * @version Vaihe 7 24.7.2020
 *
 */
public class TilaajatTulostusController implements ModalControllerInterface<boolean[]>, Initializable {
    
    @FXML CheckBoxChooser<Integer> cbKentat;
    
    /**
     * OK, suljetaan ikkuna (palautus getResult())
     */
    @FXML void handleOK() {
        ModalController.closeStage(cbKentat);
    }
    
    /**
     * Jos peruutetaan valinnat on null, suljetaan ikkuna
     */
    @FXML void handleCancel() {
        valinnat = null;
        ModalController.closeStage(cbKentat);
    }
    
    //========================================
    
    private boolean[] valinnat;
    private static Tilaaja aputilaaja = new Tilaaja();
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
    }
    
    /**
     * Asettaa oletusvalinnat valinnoiksi
     */
    @Override
    public void setDefault(boolean[] oletus) {
        valinnat = oletus;
    }
    
    /**
     * Merkitsee (oletus)valinnat valintaruutuihin kun näytetään ikkuna
     */
    @Override
    public void handleShown() {
        cbKentat.setSelectedIndices(valinnat);
    }
    
    /**
     * Palauttaa boolean-taulukon valinnoista: valituissa indekseissä true, muissa false.
     * Jos valinnat on null (peruutettiin) niin palautetaan null.
     */
    @Override
    public boolean[] getResult() {
        if (valinnat == null) return null;
        for (int i = 0; i < valinnat.length; i++)
            if (cbKentat.getSelectedIndices().contains(i))
                valinnat[i] = true;
            else
                valinnat[i] = false;
        return valinnat;
    }

    /**
     * Tarvittavat alustukset, lisää valintaruudut ja niiden otsikot
     */
    private void alusta() {
        String kysymys = "";
        cbKentat.clear();
        for (int k = aputilaaja.getEnsimmainen(); k < aputilaaja.getKenttia(); k++) {
            if (kysymys.equals("postinumero"))
                cbKentat.addExample("postitoimipaikka");
            kysymys = aputilaaja.getKysymys(k);
            cbKentat.addExample(kysymys);
        }
    }
    
}