package demo.d6;

//import demo.d2.LisaaPvm;
import fi.jyu.mit.ohj2.*;

/**
 * Alustava luokka päivämäärää varten
 * @author Vesa Lappalainen
 * @version 1.0, 07.02.2003
 * @version 1.1, 14.02.2003
 * @version 1.2, 17.02.2003
 * @version 1.3, 11.02.2008
 */
public class Pvm {

    private int pv;
    private int kk;
    private int vv;


    /**
     *  Muuttaa päivämäärän nykypäivälle.
     *  Todo: pitää vaihtaa hakemaan päivämäärä oikeasti. 
     *  Mutta perinnässäkään ei nyt saa käyttää enempää attribuutteja kuin tässä on
     */
    public void paivays() {
        pv = 13;
        kk = 2;
        vv = 2012;
    }
    
    /**
     * Alustetaan päivämäärä. 0-arvot eivät muuta vastaavaa attribuuttia. Jos jokin attribuutti on saamassa väärän arvon, mitään attribuuttia ei muuteta
     * @param ipv päivän alustus
     * @param ikk kuukauden alustus
     * @param ivv vuoden alustus, hyväksyy vuodet 1000 eteenpäin, muuttaa alle 100 1900-luvuksi ja alle 50 2000-luvuksi
     * @example
     * <pre name="test">
     * Pvm pvm = new Pvm();
     * pvm.toString() === "13.2.2012";
     * pvm.alusta(29, 0, 0);
     * pvm.toString() === "29.2.2012";
     * pvm.alusta(29, 0, 2011);
     * pvm.toString() === "29.2.2012";
     * pvm.alusta(28, 0, 2011);
     * pvm.toString() === "28.2.2011";
     * pvm.alusta(1, 1, 3);
     * pvm.toString() === "1.1.2003";
     * pvm.alusta(1, 1, 83);
     * pvm.toString() === "1.1.1983";
     * pvm.alusta(1, 1, 100);
     * pvm.toString() === "1.1.1983";
     * pvm = new Pvm(20,2,2012);
     * pvm.alusta(1,3,0);     pvm.toString() === "1.3.2012";
     * pvm.alusta(2,13,2012); pvm.toString() === "1.3.2012";
     * pvm.alusta(28,2,2012); pvm.toString() === "28.2.2012";
     * pvm.alusta(29,2,2011); pvm.toString() === "28.2.2012";
     * pvm.alusta(29,2,2012); pvm.toString() === "29.2.2012";
     * pvm.alusta(31,3,2012); pvm.toString() === "31.3.2012";
     * pvm.alusta(31,4,2012); pvm.toString() === "31.3.2012";
     * pvm.alusta( 0,2,2012); pvm.toString() === "31.3.2012";
     * </pre>
     */
    public void alusta(int ipv, int ikk, int ivv) {
        /* Vuoden oikeellisuus */
        if (100 <= ivv && ivv < 1000) return;
        
        int vuosi = vv;
        if (ivv > 0) vuosi = ivv;
        if (vuosi < 50) vuosi += 2000;
        if (vuosi < 100) vuosi += 1900;
        
        /* Kuukauden oikeellisuus */
        if (ikk < 0 || 12 < ikk) return;
        
        int kuukausi = kk;
        if ( ikk > 0 ) kuukausi = ikk;
        
        /* Päivän oikeellisuus */
        if (ipv < 0 || LisaaPvm.KPITUUDET[LisaaPvm.karkausvuosi(vuosi)][kuukausi - 1] < ipv
                || (ipv == 0 && LisaaPvm.KPITUUDET[LisaaPvm.karkausvuosi(vuosi)][kuukausi - 1] < pv)) 
            return;
        
        if ( ipv > 0 ) pv = ipv;
        kk = kuukausi;
        vv = vuosi;
    }

