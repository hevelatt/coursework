package demo.d8;

/**
 * Ohjelmalla tutkitaan postimaksun suuruutta
 * @author Vesa Lappalainen
 * @version 1.0, 21.02.2003
 */
public class Kirje {

    /**
     * Kokeillaan kutsua kirjeenpainoa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        int paino = suurinKirjeenPainoIf(3.60);
        System.out.println("Tällä voi lähettää " + paino + " g");  
    }

    
    // private static int[] painot    = {   50 , 100,  250,  500, 1000, 2000, 0 };
    // private static double[] hinnat = { 0.60, 0.90, 1.30, 2.10, 3.50, 5.50, 0 };
    
    private static Hinta[] hinnat = { new Hinta(50, 0.60), 
                                          new Hinta(100, 0.90), 
                                          new Hinta(250, 1.30), 
                                          new Hinta(500, 2.10), 
                                          new Hinta(1000, 3.50), 
                                          new Hinta(2000, 5.50), 
                                          new Hinta(0, 0) };
    
    private static double[][] postimaksut = { {   50 , 100,  250,  500, 1000, 2000, 0 },
                                              { 0.60, 0.90, 1.30, 2.10, 3.50, 5.50, 0 } };
    
    /**
     * Palautetaan suurin kirjeen paino, joka voidaan rahamäärällä lähettää<br>
     * Toteutus if-lauseiden avulla.
     * @param rahaa käytössä oleva rahamäärä
     * @return suurin kirjeen paino joka voidaan lähettää
     * @example
     * <code>
     * <pre name="test">
     * suurinKirjeenPainoIf($rahaa) ~~~ $paino;
     * 
     *   $rahaa | $paino
     * -------------------
     *    10.00 | 2000
     *     3.60 | 1000
     *     3.50 | 1000
     *     3.40 |  500
     *     0.50 |    0
     * </pre>
     * </code>    
     */
    public static int suurinKirjeenPainoIf(double rahaa) {
        if (5.50 <= rahaa) return 2000;
        if (3.50 <= rahaa) return 1000;
        if (2.10 <= rahaa) return 500;
        if (1.30 <= rahaa) return 250;
        if (0.90 <= rahaa) return 100;
        if (0.60 <= rahaa) return 50;
        return 0;
    }


    /**
     * Palautetaan suurin kirjeen paino, joka voidaan rahamäärällä lähettää<br>
     * Toteutus taulukoiden avulla.
     * @param rahaa käytössä oleva rahamäärä
     * @return suurin kirjeen paino joka voidaan lähettää
     * 
     * @example
     * <pre name="test">
     * suurinKirjeenPaino($rahaa) ~~~ $paino;
     * suurinKirjeenPaino2($rahaa) ~~~ $paino;
     * suurinKirjeenPainoIf($rahaa) ~~~ $paino;
     * 
     *   $rahaa | $paino
     * -------------------
     *    10.00 | 2000
     *     5.50 | 2000
     *     5.49 | 1000
     *     3.50 | 1000
     *     3.49 |  500
     *     2.10 |  500
     *     2.09 |  250  
     *     1.30 |  250
     *     1.29 |  100
     *     0.90 |  100
     *     0.89 |   50
     *     0.60 |   50
     *     0.59 |    0
     *     0.50 |    0
     * </pre>
     */
    public static int suurinKirjeenPaino(double rahaa) {
        int i;
        int paino = 0;

        for (i = hinnat.length - 2; i >= 0; i--)
            if (hinnat[i].getHinta() <= rahaa) return hinnat[i].getPaino();
        return paino;
    }


    /**
     * Palautetaan suurin kirjeen paino, joka voidaan rahamäärällä lähettää<br>
     * Toteutus taulukoiden avulla joissa loppumerkki.
     * @param rahaa käytössä oleva rahamäärä
     * @return suurin kirjeen paino joka voidaan lähettää
     */
    public static int suurinKirjeenPaino2(double rahaa) {
        int i;
        int paino = 0;

        for (i = 0; hinnat[i].getHinta() > 0; i++)
            if (hinnat[i].getHinta() <= rahaa) paino = hinnat[i].getPaino();
        return paino;
    }
    
    /**
     * Palauttaa painoa vastaavan postimaksun
     * @param paino Kirjeen paino
     * @return Kirjeen painoa vastaava postimaksu
     * @example
     * <pre name="test">
     * postimaksu(40) ~~~ 0.60;
     * postimaksu(51) ~~~ 0.60;
     * postimaksu(100) ~~~ 0.90;
     * postimaksu(101) ~~~ 0.90;
     * postimaksu(4000) ~~~ 5.50;
     * postimaksu(-1) ~~~ 0.60;
     * </pre>
     */
    public static double postimaksu(int paino) {
        double hinta = hinnat[0].getHinta();
        for (int i = 1; i < hinnat.length; i++)
            if (hinta < hinnat[i].getHinta() && hinnat[i].getPaino() <= paino) 
                hinta = hinnat[i].getHinta();
        return hinta;
    }
    
    /**
     * Palautetaan suurin kirjeen paino, joka voidaan rahamäärällä lähettää<br>
     * Toteutus 2-ulotteisten taulukoiden avulla.
     * @param raha käytössä oleva rahamäärä
     * @return suurin kirjeen paino joka voidaan lähettää
     * 
     * @example
     * <pre name="test">
     * suurinKirjeenPaino2D($rahaa) ~~~ $paino;
     * 
     *   $rahaa | $paino
     * -------------------
     *    10.00 | 2000
     *     5.50 | 2000
     *     5.49 | 1000
     *     3.50 | 1000
     *     3.49 |  500
     *     2.10 |  500
     *     2.09 |  250  
     *     1.30 |  250
     *     1.29 |  100
     *     0.90 |  100
     *     0.89 |   50
     *     0.60 |   50
     *     0.59 |    0
     *     0.50 |    0
     *     0.00 |    0
     *    -1.00 |    0 
     * </pre>
     */
    public static double suurinKirjeenPaino2D(double raha) {
        double paino = 0; 
        for (int i = 0; i < postimaksut[0].length; i++)
            if (paino < postimaksut[0][i] && postimaksut[1][i] <= raha) 
                paino = postimaksut[0][i];
        return paino;
    }
    
    //============================Luokat================================
    
    /**
     * Luokka kirjeiden hintoja (postimaksuja) varten
     * @author Herman
     * @version 27.6.2020
     *
     */
    public static class Hinta {
        private double hinta;
        private int    paino;
        
        /**
         * Muodostaja kirjeen hinnalle
         * @param ihinta Kuinka paljon kirje maksaa
         * @param ipaino Kuinka paljon kirje painaa
         */
        public Hinta(int ipaino, double ihinta) {
            alusta(ipaino, ihinta);
        }
        
        /**
         * Alustaa Hinnan painon ja maksun
         * @param ipaino Kirjeen paino
         * @param ihinta Postimaksun hinta
         */
        private void alusta(int ipaino, double ihinta) {
            if (ipaino < 0 || ihinta < 0) return;
            this.paino = ipaino;
            this.hinta = ihinta;
        }
        
        /**
         * Saantimetodi kirjeen hinnalle
         * @return Kirjeen hinta (postimaksu)
         */
        public double getHinta() {
            return this.hinta;
        }
        
        /**
         * Saantimetodi kirjeen painolle
         * @return Kirjeen paino
         */
        public int getPaino() {
            return this.paino;
        }
    }

}