package demo.d9;

/**
 * @author Herman
 * @version 4.7.2020
 *
 */
public class Pnouseva {
    /**
     * Testataan pisinNouseva-aliohjelmaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        System.out.println(pisinNouseva("abajiuxc"));
        System.out.println(pisinNouseva("kissa"));
        System.out.println(pisinNouseva("abcdefg"));
        System.out.println(pisinNouseva("dcba"));
        System.out.println(pisinNouseva("ab"));
        System.out.println(pisinNouseva("a"));
        System.out.println(pisinNouseva(""));
    }
    
    // BYCODEBEGIN
    /**
     * Palauttaa pisimmän kasvavan merkkijoukon pituuden
     * @param jono Jono, josta etsitään kasvavia merkkijoukkoja
     * @return Pisimmän kasvavan merkkijoukon pituuden
     * @example
     * <pre name="test">
     * pisinNouseva("abajiuxac") === 3;
     * pisinNouseva("kissa") === 3;
     * pisinNouseva("") === 0;
     * pisinNouseva("a") === 1;
     * pisinNouseva("ab") === 2;
     * pisinNouseva("ba") === 1;
     * </pre>
     */
    public static int pisinNouseva(String jono) {
        if (jono.length() < 1) return 0;
        if (jono.length() == 1) return 1;
        
        int pituus = 1;
        int pisin = 1;
        
        for (int i = 0; i < jono.length() - 1; i++) {
            if (jono.charAt(i+1) < jono.charAt(i)) {
                if (pisin < pituus)
                    pisin = pituus;
                pituus = 1;
            } else
                pituus++;
        }
        if (pisin < pituus)
            pisin = pituus;
        return pisin;
    }
    // BYCODEEND
}
