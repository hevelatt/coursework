package demo.d7;

/**
 * @author Herman
 * @version 24.6.2020
 *
 */
public class Merkkijonot {

    private final static char JOKERI = '*';
    private final static String ERIKOISMERKIT = " .,!?";
    
    /**
    * @param args ei käytössä
    */
    public static void main(String[] args) {
        String jono = "kissa";
        // jono="kissa" k="aik" -> on, k="aiks" -> ei ole
        String k = "aik";
        System.out.println(onkoMuita(jono, k)); 
        k = "aiks";
        System.out.println(onkoMuita(jono, k)); 

        // "C:\mytemp\ohj2\vesal\Koe.java" viimeinen '\'-merkki
        System.out.println(viimeinen("C:\\mytemp\\ohj2\\vesal\\Koe.java", '\\'));

        // jono="kissa" k="ibm" -> on , k="pc" -> ei ole
        k = "ibm";
        System.out.println(onkoJoku(jono, k));
        k = "pc";
        System.out.println(onkoJoku(jono, k));
        
        // onko " matti* " sama kuin "Matti Nykänen"?
        // System.out.println(onkoSamat2(" matti* ", "Matti Nykänen")); // true
        System.out.println(onkoSamat("Matti Nykänen", " matti* "));
        
        // Paljonko jonossa "Kissa istuu puussa" on yhteensä merkkejä
        // jotka kuuluvat annettuun joukkoon, jossa joukko voi olla vaikka
        //  "a-jr-w"
        System.out.println(laskeMerkit("Kissa istuu puussa", "abcdefghij") + laskeMerkit("Kissa istuu puussa", "rstuvw"));
        System.out.println(laskeMerkit("Kissa istuu puussa", "a-jr-w"));
    
        // abba on palindromi, apua ei ole
        System.out.println(palindromi("abba"));
        System.out.println(palindromi("apua"));
        
        String s="Kissa istuu";
        s = poistaLopusta(s,3); // => s = "Kissa is"
        System.out.println(s);
        StringBuilder sb = new StringBuilder("Kissa istuu");
        poistaLopusta(sb,3);    // => sb= "Kissa is"
        System.out.println(sb);
    }
    
    /**
     * Onko merkkijonossa jono muita kirjaimia kuin joukon k kirjaimet.
     * @param jono Merkkijono, jota tutkitaan
     * @param k Kirjaimet joita etsitään
     * @return True, jos merkkijonossa jono muita kirjaimia kuin k
     * @example
     * <pre name="test">
     * String jono = "kissa", k = "aik";
     * onkoMuita(jono, k) === true;
     * k = "aiks";
     * onkoMuita(jono, k) === false;
     * onkoMuita("", k) === true;
     * onkoMuita(jono, "") === true;
     * </pre>
     */
    public static boolean onkoMuita(String jono, String k) {
        if (k.length() == 0) return true;
        return !(jono.matches("[" + k + "]+"));
    }
    
    /**
     * Missä on jonon viimeinen merkki m.
     * @param jono Merkkijono, jota tutkitaan
     * @param m Merkki, jonka viimeistä esiintymää etsitään
     * @return Merkkijonon indeksin, jossa viimeinen merkki m, -1 jos merkkiä ei esiinny
     * @example
     * <pre name="test">
     * viimeinen("kissa", 's') === 3;
     * viimeinen("kissa", 'b') === -1;
     * </pre>
     */
    public static int viimeinen(String jono, char m) {
        return jono.lastIndexOf(m);
    }
    
    /**
     * Onko jonossa jokin kirjain joukosta k
     * @param jono Merkkijono, jota tutkitaan
     * @param k Kirjaimet joita etsitään
     * @return True, jos merkkijonossa jono on jokin kirjaimista k
     * @example
     * <pre name="test">
     * String jono = "kissa", k = "ibm";
     * onkoJoku(jono, k) === true;
     * k = "pc";
     * onkoJoku(jono, k) === false;
     * onkoJoku("", k) === false;
     * onkoJoku(jono, "") === false;
     * </pre>
     */
    public static boolean onkoJoku(String jono, String k) {
        if (k.length() == 0) return false;
        return jono.matches(".*[" + k + "].*");
    }

