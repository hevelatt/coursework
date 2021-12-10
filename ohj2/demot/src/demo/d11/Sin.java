package demo.d11;

import fi.jyu.mit.ohj2.Mjonot;
import fi.jyu.mit.ohj2.Syotto;

/**
 * Ohjelmalla piirretään sinin kuvaaja annetuilla tiedoilla.
 * @author Vesa Lappalainen
 * @version 1.0, 21.02.2003
 */
public class Sin {

    /**
     * Rajapinta kaikille funktiolle R->R
     */
    public interface FunktioRR {
        /**
         * @param x piste jossa lasketaan
         * @return funktion arvo pisteessä
         */
        public double f(double x);
    }

    /**
     * Luokka sini-funktiolle
     */
    public static class SinFun implements FunktioRR {
        @Override
        public double f(double x) {
            return Math.sin(x);
        }
    }
    
    /**
     * Palauttaa siistin funktion minimin likiarvon annetulla välillä
     * @param f funktio-olio jonka minimiä etsitään
     * @param x1 vaihteluvälin alkuarvo
     * @param x2 vaihteluvälin loppuarvo
     * @param dx askeleen pituus
     * @return likiarvo funktion minimille vaihteluvälillä
     * @example
     * <pre name="test">
     * funMin((x) -> x*x + 1 , 0, 3, 0.5)  ~~~        1.0;
     * funMin((x) -> x*x + 2 , 0, 3, 0.5)  ~~~        2.0;
     * funMin((x) -> -x*x + 1 , 0, 3, 0.5) ~~~ -3.0*3.0+1;
     * </pre>
     */
    public static double funMin(FunktioRR f, double x1, double x2, double dx) {
        double min = f.f(x1);
        for (double x = x1 + dx; x < x2+dx/2; x += dx) // varmistetaan että x käy myös loppuarvossa
            if (f.f(x) < min)
                min = f.f(x);
        return min;
    }
    
    /**
     * Palauttaa siistin funktion minimin likiarvon annetulla välillä
     * @param f funktio-olio jonka minimiä etsitään
     * @param x1 vaihteluvälin alkuarvo
     * @param x2 vaihteluvälin loppuarvo
     * @param dx askeleen pituus
     * @return likiarvo funktion maksimille vaihteluvälillä
     * @example
     * <pre name="test">
     * funMax((x) -> x*x + 1 , 0, 3, 0.5)  ~~~ 10.0;
     * funMax((x) -> x*x + 2 , 0, 3, 0.5)  ~~~ 11.0;
     * funMax((x) -> -x*x + 1 , 0, 3, 0.5) ~~~  1.0;
     * </pre>
     */
    public static double funMax(FunktioRR f, double x1, double x2, double dx) {
        double max = f.f(x1);
        for (double x = x1 + dx; x < x2+dx/2; x += dx) // varmistetaan että x käy myös loppuarvossa
            if (f.f(x) > max)
                max = f.f(x);
        return max;
    }

    /**
     * Piirtää annetun funktion annetulla välillä
     * @param f Funktio joka piirretään
     * @param x1 Vaihteluvälin alku
     * @param x2 Vaihteluvälin loppu
     * @param y1 Arvojen välin alku
     * @param y2 Arvojen välin loppu
     * @param dx askeleen pituus
     */
    public static void piirra(FunktioRR f, double x1, double x2, double y1, double y2, double dx) {
        double vali = x2 - x1;
        if (vali == 0) return;
        double dy = dx * dx * ((y2 - y1) / vali);
        for (double x = x1; x < x2; x += dx)
            // Mjonot.tayta
            for (double y = y1; y < f.f(x) + dy; y += dy)
                if (y < f.f(x))
                    System.out.print(' ');
                else
                    System.out.println('*');
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        double x1 = -5, x2 = 5, dx = 0.5, y1, y2;
        String s;
        FunktioRR f = new SinFun();

        System.out.println("Piirrän funktion sin(x)");
        s = Syotto.kysy("Anna väli ja tiheys jolla piirretään",
                Mjonot.fmt(x1, 4, 2) + " " + Mjonot.fmt(x2, 4, 2) + " "
                        + Mjonot.fmt(dx, 4, 2));
        StringBuffer sb = new StringBuffer(s);
        x1 = Mjonot.erota(sb, ' ', x1);
        x2 = Mjonot.erota(sb, ' ', x2);
        dx = Mjonot.erota(sb, ' ', dx);

        y1 = funMin(f, x1, x2, dx);
        y2 = funMax(f, x1, x2, dx);

        piirra(f, x1, x2, y1, y2, dx);

    }
}