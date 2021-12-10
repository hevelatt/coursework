package demo.d9;

/**
 * Luokka vaaliehdokkaan käsittelyä varten
 * @author Herman
 * @version 4.7.2020
 * 
 */
public class Ehdokas {

    private double rahaa;
    private int aania;
    
    /**
     * Ehdokkaan muodostaja
     * @param rahaa Ehdokkaan rahavarannot
     * @param aania Ehdokkaan alustava äänimäärä
     */
    public Ehdokas(double rahaa, int aania) {
        this.rahaa = rahaa;
        if (aania < 0)
            this.aania = 0;
        else
            this.aania = aania;
        
    }
    
    /**
     * Äänimäärän saantimetodi
     * @return äänien määrä
     */
    public int getAania() {
        return aania;
    }
    
    /**
     * Ostaa ääniä
     * @param aanet Kuinka monta ääntä ostetaan
     * @param raha Kuinka paljon yksi ääni maksaa
     * @return Riittikö raha
     * @example
     * <pre name="test">
     * Ehdokas ehd1 = new Ehdokas(100000.0,0);
     * ehd1.toString() =R= "Rahaa 100000[.,]00, ääniä 0";
     * ehd1.osta(200,100.0) === true;   // Ostetaan sopivasti 
     * ehd1.toString() =R= "Rahaa 80000[.,]00, ääniä 200";  
     * ehd1.osta(200,1000.0) === false; // Ostetaan liian kallista
     * ehd1.toString() =R= "Rahaa 80000[.,]00, ääniä 200";  
     * </pre>
     */
    public boolean osta(int aanet, double raha) {
        if (aanet*raha > rahaa)
            return false;
        aania += aanet;
        rahaa -= aanet*raha;
        return true;
    }
    
    /**
     * Vertaile ehdokasta toiseen ehdokkaaseen
     * @param vastaehdokas ehdokas johon verrataan
     * @return > 0 jos ehdokkaalla enemmän ääniä, 0 jos saman verran ääniä, < 0 jos vähemmän ääniä
     * @example
     * <pre name="test">
     * Ehdokas ehd1 = new Ehdokas(100000.0,0),ehd2 = new Ehdokas(20000.0,300);
     * ehd1.compareTo(ehd2) <  0 === true;
     * ehd2.compareTo(ehd1) >  0 === true;
     * ehd1.osta(300,10);
     * ehd2.compareTo(ehd1) == 0 === true;
     * </pre>
     */
    public int compareTo(Ehdokas vastaehdokas) {
        return aania - vastaehdokas.getAania();
    }
    
    /**
     * Ehdokkaan tiedot merkkijonona
     * @example
     * <pre name="test">
     * Ehdokas trump = new Ehdokas(1000000, 100);
     * trump.toString() =R= "Rahaa 1000000[,.]00, ääniä 100"; 
     * </pre>
     */
    @Override
    public String toString() {
        return "Rahaa " + String.format("%.2f", rahaa) + ", ääniä " + aania;
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args)  {
        Ehdokas ehd1 = new Ehdokas(100000.0, 0);
        Ehdokas ehd2 = new Ehdokas(20000.0, 300);
        System.out.println(ehd1); // Tulostaa: Rahaa 100000.00, ääniä 0
        System.out.println(ehd2); // Tulostaa: Rahaa 20000.00, ääniä 300
        ehd1.osta(200, 100.0); // Ostaa 200 ääntä, 100 mk/kpl
        System.out.println(ehd1); // Tulostaa: Rahaa 80000.00, ääniä 200
        boolean onnistui = ehd2.osta(300, 100);
        if (!onnistui) System.out.println("Rahat ei riitä :-)");
        System.out.println(ehd2); // Tulostaa: Rahaa 20000.00, ääniä 300
        if (ehd1.compareTo(ehd2) > 0) System.out.println("Ehdokas 1 voitti!");
        if (ehd1.compareTo(ehd2) < 0) System.out.println("Ehdokas 2 voitti!");
        if (ehd1.compareTo(ehd2) == 0) System.out.println("Tasapeli!");
        // Vertailu tehdään äänimäärien perusteella.
        // Esimerkissä tulostuu : Ehdokas 2 voitti!
    }
}