using System;
using System.IO;

namespace Tehtava19
{
    class Program
    {
        static void Main(string[] args)
        {
            string tiedosto = "tiedot.txt";
            try
            {
                string teksti = File.ReadAllText(tiedosto);
                TulostaJSONTaulukot(teksti);
            }
            catch (FileNotFoundException)
            {
                Console.WriteLine($"Tiedostoa {tiedosto} ei löytynyt");
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
            }
        }

        public static void TulostaJSONTaulukot(string json)
        {
            bool kirjoita = true;
            // Ensimmäinen kirjoitettava asia on taulukon avain (otsikko), joka halutaan kirjoittaa isoilla kirjaimilla.
            bool isona = true;

            for (int i = 0; i < json.Length; i++)
            {
                char kirjain = json[i];

                switch (kirjain)
                {
                    // Taulukko alkaa, lopetetaan kirjoittaminen kunnes ensimmäinen oliolohko alkaa.
                    case '[':
                        kirjoita = false;
                        isona = false;
                        break;
                    // Taulukko päättyy, aletaan kirjoittamaan seuraavan taulukon avainta (otsikkoa) isoin kirjaimin.
                    case ']':
                        kirjoita = true;
                        isona = true;
                        break;
                    // Oliolohko alkaa, aletaan kirjoittamaan jäseniä (avain/arvo-pareja) uudelta riviltä.
                    case '{':
                        kirjoita = true;
                        Console.WriteLine();
                        break;
                    // Oliolohko päättyy, lopetetaan kirjoittaminen.
                    case '}':
                        kirjoita = false;
                        break;
                    // Muiden merkkien tapauksessa:
                    default:
                        if (kirjoita)
                        {
                            // Jos ollaan taulukon avaimessa (otsikossa), muutetaan kirjain isoksi.
                            if (isona)
                            {
                                Console.Write(char.ToUpper(kirjain));
                            }
                            else
                            {
                                Console.Write(kirjain);
                            }
                        }
                        break;
                }
            }
        }
    }
}
