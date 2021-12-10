// package demo.d7;
package demo.d8;

/**
 * 
 * @author Vesa Lappalainen
 * @author Herman Lätti
 * @version kesä2020 27.6.2020
 */
public class Makihyppy {

    /**
     * Luokka yhtä mäkihypyn kierrosta varten
     */
    public static class Kierros {
        private final static double K_PISTE = 60.0;
        private final static double METRIKERROIN = 1.0;
        private final static int TUOMAREITA = 5;
        private double pituus; // hyppyjen pituudet metreinä
        private final double[] tuomarit = new double[TUOMAREITA]; // tuomaripisteet

        /** Kierroksen alustaminen   */
        public Kierros() {
            // attribuutin oletusalustus => pisteet 0.0
        }
        
        /**
         * Saantimetodi hypyn pituudelle
         * @return Hypyn pituus metreinä
         */
        public double getPituus() {
            return this.pituus;
        }
        
        /**
         * Saantimetodi kierroksen pisteille
         * @return Kierroksen pisteet siten että huonointa ja parasta ei laskettu mukaan
         */
        public double getPisteet() {
            // double summa = demo.d7.Rajat.summaHuonoinJaParasPois(this.tuomarit);
            double summa = demo.d7.Taulukot.summaHuonoinJaParasPois(this.tuomarit);
            return summa > 0 ? METRIKERROIN * this.pituus - K_PISTE + summa : 0;
        }
        
        /**
         * Asettaa annetun pituuden annetulle kierrokselle
         * @param pituus Pituus joka asetetaan
         */
        public void setPituus(double pituus) {
            this.pituus = pituus;
        }
        
        /**
         * Asettaa annetulle kierrokselle annetun tuomarin annetut pisteet
         * @param tuomari Tuomari (kuinka mones), jonka pisteet asetetaan
         * @param pisteet Pisteet, jotka asetetaan
         */
        public void setTuomari(int tuomari, double pisteet) {
            this.tuomarit[tuomari - 1] = pisteet;
        }
        
        /**
         * Muuttaa pistetaulukon jonoksi annetulla erottimella
         * Olettaa että pisteet ovat välillä 0.0 - 999.9 ja 1-desimaalisia
         * @param erotin Merkkijono, jolla pistetaulukon alkiot erotetaan toisistaan
         * @return Pistetaulukon jonona annetulla erottimella
         */
        public String jonoksi(String erotin) {
            StringBuilder sb = new StringBuilder();
            String ero = "";
            for (double pisteet: this.tuomarit) {
                if (pisteet < 10)
                    sb.append(erotin);
                sb.append(ero);
                sb.append(pisteet);
                ero = erotin;
            }
            return sb.toString();
        }
        
        @Override
        public String toString() {
            return this.jonoksi(" ");
        }
    }

    /**
     * Luokka mäkihypyn yhden kilpailijan tulosta varten.
     * Sisältää monta kierosta.
     */
    public static class Tulos {
        private final static int KIERROKSIA = 2;
        private final Kierros[] kierros = new Kierros[KIERROKSIA];

        /** Tuloksen alustaminen */
        public Tulos() {
            for (int i = 0; i < this.kierros.length; i++)
                this.kierros[i] = new Kierros();
        }
        
        /**
         * Saantimetodi kierrosten yhteispistemäärälle
         * @return Palauttaa kierrosten yhteispistemäärän
         */
        public double getYhteensa() {
            double yhteensa = 0;
            for (int i = 0; i < this.kierros.length; i++)
                yhteensa += this.kierros[i].getPisteet();
            return yhteensa;
        }
        
        /**
         * Asettaa annetun pituuden annetulle kierrokselle
         * @param kierros Kierros, jolle pituus asetetaan
         * @param pituus Pituus joka asetetaan
         */
        public void setPituus(int kierros, double pituus) {
            this.kierros[kierros - 1].setPituus(pituus);
        }
        
