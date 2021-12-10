package demo.d8;

/**
 * @author Herman
 * @version 27.6.2020
 *
 */
public class Taulukot {

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        double[][] matriisi = { { 3.3, 6.5, -1.5 }, { 0.0, 50.0, 23.1 } };
        System.out.println(matriisinSuurin(matriisi));
    }


    /**
    * Palauttaa kaksiulotteisen reaalilukutaulukon (matriisi) suurimman alkion arvon<br/>
    * Oletetaan, että taulukon rivit ovat saman mittaisia (taulukko on matriisi)
    * @param matriisi Taulukko, josta suurinta arvoa etsitään
    * @return Matriisin suurin reaaliluku
    * @example
    * <pre name="test">
    * double[][] matriisi = { { 3.3,  6.5, -1.5 }, { 0.0, 50.0, 23.1 } };
    * matriisinSuurin(matriisi) ~~~ 50.0; // normaali tapaus
    * matriisi = new double[][]{ { 3.3, 60.5, -1.5 }, { 0.0, 50.0, 23.1 } };
    * matriisinSuurin(matriisi) ~~~ 60.5; // suurin ensimmäisellä rivillä
    * matriisi = new double[][]{ { 3.3 }, { 0.0 } };
    * matriisinSuurin(matriisi) ~~~ 3.3; // eri kokoinen
    * matriisi = new double[][]{ { -3.3, -2.1 }, { 0.0, -6.5 } };
    * matriisinSuurin(matriisi) ~~~ 0.0; // negatiiviset luvut
    * matriisi = new double[][]{ {}, {} };
    * matriisinSuurin(matriisi) ~~~ Double.MIN_VALUE; // tyhjä
    * </pre>
    */
    public static double matriisinSuurin(double[][] matriisi) {
        double suurin = Double.MIN_VALUE;
        
        for (int i = 0; i < 2; i++) // matriisi 2-ulotteinen
            for (int j = 0; j < matriisi[0].length; j++)
                if (matriisi[i][j] > suurin) 
                    suurin = matriisi[i][j];
        return suurin;
    }

}