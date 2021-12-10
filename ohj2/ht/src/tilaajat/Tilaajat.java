package tilaajat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fi.jyu.mit.ohj2.WildChars;
import kanta.SailoException;

/**
 * Pitää yllä varsinaista tilaajarekisteriä, eli osaa lisätä ja poistaa tilaajan.
 * Lukee ja kirjoittaa tilaajat-tiedostoon, sekä osaa etsiä ja lajitella.
 * Avustajana Tilaaja.
 * @author Herman Lätti
 * @version Vaihe 7 24.7.2020
 *
 */
public class Tilaajat {
    
    private boolean muutettu = false;
    private String tiedostonPerusNimi = "tilaajat";
    
    private static final int MAX_TILAAJIA = 5;
    private int lkm;
    private Tilaaja[] alkiot = new Tilaaja[MAX_TILAAJIA];
    
    /**
     * Tilaajat oletusmuodostaja
     */
    public Tilaajat() {
        // Attribuuttien oma alustus riittää
    }
    
    /**
     * Lisää uuden tilaajan tietorakenteeseen. Ottaa tilaajan omistukseensa.
     * @param tilaaja Lisättävän tilaajan viite.
     * @example
     * <pre name="test">
     * Tilaajat tilaajat = new Tilaajat();
     * Tilaaja matti1 = new Tilaaja(), matti2 = new Tilaaja();
     * tilaajat.getLkm() === 0;
     * tilaajat.lisaa(matti1); tilaajat.getLkm() === 1;
     * tilaajat.lisaa(matti2); tilaajat.getLkm() === 2;
     * tilaajat.lisaa(matti1); tilaajat.getLkm() === 3;
     * tilaajat.anna(0) === matti1;
     * tilaajat.anna(1) === matti2;
     * tilaajat.anna(2) === matti1;
     * tilaajat.anna(1) == matti1 === false;
     * tilaajat.anna(1) == matti2 === true;
     * tilaajat.anna(3) === matti1; #THROWS IndexOutOfBoundsException
     * tilaajat.lisaa(matti1); tilaajat.getLkm() === 4;
     * tilaajat.lisaa(matti1); tilaajat.getLkm() === 5;
     * tilaajat.lisaa(matti1); tilaajat.getLkm() === 6;
     * </pre>
     */
    public void lisaa(Tilaaja tilaaja) {
        if (lkm >= alkiot.length) {
            Tilaaja[] alkiot2 = new Tilaaja[lkm * 2];
            for (int i = 0; i < alkiot.length; i++)
                alkiot2[i] = alkiot[i];
            alkiot = alkiot2;
        }
        alkiot[lkm] = tilaaja;
        lkm++;
        muutettu = true;
    }
    
    /**
     * Etsitään tilaajaa jolla on sama id. Jos löytyy niin korvataan tilaaja.
     * Jos ei löydy niin lisätään uutena tilaajana. Ottaa tilaajan omistukseensa.
     * @param tilaaja Lisättävän tai korvattavan tilaajan viite. Tietorakenne muuttuu omistajaksi.
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * Tilaajat tilaajat = new Tilaajat();
     * Tilaaja matti1 = new Tilaaja(), matti2 = new Tilaaja();
     * matti1.rekisteroi(); matti2.rekisteroi();
     * tilaajat.getLkm() === 0;
     * tilaajat.korvaaTaiLisaa(matti1); tilaajat.getLkm() === 1;
     * tilaajat.korvaaTaiLisaa(matti2); tilaajat.getLkm() === 2;
     * Tilaaja matti3 = matti1.clone();
     * matti1.anna(1) === "";
     * matti3.aseta(1, "matti3"); 
     * matti3.anna(1).equals(matti1.anna(1)) === false; // eri nimet
     * matti3.getTilaajaID() === matti1.getTilaajaID(); // sama tilaajaID
     * tilaajat.korvaaTaiLisaa(matti3); tilaajat.getLkm() === 2;
     * tilaajat.anna(0).anna(1) === "matti3";
     * </pre>
     */
    public void korvaaTaiLisaa(Tilaaja tilaaja) {
        int tid = tilaaja.getTilaajaID();
        for (int i = 0; i < getLkm(); i++) {
            if (alkiot[i].getTilaajaID() == tid) {
                alkiot[i] = tilaaja;
                muutettu = true;
                return;
            }
        }
        lisaa(tilaaja);
    }
    
