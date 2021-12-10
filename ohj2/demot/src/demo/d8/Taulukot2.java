package demo.d8;

/**
 * @author Herman
 * @version 27.6.2020
 *
 */
public class Taulukot2 {

    /**
    * @param args ei käytössä
    */
    public static void main(String[] args) {
        double[][] matriisi = { { 3.3, 6.5, -1.5 }, { 0.0, 50.0, 23.1 } };
        System.out.println(summa(matriisi));
    }


    /**
     * Laskee 2-ulotteisen reaalilukumatriisin alkioiden summan<br/>
     * Oletetaan, että taulukon rivit ovat saman mittaisia (taulukko on matriisi)
     * @param matriisi Matriisi, jonka alkioiden summaa lasketaan
     * @return 2-ulotteisen reaalilukumatriisin alkioiden summa
     * @example
     * <pre name="test">
     * double[][] matriisi = { { 3.3,  6.5, -1.5 }, { 0.0, 50.0, 23.1 } };
     * summa(matriisi) ~~~ 81.4; // normaali tapaus
     * matriisi = new double[][]{ { 3.3 }, { 0.0 } };
     * summa(matriisi) ~~~ 3.3; // eri kokoinen
     * matriisi = new double[][]{ { -3.3, -2.1 }, { 0.0, -6.5 } };
     * summa(matriisi) ~~~ -11.9; // negatiiviset luvut
     * matriisi = new double[][]{ {}, {} };
     * summa(matriisi) ~~~ 0; // tyhjä
     * </pre>
     */
    public static double summa(double[][] matriisi) {
        double summa = 0;
        for (int i = 0; i < 2; i++)
            summa += demo.d7.Taulukot.summa(matriisi[i]);
        return summa;  
    }

}