package demo.d6;

/**
 * @author Herman
 * @version 19.6.2020
 *
 */
public class TaulukonKasittely {

    /**
     * Aliohjelma palauttaa kokonaislukutaulukon pienimmän alkion indeksin
     * @param t Taulukko, josta etsitään pienimmän alkion indeksiä
     * @return Palauttaa pienimmän alkion indeksin
     * @example
     * <pre name="test">
     * int[] t = {4, 2, 1, 0, 3};
     * pienimmanPaikka(t) === 3;
     * int[] t2 = {4, 2, -1, 0, 3};
     * pienimmanPaikka(t2) === 2;
     * int[] t3 = {};
     * pienimmanPaikka(t3) === -1;
     * int[] t4 = {5};
     * pienimmanPaikka(t4) === 0;
     * int[] t5 = {5, 5, 5, 5};
     * pienimmanPaikka(t5) === 0;
     * int[] t6 = {5, 6, 5, 5};
     * pienimmanPaikka(t6) === 0;
     * </pre>
     */
    public static int pienimmanPaikka(int[] t) {
        int pienin = Integer.MAX_VALUE;
        int paikka = -1;
        for (int i = 0; i < t.length; i++)
            if (t[i] < pienin) {
                pienin = t[i];
                paikka = i;
            }
        return paikka;
    }
    
    /**
     * Aliohjelma palauttaa kokonaislukutaulukon pienimmän alkion
     * @param t Taulukko, jonka pienintä alkiota etsitään
     * @return Palauttaa pienimmän alkion
     * @example
     * <pre name="test">
     * int[] t = {4, 2, 1, 0, 3};
     * pienin(t) === 0;
     * int[] t2 = {4, 2, -1, 0, 3};
     * pienin(t2) === -1;
     * int[] t3 = {};
     * pienin(t3) === Integer.MAX_VALUE;
     * int[] t4 = {5};
     * pienin(t4) === 5;
     * int[] t5 = {5, 5, 5, 5};
     * pienin(t5) === 5;
     * int[] t6 = {5, 6, 5, 5};
     * pienin(t6) === 5;
     * </pre>
     */
    public static int pienin(int[] t) {
        int pienin = Integer.MAX_VALUE;
        for (int luku : t)
            if (luku < pienin)
                pienin = luku;
        return pienin;
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        /*ta,he,ma,hu,to,ke,he,el,sy,lo,ma,jo*/
        int kPituudet[] = {31,28,31,30,31,30,31,31,30,31,30,31};
        System.out.println(pienimmanPaikka(kPituudet)); // 1
        System.out.println(pienin(kPituudet)); // 28       
    }
}