    /**
     * Etsii tilajaan sijainnin tietorakenteessa tilaaja-id:n perusteella
     * @param tid Tilaaja-id, jonka perusteella etsitään
     * @return Tilaajan sijainti (indeksi) tietorakenteessa, -1 jos ei löydy
     * @example
     * <pre name="test">
     * Tilaajat tilaajat = new Tilaajat();
     * Tilaaja matti1 = new Tilaaja(), matti2 = new Tilaaja(), matti3 = new Tilaaja();
     * matti1.rekisteroi(); matti2.rekisteroi(); matti3.rekisteroi();
     * int tid = matti1.getTilaajaID();
     * tilaajat.lisaa(matti1); tilaajat.lisaa(matti2); tilaajat.lisaa(matti3);
     * tilaajat.etsiTilaajaID(tid) === 0;
     * tid + 1 == matti2.getTilaajaID() === true;
     * tilaajat.etsiTilaajaID(tid + 1) === 1;
     * tilaajat.etsiTilaajaID(tid + 2) === 2;
     * tilaajat.etsiTilaajaID(tid + 3) === -1;
     * </pre>
     */
    public int etsiTilaajaID(int tid) {
        for (int i = 0; i < getLkm(); i++)
            if (tid == alkiot[i].getTilaajaID()) return i;
        return -1;
    }
    
    /** 
     * Poistaa tilaajan, jolla on annettu tunnusluku
     * @param tid Poistettavan tilaajan tunnusluku
     * @example
     * <pre name="test">
     * Tilaajat tilaajat = new Tilaajat();
     * Tilaaja matti1 = new Tilaaja(), matti2 = new Tilaaja(), matti3 = new Tilaaja();
     * matti1.rekisteroi(); matti2.rekisteroi(); matti3.rekisteroi();
     * int tid = matti1.getTilaajaID();
     * tid + 1 == matti2.getTilaajaID() === true;
     * tid + 2 == matti3.getTilaajaID() === true;
     * tilaajat.lisaa(matti1); tilaajat.lisaa(matti2); tilaajat.lisaa(matti3);
     * tilaajat.getLkm() === 3;
     * tilaajat.etsiTilaajaID(tid) === 0;
     * tilaajat.etsiTilaajaID(tid + 1) === 1;
     * tilaajat.etsiTilaajaID(tid + 2) === 2;
     * tilaajat.poista(tid + 1); tilaajat.getLkm() === 2;
     * tilaajat.etsiTilaajaID(tid) === 0;
     * tilaajat.etsiTilaajaID(tid + 1) === -1;
     * tilaajat.etsiTilaajaID(tid + 2) === 1;
     * tilaajat.anna(1) === matti3;
     * tilaajat.poista(tid + 2); tilaajat.getLkm() === 1;
     * tilaajat.etsiTilaajaID(tid + 2) === -1;
     * tilaajat.anna(1) === matti3; #THROWS IndexOutOfBoundsException
     * </pre>
     */ 
    public void poista(int tid) { 
        int index = etsiTilaajaID(tid);
        if (index < 0) return;
        lkm--;
        for (int i = index; i < getLkm(); i++)
            alkiot[i] = alkiot[i + 1];
        alkiot[lkm] = null;
        muutettu = true;
    } 
    
