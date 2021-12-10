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
import kanta.PostinumeroTarkistus;
import kanta.SailoException;

/**
 * Pitää yllä varsinaista postiosoiterekisteriä, eli osaa lisätä ja poistaa postiosoitteen.
 * Lukee ja kirjoittaa postiosoitteet-tiedostoon, sekä osaa etsiä ja lajitella.
 * Avustajana Postiosoite.
 * @author Herman Lätti
 * @version Vaihe 7 24.7.2020
 *
 */
public class Postiosoitteet {
    
    private boolean muutettu = false;
    private String tiedostonPerusNimi = "postiosoitteet";
    private ArrayList<Postiosoite> alkiot = new ArrayList<Postiosoite>();
    
    /**
     * Oletusmuodostaja
     */
    public Postiosoitteet() {
        // Attribuuttien oma alustus riittää
    }
    
    /**
     * Lisää uuden postiosoitteen tietorakenteeseen. Ottaa postiosoitteen omistukseensa.<br/>
     * Varmistaa, että postinumero säilyy yksiselitteisenä tietorakenteessa.
     * @param postiosoite Lisättävän postiosoitteen viite
     * @example
     * <pre name="test">
     * Postiosoitteet postiosoitteet = new Postiosoitteet();
     * Postiosoite helsinki1 = new Postiosoite(10), helsinki2 = new Postiosoite(20);
     * postiosoitteet.getLkm() === 0;
     * postiosoitteet.lisaa(helsinki1); postiosoitteet.getLkm() === 1;
     * postiosoitteet.lisaa(helsinki2); postiosoitteet.getLkm() === 2;
     * postiosoitteet.lisaa(helsinki1); postiosoitteet.getLkm() === 2; // Vain yksi samaa postinumeroa
     * postiosoitteet.anna(0) === helsinki1;
     * postiosoitteet.anna(1) === helsinki2;
     * postiosoitteet.anna(2) === helsinki1; #THROWS IndexOutOfBoundsException
     * postiosoitteet.anna(1) == helsinki1 === false;
     * postiosoitteet.anna(1) == helsinki2 === true;
     * postiosoitteet.lisaa(new Postiosoite(30)); postiosoitteet.getLkm() === 3;
     * postiosoitteet.anna(0).getPostitoimipaikka() === "";
     * postiosoitteet.lisaa(new Postiosoite(10, "HELSINKI")); postiosoitteet.getLkm() === 3;
     * postiosoitteet.anna(0).getPostitoimipaikka() === "HELSINKI";
     * </pre>
     */
    public void lisaa(Postiosoite postiosoite) {
        boolean tarveUudelle = true;
        
        for (int i = 0; i < alkiot.size(); i++)
            if (postiosoite.getPostinumero() == alkiot.get(i).getPostinumero()) {
                if (postiosoite.getPostitoimipaikka() != null && postiosoite.getPostitoimipaikka().length() > 0)
                    alkiot.set(i, postiosoite);
                tarveUudelle = false;
            }
        
        if (tarveUudelle)
            alkiot.add(postiosoite);
        
        muutettu = true;
    }
    
    /**
     * Poistaa annetun postiosoitteen tietorakenteesta
     * @param postiosoite Poistettava postiosoite
     * @example
     * <pre name="test">
     * Postiosoitteet postiosoitteet = new Postiosoitteet();
     * Postiosoite helsinki1 = new Postiosoite(10), helsinki2 = new Postiosoite(20); 
     * Postiosoite helsinki3 = new Postiosoite(30);
     * postiosoitteet.getLkm() === 0;
     * postiosoitteet.lisaa(helsinki1); postiosoitteet.lisaa(helsinki2); postiosoitteet.lisaa(helsinki3);
     * postiosoitteet.getLkm() === 3;
     * postiosoitteet.anna(2) === helsinki3;
     * postiosoitteet.poista(helsinki2);
     * postiosoitteet.getLkm() === 2;
     * postiosoitteet.anna(2) === helsinki3; #THROWS IndexOutOfBoundsException
     * postiosoitteet.annaPostinumeronPerusteella(20) === null;
     * </pre>
     */
    public void poista(Postiosoite postiosoite) {
        alkiot.remove(postiosoite);
        muutettu = true;
    }
    