    /**
     * Alustetaan päivämäärä. Arvot <= 0 eivät muuta päivämäärää. Ylimääräiset kuukaudet ja päivät lisätään päivämäärään.  
     * @param ipv päivän alustus
     * @param ikk kuukauden alustus
     * @param ivv vuoden alustus
     * @example
     * <pre name="test">
     * Pvm pvm = new Pvm();
     * pvm.toString() === "13.2.2012";
     * pvm.alustaYnnaten(30, 0, 0);
     * pvm.toString() === "1.3.2012";
     * pvm.alustaYnnaten(30, 2, 2013);
     * pvm.toString() === "2.3.2013";
     * pvm.alustaYnnaten(92, 12, 2011); // 92-31-31-29 <- karkausvuosi
     * pvm.toString() === "1.3.2012";
     * pvm.alustaYnnaten(92, 24, 2010);
     * pvm.toString() === "1.3.2012";
     * pvm.alustaYnnaten(92, 25, 2010); // 92.1.2012, 92-31-29-31
     * pvm.toString() === "1.4.2012";
     * pvm.alustaYnnaten(0, -1, -2);
     * pvm.toString() === "1.4.2012";
     * pvm.alustaYnnaten(1, 1, 03);
     * pvm.toString() === "1.1.2003";
     * pvm.alustaYnnaten(1, 1, 83);
     * pvm.toString() === "1.1.1983";
     * pvm.alustaYnnaten(1, 1, 100);
     * pvm.toString() === "1.1.100";
     * </pre>
     */
    public void alustaYnnaten(int ipv, int ikk, int ivv) {
        /* Vuoden oikeellisuus */
        if (ivv > 0) vv = ivv;
        if (vv < 50) vv += 2000;
        if (vv < 100) vv += 1900;
        
        /* Kuukauden oikeellisuus */
        if (ikk > 0) kk = ikk;
        while (kk > 12) {
            vv++;
            kk -= 12;
        }
        
        /* Päivän oikeellisuus */     
        if (ipv > 0) this.pv = ipv;
        while (pv > LisaaPvm.KPITUUDET[LisaaPvm.karkausvuosi(vv)][kk-1]) {
            pv -= LisaaPvm.KPITUUDET[LisaaPvm.karkausvuosi(vv)][kk-1];
            kk++;
            /* Kuukauden oikeellisuus */
            if (kk > 12) {
                vv++;
                kk = 1;
            }
        }
    }


    /** Alustetaan kaikki attribuutit oletusarvoon */
    public Pvm() {
        this(0, 0, 0);
    }


    /** 
     * Alustetaan kuukausi ja vuosi oletusarvoon
     * @param pv päivän alustusarvo
     */
    public Pvm(int pv) {
        this(pv, 0, 0);
    }


    /**
     * Alustetaan vuosi oletusarvoon
     * @param pv päivän alustusarvo
     * @param kk kuukauden oletusarvo
     */
    public Pvm(int pv, int kk) {
        this(pv, kk, 0);
    }


    /**
     * Alustetaan vuosi oletusarvoon
     * @param pv päivän alustusarvo
     * @param kk kuukauden oletusarvo
     * @param vv vuoden alustusarvo
     */
    public Pvm(int pv, int kk, int vv) {
        paivays();
        alusta(pv, kk, vv);
    } 


    /**
     * Alustetaan päivämäärä merkkijonosta
     * @param s muotoa 12.3.2008 oleva merkkijono
     */
    public Pvm(String s) {
        paivays();
        pvmParse(s);
    } 


    /**
     * Päivämäärä merkkijonona
     * @return päivämäärä muodossa 17.2.2007
     * @example
     * <pre name="test">
     *   Pvm tammi2011 = new Pvm(1,1,2011);
     *   tammi2011.toString() === "1.1.2011"
     *   Pvm helmi2011 = new Pvm(1,2,2011);
     *   helmi2011.toString() === "1.2.2011"
     *   Pvm tanaan = new Pvm(14,2,2011);
     *   tanaan.toString()    === "14.2.2011"
     *   Pvm maalis97 = new Pvm(1,3,1997);
     *   maalis97.toString()  === "1.3.1997"
     * </pre>
     */
    @Override
    public String toString() {
        return pv + "." + kk + "." + vv;
    }


    /**
     * Ottaa päivämäärän tiedot merkkijonosta joka on muotoa 17.2.2007
     * Jos joku osa puuttuu, sille käytetään tämän päivän arvoa oletuksena.
     * @param sb tutkittava merkkijono
     */
    protected final void pvmParse(StringBuilder sb) {
        int p = Mjonot.erota(sb, '.', 0);
        int k = Mjonot.erota(sb, '.', 0);
        int v = Mjonot.erota(sb, ' ', 0);
        alusta(p, k, v);
        // tai alusta(Mjonot.erota(sb,'.',0),Mjonot.erota(sb,'.',0),Mjonot.erota(sb,'.',0));
    }


