    package demo.d7.fx;

import java.util.ArrayList;

import demo.d7.Astia;
import demo.d7.AstiaPeli;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Graafinen versio astiapelistä.
 * @author vesal
 * @version 12.2.2011/Swing
 * @version 20.2.2016/JavaFx
 */
public class GraafinenAstiaPeli extends Application {

    /**
     * Käynnistetään astia-peli parilla astialla
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        AstiaPeli peli = new AstiaPeli();
        peli.lisaaAstia("8", 8);
        peli.lisaaAstia("5", 5);
        peli.lisaaAstia("2", 2);
        // peli.lisaaAstia("1", 1);
        pelaa(peli);
    }

    
    /**
     * Aloitetaan peli 
     * @param peli AstiaPeli, jonka tiedoilla pelataan
     */
    public static void pelaa(final AstiaPeli peli) {
        tempPeli = peli;
        launch(new String[0]);
    }

    
    private static AstiaPeli tempPeli; // kömpelö tapa viedä parametri start-metodille
    
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Pane root = luoNaytto(tempPeli);
            Scene scene = new Scene(root);
            // scene.getStylesheets().add(getClass().getResource("astiapeli.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Astiapeli");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private final HBox panelAstiat = new HBox();
    private final HBox panelLoydetyt = new HBox();
    private final Label labelOhje = new Label("Valitse mist\u00E4 kaadetaan");
    private final TextArea textTulostus = new TextArea();
    
    // private final String loydettyStyle = "-fx-background-color: yellow;"; // TODO: ota pois kommenteista
    private final String beveledStyle = "    -fx-border-insets: 0;"+
                                        "-fx-border-width: 2px;"+
                                        "-fx-border-style: solid line-join miter 45;"+
                                        "-fx-border-color: gray lightgray lightgray gray;";
    


    /**
     * Luodaan ulkoasu
     * @param astiapeli peli jota käytetään
     * @return ulommainen paneli
     */
    public Pane luoNaytto(AstiaPeli astiapeli) {
        BorderPane contentPane = new BorderPane();
        HBox box = new HBox();
        contentPane.setBottom(box);
        contentPane.setCenter(textTulostus);
        BorderPane.setMargin(textTulostus, new Insets(0,10,0,5));
        textTulostus.setMinWidth(100);
        textTulostus.setPrefWidth(200);
        textTulostus.setMaxWidth(1000);
        
        contentPane.setTop(labelOhje);
        BorderPane.setMargin(labelOhje, new Insets(20,0,10,20));
        
        contentPane.setLeft(panelAstiat);
        // panelAstiat.getStyleClass().add("beveled");
        panelAstiat.setStyle(beveledStyle);
        BorderPane.setMargin(panelAstiat, new Insets(0,0,0,10));

        contentPane.setBottom(panelLoydetyt);
        panelLoydetyt.setSpacing(3);
        panelLoydetyt.setPrefHeight(36);
        BorderPane.setMargin(panelLoydetyt, new Insets(10));
        
        alustaAstiat(astiapeli);
        
        return contentPane;
    }

    
    //========================================================================
    // Omat aliohjelmat:

    @SuppressWarnings("unused")  // TODO: poista tämä sitten kun peli-oliota käytetään 
    private AstiaPeli peli;
    private ArrayList<Label> loydetyt = new ArrayList<Label>();

    private double maxSize = 0;

    private GraafinenAstia mistaAstia = null;
    private GraafinenAstia mihinAstia = null;
    
    private double[] loydettavat = {1,2,3,4,5,6,7,8,9,10,11,12,13};


    /**
     * Palautetaan klikkaamista kuvaava ohje.  Teksti riippu
     * siitä, mitä aikaisemmin klikattu.  Jos peli on valmis,
     * palautetaan tieto tästä.
     * @return näyttöön näytettävä ohje
     */
    private String ohje() {
        // TODO: GURU: tee metodi lapi AstiaPeli-luokkaan ja poista seuraavasta kommentti
        // if ( peli.lapi() ) return "Onneksi olkoon, sait pelin kokonaan läpi!";  
        if ( mistaAstia == null ) return "Valitse mistä kaadetaan.";
        String smista = mistaAstia.getAstia().getNimi();
        return "Kaadetaan astiasta " + smista + ". Valitse mihin kaadetaan.";
    }


    private void setOhje() {
      labelOhje.setText(ohje());
    }  
    
    
    /**
     * Tätä kutsutaan kun jotakin astiaa on klikattu.  Jos 1. klikattu
     * astia, laitetaan se valituksi.  Jos samaa klikataa uudelleen,
     * poistetana valinta.  Muuten kaadetaan 1. 2. astiaan.
     * @param klikattuAstia astia jota klikattiin
     */
    private void astiaKlikattu(GraafinenAstia klikattuAstia) {
        if ( mistaAstia == null ) { // ei vielä valittu mitään
            klikattuAstia.setSelected(true);
            mistaAstia = klikattuAstia;
            setOhje();
            return;
        }
        if ( mistaAstia == klikattuAstia ) { // sama valittiin uudelleen
            klikattuAstia.setSelected(false);
            mistaAstia = null;
            setOhje();
            return;
        }
        mihinAstia = klikattuAstia;
        mistaAstia.setSelected(false);

        Astia mista = mistaAstia.getAstia();
        Astia mihin = mihinAstia.getAstia();

        mista.kaada(mihin);
        
        textTulostus.appendText("Kaadettiin astiasta " + mista.getNimi() + " astiaan " + mihin.getNimi() + "\n");
        // TODO: Guru tehtävässä lisää niin että löydetyt tulevat keltaiseksi
        
        mistaAstia = null;
        mihinAstia = null;
        setOhje();
    }


    /**
     * Lisätään astia näyttöön
     * @param astia lisättävä astia
     * @return viite lisättyyn astiaan.
     */
    private Astia lisaaAstia(Astia astia) {
        GraafinenAstia gastia = new GraafinenAstia();
        gastia.setMaxkoko(maxSize);
        gastia.setAstia(astia);
        panelAstiat.getChildren().add(gastia);
        gastia.addAstiaClickedListener(klikattuAstia -> astiaKlikattu(klikattuAstia));
        return astia;
    }


    /**
     * Alustetaan astiat sen mukaan mitä astioita on itse pelissä.
     * Luodaan myös merkinnät löydetyille tilavuuksille.
     * @param liitettavaPeli josta astioiden tiedot otetaan.
     */
    private void alustaAstiat(AstiaPeli liitettavaPeli) {
        this.peli = liitettavaPeli;
        maxSize = liitettavaPeli.astioidenSumma();
        for (int i = 0; i < liitettavaPeli.getLkm(); i++) {
            Astia astia = liitettavaPeli.anna(i);
            lisaaAstia(astia);
        }

        loydetyt.add(null); // yksi ylimääräinen alkuun, niin indeksit toimivat suoraan
        
        for (double i: loydettavat) {
            Label loydetty;
            if (i % 1 == 0) loydetty = new Label(String.format("%.0f", i));
            else loydetty = new Label("" + i);
            loydetty.setPadding(new Insets(10));
            // loydetty.getStyleClass().add("beveled");
            loydetty.setStyle(beveledStyle);
            panelLoydetyt.getChildren().add(loydetty);
            loydetyt.add(loydetty);
        }
        
    }



}