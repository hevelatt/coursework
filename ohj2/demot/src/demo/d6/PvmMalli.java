package demo.d6;

import fi.jyu.mit.ohj2.*;

/**
 * Alustava luokka päivämäärää varten
 * @author Vesa Lappalainen
 * @version 1.0, 07.02.2003
 * @version 1.1, 14.02.2003
 * @version 1.2, 17.02.2003
 * @version 1.3, 11.02.2008
 */
public class PvmMalli implements Comparable<Object> {
    /*
     * #CLASSIMPORT
     * @example
     * <pre name="testJAVA">
     *  private static final String s22_2     = "22.2.2012",
     *                              s23_2     = "23.2.2012";
     *  private final PvmMalli    tammi2012 = new PvmMalli(1,1),
     *                       helmi2012 = new PvmMalli(1),
     *                       tanaan    = new PvmMalli(),
     *                       maalis97  = new PvmMalli(1,3,97),
     *                       p22_2     = new PvmMalli(s22_2),
     *                       p23_2     = new PvmMalli(s23_2);
     * </pre>
     */

    /** Päivän arvo */
    private int pv;
    /** Kuukauden arvo */
    private int kk;
    /** Vuoden arvo */
    private int vv;


    /**
     *  Muuttaa päivämäärän nykypäivälle.
     *  Todo: pitää vaihtaa hakemaan päivämäärä oikeasti. 
     *  Mutta perinnässäkään ei nyt saa käyttää enempää attribuutteja kuin tässä on
     */
    public void paivays() { 
        pv = 20; 
        kk = 2;  
        vv = 2012;  
    } 


    /**
     * Alustetaan päivämäärä. 0-arvot eivät muuta vastaavaa attribuuttia  
     * @param ipv päivän alustus
     * @param ikk kuukauden alustus
     * @param ivv vuoden alustus
     * @return true jos alustus onnistuu
     * @example
     * <pre name="test">
     *   PvmMalli PvmMalli = new PvmMalli(20,2,2012);
     *   PvmMalli.alusta(1,3,0)     === true;  PvmMalli.toString() === "1.3.2012";
     *   PvmMalli.alusta(2,13,2012) === false; PvmMalli.toString() === "1.3.2012";
     *   PvmMalli.alusta(28,2,2012) === true;  PvmMalli.toString() === "28.2.2012";
     *   PvmMalli.alusta(29,2,2011) === false; PvmMalli.toString() === "28.2.2012";
     *   PvmMalli.alusta(29,2,2012) === true;  PvmMalli.toString() === "29.2.2012";
     *   PvmMalli.alusta(31,3,2012) === true;  PvmMalli.toString() === "31.3.2012";
     *   PvmMalli.alusta(31,4,2012) === false; PvmMalli.toString() === "31.3.2012";
     *   PvmMalli.alusta( 0,2,2012) === false; PvmMalli.toString() === "31.3.2012";
     * </pre>
     */
    public boolean alusta(int ipv, int ikk, int ivv) {
        int p = this.pv;
        int k = this.kk;
        int v = this.vv;
        if ( ivv > 0 ) v = ivv;
        if ( v < 50 ) v += 2000;
        if ( v < 100 ) v += 1900;
        if ( ikk > 0 ) k = ikk;
        if ( ipv > 0 ) p = ipv;

        if ( k > 12 ) return false;
        int kv = demo.d2.LisaaPvm.karkausvuosi(v);
        int pv_lkm = demo.d2.LisaaPvm.KPITUUDET[kv][k - 1];
        if ( p > pv_lkm ) return false;

        this.pv = p;
        this.kk = k;
        this.vv = v;
        return true;
    }


    /** Alustetaan kaikki attribuutit oletusarvoon */
    public PvmMalli() {
        this(0, 0, 0);
    }


    /** 
     * Alustetaan kuukausi ja vuosi oletusarvoon.  
     * @param pv päivän alustusarvo, jos huono, käytetään nykypäivää
     */
    public PvmMalli(int pv) {
        this(pv, 0, 0);
    }


    /**
     * Alustetaan vuosi oletusarvoon. Jos pv tai kk on huonoja,
     * käytetään nykypäiväystä.
     * @param pv päivän alustusarvo 
     * @param kk kuukauden oletusarvo
     */
    public PvmMalli(int pv, int kk) {
        this(pv, kk, 0);
    }


