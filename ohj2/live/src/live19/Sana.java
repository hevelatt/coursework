/**
 * 
 */
package live19;

/**
 * @author Herman
 * @version 3.7.2020
 *
 */
public class Sana {

    private int lkm;
    private String teksti;

    /**
     * Alustetaan teksti jonolla lkm = 0;
     * @param s jono jolla alustetaan
     * @example
     * <pre name="test">
     * Sana sana = new Sana("kissa");
     * sana.toString() === "kissa: 0";
     * </pre>
     */
    public Sana(String s) {
        lkm = 1;
        teksti = s;
    }

    /**
     * Lisätään laskuria yhdellä
     * @example
     * <pre name="test">
     * Sana sana = new Sana("kissa");
     * sana.toString() === "kissa: 0";
     * sana.lisaa();
     * sana.toString() === "kissa: 1";
     * sana.lisaa();
     * sana.toString() === "kissa: 2";
     * </pre>
     */
    public void lisaa() {
        lkm++;
    }

    /**
     * @return sana ja määrä merkkijonona
     * @example
     * <pre name="test">
     * Sana sana = new Sana("kissa");
     * sana.toString() === "kissa: 0";
     * </pre>
     */
    @Override
    public String toString() {
        return teksti + ": " + lkm;
    }

    /**
     * Verrataan onko tutkittava jono
     * @param s tutkittava jono
     * @return true jos sama kuin s
     * <pre name="test">
     *   Sana sana = new Sana("kissa");
     *   sana.oletko("kissa") === true;
     *   sana.oletko("kISSA") === true;
     *   sana.oletko("kiss")  === false;
     * </pre>
     */
    public boolean oletko(String s) {
        return teksti.equalsIgnoreCase(s);
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Sana sana = new Sana("kissa");
        if (sana.oletko("kana")) sana.lisaa();
        System.out.println(sana.toString());
    }

}
