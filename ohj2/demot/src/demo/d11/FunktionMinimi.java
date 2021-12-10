package demo.d11;

import static java.lang.Math.*;

/**
 * @author Herman
 * @version 19.7.2020
 *
 */
public class FunktionMinimi {

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
     * Luokka 2-asteen polynomille
     */
    public static class P2 implements FunktioRR {
        private double a;
        private double b;
        private double c;

        /**
         * Alustetaan polynomiksi x^2 
         */
        public P2() {
            a = 1;
        }


        /**
         * Alustetaan polynomi kertoimilla
         * @param a x^2 kerroin
         * @param b x:n kerroin
         * @param c vakiotermi
         */
        public P2(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public double f(double x) {
            return a * x * x + b * x + c;
        }
    }

    /**
     * Integroidaan sin(x) välillä x1-x2
     * @param f integroitva funktio-olio
     * @param x1 alkuarvo
     * @param x2 loppuarvo
     * @param tiheys monellako askeleella
     * @return likiarvo integraalille
     */
    public static double integroi(FunktioRR f, double x1, double x2, int tiheys) {
        double summa = 0;
        double dx = (x2 - x1) / tiheys;
        for (double x = x1 + dx / 2; x < x2; x += dx)
            summa += f.f(x) * dx;
        return summa;
    }
    
    /**
     * Palauttaa siistin funktion minimin likiarvon annetulla välillä
     * @param f funktio-olio jonka minimiä etsitään
     * @param x1 vaihteluvälin alkuarvo
     * @param x2 vaihteluvälin loppuarvo
     * @param tiheys monellako askeleella
     * @return likiarvo funktion minimille vaihteluvälillä
     * @example
     * <pre name="test">
     * funMin((x) -> x*x + 1 , 0, 3, 10) ~~~ 1.0;
     * funMin((x) -> x*x + 2 , 0, 3, 100) ~~~ 2.0;
     * funMin((x) -> -x*x + 1 , 0, 3, 1000) ~~~ -3.0*3.0+1;
     * </pre>
     */
    public static double funMin(FunktioRR f, double x1, double x2, int tiheys) {
        double min = f.f(x2); // varmistetaan että x käy myös loppuarvossa
        double dx = (x2 - x1) / tiheys;
        for (double x = x1; x < x2; x += dx) { 
            double y = f.f(x);
            if (y < min)
                min = y;
        }
        return min;
    }

    /**
     * Palauttaa siistin funktion minimin likiarvon annetulla välillä 1000 askeleella
     * @param f funktio-olio jonka minimiä etsitään
     * @param x1 vaihteluvälin alkuarvo
     * @param x2 vaihteluvälin loppuarvo
     * @return likiarvo funktion minimille vaihteluvälillä
     * @example
     * <pre name="test">
     * funMin((x) -> x*x + 1 , 0, 3) ~~~ 1.0;
     * funMin((x) -> x*x + 2 , 0, 3) ~~~ 2.0;
     * funMin((x) -> -x*x + 1 , 0, 3) ~~~ -3.0*3.0+1;
     * </pre>
     */
    public static double funMin(FunktioRR f, double x1, double x2) {
        return funMin(f, x1, x2, 1000);
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        SinFun sinf = new SinFun();
        P2 p2 = new P2(1, 2, -3); // x^2 + 2x - 3
        FunktioRR exp = new FunktioRR() {
            @Override
            public double f(double x) { 
                return Math.exp(x); 
            }
        };
        String format = "%17.15f%n";
        
        // Sin välillä [0, Pi]
        double min = funMin(sinf, 0, PI);
        System.out.printf(format, min);
        // x^2 + 2x - 3 välillä [0, 3]
        min = funMin(p2, 0, 3);
        System.out.printf(format, min);
        // e^x välillä [0, 3]
        min = funMin(exp, 0, 3);
        System.out.printf(format ,min);
        // x^2 + 1 välillä [0, 3]
        min = funMin(new FunktioRR() {
                @Override
                public double f(double x) {
                    return x*x + 1; 
                }
            }, 0, 3);
        System.out.printf(format, min);
        // x^2 + 1 välillä [0, 3]
        min = funMin((x) -> x*x + 1 , 0, 3);
        System.out.printf(format, min);
        // 4 välillä [0, 3]
        min = funMin(x -> 4 , 0, 3);
        System.out.printf(format, min);

    }

}
