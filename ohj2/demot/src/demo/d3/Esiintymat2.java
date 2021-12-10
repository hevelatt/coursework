package demo.d3;

/**
 * @author Herman
 * @version 10.6.2020
 *
 */
public class Esiintymat2 {

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /**
     * Poistaa merkkijonosta tietyn merkkijonon kaikki esiintymät iteratiivisesti
     * @param jono Merkkijono, josta poistetaan
     * @param poistettava Merkkijono, jonka esiintymiä poistetaan
     * @return Merkkijonon jono ilman poistettavan esiintymiä
     * @example
     * <pre name="test">
     * poistaEsiintymat("Catcat", "at") === "Cc";
     * poistaEsiintymat("Paatti", "at") === "Pi";
     * poistaEsiintymat("Puatit", "at") === "Puit";
     * poistaEsiintymat("aaattt", "at") === "";
     * poistaEsiintymat("okei", "ok") === "ei";
     * poistaEsiintymat("ok", "okei") === "ok";
     * poistaEsiintymat("okei", "okei") === "";
     * poistaEsiintymat("", "") === "";
     * poistaEsiintymat("okei", "") === "okei";
     * poistaEsiintymat(null, "") === "";
     * poistaEsiintymat(null, "k") === ""; // # THROWS NullPointerException
     * poistaEsiintymat("Kissa", null) === "Kissa";
     * </pre>
     */
    public static String poistaEsiintymat(String jono, String poistettava)
    {
        if (jono == null) return ""; //malli, jos jono ei mitään palautetaan ""
        if (poistettava == null) return jono; //malli, jos poistettavaa ei ole niin palautetaan jono
        if (poistettava.length() <= 0) return jono; //malli, jos poistettavaa ei ole niin palautetaan jono
        
        StringBuilder s = new StringBuilder(jono); //malli, tehdään uusi StringBuilder jonon pohjalta
        int pituus = poistettava.length();
        
        int paikka = 0;
        while (true) {
            paikka = s.indexOf(poistettava); //paikka = etsiAloittavaIndeksi(jono, poistettava); siihen oli valmiina aliohjelma....
            if (paikka < 0) break; //if (paikka == -1) return jono;
            s.delete(paikka, paikka + pituus); //poistaJonosta(jono, paikka, pituus); valmis aliohjelma....
        }
        return s.toString();
    }
}