    /**
     * Palauttaa viitteen tilaajaan, jonka indeksi on i.
     * @param i Indeksi, jonka tilaaja halutaan.
     * @return Viite tilaajaan, jonka indeksi on i.
     * @throws IndexOutOfBoundsException Jos i ei ole sallitulla alueella.
     * @example
     * <pre name="test">
     * Tilaajat tilaajat = new Tilaajat();
     * Tilaaja matti1 = new Tilaaja(), matti2 = new Tilaaja();
     * tilaajat.lisaa(matti1);
     * tilaajat.anna(0) === matti1;
     * tilaajat.anna(1) === matti2; #THROWS IndexOutOfBoundsException
     * tilaajat.lisaa(matti2);
     * tilaajat.anna(1) === matti2;
     * </pre>
     */
    public Tilaaja anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || getLkm() <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    /**
     * Lukee tilaajien tiedostosta
     * @param tied Tiedoston hakemisto
     * @throws SailoException Heittää poikkeuksen jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * #import kanta.SailoException;
     * #import java.io.File;
     * Tilaajat tilaajat = new Tilaajat();
     * Tilaaja matti1 = new Tilaaja(), matti2 = new Tilaaja();
     * Tilaaja matti3 = new Tilaaja();
     * matti1.taytaMattiTiedoilla(10);
     * matti2.taytaMattiTiedoilla(20);
     * matti3.taytaMattiTiedoilla(30);
     * String hakemisto = "testiakuankka";
     * String tiedNimi = hakemisto+"/tilaajat";
     * File ftied = new File(tiedNimi+".dat");
     * File dir = new File(hakemisto);
     * dir.mkdir();
     * ftied.delete();
     * 
     * tilaajat.lueTiedostosta(tiedNimi); #THROWS SailoException
     * tilaajat.lisaa(matti1);
     * tilaajat.lisaa(matti2);
     * tilaajat.anna(0).toString() === matti1.toString();
     * tilaajat.anna(1).toString() === matti2.toString();
     * tilaajat.anna(2).toString() === matti3.toString(); #THROWS IndexOutOfBoundsException
     * tilaajat.tallenna();
     * 
     * // Poistetaan vanhat postiosoitteet ja ladataan ne sitten tiedostosta
     * tilaajat = new Tilaajat();
     * tilaajat.anna(0).toString() === matti1.toString(); #THROWS IndexOutOfBoundsException
     * tilaajat.lueTiedostosta(tiedNimi);
     * tilaajat.anna(0).toString() === matti1.toString();
     * tilaajat.anna(1).toString() === matti2.toString();
     * tilaajat.anna(2).toString() === matti3.toString(); #THROWS IndexOutOfBoundsException
     * 
     * tilaajat.lisaa(matti3);
     * tilaajat.anna(2).toString() === matti3.toString();
     * tilaajat.tallenna();
     * 
     * ftied.delete() === true;
     * File fbak = new File(tiedNimi+".bak");
     * fbak.delete() === true;
     * dir.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String tied) throws SailoException {
        setTiedostonPerusNimi(tied);
        try (Scanner fi = new Scanner(new FileInputStream(new File(getTiedostonNimi()).getCanonicalPath()))) {
            String otsikot = fi.nextLine(); 
            if (otsikot == null) throw new SailoException("Otsikkotiedot puuttuvat");
            
            String rivi = "";
            while (fi.hasNext()) {
                rivi = fi.nextLine();
                rivi = rivi.trim();
                if ("".equals(rivi)) continue;
                Tilaaja tilaaja = new Tilaaja();
                tilaaja.parse(rivi);
                lisaa(tilaaja);
            }
            muutettu = false;
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch (IOException e1) {
            throw new SailoException("Tiedoston " + getTiedostonNimi() + " lukeminen ei onnistu");
        }
    }
    
    /**
     * Luetaan aikaisemmin annetun nimisestä tiedostosta
     * @throws SailoException jos tulee poikkeus
     */
    public void lueTiedostosta() throws SailoException {
        lueTiedostosta(getTiedostonPerusNimi());
    }

    /**
     * Tallentaa tilaajien tiedostoon. 
     * @throws SailoException Heittää poikkeuksen jos tallentaminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException, IOException
     * #import kanta.SailoException;
     * #import java.io.IOException;
     * #import java.io.File;
     * #import fi.jyu.mit.ohj2.VertaaTiedosto;
     * Tilaajat tilaajat = new Tilaajat();
     * Tilaaja matti1 = new Tilaaja(), matti2 = new Tilaaja();
     * Tilaaja matti3 = new Tilaaja();
     * matti1.taytaMattiTiedoilla(10);
     * matti2.taytaMattiTiedoilla(20);
     * matti3.taytaMattiTiedoilla(30);
     * String hakemisto = "testiakuankka";
     * String tiedNimi = hakemisto+"/tilaajat";
     * File ftied = new File(tiedNimi+".dat");
     * File dir = new File(hakemisto);
     * dir.mkdir();
     * ftied.delete();
     * 
     * tilaajat.lueTiedostosta(tiedNimi); #THROWS SailoException
     * tilaajat.lisaa(matti1);
     * tilaajat.lisaa(matti2);
     * tilaajat.anna(0).toString() === matti1.toString();
     * tilaajat.anna(1).toString() === matti2.toString();
     * tilaajat.tallenna();
     * 
     * String tulos = "tid|sukunimi etunimi|sähköposti|puhelin|katuosoite|postinumero|aloitti tilaamisen|maksettu\n" +
     *                  matti1.toString() + "\n" +
     *                  matti2.toString();
     * 
     * VertaaTiedosto.vertaaFileString(tiedNimi+".dat", tulos) === null;
     * 
     * tilaajat.lisaa(matti3);
     * tilaajat.anna(2).toString() === matti3.toString();
     * tilaajat.tallenna();
     * 
     * tulos = "tid|sukunimi etunimi|sähköposti|puhelin|katuosoite|postinumero|aloitti tilaamisen|maksettu\n" +
     *                  matti1.toString() + "\n" +
     *                  matti2.toString() + "\n" +
     *                  matti3.toString();
     * 
     * VertaaTiedosto.vertaaFileString(tiedNimi+".dat", tulos) === null;
     * 
     * ftied.delete() === true;
     * File fbak = new File(tiedNimi+".bak");
     * fbak.delete() === true;
     * dir.delete() === true;
     * </pre>
     */
    public void tallenna() throws SailoException {
        if (!muutettu) return; // Jos ei olla muutettu niin ei tarvitse tallentaa
        
        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete();
        ftied.renameTo(fbak);
        
        try (PrintStream fo = new PrintStream (new FileOutputStream(ftied.getCanonicalPath()))) {
            fo.println("tid|sukunimi etunimi|sähköposti|puhelin|katuosoite|postinumero|aloitti tilaamisen|maksettu");
            for (int i = 0; i < getLkm(); i++) { 
                Tilaaja tilaaja = anna(i);
                fo.println(tilaaja.toString());
            }
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch (IOException ex) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittaminen ei onnistu");
        }
        
        muutettu = false;
    }
    
