package demo.d7.fx;

import java.util.ArrayList;

import demo.d7.Astia;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Graafinen astia
 * @author vesal
 * @version 12.2.2011/Swing
 * @version 20.2.2016/JavaFx
 */
public class GraafinenAstia extends AnchorPane implements Astia.MaaraMuuttuu {
    private final ProgressBar barAstia = new ProgressBar();
    private Astia astia;
    private double maxkoko = 20;
    private boolean selected = false;

    private final Label labelNimi = new Label("\u00E4");
    private final Label labelMaara = new Label("0.0");

    private final ArrayList<AstiaClickedListener> astiaClickedListenerList = new ArrayList<AstiaClickedListener>();


    /**
     * Rajapinta astian klikkauksen kuuntelijoille
     * @author vesal
     * @version 6.2.2011
     */
    public static interface AstiaClickedListener {
        /**
         * Metodi jota kutsutaan kun astiaa klikataan
         * @param klikattuAstia jota klikattiin
         */
        public void clicked(GraafinenAstia klikattuAstia);
    }


    /**
     * Alustetaan graafinen astia.
     */
    public GraafinenAstia() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> klikattu());
        barAstia.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> klikattu());
        this.heightProperty().addListener(e -> tutkiKoko());
        
        astia = new Astia("ä", 10);
        this.setMaxWidth(100);
        this.setMinWidth(100);
        this.setPrefWidth(100);
        
        this.getChildren().add(barAstia);
        barAstia.setProgress(0);
        barAstia.setPrefHeight(60);
        barAstia.setPrefWidth(100);
        barAstia.rotateProperty().set(-90);
        
        this.getChildren().add(labelNimi);
        labelNimi.setAlignment(Pos.CENTER);
        labelNimi.setPrefWidth(100);
        AnchorPane.setBottomAnchor(labelNimi, 0.0);
        
        this.getChildren().add(labelMaara);
        labelMaara.setAlignment(Pos.CENTER);
        labelMaara.setPrefWidth(100);
        AnchorPane.setBottomAnchor(labelMaara, 18.0);

        tutkiKoko();
    }


    /**
     *  tutkii minkä kokoinen astian pitäisi olla suhteessa maksimikokoon
     */
    protected void tutkiKoko() {
        double lh = 15; // labelNimi.getHeight();
        double hm = getHeight() - lh;
        if ( hm <= 0.1 ) return;
        double sy = hm / getMaxkoko();
        double tilavuus = Math.min(astia.getTilavuus(),getMaxkoko());
        double koko = (sy * tilavuus); 
        barAstia.setPrefWidth(koko); // koska kierretty
        //barAstia.rotateProperty().set(0);
        AnchorPane.setBottomAnchor(barAstia, koko/2 - 14);
        barAstia.setLayoutX(50 - koko/2 );
    }


    /**
     * @return suurimman astian koko
     */
    public double getMaxkoko() {
        return maxkoko;
    }


    /**
     * Suurimman astian koko määrää muiden astioiden suhteellisen koon
     * @param maxkoko mikä on suurimman astian koko
     */
    public void setMaxkoko(double maxkoko) {
        this.maxkoko = maxkoko;
        tutkiKoko();
    }


    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }


    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
        if ( selected ) labelNimi.setStyle("-fx-background-color: blue;");
        else labelNimi.setStyle("-fx-background-color: none;");
    }


    /**
     * @return astia jota näytetään
     */
    public Astia getAstia() {
        return astia;
    }


    /**
     * Asetetaan näytettävä astia
     * @param astia jota näytetään
     */
    public void setAstia(Astia astia) {
        this.astia = astia;
        tutkiKoko();
        muutos(astia);
        labelNimi.setText(astia.getNimi());
        astia.addMaaraMuuttuuListener(this);
    }


    @Override
    public void muutos(Astia muuttuvaAstia) {
        if ( muuttuvaAstia.getTilavuus() <= 0 ) return;
        double tilavuus = Math.min(astia.getTilavuus(),getMaxkoko());
        barAstia.setProgress(muuttuvaAstia.getMaara()/tilavuus);
        labelMaara.setText(""+muuttuvaAstia.getMaara());
        tutkiKoko();
    }


    /**
     * Lisätään kuuntelija kuuntelemaan astian klikkausta
     * @param listener uusi kuuntelija
     */
    public void addAstiaClickedListener(AstiaClickedListener listener) {
        astiaClickedListenerList.add(listener);
    }


    /**
     * Komponettia on klikattu
     */
    protected void klikattu() {
        for (AstiaClickedListener listener : astiaClickedListenerList)
            listener.clicked(this);
    }

}