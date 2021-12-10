package tilaajat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kanta.SailoException;

/**
 * Huolehtii Tilaajat ja Postiosoitteet -luokkien välisestä yhteistyöstä 
 * ja välittää näitä tietoja pyydettäessä.
 * Lukee ja kirjoittaa tuotteen tiedostoon pyytämällä apua avustajiltaan.
 * Avustajana Tilaajat, Postiosoitteet, Tilaaja ja Postiosoite.
 * @author Herman Lätti
 * @version Vaihe 7 24.7.2020
 *
 */
public class Tuote {

    Postiosoitteet postiosoitteet = new Postiosoitteet();
    Tilaajat tilaajat = new Tilaajat();
    
    /**
     * Tuote oletusmuodostaja
     */
    public Tuote() {
        // Attribuuttien oma alustus riittää
    }
    
    /**
     * Saantimetodi tuotteen postiosoitteiden lukumäärälle
     * @return Palauttaa, kuinka monta postiosoitetta tuotteella on
     * @example
     * <pre name="test">
     * Tuote akuankka = new Tuote();
     * akuankka.getPostiosoitteita() === 0;
     * akuankka.lisaa(new Postiosoite(10));
     * akuankka.getPostiosoitteita() === 1;
     * </pre>
     */
    public int getPostiosoitteita() {
        return postiosoitteet.getLkm();
    }
    
    /**
     * Saantimetodi tuotteen tilaajien lukumäärälle
     * @return Palauttaa, kuinka monta tilaajaa tuotteella on
     * @example
     * <pre name="test">
     * Tuote akuankka = new Tuote();
     * akuankka.getTilaajia() === 0;
     * akuankka.lisaa(new Tilaaja(10));
     * akuankka.getTilaajia() === 1;
     * </pre>
     */
    public int getTilaajia() {
        return tilaajat.getLkm();
    } 
    
    /**
     * Lisää uuden postiosoitteen tuotteelle.
     * @param postiosoite Lisättävän postiosoitteen viite
     * @example
     * <pre name="test">
     * Tuote akuankka = new Tuote();
     * Postiosoite helsinki1 = new Postiosoite(10), helsinki2 = new Postiosoite(20);
     * akuankka.getPostiosoitteita() === 0;
     * akuankka.lisaa(helsinki1); akuankka.getPostiosoitteita() === 1;
     * akuankka.lisaa(helsinki2); akuankka.getPostiosoitteita() === 2;
     * akuankka.lisaa(helsinki1); akuankka.getPostiosoitteita() === 2; // Vain yksi samaa postinumeroa
     * akuankka.annaPostiosoite(0) === helsinki1;
     * akuankka.annaPostiosoite(1) === helsinki2;
     * akuankka.annaPostiosoite(2) === helsinki1; #THROWS IndexOutOfBoundsException
     * akuankka.annaPostiosoite(1) == helsinki1 === false;
     * akuankka.annaPostiosoite(1) == helsinki2 === true;
     * akuankka.lisaa(new Postiosoite(30)); akuankka.getPostiosoitteita() === 3;
     * akuankka.annaPostiosoite(0).getPostitoimipaikka() === "";
     * akuankka.lisaa(new Postiosoite(10, "HELSINKI")); akuankka.getPostiosoitteita() === 3;
     * akuankka.annaPostiosoite(0).getPostitoimipaikka() === "HELSINKI";
     * </pre>
     */
    public void lisaa(Postiosoite postiosoite) {
        postiosoitteet.lisaa(postiosoite);
    }
    
