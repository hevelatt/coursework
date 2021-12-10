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
public class TiedostonNumerointi {

    /**
     * Tulostaa tiedoston sisällön siten, että jokaisen rivin edessä on rivinumero ja 
     * rivin pituus on korkeintaan 40 merkkiä
     * @param args tiedoston nimi
     * @example
     * <pre name="test">
     * #THROWS IOException
     * #import java.io.IOException;
     * #import fi.jyu.mit.ohj2.Suuntaaja;
     * #import fi.jyu.mit.ohj2.VertaaTiedosto;
     * String tiednimi = "testikoe.txt";
     * VertaaTiedosto.kirjoitaTiedosto(tiednimi,
     *                  "123456789012345678901234567890123456789012345678901234567890\n" +
     *                  "Kissa istuu puussa\n" +
     *                  "ja ihmettelee\n" +
     *                  "mualiman menoa");
     * Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     * main(new String[]{tiednimi});
     * so.palauta();
     * String tulos = "/* 01 "+ "*" + "/" + " 1234567890123456789012345678901234567890\n" +
     *                  "/* 02 "+ "*" + "/" + " Kissa istuu puussa\n" +
     *                  "/* 03 "+ "*" + "/" + " ja ihmettelee\n" +
     *                  "/* 04 "+ "*" + "/" + " mualiman menoa\n";  
     * so.ero(tulos) === null;
     * VertaaTiedosto.tuhoaTiedosto(tiednimi);
     * Suuntaaja.StringOutput se = new Suuntaaja.StringOutput(true);
     * main(new String[]{"xxx.xxx"});
     * se.palauta();
     * se.toString() =R= "(?s)"+"Tiedosto ei aukea! xxx\\.xxx .*";
     * </pre>
     */
    public static void main(String[] args) {
        int rivinro = 0;
        String rivi = "";
        
        String tiedNimi = "koe.txt";
        if (args.length > 0)
            tiedNimi = args[0];
        
        try (Scanner fi = new Scanner(new FileInputStream(new File(tiedNimi)))) {
            while (fi.hasNext()) {
                rivinro++;
                rivi = fi.nextLine();
                if ("".equals(rivi))
                    continue;
                    
                System.out.print("/* " + String.format("%02d", rivinro) + " */ ");
                
                if (rivi.length() >= 40)
                    System.out.println(rivi.substring(0, 40));
                else
                    System.out.println(rivi);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Tiedosto ei aukea! "+ e.getMessage());
        }
    }

}