    /**
     * Palauttaa tilaajien lukumäärän
     * @return Tilaajien lukumäärä
     * @example
     * <pre name="test">
     * Tilaajat tilaajat = new Tilaajat();
     * tilaajat.getLkm() === 0;
     * Tilaaja matti = new Tilaaja(); matti.rekisteroi();
     * tilaajat.lisaa(matti);
     * tilaajat.getLkm() === 1;
     * </pre>
     */
    public int getLkm() {
        return lkm;
    }
    
    /**
     * Saantimetodi tallennuksessa käytettävälle tiedostonnimelle
     * @return Palauttaa tallennustiedoston nimen
     * @example
     * <pre name="test">
     * Tilaajat tilaajat = new Tilaajat();
     * tilaajat.getTiedostonPerusNimi() === "tilaajat";
     * </pre>
     */
    public String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }
    
    /**
     * Asettaa tiedoston perusnimen ilman tarkenninta
     * @param nimi Tallennustiedoston perusnimi
     * @example
     * <pre name="test">
     * Tilaajat tilaajat = new Tilaajat();
     * tilaajat.getTiedostonPerusNimi() === "tilaajat";
     * tilaajat.setTiedostonPerusNimi("testi");
     * tilaajat.getTiedostonPerusNimi() === "testi";
     * </pre>
     */
    public void setTiedostonPerusNimi(String nimi) {
        tiedostonPerusNimi = nimi;
    }
    
    /**
     * Saantimetodi tiedoston nimelle, jota käytetään tallennukseen (tiedostopäätteellä)
     * @return Palauttaa tallennustiedoston nimen tiedostopäätteellä
     * @example
     * <pre name="test">
     * Tilaajat tilaajat = new Tilaajat();
     * tilaajat.getTiedostonNimi() === "tilaajat.dat";
     * tilaajat.setTiedostonPerusNimi("testi");
     * tilaajat.getTiedostonNimi() === "testi.dat";
     * </pre>
     */
    public String getTiedostonNimi() {
        return getTiedostonPerusNimi() + ".dat";
    }


    /**
     * Saantimetodi varakopiotiedoston nimelle
     * @return Palauttaa varakopiotiedoston nimen
     * @example
     * <pre name="test">
     * Tilaajat tilaajat = new Tilaajat();
     * tilaajat.getBakNimi() === "tilaajat.bak";
     * tilaajat.setTiedostonPerusNimi("testi");
     * tilaajat.getBakNimi() === "testi.bak";
     * </pre>
     */
    public String getBakNimi() {
        return tiedostonPerusNimi + ".bak";
    }
    
    /**
     * Haetaan kaikki postiosoitteen tilaajat
     * @param postinumero Postiosoitteen postinumero, jolle tilaajia haetaan
     * @return Tietorakenne, jossa viitteet löydettyihin tilaajiin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     * Tilaajat tilaajat = new Tilaajat();
     * Tilaaja matti1 = new Tilaaja(10); tilaajat.lisaa(matti1);
     * Tilaaja matti2 = new Tilaaja(20); tilaajat.lisaa(matti2);
     * Tilaaja matti3 = new Tilaaja(30); tilaajat.lisaa(matti3);
     * Tilaaja matti4 = new Tilaaja(10); tilaajat.lisaa(matti4);
     * Tilaaja matti5 = new Tilaaja(10); tilaajat.lisaa(matti5);
     * Tilaaja matti6 = new Tilaaja(20); tilaajat.lisaa(matti6);
     *  
     * List<Tilaaja> loytyneet;
     * loytyneet = tilaajat.annaTilaajat(1);
     * loytyneet.size() === 0; 
     * loytyneet = tilaajat.annaTilaajat(20);
     * loytyneet.size() === 2; 
     * loytyneet.get(0) == matti2 === true;
     * loytyneet.get(1) == matti6 === true;
     * loytyneet = tilaajat.annaTilaajat(30);
     * loytyneet.size() === 1; 
     * loytyneet.get(0) == matti3 === true;
     * </pre>
     */
    public List<Tilaaja> annaTilaajat(int postinumero) {
        List<Tilaaja> loydetyt = new ArrayList<Tilaaja>();
        for (int i = 0; i < getLkm(); i++)
            if (alkiot[i].getPostinumero() == postinumero) loydetyt.add(alkiot[i]);
        return loydetyt;
    }
    
    /**
     * 
     * Palauttaa tietorakenteessa hakuehtoon vastaavien tilaajien viitteet
     * @param hakuehto Hakuehto
     * @param k Etsittävän kentän indeksi
     * @return Tietorakenne löytyneistä tilaajista
     * @example
     * <pre name="test">
     * Tilaajat tilaajat = new Tilaajat();
     * Tilaaja tilaaja1 = new Tilaaja(); tilaaja1.parse(" 1|Pukki Joulu      ||||99999||1"); 
     * Tilaaja tilaaja2 = new Tilaaja(); tilaaja2.parse(" 3|Tietäväinen Teijo||||40014||1");
     * Tilaaja tilaaja3 = new Tilaaja(); tilaaja3.parse(" 4|Toljanteri Tonttu||||99999||0");
     * Tilaaja tilaaja4 = new Tilaaja(); tilaaja4.parse(" 9|Meikäläinen Matti||||00010||1");
     * Tilaaja tilaaja5 = new Tilaaja(); tilaaja5.parse("13|Tietäväinen Tuija||||40014||0");
     * tilaajat.lisaa(tilaaja1); tilaajat.lisaa(tilaaja2); tilaajat.lisaa(tilaaja3);
     * tilaajat.lisaa(tilaaja4); tilaajat.lisaa(tilaaja5);
     * 
     * List<Tilaaja> loytyneet;  
     * loytyneet = tilaajat.etsi("*k*", 1); // etsi nimen perusteella
     * loytyneet.size() === 2;  
     * loytyneet.get(0) == tilaaja1 === true;  
     * loytyneet.get(1) == tilaaja4 === true;  
     * 
     * loytyneet = tilaajat.etsi("*0*", 7); // etsi maksettu perusteella
     * loytyneet.size() === 2;  
     * loytyneet.get(0) == tilaaja3 === true;  
     * loytyneet.get(1) == tilaaja5 === true; 
     * 
     * loytyneet = tilaajat.etsi(null, -1);  
     * loytyneet.size() === 5;  
     * </pre>
     */
    public List<Tilaaja> etsi(String hakuehto, int k) {
        String ehto = "*";
        if (hakuehto != null && hakuehto.length() > 0)
            ehto = hakuehto;
        
        List<Tilaaja> loydetyt = new ArrayList<Tilaaja>();
        for (int i = 0; i < getLkm(); i++) 
            if (WildChars.onkoSamat(alkiot[i].anna(k), ehto))
                loydetyt.add(alkiot[i]);
        
        return loydetyt;
    }
    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Tilaajat tilaajat = new Tilaajat();
        Tilaaja matti = new Tilaaja(), matti2 = new Tilaaja(), matti3 = new Tilaaja();
        matti.rekisteroi();
        matti.taytaMattiTiedoilla(10);
        matti2.rekisteroi();
        matti2.taytaMattiTiedoilla(20);
        matti3.rekisteroi();
        matti3.taytaMattiTiedoilla(10);
        
        tilaajat.lisaa(matti);
        tilaajat.lisaa(matti2);
        tilaajat.lisaa(matti3);

        System.out.println("========== Tilaajat testi ==========");   
        for (int i = 0; i < tilaajat.getLkm(); i++) {
            Tilaaja tilaaja = tilaajat.anna(i);
            System.out.println("Tilaajan indeksi: " + i);
            tilaaja.tulosta(System.out);
        }
        System.out.println("====================");
        System.out.println("Tilaajat joiden postiosoite on 00010:");
        List<Tilaaja> tilaajat10 = tilaajat.annaTilaajat(10);
        for (Tilaaja til: tilaajat10) til.tulosta(System.out);
    }
}