    /**
     * Lisää uuden tilaajan tuotteelle.
     * @param tilaaja Lisättävän tilaajan viite.
     * @example
     * <pre name="test">
     * Tuote akuankka = new Tuote();
     * Tilaaja matti1 = new Tilaaja(), matti2 = new Tilaaja();
     * matti1.rekisteroi(); matti2.rekisteroi();
     * akuankka.getTilaajia() === 0;
     * akuankka.lisaa(matti1); akuankka.getTilaajia() === 1;
     * akuankka.lisaa(matti2); akuankka.getTilaajia() === 2;
     * akuankka.lisaa(matti1); akuankka.getTilaajia() === 3;
     * akuankka.annaTilaaja(0) === matti1;
     * akuankka.annaTilaaja(1) === matti2;
     * akuankka.annaTilaaja(2) === matti1;
     * akuankka.annaTilaaja(1) == matti1 === false;
     * akuankka.annaTilaaja(1) == matti2 === true;
     * akuankka.annaTilaaja(3) === matti1; #THROWS IndexOutOfBoundsException
     * akuankka.lisaa(matti1); akuankka.getTilaajia() === 4;
     * akuankka.lisaa(matti1); akuankka.getTilaajia() === 5;
     * akuankka.lisaa(matti1); akuankka.getTilaajia() === 6;
     * </pre>
     */
    public void lisaa(Tilaaja tilaaja) {
        tilaajat.lisaa(tilaaja);
    }
    
    /**
     * Etsitään tilaajaa jolla on sama id. Jos löytyy niin korvataan tilaaja.
     * Jos ei löydy niin lisätään uutena tilaajana. Ottaa tilaajan omistukseensa.
     * @param tilaaja Lisättävän tai korvattavan tilaajan viite. Tietorakenne muuttuu omistajaksi.
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * Tuote tuote = new Tuote();
     * Tilaaja matti1 = new Tilaaja(), matti2 = new Tilaaja();
     * matti1.rekisteroi(); matti2.rekisteroi();
     * tuote.getTilaajia() === 0;
     * tuote.korvaaTaiLisaa(matti1); tuote.getTilaajia() === 1;
     * tuote.korvaaTaiLisaa(matti2); tuote.getTilaajia() === 2;
     * Tilaaja matti3 = matti1.clone();
     * matti1.anna(1) === "";
     * matti3.aseta(1, "matti3"); 
     * matti3.anna(1).equals(matti1.anna(1)) === false; // eri nimet
     * matti3.getTilaajaID() === matti1.getTilaajaID(); // sama tilaajaID
     * tuote.korvaaTaiLisaa(matti3); tuote.getTilaajia() === 2;
     * tuote.annaTilaaja(0).anna(1) === "matti3";
     * </pre>
     */
    public void korvaaTaiLisaa(Tilaaja tilaaja) { 
        tilaajat.korvaaTaiLisaa(tilaaja); 
    }
    
    /**
     * Palauttaa viitteen tilaajaan, jonka indeksi on i.
     * @param i Indeksi, jonka tilaaja halutaan.
     * @return Viite tilaajaan, jonka indeksi on i.
     * @throws IndexOutOfBoundsException Jos i ei ole sallitulla alueella.
     * @example
     * <pre name="test">
     * Tuote akuankka = new Tuote();
     * Postiosoite helsinki1 = new Postiosoite(10), helsinki2 = new Postiosoite(20);
     * akuankka.lisaa(helsinki1);
     * akuankka.annaPostiosoite(0) === helsinki1;
     * akuankka.annaPostiosoite(1) === helsinki2; #THROWS IndexOutOfBoundsException
     * akuankka.lisaa(helsinki2);
     * akuankka.annaPostiosoite(1) === helsinki2;
     * </pre>
     */
    public Postiosoite annaPostiosoite(int i) throws IndexOutOfBoundsException {
        return postiosoitteet.anna(i);
    }
    
    /**
     * Palauttaa viitteen postinumeroa vastaavaan postiosoitteeseen, null jos vastaavaa osoitetta ei ole
     * @param postinumero Postinumero, jonka postiosoite halutaan
     * @return Viite postiosoitteeseen jolla on vastaava postinumero, null jos vastaavaa osoitetta ei ole
     * @example
     * <pre name="test">
     * Tuote akuankka = new Tuote();
     * Postiosoite helsinki1 = new Postiosoite(10), helsinki2 = new Postiosoite(20);
     * akuankka.lisaa(helsinki1);
     * akuankka.lisaa(helsinki2);
     * akuankka.annaPostiosoitePostinumeronPerusteella(0) === null;
     * akuankka.annaPostiosoitePostinumeronPerusteella(10) === helsinki1;
     * Postiosoite helsinki3 = new Postiosoite(10); akuankka.lisaa(helsinki3);
     * akuankka.annaPostiosoitePostinumeronPerusteella(10) === helsinki1;
     * akuankka.annaPostiosoitePostinumeronPerusteella(20) === helsinki2;
     * </pre>
     */
    public Postiosoite annaPostiosoitePostinumeronPerusteella(int postinumero) {
        return postiosoitteet.annaPostinumeronPerusteella(postinumero);
    }
    
