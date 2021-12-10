package demo.d5;

/**
 * Luokka yksinkertaiselle tietokoneelle
 * @author Herman
 * @version 15.6.2020
 *
 */
public class Tietokone {

    private double muisti;
    private double kiintolevy;
    private double prosessorintaajuus;
    private int ytimet;
    private int saikeet;
    private double videomuisti;
    
    /**
     * Oletusmuodostaja, alustaa kaiken nollaksi
     */
    public Tietokone() { }
    
    /**
     * Muodostaja
     * @param muisti Kuinka paljon muistia tietokoneessa on
     */
    public Tietokone(double muisti) {
        this.muisti = muisti;
    }
    
    /**
     * Muodostaja
     * @param muisti Kuinka paljon muistia tietokoneessa on
     * @param kiintolevy Kuinka paljon kiintolevytilaa tietokoneessa on
     */
    public Tietokone(double muisti, double kiintolevy) {
        this.muisti = muisti;
        this.kiintolevy = kiintolevy;
    }
    
    /**
     * Muodostaja
     * @param muisti Kuinka paljon muistia tietokoneessa on
     * @param kiintolevy Kuinka paljon kiintolevytilaa tietokoneessa on
     * @param prosessorintaajuus Mikä on prosessorin kellotaajuus
     */
    public Tietokone(double muisti, double kiintolevy, double prosessorintaajuus) {
        this.muisti = muisti;
        this.kiintolevy = kiintolevy;
        this.prosessorintaajuus = prosessorintaajuus;
    }
    
    /**
     * Muodostaja
     * @param muisti Kuinka paljon muistia tietokoneessa on
     * @param kiintolevy Kuinka paljon kiintolevytilaa tietokoneessa on
     * @param prosessorintaajuus Mikä on prosessorin kellotaajuus
     * @param ytimet Kuinka monta ydintä prosessorissa on
     */
    public Tietokone(double muisti, double kiintolevy, double prosessorintaajuus, int ytimet) {
        this.muisti = muisti;
        this.kiintolevy = kiintolevy;
        this.prosessorintaajuus = prosessorintaajuus;
        this.ytimet = ytimet;
        this.saikeet = ytimet;
    }
    
    /**
     * Muodostaja
     * @param muisti Kuinka paljon muistia tietokoneessa on
     * @param kiintolevy Kuinka paljon kiintolevytilaa tietokoneessa on
     * @param prosessorintaajuus Mikä on prosessorin kellotaajuus
     * @param ytimet Kuinka monta ydintä prosessorissa on
     * @param saikeet Prosessorisäikeiden määrä
     */
    public Tietokone(double muisti, double kiintolevy, double prosessorintaajuus, int ytimet, int saikeet) {
        this.muisti = muisti;
        this.kiintolevy = kiintolevy;
        this.prosessorintaajuus = prosessorintaajuus;
        this.ytimet = ytimet;
        this.saikeet = saikeet;
    }
    
    /**
     * Muodostaja
     * @param muisti Kuinka paljon muistia tietokoneessa on
     * @param kiintolevy Kuinka paljon kiintolevytilaa tietokoneessa on
     * @param prosessorintaajuus Mikä on prosessorin kellotaajuus
     * @param ytimet Kuinka monta ydintä prosessorissa on
     * @param saikeet Prosessorisäikeiden määrä
     * @param videomuisti Kuinka paljon videomuistia tietokoneessa on
     */
    public Tietokone(double muisti, double kiintolevy, double prosessorintaajuus, int ytimet, int saikeet, double videomuisti) {
        this.muisti = muisti;
        this.kiintolevy = kiintolevy;
        this.prosessorintaajuus = prosessorintaajuus;
        this.ytimet = ytimet;
        this.saikeet = saikeet;
        this.videomuisti = videomuisti;
    }
    