    /**
     * Palauttaa viitteen postiosoitteeseen, jonka indeksi on i
     * @param i Indeksi, jonka postiosoite halutaan
     * @return Viite postiosoitteeseen, jonka indeksi on i
     * @throws IndexOutOfBoundsException Jos i ei ole sallitulla alueella
     * @example
     * <pre name="test">
     * Postiosoitteet postiosoitteet = new Postiosoitteet();
     * Postiosoite helsinki1 = new Postiosoite(10), helsinki2 = new Postiosoite(20);
     * postiosoitteet.lisaa(helsinki1);
     * postiosoitteet.anna(0) === helsinki1;
     * postiosoitteet.anna(1) === helsinki2; #THROWS IndexOutOfBoundsException
     * postiosoitteet.lisaa(helsinki2);
     * postiosoitteet.anna(1) === helsinki2;
     * </pre>
     */
    public Postiosoite anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || getLkm() <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot.get(i);
    }
    
    /**
     * Palauttaa viitteen postinumeroa vastaavaan postiosoitteeseen, null jos vastaavaa osoitetta ei ole
     * @param postinumero Postinumero, jonka postiosoite halutaan
     * @return Viite postiosoitteeseen jolla on vastaava postinumero, null jos vastaavaa osoitetta ei ole
     * @example
     * <pre name="test">
     * Postiosoitteet postiosoitteet = new Postiosoitteet();
     * Postiosoite helsinki1 = new Postiosoite(10), helsinki2 = new Postiosoite(20);
     * postiosoitteet.lisaa(helsinki1);
     * postiosoitteet.lisaa(helsinki2);
     * postiosoitteet.annaPostinumeronPerusteella(0) === null;
     * postiosoitteet.annaPostinumeronPerusteella(10) === helsinki1;
     * Postiosoite helsinki3 = new Postiosoite(10); postiosoitteet.lisaa(helsinki3);
     * postiosoitteet.annaPostinumeronPerusteella(10) === helsinki1;
     * postiosoitteet.annaPostinumeronPerusteella(20) === helsinki2;
     * </pre>
     */
    public Postiosoite annaPostinumeronPerusteella(int postinumero) {
        for (Postiosoite osoite: alkiot)
            if (osoite.getPostinumero() == postinumero) return osoite;
        return null;
    }
    
    /**
     * Lukee postiosoitteiden tiedostosta
     * @param tied Tiedoston perusnimi
     * @throws SailoException Heittää poikkeuksen jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * #import kanta.SailoException;
     * #import java.io.File;
     * Postiosoitteet postiosoitteet = new Postiosoitteet();
     * Postiosoite helsinki1 = new Postiosoite(10), helsinki2 = new Postiosoite(20);
     * Postiosoite helsinki3 = new Postiosoite(30);
     * helsinki1.taytaHelsinkiTiedoilla();
     * helsinki2.taytaHelsinkiTiedoilla();
     * helsinki3.taytaHelsinkiTiedoilla();
     * String hakemisto = "testiakuankka";
     * String tiedNimi = hakemisto+"/postiosoitteet";
     * File ftied = new File(tiedNimi+".dat");
     * File dir = new File(hakemisto);
     * dir.mkdir();
     * ftied.delete();
     * 
     * postiosoitteet.lueTiedostosta(tiedNimi); #THROWS SailoException
     * postiosoitteet.lisaa(helsinki1);
     * postiosoitteet.lisaa(helsinki2);
     * postiosoitteet.anna(0).toString() === helsinki1.toString();
     * postiosoitteet.anna(1).toString() === helsinki2.toString();
     * postiosoitteet.anna(2).toString() === helsinki3.toString(); #THROWS IndexOutOfBoundsException
     * postiosoitteet.tallenna();
     * 
     * // Poistetaan vanhat postiosoitteet ja ladataan ne sitten tiedostosta
     * postiosoitteet = new Postiosoitteet();
     * postiosoitteet.anna(0).toString() === helsinki1.toString(); #THROWS IndexOutOfBoundsException
     * postiosoitteet.lueTiedostosta(tiedNimi);
     * postiosoitteet.anna(0).toString() === helsinki1.toString();
     * postiosoitteet.anna(1).toString() === helsinki2.toString();
     * postiosoitteet.anna(2).toString() === helsinki3.toString(); #THROWS IndexOutOfBoundsException
     * 
     * postiosoitteet.lisaa(helsinki3);
     * postiosoitteet.anna(2).toString() === helsinki3.toString();
     * postiosoitteet.tallenna();
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
                Postiosoite postiosoite = new Postiosoite();
                postiosoite.parse(rivi);
                lisaa(postiosoite);
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
     * Tallentaa postiosoitteiden tiedostoon
     * @throws SailoException Heittää poikkeuksen jos tallentaminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException, IOException
     * #import kanta.SailoException;
     * #import java.io.IOException;
     * #import java.io.File;
     * #import fi.jyu.mit.ohj2.VertaaTiedosto;
     * Postiosoitteet postiosoitteet = new Postiosoitteet();
     * Postiosoite helsinki1 = new Postiosoite(10), helsinki2 = new Postiosoite(20);
     * Postiosoite helsinki3 = new Postiosoite(30);
     * helsinki1.taytaHelsinkiTiedoilla();
     * helsinki2.taytaHelsinkiTiedoilla();
     * helsinki3.taytaHelsinkiTiedoilla();
     * String hakemisto = "testiakuankka";
     * String tiedNimi = hakemisto+"/postiosoitteet";
     * File ftied = new File(tiedNimi+".dat");
     * File dir = new File(hakemisto);
     * dir.mkdir();
     * ftied.delete();
     * 
     * postiosoitteet.lueTiedostosta(tiedNimi); #THROWS SailoException
     * postiosoitteet.lisaa(helsinki1);
     * postiosoitteet.lisaa(helsinki2);
     * postiosoitteet.anna(0).toString() === helsinki1.toString();
     * postiosoitteet.anna(1).toString() === helsinki2.toString();
     * postiosoitteet.tallenna();
     * 
     * String tulos = "postinro|postitoimipaikka\n" +
     *                  helsinki1.toString() + "\n" +
     *                  helsinki2.toString();
     * 
     * VertaaTiedosto.vertaaFileString(tiedNimi+".dat", tulos) === null;
     * 
     * postiosoitteet.lisaa(helsinki3);
     * postiosoitteet.anna(2).toString() === helsinki3.toString();
     * postiosoitteet.tallenna();
     * 
     * tulos = "postinro|postitoimipaikka\n" +
     *          helsinki1.toString() + "\n" +
     *          helsinki2.toString() + "\n" +
     *          helsinki3.toString();
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
            fo.println("postinro|postitoimipaikka");
            for (Postiosoite postiosoite: alkiot) //for (int i = 0; i < getLkm(); i++) { Postiosoite postiosoite = anna(i);
                fo.println(postiosoite.toString());
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch (IOException ex) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittaminen ei onnistu");
        }
        
        muutettu = false;
    }
    
    /**
     * Palauttaa postiosoitteiden lukumäärän
     * @return Postiosoitteiden lukumäärä
     * @example
     * <pre name="test">
     * Postiosoitteet postiosoitteet = new Postiosoitteet();
     * postiosoitteet.getLkm() === 0;
     * Postiosoite helsinki = new Postiosoite();
     * postiosoitteet.lisaa(helsinki);
     * postiosoitteet.getLkm() === 1;
     * </pre>
     */
    public int getLkm() {
        return alkiot.size();
    }

    /**
     * Saantimetodi tallennuksessa käytettävälle tiedostonnimelle
     * @return Palauttaa tallennustiedoston nimen
     * @example
     * <pre name="test">
     * Postiosoitteet postiosoitteet = new Postiosoitteet();
     * postiosoitteet.getTiedostonPerusNimi() === "postiosoitteet";
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
     * Postiosoitteet postiosoitteet = new Postiosoitteet();
     * postiosoitteet.getTiedostonPerusNimi() === "postiosoitteet";
     * postiosoitteet.setTiedostonPerusNimi("testi");
     * postiosoitteet.getTiedostonPerusNimi() === "testi";
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
     * Postiosoitteet postiosoitteet = new Postiosoitteet();
     * postiosoitteet.getTiedostonNimi() === "postiosoitteet.dat";
     * postiosoitteet.setTiedostonPerusNimi("testi");
     * postiosoitteet.getTiedostonNimi() === "testi.dat";
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
     * Postiosoitteet postiosoitteet = new Postiosoitteet();
     * postiosoitteet.getBakNimi() === "postiosoitteet.bak";
     * postiosoitteet.setTiedostonPerusNimi("testi");
     * postiosoitteet.getBakNimi() === "testi.bak";
     * </pre>
     */
    public String getBakNimi() {
        return tiedostonPerusNimi + ".bak";
    }
    
    /**
     * 
     * Palauttaa tietorakenteessa postitoimipaikkaa vastaavat postiosoitteiden viitteet
     * @param hakuehto Postitoimipaikan hakuehto
     * @return Tietorakenne löytyneistä postiosoitteista
     * @example
     * <pre name="test">
     * #import java.util.List;
     * Postiosoitteet postiosoitteet = new Postiosoitteet();
     * Postiosoite postiosoite1 = new Postiosoite(); postiosoite1.parse("00010|HELSINKI "); 
     * Postiosoite postiosoite2 = new Postiosoite(); postiosoite2.parse("40000|JYVÄSKYLÄ");
     * Postiosoite postiosoite3 = new Postiosoite(); postiosoite3.parse("00020|HELSINKI ");
     * Postiosoite postiosoite4 = new Postiosoite(); postiosoite4.parse("00030|HELSINKI ");
     * Postiosoite postiosoite5 = new Postiosoite(); postiosoite5.parse("99998|NUORGAM  ");
     * postiosoitteet.lisaa(postiosoite1); postiosoitteet.lisaa(postiosoite2); 
     * postiosoitteet.lisaa(postiosoite3); postiosoitteet.lisaa(postiosoite4); 
     * postiosoitteet.lisaa(postiosoite5);
     * 
     * List<Postiosoite> loytyneet;  
     * loytyneet = postiosoitteet.etsiPostitoimipaikka("*k*");
     * loytyneet.size() === 4;  
     * loytyneet.contains(postiosoite5) === false;
     * 
     * loytyneet = postiosoitteet.etsiPostitoimipaikka("H*");
     * loytyneet.size() === 3;  
     * loytyneet.get(0) == postiosoite1 === true;  
     * loytyneet.get(1) == postiosoite3 === true; 
     * loytyneet.get(2) == postiosoite4 === true; 
     * 
     * loytyneet = postiosoitteet.etsiPostitoimipaikka(null);  
     * loytyneet.size() === 5;  
     * </pre>
     */
    public List<Postiosoite> etsiPostitoimipaikka(String hakuehto) {
        String ehto = "*";
        if (hakuehto != null && hakuehto.length() > 0)
            ehto = hakuehto;
        
        List<Postiosoite> loydetyt = new ArrayList<Postiosoite>();
        for (Postiosoite postiosoite: alkiot)
            if (WildChars.onkoSamat(postiosoite.getPostitoimipaikka(), ehto))
                loydetyt.add(postiosoite);

        return loydetyt;
    }
    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Postiosoitteet postiosoitteet = new Postiosoitteet();
        var helsinki = new Postiosoite(PostinumeroTarkistus.satunnainenPostinumero(0, 990)); 
        var helsinki2 = new Postiosoite(PostinumeroTarkistus.satunnainenPostinumero(0, 990));
        helsinki.taytaHelsinkiTiedoilla();
        helsinki2.taytaHelsinkiTiedoilla();
        
        postiosoitteet.lisaa(helsinki);
        postiosoitteet.lisaa(helsinki2);
        postiosoitteet.lisaa(helsinki); // ei lisäänny uudestaan

        System.out.println("========== Postiosoitteet testi ==========");   
        for (int i = 0; i < postiosoitteet.getLkm(); i++) {
            Postiosoite postiosoite = postiosoitteet.anna(i);
            postiosoite.tulosta(System.out);
        }
    }
}