package tilaajat;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;
import kanta.PostinumeroTarkistus;

/**
 * Tietää tilaajan kentät (tiedot): nimi, sähköposti, puhelin, katuosoite, postinumero, aloitti tilaamisen, maksettu.
 * Osaa tarkistaa tietyn kentän oikeellisuuden, osaa muuttaa "9|Meikäläinen Matti|...|" -merkkijonon tilaajan tiedoiksi (parse).
 * Osaa antaa merkkijonona i:n kentän tiedot ja laittaa merkkijonon i:neksi kentäksi.
 * @author Herman Lätti
 * @version Vaihe 7 24.7.2020
 *
 */
public class Tilaaja implements Cloneable {

    private int tilaajaID;
    private String nimi = "";
    private String email = "";
    private String puhelin = "";
    private String katuosoite = "";
    private int postinumero;
    private String aloittiTilaamisen = "";
    private boolean maksettu = false;
    
    private static int seuraavaNro = 1;
    
    /**
     * Tilaajan oletusmuodostaja, postinumero 00000
     */
    public Tilaaja() {
        // Attribuuttien oma alustus riittää
    }
    
    /**
     * Alustetaan tietyn postiosoitteen tilaaja
     * @param postinumero Postiosoitteen postinumero
     */
    public Tilaaja(int postinumero) {
        this.postinumero = postinumero;
    }
    
    /**
     * Palauttaa tilaajan tunnusnumeron
     * @return Tilaajan tunnusnumero
     * @example
     * <pre name="test">
     * Tilaaja matti = new Tilaaja();
     * matti.getTilaajaID() === 0;
     * </pre>
     */
    public int getTilaajaID() {
        return tilaajaID;
    }
    
    /**
     * Postinumeron saantimetodi: mille postiosoitteelle tilaaja kuuluu
     * @return Palauttaa tilaajan postinumeron
     * @example
     * <pre name="test">
     * Tilaaja matti = new Tilaaja();
     * matti.getPostinumero() === 0;
     * matti = new Tilaaja(10);
     * matti.getPostinumero() === 10;
     * </pre>
     */
    public int getPostinumero() {
        return postinumero;
    }
    
    /**
     * Palauttaa tilaajan kenttien lukumäärän
     * @return Tilaajan kenttien lukumäärä
     */
    public int getKenttia() {
        return 8;
    }
    
    /**
     * Palauttaa ensimmäisen mielekkään kentän indeksin
     * @return Tilaajan ensimmäinen mielekäs kenttä
     */
    public int getEnsimmainen() {
        return 1;
    }
    
    /**
     * @param k Minkä kentän sisältö halutaan
     * @return Valitun kentän sisältö
     * @example
     * <pre name="test">
     * Tilaaja matti = new Tilaaja();
     * matti.anna(0) === "0";
     * matti.taytaMattiTiedoilla(10);
     * matti.anna(1) =R= "Meikäläinen Matti .*";
     * matti.anna(2) === "matti.meikalainen@gmail.com";
     * matti.anna(5) === "00010";
     * matti.anna(7) === "1";
     * matti.anna(9) === "???";
     * </pre>
     */
    public String anna(int k) {
        switch (k) {
            case 0:
                return "" + getTilaajaID();
            case 1:
                return nimi;
            case 2:
                return email;
            case 3:
                return puhelin;
            case 4:
                return katuosoite;
            case 5:
                return PostinumeroTarkistus.postinumeroJonona(getPostinumero());
            case 6:
                return aloittiTilaamisen;
            case 7:
                return "" + (maksettu ? 1 : 0);
            default:
                return "???";
        }
    }
    
    /**
     * Asettaa tilaaja id:n ja varmistaa että seuraava numero on suurempi kuin tähän mennessä suurin
     * @param tid Asetettava tilaaja id
     */
    public void setTilaajaID(int tid) {
        tilaajaID = tid;
        if (tilaajaID >= seuraavaNro) seuraavaNro = tilaajaID + 1;
    }
    