    /**
     * Ottaa päivämäärän tiedot merkkijonosta joka on muotoa 17.2.2007
     * Jos joku osa puuttuu, sille käytetään tämän päivän arvoa oletuksena.
     * @param s tutkittava merkkijono
     */
    protected final void pvmParse(String s) {
        pvmParse(new StringBuilder(s));
    }


    /**
     * Ottaa päivämäärän tiedot merkkijonosta joka on muotoa 17.2.2007
     * Jos joku osa puuttuu, sille käytetään tämän päivän arvoa oletuksena.
     * @param s tutkittava merkkijono
     * 
     * @example
     * <pre name="test">
     * Pvm pvm = new Pvm(11,3,2003);
     * pvm.parse("12");           pvm.toString() === "12.3.2003";
     * pvm.parse("..2001");       pvm.toString() === "12.3.2001";
     * pvm.parse("..2009 14:30"); pvm.toString() === "12.3.2009"; 
     * </pre>
     */
    public void parse(String s) {
        pvmParse(s);
    }


    /**
     * Ottaa päivämäärän tiedot merkkijonosta joka on muotoa 17.2.2007
     * Jos joku osa puuttuu, sille käytetään tämän päivän arvoa oletuksena.
     * Merkkijonosta otetaan pois vain se osa, jota tarvitaan.
     * @param sb tutkittava merkkijono
     * 
     * @example
     * <pre name="test">
     * Pvm pvm = new Pvm(11,3,2003);
     * StringBuilder jono = new StringBuilder("12");
     * pvm.parse(jono); pvm.toString() === "12.3.2003"; jono.toString() === "";
     * jono = new StringBuilder("..2001");
     * pvm.parse(jono); pvm.toString() === "12.3.2001"; jono.toString() === "";
     * jono = new StringBuilder("..2009 14:30");
     * pvm.parse(jono); pvm.toString() === "12.3.2009"; jono.toString() === "14:30";
     * </pre>
     */
    public void parse(StringBuilder sb) {
        pvmParse(sb);
    }


    // Lisätty saantimetodit:

    /**
     * @return päivän arvo
     * @example
     * <pre name="test">
     *   Pvm pv = new Pvm("14.2.2011");
     *   pv.getPv() === 14;
     * </pre>
     */
    public int getPv() {
        return pv;
    }


    /**
     * @return kuukauden arvo
     * @example
     * <pre name="test">
     *   Pvm pv = new Pvm("14.2.2011");
     *   pv.getKk() === 2;
     * </pre>
     */
    public int getKk() {
        return kk;
    }


    /**
     * @return vuoden arvo
     * @example
     * <pre name="test">
     *   Pvm pv = new Pvm("14.2.2011");
     *   pv.getVv() === 2011;
     * </pre>
     */
    public int getVv() {
        return vv;
    }

    /**
     * Tutkii onko päivämäärä suurempi kuin päivämäärä johon verrataan
     * @param pv2 Päivämäärä johon verrataan
     * @return 1, jos on suurempi, 0 jos yhtä suuret, -1 jos pienempi
     * @example
     * <pre name="test">
     * Pvm pv1 = new Pvm(12, 12, 2012), pv2 = new Pvm(20, 1, 2020);
     * pv1.compareTo(pv2) === -1;
     * pv1.alusta(21, 1, 2020);
     * pv1.compareTo(pv2) === 1;
     * pv2.alusta(21, 1, 2020);
     * pv1.compareTo(pv2) === 0;
     * pv2.compareTo(pv1) === 0; //Symmetrisyys
     * pv1.compareTo(pv1) === 0; //Reflektiivisyys
     * Pvm pv3 = new Pvm(21, 1, 2020);
     * pv2.compareTo(pv3) === 0;
     * pv1.compareTo(pv3) === 0; //Transitiivisuus
     * </pre>
     */
    public int compareTo(Pvm pv2) {
        int vverotus = vv - pv2.getVv();
        if (vverotus < 0) return -1;
        if (vverotus > 0) return 1;
        int kkerotus = kk - pv2.getKk();
        if (kkerotus < 0) return -1;
        if (kkerotus > 0) return 1;
        int pperotus = pv - pv2.getPv();
        if (pperotus < 0) return -1;
        if (pperotus > 0) return 1;
        return 0;
    }
    
