package demo.d6;

/**
 * Luokka kulkuneuvosta peritylle laivalle
 * @author Herman
 * @version 19.6.2020
 *
 */
public class Laiva extends Kulkuneuvo {
    
    private double uppoamiskerroin; //laivan ominaisuus: kuinka helposti uppoaa
    
    /**
     * Laivan muodostaja
     * @param nopeus Laivan nopeus
     * @param matkustajat Laivan matkustajien lukumäärä
     */
    public Laiva(double nopeus, int matkustajat) {
        super(nopeus, matkustajat);
        arvoUppoamiskerroin();
    }
    
    /**
     * Laivan muodostaja
     * @param nopeus Laivan nopeus
     */
    public Laiva(double nopeus) {
        super(nopeus);
        arvoUppoamiskerroin();
    }
    
    /**
     * Laivan oletusmuodostaja
     */
    public Laiva() {
        super();
        arvoUppoamiskerroin();
    }
    
    /**
     * Arvotaan uppoamiskerroin
     */
    public void arvoUppoamiskerroin() {
        uppoamiskerroin = Math.random()*100;
    }
    
    /**
     * Tulostaa uppoaako laiva
     * @param myrskykerroin kuinka kova myrsky
     * @return uppoaako laiva
     */
    public boolean uppoaako(double myrskykerroin) {
        if (uppoamiskerroin < myrskykerroin*Math.random()) return true;
        return false;
    }
    
    /**
     * Upotetaan laiva
     * @param myrskykerroin Kuinka vahva myrsky on
     * @param myrskyt Kuinka monesta myrsykstä laiva joutuu selviämään
     */
    public void upotetaan(double myrskykerroin, int myrskyt) {
        int i = 1;
        while (!uppoaako(myrskykerroin) && i < myrskyt) i++;
        if (i < myrskyt)
            System.out.println("Laiva upposi " + i + ". myrskyssä.");
        else 
            System.out.println("Laiva selvisi myrskyistä!");
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Laiva titanic = new Laiva(50, 500);
        titanic.upotetaan(50, 100);
        titanic.arvoUppoamiskerroin();
        titanic.upotetaan(50, 100);
    }

}