    /**
     * Vertaa merkkijonoa a merkkijonoon b jättäen huomiotta ylimääräiset välilyönnit, erot kirjainten koossa 
     * sekä ottaen huomioon jokerimerkinnät
     * @param a Merkkijono, jota verrataan
     * @param b Merkkijono, johon verrataan
     * @return True, jos merkkijonot ovat ehdot huomioon ottaen samat
     * @example
     * <pre name="test">
     * onkoSamat2("   matti   ", "Matti") === true;
     * onkoSamat2(" matti* ", "  MATTI*") === true;
     * onkoSamat2("matti.*", "Matti Nykänen") === true;
     * onkoSamat2("  matti*  ", "Matti Nykänen") === true;
     * onkoSamat2("   ", "") === true;
     * onkoSamat2(" matti   ", "") === false;
     * onkoSamat2("   ", "matti") === false;
     * onkoSamat2(" .*  ", "matti") === true;
     * onkoSamat2(" *  ", "matti") === true;
     * </pre>
     */
    public static boolean onkoSamat2(String a, String b) {
        String at = a.trim().toLowerCase();
        String bt = b.trim().toLowerCase();
        
        if (at.equals(bt)) return true; // Jos päästään trimmauksen jälkeen ilman jokereita
        else if (!at.contains("" + JOKERI)) return false; // Jos ei päästä niin ilman jokereita turha jatkaa
        if (!at.equals("" + JOKERI)) // Jos on muutakin kuin pelkkä jokerimerkki (kääntäjä ei tykkää yksinäisistä trailing reg exp)
            if (bt.matches(at)) return true; // Päästäänkö tässä välissä valmiiden reg expien avulla läpi
        
        String s = at.substring(0, viimeinen(at, JOKERI)) + ".*" 
                + at.substring(viimeinen(at, JOKERI) + 1, at.length()); // Laitetaan oikea-syntaksinen reg exp oikeaan väliin
        return bt.matches(s);
    }

    /**
     * Tutkitaan onko jono sama kuin maski jossa jokerimerkki
     * @param jono Tutkittava jono
     * @param maski Jono, joka sisältää mahdollisesti jokerimerkkejä
     * @return True, jos merkkijonot ovat maskin mielessä samat
     * @example
     * <pre name="test">
     * onkoSamat("Matti", "   matti   ") === true;
     * onkoSamat("MATTI*", " matti* ") === true;
     * onkoSamat("Matti Nykänen", "matti.*") === true;
     * onkoSamat("Matti Nykänen", "  matti*  ") === true;
     * onkoSamat("Matti Nykänen", "matti*nen") === true;
     * onkoSamat("Matti Nykänen", "matti*n*k*e*") === true;
     * onkoSamat("", "   ") === true;
     * onkoSamat("", " matti   ") === false;
     * onkoSamat("matti", "    ") === false;
     * onkoSamat("matti", "  .*  ") === true;
     * onkoSamat("matti", " *   ") === true;
     * onkoSamat("Matti Nykänen.", "  matti*.") === true;
     * onkoSamat("Matti Nykänen", "  matti*.") === false;
     * </pre>
     */
    public static boolean onkoSamat(final String jono, final String maski) {
        String maskit = maski.trim().toLowerCase();
        String jonot = jono.toLowerCase();
        
        //if (jonot.equals(maskit)) return true; // Jos päästään trimmauksen jälkeen ilman jokereita
        //else if (!maskit.contains("" + JOKERI)) return false; // Jos ei päästä niin ilman jokereita turha jatkaa
        if (!maskit.equals("" + JOKERI)) // Jos on muutakin kuin pelkkä jokerimerkki (kääntäjä ei tykkää yksinäisistä trailing reg exp)
            if (jonot.matches(maskit)) return true; // Päästäänkö tässä välissä valmiiden reg expien avulla läpi
        
        maskit = maskit.replaceAll("\\.", "\\\\."); // myös . pitää olla tavallinen kirjain eli \. ja javalle \\.
        maskit = maskit.replaceAll("\\" + JOKERI, "." + JOKERI); // vaihdetaan merkki \*, mikä on kirjain * eikä regex * regexissä
        return jonot.matches(maskit);
    }
    
