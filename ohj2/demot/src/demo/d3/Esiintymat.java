package demo.d3;


/**
 * @author Herman
 * @version 6.6.2020
 *
 */
public class Esiintymat
{
    // BYCODEBEGIN
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args)
    {
        //
    }

    /**
     * Poistaa merkkijonosta jono kaikki poistettavan merkkijonon esiintymät
     * @param jono Merkkijono, josta poistetaan esiintymiä
     * @param poistettava Jonosta poistettavat merkkijonot
     * @return Jono, josta on poistettu poistettavat
     */
    public static String poistaEsiintymat(String jono, String poistettava) {
        int pituusp = poistettava.length();
        if (pituusp == 0) return jono;
        char ekap = poistettava.charAt(0);
        //String uusijono = jono;
        while(true){
            //jono = uusijono;
            for (int i = 0; i < jono.length(); i++) {
                if (jono.charAt(i) == ekap) {
                    if (jono.length() - i - 1 < pituusp) return jono;
                    //if (vertaaJonoja(osaJono(jono, i, i+pituusp), poistettava)) uusijono = osaJono(jono, i, i+pituusp);
                }
            }
            return "XD";
        }
    }
    /**
     * Vertaa kahta merkkijonoa toisiinsa
     * @param jono1 Ensimmäinen verrattava
     * @param jono2 Toinen verrattava
     * @return Ovatko samat?
     */
    public static boolean vertaaJonoja(String jono1, String jono2) {
        for (int i = 0; i < jono1.length(); i++) {
            if (jono1.charAt(i) != jono2.charAt(i)) return false;
        }
        return true;
    }
    /**
     * Tekee osajonon
     * @param jono Jono josta tehdään osajono
     * @param mista Mistä indeksistä lähtien
     * @param mihin Mihin indeksiin saakka
     * @return Osajono
     */
    public static String osaJono(String jono, int mista, int mihin) {
        StringBuilder s = new StringBuilder(jono);
        return s.substring(mista, mihin).toString();
    }

    // BYCODEEND
}