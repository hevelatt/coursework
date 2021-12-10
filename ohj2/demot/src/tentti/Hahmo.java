package tentti;

import java.util.ArrayList;




//BYCODEBEGIN
//Tehdään samaan tiedostoon, jolloin public jää pois

interface HahmoRajapinta {
    /**
     * Saantimetodi hahmon nimelle
     * @return Palauttaa hahmon nimen
     */
    
    public String getNimi();
    /**
     * Hahmon esittäytyminen
     * @return Palauttaa hahmon esittäytymisen
     */
    public String esittaydy();
}
//BYCODEEND



class Hahmo implements HahmoRajapinta {
    private String nimi;
    
    
    public Hahmo (String nimi) {
        this.nimi = nimi;
    }
    
    @Override
    public String esittaydy() {
        return "Olen " + nimi;
    }
    
    @Override
    public String getNimi() {
        return nimi;
    }
    
    public static void main(String[] args) {
        var hahmot = new ArrayList<HahmoRajapinta>();
        hahmot.add(new Hahmo("Aku Ankka"));
        hahmot.add(new Supersankari("Superman", "Clark Kent"));
        hahmot.add(new Supersankari("Batman", "Bruce Wayne"));
        for (var hahmo : hahmot) {
            System.out.println(hahmo.getNimi() + ": " + hahmo.esittaydy());
        }
    }

    
}

class Supersankari implements HahmoRajapinta {

    private String oikeanimi = "";
    private String nimi = "";
    
    /**
     * Supersankarin muodostaja
     * @param alias Supersankarin supernimi
     * @param nimi Supersankarin oikea nimi
     */
    public Supersankari(String alias, String nimi) {
        this.nimi = alias;
        oikeanimi = nimi;
    }
    
    /**
     * Supersankari esittäytyy oikealla nimellään
     * @return Palauttaa supersankarin oikean nimen
     */
    @Override
    public String esittaydy() {
        return "Olen " + oikeanimi;
    }

    @Override
    public String getNimi() {
        return nimi;
    }
}