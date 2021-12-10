package demo.d4;

/**
 * Apulaskuja iän laskemiseksi
 * @author Herman
 * @version 13.6.2020
 *
 */
public class Ika {
    
    /**
     * Nykyinen vuosi
     */
    public final static int VUOSI = 2020;
    
    /**
     * Laskee iän syntymävuoden perusteella
     * @param syntymavuosi Vuosi, jonka perusteella ikä lasketaan
     * @return Kuinka paljon täyttää tänä vuonna
     * @example
     * <pre name="test">
     * laskeIka(1990) === 30;
     * laskeIka(2020) === 0;
     * laskeIka(0) === 2020;
     * laskeIka(-10) === 2030;
     * laskeIka(2030) === -10;
     * </pre>
     */
    public static int laskeIka(int syntymavuosi) {
        return VUOSI - syntymavuosi;
    }
}