    /**
     * Palauttaa viitteen tilaajaan, jonka indeksi on i.
     * @param i Indeksi, jonka tilaaja halutaan.
     * @return Viite tilaajaan, jonka indeksi on i.
     * @throws IndexOutOfBoundsException Jos i ei ole sallitulla alueella.
     * @example
     * <pre name="test">
     * Tuote akuankka = new Tuote();
     * Tilaaja matti1 = new Tilaaja(), matti2 = new Tilaaja();
     * akuankka.lisaa(matti1);
     * akuankka.annaTilaaja(0) === matti1;
     * akuankka.annaTilaaja(1) === matti2; #THROWS IndexOutOfBoundsException
     * akuankka.lisaa(matti2);
     * akuankka.annaTilaaja(1) === matti2;
     * </pre>
     */
    public Tilaaja annaTilaaja(int i) throws IndexOutOfBoundsException {
        return tilaajat.anna(i);
    }
    
    /**
     * Haetaan kaikki postiosoitteen tilaajat
     * @param postiosoite Postiosoite, jonka tilaajia haetaan
     * @return Tietorakenne, jossa viitteet löydettyihin tilaajiin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     * Tuote akuankka = new Tuote();
     * Tilaaja matti1 = new Tilaaja(10); akuankka.lisaa(matti1);
     * Tilaaja matti2 = new Tilaaja(20); akuankka.lisaa(matti2);
     * Tilaaja matti3 = new Tilaaja(30); akuankka.lisaa(matti3);
     * Tilaaja matti4 = new Tilaaja(10); akuankka.lisaa(matti4);
     * Tilaaja matti5 = new Tilaaja(10); akuankka.lisaa(matti5);
     * Tilaaja matti6 = new Tilaaja(20); akuankka.lisaa(matti6);
     *  
     * List<Tilaaja> loytyneet;
     * loytyneet = akuankka.annaTilaajat(new Postiosoite());
     * loytyneet.size() === 0; 
     * loytyneet = akuankka.annaTilaajat(new Postiosoite(20));
     * loytyneet.size() === 2; 
     * loytyneet.get(0) == matti2 === true;
     * loytyneet.get(1) == matti6 === true;
     * loytyneet = akuankka.annaTilaajat(new Postiosoite(30));
     * loytyneet.size() === 1; 
     * loytyneet.get(0) == matti3 === true;
     * </pre>
     */
    public List<Tilaaja> annaTilaajat(Postiosoite postiosoite) {
        return tilaajat.annaTilaajat(postiosoite.getPostinumero());
    }
    
    /**
     * Poistaa annetun tilaajan tietorakenteesta. Jos tilaajan postinumeroa vastaavaan postiosoitteeseen
     * ei enää viitata, poistetaan myös postiosoite.
     * @param tilaaja Poistettava tilaaja
     * @example
     * <pre name="test">
     * Tuote tuote = new Tuote();
     * Tilaaja tilaaja1 = new Tilaaja(); tilaaja1.parse(" 3|Tietäväinen Teijo||||40014||1");
     * Tilaaja tilaaja2 = new Tilaaja(); tilaaja2.parse(" 9|Meikäläinen Matti||||00010||1");
     * Tilaaja tilaaja3 = new Tilaaja(); tilaaja3.parse("13|Tietäväinen Tuija||||40014||0");
     * tuote.lisaa(tilaaja1); tuote.lisaa(tilaaja2); tuote.lisaa(tilaaja3);;
     * Postiosoite postiosoite1 = new Postiosoite(); postiosoite1.parse("00010|HELSINKI"); 
     * Postiosoite postiosoite2 = new Postiosoite(); postiosoite2.parse("40014|JYVÄSKYLÄN YLIOPISTO");
     * tuote.lisaa(postiosoite1); tuote.lisaa(postiosoite2);
     * tuote.getTilaajia() === 3; tuote.getPostiosoitteita() === 2;
     * tuote.poista(tilaaja1);
     * tuote.getTilaajia() === 2; tuote.getPostiosoitteita() === 2;
     * tuote.poista(tilaaja2);
     * tuote.getTilaajia() === 1; tuote.getPostiosoitteita() === 1;
     * </pre>
     */
    public void poista(Tilaaja tilaaja) {
        if (tilaaja == null) return;
        Postiosoite postiosoite = annaPostiosoitePostinumeronPerusteella(tilaaja.getPostinumero());
        
        tilaajat.poista(tilaaja.getTilaajaID());
        if (postiosoite != null && annaTilaajat(postiosoite).isEmpty())
            postiosoitteet.poista(postiosoite);
    }
    
