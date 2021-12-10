package demo.d12;

import java.io.*;
import fi.jyu.mit.ohj2.*;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

/**
 * Ohjelmalla vaihdetaan valuuttoja
 * Versioon 1.1 lisätty Valuutat -luokan rajapintaa
 * uusia metodeja, mm. getVaihdettuMaara, getValuutat
 * @author Vesa Lappalainen
 * @version 1.0, 15.03.2003
 * @version 1.1, 09.04.2003
 */
public class ValuuttaMuunnos {
    // #DYNAMICIMPORT    

    /**
     * Muunnetaan valuuttaa
     * @param args ei käytössä
     * @throws IOException jos jokin menee pieleen tiedoston luvussa
     */
    public static void main(String[] args) throws IOException {
        Valuutat valuutat = new Valuutat();
        /*
            valuutat.lisaa(1.0,"mk");
            valuutat.lisaa(5.7,"$");
            valuutat.lisaa(5.9,"EUROA");
            valuutat.lisaa(0.6,"SKr");
        */
        if (!valuutat.lue())
            return;

        ValuuttaNaytto naytto = new ValuuttaNaytto(valuutat);

        while (naytto.kysy()) {
            naytto.tulosta();
        }

    }

    /**
     * Luokka valuuttojen tallentamiselle
     * @author Vesa Lappalainen
     * @version 1.0, 15.03.2003
     */
    public static class Valuutat {
        //private int lkm = 0;
        //private Valuutta alkiot[] = new Valuutta[20];
        //private double maara;
        //private String valuutannimi;
        Map<String, Double> alkiot = new TreeMap<>();

        /**
         * Lisätään tietorakenteeseen uusi valuutta.
         * @param kerroin lisättävän valuutan kerroin
         * @param valuutta listtävän valuutan yksikkö
         * @example
         * <pre name="test">
         * Valuutat valuutat = new Valuutat();
         * valuutat.lisaa(1,"e");
         * valuutat.lisaa(10,"SKr");
         * valuutat.lisaa(1.6,"$");
         * valuutat.getKerroin("$") ~~~ 1.6;
         * </pre>
         */
        public void lisaa(double kerroin, String valuutta) {
            if (alkiot.get(valuutta) != null) return;
            alkiot.put(valuutta, kerroin);
        }
        
        /**
         * Etsitään hakujonoa vastaavan valuutan kerroin.
         * @param valuutannimi etsittävän valuutan yksikkö
         * @return valuutan kerroin tai 1.0 jo ei löydy.
         * @example
         * <pre name="test">
         * Valuutat valuutat = new Valuutat();
         * valuutat.lisaa(1,"e");
         * valuutat.lisaa(10,"SKr");
         * valuutat.lisaa(1.6,"$");
         * valuutat.getKerroin("$") ~~~ 1.6;
         * </pre>
         */
        public double getKerroin(String valuutannimi) {
            if (!alkiot.containsKey(valuutannimi)) return 1.0;
            return alkiot.get(valuutannimi);
        }

        /**
         * Etsitään hakujonoa vastaavan valuutan yksikkö
         * @param valuutannimi etsittävän valuutan yksikkö
         * @return valuutan nimi tai "" jos ei löydy
         * @example
         * <pre name="test">
         * Valuutat valuutat = new Valuutat();
         * valuutat.lisaa(1,"e");
         * valuutat.lisaa(10,"SKr");
         * valuutat.lisaa(1.6,"$");
         * valuutat.getValuutannimi("e") === "e";
         * valuutat.getValuutannimi("$") === "$";
         * valuutat.getValuutannimi("s") === "";
         * </pre>
         */
        public String getValuutannimi(String valuutannimi) {
            String[] nimet = getValuuttojenNimet();
            for (int i = 0; i < nimet.length; i++) {
                if (nimet[i].equals(valuutannimi))
                    return valuutannimi;
            }
            return "";
        }
        
        /**
         * Luetaan valutuutat tietovirrasta.  
         * @param fi tietovirta  josta luetaan
         * @return true jos lukeminen onnistui
         * @throws IOException jos jokin menee pieleen tiedoston käsittelyssä. 
         */
        public boolean lue(BufferedReader fi) throws IOException {
            if (fi == null)
                return false;

            String jono;
            while ((jono = fi.readLine()) != null) {
                if ("".equals(jono))
                    continue;
                StringBuilder sb = new StringBuilder(jono);
                lisaa(Mjonot.erotaDouble(sb, 0), Mjonot.erota(sb, '|', ""));
            }

            return true;
        }

        /**
         * Luetaan valuutat tiedostosta tai www:stä.
         * Jos tiedoston nimi alkaa http-luetaan www:stä
         * @param nimi luettavan valuuttatiedoston nimi
         * @return true jos lukeminen onnistuu.
         * @throws IOException jos jokin menee pieleen tiedoston käsittelyssä.
         * @example
         * </pre>
         */
        @SuppressWarnings("resource") // suljetaan finallyssä
        public boolean lue(String nimi) throws IOException {
            BufferedReader fi = null;
            try {
                if (nimi.startsWith("http")) {
                    URL sivu = new URL(nimi);
                    InputStream in = sivu.openStream();
                    Reader reader = new InputStreamReader(in);
                    fi = new BufferedReader(reader);
                    return lue(fi);
                }
                fi = Tiedosto.avaa_lukemista_varten(nimi);
                return lue(fi);
            } finally {
                if (fi != null)
                    fi.close();
            }
        }