    /**
     * Alustetaan päivämäärä.  
     * Jos pv tai kk on huonoja, käytetään nykypäiväystä.
     * @param pv päivän alustusarvo
     * @param kk kuukauden oletusarvo
     * @param vv vuoden alustusarvo
     */
    public PvmMalli(int pv, int kk, int vv) {
        paivays();
        alusta(pv, kk, vv);
    } 


    /**
     * Alustetaan päivämäärä merkkijonosta.  Jos jonon sisältö väärän
     * muotoinen päiväys, käytetään nykypäivää.
     * @param s muotoa 12.3.2008 oleva merkkijono
     * @example
     * <pre name="test">
     *   PvmMalli PvmMalli1 = new PvmMalli("12.3.2008"); PvmMalli1.toString() === "12.3.2008";
     *   PvmMalli PvmMalli2 = new PvmMalli("12.13.2008"); PvmMalli2.toString() === tanaan.toString();
     *   PvmMalli PvmMalli3 = new PvmMalli("32.1.2008");  PvmMalli3.toString() === tanaan.toString();
     * </pre>
     */
    public PvmMalli(String s) {
        paivays();
        PvmMalliParse(s);
    } 


    /**
     * Päivämäärä merkkijonona
     * @return päivämäärä muodossa 17.2.2007
     * @example
     * <pre name="test">
     *   tammi2012.toString() === "1.1.2012"
     *   helmi2012.toString() === "1.2.2012"
     *   tanaan.toString()    === "20.2.2012"
     *   maalis97.toString()  === "1.3.1997"
     *   p23_2.toString()     === s23_2
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
    protected final void PvmMalliParse(StringBuilder sb) {
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
    protected final void PvmMalliParse(String s) {
        PvmMalliParse(new StringBuilder(s));
    }


    /**
     * Ottaa päivämäärän tiedot merkkijonosta joka on muotoa 17.2.2007
     * Jos joku osa puuttuu, sille käytetään tämän päivän arvoa oletuksena.
     * @param s tutkittava merkkijono
     * 
     * @example
     * <pre name="test">
     * PvmMalli PvmMalli = new PvmMalli(11,3,2003);
     * PvmMalli.parse("12"); PvmMalli.toString() === "12.3.2003";
     * PvmMalli.parse("..2001"); PvmMalli.toString() === "12.3.2001";
     * PvmMalli.parse("..2009 14:30"); PvmMalli.toString() === "12.3.2009"; 
     * </pre>
     */
    public void parse(String s) {
        PvmMalliParse(s);
    }


    /**
     * Ottaa päivämäärän tiedot merkkijonosta joka on muotoa 17.2.2007
     * Jos joku osa puuttuu, sille käytetään tämän päivän arvoa oletuksena.
     * Merkkijonosta otetaan pois vain se osa, jota tarvitaan.
     * @param sb tutkittava merkkijono
     * 
     * @example
     * <pre name="test">
     * PvmMalli PvmMalli = new PvmMalli(11,3,2003);
     * StringBuilder jono = new StringBuilder("12");
     * PvmMalli.parse(jono); PvmMalli.toString() === "12.3.2003"; jono.toString() === "";
     * jono = new StringBuilder("..2001");
     * PvmMalli.parse(jono); PvmMalli.toString() === "12.3.2001"; jono.toString() === "";
     * jono = new StringBuilder("..2009 14:30");
     * PvmMalli.parse(jono); PvmMalli.toString() === "12.3.2009"; jono.toString() === "14:30";
     * </pre>
     */
    public void parse(StringBuilder sb) {
        PvmMalliParse(sb);
    }


    // Lisätty saantimetodit:

    /**
     * @return päivän arvo
     * @example
     * <pre name="test">
     *   PvmMalli pv = new PvmMalli("22.2.2010");
     *   pv.getPv() === 22;
     * </pre>
     */
    public int getPv() {
        return pv;
    }


    /**
     * @return kuukauden arvo
     * @example
     * <pre name="test">
     *   PvmMalli pv = new PvmMalli("22.2.2010");
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
     *   PvmMalli pv = new PvmMalli("22.2.2010");
     *   pv.getVv() === 2010;
     * </pre>
     */
    public int getVv() {
        return vv;
    }


