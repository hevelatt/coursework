package fxTilaajat;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.StringGrid;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import kanta.SailoException;
import tilaajat.Postiosoite;
import tilaajat.Tilaaja;
import tilaajat.Tuote;

/**
 * Pääkäyttöliittymän ohjauksesta vastaava luokka
 * @author Herman Lätti
 * @version Vaihe 7 24.7.2020
 *
 */
public class TilaajatGUIController implements Initializable {

    @FXML StringGrid<Tilaaja> tableTilaajat;
    @FXML TextField hakuehto;
    @FXML ComboBoxChooser<String> cbKentat;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }

    @FXML void handleTallenna() {
        tallenna();
    }

    @FXML void handleAvaa() {
        avaa();  
    }
    
    @FXML void handleHakuehto() {
        hae();
    }
    
    @FXML void handleTulosta() {
        tulosta();
    }

    @FXML void handleLopeta() {
        tallenna();
        Platform.exit();
    }
    
    @FXML void handleUusiTilaaja() {
        uusiTilaaja();
    }
    
    @FXML void handleMuokkaaTilaajaa() {
        muokkaaTilaajaa(0);
    }
    
    @FXML void handlePoistaTilaaja() {
        poistaTilaaja();
    }

    @FXML void handleApua() {
        avustus();
    }
    
    /**
     * Avaa tietodialogin
     */
    @FXML void handleTietoja() {
        ModalController.showModal(TilaajatGUIController.class.getResource("AboutView.fxml"), 
                "Tilaajat", null, "");
    }
    
    //========================================
    
    private String tuotteennimi = "akuankka";
    private Tuote tuote;
    private Tilaaja tilaajaKohdalla;
    private static Tilaaja aputilaaja = new Tilaaja();
    private int postiIndex = Integer.MAX_VALUE;
    
    /**
     * Tarvittavat alustukset, alustetaan suodattimen alasvetovalikko sekä tilaajatietojen taulukko
     * ja lisätään oikeat otsikot. Asetetaan sopivat leveydet taulukon sarakkeille, asetetaan
     * oletuslajittelu ja lisätään tapahtumakäsittelijät.
     */
    private void alusta() {
        
        int ensimmainen = aputilaaja.getEnsimmainen();
        int kenttia = aputilaaja.getKenttia();
        int postitoimipaikka = TilaajaDialogViewController.postitoimipaikka();
        int tietoja = kenttia - ensimmainen + postitoimipaikka;
        String kysymys = "";
        String[] headings = new String[tietoja];
        
        cbKentat.clear();
        tableTilaajat.clear();
        
        for (int i = 0, k = ensimmainen; k < kenttia; i++, k++) {
            if (kysymys.equals("postinumero")) {
                if (postitoimipaikka > 0)
                    postiIndex = i - 1;
                cbKentat.add("postitoimipaikka");
                headings[i] = "postitoimipaikka";
                i++;
            }
            
            kysymys = aputilaaja.getKysymys(k);
            cbKentat.add(kysymys);
            headings[i] = kysymys;
            
            if (kysymys.equals("maksettu"))
                cbKentat.getSelectionModel().select(i);
        }
        
        tableTilaajat.initTable(headings);
        double[] leveydet = new double[] {30, 150, 175, 75, 125, 80, 150, 100, 60};
        for (int i = 0, k = ensimmainen + (postitoimipaikka > 0 ? 0 : 1); i < tietoja; i++, k++)
            tableTilaajat.setColumnWidth(i, leveydet[k]);

        if (postitoimipaikka > 0)
            tableTilaajat.getSortOrder().add(tableTilaajat.getColumns().get(postiIndex));
        tableTilaajat.setOnMouseClicked(e -> {
            tilaajaKohdalla = tableTilaajat.getObject();
            if (tableTilaajat.getColumnNr() >= 0 && e.getClickCount() > 1)
                muokkaaTilaajaa(tableTilaajat.getColumnNr());
        });
    }
    
    /**
     * Tarkistetaan onko tallennus tehty
     * @return True, jos sovelluksen saa sulkea, muuten false
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }
    
    /**
     * Asettaa ikkunan otsikon
     * @param title Otsikko joka asetetaan
     */
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }
    
    /**
     * Alustaa tuotteen lukemalla sen valitun nimisestä tiedostosta.
     * Hakee tiedot taulukkoon sekä asetaa tuotteen nimen ja otsikon.
     * @param nimi Tiedosto, josta tuotteen tiedot luetaan
     * @return null, jos lukeminen onnistuu, muuten palauttaa virheen
     */
    protected String lueTiedosto(String nimi) {
        tuotteennimi = nimi;
        setTitle("Tuote - " + tuotteennimi);
        try {
            tuote.lueTiedostosta(nimi);
            hae();
            return null;
        } catch (SailoException e) {
            hae();
            String virhe = e.getMessage(); 
            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
            return virhe;
        }
    }
    
    /**
     * Kysytään tuotteen nimeä
     * @return true, jos tuotteen avaaminen onnistuu, false jos nimeä ei saada
     */
    public boolean avaa() {
        String uusinimi = TuoteNimiController.kysyNimi(null, tuotteennimi);
        if (uusinimi == null)
            return false;
        lueTiedosto(uusinimi);
        return true;
    }
    
    /**
     * Sijoittaa tilaajan tiedot ja postitoimipaikan pääikkunan taulukkoon
     * @param tilaaja Tilaaja, joka sijoitetaan
     */
    private void sijoitaTaulukkoon(Tilaaja tilaaja) {
        
        int ensimmainen = aputilaaja.getEnsimmainen();
        int kenttia = aputilaaja.getKenttia();
        int postitoimipaikka = TilaajaDialogViewController.postitoimipaikka();
        int tietoja = kenttia - ensimmainen + postitoimipaikka;
        String[] rivi = new String[tietoja];
        
        for (int i = 0, k = ensimmainen; k < kenttia; i++, k++) {
            rivi[i] = tilaaja.anna(k);
            if (i == postiIndex) {
                Postiosoite postiosoite = tuote.annaPostiosoitePostinumeronPerusteella(tilaaja.getPostinumero());
                if (postiosoite == null)
                    tuote.lisaa(postiosoite = new Postiosoite(tilaaja.getPostinumero()));
                rivi[++i] = postiosoite.getPostitoimipaikka();
            }
        }
        tableTilaajat.add(tilaaja, rivi);
    }
    
    /**
     * Hakee tilaajien tiedot taulukkoon ottaen huomioon hakuehdon ja suodatuksen
     */
    private void hae() {
        int k = cbKentat.getSelectionModel().getSelectedIndex();
        String ehto = hakuehto.getText();
        if (ehto.indexOf('*') < 0)
            ehto = "*" + ehto + "*";
        
        tableTilaajat.clear();
        
        List<Tilaaja> loydetyt = new ArrayList<>();
        if (k > postiIndex && --k == postiIndex) loydetyt = tuote.etsiPostitoimipaikka(ehto);
        else loydetyt = tuote.etsi(ehto, k + aputilaaja.getEnsimmainen());
        
        for (Tilaaja tilaaja: loydetyt)
            sijoitaTaulukkoon(tilaaja);

        tableTilaajat.sort();
        
        tilaajaKohdalla = null;
        tableTilaajat.selectRow(-1);
    }
    
    /**
     * Luo uuden tilaajan ja pyytää käyttäjää asettamaan tiedot, minkä jälkeen uusi tilaaja rekisteröidään,
     * lisätään taulukkoon ja virkistetään.
     */
    private void uusiTilaaja() {
        Tilaaja uusi = new Tilaaja();
        uusi = TilaajaDialogViewController.kysyTilaaja(null, uusi, tuote, 0, true);
        if (uusi == null) return;
        uusi.rekisteroi();
        tuote.lisaa(uusi);
        hae();
    }
    
    /**
     * Käsittelee tilaajan muokkauksen. Jos muokatessa peruutetaan tiedot pysyvät ennalaan.
     * @param i Muokattavan kentän indeksi
     */
    private void muokkaaTilaajaa(int i) {
        if (tilaajaKohdalla == null) return;
        try {
            Tilaaja tilaaja;
            tilaaja = TilaajaDialogViewController.kysyTilaaja(null, tilaajaKohdalla.clone(), tuote, i, false);
            if (tilaaja == null) return;
            tuote.korvaaTaiLisaa(tilaaja);
            hae();
        } catch (CloneNotSupportedException e) {
            //
        }
    }
    
    /**
     * Poistaa valitun tilaajan tiedot. Varmistaa poistamisen ennen poistamista.
     */
    private void poistaTilaaja() {
        Tilaaja tilaaja = tilaajaKohdalla;
        if (tilaaja == null) return;
        if (!Dialogs.showQuestionDialog("Poisto", "Poistetaanko tilaaja: " + tilaaja.anna(1), "Kyllä", "Ei"))
            return;    
        tuote.poista(tilaaja);
        hae();
    }
    
    /**
     * Tietojen tallennus
     * @return Null, jos tietojen tallennus onnistuu, muuten palauttaa virheen
     */
    private String tallenna() {
        try {
            tuote.tallenna();
            return null;
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Tallennuksessa tuli virhe: " + e.getMessage());
            return e.getMessage();
        }
    }
    
    /**
     * Pääikkunan taulukon tulostaminen valituilla tiedoilla mahdollisesti suodatettuna ja lajiteltuna
     */
    private void tulosta() {
        var sarakkeet = tableTilaajat.getColumns();
        int sarakkeita = sarakkeet.size();
        boolean[] valinnat = new boolean[sarakkeita];
        
        // valitaan tulostuksen oletusasetukset
        String[] oletus = new String[] {"nimi", "katuosoite", "postinumero", "postitoimipaikka"};
        for (int i = 0; i < sarakkeita; i++)
            for (int j = 0;  j < oletus.length; j++)
                if (tableTilaajat.getColumns().get(i).getText().equals(oletus[j]))
                    valinnat[i] = true;
        
        valinnat = ModalController.showModal(
                TilaajatGUIController.class.getResource("TilaajatTulostusView.fxml"), 
                "Tulostusvalinta", null, valinnat);
        if (valinnat == null) return;
        
        var rivit = tableTilaajat.getItems();
        int riveja = rivit.size();
        String erotin = ";";
        String hakemistonNimi = "";
        if (!tuotteennimi.isEmpty()) hakemistonNimi = tuotteennimi + "/";
        File ftied = new File(hakemistonNimi + "tulostus.txt");
        
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied.getCanonicalPath(), true))) {
            for (int i = 0; i < riveja; i++) {
                String ero = "";
                var rivi = rivit.get(i);
                for (int j = 0; j < sarakkeita; j++) {
                    if (valinnat[j]) {
                        String solu = rivi.get(j);
                        rivi.set(j, solu.replace(' ', '§')); // säilytetään välilyönnit korvaamalla ne väliaikaisesti
                        
                        fo.print(ero);
                        fo.print(sarakkeet.get(j).getCellObservableValue(rivi)
                                .getValue().toString().replace('§', ' '));
                        ero = erotin;
                        
                        rivi.set(j, solu);
                    }
                }
                fo.println();
            }
        } catch (FileNotFoundException ex) {
            Dialogs.showMessageDialog("Tiedosto " + ftied.getName() + " ei aukea");
            return;
        } catch (IOException ex) {
            Dialogs.showMessageDialog("Tiedostoon " + ftied.getName() + " kirjoittaminen ei onnistu");
            return;
        }
        Dialogs.showMessageDialog("Tulostettu valitut tiedot tiedostoon " + ftied.getName());
    }
    
    /**
     * Näytetään ohjelman suunnitelma selaimessa
     */
    private void avustus() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2020k/ht/hevelatt");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }

    /**
     * Asetetaan kontrollerin tuoteviite
     * @param tuote Mihin tuotteeseen viitataan
     */
    public void setTuote(Tuote tuote) {
        this.tuote = tuote;
    }
}