package demo.d9;

import java.util.Arrays;

/**
 * @author Herman
 * @version 3.7.2020
 *
 */
public class KuvastaLuokat {
    
    /** 
     * Luokka täyden taulukon poikkeusta varten
     */
    public static class TaulukkoTaysiException extends Error {
        private static final long serialVersionUID = 1L;
        
        TaulukkoTaysiException(String viesti) {
            super(viesti);
        }
    }
    
    /**
     * Luokka joka kokoaa tietoja
     */
    public static class Kokoaja {
        private Tiedot[] tiedot = new Tiedot[7];
        private int lkm;
        
        /**
         * Lisää tiedon kokoajaan
         * @param tieto Jokin lisättävä tieto
         * @throws TaulukkoTaysiException jos taulukko on täysi
         */
        public void lisaa(Tiedot tieto) throws TaulukkoTaysiException {
            if (lkm >= tiedot.length) throw new TaulukkoTaysiException("Henkilöt täynnä.");
            tiedot[lkm++] = tieto;
        }
        
        /**
         * Muuttaa tiedot merkkijonoksi
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < lkm; i++) {
                sb.append(tiedot[i]);
            }
            return sb.toString();
        }
        
        /**
         * @param args ei käytössä
         */
        public static void main(String args[]) {
            Numero ysi = new Numero(9), kaksi = new Numero(2);
            Numerot numerot = new Numerot();
            numerot.lisaa(new Numero(5)); 
            numerot.lisaa(ysi);
            numerot.lisaa(new Numero(3));
            numerot.lisaa(kaksi);
            numerot.lisaa(new Numero(1));
            numerot.lisaa(new Numero(0));
            Henkilo katto = new Henkilo("Kassinen Katto", "Katto", "3452", ysi);
            Henkilo sepe = new Henkilo("Susi Sepe", "Takametsä", "-", kaksi);
            Henkilot henkilot = new Henkilot();
            henkilot.lisaa(katto);
            henkilot.lisaa(sepe);
            henkilot.lisaa(sepe);
            Kokoaja kokoaja = new Kokoaja();
            kokoaja.lisaa(numerot);
            kokoaja.lisaa(katto);
            kokoaja.lisaa(henkilot);
            System.out.println(kokoaja);
        }
    }
    
    /**
     * Yliluokka erilaisille tiedoille
     */
    public static class Tiedot {
        //
    }
    
    /**
     * Luokka henkilöiden tietojen keräämistä varten
     */
    public static class Henkilot extends Tiedot {
        private Henkilo[] henkilot = new Henkilo[7];
        private int lkm;
        
        /**
         * Lisää henkilön henkilöihin
         * @param henkilo Lisättävä henkilö
         * @throws TaulukkoTaysiException jos taulukko on täysi
         */
        public void lisaa(Henkilo henkilo) throws TaulukkoTaysiException {
            if (lkm >= henkilot.length) throw new TaulukkoTaysiException("Henkilöt täynnä.");
            henkilot[lkm++] = henkilo;
        }
        
        /**
         * Muuttaa henkilöt merkkijonoksi
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < lkm; i++) {
                sb.append(henkilot[i]);
            }
            return sb.toString();
        }
    }
    
    /**
     * Luokka henkilön tiedoille
     */
    public static class Henkilo extends Tiedot {
        private String nimi;
        private String osoite;
        private String sarja;
        private Numero luku;
        
        /**
         * Henkilön muodostaja
         * @param nimi Henkilön nimi
         * @param osoite Henkilön osoite
         * @param sarja Henkilön sarjatunnusluku
         * @param luku Henkilön numero
         */
        public Henkilo(String nimi, String osoite, String sarja, Numero luku) {
            this.nimi = nimi;
            this.osoite = osoite;
            this.sarja = sarja;
            this.luku = luku;
        }
        
        /**
         * Muuttaa henkilön tiedot merkkijonoksi
         */
        @Override
        public String toString() {
            return nimi + "\n" + osoite + "\n" + sarja + "\n" + luku + "\n";
        }
    }
    
    /**
     * Luokka numeroiden keräämistä varten
     */
    public static class Numerot extends Tiedot {
        private Numero[] numerot = new Numero[6];
        private int lkm;
        
        /**
         * Lisää numeron numeroihin
         * @param numero Lisättävä numero
         * @throws TaulukkoTaysiException jos taulukko on täysi
         */
        public void lisaa(Numero numero) throws TaulukkoTaysiException {
            if (lkm >= numerot.length) throw new TaulukkoTaysiException("Numerot täynnä.");
            numerot[lkm++] = numero;
        }
        
        /**
         * Muuttaa numerot merkkijonoksi
         */
        @Override
        public String toString() {
            /*
            StringBuilder sb = new StringBuilder();
            String erotin = " ";
            String ero = "";
            for (Numero luku: numerot) {
                sb.append(ero);
                sb.append(luku);
                ero = erotin;
            }
            return sb.toString();
            */
            return Arrays.toString(numerot) + "\n";
        }
    }
    
    /**
     * Luokka numero-olioita varten
     */
    public static class Numero {
        private int luku;
        
        /**
         * Numeron muodostaja
         * @param luku Numeron numero
         */
        public Numero(int luku) {
            this.luku = luku;
        }
        
        /**
         * Muuttaa numeron merkkijonoksi
         */
        @Override
        public String toString() {
            return ""+luku;
        }
    }
}
