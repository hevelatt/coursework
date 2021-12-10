package demo.d9;

/**
 * Integer-luokka, jossa arvoa voi muuttaa
 * @author Vesa Lappalainen
 * @version 1.0, 09.03.2003
 */
public class Int implements Cloneable {
    private int arvo;

    /**
     * @param arvo alustuksessa annettava arvo
     */
    public Int(int arvo)            { this.arvo = arvo;           }

    /**
     * @return luvun arvo
     */
    public int intValue()           { return arvo;                }

    /**
     * @param arvo uusi arvo luvulla
     * @example
     * <pre name="test">
     * Int luku = new Int(3);
     * luku.intValue() === 3;
     * luku.set(5);
     * luku.intValue() === 5;
     * </pre>
     */
    public void set(int arvo)       { this.arvo = arvo;           }

    /**
     * @return arvo merkkijonona
     * @example
     * <pre name="test">
     * Int luku = new Int(3);
     * luku.toString() === "3";
     * </pre>
     */
    @Override
    public String toString()        { return "" + intValue();     }

    /**
     * @return kopio luvusta
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * Int luku  = new Int(3);
     * Int luku2 = luku.clone();
     * luku.intValue()  === 3;
     * luku2.intValue() === 3;
     * luku.set(5);
     * luku.intValue()  === 5;
     * luku2.intValue() === 3;
     * </pre>
     */
    @Override
    public Int clone()  throws CloneNotSupportedException {
        Int n = (Int)super.clone();
        return n;
    }

    /**
     * @param o olio johon verrataan
     * @return true jos sama sisältö kuin o:lla, muuten false
     * @example
     * <pre name="test">
     * Int luku = new Int(3);
     * luku.equals(null) === false; 
     * "3".equals(luku)  === false;
     * Int luku2 = new Int(3);
     * luku.equals(luku2) === true;
     * luku.set(5);
     * luku.equals(luku2) === false;
     * </pre>
     */
    @Override
    public boolean equals(Object o) {
        if ( !(o instanceof Int) ) return false;
        Int i = (Int)o;
        return intValue() == i.intValue();
    }


    /**
     * Palauttaa olion lajitteluarvon
     * @return olion oma arvo
     */
    @Override
    public int hashCode() {
        return arvo;
    }


    /**
     * Testataan lukua
     * @param args ei käytössä
     */
    public static void main(String[] args)  {
        Int i = new Int(99);
        System.out.println(i);
        i.set(77);
        System.out.println(i);
    }
}