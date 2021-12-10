package demo.d12;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fi.jyu.mit.ohj2.Help;
import fi.jyu.mit.ohj2.Mjonot;
import fi.jyu.mit.ohj2.Syotto;

/**
 * @author Herman
 * @version 26.7.2020
 *
 */
public class Komentorivi {
    
    /**
     *  Funktionaalinen rajapinta yhden komennon suorittamiseen
     */
    public interface KomentoRajapinta {
        /**
         * Suorittaa yhden komennon
         * @param parametrit Komennon parametrit
         * @return Suorituksen tulos
         */
        String suorita(String parametrit);
    }
    
    /**
     * Luokka avustuksia varten
     */
    public static class Apua implements KomentoRajapinta {

        private Help help;
        
        /**
         * Avun muodostaja
         * @param tiedosto Mistä tiedostosta avustukset luetaan
         */
        public Apua(String tiedosto) {
            help = new Help();
            try {
                help.readFile(tiedosto);
            } catch (IOException e) {
                System.err.println("Avustustiedosto ei aukea");
            }
        }
        
        /**
         * Tulostaa pyydetyn avustuksen
         */
        @Override
        public String suorita(String parametrit) {
            if ("".equals(parametrit.trim())) {
                help.printTopic("YLEISTÄ");
                return "";
            }
            help.printMatchingTopics(parametrit);
            return "";
        }
        
    }
    
    /**
     * Laskee yhteen välilyönnillä erotetut kokonaisluvut
     * @example
     * <pre name="test">
     * #STATICIMPORT
     * Ynnaa ynnaa = new Ynnaa();
     * ynnaa.suorita("4 5 6") === "Tulos on 15.";
     * ynnaa.suorita("abcd") === "Tulos on 0.";
     * ynnaa.suorita("a5c6") === "Tulos on 11.";
     * ynnaa.suorita("5.6") =R= "Tulos on 5[.,]6.";
     * ynnaa.suorita("") === "Tulos on 0.";
     * </pre>
     */
    public static class Ynnaa implements KomentoRajapinta {
        @Override
        public String suorita(String parametrit) {
            StringBuilder sb = new StringBuilder(parametrit.replaceAll("[^0-9. ]+", " "));
            ArrayList<Double> luvut = new ArrayList<>();
            while (sb.length() > 0)
                luvut.add(Mjonot.erotaDouble(sb, 0));
            double summa = luvut.stream().mapToDouble(Double::doubleValue).sum();
            String format = summa % 1 == 0 ? "%.0f" : "%.1f";
            return "Tulos on " + String.format(format, summa) + ".";
        }
    }
    
    /**
     * Palauttaa annetun merkkijonon isoilla kirjaimilla kirjoitettuna
     * @example
     * <pre name="test">
     * Isoksi isoksi = new Isoksi();
     * isoksi.suorita("kissa") === "kissa isona on KISSA";
     * isoksi.suorita("") === " isona on ";
     * isoksi.suorita("abcd5FGH") === "abcd5FGH isona on ABCD5FGH";
     * </pre>
     */
    public static class Isoksi implements KomentoRajapinta {
        @Override
        public String suorita(String parametrit) {
            return parametrit + " isona on " + parametrit.toUpperCase();
        }
    }
    
    /**
     * Kertoo onko annettu merkkijono palindromi
     * @example
     * <pre name="test">
     * Palindromiko pali = new Palindromiko();
     * pali.suorita("innostunut sonni") === "Sana \"innostunut sonni\" on palindromi!";
     * pali.suorita("kissa") === "Sana \"kissa\" ei ole palindromi!";
     * pali.suorita("'Innostunut' sonni?") === "Sana \"'Innostunut' sonni?\" on palindromi!";
     * pali.suorita("") === "Sana \"\" on palindromi!";
     * </pre>
     */
    public static class Palindromiko implements KomentoRajapinta {
        @Override
        public String suorita(String parametrit) {
            String s = parametrit.replaceAll("[ ,.?!\"']+", "").toUpperCase();
            for (int i = 0, j = s.length() - 1; i < j; i++, j--)
                if (s.charAt(i) != s.charAt(j))
                    return "Sana \"" + parametrit + "\" ei ole palindromi!";
            return "Sana \"" + parametrit + "\" on palindromi!";
        }
    }
    
