package jarj;
import java.util.Scanner; // tarvitaan tietojen syöttöön
/**
 * Ohjelma järjestää kolme merkkijonoa aakkosjärjestykseen
 * @author vesal
 * @version 21.1.2018
 */
public class Jarj3a {

    private static final Scanner inputScanner = new Scanner(System.in);

    /**
     * Kysyy käyttäjältä merkkijonon annetulla tulosteella.
     *  @param prompt lause, joka käyttäjä näkee ennen tietojen syöttöä
     *  @return käyttäjän syöttämä merkkijono ilman rivinvaihtoa
     */
    public static StringBuilder kysy(String prompt) {
        System.out.print(prompt);
        return new StringBuilder(inputScanner.nextLine());
    }


    /**
     * Vaihtaa kaksi merkkijonoa keskenään.
     *
     * @param a ensimmäinen vaihdettavista merkkijonoista.
     * @param b toinen vaihdettavista merkkijonoista.
     */
    public static void vaihda(StringBuilder a,StringBuilder b) {
        String t;
        t = new String(a.toString());
        a.delete(0,a.length());
        a.append(b);
        b.delete(0,b.length());
        b.append(t);
    }


    /**
     * Pääohjelma
     * @param args Komentorivillä annetut parametetrit
     */
    public static void main(String[] args) {
        StringBuilder s1,s2,s3;
        s1 = kysy("Anna 1. merkkijono >");
        s2 = kysy("Anna 2. merkkijono >");
        s3 = kysy("Anna 3. merkkijono >");
        System.out.println("Jonot "+s1+", "+s2+", "+s3+".");

        if ( s1.toString().compareTo(s2.toString())>0 ) vaihda(s1,s2);
        if ( s1.toString().compareTo(s3.toString())>0 ) vaihda(s1,s3);
        if ( s2.toString().compareTo(s3.toString())>0 ) vaihda(s2,s3);
        System.out.println("Jonot "+s1+", "+s2+", "+s3+".");
    }
}