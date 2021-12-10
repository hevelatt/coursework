package demo.d4;

/**
 * @author Herman
 * @version 13.6.2020
 *
 */
public class Demo {
    
    /**
     * Laskee demohyvityspisteet palautettujen tehtävien pisteiden ja demotehtävien maksimimäärän perusteella
     * @param pisteet Palautettujen demotehtävien pisteet
     * @param maksimi Demotehtävien maksimipistemäärä
     * @return Demohyvityspisteet
     * @example
     * <pre name="test">
     * demohyvitys(5, 6) === 5; //Normaali tapaus
     * demohyvitys(3, 6) === 2; //Tasan sama prosentti
     * demohyvitys(50, 50) === 6; //Kaikki tehty
     * demohyvitys(51, 50) === 6; //On tehty ylimääräistä
     * demohyvitys(49, 50) === 6; //On tehty melkein kaikki
     * demohyvitys(9, 10) === 6; //On tehty 90%
     * demohyvitys(0, 10) === 0; //Ei ole tehty mitään
     * demohyvitys(1, 10) === 0; //On tehty hyvin vähän
     * demohyvitys(-1, 0) === 6; //Maksimipistemäärää ei ole joten saadaan maxhyvitys automaattisesti
     * demohyvitys(2, -1) === 6; //Maksimipistemäärää ei ole joten saadaan maxhyvitys automaattisesti
     * demohyvitys(-1, 10) === 0; //Negatiiviset pisteet
     * </pre>
     */
    public static int demohyvitys(int pisteet, int maksimi) {
        double[] hyvityspisteaskeleet = {0.4, 0.5, 0.6, 0.7, 0.8, 0.9}; //Jokainen askel vastaa yhtä pistettä
        int maxhyvitys = hyvityspisteaskeleet.length;
        
        if (maksimi <= 0) return maxhyvitys; //Jos maksimi on pienempi kuin nolla on varmasti tehty tarpeeksi maksimihyvityksen eteen
        
        double prosentti = (double) pisteet / (double) maksimi;      
        
        int i = 0;
        while(prosentti >= hyvityspisteaskeleet[i] || Tarkkuus.etumerkki(prosentti-hyvityspisteaskeleet[i], 0.001) == 0) { //Verrataan prosenttia hyvityspisteaskeleisiin kolmen desimaalin tarkkuudella koska pientä epätarkkuutta tulee olemaan
            i++;
            if (i > maxhyvitys - 1) break; //Ollaan jo maksimipisteissä
        }
        
        return i; //Palautetaan niin monta pistettä kuin on edetty askelta prosenttitaulukossa
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
        System.out.println(demohyvitys(5, 6));
        System.out.println(demohyvitys(-1, 10));
        System.out.println(demohyvitys(10, 6));  
    }
}
