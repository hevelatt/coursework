package demo.d3;
//import fi.jyu.mit.ohj2.Mjonot;


/**
 * @author Herman
 * @version 6.6.2020
 *
 */
public class Pvm
{
 // BYCODEBEGIN
    private int p;
    private int k;
    private int v;
    
    /**
     * Alustaa päivämäärän
     * @param p Päivä
     * @param k Kuukausi
     * @param v Vuosi
     * @example
     * <pre name="test">
     * new Pvm(12, 12, 2012).toString() === "12.12.2012";
     * </pre>
     */
    public Pvm(int p, int k, int v) {
        this.p = p;
        this.k = k;
        this.v = v;
    }
    
    /**
     * @return päivämäärä merkkijonona muodossa p.k.v
     * @example
     * <pre name="test">
     * new Pvm(12, 12, 2012).toString() === "12.12.2012";
     * </pre>
     */
    @Override
    public String toString() {
        return p + "." + k + "." + v;
    }

   /**
    * @param args ei käytössä
    * @example
    * <pre name="test">
    * Pvm p1 = new Pvm(1, 4, 2020);
    * p1.toString() === "1.4.2020";
    * </pre>
    */
    public static void main(String[] args) {
       Pvm p1 = new Pvm(21, 1,  2015);
       Pvm p2 = new Pvm(12, 12, 2012);
       Pvm p3 = new Pvm(19, 8,  1990);
       System.out.println(p1);
       System.out.println(p2);
       System.out.println(p3);
    }

   // BYCODEEND
}