    /**
     * Saantimetodi muistin määrälle
     * @return Muistin määrä tietokoneessa@param videomuisti Kuinka paljon videomuistia tietokoneessa on
     */
    public double getMuisti() {
        return muisti;
    }
    
    /**
     * Saantimetodi kiintolevytilalle
     * @return Kiintolevytila tietokoneessa
     */
    public double getKiintolevy() {
        return kiintolevy;
    }
    
    /**
     * Saantimetodi prosessorin kellotaajuudelle
     * @return Prosessorin kellotaajuus tietokoneessa
     */
    public double getProsessorinTaajuus() {
        return prosessorintaajuus;
    }
    
    /**
     * Saantimetodi prosessorin ytimien lukumäärälle
     * @return Prosessorin ytimien lukumäärä
     */
    public int getYtimet() {
        return ytimet;
    }
    
    /**
     * Saantimetodi prosessorin säikeiden lukumäärälle
     * @return Prosessorin säikeiden lukumäärä
     */
    public int getSaikeet() {
        return saikeet;
    }
    
    /**
     * Saantimetodi videomuistin määrälle
     * @return Videomuistin määrä
     */
    public double getVideomuisti() {
        return videomuisti;
    }
    
    /**
     * Saantimetodi muistin määrälle
     * @param muisti  Muistin määrä tietokoneessa
     */
    public void setMuisti(double muisti) {
        this.muisti = muisti;
    }
    
    /**
     * Saantimetodi kiintolevytilalle
     * @param kiintolevy Kiintolevytila tietokoneessa
     */
    public void setKiintolevy(double kiintolevy) {
        this.kiintolevy = kiintolevy;
    }
    
    /**
     * Saantimetodi prosessorin kellotaajuudelle
     * @param prosessorintaajuus Prosessorin kellotaajuus tietokoneessa
     */
    public void setProsessorinTaajuus(double prosessorintaajuus) {
        this.prosessorintaajuus = prosessorintaajuus;
    }
    
    /**
     * Saantimetodi prosessorin ytimien lukumäärälle
     * @param ytimet Prosessorin ytimien lukumäärä
     */
    public void setYtimet(int ytimet) {
        this.ytimet = ytimet;
    }
    
    /**
     * Sijoitusmetodi prosessorin säikeiden lukumäärälle
     * @param saikeet Prosessorin säikeiden lukumäärä
     */
    public void setSaikeet(int saikeet) {
        this.saikeet = saikeet;
    }
    
    /**
     * Sijoitusmetodi videomuistin määrälle
     * @param videomuisti Videomuistin määrä
     */
    public void setVideomuisti(double videomuisti) {
        this.videomuisti = videomuisti;
    }
    
    @Override
    public String toString() {
        return muisti + "|" + kiintolevy + "|" + prosessorintaajuus + "|" + ytimet + "|" + saikeet + "|" + videomuisti;
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Tietokone tietsikka = new Tietokone(256, 100, 1.5, 2, 4, 2);
        System.out.println(tietsikka.toString()); 
        System.out.println("Tietsikassa on muistia " + tietsikka.getMuisti() + " MB, kiintolevytilaa " + tietsikka.getKiintolevy() + " GB jne.");
        tietsikka.setMuisti(512);
        System.out.println("Oho, lisättiin muistia, nyt on " + tietsikka.getMuisti() + " MB");
        Tietokone huomsaikeet = new Tietokone(256, 100, 1.5, 2);
        System.out.println(huomsaikeet.toString()); // Huomaa säikeiden määrä = ydinten määrä
        
        /* 01 */  int a=23,b=13,c=17;         
        /* 02 */  char m = 'b';
        /* 03 */  if ( ( a = b ) != 0 ) c+=0x0f;
        /* 04 */  if ( ( a & ~b ) != 0 ) c--;
        /* 05 */  m ^= 1 << 5;
        /* 06 */  if ( m == 'B' ) b &= c;
        /* 07 */  System.out.print("a=" + a + " b=" + b + " c=" + c + " m=" +m );
    }
}
