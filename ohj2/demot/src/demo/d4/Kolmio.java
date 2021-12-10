package demo.d4;

/**
 * Luokka kolmiolaskuja varten, testit JUnitilla
 * @author Herman
 * @version 13.6.2020
 *
 */
public class Kolmio {

    /**
     * Laskee kolmion pinta-alan kateettien perusteella
     * @param kanta Kolmion kannan pituus
     * @param korkeus Kolmion korkeus eli kannan etäisyys sen vastapäisestä kärjestä
     * @return Kolmion pinta-ala
     */
    public static double kolmionAla(double kanta, double korkeus) {
        if (kanta*korkeus <= 0) return 0; //ei hyväksytä negatiivisia tuloksia
        return (kanta * korkeus) / 2;
    }

    /**
     * Palauttaa suorakulmaisen kolmion hypotenuusan pituuden
     * @param kateetti1 Ensimmäinen kateetti
     * @param kateetti2 Toinen kateetti
     * @return Kolmion hypotenuusan pituus
     */
    public static double hypotenuusa(double kateetti1, double kateetti2) {
        if ((kateetti1 == 0) || (kateetti2 == 0)) return 0;
        return Math.sqrt(kateetti1*kateetti1 + kateetti2*kateetti2);
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        System.out.println("Suorakulmaisen kolmion kateettien pituudet ovat 3 ja 4.");
        System.out.println("Hypotenuusan pituus: " + hypotenuusa(3, 4));
        System.out.println("Kolmion pinta-ala: " + kolmionAla(3, 4));
    }
}