    /**
     * Palauttaa tietorakenteessa hakuehtoon vastaavien tilaajien viitteet
     * @param hakuehto Hakuehto
     * @param k Etsittävän kentän indeksi
     * @return Tietorakenne löytyneistä tilaajista
     * @example
     * <pre name="test">
     * Tuote tuote = new Tuote();
     * Tilaaja tilaaja1 = new Tilaaja(); tilaaja1.parse(" 1|Pukki Joulu      ||||99999||1"); 
     * Tilaaja tilaaja2 = new Tilaaja(); tilaaja2.parse(" 3|Tietäväinen Teijo||||40014||1");
     * Tilaaja tilaaja3 = new Tilaaja(); tilaaja3.parse(" 4|Toljanteri Tonttu||||99999||0");
     * Tilaaja tilaaja4 = new Tilaaja(); tilaaja4.parse(" 9|Meikäläinen Matti||||00010||1");
     * Tilaaja tilaaja5 = new Tilaaja(); tilaaja5.parse("13|Tietäväinen Tuija||||40014||0");
     * tuote.lisaa(tilaaja1); tuote.lisaa(tilaaja2); tuote.lisaa(tilaaja3);
     * tuote.lisaa(tilaaja4); tuote.lisaa(tilaaja5);
     * 
     * List<Tilaaja> loytyneet;
     * loytyneet = tuote.etsi("*k*", 1); // etsi nimen perusteella
     * loytyneet.size() === 2;  
     * loytyneet.get(0) == tilaaja1 === true;  
     * loytyneet.get(1) == tilaaja4 === true;  
     * 
     * loytyneet = tuote.etsi("*0*", 7); // etsi maksettu perusteella
     * loytyneet.size() === 2;  
     * loytyneet.get(0) == tilaaja3 === true;  
     * loytyneet.get(1) == tilaaja5 === true; 
     * 
     * loytyneet = tuote.etsi(null, -1);  
     * loytyneet.size() === 5;
     * </pre>
     */
    public List<Tilaaja> etsi(String hakuehto, int k) {
        return tilaajat.etsi(hakuehto, k);
    }
    
    /**
     * Palauttaa tietorakenteessa kaikki tilaajat joilla on hakuehtoa vastaava postitoimipaikka
     * @param hakuehto Postitoimipaikan hakuehto
     * @return Tietorakenne, jossa on kaikki tilaajat joilla on hakuehtoa vastaava postitoimipaikka
     * @example
     * <pre name="test">
     * Tuote tuote = new Tuote();
     * Tilaaja tilaaja1 = new Tilaaja(); tilaaja1.parse(" 3|Tietäväinen Teijo||||40014||1");
     * Tilaaja tilaaja2 = new Tilaaja(); tilaaja2.parse(" 9|Meikäläinen Matti||||00010||1");
     * Tilaaja tilaaja3 = new Tilaaja(); tilaaja3.parse("13|Tietäväinen Tuija||||40014||0");
     * tuote.lisaa(tilaaja1); tuote.lisaa(tilaaja2); tuote.lisaa(tilaaja3);;
     * Postiosoite postiosoite1 = new Postiosoite(); postiosoite1.parse("00010|HELSINKI"); 
     * Postiosoite postiosoite2 = new Postiosoite(); postiosoite2.parse("40014|JYVÄSKYLÄN YLIOPISTO");
     * tuote.lisaa(postiosoite1); tuote.lisaa(postiosoite2);
     * 
     * List<Tilaaja> loytyneet;  
     * loytyneet = tuote.etsiPostitoimipaikka("*k*");
     * loytyneet.size() === 3;
     * 
     * loytyneet = tuote.etsiPostitoimipaikka("J*");
     * loytyneet.size() === 2;  
     * loytyneet.get(0) == tilaaja1 === true;  
     * loytyneet.get(1) == tilaaja3 === true;
     * 
     * loytyneet = tuote.etsiPostitoimipaikka(null);  
     * loytyneet.size() === 3;  
     * </pre>
     */
    public List<Tilaaja> etsiPostitoimipaikka(String hakuehto) {
        List<Tilaaja> loydetyt = new ArrayList<Tilaaja>();
        for (Postiosoite postiosoite: postiosoitteet.etsiPostitoimipaikka(hakuehto))
            loydetyt.addAll(annaTilaajat(postiosoite));
        return loydetyt;
    }
    
