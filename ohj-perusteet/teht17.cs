using System;

namespace Tehtava17
{
    class Program
    {
        private enum Valinnat { lisaaNimi, lisaaIka, naytaNimi, naytaIka, poistu }
        static void Main(string[] args)
        {
            const int aloitus = 1; // valikon aloitusindeksi
            int valinta;
            const string virheilmoitus = "Virheellinen valinta.";
            string nimi = "";
            // string ika = "";
            bool poistu = false;
            bool tulosta = true;
            bool syntymapaivaSaatu = false;
            DateTime syntymapaiva = new DateTime();

            Console.Write("Tervetuloa ohjelmaan. ");

            while (!poistu)
            {
                // Valikon tulostaminen
                if (tulosta)
                {
                    TulostaValikko(aloitus);
                }
                tulosta = true;
                // Syötteen kysyminen
                while (!int.TryParse(KysySyote(), out valinta))
                {
                    Console.WriteLine(virheilmoitus);
                }
                // Syötteen käsittely
                switch (valinta - aloitus)
                {
                    case (int)Valinnat.lisaaNimi:
                        Console.WriteLine("Anna oma nimesi:");
                        nimi = KysySyote();
                        break;
                    case (int)Valinnat.lisaaIka:
                        // Vaihe 1:
                        // Console.WriteLine("Anna oma ikäsi:");
                        // ika = KysySyote();
                        // Vaihe 2:
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
                        // Vaihe 1:
                        // if (ika == "")
                        // {
                        //     Console.WriteLine("Et ole kertonut ikääsi.");
                        // }
                        // else
                        // {
                        //     Console.WriteLine($"Ikäsi on {ika}.");
                        // }
                        // Vaihe 2:
                        if (syntymapaivaSaatu)
                        {
                            Console.WriteLine("Ikäsi on {0}.",
                                Math.Floor((DateTime.Today - syntymapaiva).TotalDays / 365.25));
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
                        Console.WriteLine(virheilmoitus);
                        // Ei tulosteta valikkoa uudelleen virheellisen syötteen jälkeen
                        // Yhdenmukainen ohjelman muun toiminnan kanssa (int.TryParse())
                        tulosta = false;
                        break;
                }
            }
        }
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
                }
            }
        }
        public static string KysySyote()
        {
            Console.Write("> ");
            return Console.ReadLine();
        }
    }
}