        /**
         * Asettaa annetulle kierrokselle annetun tuomarin annetut pisteet
         * @param kierros Kierros, jolle pisteet asetetaan
         * @param tuomari Tuomari (kuinka mones), jonka pisteet asetetaan
         * @param pisteet Pisteet, jotka asetetaan
         */
        public void setTuomari(int kierros, int tuomari, double pisteet) {
            this.kierros[kierros - 1].setTuomari(tuomari, pisteet);
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            String format = "%6s";
            for (int i = 0; i < this.kierros.length; i++)
                sb.append("Kierros " + (i + 1) + " " + String.format(format, this.kierros[i].getPituus()) + " m.  Tuomarit:  "
                            + this.kierros[i] + " =" + String.format(format, this.kierros[i].getPisteet()) + " pistettä\n");
            sb.append("Yhteensä: " + String.format(format, this.getYhteensa()) + " pistettä.");
            return sb.toString();
        }
    }


    /**
     * Luokka yhtä mäkihypyn kilpailijaa varten.
     * Sisältää mm. tuloksen.
     */
    public static class Kilpailija {
        private String nimi;
        private int nro;
        private final Tulos tulos = new Tulos();

        /**
         * Kilpailijan alustaminen
         * @param nimi kilpailijan nimi
         * @param nro kilpailijan kilpailunumero
         */
        public Kilpailija(String nimi,int nro) {
            this.nimi = nimi;
            this.nro = nro;
        }
        
        /**
         * Saantimetodi kierrosten yhteispistemäärälle
         * @return Palauttaa kierrosten yhteispistemäärän
         */
        public double getYhteensa() {
            return this.tulos.getYhteensa();
        }
        
        /**
         * Asettaa annetun pituuden annetulle kierrokselle
         * @param kierros Kierros, jolle pituus asetetaan
         * @param pituus Pituus joka asetetaan
         */
        public void setPituus(int kierros, double pituus) {
            this.tulos.setPituus(kierros, pituus);
        }
        
        /**
         * Asettaa annetulle kierrokselle annetun tuomarin annetut pisteet
         * @param kierros Kierros, jolle pisteet asetetaan
         * @param tuomari Tuomari (kuinka mones), jonka pisteet asetetaan
         * @param pisteet Pisteet, jotka asetetaan
         */
        public void setTuomari(int kierros, int tuomari, double pisteet) {
            this.tulos.setTuomari(kierros, tuomari, pisteet);
        }
        
        @Override
        public String toString() {
            return this.nro + ": " + this.nimi;
        }

        /** Tulostaa kilpailijan tiedot */
        public void tulosta() {
            System.out.println(this);
            System.out.println(this.tulos + "\n");
        }

    }

    /**
     * Aliohjelma kisan käynnistämiseksi
     */
    public void kisa() { 
        Kilpailija toni = new Kilpailija("Toni",3); 
        Kilpailija matti = new Kilpailija("Matti",7); 
        toni.tulosta(); 
        matti.tulosta(); 
        
        toni.setPituus(1,107);       // 1. kierros, 107 m
        toni.setPituus(2,100);       // 2. kierros, 100 m
        toni.setTuomari(2,1,19.0);   // 2. kierros, 1. tuomari, 19 p 
        toni.setTuomari(2,2,18.0);   // 2. kierros, 2. tuomari, 18 p
        toni.setTuomari(2,3,19.5); 
        toni.setTuomari(2,4,18.0); 
        toni.setTuomari(2,5,20.0); 

        matti.setPituus(1,125); 
        matti.setTuomari(1,1,20.0); 
        matti.setTuomari(1,2,20.0); 
        matti.setTuomari(1,3,20.0); 
        matti.setTuomari(1,4,20.0); 
        matti.setPituus(2,109); 
        matti.setTuomari(2,1,20.0); 
        matti.setTuomari(2,2,20.0); 
        matti.setTuomari(2,3,20.0); 
        matti.setTuomari(2,4,20.0); 

        toni.tulosta(); 
        matti.tulosta(); 
        
        if (matti.getYhteensa() > toni.getYhteensa())
            System.out.println("Matti voitti!");
        else
            System.out.println("Toni voitti!");
      } 


    /**
     * Testataan luokan kääntymistä
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Makihyppy kisa = new Makihyppy();
        kisa.kisa();
    }


}