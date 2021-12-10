package fxAanestysRB;


import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Chooser;
import fi.jyu.mit.fxgui.Dialogs;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


/**
 * Kontrolleri äänestykselle
 * @author vesal
 * @version 4.2.2017
 *
 */
public class AanestysController4 implements Initializable  {
    @FXML private Label labelValittu;
    @FXML private Chooser<String> valinta;

    @FXML void handleAanesta() { aanesta(); }

    private int aanet0;
    private int aanet1;
    private int aanet2;
    private int aanet3;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        valinta.addSelectionListener((e) -> nayta());
        labelValittu.setText("");
    }
    
    
    private void nayta() {
        String s = valinta.getSelectedText();
        if ( s == null ) return;
        labelValittu.setText("Valittu: " + s);
    }


    private void aanesta() {
        String kohde = valinta.getSelectedText();
        int i = valinta.getSelectedIndex();
        switch (i) {
            case 0: 
                aanet0++;
                break;
            case 1:
                aanet1++;
                break;
            case 2:
                aanet2++;
                break;
            case 3:
                aanet3++;
                break;
            default:
                break;
        }
        Dialogs.showMessageDialog("Olet siis: " + kohde + " " + 
                "\nTilanne:\nTyttö: " + aanet0 +
                          "\nPoika: " + aanet1 + 
                          "\nNainen: " + aanet2 + 
                          "\nMies: " + aanet3);
    }

}
