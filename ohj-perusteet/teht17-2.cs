using System;

namespace Tehtava17
{
    class Program
    {
        private enum Valinnat { lisaaNimi, lisaaIka, naytaNimi, naytaIka, poistu }

        static void Main(string[] args)
        {
            // Valikon aloitusindeksi, ei saa muuttua ajon aikana.
            // Pitää valikon ja valinnan numerot synkronoituina.
            const int aloitus = 1;
            int valinta;
            string nimi = "";
            bool poistu = false;
            bool tulosta = true;
            bool syntymapaivaSaatu = false;
            DateTime syntymapaiva = new DateTime();

            Console.Write("Tervetuloa ohjelmaan. ");

            while (!poistu)
            {
                // Valikon tulostaminen.
                if (tulosta)
                {
                    TulostaValikko(aloitus);
                }
                tulosta = true;
                // Syötteen kysyminen.
                while (!int.TryParse(KysySyote(), out valinta))
                {
                    Console.WriteLine("Virheellinen valinta.");
                }
                // Syötteen käsittely.
                switch (valinta - aloitus)
                {
                    case (int)Valinnat.lisaaNimi:
                        Console.WriteLine("Anna oma nimesi:");
                        nimi = KysySyote();
                        break;
                    case (int)Valinnat.lisaaIka:
                        do
                        {
                            Console.WriteLine("Anna oma syntymäpäiväsi (pp.kk.vvvv):");
                        } while (!DateTime.TryParse(KysySyote(), out syntymapaiva));
                        syntymapaivaSaatu = true;
                        break;
                    case (int)Valinnat.naytaNimi:
                        if (nimi == "")
                        {
                            Console.WriteLine("Et ole kertonut nimeäsi.");
                        }
                        else
                        {
                            Console.WriteLine($"Nimesi on {nimi}.");
                        }
                        break;
                    case (int)Valinnat.naytaIka:
                        if (syntymapaivaSaatu)
                        {
                            Console.WriteLine("Ikäsi on {0}.",
                                Math.Floor( // Pyöristetään alaspäin...
                                    // ... päivien erotus jaettuna vuoden pituus päivinä.
                                    (DateTime.Today - syntymapaiva).TotalDays / 365.25)); 
                        }
                        else
                        {
                            Console.WriteLine("Et ole kertonut ikääsi.");
                        }
                        break;
                    case (int)Valinnat.poistu:
                        Console.WriteLine("Poistutaan ohjelmasta.");
                        poistu = true;
                        break;
                    default:
                        Console.WriteLine($"Ei toimintoa ({valinta}).");
                        // Ei tulosteta valikkoa uudelleen virheellisen syötteen jälkeen.
                        // Yhdenmukainen ohjelman muun toiminnan kanssa (Syötteen kysyminen).
                        tulosta = false;
                        break;
                }
            }
        }

        /// <summary>
        /// Tulostaa valikon vakiolistan Valinnat jäsenistä.
        /// </summary>
        /// <param name="i">Numero, josta aloitetaan valintojen numerointi.</param>
        private static void TulostaValikko(int i)
        {
            Console.WriteLine("Siirry valikossa valitsemalla numero.");
            foreach (Valinnat valinta in Enum.GetValues(typeof(Valinnat)))
            {
                Console.Write($"({i++}) ");
                switch (valinta)
                {
                    case Valinnat.lisaaNimi:
                        Console.WriteLine("Syötä nimesi.");
                        break;
                    case Valinnat.lisaaIka:
                        Console.WriteLine("Syötä ikäsi.");
                        break;
                    case Valinnat.naytaNimi:
                        Console.WriteLine("Näytä nimesi.");
                        break;
                    case Valinnat.naytaIka:
                        Console.WriteLine("Näytä ikäsi.");
                        break;
                    case Valinnat.poistu:
                        Console.WriteLine("Poistu ohjelmasta.");
                        break;
                    default:
                        // Jos vakiolistan jäsenelle ei ole määritelty erityistä valikkotekstiä,
                        // on valikkotekstinä itse vakiolistan jäsen.
                        Console.WriteLine(valinta);
                        break;
                }
            }
        }

        /// <summary>
        /// Kysyy käyttäjältä merkkijonosyötteen.
        /// </summary>
        /// <returns>Käyttäjän syöttämä merkkijono.</returns>
        public static string KysySyote()
        {
            Console.Write("> ");
            return Console.ReadLine();
        }
    }
}
