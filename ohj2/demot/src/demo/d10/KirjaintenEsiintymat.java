package demo.d10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Herman
 * @version 11.7.2020
 *
 */
public class KirjaintenEsiintymat {

    /**
     * Laskee kunkin kirjaimen esiintymät tiedostosta ja tulostaa ne
     * @param args Tutkittava tiedosto
     * @example
     * <pre name="test">
     * #THROWS IOException
     * #import java.io.IOException;
     * #import fi.jyu.mit.ohj2.Suuntaaja;
     * #import fi.jyu.mit.ohj2.VertaaTiedosto;
     * String tiednimi = "testiesiintymat.txt";
     * VertaaTiedosto.kirjoitaTiedosto(tiednimi, "AaAkkostesti3\näöååå");
     * Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     * main(new String[]{tiednimi});
     * so.palauta();
     * String tulos = "a: 3\ne: 1\ni: 1\nk: 2\no: 1\ns: 2\nt: 2\nä: 1\nå: 3\nö: 1\n";  
     * so.ero(tulos) === null;
     * VertaaTiedosto.tuhoaTiedosto(tiednimi);
     * Suuntaaja.StringOutput se = new Suuntaaja.StringOutput(true);
     * main(new String[]{"xxx.xxx"});
     * se.palauta();
     * se.toString() =R= "(?s)"+"Tiedosto ei aukea! xxx\\.xxx .*";
     * </pre>
     */
    public static void main(String[] args) {
        int[] kirjaimet = new int[254]; // ASCII
        String rivi = "";
        String tiedNimi = "esiintymat.txt";
        if (args.length > 0)
            tiedNimi = args[0];
        
        try (Scanner fi = new Scanner(new FileInputStream(new File(tiedNimi)))) {
            while (fi.hasNext()) {
                rivi = fi.nextLine().toLowerCase();
                for (int i = 0; i < rivi.length(); i++)
                    kirjaimet[rivi.charAt(i)] += 1;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Tiedosto ei aukea! " + e.getMessage());
        }
        
        for (int i = 97; i < kirjaimet.length; i++) // 97 = a
            if (kirjaimet[i] > 0)
                System.out.println((char)i + ": " + kirjaimet[i]);
    }

}
