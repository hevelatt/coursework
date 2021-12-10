package demo.d5;

import java.io.*;

/**
 * Luokka, joka pitää kirjaa linjurin matkustajista
 * @author Herman
 * @version 15.6.2020
 *
 */
public class LinjaAuto {
    
    private int paikat;
    private int vapaana;
    
    /**
     * Linja-auton muodostaja
     * @param paikat Linja-auton matkustajakapasiteetti
     * @example
     * <pre name="test">
     * LinjaAuto linkki = new LinjaAuto(42);
     * linkki.getTilaa() === 42;
     * </pre>
     */
    public LinjaAuto(int paikat) {
        this.paikat = paikat;
        this.vapaana = paikat;
    }
    
    /**
     * Oletusmuodostaja alustaa linja-auton 0-paikkaisena
     * @example
     * <pre name="test">
     * LinjaAuto linkki = new LinjaAuto();
     * linkki.getTilaa() === 0;
     * </pre>
     */
    public LinjaAuto() {
        // paikat = 0; vapaana = 0;
    }
    
    /**
     * Tulostaa linja-auton tiedot parametrina tuotuun tietovirtaan
     * @param os Tietovirta, johon tiedot tulostetaan
     * @example
     * <pre name="test">
     * #import java.io.*;
     * ByteArrayOutputStream byteoutput = new ByteArrayOutputStream();
     * LinjaAuto linkki = new LinjaAuto(42);
     * linkki.tulosta(byteoutput);
     * byteoutput.toString() =R= "42,0,42\\s*"
     * </pre>
     */
    public void tulosta(OutputStream os) {
        PrintStream out = new PrintStream(os);
        out.println(paikat + "," + (paikat - vapaana) + "," + vapaana);
    }
    
    /**
     * Saantimetodi linja-autossa olevalle tilalle
     * @return Palauttaa kuinka paljon tilaa linja-autossa on
     * @example
     * <pre name="test">
     * LinjaAuto linkki = new LinjaAuto(42);
     * linkki.getTilaa() === 42;
     * linkki.lisaa(5);
     * linkki.getTilaa() === 37;
     * </pre>
     */
    public int getTilaa() {
        return vapaana;
    }
    
    /**
     * Kertoo, onko linja-autossa tilaa
     * @return True jos on tilaa, false jos ei
     * @example
     * <pre name="test">
     * LinjaAuto linkki = new LinjaAuto(42);
     * linkki.tilaa() === true;
     * linkki.lisaa(50);
     * linkki.tilaa() === false;
     * </pre>
     */
    public boolean tilaa() {
        if (vapaana > 0) return true;
        return false;
    }
    
    /**
     * Lisää linja-autoon matkustajia
     * @param matkustajat Kuinka monta matkustajaa lisätään
     * @return Palauttaa kuinka monta ei mahtunut linja-autoon
     * @example
     * <pre name="test">
     * LinjaAuto linkki = new LinjaAuto(42);
     * int yli = linkki.lisaa(50);
     * linkki.getTilaa() === 0;
     * yli === 8;
     * int ali = linkki.lisaa(-50);
     * linkki.getTilaa() === 42;
     * ali === -8;
     * </pre>
     */
    public int lisaa(int matkustajat) {
        vapaana -= matkustajat;
        return kapasiteetti();
    }
    
    /**
     * Poistaa linja-autosta matkustajia
     * @param matkustajat Kuinka monta matkustajaa poistetaan
     * @return Palauttaa kuinka paljon poistettiin kapasiteetin yli
     * @example
     * <pre name="test">
     * LinjaAuto linkki = new LinjaAuto(42);
     * int ali = linkki.vahenna(50);
     * linkki.getTilaa() === 42;
     * ali === -50;
     * linkki.lisaa(30);
     * linkki.vahenna(8);
     * linkki.getTilaa() === 20;
     * int yli = linkki.vahenna(-30);
     * linkki.getTilaa() === 0;
     * yli === 10;
     * </pre>
     */
    public int vahenna(int matkustajat) {
        vapaana += matkustajat;
        return kapasiteetti();
    }
    
    /**
     * Suhteuttaa vapaana olevien paikkojen määrän kapasiteettiin, testit ks lisaa() ja vahenna()
     * @return Palauttaa kuinka paljon matkustajat ylittävät kapasiteetin
     */
    private int kapasiteetti() {
        int ylim = 0;
 
        if (vapaana < 0) {
            ylim = -vapaana;
            vapaana = 0;
        }
        if (vapaana > paikat) {
            ylim = -(vapaana - paikat);
            vapaana = paikat;
        }
        return ylim;
    }
    
    /**
     * @param args ei käytössä
     * @author vesal
     */
    public static void main(String[] args) {
        LinjaAuto pikkubussi = new LinjaAuto(10);
        LinjaAuto isobussi = new LinjaAuto(45);
        pikkubussi.lisaa(4);    pikkubussi.tulosta(System.out); // 10,4,6
        isobussi.lisaa(30);     isobussi.tulosta(System.out);   // 45,30,15
        int yli = pikkubussi.lisaa(15);
        isobussi.lisaa(yli);
        pikkubussi.tulosta(System.out);                         // 10,10,0
        isobussi.tulosta(System.out);                           // 45,39,6
        if ( pikkubussi.getTilaa() > 0 )
            System.out.println("Pieneen bussiin mahtuu!");   // ei tulosta
        if ( isobussi.tilaa() )
            System.out.println("Isoon bussiin mahtuu!");     // tulostaa
        int vajaa = pikkubussi.vahenna(12);                  // vajaa = -2
        if ( vajaa < 0 )
            System.out.println("Pikkubussissa ei edes ole näin montaa!");
        pikkubussi.tulosta(System.out);                         // 10,0,10
   }
    
    /*
    public static void main(String args[]) {
        LinjaAuto linkki = new LinjaAuto(40);
        linkki.tulosta(System.out); // 40 40
        linkki.lisaa(12);
        linkki.tulosta(System.out); // 40 28
        linkki.vahenna(5);
        linkki.tulosta(System.out); // 40 33
        linkki.vahenna(50);
        linkki.tulosta(System.out); // 40 40
        linkki.lisaa(200);
        linkki.tulosta(System.out); // 40 0
        
        LinjaAuto nysse = new LinjaAuto();
        nysse.tulosta(System.out); // 0 0
        nysse.lisaa(5);
        nysse.tulosta(System.out); // 0 0
        nysse.vahenna(6);
        nysse.tulosta(System.out); // 0 0

        linkki.lisaa(-5);
        linkki.tulosta(System.out); // 40 5
        linkki.lisaa(-50);
        linkki.tulosta(System.out); // 40 40
        linkki.vahenna(-500);
        linkki.tulosta(System.out); // 40 0
    }
    */
}
