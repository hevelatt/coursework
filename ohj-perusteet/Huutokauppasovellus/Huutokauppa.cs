using System;

namespace Huutokauppasovellus
{
    class Huutokauppa
    {
        static int myytyjenLkm = 0;
        static double yhteishinta  = 0;
        static double korkeinHinta = 0;

        private static void Main()
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;
            double tavoite = Huutosilmukka();
            TulostaKorkeinHinta();
            TulostaTavoite(tavoite, false);
        }

        /// <summary>
        /// Huutokaupan pääsilmukka: tulostukset ja syötteen käsittely.
        /// </summary>
        /// <returns>Palauttaa huutokaupan tavoitesumman.</returns>
        private static double Huutosilmukka()
        {
            bool lopeta       = false;
            bool myyty        = false;
            bool pyydaTavoite = true;
            bool virheSyote   = false;
            const string syoteLopeta  = "l";
            const string syoteMyy     = "m";
            const string syoteTavoite = "t";
            double tavoite        = 0;
            double edellinenHinta = 0;
            double myyntihinta    = 0;

            while (!lopeta)
            {
                Console.Clear();
                // Ensimmäisen rivin tulostus.
                if (virheSyote)
                {
                    virheSyote = false;
                    Console.WriteLine("Virheellinen syöte!");
                }
                else if (pyydaTavoite)
                {
                    Console.WriteLine($"Tavoite: {tavoite:c}");
                }
                else
                {
                    Console.WriteLine($"Myyntihinta: {myyntihinta:c}");
                }
                // Muut tulostukset ja myytäessä tietojen päivittäminen.
                if (myyty)
                {
                    UusiMyynti(myyntihinta);
                    TulostaMyyntienLkm();
                    TulostaHalvempiVaiKalliimpi(myyntihinta, edellinenHinta);
                    TulostaKorkeinHinta();
                    TulostaTavoite(tavoite);
                    edellinenHinta = myyntihinta;
                    Console.WriteLine($"({syoteLopeta}) Lopeta huutokauppa." +
                        "\n(Enter) Uusi hinta.");
                }
                else if (pyydaTavoite)
                {
                    Console.WriteLine($"({syoteTavoite}) Aseta uusi tavoite." +
                        "\nAseta tavoite (€):");
                }
                else
                {
                    Console.WriteLine($"({syoteMyy}) Myy artikkeli." +
                        $"\n({syoteLopeta}) Lopeta huutokauppa." +
                        "\nSyötä hinta (€):");
                }
                // Syötteen pyyntö.
                string syote = KysySyote().Trim();
                // Syötteen käsittely.
                if (syote.Equals(syoteLopeta, comparisonType: StringComparison.OrdinalIgnoreCase))
                {
                    lopeta = true;
                }
                else if (syote.Equals(syoteMyy, comparisonType: StringComparison.OrdinalIgnoreCase))
                {
                    pyydaTavoite = false;
                    myyty = true;
                }
                else if (syote.Equals(syoteTavoite, comparisonType: StringComparison.OrdinalIgnoreCase))
                {
                    myyty = false;
                    pyydaTavoite = true;
                }
                else if (myyty)
                {
                    // Jos myyty, ei haluta käsitellä lukusyötteitä kuin vasta ensi kierroksella.
                    myyty = false;
                }
                else
                {
                    bool onLuku = double.TryParse(syote, out double syoteLuku);
                    if (onLuku)
                    {
                        if (pyydaTavoite)
                        {
                            pyydaTavoite = false;
                            tavoite = syoteLuku;
                        }
                        else
                        {
                            myyntihinta = syoteLuku;
                        }
                    }
                    else
                    {
                        virheSyote = true;
                    }
                }
            }
            return tavoite;
        }

        /// <summary>
        /// Pyytää käyttäjältä merkkijonosyötteen.
        /// </summary>
        /// <returns>Käyttäjän syöttämä merkkijono.</returns>
        private static string KysySyote()
        {
            Console.Write("> ");
            return Console.ReadLine();
        }

        /// <summary>
        /// Uusi myynti määritellyllä hinnalla. Päivittää luokan kentät.
        /// </summary>
        /// <param name="myyntiHinta">Hinta, jolla artikkeli myytiin.</param>
        private static void UusiMyynti(double myyntihinta)
        {
            // Päivitetään kenttä myytyjenLkm.
            myytyjenLkm++;
            // Päivitetään kenttä yhteishinta.
            yhteishinta += myyntihinta;
            // Päivitetään kenttä korkeinHinta.
            if (myyntihinta > korkeinHinta)
            {
                korkeinHinta = myyntihinta;
            }
        }

        /// <summary>
        /// Tulostaa tiedon myytyjen artikkeleiden lukumäärästä.
        /// </summary>
        private static void TulostaMyyntienLkm()
        {
            Console.WriteLine($"Myytyjä artikkeleita: {myytyjenLkm}");
        }

        /// <summary>
        /// Tulostaa tiedon huutokaupan korkeimmasta myyntihinnasta.
        /// </summary>
        private static void TulostaKorkeinHinta()
        {
            Console.WriteLine($"Korkein myyntihinta: {korkeinHinta:c}");
        }

        /// <summary>
        /// Tulostaa tiedon siitä, oliko edellinen myynti halvempi vai kalliimpi kuin myyty artikkeli.
        /// </summary>
        /// <param name="hinta">Hinat, jolla artikkeli myytiin.</param>
        /// <param name="edellinenHinta">Hinta, jolla edellinen artikkeli myytiin.</param>
        private static void TulostaHalvempiVaiKalliimpi(double hinta, double edellinenHinta)
        {
            if (hinta > edellinenHinta)
            {
                Console.WriteLine("Artikkeli oli kalliimpi kuin edellinen myynti.");
            }
            else if (hinta < edellinenHinta)
            {
                Console.WriteLine("Artikkeli oli halvempi kuin edellinen myynti.");
            }
            else
            {
                Console.WriteLine("Artikkeli oli saman hintainen kuin edellinen myynti.");
            }
        }

        /// <summary>
        /// Tulostaa tiedon tavoitesumman täyttymisestä.
        /// </summary>
        /// <param name="tavoite">Huutokaupan tavoitesumma.</param>
        /// <param name="tulostaPuuttuvaRaha">Tulostetaanko puuttuva rahamäärä?</param>
        private static void TulostaTavoite(double tavoite, bool tulostaPuuttuvaRaha = true)
        {
            double rahaaTavoitteeseen = tavoite - yhteishinta;
            if (rahaaTavoitteeseen > 0)
            {
                Console.WriteLine("Tavoite ei ole täyttynyt.");
                if (tulostaPuuttuvaRaha)
                {
                    Console.WriteLine($"Rahaa tavoitteeseen: {rahaaTavoitteeseen:c}");
                }
            }
            else
            {
                Console.WriteLine("Tavoite on täyttynyt!");
            }
        }
    }
}
