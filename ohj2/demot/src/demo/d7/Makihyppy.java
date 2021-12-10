package demo.d7;
/**
 * @author Herman
 * @version 23.6.2020
 *
 */
public class Makihyppy {
    /**
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        /* tonin sijoitukset */
        Kilpailija toni = new Kilpailija();
        toni.nimi = new String("Toni N").toCharArray();
        toni.nro = 3;
        toni.tulos.kierros[0].pituus = 107;
        toni.tulos.kierros[1].tuomarit = new double[]{19,18,19.5,18,20};
        /* matin sijoitukset */
        Kilpailija matti = new Kilpailija();
        matti.nimi = new String("Matti H").toCharArray();
        matti.nro = 7;
        matti.tulos.kierros[1].pituus = 109;
        matti.tulos.kierros[0].pisteet = 125;
        matti.tulos.lopputulos = 251;
    }
}

class Kilpailija {
    char nimi[] = new char[8];
    int  nro;
    Tulos tulos = new Tulos();
    
    //public Kilpailija() { }
}

class Tulos {
    Kierros[] kierros = new Kierros[]{new Kierros(), new Kierros()}; // new Kierros[2]
    double lopputulos;
    
    //public Tulos() { }
}

class Kierros {
    double pituus;                      /* hyppyjen pituudet metreinä */
    double[] tuomarit = new double[5];  /* tuomaripisteet             */
    double pisteet;                     /* Yhteistulos                */
    
    //public Kierros() { }
}