    /**
     * Asettaa k:n kentän arvoksi paramterina tuodun merkkijonon. 
     * Tyhjän asettaminen tulkitaan vanhan tiedon säilyttämiseksi.
     * @param k Kuinka monennen kentän arvo asetetaan
     * @param jono Jono, joka asetetaan kentän arvoksi
     * @return null jos asettaminen onnistuu, muuten vastaava virheilmoitus
     * @example
     * <pre name="test">
     * Tilaaja tilaaja = new Tilaaja();
     * tilaaja.aseta(0, "50") === null; tilaaja.anna(0) === "50";
     * tilaaja.aseta(0, "viisi") === null; tilaaja.anna(0) === "50";
     * tilaaja.aseta(1, "Matti") === null; tilaaja.anna(1) === "Matti";
     * tilaaja.aseta(1, "  ") === null; tilaaja.anna(1) === "Matti";
     * tilaaja.aseta(2, "kääk") === "email: väärä muoto";
     * tilaaja.aseta(2, "kaak@gmail.com") === null;
     * tilaaja.aseta(3, "kääk") === "puhelinnumero: vain numeroita";
     * tilaaja.aseta(3, "+358123456789") === null;
     * tilaaja.aseta(5, "0050") === "postinumero: liian lyhyt";
     * tilaaja.aseta(5, "000050") === "postinumero: liian pitkä";
     * tilaaja.aseta(5, "a0050") === "postinumero: vain numeroita";
     * tilaaja.aseta(6, "1990-01-01") === null;
     * tilaaja.aseta(6, "90-1-1") === "aloitti tilaamisen: muotoa VVVV-KK-PP";
     * tilaaja.aseta(7, "kääk") === "maksettu: 0 tai 1";
     * tilaaja.aseta(7, "1") === null; tilaaja.anna(7) === "1";
     * tilaaja.aseta(7, "0") === null; tilaaja.anna(7) === "0";
     * </pre>
     */
    public String aseta(int k, String jono) {
        String tjono = jono.trim();
        if (tjono.length() <= 0) return null;
        switch (k) {
        case 0:
            setTilaajaID(Mjonot.erotaInt(tjono, getTilaajaID())); // Jos ei pystytä erottamaan niin käytetään oletuksena vanhaa
            return null;
        case 1:
            nimi = tjono;
            return null;
        case 2:
            if (!tjono.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))
                return "email: väärä muoto";
            email = tjono;
            return null;
        case 3:
            if (!tjono.matches("[0123456789+ ]+")) 
                return "puhelinnumero: vain numeroita";
            puhelin = tjono;
            return null;
        case 4:
            katuosoite = tjono;
            return null;
        case 5:
            String virhe = PostinumeroTarkistus.tarkista(tjono);
            if (virhe != null) return "postinumero: " + virhe;
            postinumero = PostinumeroTarkistus.parsePostinumero(tjono);
            return null;
        case 6:
            if (!tjono.matches("[0123456789]{4}-[0123456789]{2}-[0123456789]{2}"))
                return "aloitti tilaamisen: muotoa VVVV-KK-PP";
            aloittiTilaamisen = tjono;
            return null;
        case 7:
            int imaksettu = Mjonot.erotaInt(tjono, -1);
            if (imaksettu != 0 && imaksettu != 1)
                return "maksettu: 0 tai 1";
            maksettu = (imaksettu == 1);
            return null;
        default:
            return null;
        }
    }
    
    /**
     * Palauttaa k:nnen kentän sisältöä vastaavan kysymyksen
     * @param k Kuinka monennen kentän kysymys palautetaan
     * @return Kenttää k vastaava kysymys
     */
    public String getKysymys(int k) {
        switch (k) {
        case 0: return "tid";
        case 1: return "nimi";
        case 2: return "sähköposti";
        case 3: return "puhelin";
        case 4: return "katuosoite";
        case 5: return "postinumero";
        case 6: return "aloitti tilaamisen";
        case 7: return "maksettu";
        default: return "???";
        }
    }
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot tilaajalle
     * @param ipostinumero Viite postiosoitteeseen, jonka tilaajasta kyse
     */
    public void taytaMattiTiedoilla(int ipostinumero) {
        nimi = "Meikäläinen Matti " + PostinumeroTarkistus.rand(1000, 9999);
        email = "matti.meikalainen@gmail.com";
        puhelin = "2507899879";
        katuosoite = "Pihkakuja 5";
        postinumero = ipostinumero;
        aloittiTilaamisen = "1970-02-30";
        maksettu = true;
    }
    
    /**
     * Tulostetaan tilaajan tiedot
     * @param out Tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(getTilaajaID() + " " + nimi + " " + email + " " + puhelin);
        out.println(katuosoite + " " + PostinumeroTarkistus.postinumeroJonona(getPostinumero()));
        out.print("Aloitti tilaamisen " + aloittiTilaamisen + " Tilausmaksu ");
        if (maksettu) out.println("maksettu");
        else out.println("ei maksettu");
    }
    
    /**
     * Tulostetaan tilaajan tiedot
     * @param os Tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    /**
     * Antaa tilaajalle seuraavan rekisterinumeron
     * @return Tilaajan uusi tunnusnumero
     * @example
     * <pre name="test">
     * Tilaaja matti1 = new Tilaaja();
     * matti1.getTilaajaID() === 0;
     * matti1.rekisteroi();
     * Tilaaja matti2 = new Tilaaja();
     * matti2.rekisteroi();
     * int n1 = matti1.getTilaajaID();
     * int n2 = matti2.getTilaajaID();
     * n1 === n2 - 1;
     * </pre>
     */
    public int rekisteroi() {
        tilaajaID = seuraavaNro;
        seuraavaNro++;
        return tilaajaID;
    }
    
    /**
     * Palauttaa tilaajan tiedot merkkijonona, jonka voi tallettaa tiedostoon
     * @return Tilaajan tiedot '|' -erotellussa merkkijonossa
     * @example
     * <pre name="test">
     * Tilaaja tilaaja = new Tilaaja();
     * tilaaja.parse("  9  | Meikäläinen Matti |matti.meikalainen@gmail.com|2507899879|Pihkakuja 5|00010|1970-02-30|1");
     * tilaaja.toString()    === "9|Meikäläinen Matti|matti.meikalainen@gmail.com|2507899879|Pihkakuja 5|00010|1970-02-30|1";
     * </pre>  
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String ero = "";
        String erotin = "|";
        for (int k = 0; k < getKenttia(); k++) {
            sb.append(ero);
            sb.append(anna(k));
            ero = erotin;
        }
        return sb.toString();
    }

    /**
     * Selvittää tilaajan tiedot '|' -erotellusta merkkijonosta
     * @param rivi Merkkijono, josta tilaajan tiedot erotetaan
     * @example
     * <pre name="test">
     * Tilaaja tilaaja = new Tilaaja();
     * tilaaja.parse("  9  | Meikäläinen Matti |matti.meikalainen@gmail.com|2507899879|Pihkakuja 5|00010|1970-02-30|1");
     * tilaaja.getTilaajaID() === 9;
     * tilaaja.toString() === "9|Meikäläinen Matti|matti.meikalainen@gmail.com|2507899879|Pihkakuja 5|00010|1970-02-30|1";
     * tilaaja.parse("|||||||0");
     * tilaaja.toString() === "9|Meikäläinen Matti|matti.meikalainen@gmail.com|2507899879|Pihkakuja 5|00010|1970-02-30|0";
     * </pre>
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        for (int k = 0; k < getKenttia(); k++) {
            aseta(k, Mjonot.erota(sb, '|'));
        }
    }
    
    /**
     * Tehdään identtinen klooni tilaajasta
     * @return Object kloonattu jäsen
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * Tilaaja tilaaja = new Tilaaja();
     * tilaaja.parse("9|Meikäläinen Matti|matti.meikalainen@gmail.com");
     * Tilaaja kopio = tilaaja.clone();
     * kopio.toString() === tilaaja.toString(); // kopion sisältö on sama
     * tilaaja.parse("20|Käkelä Kia|kiak@yahoo.com");
     * kopio.toString().equals(tilaaja.toString()) === false; // kopio ei muutu kun alkuperäistä muutetaan
     * </pre>
     */
    @Override
    public Tilaaja clone() throws CloneNotSupportedException {
        return (Tilaaja) super.clone(); // riittää, koska attribuutit eivät ole mutable viitteitä
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Tilaaja matti = new Tilaaja();
        Tilaaja matti2 = new Tilaaja();
        
        matti.rekisteroi();
        matti2.rekisteroi(); // eri tilaajaID
        
        matti.tulosta(System.out);
        matti.taytaMattiTiedoilla(10);
        matti.tulosta(System.out);
        
        matti2.tulosta(System.out);
        matti2.taytaMattiTiedoilla(20);
        matti2.tulosta(System.out);
    }
}