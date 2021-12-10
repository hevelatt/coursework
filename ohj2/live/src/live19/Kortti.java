package live19;

/**
 * @author Herman
 * @version 3.7.2020
 *
 */
public class Kortti {

    /**
     * @author Herman
     * @version 3.7.2020
     *
     */
    public static enum Maa {
        /**
        * 
        */
        pata,
        /**
        * 
        */
        hertta,
        /**
        * 
        */
        risti,
        /**
        * 
        */
        ruutu
    }

    private Maa maa;

    /**
     * @param maa kortin maa
     */
    public Kortti(Maa maa) {
        this.maa = maa;
    }


    @Override
    public String toString() {
        return "Olen pelikortti ja maani on " + this.maa + ".";
    }


    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kortti k = new Kortti(Maa.pata);
        int maajakauma[] = new int[Maa.values().length];
        for (Maa m : Maa.values())
            maajakauma[m.ordinal()] = 13;
        System.out.println(k);
        System.out.println(Maa.values()[1]);
        System.out.println(Maa.values()[2].ordinal());
        System.out.println(Maa.ruutu.ordinal());
        System.out.println(maajakauma[Maa.hertta.ordinal()]);
    }
}