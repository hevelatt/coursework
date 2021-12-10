package demo.d5;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Alustava luokka päivämäärää varten
 * @author Vesa Lappalainen
 * @version 1.0, 07.02.2003
 * @version 1.1, 09.02.2008
 */
public class Pvm {
    private int pv, kk, vv;


    /**
     * Palauttaa tämänhetkisen päiväyksen.
     * TODO: muutettava toimimaan oikein.
     */
    public void paivays() {
        pv = 30;
        kk = 1;
        vv = 2012;
    }


    /**
     * Alustetaan päivämäärä. 0-arvot eivät muuta vastaavaa attribuuttia
     * TODO: oikeellisuustarkitukset
     * @param ipv päivän alustus
     * @param ikk kuukauden alustus
     * @param iivv vuoden alustus
     */
    public final void alusta(final int ipv, final int ikk, final int iivv) {
        if (ipv > 0)        this.pv = ipv;
        if (ikk > 0)        this.kk = ikk;
        if (iivv > 0)        this.vv = iivv;
        if (this.vv < 50)  this.vv += 2000;
        if (this.vv < 100) this.vv += 1900;
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
     * Palautetaan päiväys merkkijonona
     * @return päiväys merkkijonoja
     * @example
     * <pre name="test">
     * Pvm pvm = new Pvm(10,2,2008);
     * pvm.toString() === "10.2.2008";
     * </pre>
     */
    @Override
    public String toString() {
        return pv + "." + kk + "." + vv;
    }


    /**
     * Alustetaan päivän arvon merkkijonosta
     * @param s jono josta pvm otetaan
     * @example
     * <pre name="test">
     * Pvm pvm = new Pvm();
     * pvm.parse("11.05.02");  pvm.toString() === "11.5.2002";
     * pvm.parse("11.05.97");  pvm.toString() === "11.5.1997";
     * pvm.parse("17.3");      pvm.toString() === "17.3.1997";
     * pvm.parse("19");        pvm.toString() === "19.3.1997";
     * </pre>
     */
    public void parse(String s) {
        StringBuffer sb = new StringBuffer(s);
        int p = Mjonot.erota(sb, '.', 0);
        int k = Mjonot.erota(sb, '.', 0);
        int v = Mjonot.erota(sb, '.', 0);
        alusta(p, k, v);
    }

    // BYCODEBEGIN
    /**
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Pvm pvm = new Pvm();
        pvm.parse("3.4.2011");
        System.out.println(pvm.getPv()); // tulostaa 3
        System.out.println(pvm.getKk()); // tulostaa 4
        System.out.println(pvm.getVv()); // tulostaa 2011
    }
    
    /**
     * Päivämäärän saantimetodi
     * @return Palauttaa päivämäärän
     * @example
     * <pre name="test">
     * Pvm pvm = new Pvm();
     * pvm.getPv() === 30;
     * pvm.parse("3.4");
     * pvm.getPv() === 3;
     * </pre>
     */
    public int getPv() {
        return pv;
    }
    
    /**
     * Kuukauden saantimetodi
     * @return Palauttaa kuukauden
     * @example
     * <pre name="test">
     * Pvm pvm = new Pvm();
     * pvm.getKk() === 1;
     * pvm.parse("3.4");
     * pvm.getKk() === 4;
     * </pre>
     */
    public int getKk() {
        return kk;
    }
    
    /**
     * Vuoden saantimetodi
     * @return Palauttaa vuoden
     * @example
     * <pre name="test">
     * Pvm pvm = new Pvm();
     * pvm.getVv() === 2012;
     * pvm.parse("3.4");
     * pvm.getVv() === 2012;
     * pvm.parse("3.4.1");
     * pvm.getVv() === 2001;
     * </pre>
     */
    public int getVv() {
        return vv;
    }
// BYCODEEND
}


