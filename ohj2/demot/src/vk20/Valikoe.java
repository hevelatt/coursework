package vk20;

/**
 * @author Herman
 * @version 17.7.2020
 *
 */
public class Valikoe {

    /**
     * @param args ei käytössä
     */
    public static void main(String []args){
        String[] ta = {"Osaatko", "laskea", "sanojen", "pituuksien", "keskiarvon", "?"};
        System.out.println(keskiarvo(ta));
    }

    // BYCODEBEGIN
    /**
     * Laskee merkkijonotaulukon alkioiden pituuksien keskiarvon
     * @param t Merkkijonotaulukko, jonka alkioiden pituuksien keskiarvoa lasketaan
     * @return Merkkijonotaulukon alkioiden pituuksien keskiarvo
     * @example
     * <pre name="test">
     * keskiarvo(new String[]{"Kuusia", "on", "!"}) ~~~ 3.0 // Perustapaus
     * keskiarvo(new String[]{}) ~~~ 0.0 // Tyhjä taulukko
     * keskiarvo(new String[]{"   ", ""}) ~~~ 1.5 // Tyhjä merkkijono on 0-pituinen, välilyönti lasketaan merkiksi
     * </pre>
     */
     public static double keskiarvo(String[] t) {
         if (t == null || t.length < 1)
             return 0;
         int summa = 0;
         int lkm = 0;
         for (String jono : t) {
             summa += jono.length();
             lkm++;
         }
         return 1.0*summa/lkm;
     }
}

