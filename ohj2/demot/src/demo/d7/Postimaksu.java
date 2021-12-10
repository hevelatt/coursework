package demo.d7;
/**
 * @author Herman
 * @version 23.6.2020
 *
 */
public class Postimaksu {
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        double[][] postimaksut = {  {1.00,  1.40,   2.00,   4.00,   6.00,   10.00}, 
                                    {50,    100,    250,    500,    1000,   2000 }  };
        System.out.println("Vitosella voit lähettää " + suurinKirjeenPaino(5, postimaksut) + " gramman painoisen kirjeen.");
    }

    /**
     * Palauttaa suurimman kirjeen tai kirjeiden painon, jonka voi lähettää tietyllä rahasummalla
     * @param raha Kuinka paljon rahaa käytetään kirjeiden lähettelyyn
     * @param postimaksut Taulukko, jossa on postimaksut
     * @return Suurin kirjeen tai kirjeiden paino, jonka voi lähettää annetulla rahasummalla
     * @example
     * <pre name="test">
     * double[][] postimaksut = {   {1.00,  1.40,   2.00,   4.00,   6.00,   10.00}, 
     *                              {50,    100,    250,    500,    1000,   2000}   };
     * suurinKirjeenPaino(2.05, postimaksut) ~~~ 250;
     * suurinKirjeenPaino(20.00, postimaksut) ~~~ 2000;
     * suurinKirjeenPaino(21.00, postimaksut) ~~~ 2000;
     * suurinKirjeenPaino(0.00, postimaksut) ~~~ 0;
     * suurinKirjeenPaino(0.99, postimaksut) ~~~ 0;
     * suurinKirjeenPaino(-1, postimaksut) ~~~ 0;
     * postimaksut[0][0] = 0; // {{0, 1.40, 2.00, 4.00, 6.00, 10.00}, {50, 100, 250, 500, 1000, 2000}}
     * suurinKirjeenPaino(1.39, postimaksut) ~~~ 50;
     * suurinKirjeenPaino(-1, postimaksut) ~~~ 0;
     * postimaksut[0][0] = -1; // {{-1, 1.40, 2.00, 4.00, 6.00, 10.00}, {50, 100, 250, 500, 1000, 2000}}
     * suurinKirjeenPaino(-1, postimaksut) ~~~ 50; // Futureproofattu antirahan käyttöönottoa varten
     * double[][] tyhja = {};
     * suurinKirjeenPaino(2.05, tyhja) ~~~ 0;
     * double[][] vajaa = {   {1.00,  1.40,   2.00,   4.00,   6.00,   10.00}, 
     *                        {50,    100,    250                          }   };
     * suurinKirjeenPaino(2.05, vajaa) ~~~ 250;
     * suurinKirjeenPaino(20.00, vajaa) ~~~ 250;
     * double[][] vajaa2 = {  {1.00,  1.40,   2.00,                       }, 
     *                        {50,    100,    250,    500,    1000,   2000}    };
     * suurinKirjeenPaino(2.05, vajaa2) ~~~ 250;
     * suurinKirjeenPaino(20.00, vajaa2) ~~~ 250;
     * </pre>
     */
    public static double suurinKirjeenPaino(double raha, double[][] postimaksut) {

        double paino = 0;
        
        if (postimaksut == null || postimaksut.length == 0) return paino;
        if (postimaksut[0] == null || postimaksut[0].length == 0) return paino;
        if (postimaksut[1] == null || postimaksut[1].length == 0) return paino;
        
        for (int i = 0; i < postimaksut[0].length && i < postimaksut[1].length; i++)
            if (postimaksut[0][i] <= raha) 
                paino = postimaksut[1][i];
            else break;
           
        return paino;
    }
}