    /**
     * Asettaa tiedostojen perusnimet: nimi/postiosoitteet ja nimi/tilaajat
     * @param nimi Uusi tuotteen nimi
     */
    public void setTiedosto(String nimi) {
        File dir = new File(nimi);
        dir.mkdirs();
        String hakemistonNimi = "";
        if (!nimi.isEmpty()) hakemistonNimi = nimi + "/";
        postiosoitteet.setTiedostonPerusNimi(hakemistonNimi + "postiosoitteet");
        tilaajat.setTiedostonPerusNimi(hakemistonNimi + "tilaajat");
    }
    
    /**
     * Lukee tuotteen tiedot tiedostosta
     * @param nimi Tuotteen nimi, jota käytetään lukemisessa
     * @throws SailoException Heittää poikkeuksen jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import kanta.SailoException;
     * #import java.io.*;
     * #import java.util.*;
     * 
     * Tuote tuote = new Tuote();
     * 
     * Postiosoite helsinki1 = new Postiosoite(10); helsinki1.taytaHelsinkiTiedoilla();
     * Postiosoite helsinki2 = new Postiosoite(20); helsinki2.taytaHelsinkiTiedoilla();
     * Postiosoite helsinki3 = new Postiosoite(30); helsinki3.taytaHelsinkiTiedoilla();
     * Tilaaja matti21 = new Tilaaja(); matti21.taytaMattiTiedoilla(helsinki2.getPostinumero());
     * Tilaaja matti11 = new Tilaaja(); matti11.taytaMattiTiedoilla(helsinki1.getPostinumero());
     * Tilaaja matti22 = new Tilaaja(); matti22.taytaMattiTiedoilla(helsinki2.getPostinumero()); 
     * Tilaaja matti12 = new Tilaaja(); matti12.taytaMattiTiedoilla(helsinki1.getPostinumero()); 
     * Tilaaja matti23 = new Tilaaja(); matti23.taytaMattiTiedoilla(helsinki2.getPostinumero());
     *  
     * String hakemisto = "testituote";
     * File dir = new File(hakemisto);
     * File fptied  = new File(hakemisto+"/postiosoitteet.dat");
     * File fttied = new File(hakemisto+"/tilaajat.dat");
     * dir.mkdir();  
     * fptied.delete();
     * fttied.delete();
     * 
     * tuote.lueTiedostosta(hakemisto); #THROWS SailoException
     * tuote.lisaa(helsinki1); tuote.annaPostiosoite(0) === helsinki1;
     * tuote.lisaa(helsinki2); tuote.annaPostiosoite(1) === helsinki2;
     * tuote.annaPostiosoite(2) === helsinki1; #THROWS IndexOutOfBoundsException
     * tuote.getPostiosoitteita() === 2;
     * tuote.lisaa(matti21); tuote.annaTilaaja(0) === matti21;
     * tuote.lisaa(matti11); tuote.annaTilaaja(1) === matti11;
     * tuote.lisaa(matti22); tuote.annaTilaaja(2) === matti22;
     * tuote.lisaa(matti12); tuote.annaTilaaja(3) === matti12;
     * tuote.lisaa(matti23); tuote.annaTilaaja(4) === matti23;
     * tuote.annaTilaaja(5) === matti21; #THROWS IndexOutOfBoundsException
     * tuote.getTilaajia() === 5;
     * tuote.tallenna();
     * 
     * // Poistetaan vanha tuote ja ladataan tiedot tiedostosta
     * tuote = new Tuote();
     * tuote.getPostiosoitteita() === 0; tuote.getTilaajia() === 0;
     * tuote.lueTiedostosta(hakemisto);
     * tuote.getPostiosoitteita() === 2; tuote.getTilaajia() === 5;
     * List<Tilaaja> loytyneet = tuote.annaTilaajat(helsinki1);
     * loytyneet.size() === 2;
     * loytyneet = tuote.annaTilaajat(helsinki2);
     * loytyneet.size() === 3;
     * 
     * tuote.lisaa(helsinki3);
     * tuote.lisaa(new Tilaaja(helsinki3.getPostinumero()));
     * tuote.tallenna();
     * 
     * fptied.delete()  === true;
     * fttied.delete() === true;
     * File fbak = new File(hakemisto+"/postiosoitteet.bak");
     * File fhbak = new File(hakemisto+"/tilaajat.bak");
     * fbak.delete() === true;
     * fhbak.delete() === true;
     * dir.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        postiosoitteet = new Postiosoitteet();
        tilaajat = new Tilaajat();
        
        setTiedosto(nimi);
        postiosoitteet.lueTiedostosta();
        tilaajat.lueTiedostosta();
    }


    /**
     * Tallentaa tuotteen tiedot tiedostoon
     * @throws SailoException Heittää poikkeuksen jos tallentaminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException, IOException
     * #import kanta.SailoException;
     * #import java.io.IOException;
     * #import java.io.File;
     * #import fi.jyu.mit.ohj2.VertaaTiedosto;
     * Tuote tuote = new Tuote();
     * 
     * Postiosoite helsinki1 = new Postiosoite(10); helsinki1.taytaHelsinkiTiedoilla();
     * Postiosoite helsinki2 = new Postiosoite(20); helsinki2.taytaHelsinkiTiedoilla();
     * Postiosoite helsinki3 = new Postiosoite(30); helsinki3.taytaHelsinkiTiedoilla();
     * Tilaaja matti21 = new Tilaaja(); matti21.taytaMattiTiedoilla(helsinki2.getPostinumero());
     * Tilaaja matti11 = new Tilaaja(); matti11.taytaMattiTiedoilla(helsinki1.getPostinumero());
     * Tilaaja matti22 = new Tilaaja(); matti22.taytaMattiTiedoilla(helsinki2.getPostinumero()); 
     * Tilaaja matti12 = new Tilaaja(); matti12.taytaMattiTiedoilla(helsinki1.getPostinumero()); 
     * Tilaaja matti23 = new Tilaaja(); matti23.taytaMattiTiedoilla(helsinki2.getPostinumero());
     *  
     * String hakemisto = "testituote";
     * File dir = new File(hakemisto);
     * File fptied  = new File(hakemisto+"/postiosoitteet.dat");
     * File fttied = new File(hakemisto+"/tilaajat.dat");
     * dir.mkdir();  
     * fptied.delete();
     * fttied.delete();
     * 
     * tuote.lueTiedostosta(hakemisto); #THROWS SailoException
     * tuote.lisaa(helsinki1); tuote.annaPostiosoite(0) === helsinki1;
     * tuote.lisaa(helsinki2); tuote.annaPostiosoite(1) === helsinki2;
     * tuote.getPostiosoitteita() === 2;
     * tuote.lisaa(matti21); tuote.annaTilaaja(0) === matti21;
     * tuote.lisaa(matti11); tuote.annaTilaaja(1) === matti11;
     * tuote.lisaa(matti22); tuote.annaTilaaja(2) === matti22;
     * tuote.lisaa(matti12); tuote.annaTilaaja(3) === matti12;
     * tuote.lisaa(matti23); tuote.annaTilaaja(4) === matti23;
     * tuote.getTilaajia() === 5;
     * tuote.tallenna();
     * 
     * String ptulos = "postinro|postitoimipaikka\n" +
     *                  helsinki1.toString() + "\n" +
     *                  helsinki2.toString();          
     * VertaaTiedosto.vertaaFileString(hakemisto+"/postiosoitteet.dat", ptulos) === null;  
     * 
     * String ttulos = "tid|sukunimi etunimi|sähköposti|puhelin|katuosoite|postinumero|aloitti tilaamisen|maksettu\n" +
     *                  matti21.toString() + "\n" +
     *                  matti11.toString() + "\n" +
     *                  matti22.toString() + "\n" +
     *                  matti12.toString() + "\n" +
     *                  matti23.toString();        
     * VertaaTiedosto.vertaaFileString(hakemisto+"/tilaajat.dat", ttulos) === null;                 
     * 
     * tuote.lisaa(helsinki3);
     * Tilaaja tilaaja = new Tilaaja(helsinki3.getPostinumero());
     * tuote.lisaa(tilaaja);
     * tuote.tallenna();
     * 
     * ptulos += "\n" + helsinki3.toString();          
     * VertaaTiedosto.vertaaFileString(hakemisto+"/postiosoitteet.dat", ptulos) === null;  
     * 
     * ttulos += "\n" + tilaaja.toString();        
     * VertaaTiedosto.vertaaFileString(hakemisto+"/tilaajat.dat", ttulos) === null;      
     * 
     * fptied.delete()  === true;
     * fttied.delete() === true;
     * File fbak = new File(hakemisto+"/postiosoitteet.bak");
     * File fhbak = new File(hakemisto+"/tilaajat.bak");
     * fbak.delete() === true;
     * fhbak.delete() === true;
     * dir.delete() === true;
     * </pre>
     */
    public void tallenna() throws SailoException {
        String virhe = "";
        
        try {
            postiosoitteet.tallenna();
        } catch (SailoException e) {
            virhe += e.getMessage();
        }
        
        try {
            tilaajat.tallenna();
        } catch (SailoException e) {
            virhe += e.getMessage();
        }
        
        if (virhe.length() > 0)
            throw new SailoException(virhe);
    }
    
