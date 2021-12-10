package demo.d6;

/**
 * @author Herman
 * @version 19.6.2020
 *
 */
public class Lentokone extends Kulkuneuvo {

    private double lentokorkeus;
    
    /**
     * Lentokoneen muodostaja
     * @param nopeus Lentokoneen nopeus
     * @param matkustajat Lentokoneen matkustajien lukumäärä
     * @param lentokorkeus Lentokoneen lentokorkeus
     */
    public Lentokone(double nopeus, int matkustajat, double lentokorkeus) {
        super(nopeus, matkustajat);
        this.lentokorkeus = lentokorkeus;
    }
    
    /**
     * Lentokoneen muodostaja
     * @param nopeus Lentokoneen nopeus
     * @param matkustajat Lentokoneen matkustajien lukumäärä
     */
    public Lentokone(double nopeus, int matkustajat) {
        super(nopeus, matkustajat);
    }
    
    /**
     * Lentokoneen muodostaja
     * @param nopeus Lentokoneen nopeus
     */
    public Lentokone(double nopeus) {
        super(nopeus);
    }
    
    /**
     * Lentokoneen oletusmuodostaja
     */
    public Lentokone() {
        super();
    }
    
    /**
     * Lentokorkeuden saantimetodi
     * @return Palauttaa lentokorkeuden
     */
    public double getLentokorkeus() {
        return lentokorkeus;
    }
    
    /**
     * Muuttaa lentokoneen lentokorkeutta satunnaisesti
     * @param maara Kuinka paljon turbulenssia on (kuinka vahva lentokorkeuden muutos on)
     */
    public void turbulenssi(double maara) {
        int etumerkki = 1;
        if (Math.random() < 0.5) etumerkki = -1;
        lentokorkeus += Math.random() * etumerkki * maara;
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Lentokone lentsikka = new Lentokone(1000, 2, 500);
        System.out.println("Lentokorkeus on " + lentsikka.getLentokorkeus() + ".");
        System.out.println("Oho turbulenssia.");
        lentsikka.turbulenssi(40);
        System.out.println("Lentokorkeus on " + lentsikka.getLentokorkeus() + ".");
        
    }

}
