package demo.d6;

/**
 * Kantaluokka kulkuneuvoille
 * @author Herman
 * @version 19.6.2020
 *
 */
public class Kulkuneuvo {
    
    private double nopeus;
    private int matkustajat;
    
    /**
     * Kulkuneuvon muodostaja
     * @param nopeus Kulkuneuvon nopeus
     * @param matkustajat Kulkuneuvon matkustajien lukumäärä
     */
    public Kulkuneuvo(double nopeus, int matkustajat) {
        this.nopeus = nopeus;
        this.matkustajat = matkustajat;
    }
    
    /**
     * Kulkuneuvon muodostaja
     * @param nopeus Kulkuneuvon nopeus
     */
    public Kulkuneuvo(double nopeus) {
        this.nopeus = nopeus;
    }
    
    /**
     * Oletusmuodostaja
     */
    public Kulkuneuvo() {
        //
    }
    
    @Override
    public String toString() {
        return nopeus + " " + matkustajat;
    }
    
    /**
     * Nopeuden saantimetodi
     * @return Kulkuneuvon nopeus
     */
    public double getNopeus() {
        return nopeus;
    }
    
    /**
     * Matkustajien saantimetodi
     * @return Kulkuneuvon matkustajien lukumäärä
     */
    public int getMatkustajat() {
        return matkustajat;
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kulkuneuvo vehicle = new Kulkuneuvo(5);
        System.out.println("Kulkuneuvon nopeus ja matkustajakapasiteetti: " + vehicle);
    }

}
