package demo.d7;

/**
 * @author Herman
 * @version 23.6.2020
 *
 */
public class Taulukot {

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        double[] pisteet = {19,18,19.5,18,20};
        System.out.print("Tuomarit antavat pisteet: ");
        for (int i = 0; i < pisteet.length; i++)
            if (i == pisteet.length - 1)
                System.out.println(pisteet[i] + ".");
            else
                System.out.print(pisteet[i] + ", ");
        System.out.println("Lopullinen pistemäärä on siis: " + summaHuonoinJaParasPois(pisteet));
    }


    /**
    * Etsitään paras (suurin) luku taulukosta
    * @param t Taulukko, josta parasta (suurinta) lukua etsitään
    * @return Taulukon suurin luku, 0 jos taulukko on tyhjä
    * @example
    * <pre name="test">
    * paras(new double[]{5, 7, 3, 2, 1}) ~~~ 7;
    * paras(new double[]{5.5, 5.6, 5.65}) ~~~ 5.65;
    * paras(new double[]{1}) ~~~ 1;
    * paras(new double[]{-5, -7, -3, -2, -1}) ~~~ -1;
    * paras(new double[]{}) ~~~ 0;
    * </pre>
    */
    public static double paras(double[] t) {
        if (t.length == 0) return 0;
        double paras = t[0];
        
        for (double luku: t)
            if (luku > paras) paras = luku;
        
        return paras;
    }


    /**
    * Etsitään huonoin (pienin) luku taulukosta
    * @param t Taulukko, josta huonointa (pienintä) lukua etsitään
    * @return Taulukon pienin luku, 0 jos taulukko on tyhjä
    * @example
    * <pre name="test">
    * huonoin(new double[]{5, 7, 3, 2, 1}) ~~~ 1;
    * huonoin(new double[]{5.5, 5.6, 5.65}) ~~~ 5.5;
    * huonoin(new double[]{1}) ~~~ 1;
    * huonoin(new double[]{-5, -7, -3, -2, -1}) ~~~ -7;
    * huonoin(new double[]{}) ~~~ 0;
    * </pre>
    */
    public static double huonoin(double[] t) {
        if (t.length == 0) return 0;
        double huonoin = t[0];
        
        for (double luku: t)
            if (luku < huonoin) huonoin = luku;
        
        return huonoin;
    }


    /**
    * Summaa taulukon alkiot yhteen
    * @param t Taulukko, jonka alkiot summataan
    * @return Taulukon alkioiden summa
    * @example
    * <pre name="test">
    * summa(new double[]{5, 7, 3, 2, 1}) ~~~ 18;
    * summa(new double[]{5.5, 5.6, 5.65}) ~~~ 16.75;
    * summa(new double[]{1}) ~~~ 1;
    * summa(new double[]{-5, -7, -3, -2, -1}) ~~~ -18;
    * summa(new double[]{}) ~~~ 0;
    * </pre>
    */
    public static double summa(double[] t) {
        if (t == null) return 0;
        double summa = 0;
        
        for (double luku: t)
            summa += luku;
        
        return summa;
    }


    /**
    * Summaa taulukon alkiot yhteen lukuunottamatta huonointa ja parasta (pienintä ja suurinta)
    * @param t Taulukko, jonka alkiot, paitsi suurin ja pienin, summataan
    * @return Taulukon alkoiden summa, paitsi suurin ja pienin
    * @example
    * <pre name="test">
    * summaHuonoinJaParasPois(new double[]{5, 7, 3, 2, 1}) ~~~ 10;
    * summaHuonoinJaParasPois(new double[]{5.5, 5.6, 5.65, 5.76, 4.43}) ~~~ 16.75;
    * summaHuonoinJaParasPois(new double[]{1,2,3}) ~~~ 2;
    * summaHuonoinJaParasPois(new double[]{1,2}) ~~~ 0;
    * summaHuonoinJaParasPois(new double[]{1}) ~~~ 0;
    * summaHuonoinJaParasPois(new double[]{-5, -7, -3, -2, -1}) ~~~ -10;
    * summaHuonoinJaParasPois(new double[]{}) ~~~ 0;
    * </pre>
    */
    public static double summaHuonoinJaParasPois(double[] t) {
        if (t.length <= 2) return 0;
        return summa(t) - paras(t) - huonoin(t);
    }
}