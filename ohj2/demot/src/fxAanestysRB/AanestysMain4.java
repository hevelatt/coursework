package fxAanestysRB;
    
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


/**
 * Pääohjelma äänestykselle. Käytetään RadioButtonChooser-komponenttia 
 * @author vesal
 * @version 31.1.2016
 *
 */
public class AanestysMain4 extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AanestysView4.fxml"));
            Scene scene = new Scene(root);
            // scene.getStylesheets().add(getClass().getResource("aanestys.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Äänestys");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Käynnistetään äänestys
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}
