package yhdista;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author Herman
 * @version 1.6.2020
 *
 */
public class YhdistaGUIController {
    @FXML private TextField editEtu;

    @FXML private TextField editSuku;

    @FXML private TextField editKokonimi;

    @FXML void handleYhdista() {
        String nimi = editEtu.getText() + " " + editSuku.getText();
        editKokonimi.setText(nimi);
    }
}