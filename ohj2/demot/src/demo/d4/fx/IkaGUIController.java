package demo.d4.fx;
import demo.d4.Ika;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Herman
 * @version 13.6.2020
 *
 */
public class IkaGUIController {
    
    @FXML private TextField textSyntymavuosi;
    @FXML private Label labelIka;

    @FXML void keyReleased() {
        uusiTeksti();
    }
    
    //============================================
    
    /**
     * Sijoitetaan oikea teksti Labeliin
     */
    protected void uusiTeksti() {
        laitaOtsikko(labelIka, "Täytät tänä vuonna " + Ika.laskeIka(haeInt(textSyntymavuosi, 0)) + " vuotta." );
    }
    
    /**
     * Haetaan kokonaisluku tekstikentästä
     * @param text Tekstikenttä, josta haetaan lukua
     * @param oletus Luku, joka on teksikentässä oletuksena
     * @return Palauttaa luvun, joka on tekstikentässä
     * @example
     * <pre name="test">
     * #import javafx.embed.swing.JFXPanel;
     * #import javafx.scene.control.*;
     * (new JFXPanel()).toString(); 
     * haeInt(new TextField("234"), 44) === 234;
     * haeInt(new TextField("foobar1"), 44) === 44;
     * haeInt(new TextField("1foobar"), 44) === 1;
     * </pre>
     */
    public static int haeInt(TextField text, int oletus) {
        return Mjonot.erotaInt(text.getText(), oletus);
    }
    
    /**
     * Sijoitetaan luku labeliin
     * @param label Otsikko, johon sijoitetaan
     * @param jono Merkkijono, joka sijoitetaan otsikkoon
     */
    public static void laitaOtsikko(Label label, String jono) {
        label.setText(jono);
    }   
}