    /**
     * Vertaa päivämäärää toiseen
     * @param pv2 Päivämäärä, johon verrataan
     * @return Onko sama päivämäärä, true jos on, false jos ei
     * @example
     * <pre name="test">
     * Pvm pv1 = new Pvm(21, 8, 2021), pv2 = new Pvm(21, 8, 2021), pv3 = new Pvm(21, 8, 2021);
     * pv1.equals(pv1) === true; //Reflektiivisyys
     * pv1.equals(pv2) === true;
     * pv2.equals(pv1) === true; //Symmetrisyys
     * pv2.equals(pv3) === true;
     * pv1.equals(pv3) === true; //Transitiivisuus
     * pv1.alusta(1, 1, 2020);
     * pv1.equals(pv2) === false;
     * </pre>
     */
    public boolean equals(Pvm pv2) {
        return this.compareTo(pv2) == 0;
    }
    
    /**
     * Staattinen metodi, jolla tutkitaan onko ensimmäinen päivämäärä suurempi kuin toinen
     * @param pv1 Ensimmäinen päivämäärä
     * @param pv2 Toinen päivämäärä
     * @return 1, jos ensimmäinen suurempi, 0 jos yhtä suuret, -1 jos ensimmäinen pienempi
     * @example
     * <pre name="test">
     * Pvm pv1 = new Pvm(12, 12, 2012), pv2 = new Pvm(20, 1, 2020);
     * Pvm.compareTo(pv1, pv2) === -1;
     * pv1.alusta(21, 1, 2020);
     * Pvm.compareTo(pv1, pv2) === 1;
     * pv2.alusta(21, 1, 2020);
     * Pvm.compareTo(pv1, pv2) === 0;
     * Pvm.compareTo(pv2, pv1) === 0; //Symmetrisyys
     * Pvm.compareTo(pv1, pv1) === 0; //Reflektiivisyys
     * Pvm pv3 = new Pvm(21, 1, 2020);
     * Pvm.compareTo(pv2, pv3) === 0;
     * Pvm.compareTo(pv1, pv3) === 0; //Transitiivisuus
     * pv1 = new Pvm(15,6,2013);
     * pv2 = new Pvm(14,5,2014);
     * pv3 = new Pvm(15,7,2014);
     * Pvm pv4 = new Pvm(16,7,2014);
     * Pvm pv5 = new Pvm(16,7,2014);
     * Pvm pv6 = new Pvm(16,7,2012);
     * Pvm.compareTo(pv1,pv2) === -1;  // ero vuodessa
     * Pvm.compareTo(pv2,pv1) ===  1;
     * Pvm.compareTo(pv2,pv3) === -1;  // ero kuukaudessa
     * Pvm.compareTo(pv3,pv2) ===  1;
     * Pvm.compareTo(pv3,pv4) === -1;  // ero päivässä
     * Pvm.compareTo(pv4,pv3) ===  1;
     * Pvm.compareTo(pv4,pv5) ===  0;  // kaikki samoja
     * Pvm.compareTo(pv6,pv2) === -1;  // ero kuukaudessa, mutta vuodessa toisinpäin
     * Pvm.compareTo(pv2,pv6) ===  1;
     * </pre>
     */
    public static int compareTo(Pvm pv1, Pvm pv2) {
        int vverotus = pv1.getVv() - pv2.getVv();
        if (vverotus < 0) return -1;
        if (vverotus > 0) return 1;
        int kkerotus = pv1.getKk() - pv2.getKk();
        if (kkerotus < 0) return -1;
        if (kkerotus > 0) return 1;
        int pperotus = pv1.getPv() - pv2.getPv();
        if (pperotus < 0) return -1;
        if (pperotus > 0) return 1;
        return 0;
    }

    /**
     * Testataan päivämäärä-luokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pvm pvm = new Pvm();

        pvm.parse("12.1.1995");
        System.out.println(pvm);
        pvm.parse("15.3");
        System.out.println(pvm);
        pvm.parse("14");
        System.out.println(pvm.getPv());
        
        // Käyttöesimerkki 2&3:een kun compareTo-metodit ovat Pvm-luokassa
        Pvm pv1 = new Pvm(1,2),pv2 = new Pvm(3,3);
        if ( compareTo(pv1,pv2) < 0 ) System.out.println(pv1 + " < " + pv2);
        if ( pv1.compareTo(pv2) != 0 ) System.out.println(pv1 + " != " + pv2);
        if ( pv1.equals(pv2) ) System.out.println(pv1 + " == " + pv2);

    }
}