    /**
     * Verrataan päivämäärää olioon obj.  Jos o ei ole päivämäärä tai merkkijono
     * heiteään poikkeus.
     * @param obj olio johon verrataan, jonka toivotaan olevan Pv,2 tyyppiä.
     * @return -1 jos <  0 jos == ja 1 jos > kuin verrattava obj.
     * @throws ClassCastException jos luokka väärää tyyppiä
     * @example
     * <pre name="test">
     *   PvmMalli PvmMalli = new PvmMalli(12,3,2012);
     *   PvmMalli.compareTo(new Double(2)) === 0; #THROWS ClassCastException
     *   PvmMalli.compareTo("12.3.2012") === 0; 
     *   PvmMalli.compareTo(new StringBuilder("12.3.2012")) === 0;
     *   PvmMalli.compareTo(new StringBuffer("12.3.2012")) === 0;
     *   PvmMalli.compareTo(tanaan) === 1; 
     * </pre>
     */
    @Override
    public int compareTo(Object obj) {
        PvmMalli PvmMalli = null;
        if ( obj instanceof String )
            PvmMalli = new PvmMalli((String)obj);
        else if ( obj instanceof StringBuilder )
            PvmMalli = new PvmMalli(((StringBuilder)obj).toString());
        else if ( obj instanceof StringBuffer )
            PvmMalli = new PvmMalli(((StringBuffer)obj).toString());
        else if ( !(obj instanceof PvmMalli) ) throw new ClassCastException();
        if ( PvmMalli == null ) PvmMalli = (PvmMalli)obj;
        return compareTo(PvmMalli);
    }


    /**
     * Verrataan miten kaksi päivämäärää suhtautuu toisiinsa.
     * @param PvmMalli2 toinen verrattava
     * @return -1 jos  < PvmMalli2, 0 jos yhtäsuuret ja 1 jos  > PvmMalli2
     * 
     * @example
     * <pre name="test">
     *  maalis97.compareTo(tammi2012) === -1;  // ero vuodessa
     *  tammi2012.compareTo(maalis97) ===  1;
     *  tammi2012.compareTo(tanaan)   === -1;    // ero kuukaudessa
     *  tanaan.compareTo(tammi2012)   ===  1;
     *  helmi2012.compareTo(tanaan)   === -1;    // ero päivässä
     *  tanaan.compareTo(helmi2012)   ===  1;
     *  tanaan.compareTo(tanaan)      ===  0;
     * </pre>
     */
    public int compareTo(PvmMalli PvmMalli2) {
        if ( getVv() < PvmMalli2.getVv() ) return -1;
        if ( getVv() > PvmMalli2.getVv() ) return 1;
        if ( getKk() < PvmMalli2.getKk() ) return -1;
        if ( getKk() > PvmMalli2.getKk() ) return 1;
        if ( getPv() < PvmMalli2.getPv() ) return -1;
        if ( getPv() > PvmMalli2.getPv() ) return 1;
        return 0;
        //  return compareTo(this,PvmMalli2);       // Olisi helpoin jos on staattinen metodi
        //  return hashCode()-PvmMalli2.hashCode(); // ei palauta -1,0,1 mutta <0, 0, >0
    }


    /**
     * Verrataan miten kaksi päivämäärää suhtautuu toisiinsa.
     * @param PvmMalli1 ensimmäinen verratava
     * @param PvmMalli2 toinen verrattava
     * @return -1 jos PvmMalli1 < PvmMalli2, 0 jos yhtäsuuret ja 1 jos PvmMalli1 > PvmMalli2
     * 
     * @example
     * <pre name="test">
     * #STATICIMPORT
     *  compareTo(maalis97,tammi2012) === -1;  // ero vuodessa
     *  compareTo(tammi2012,maalis97) === 1;
     *  compareTo(tammi2012,tanaan)   === -1;    // ero kuukaudessa
     *  compareTo(tanaan,tammi2012)   === 1;
     *  compareTo(helmi2012,tanaan)   === -1;    // ero päivässä
     *  compareTo(tanaan,helmi2012)   === 1;
     *  compareTo(tanaan,tanaan)      === 0;
     * </pre>
     * @example
     * <pre name="test">
     * #STATICIMPORT
     *  PvmMalli pv1 = new PvmMalli(15,6,2013);
     *  PvmMalli pv2 = new PvmMalli(14,5,2014);
     *  PvmMalli pv3 = new PvmMalli(15,7,2014);
     *  PvmMalli pv4 = new PvmMalli(16,7,2014);
     *  PvmMalli pv5 = new PvmMalli(16,7,2014);
     *  PvmMalli pv6 = new PvmMalli(16,7,2012);
     *  compareTo(pv1,pv2) === -1;  // ero vuodessa
     *  compareTo(pv2,pv1) ===  1;
     *  compareTo(pv2,pv3) === -1;  // ero kuukaudessa
     *  compareTo(pv3,pv2) ===  1;
     *  compareTo(pv3,pv4) === -1;  // ero päivässä
     *  compareTo(pv4,pv3) ===  1;
     *  compareTo(pv4,pv5) ===  0;  // kaikki samoja
     *  compareTo(pv6,pv2) === -1;  // ero kuukaudessa, mutta vuodessa toisinpäin
     *  compareTo(pv2,pv6) ===  1;
     * </pre>
     */
    public static int compareTo(PvmMalli PvmMalli1, PvmMalli PvmMalli2) {
        if ( PvmMalli1.getVv() < PvmMalli2.getVv() ) return -1;
        if ( PvmMalli1.getVv() > PvmMalli2.getVv() ) return 1;
        if ( PvmMalli1.getKk() < PvmMalli2.getKk() ) return -1;
        if ( PvmMalli1.getKk() > PvmMalli2.getKk() ) return 1;
        if ( PvmMalli1.getPv() < PvmMalli2.getPv() ) return -1;
        if ( PvmMalli1.getPv() > PvmMalli2.getPv() ) return 1;
        return 0;
        //  return PvmMalli1.compareTo(PvmMalli2);  // Helpoin olisi
    }


