package demo.d9;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Luokka tiettyä lukua suuremmilla luvuilla alkavien rivien kopioimiseen
 * @author Herman
 * @version 4.7.2020
 *
 */
public class TiedostostaKopiointi {

    private static final int OLETUSLUKU = 30;
    
    /**
     * @param args Tiedosto josta kopioidaan ja tiedosto johon kopioidaan
     * @example
     * <pre name="test">
     * #THROWS IOException
     * #import java.io.IOException;
     * #import fi.jyu.mit.ohj2.VertaaTiedosto;
     * String mista = "hiljaahiipii.txt";
     * VertaaTiedosto.kirjoitaTiedosto(mista,"33 hiljaa 1 hiipii\nhyvä 33 tulee\n25 jep\n36 1 3 5 55\nnyt 33 riittää");
     * String mihin = "hiljaatulos.txt";
     * VertaaTiedosto.tuhoaTiedosto(mihin);
     * main(new String[]{mista, mihin});
     * String tulos = "33 hiljaa 1 hiipii\n36 1 3 5 55";
     * VertaaTiedosto.vertaaFileString(mihin,tulos) === null;
     * VertaaTiedosto.tuhoaTiedosto(mihin);
     * VertaaTiedosto.tuhoaTiedosto(mista);
     * </pre>
     */
    public static void main(String[] args) {
        kopioiRivit(args[0], args[1], OLETUSLUKU);
    }
    
    /**
     * Kopioi kaikki rivit tiedostosta jotka alkavat luvulla joka on suurempi kuin annettu luku toiseen tiedostoon
     * @param mista Tiedosto, josta luetaan rivejä
     * @param mihin Tiedosto, johon kirjoitetaan
     * @param alaraja Mitä lukua suuremmilla luvuilla alkavat rivit kopioidaan
     */
    public static void kopioiRivit(String mista, String mihin, int alaraja) {
        try (Scanner fi = new Scanner(new FileInputStream(new File(mista)))) {
            try (PrintStream fo = new PrintStream(new FileOutputStream(mihin))) {
                while ( fi.hasNext() ) {
                    try {
                        String rivi = fi.nextLine();
                        if (Mjonot.erotaInt(rivi.substring(0, 2), 0) > alaraja)
                            fo.println(rivi);
                    } catch (NumberFormatException ex) {
                        // Hylätään
                    }
                }
            } catch (FileNotFoundException ex) {
                System.err.println("Tiedosto ei aukea: " + ex.getMessage());
                return;
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto ei aukea: " + ex.getMessage());
            return;
        }
    }
    
}

