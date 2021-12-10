package demo.d6.perinta;

/**
 * Kissa-luokka
 * @author Vesa Lappalainen
 * @version 1.0, 06.02.2003
 * @example
 * <pre name="test">
 *   Kissa miuku = new Kissa("Miuku",1200);
 *   miuku.toString() === "Miuku 1200.0";
 * </pre>
 */
public class Kissa extends Elain { // kissa on elain  cat is a animal  

    /**
     * Alustetaan kissan nimi ja paino
     * @param nimi kissan nimi
     * @param paino kissan paino
     */
    public Kissa(String nimi, double paino) {
        super(nimi, paino);
    }

 
    /**
     * Äännellään kuten kissa
     */
    @Override
    public void aantele() {
        System.out.println("Miuaaa");
    }


    /**
     * Testataan Kissa-luokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {

        Kissa miuku = new Kissa("Miuku", 18000);
        miuku.aantele();
        System.out.println(miuku);

        Kissa mirri = new Kissa("Mirri", 930);
        mirri.aantele();
        System.out.println(mirri);
    }

}
