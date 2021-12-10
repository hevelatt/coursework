package demo.d4;

/**
 * Luokka henkilön luomiseksi
 * @author Herman
 * @version 13.6.2020
 *
 */
public class Henkilo {

    private String sukunimi;
    private String etunimi;
    private int syntymavuosi;
    
    /**
     * Muodostaja
     * @param etunimi Henkilön etunimi
     * @param sukunimi Henkilön sukunimi
     * @param syntymavuosi Henkilön syntymävuosi
     */
    public Henkilo(String etunimi, String sukunimi, int syntymavuosi) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.syntymavuosi = syntymavuosi;
    }
    
    /**
     * Muodostaja, alustetaan vuosi oletusarvoon
     * @param etunimi Henkilön etunimi
     * @param sukunimi Henkilön sukunimi
     */
    public Henkilo(String etunimi, String sukunimi) {
        this(etunimi, sukunimi, 0);
    }
    
    /**
     * Muodostaja, yksi nimi, alustetaan vuosi oletusarvoon
     * @param nimi Henkilön nimi
     */
    public Henkilo(String nimi) {
        this(nimi, "", 0);
    }
    
    /**
     * Oletusmuodostaja, alustetaan kaikki oletusarvoon 
     */
    public Henkilo() {
        this("", "", 0);
    }
    
    /**
     * Palauttaa henkilön nimen
     * @return Henkilön nimi muodossa "etunimi sukunimi"
     */
    public String getNimi() {
        return etunimi + " " + sukunimi;
    }
    
    /**
     * Palauttaa henkilön syntymävuoden
     * @return Henkilön syntymävuosi
     */
    public int getSyntymavuosi() {
        return syntymavuosi;
    }
    
    /**
     * Henkilön tiedot merkkijonona
     * @return Henkilön tiedot muodossa "etunimi|sukunimi|syntymavuosi"
     * @example
     * <pre name="test">
     * Henkilo henkilo = new Henkilo("Matti", "Meikäläinen", 2000);
     * henkilo.toString() === "Matti|Meikäläinen|2000";
     * Henkilo henkilo2 = new Henkilo("Matti", "Meikäläinen");
     * henkilo2.toString() === "Matti|Meikäläinen|0";
     * Henkilo henkilo3 = new Henkilo("Matti");
     * henkilo3.toString() === "Matti||0";
     * Henkilo henkilo4 = new Henkilo();
     * henkilo4.toString() === "||0";
     * </pre>
     */
    @Override
    public String toString() {
        return etunimi + "|" + sukunimi + "|" + syntymavuosi;
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Henkilo aku = new Henkilo("Aku", "Ankka", 1934);
        System.out.println(aku.getNimi());  // tulostaa Aku Ankka
        System.out.println(aku.getSyntymavuosi());  // tulostaa 1934
        System.out.println(aku.toString()); // tulostaa Aku|Ankka|1934
    }
}
