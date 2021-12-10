package demo.d6;

import java.awt.Graphics;
import fi.jyu.mit.graphics.*;

/**
 * Luokka monikulmioille
 * @author Markus Kivioja
 * @author Herman
 *
 */
public class SaannollinenMonikulmio extends BasicShape {

    private final RPoint center;
    private double r;
    private int resolution;
    private int[] sXPoints;
    private int[] sYPoints;
    
    /**
     * Luo uuden monikulmion
     * @param x monikulmion keskipisteen x-koordinaatti
     * @param y monikulmion keskipisteen y-koordinaatti
     * @param r monikulmion säde
     * @param k monikulmion kulmien määrä
     */
    public SaannollinenMonikulmio(double x, double y, double r, int k) {
        super();
        this.center = new RPoint(x, y);
        this.r = r;
        initialize(k);
    }


    /**
     * Luo uuden monikulmion
     * @param x monikulmion keskipisteen x-koordinaatti
     * @param y monikulmion keskipisteen y-koordinaatti
     * @param z monikulmion keskipisteen z-koordinaatti
     * @param r monikulmion säde
     */
    public SaannollinenMonikulmio(double x, double y, double z, double r) {
        super();
        this.center = new RPoint(x, y, z);
        this.r = r;
        initialize();
    }
    /**
     * Alustaa monikulmion asetukset
     */
    private void initialize(int kulmat) {
        this.resolution = kulmat;
        this.sXPoints = new int[resolution];
        this.sYPoints = new int[resolution];
    }
    
    /**
     * Alustaa monikulmion asetukset oletuksena ympyräksi
     */
    private void initialize() {
        this.resolution = 360;
        this.sXPoints = new int[resolution];
        this.sYPoints = new int[resolution];
    }


    /**
     * Asetetaan monikulmiolle uusi säde
     * @param r uusi säteen arvo
     * @return tämä olio ketjuttamista varten
     */
    public SaannollinenMonikulmio setR(double r) {
        this.r = r;
        redraw();
        return this;
    }


    /**
     * Piirretään monikulmio
     * @see fi.jyu.mit.graphics.BasicShape#drawShape(java.awt.Graphics, fi.jyu.mit.graphics.Matrix)
     */
    @Override
    protected void drawShape(Graphics g, Matrix a) {
        Vector rp = new Vector(); 
        SPoint sp = new SPoint(0, 0);
        for (int i = 0; i < resolution; i++) {
            a.transform(
                    this.r * Math.cos((2 * Math.PI / resolution) * i)
                            + this.center.getX(),
                    this.r * Math.sin((2 * Math.PI / resolution) * i)
                            + this.center.getY(),
                    this.center.getZ(), rp, sp);
            sXPoints[i] = sp.getX();
            sYPoints[i] = sp.getY();
        }
        g.drawPolygon(sXPoints, sYPoints, resolution);
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        EasyWindow window = new EasyWindow();
        window.add(new SaannollinenMonikulmio( 50, 50,30,3));
        window.add(new SaannollinenMonikulmio(150, 50,30,4));
        window.add(new SaannollinenMonikulmio( 50,150,30,5));
        window.add(new SaannollinenMonikulmio(150,150,30,6));
        window.showWindow();
    }

}
