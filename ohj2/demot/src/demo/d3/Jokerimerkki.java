package demo.d3;

/**
 * @author Herman
 * @version 1.0 6.6.2020
 *
 */
public class Jokerimerkki
{
    // BYCODEBEGIN
    /** Jokerimerkki */
    public static final char JOKERI = '?';
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args)
    {
        if ( onkoSamatKysymysmerkilla("Kissa","K?ss?") )
            System.out.println("On samat");
        if ( !onkoSamatKysymysmerkilla("Kiss","K?ss?") )
            System.out.println("Ei samat");
        if ( !onkoSamatKysymysmerkilla("Kissa","Kassa") )
            System.out.println("Ei samat");
        if ( !onkoSamatKysymysmerkilla("Kissa?","Kissa!") )
            System.out.println("Ei samat");
        if ( onkoSamatKysymysmerkilla("","") )
            System.out.println("On samat");
        if ( onkoSamatKysymysmerkilla("Kissa","K????") )
            System.out.println("On samat");
    }

    /**
     * Tarkistaa, onko merkkijono sama kuin kysymysmerkkejä sisältävä merkkijono (? = mikä merkki vaan)
     * @param jono Merkkijono johon verrataan
     * @param maski Kysymysmerkkejä jokerina sisältävä maskijono jota verrataan jonoon
     * @return Ovatko samat?
     */
    public static boolean onkoSamatKysymysmerkilla(String jono, String maski)
    {
        if (jono.length() != maski.length()) return false;
        for (int i = 0; i < jono.length(); i++) {
            if (jono.charAt(i) != maski.charAt(i) && maski.charAt(i) != JOKERI) return false;
        }
        return true;
    }
    // BYCODEEND
}