    /**
     * Testiohjelma tuotteesta
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Tuote akuankka = new Tuote();
        
        Tilaaja matti = new Tilaaja(), matti2 = new Tilaaja();
        matti.rekisteroi();
        matti.taytaMattiTiedoilla(10);
        matti2.rekisteroi();
        matti2.taytaMattiTiedoilla(10);
        
        akuankka.lisaa(matti);
        akuankka.lisaa(matti2);
        
        Postiosoite helsinki = new Postiosoite(10), helsinki2 = new Postiosoite();
        helsinki.taytaHelsinkiTiedoilla();
        helsinki2.taytaHelsinkiTiedoilla();

        akuankka.lisaa(helsinki);
        akuankka.lisaa(helsinki2);
        
        System.out.println("===== Tuote-testi: Postiosoitteiden tilaajat =====");
         
        for (int i = 0; i < akuankka.getPostiosoitteita(); i++) {
            Postiosoite postiosoite = akuankka.annaPostiosoite(i);
            System.out.print("Postiosoite paikassa " + i + ": ");
            postiosoite.tulosta(System.out);
            List<Tilaaja> loytyneet = akuankka.annaTilaajat(postiosoite);
            for (Tilaaja tilaaja: loytyneet)
                tilaaja.tulosta(System.out);
            System.out.println("-----------");
        }
        
        System.out.println("===== Tuote-testi: Tilaaja ja tämän postiosoite =====");
        
        for (int i = 0; i < akuankka.getTilaajia(); i++) {
            Tilaaja tilaaja = akuankka.annaTilaaja(i);
            System.out.println("Tilaaja paikassa " + i + ": ");
            tilaaja.tulosta(System.out);
            System.out.print("Postiosoite: ");
            akuankka.annaPostiosoitePostinumeronPerusteella(tilaaja.getPostinumero()).tulosta(System.out);
            System.out.println("-----------");
        }
    }
}