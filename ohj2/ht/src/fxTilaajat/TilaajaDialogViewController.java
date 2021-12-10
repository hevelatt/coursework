package fxTilaajat;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tilaajat.Postiosoite;
import tilaajat.Tilaaja;
import tilaajat.Tuote;

/**
 * Tilaajan tietojen muokkausta ja tallennusta ohjaava luokka
 * @author Herman Lätti
 * @version Vaihe 7 24.7.2020
 *
 */

public class TilaajaDialogViewController implements ModalControllerInterface<Tilaaja>, Initializable {

    @FXML private GridPane gridTiedot;
    @FXML private Label labelVirhe;
    
    @FXML void handleOK() {
        ModalController.closeStage(labelVirhe);
    }
    
    @FXML void handleCancel() {
        tilaajaKohdalla = null; // Palautetaan null jos painetaan cancel
        ModalController.closeStage(labelVirhe);
    }
    
    //========================================
    
    private Tuote tuote;
    private Tilaaja tilaajaKohdalla;
    private Postiosoite postiosoite;
    private static Tilaaja aputilaaja = new Tilaaja(); // Tilaaja, jolta voidaan kysellä tietoja, ei koskaan null
    private TextField[] edits;
    private int index = 0;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
    }
    
    /**
     * Asetetaan kohdalla oleva tilaaja oletustilaajaksi
     */
    @Override
    public void setDefault(Tilaaja oletus) {
        tilaajaKohdalla = oletus;
    }
    
    /**
     * Ikkunaa näytettäessä haetaan tilaajan postiosoite ja näytetään tilaaja sekä asetetaan klikattu
     * kenttä aktiiviseksi.
     */
    @Override
    public void handleShown() {
        haePostiosoite();
        naytaTilaaja();
        edits[index].requestFocus();
    }    
    
    /**
     * Jos on luotu uusi postiosoite, lisätään se tuotteen postiosoitteisiin.
     * Palautetaan tilaaja (ja siis myös uudet tiedot)
     */
    @Override
    public Tilaaja getResult() {
        if (postiosoite != null)
            tuote.lisaa(postiosoite);
        return tilaajaKohdalla;
    }
    
    /**
     * Luodaan tarvittavat kentät tilaajan tietoja varten sekä niiden otsikot
     */
    public void luoKentat() {
        edits = new TextField[aputilaaja.getKenttia() - aputilaaja.getEnsimmainen() + postitoimipaikka()];
        
        for (int i = 0, k = aputilaaja.getEnsimmainen(); i < edits.length; k++, i++) {
            String kysymys = aputilaaja.getKysymys(k);
            Label label = new Label(kysymys);
            gridTiedot.add(label, 0, i);
            
            TextField edit = new TextField();
            edits[i] = edit;
            edit.setId("" + k);
            gridTiedot.add(edit, 1, i);
            
            if (kysymys.equals("postinumero")) {
                i++;
                
                gridTiedot.add(new Label("postitoimipaikka"), 0, i);
                TextField editp = new TextField();
                
                edits[i] = editp;
                editp.setId("p");
                gridTiedot.add(editp, 1, i);
            }
        }
    }

    /**
     * Tekee tarvittavat muut alustukset eli kutsuu luoKentat() ja lisää muutoskäsittelijät tekstikenttiin
     */
    private void alusta() {
        luoKentat();
        for (TextField edit : edits)
            edit.setOnKeyReleased( e -> kasitteleMuutosTilaajaan((TextField)(e.getSource())));
    }
    
    /**
     * Huolehtii virheen näyttämisestä dialogi-ikkunassa.
     * Virhe-label muuttuu punaiseksi ja näyttää virheen tekstinä
     * @param virhe virhe joka näytetään
     */
    private void naytaVirhe(String virhe) {
        if (virhe == null || virhe.isEmpty()) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    /**
     * Hakee muokattavan tilaajan postinumeroa vastaavan postiosoitteen
     */
    public void haePostiosoite() {
        postiosoite = tuote.annaPostiosoitePostinumeronPerusteella(tilaajaKohdalla.getPostinumero());
    }
    
    /**
     * Käsitellään tilaajan muutos.
     * Virheellinen kenttä muuttuu punaiseksi ja kutsuu virheenkäsittelijää (naytaVirhe()).
     * Erikoiskäsittely postitoimipaikalle: Lisätään uudelle postinumerolle vastaava postitoimipaikka
     * tai muutetaan postinumeroa vastaavaa postitoimipaikkaa.
     * @param edit Muuttunut kenttä
     */
    private void kasitteleMuutosTilaajaan(TextField edit) {
        if (tilaajaKohdalla == null) return;
        
        String id = edit.getId();
        int k = Mjonot.erotaInt(id, -1);
        String s = edit.getText();
        String virhe = null;
        
        virhe = tilaajaKohdalla.aseta(k, s);
        
        if (id.equals("p")) {
            if (postiosoite == null)
                postiosoite = new Postiosoite(tilaajaKohdalla.getPostinumero());
            postiosoite.setPostitoimipaikka(s);
        }

        if (virhe == null) {
            edit.getStyleClass().removeAll("virhe");
            naytaVirhe(virhe);
            if (aputilaaja.getKysymys(k).equals("postinumero")) {
                haePostiosoite();
                if (postiosoite == null)
                    postiosoite = new Postiosoite(tilaajaKohdalla.getPostinumero());
                edits[k - aputilaaja.getEnsimmainen() + 1].setText(postiosoite.getPostitoimipaikka()); // seuraava postinumerosta
            }
        } else {
            edit.getStyleClass().add("virhe");
            naytaVirhe(virhe);
        }
        
    }
    
    /**
     * Näytetään tilaajan tiedot tekstikenttiin
     */
    public void naytaTilaaja() {
        if (tilaajaKohdalla == null) return;
        int vali = 0;
        for (int i = 0, k = aputilaaja.getEnsimmainen(); i < edits.length; i++, k++) {
            if (aputilaaja.getKysymys(k - postitoimipaikka()).equals("postinumero")) {
                if (postiosoite != null)
                    edits[i].setText(postiosoite.getPostitoimipaikka());
                vali += 1; // Annetaan tarpeeksi väliä postitoimipaikalle
                continue;
            }
            edits[i].setText(tilaajaKohdalla.anna(k - vali));
        }
    }
    
    /**
     * Palauttaa kuinka monta paikkaa varataan postitoimipaikalle (esiintyykö postinumero käyttöliittymässä?)
     * @return 0 jos ei varata tilaa postitoimipaikalle, 1 jos varataan
     */
    public static int postitoimipaikka() {
        for (int k = aputilaaja.getEnsimmainen(); k < aputilaaja.getKenttia(); k++)
            if (aputilaaja.getKysymys(k).equals("postinumero"))
                return 1;
        return 0;
    }
    
    /**
     * Luo tilaajan kysymisdialogin ja palauttaa saman tietueen muutettuna tai null-arvoisena
     * @param modalityStage Mille ollaan modaalisia, null niin sovellukselle
     * @param oletus Mitä dataa näytetään oletuksena
     * @param tuote Minkä tuotteen tilaaja
     * @param i Muokattavan tiedon indeksi
     * @param uusi Onko kyseessä uusi tilaaja
     * @return null jos perutaan, muuten täytetty tietue
     */
    public static Tilaaja kysyTilaaja(Stage modalityStage, Tilaaja oletus, Tuote tuote, int i, boolean uusi) {
        String title = "Muokkaa tilaajaa";
        if (uusi) title = "Uusi tilaaja";
        return ModalController.<Tilaaja, TilaajaDialogViewController>showModal(
                TilaajatGUIController.class.getResource("TilaajaDialogView.fxml"),
                title, modalityStage, oletus, ctrl -> { 
                    ctrl.setTuote(tuote); ctrl.setIndex(i); 
                    });
    }

    /**
     * Asettaa muokattavan tiedon indeksin 
     * @param index Muokattavan tiedon indeksi
     */
    private void setIndex(int index) {
        this.index = index;
    }
    
    /**
     * Asettaa kontrollerin tuoteviitteen
     * @param tuote Mihin tuotteeseen viitataan
     */
    public void setTuote(Tuote tuote) {
        this.tuote = tuote;
    }
}