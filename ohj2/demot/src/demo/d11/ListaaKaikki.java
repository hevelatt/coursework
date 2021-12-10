package demo.d11;

import java.io.File;
import java.io.IOException;

/**
 * Ohjelmalla käydään koko hakemistopuu lävitse.
 * Tehtävä: muuta ohjelmaa niin, että se todella käy
 * koko hakemistopuun lävitse.  Vinkki: rekursio.
 * @author Vesa Lappalainen
 * @version 1.0, 21.02.2003
 */
public class ListaaKaikki {

    /**
     * Käy läpi kaikki hakemiston tiedostot tiedostot
     * @param nyky hakemisto josta läpikäynti aloitetaan
     * @param maski millä ehdolla tiedoston nimi tulostetaan
     */
    public static void listaaHakemisto(File nyky, String maski) {
        File tama_hakemisto[] = nyky.listFiles();

        for (int i=0; i<tama_hakemisto.length; i++ ) {
            File tiedosto = tama_hakemisto[i];
            if ( tiedosto.getName().matches(maski) )
                try {
                    System.out.println(tiedosto.getCanonicalPath());
                } catch ( IOException e ) { /**/ }
            if (tiedosto.listFiles() != null)
                listaaHakemisto(tiedosto, maski);
        }
    }


    /**
     * Tulostetaan kaikki tietyn hakemiston tiedostot
     * @param args hakemisto maski, esim:  . .*  tai  c:\mytemp  .*java
     * @throws IOException jos menee pieleen
     */
    public static void main(String[] args) throws IOException {
        String aloitus = ".";
        String maski = ".*";
        if ( args.length > 0 ) aloitus = args[0];
        if ( args.length > 1 ) maski = args[1];
        File nyky = new File(aloitus);
        listaaHakemisto(nyky,maski);
    }
}