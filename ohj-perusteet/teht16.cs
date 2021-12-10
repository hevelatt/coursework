using System;

namespace Tehtava16
{
    class Program
    {
        // Tehtävässä oli tarkoitus harjoitella switch-case-rakenteen käyttöä, minkä vuoksi valinta on toteutettu sitä käyttäen.
        static void Main(string[] args)
        {
            // Rivi tulostaa valikon ja kysyy uutta valintaa kunnes käyttäjä syöttää validin viikonpäivän
            while (!TulostaValittuViikonpäivä(KysySyoteTaiMerkki(TulostaValikko(new string[] { "maanantai", "tiistai", "keskiviikko", "torstai", "perjantai", "lauantai", "sunnuntai" })))) ;

            // Lähes sama asia selkeämmin, erona on valikon tulostaminen vain kerran (while-silmukan ulkopuolella)
            Console.WriteLine("~~~~~~~~~~~~~~~~~~~~~\nValitse arvosana:");
            string[] arvosanat = { "hylätty", "huono", "välttävä", "tyydyttävä", "hyvä", "kiitettävä", "erinomainen" };
            // merkkiko on palautusarvo aliohjelmasta TulostaValikko ja se on true jos suurin valinnan numero on < 10 (ja pienin >= 0)
            bool merkkiko = TulostaValikko(arvosanat, 4); // Valikon tulostaminen alkaa luvusta 4
            bool jatka = true;
            while (jatka)
            {
                // Aliohjelma tarvitsee parametrikseen tiedon siitä, kysytäänkö käyttäjältä yhtä merkkiä vai merkkijonoa
                string syote = KysySyoteTaiMerkki(merkkiko);
                // muuttuja jatka on aliohjelman palautuksen negaatio
                // siis jatketaan jos ei onnistuta tulostamaan arvosanoja (palautus false -> jatka true)
                jatka = !TulostaValittuArvosana(syote);
            }
        }

        /// <summary>
        /// Tulostaa annetun merkkijonotaulukon numeroituna taulukkona
        /// </summary>
        /// <param name="valinnat">Tulostettavat valinnat merkkijonotaulukossa</param>
        /// <param name="aloitus">Mistä numerosta aloitetaan valintojen numeroiminen</param>
        /// <returns>True jos valintojen numerot ovat kaikki vain yhden merkin pituisia (< 10 ja >= 0) </returns>
        public static bool TulostaValikko(string[] valinnat, int aloitus = 1)
        {
            for (int i = 0; i < valinnat.Length; i++)
            {
                Console.WriteLine($"({aloitus + i}) {valinnat[i]}");
            }
            return !(aloitus + valinnat.Length > 10 || aloitus < 0);
        }

        /// <summary>
        /// Tulostaa viikonpäivän valinnan mukaan (1-7)
        /// </summary>
        /// <param name="valinta">Viikonpäivän numero merkkijonona</param>
        /// <returns>True jos tulostaminen onnistuu, False jos väärä valinta</returns>
        private static bool TulostaValittuViikonpäivä(string valinta)
        {
            switch (valinta)
            {
                case "1":
                    Console.WriteLine("maanantai");
                    break;
                case "2":
                    Console.WriteLine("tiistai");
                    break;
                case "3":
                    Console.WriteLine("keskiviikko");
                    break;
                case "4":
                    Console.WriteLine("torstai");
                    break;
                case "5":
                    Console.WriteLine("perjantai");
                    break;
                case "6":
                    Console.WriteLine("lauantai");
                    break;
                case "7":
                    Console.WriteLine("sunnuntai");
                    break;
                default:
                    Console.WriteLine("Virheellinen valinta.");
                    return false;
            }
            return true;
        }

        /// <summary>
        /// Tulostaa arvosanan valinnan mukaan (4-10)
        /// </summary>
        /// <param name="valinta">Arvosanan numero merkkijonona</param>
        /// <returns>True jos tulostaminen onnistuu, False jos väärä valinta</returns>
        private static bool TulostaValittuArvosana(string valinta)
        {
            switch (valinta)
            {
                case "4":
                    Console.WriteLine("4 on hylätty.");
                    break;
                case "5":
                    Console.WriteLine("5 on huono arvosana.");
                    break;
                case "6":
                    Console.WriteLine("6 on välttävä arvosana.");
                    break;
                case "7":
                    Console.WriteLine("7 on tyydyttävä arvosana.");
                    break;
                case "8":
                    Console.WriteLine("8 on hyvä arvosana.");
                    break;
                case "9":
                    Console.WriteLine("9 on kiitettävä arvosana. ");
                    break;
                case "10":
                    Console.WriteLine("10 on erinomainen arvosana.");
                    break;
                default:
                    Console.WriteLine("Virheellinen valinta.");
                    return false;
            }
            return true;
        }

        /// <summary>
        /// Kysyy käyttäjältä syötemerkin
        /// </summary>
        /// <returns>Palauttaa syötetyn merkin</returns>
        public static char KysySyoteMerkki()
        {
            Console.Write("> ");
            char syote = Console.ReadKey().KeyChar;
            Console.WriteLine();
            return syote;
        }

        /// <summary>
        /// Kysyy käyttäjältä syötteen
        /// </summary>
        /// <returns>Palauttaa syötetyn merkkijonon</returns>
        public static string KysySyote()
        {
            Console.Write("> ");
            return Console.ReadLine();
        }

        /// <summary>
        /// Kysyy joko merkin tai syötteen, jos kysyy merkkiä niin palauttaa merkkijonona
        /// </summary>
        /// <param name="merkki">Onko syöte merkki?</param>
        /// <returns>Käyttäjän syöte merkkijonona</returns>
        public static string KysySyoteTaiMerkki(bool merkki)
        {
            if (merkki)
                return KysySyoteMerkki().ToString();
            else
                return KysySyote();
        }
    }
}
