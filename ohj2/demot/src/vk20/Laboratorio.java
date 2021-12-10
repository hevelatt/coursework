package vk20;

import java.util.ArrayList;

/**
 * Ohjelmointi 2 välikoe 20.3.2020
 * Oliot ja luokat.
 * 
 * Laboratorio huolehtii sinne säilötyistä viruksista.
 *
 */
public class Laboratorio {

    private static int lkm = 0;
    private Virus[] virukset = new Virus[2];

    /**
     * Luokka viruksia varten
     * @author Herman
     * @version 17.7.2020
     *
     */
    public static class Virus {
        private String nimi = "";
        private String tyyppi = "";
        private double vihaisuus;
        
        /**
         * Viruksen oletusmuodostaja
         */
        public Virus( ) {
            // Oletusmuodostaja, attribuuttien oletusalustukset
        }
        
        /**
         * Viruksen muodostaja
         * @param nimi Viruksen nimi
         */
        public Virus(String nimi) {
            this.nimi = nimi;
        }
        
        /**
         * Viruksen muodostaja
         * @param nimi Viruksen nimi
         * @param tyyppi Viruksen tyyppi
         */
        public Virus(String nimi, String tyyppi) {
            this.nimi = nimi;
            this.tyyppi = tyyppi;
        }
        
        /**
         * Viruksen muodostaja
         * @param nimi Viruksen nimi
         * @param tyyppi Viruksen tyyppi
         * @param vihaisuus Viruksen vihaisuus
         */
        public Virus(String nimi, String tyyppi, double vihaisuus) {
            this.nimi = nimi;
            this.tyyppi = tyyppi;
            this.vihaisuus = vihaisuus;
        }
        
        /**
         * Saantimetodi viruksen tyypille
         * @return Palauttaa viruksen tyypin
         */
        public String getTyyppi() {
            return tyyppi;
        }
        
        /**
         * Saantimetodi viruksen vihaisuudelle
         * @return Palauttaa viruksen vihaisuuden
         */
        public double getVihaisuus() {
            return vihaisuus;
        }
        
        /**
         * Asetusmetodi viruksen nimelle
         * @param nimi Viruksen uusi nimi
         */
        public void setNimi(String nimi) {
            this.nimi = nimi;
        }
        /**
         * Asetusmetodi viruksen tyypille
         * @param tyyppi Viruksen uusi tyyppi
         */
        public void setTyyppi(String tyyppi) {
            this.tyyppi = tyyppi;
        }
        /**
         * Asetusmetodi viruksen vihaisuudelle
         * @param vihaisuus Viruksen uusi vihaisuus
         */
        public void setVihaisuus(double vihaisuus) {
            this.vihaisuus = vihaisuus;
        }
        
        /**
         * Virus merkkijonoksi
         * @return Virus merkkijonona muodossa nimi|tyyppi|vihaisuus|
         */
        @Override
        public String toString() {
            return nimi + "|" + tyyppi + "|" + vihaisuus + "|";
        }
    }
    
    /**
     * Lisää annetun virukset laboratorion viruskokoelmaan.
     * @param vi Lisättävä virus
     */
    public void lisaaVirus(Virus vi) {
        if (lkm >= virukset.length) {
            Virus[] virukset2 = new Virus[lkm*2];
            for (int i = 0; i < virukset.length; i++)
                virukset2[i] = virukset[i];
            virukset = virukset2;
        }
        virukset[lkm]=vi;
        lkm++;
    }
    
    /**
     * Etsitään listaan virukset joilla on tietty tyyppi ja tietyn suuruinen vihaisuus
     * @param tyyppi Minkä tyyppisiä viruksia listataan
     * @param vihaisuus Vihaisuus vähintään tämän verran
     * @return Palauttaa listan viruksista, joiden tyyppi vastaa parametria ja joiden vihaisuus on vähintään annetun verran
     */
    public ArrayList<Virus> keraaTietynTyyppiset(String tyyppi, double vihaisuus) {
        ArrayList<Virus> tietynTyyppiset = new ArrayList<Virus>();
        for (int i = 0; i < lkm; i++) {
            if (virukset[i].tyyppi.equals(tyyppi) && virukset[i].vihaisuus >= vihaisuus)
                tietynTyyppiset.add(virukset[i]);
        }
        return tietynTyyppiset;
    }
    
    /**
     * Pääohjelma testaa Laboratorio- ja Virus-luokkia.
     * @param args Ei käytössä.
     */
    public static void main(String[] args)  {

        Virus nimeton = new Virus();
        Virus korona = new Virus("SARS-CoV-2"); 
        
        // TODO a) 1p - tee Virus-luokkaan kaikki muodostajat
        // TODO b) 1p - tee kaikki tarvittavat metodit olion tietojen muokkaamista ja hakemista varten
        
        korona.setTyyppi("koronavirus"); 
        
        // TODO c) 1p - Viruksen merkkijonoesitys on muotoa: nimi|tyyppi|vihaisuus|
        
        System.out.println(korona);
        System.out.println(nimeton);
        // Tulostus: 
        // SARS-CoV-2|koronavirus|0.0|
        // ||0.0
        
        nimeton.setNimi("Ebola");
        nimeton.setTyyppi("ebolavirus");
        nimeton.setVihaisuus(0.40);
   
        System.out.println(korona);
        System.out.println(nimeton);
        
        //Tulostus:
        // SARS-CoV|koronavirus|0.0|
        // Ebola|ebolavirus|0.40|
        
        Virus h1n1 = new Virus("sikainfluenssa", "influenssavirukset", 0.006);
        
        System.out.println(h1n1);
        //Tulostus:
        // sikainfluenssa|influenssavirukset|0.006|
         
        Laboratorio pfizer = new Laboratorio();
        pfizer.lisaaVirus(korona);
        pfizer.lisaaVirus(new Virus("tuhkarokko", "rokko", 0.2));
        
        pfizer.lisaaVirus(h1n1); 
                
        // Laboratorioon mahtuu tällä hetkellä vain rajallinen määrä viruksia säilöön. 
        // Päästetäänkö kaikki ylimenevät virukset karkuun maailmalle vai kasvatetaanko säiliön tilaa? 
        
        // TODO 1p d) muokkaa aliohjelmaa lisaaVirus niin, että taulukko kasvaa dynaamisesti. 
        
        Virus iso = new Virus("isorokko", "rokko");
        iso.setVihaisuus(0.3);
        pfizer.lisaaVirus(iso);
             
        pfizer.lisaaVirus(new Virus("vesirokko", "rokko", 0.5));
        pfizer.lisaaVirus(new Virus("parvorokko", "rokko", 0.002));
            
        // TODO e) 2p - Toteuta keraaTietynTyyppiset-metodi
       
        System.out.println("Rokkoviruksia, joiden vihaisuus on vähintään 0.3:");
        for (Virus v : pfizer.keraaTietynTyyppiset("rokko", 0.3))
           System.out.println(v);
        
        //Tulostus:
        //isorokko|rokko|0.3|
        //vesirokko|rokko|0.5|
        
    }

}