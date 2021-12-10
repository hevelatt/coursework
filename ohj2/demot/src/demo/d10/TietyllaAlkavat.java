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
public class TietyllaAlkavat {

    /**
     * Tulostaa tietyllä merkkijonolla alkavat rivit
     * @param args tiedoston nimi
     * @example
     * <pre name="test">
     * #THROWS IOException
     * #import java.io.IOException;
     * #import fi.jyu.mit.ohj2.Suuntaaja;
     * #import fi.jyu.mit.ohj2.VertaaTiedosto;
     * String tiednimi = "testirivit.txt";
     * VertaaTiedosto.kirjoitaTiedosto(tiednimi,
     *      "* hei\n"       +
     *      "** moi\n"      +
     *      "*** terve\n\n" +
     *      "asd\n"         +
     *      "**");
     * Suuntaaja.StringInput si = new Suuntaaja.StringInput("");  
     * Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     * si.input("**\n");
     * main(new String[]{tiednimi});
     * si.palauta(); so.palauta();
     * String tulos = 
     *      "Anna merkkijono, jolla alkavat rivit tulostetaan >** moi\n" +
     *      "*** terve\n" +
     *      "**\n";
     * so.ero(tulos) === null;
     * VertaaTiedosto.tuhoaTiedosto(tiednimi);
     * Suuntaaja.StringOutput se = new Suuntaaja.StringOutput(true);
     * si.input("**\n");
     * main(new String[]{"xxx.xxx"});
     * si.palauta(); se.palauta();
     * se.toString() =R= "(?s)"+"Tiedosto ei aukea! xxx\\.xxx .*";
     * </pre>
     */
    public static void main(String[] args) {
        String tiedNimi = "rivit.txt";
        if (args.length > 0)
            tiedNimi = args[0];
             
        try (Scanner fi = new Scanner(new FileInputStream(new File(tiedNimi)))) {
            System.out.print("Anna merkkijono, jolla alkavat rivit tulostetaan >"); 
            try (Scanner in = new Scanner(System.in)) {
                String aloitus = in.nextLine();
                String rivi = "";
                while (fi.hasNext()) {
                    rivi = fi.nextLine();
                    if (rivi.length() < aloitus.length())
                        continue;
                    if (rivi.substring(0, aloitus.length()).equals(aloitus))
                        System.out.println(rivi);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Tiedosto ei aukea! " + e.getMessage());
        }
    }

}
