using System;

namespace Tehtava8_3
{
    /// <summary>
    /// Ohjelma antaa käyttäjälle taulukon tietoja määritellyn sarakkeen perusteella kysymällä syötettä toistuvasti.
    /// Käyttäjä antaa syötteenä joko määritellyn kentän tiedon tai järjestysnumeron tai lopetusmerkkijonon.
    /// </summary>
    class Program
    {
        static void Main(string[] args)
        {
            //--Alustus--
            int c = 0; // Sarake, jonka perusteella kysellään tietoja
            string[,] table =
            {
                { "HERO"       , "ROLE"   , "HEALTH", "ARMOR" }, // Taulukon ensimmäinen rivi varattu otsikkotiedoille
                { "Ana"        , "Support", "200"   , "0"     },
                { "Bastion"    , "Defense", "200"   , "100"   },
                { "Brigitte"   , "Support", "200"   , "50"    },
                { "D.Va (Mech)", "Tank"   , "400"   , "200"   }
            };
            int rows    = table.GetLength(0); // Rivien määrä (5), käytetään myöhemmin silmukoissa
            int columns = table.GetLength(1); // Sarakkeiden määrä (4), käytetään myöhemmin silmukoissa
            string header = table[0, c]; // Sarakkeen c otsikko (HERO)
            string input; // Varataan merkkijonomuuttuja syötettä varten
            string stop = "q"; // Lopetusmerkkijono, joka lopettaa kyselyn

            //--Ohjeet--
            Console.WriteLine($"Select {header}:"); // Pyydetään valintaa sarakkeen c perusteella
            for (int i = 1; i < rows; i++) // Käydään läpi rivit. Huom: Otsikkorivi ohitetaan aloittamalla indeksistä 1
            {
                //                        {Sarake c   }
                Console.WriteLine($"({i}) {table[i, c]}"); // Tulostetaan sarakkeen c kaikki tiedot numeroituna
            }
            Console.WriteLine($"({stop}) to quit"); // Lisää ohjeita (kuinka poistutaan ohjelmasta)
            
            //--Syöte--
            Console.Write("> "); // Syöteosoitin
            while ((input = Console.ReadLine()) != stop) // Kysytään syötettä kunnes käyttäjä antaa lopetusmerkkijonon
            {
                bool found = false; // Aputotuusarvo: Löydetäänkö valinta?
                for (int i = 1; i < rows; i++) // Käydään läpi rivit. Huom: Ei verrata otsikkoriviin (aloitusindeksi 1)
                {
                    // Onko syöte sama kuin sarakkeen c tieto (ei oteta huomioon kirjainten kokoa ja kulttuurillisia eroja)...
                    if (input.Equals(table[i, c], comparisonType: StringComparison.InvariantCultureIgnoreCase)
                        || input == $"{i}") // ... TAI onko syöte sama kuin järjestysnumero (i merkkijonona)?
                    {
                        string separator = ""; // Alustetaan välistysmerkkijono
                        // Kun vastaavuus löydetään niin...
                        for (int j = 0; j < columns; j++) // ... käydään läpi ja tulostetaan kaikkien sarakkeiden tiedot
                        {
                            //              {Välistys }{Otsikko    }: {Tiedot     }
                            Console.Write($"{separator}{table[0, j]}: {table[i, j]}"); // Otsikko1: Tieto1 | Otsikko2: Tieto2
                            separator = " | "; // Välistysmerkkijono tästä eteenpäin
                        }
                        found = true; // Löydettiin vastaavuus
                        Console.WriteLine(); // Rivinvaihto
                    }
                }
                if (!found) // Jos vastaavuutta ei löydetty
                {
                    //                  {otsikko} "{syöte}"
                    Console.WriteLine($"{header} \"{input}\" not found!");
                }
                Console.Write("> "); // Syöteosoitin
            }
        }
    }
}
