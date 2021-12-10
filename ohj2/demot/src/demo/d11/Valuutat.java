package demo.d11;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import fi.jyu.mit.ohj2.Mjonot;
import fi.jyu.mit.ohj2.Syotto;

/**
 * Luokka valuuttakurssien käsittelemiselle
 * @author Herman
 * @version 18.7.2020
 *
 */
public class Valuutat {
    
    /*private static final Valuutta[] kurssit = new Valuutta[]{
            new Valuutta("mk"   ,     1.0),
            new Valuutta("$"    ,     5.7),
            new Valuutta("EUROA", 5.94573),
            new Valuutta("SKr"  ,     0.6)};*/
    
    /**
     * @param args Luettavan tiedoston nimi
     */
    public static void main(String[] args) {
        // Luetaan tekstitiedosto ja talletetaan ArrayListiin
        String tiedNimi = "valuutat.txt";
        if (args.length > 0)
            tiedNimi = args[0];
        var kurssit = new ArrayList<Valuutta>();
        try (Scanner fi = new Scanner(new FileInputStream(new File(tiedNimi)))) {
            String trivi;
            while (fi.hasNext()) {
                trivi = fi.nextLine();
                StringBuilder sb = new StringBuilder(trivi);
                String raha = Mjonot.erota(sb, '|');
                String valuutta = Mjonot.erota(sb, '|'); 
                kurssit.add(new Valuutta(valuutta, Mjonot.erotaDouble(raha, 0)));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Tiedosto ei aukea! " + e.getMessage());
        }
        // Kysytään käyttäjältä syöte
        while(true) {
            String rivi = Syotto.kysy("Määrä ja valuutta");
            if (rivi.length() == 0 || rivi.equalsIgnoreCase("loppu")) {
                System.out.println("Kiitos!");
                break;
            }

            StringBuilder sb = new StringBuilder(rivi);
            String raha = Mjonot.erota(sb);
            String valuutta = Mjonot.erota(sb); 
            
            System.out.println(raha + " " + valuutta + " on " + 
                    vaihdaValuutta(Mjonot.erotaDouble(raha, 0), valuutta, kurssit) + " mk.");
        }
    }

    /**
     * Aliohjelma valuutan vaihtamista varten
     * @param raha Mikä määrä rahaa vaihdetaan
     * @param valuutta Mitä valuuttaa vaihdetaan
     * @param kurssit Valuuttakurssien taulukkolista
     * @return Rahamäärä markkoina, jos ei tunneta valuuttaa niin 0
     */
    public static double vaihdaValuutta(double raha, String valuutta, ArrayList<Valuutta> kurssit) {
        for(Valuutta kurssi : kurssit) {
            if (kurssi.getNimi().equalsIgnoreCase(valuutta))
                return raha * kurssit.get(0).getKurssi() * kurssi.getKurssi();
        }
        return 0;
    }
}

class Valuutta {
    private String nimi;
    private double kurssi;
    
    public Valuutta(String nimi, double kurssi) {
        this.nimi = nimi;
        this.kurssi = kurssi;
    }
    
    public String getNimi() {
        return nimi;
    }
    
    public double getKurssi() {
        return kurssi;
    }
}