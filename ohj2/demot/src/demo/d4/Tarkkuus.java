package demo.d4;

/**
 * Luokka tarkkuuslaskentaa varten
 * @author Herman
 * @version 13.6.2020
 *
 */
public class Tarkkuus {

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
        testaaEtumerkki(37-36.8, 0.3);
        testaaEtumerkki(0.890-0.8, 0.01);
        testaaEtumerkki(0.809-0.8, 0.01);
        testaaEtumerkki(0.801-0.8, 0.001);
        testaaEtumerkki(0.8009-0.8, 0.001);
        testaaEtumerkki(0.790-0.8, 0.01);
        testaaEtumerkki(0.791-0.8, 0.01);
        testaaEtumerkki(0.799-0.8, 0.001);
        testaaEtumerkki(0.7991-0.8, 0.001);
    }
    
    /**
     * Testataan funktiota etumerkki: tulostetaan, oliko luku 0 tarkkuuden eps rajoissa
     * @param x Luku, jota verrataan nollaan
     * @param eps Tarkkuus, positiivinen luku, jonka rajoissa verrataan
     */
    private static void testaaEtumerkki(double x, double eps) {
        if(etumerkki(x, eps) > 0) {
            System.out.println("Luku on suurempi kuin nolla tarkkuuden " + eps + " rajoissa.");
            return;
        }
        if(etumerkki(x, eps) < 0) {
            System.out.println("Luku on pienempi kuin nolla tarkkuuden " + eps + " rajoissa.");
            return;
        }
        System.out.println("Luku on nolla tarkkuuden " + eps + " rajoissa.");
    }

}
