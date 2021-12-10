package demo.d4;

/**
 * @author Herman
 * @version 13.6.2020
 *
 */
public class DemoMalli {
    
    /**
     * Laskee demohyvityspisteet palautettujen tehtävien pisteiden ja demotehtävien maksimimäärän perusteella
     * @param pistetaulu Pistetaulu, jonka mukaan arvioidaan
     * @param pisteet Palautettujen demotehtävien pisteet
     * @param maksimi Demotehtävien maksimipistemäärä
     * @return Demohyvityspisteet
     * @example
     * <pre name="test">
     * int[][] pistetaulu = { {40, 50, 60, 70, 80, 90},{1,2,3,4,5,6} };
     * demohyvitys(pistetaulu, 5, 6) === 5; //Normaali tapaus
     * demohyvitys(pistetaulu, 3, 6) === 2; //Tasan sama prosentti
     * demohyvitys(pistetaulu, 50, 50) === 6; //Kaikki tehty
     * demohyvitys(pistetaulu, 51, 50) === 6; //On tehty ylimääräistä
     * demohyvitys(pistetaulu, 49, 50) === 6; //On tehty melkein kaikki
     * demohyvitys(pistetaulu, 9, 10) === 6; //On tehty 90%
     * demohyvitys(pistetaulu, 0, 10) === 0; //Ei ole tehty mitään
     * demohyvitys(pistetaulu, 1, 10) === 0; //On tehty hyvin vähän
     * demohyvitys(pistetaulu, -1, 0) === 6; //Maksimipistemäärää ei ole joten saadaan maxhyvitys automaattisesti
     * demohyvitys(pistetaulu, 2, -1) === 6; //Maksimipistemäärää ei ole joten saadaan maxhyvitys automaattisesti
     * demohyvitys(pistetaulu, -1, 10) === 0; //Negatiiviset pisteet
     * </pre>
     */
    public static int demohyvitys(int[][] pistetaulu, int pisteet, int maksimi) {
        int prosentti;
        
        if (maksimi <= 0) 
            prosentti = 100;
        else 
            prosentti = (int)Math.round(100.0 * pisteet/maksimi);
        
        for (int i = pistetaulu[0].length - 1; i >= 0; i--) {
            if (pistetaulu[0][i] <= prosentti) return pistetaulu[1][i];
        }
        return 0;
    }
    
    /**
     * Kertoo, onko luku x vähemmän vai enemmän vai yhtäsuuri kuin 0 tarkkuuden eps rajoissa
     * @param x Luku, jota verrataan nollaan
     * @param eps Tarkkuus, positiivinen luku, jonka rajoissa verrataan
     * @return Palauttaa 0 jos x = 0, 1 jos x > 0 ja -1 jos x < 0 tarkkuuden esp rajoissa
     * @example
     * <pre name="test">
     * etumerkki(37-36.8, 0.3) === 0;
     * etumerkki(1, 0.1) === 1;
     * etumerkki(-1, 0.1) === -1;
     * etumerkki(1, -0.1) === 1;
     * etumerkki(1, 0) === 1;
     * etumerkki(0, 0) === 0;
     * etumerkki(0, 1) === 0;
     * </pre>
     */
    public static int etumerkki(double x, double eps) {
        double tarkkuus = eps;
        if (eps < 0) tarkkuus = -eps;
        if (x < -tarkkuus) return -1;
        if (x > tarkkuus) return 1;
        return 0;
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        int[][] pistetaulu = {
                {40, 50, 60, 70, 80, 90},
                {1,2,3,4,5,6}
        };
        System.out.println(demohyvitys(pistetaulu, 5, 6));
        System.out.println(demohyvitys(pistetaulu, -1, 10));
        System.out.println(demohyvitys(pistetaulu, 10, 6));  
    }
}