    /**
     * Komennon nimi ja vastaava vastaava funktio
     */
    public static class Komento {
        private String nimi;
        private KomentoRajapinta komento;
        
        /**
         * Muodostaja
         * @param nimi Komennon nimi
         * @param komento Suoritettava komento
         */
        public Komento(String nimi, KomentoRajapinta komento) {
            this.nimi = nimi;
            this.komento = komento;
        }
        
        /**
         * Vertaa nimeään annettuun merkkijonoon
         * @param s Merkkijono, johon nimeä verrataan
         * @return true jos vastaa nimeä
         * @example
         * <pre name="test">
         * Komento komento = new Komento("komento", s -> s);
         * komento.onko("komento") === true;
         * komento.onko("Kom") === true;
         * komento.onko("K") === true;
         * komento.onko("Kon") === false;
         * komento.onko("") === false;
         * </pre>
         */
        public boolean onko(String s) {
            if ("".equals(s)) return false;
            if (nimi.toUpperCase().startsWith(s.toUpperCase()))
                return true;
            return false;
        }
        
        /**
         * Palauttaa funktion tuloksen annetulla syötteellä
         * @param syote Syöte, joka annetaan funktiolle
         * @return Funktion tulos
         * @example
         * <pre name="test">
         * Komento komento = new Komento("komento", s -> s.toLowerCase());
         * komento.tulos("KALA") === "kala";
         * </pre>
         */
        public String tulos(String syote) {
            return komento.suorita(syote);
        }
    }
    
    /**
     * Lista komennoista ja metodit etsimiseksi ja suorittamiseksi.
     */
    public static class Komennot {
        private static List<Komento> komennot = new ArrayList<Komento>();
        
        /**
         * Lisää komennon komentoihin
         * @param komento Lisättävä komento
         */
        public void add(Komento komento) {
            komennot.add(komento);
        }

        /**
         * Tulkitsee merkkijonosyötteen siten että ensimmäinen osa ennen välilyöntiä on komento
         * ja toinen osa komennon parametrit
         * @param s Tulkittava merkkijono
         * @return Palauttaa komennon tuloksen tai valituksen
         * @example
         * <pre name="test">
         * Komennot komennot = new Komennot();
         * komennot.add(new Komento("pieneksi", s -> s.toLowerCase()));
         * komennot.tulkitse("pieneksi KALA") === "kala";
         * komennot.tulkitse("isoksi kala") === "En tunne komentoa ISOKSI!"
         * </pre>
         */
        public String tulkitse(String s) {
            StringBuilder sb = new StringBuilder(s);
            String nimi = Mjonot.erota(sb);
            for (Komento komento: komennot)
                if (komento.onko(nimi))
                    return komento.tulos(sb.toString());
            return "En tunne komentoa " + nimi.toUpperCase() + "!";
        }
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Komennot komennot = new Komennot();
        Apua apua = new Apua("komento.txt");
        komennot.add(new Komento("?", apua));
        komennot.add(new Komento("apua", apua));
        komennot.add(new Komento("+", new Ynnaa()));
        komennot.add(new Komento("ynnää", new Ynnaa()));
        komennot.add(new Komento("isoksi", new Isoksi()));
        komennot.add(new Komento("palindromiko", new Palindromiko()));
        
        String s;
        
        while (true) {
            s = Syotto.kysy("Anna komento");
            if ("".equals(s))
                break;
            String tulos = komennot.tulkitse(s);
            System.out.println(tulos);
        }
    }

}
