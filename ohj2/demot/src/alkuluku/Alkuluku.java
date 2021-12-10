/**
 * 
 */
package alkuluku;

/**
 * Tutkitaan alkuluku-aliohjelmaa
 * @author Herman
 * @version 1.6.2020
 *
 */
public class Alkuluku {

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        int jaollinen = millaJaollinen(25);
        if (jaollinen == 1) System.out.println("25 on alkuluku");
        else System.out.println("25 ei ole alkuluku kun se on jaollinen luvulla " + jaollinen);
    }

    /**
     * Tutkitaan onko luku alkuluku, jos ei niin palautetaan millä jaollinen
     * 
     * Jaetaan tutkittavaa lukua jakajilla 2,3,5,7...luku/2.
     * Jos jokin jako menee tasan, niin ei alkuluku:
     * 
     * @param luku tutkittava luku
     * @return 1 jos alkuluku, muuten millä jaollinen
     * @example
     * <pre name="test">
     *      millaJaollinen(2) === 1;
     *      millaJaollinen(25) === 5;
     *      millaJaollinen(23) === 1;
     *      millaJaollinen(7) === 1;
     * </pre>
     */
    public static int millaJaollinen(int luku) {
        if (luku == 2) return 1;
        int jakaja = 2;
        int kasvatus = 1;
        
        do {
            int jakojaannos = luku % jakaja;
            if (jakojaannos == 0) return jakaja;
        
            jakaja += kasvatus;
            kasvatus = 2;        
        } while (jakaja < luku /2);
        
        return 1; 
    }
}