        /**
         * Luetaan valuutat.dat nimisestä tiedostosta valuutat.
         * @return true jos lukeminen onnistui false jos tiedosto ei aukea.
         * @throws IOException  jos jokin menee pieleen tiedoston käsittelyssä.
         */
        public boolean lue() throws IOException {
            return lue("valuutat.dat");
        }


        /**
         * Palautetaan haettavaa valuuttaa vastaava summa kantavaluutassa.
         * @param nimi  haettavan valuutan hakujono
         * @param maara rahamäärä joka muutetaan kantavaluuttaan.
         * @return rahamäärä muutettuna kantavaluuttaan.
         * Valuutat valuutat = new Valuutat();
         * valuutat.lisaa(1,"e");
         * valuutat.lisaa(1.6,"$");
         * valuutat.lisaa(10,"SKr");
         * getVaihdettuMaara("SKr", 5) ~~~ 50;
         * getVaihdettuMaara("$", 5) ~~~ 8;
         */
        public double getVaihdettuMaara(String nimi, double maara) {
            return getKerroin(nimi) * maara;
        }

        /**
         * Palautetaan merkkijonotaulukkona kaikkien valuuttojen nimet
         * @return valuuttojen nimet merkkijonotaulukossa.
         * @example
         * <pre name="test">
         * Valuutat valuutat = new Valuutat();
         * valuutat.lisaa(1,"e");
         * valuutat.lisaa(1.6,"$");
         * valuutat.lisaa(10,"SKr");
         * String nimet[] = valuutat.getValuuttojenNimet();
         * nimet.length === 3;
         * nimet[0] === "$";
         * nimet[1] === "SKr";
         * nimet[2] === "e";
         * </pre>
         */
        public String[] getValuuttojenNimet() {
            return alkiot.keySet().toArray(new String[alkiot.keySet().size()]);
        }

    }

    /**
     * Näyttöluokka valuuttojen käyttämiseksi konsolisovelluksesta.
     * @author Vesa Lappalainen
     * @version 1.0, 15.03.2003
     * 
     * @example
     * <pre name="test">
     * @example
     * <pre name="test">
     * #import fi.jyu.mit.ohj2.Suuntaaja;
     *     
     * Valuutat valuutat = new Valuutat();
     * valuutat.lisaa(1,"e");
     * valuutat.lisaa(10,"SKr");
     * valuutat.lisaa(1.6,"$");
     * ValuuttaNaytto naytto = new ValuuttaNaytto(valuutat);
     * 
     * Suuntaaja.StringInput si = new Suuntaaja.StringInput("");  
     * Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     *
     * si.input("");       naytto.kysy() === false; 
     * si.input("loppu");  naytto.kysy() === false;
     * so.reset(); 
     * si.input("3 SKr");    naytto.kysy() === true;
     * so.reset(); naytto.tulosta(); so.ero("3,00 SKr on 30,00 e\n") === null; 
     * si.input("3 $");    naytto.kysy() === true;
     * so.reset(); naytto.tulosta(); so.ero("3,00 $ on 4,80 e\n") === null; 
     * si.input("2 $");      naytto.kysy() === true;
     * so.reset(); naytto.tulosta(); so.ero("2,00 $ on 3,20 e\n") === null; 
     * si.input("2 k lati"); naytto.kysy() === true; // Yksikköä ei löydy
     * so.reset(); naytto.tulosta(); so.ero("2,00  on 2,00 e\n") === null; 
     * si.input("");       naytto.kysy() === false; 
     * 
     * si.palauta(); so.palauta();
     * 
     * </pre>
     * </pre>
     */
    public static class ValuuttaNaytto {
        private final Valuutat valuutat;
        private String valuutta;
        private double maara;

        /**
         * Alustetaan näyttö käyttämään valuuttataulukkoa.
         * @param valuutat valuuttataulukko jota käytetään.
         */
        public ValuuttaNaytto(Valuutat valuutat) {
            this.valuutat = valuutat;
        }


        /**
         * Kysytään valuutan tietoja.
         * @return true jos kysely onnistui.
         */
        public boolean kysy() {
            String jono = Syotto.kysy("Määrä ja valuutta");

            if ("".equals(jono))
                return false;
            if ("loppu".equals(jono))
                return false;
            StringBuilder sb = new StringBuilder(jono);
            maara = Mjonot.erotaDouble(sb, 0);
            valuutta = valuutat.getValuutannimi(Mjonot.erota(sb, '|', ""));
            return true;
        }


        /**
         * Tulostetaan kysytyn valuutan tiedot.
         */
        public void tulosta() {
            System.out.println(String.format("%.2f", maara) + " " + valuutta + " on " + String.format("%.2f", valuutat.getVaihdettuMaara(valuutta, maara)) + " e");
        }

    }

}