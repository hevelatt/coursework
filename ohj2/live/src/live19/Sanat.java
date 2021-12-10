/**
 * 
 */
package live19;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * @author Herman
 * @version 3.7.2020
 *
 */
public class Sanat {

    private Collection<Sana> alkiot = new ArrayList<Sana>();
    
    /**
     * Lisätään uusi sana rakenteeseen. Jos on jo, lisätään vanhan määrää,
     * muuten luodaan uusi sana
     * @param s lisättävä sana
     * @example
     * <pre name="test">
     * #import java.io.ByteArrayOutputStream;
     * #import static fi.jyu.mit.ohj2.VertaaTiedosto.*;
     *  ByteArrayOutputStream bs = new ByteArrayOutputStream();
     *  Sanat sanat = new Sanat();
     *  sanat.lisaa("kissa"); sanat.tulosta(bs);
     *  vertaaString(bs, "kissa: 1\n") === null;
     *  sanat.lisaa("kissa"); sanat.tulosta(bs);
     *  vertaaString(bs, "kissa: 2\n") === null;
     *  sanat.lisaa("kana"); sanat.tulosta(bs);
     *  vertaaString(bs, "kissa: 2\nkana: 1\n") === null;
     * </pre>
     */
    public void lisaa(String s) {
        for (Sana sana: alkiot) {
            if (sana.oletko(s)) {
                sana.lisaa();
                return;
            }
        }
        Sana sana = new Sana(s);
        // sana.lisaa();
        alkiot.add(sana);
    }
    
    /**
     * Tulostetaan koko rakenne tietovirtaan
     * @param os tietovirta johon tulostetaan
     * @example
     * <pre name="test">
     *  ByteArrayOutputStream bs = new ByteArrayOutputStream();
     *  Sanat sanat = new Sanat();
     *  sanat.tulosta(bs);
     *  bs.toString() === "";
     *  sanat.lisaa("kissa"); sanat.tulosta(bs);
     *  vertaaString(bs, "kissa: 1\n") === null;
     * </pre>
     */
    public void tulosta(OutputStream os) {
        PrintStream out = new PrintStream(os);
        
        for (Sana sana: alkiot)
            out.println(sana);
    }
    
    /**
     * Pilkotaan rivi sanoiksi ja lisataan ne kaikki rakenteeseen
     * @param rivi kasiteltava rivi
     * @example
     * <pre name="test">
     *  ByteArrayOutputStream bs  = new ByteArrayOutputStream();
     *  Sanat sanat = new Sanat();
     *  sanat.kasitteleRivi("kissa, kana kissa"); sanat.tulosta(bs);
     *  vertaaString(bs,"kissa: 2\nkana: 1\n") === null;
     *  sanat.kasitteleRivi("kissa istuu kana"); sanat.tulosta(bs);
     *  vertaaString(bs,"kissa: 3\nkana: 2\nistuu: 1\n") === null;
     * </pre>
     */
    public void kasitteleRivi(String rivi) {
        String[] palat = rivi.split("[ ,();.:{}\\\"!?+\\\\-\\\\[\\\\]]+");
        for (String s: palat)
            if(!s.isEmpty())
                lisaa(s);
    }
    
    /**
     * Luetaan tiedoston kaikki rivit ja kasitellaan laskemalla kaikki sanat
     * @param tiedostonNimi tiedoston nimi, jota luetaan
     * @throws IOException jos tiedostoa ei voi kasitella
     * <pre name="test">
     * #THROWS IOException
     * #import java.io.*;
     *  kirjoitaTiedosto("sanatLueKoe.txt","kissa kana kissa\nkissa istuu kana");
     *  ByteArrayOutputStream bs  = new ByteArrayOutputStream();
     *  Sanat sanat = new Sanat();
     *  sanat.lueTiedostoJaLaske("sanatLueKoe.txt"); sanat.tulosta(bs);
     *  vertaaString(bs,"kissa: 3\nkana: 2\nistuu: 1\n") === null;
     *  tuhoaTiedosto("sanatLueKoe.txt");
     * </pre>
     */
    public void lueTiedostoJaLaske(String tiedostonNimi) throws IOException {
        try (Scanner fi = new Scanner(new FileInputStream(new File(tiedostonNimi)))) {
            while ( fi.hasNext() ) {
                String rivi = fi.nextLine();
                kasitteleRivi(rivi);
            }
        }
    }


    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Sanat sanat = new Sanat();
        sanat.lisaa("kissa");
        sanat.lisaa("kissa");
        sanat.lisaa("koira");
        sanat.tulosta(System.out);
        sanat.kasitteleRivi("kissa, istuu kissa puussa");
        sanat.tulosta(System.out);
    }
    
}