    /**
     * Vertaa onko päivämäärä sama kuin toinen objekti.
     * Osaa verrata String ja StringBuilder-luokkiinkin
     * @param obj verrattava objekti
     * @return true jos sisällöltään samat, muuten false
     * @example
     * <pre name="test">
     * maalis97.equals(tammi2012)          === false;
     * tammi2012.equals(maalis97)          === false;
     * tanaan.equals(tanaan)               === true;
     * p23_2.equals(new PvmMalli("23.2.2012"))  === true;
     * p23_2.equals(p22_2)                 === false;
     * p23_2.equals(s23_2)                 === true;
     * p23_2.equals(s22_2)                 === false;
     * s23_2.equals(p23_2)                 === false; // String ei osaa verrata PvmMalli:ään
     * s23_2.equals(p22_2)                 === false; // String ei osaa verrata PvmMalli:ään
     * s23_2.equals(p23_2.toString())      === true;  // mutta osaa merkkijonoon
     * tanaan.equals(new Double(2))        === false; // PvmMalli ei osaa verrata muihin tyyppeihin
     * p23_2.equals(new StringBuilder("23.2.2012")) === true;
     * p23_2.equals(new StringBuffer("23.2.2012")) === true;
     * p23_2.equals(new StringBuilder("22.2.2012")) === false;
     * p23_2.equals(new StringBuffer("22.2.2012")) === false;
     * </pre>
     */
    @Override
    public boolean equals(Object obj) {
        if ( (obj instanceof String) ||
             (obj instanceof StringBuilder) ||
             (obj instanceof StringBuffer) ||
             (obj instanceof PvmMalli) ) return compareTo(obj) == 0;
        return false;
    }


    /**
     * @return päivämäärälle järjestysnumero
     * @example
     * <pre name="test">
     *   PvmMalli pv1 = new PvmMalli(1,1,2012);
     *   PvmMalli pv2 = new PvmMalli(2,1,2012);
     *   pv1.hashCode() === 2012*10000 + 100 + 1; 
     *   pv1.hashCode() === 20120101; 
     *   pv2.hashCode() === pv1.hashCode() + 1;
     *   PvmMalli pv3 = new PvmMalli(31,12,2011);
     *   pv3.hashCode() === 20111231; 
     * </pre>
     */
    @Override
    public int hashCode() {
        return 100*100*getVv() + 100*getKk() +  getPv();
    }


    /**
     * Testataan päivämäärä-luokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        PvmMalli PvmMalli = new PvmMalli();

        PvmMalli.parse("12.1.1995");
        System.out.println(PvmMalli);
        PvmMalli.parse("15.3");
        System.out.println(PvmMalli);
        PvmMalli.parse("14");
        System.out.println(PvmMalli.getPv());

        PvmMalli pv1 = new PvmMalli(1, 2), pv2 = new PvmMalli(3, 3);
        if ( compareTo(pv1, pv2) < 0 ) System.out.println(pv1 + " < " + pv2);
        if ( pv1.compareTo(pv2) != 0 ) System.out.println(pv1 + " != " + pv2);
    }
}

// BYCODEBEGIN

// BYCODEEND