    /**
     * Paljonko jonossa jono on yhteensä merkkejä välillä vali
     * @param jono Merkkijono, josta etsitään merkkejä
     * @param vali Merkit, joiden esiintymiä lasketaan
     * @return Palauttaa kuinka monta merkkiä merkkijonosta vali esiintyy merkkijonossa jono
     * @example
     * <pre name="test">
     * laskeMerkit("Kissa istuu puussa", "abcdefghij") === 4;
     * laskeMerkit("Kissa istuu puussa", "abcdefghijrstuvw") === 14;
     * laskeMerkit("Kissa istuu puussa", "bcdefgh") === 0;
     * laskeMerkit("", "bcdefgh") === 0;
     * laskeMerkit("Kissa istuu puussa", "") === 0;
     * laskeMerkit("Kissa istuu puussa", "a-jr-w") === 14; // tukee suoraan regex-välejä
     * </pre>
     */
    public static int laskeMerkit(String jono, String vali) {
        if (vali.length() == 0) return 0;
        return jono.length() - jono.replaceAll("[" + vali + "]", "").length();
    }
    
    /**
     * Kertoo onko sana palindromi
     * @param sana Sana, jota testataan
     * @return True, jos sana on palindromi, false jos merkkijono ei ole palindromi tai ei ole sana
     * @example
     * <pre name="test">
     * palindromi("abba") === true;
     * palindromi("apua") === false;
     * palindromi("saippuakivikauppias") === true;
     * palindromi("Kääk") === true;
     * palindromi("Kääk!") === false
     * palindromi("i") === true;
     * palindromi("") === false;
     * </pre>
     */
    public static boolean palindromi(String sana) {
        if (sana.length() == 0 || onkoJoku(sana, ERIKOISMERKIT)) return false;
        String sanapienella = sana.toLowerCase(); // luo vain yhden uuden olion joten parempi?
        // char[] merkit = sana.toLowerCase().toCharArray(); // luo kaksi uutta oliota sana.toLowerCase() ja .toCharArray();
        for (int i = 0, j = sana.length() - 1; i < j; i++, j--)
            if (sanapienella.charAt(i) != sanapienella.charAt(j)) return false;
        return true;
    }
    
    /**
     * Poistaa merkkijonon lopusta annetun määrän merkkejä
     * @param s Merkkijono, josta poistetaan
     * @param maara Kuinka monta merkkiä poistetaan
     * @return Merkkijonon, josta on poistettu lopusta luvun maara verran merkkejä
     * @example
     * <pre name="test">
     * poistaLopusta("Kissa istuu", 3) === "Kissa is";
     * poistaLopusta("abc", 3) === "";
     * poistaLopusta("a", 3) === "";
     * poistaLopusta("", 3) === "";
     * </pre>
     */
    public static String poistaLopusta(String s, int maara) {
        if (s.length() <= maara) return "";
        return s.substring(0, s.length() - maara);
    }
    
    /**
     * Poistaa merkkijonon lopusta annetun määrän merkkejä
     * @param sb Merkkijono, josta poistetaan
     * @param maara Kuinka monta merkkiä poistetaan
     * @example
     * <pre name="test">
     * StringBuilder sb = new StringBuilder("Kissa istuu");
     * poistaLopusta(sb,3); sb.toString() === "Kissa is";
     * sb = new StringBuilder("abc"); poistaLopusta(sb, 3); sb.toString() === "";
     * sb = new StringBuilder("a"); poistaLopusta(sb, 3); sb.toString() === "";
     * sb = new StringBuilder(""); poistaLopusta(sb, 3);sb.toString() === "";
     * </pre>
     */
    public static void poistaLopusta(StringBuilder sb, int maara) {
        if (sb.length() <= maara) {
            sb.replace(0, sb.length() + 1, "");
            return;
        }
        sb.replace(sb.length() - maara, sb.length() + 1, "");
    }
}