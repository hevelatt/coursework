package fxTilaajat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import tilaajat.Tuote;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

/**
 * Pääohjelma tilaajarekisterin käynnistämiseksi
 * @author Herman Lätti
 * @version Vaihe 7 24.7.2020
 *
 */
public class TilaajatMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("TilaajatGUIView.fxml"));
            final Pane root = ldr.load();
            final TilaajatGUIController tilaajatCtrl = (TilaajatGUIController) ldr.getController();
            
            final Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("tilaajat.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Tilaajat");
            
            primaryStage.setOnCloseRequest((event) -> {
                if (!tilaajatCtrl.voikoSulkea()) event.consume();
            });
            
            Tuote tuote = new Tuote();
            tilaajatCtrl.setTuote(tuote);
            
            primaryStage.show();
            
            Application.Parameters params = getParameters();
            if (params.getRaw().size() > 0) // Jos ohjelman käynnistetään parametrien kanssa niin tiedoston nimi on ensimmäinen parametri
                tilaajatCtrl.lueTiedosto(params.getRaw().get(0)); 
            else if (!tilaajatCtrl.avaa()) // Avataan, jos avaaminen ei onnistu niin suljetaan ohjelma
                Platform.exit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Käynnistetään käyttöliittymä
     * @param args Komentorivin parametrit
     */
    public static void main(String[] args) {
        launch(args);
    }
}