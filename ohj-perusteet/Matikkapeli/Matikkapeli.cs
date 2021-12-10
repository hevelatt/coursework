using System;
using System.Collections.Generic;

namespace Aritmetiikka
{
    enum Asetus { Summa, Erotus, Tulo, Osamaara, 
        NegatiivisiaVastauksia, VainKokonaislukuja, OsamaaratVainKokonaislukuja, IsotLuvut, KaksiDesimaalia, 
        Pituus }

    class Matikkapeli
    {
        private const int laskutoimituksia = 4;

        private static void Main()
        {
            Random satunnaisluku = new Random();

            bool[] asetukset = new bool[(int)Asetus.Pituus];
            AlustaAsetukset(asetukset);

            Asetus[] valitutLaskutoimitukset = new Asetus[laskutoimituksia];
            int valittujaLaskutoimituksia = AsetaLaskutoimitukset(valitutLaskutoimitukset, asetukset);

            const int asetusValikkoAloitusnumero = 1;

            int palkinnot = 0;
            int naytaPalkinnot = 10;

            double[] arvotutLuvut;
            double ratkaisu = 0;

            string virheilmoitus = "";

            // Ohjausmuuttujat.
            bool lopeta = false;
            bool asetuksissa = false;
            bool ohjeissa = false;
            bool virheValinnassa = false;
            bool ratkaistu = false;
            bool virheAsetuksissa = false;
            bool ratkaistuOikein = false;

            // Pääsilmukka.
            while (!lopeta)
            {
                Console.Clear();
                // Kunkin näkymän ensimmäisen rivin tulostus.
                if (virheAsetuksissa)
                {
                    Console.WriteLine(virheilmoitus);
                    // Jos asetuksissa on virhe niin pakotetaan asetuksissa pysyminen, kunnes käyttäjä korjaa virheen.
                    ohjeissa = false;
                    asetuksissa = true;
                }
                else if (virheValinnassa)
                {
                    Console.WriteLine("Virhe valinnassa!");
                    // Valintavirhe näytetään vain kerran virheellisen valinnan jälkeen.
                    virheValinnassa = false;
                }
                else if (ratkaistu)
                {
                    if (ratkaistuOikein)
                    {
                        Console.Write("Oikea vastaus!");
                        palkinnot++;
                    }
                    else
                    {
                        Console.Write("Väärä vastaus!");
                    }
                    naytaPalkinnot--;
                    if (naytaPalkinnot < 1)
                    {
                        naytaPalkinnot = 10;
                        Console.Write($" Palkintoja {palkinnot}.");
                    }
                    Console.WriteLine();
                    // Ratkaisu näytetään vain arvauksen jälkeen.
                    ratkaistu = false;
                }
                else
                {
                    // Jos ei esitetä virhettä eikä ratkaisua, näytetään ohjeviesti.
                    Console.WriteLine("(h) Näytä ohjeet.");
                }

                //================================================================

                // Muut näkymän tulostukset ja alustukset.
                Console.WriteLine("----------------");
                if (ohjeissa)
                {
                    Console.WriteLine("Vastaa laskutoimituksiin oikein ja kerää palkintoja!" +
                        "\n(h) Tulosta nämä ohjeet." +
                        "\n(x) Palaa takaisin." +
                        "\n(s) Muuta asetuksia." +
                        "\n(q) Poistu ohjelmasta.");
                }
                else if (asetuksissa)
                {
                    // Tulostetaan asetusvalikko.
                    TulostaAsetusValikko(asetukset, asetusValikkoAloitusnumero);
                }
                else
                {
                    // Arvotaan laskutoimitus ja luvut.
                    Asetus arvottuLaskutoimitus = valitutLaskutoimitukset[satunnaisluku.Next(valittujaLaskutoimituksia)];
                    arvotutLuvut = ArvoLuvut(arvottuLaskutoimitus, asetukset, satunnaisluku);
                    // Tulostetaan laskutehtävä.
                    ratkaisu = TulostaJaRatkaiseLasku(arvottuLaskutoimitus, arvotutLuvut);
                }

                //================================================================

                // Syötteen lukeminen ja käsittely.
                string syote = Console.ReadLine();
                switch (syote)
                {
                    case "q":
                        lopeta = true;
                        break;
                    case "h":
                        ohjeissa = true;
                        asetuksissa = false;
                        break;
                    case "s":
                        ohjeissa = false;
                        asetuksissa = true;
                        break;
                    case "x":
                        ohjeissa = false;
                        asetuksissa = false;
                        break;
                    default:
                        bool onLuku = double.TryParse(syote, out double syoteLuku);
                        if (ohjeissa)
                        {
                            // Ei syötteitä ohjeissa.
                        }
                        else if (asetuksissa)
                        {
                            if (asetusValikkoAloitusnumero <= syoteLuku && syoteLuku < asetusValikkoAloitusnumero + (int)Asetus.Pituus)
                            {
                                MuutaAsetuksia((int)syoteLuku - asetusValikkoAloitusnumero, asetukset);
                                valittujaLaskutoimituksia = AsetaLaskutoimitukset(valitutLaskutoimitukset, asetukset);
                            }
                            else
                            {
                                // Virheellinen valinta.
                                virheValinnassa = true;
                            }
                            // Virheen käsittely.
                            if (valittujaLaskutoimituksia == 0)
                            {
                                virheAsetuksissa = true;
                                virheilmoitus = "Ainakin yksi laskutoimitus pitää olla valittuna!";
                            }
                            else if (asetukset[(int)Asetus.VainKokonaislukuja] && asetukset[(int)Asetus.KaksiDesimaalia])
                            {
                                virheAsetuksissa = true;
                                virheilmoitus = "Luvuissa ei voi olla kahta desimaalia, koska arvotaan vain kokonaislukuja!";
                            }
                            else
                            {
                                virheAsetuksissa = false;
                            }
                        }
                        else if (onLuku)
                        {
                            ratkaistu = true;
                            ratkaistuOikein = Math.Abs(Math.Round(syoteLuku, 2) - ratkaisu) <= .01;
                        }
                        break;
                }
            }
            Console.Clear();
            Console.WriteLine($"Palkintoja {palkinnot}.");
            Console.ReadKey();
        }

        /// <summary>
        /// Alustaa asetukset (true tai false).
        /// </summary>
        /// <param name="asetukset">Alustettavat asetukset.</param>
        private static void AlustaAsetukset(bool[] asetukset)
        {
            asetukset[(int)Asetus.Summa] = true;
            asetukset[(int)Asetus.Erotus] = true;
            asetukset[(int)Asetus.Tulo] = true;
            asetukset[(int)Asetus.Osamaara] = true;
            //asetukset[(int)Asetus.NegatiivisiaVastauksia] = false;
            asetukset[(int)Asetus.VainKokonaislukuja] = true;
            asetukset[(int)Asetus.OsamaaratVainKokonaislukuja] = true;
        }

        /// <summary>
        /// Muutetaan valinnan mukaista asetusta.
        /// </summary>
        /// <param name="valinta">Valinta asetuksesta, jota muutetaan.</param>
        /// <param name="asetukset">Asetukset, joista muutetaan asetusta.</param>
        private static void MuutaAsetuksia(int valinta, bool[] asetukset)
        {
            asetukset[valinta] = asetukset[valinta] ? false : true;
        }

        /// <summary>
        /// Asetetaan laskutoimitukset asetusten perusteella.
        /// </summary>
        /// <param name="laskutoimitukset">Taulukko, johon asetetaan asetuksissa määritellyt laskutoimitukset.</param>
        /// <param name="asetukset">Asetukset, joiden perusteella määritellään laskutoimitukset.</param>
        /// <returns>Palauttaa asetettujen laskutoimitusten lukumäärän.</returns>
        private static int AsetaLaskutoimitukset(Asetus[] laskutoimitukset, bool[] asetukset)
        {
            int asetettujaLaskutoimituksia = 0;
            int i = 0;
            for (int j = 0; j < Matikkapeli.laskutoimituksia; j++)
            {
                if (asetukset[j])
                {
                    laskutoimitukset[i++] = (Asetus)j;
                    asetettujaLaskutoimituksia++;
                }
            }
            return asetettujaLaskutoimituksia;
        }

        /// <summary>
        /// Tulostetaan asetusvalikko.
        /// </summary>
        /// <param name="asetukset">Asetukset, joista tulostetaan valikko.</param>
        /// <param name="aloitusnumero">Ensimmäisen asetuksen numerovalinta.</param>
        private static void TulostaAsetusValikko(bool[] asetukset, int aloitusnumero)
        {
            for (int i = 0; i < (int)Asetus.Pituus; i++)
            {
                Asetus asetus = (Asetus)i;
                Console.Write($"({aloitusnumero + i}) ");
                switch (asetus)
                {
                    case Asetus.Osamaara:
                        Console.Write("Osamäärä");
                        break;
                    case Asetus.NegatiivisiaVastauksia:
                        Console.Write("Vastaus voi olla negatiivinen");
                        break;
                    case Asetus.VainKokonaislukuja:
                        Console.Write("Luvut vain kokonaislukuja\t");
                        break;
                    case Asetus.OsamaaratVainKokonaislukuja:
                        Console.Write("Osamäärät vain kokonaislukuja");
                        break;
                    case Asetus.IsotLuvut:
                        Console.Write("Isompia lukuja\t\t");
                        break;
                    case Asetus.KaksiDesimaalia:
                        Console.Write("Luvuissa kaksi desimaalia\t");
                        break;
                    default:
                        Console.Write($"{asetus}");
                        break;
                }
                if (asetukset[i])
                {
                    Console.Write("\tX");
                }
                Console.WriteLine();
            }
        }

        /// <summary>
        /// Arpoo laskutoimitukseen sopivat ja asetusten mukaiset luvut.
        /// </summary>
        /// <param name="laskutoimitus">Laskutoimitus, jonka mukaan arvotaan luvut.</param>
        /// <param name="asetukset">Asetukset, joiden mukaan arvotaan luvut.</param>
        /// <param name="satunnaisluku">Satunnaislukuolio, jolla arvotaan luvut.</param>
        /// <returns>Palauttaa taulukon kahdesta asetusten mukaan arvotusta liukuluvusta.</returns>
        private static double[] ArvoLuvut(Asetus laskutoimitus, bool[] asetukset, Random satunnaisluku)
        {
            double[] arvotutLuvut = new double[2];
            int desimaaleja = asetukset[(int)Asetus.VainKokonaislukuja] ?
                0 : (asetukset[(int)Asetus.KaksiDesimaalia] ? 2 : 1);
            int kerroin = asetukset[(int)Asetus.IsotLuvut] ? 100 : 10;
            arvotutLuvut[0] = Math.Round(satunnaisluku.NextDouble() * kerroin, desimaaleja);
            switch (laskutoimitus)
            {
                case Asetus.Summa:
                    arvotutLuvut[1] = Math.Round(satunnaisluku.NextDouble() * kerroin, desimaaleja);
                    break;
                case Asetus.Erotus:
                    if (asetukset[(int)Asetus.NegatiivisiaVastauksia])
                    {
                        arvotutLuvut[1] = Math.Round(satunnaisluku.NextDouble() * kerroin, desimaaleja);
                    }
                    else
                    {
                        arvotutLuvut[0] += arvotutLuvut[1] = Math.Round(satunnaisluku.NextDouble() * kerroin, desimaaleja);
                    }
                    break;
                case Asetus.Tulo:
                    arvotutLuvut[1] = Math.Round(satunnaisluku.NextDouble() * kerroin, desimaaleja);
                    break;
                case Asetus.Osamaara:
                    if (asetukset[(int)Asetus.OsamaaratVainKokonaislukuja])
                    {
                        arvotutLuvut[1] = Math.Round(satunnaisluku.NextDouble() * kerroin, desimaaleja);
                        arvotutLuvut[0] = satunnaisluku.Next(kerroin) * arvotutLuvut[1];
                    }
                    else
                    {
                        arvotutLuvut[1] = Math.Round(satunnaisluku.NextDouble() * kerroin, desimaaleja);
                    }
                    // Varmistetaan ettei tule nollalla jakoa.
                    if (arvotutLuvut[1] == 0)
                    {
                        arvotutLuvut[1] = 1;
                    }
                    break;
            }
            return arvotutLuvut;
        }

        /// <summary>
        /// Tulostaa ja ratkaisee määritellyn laskun määritellyillä luvuilla.
        /// </summary>
        /// <param name="laskutoimitus">Laskutoimitus, joka tulostetaan ja ratkaistaan.</param>
        /// <param name="luvut">Luvut, joita käytetään laskutoimituksessa.</param>
        /// <returns>Palauttaa laskutoimituksen ratkaisun.</returns>
        private static double TulostaJaRatkaiseLasku(Asetus laskutoimitus, double[] luvut)
        {
            double ratkaisu = 0;
            Console.Write($"{luvut[0]:0.##} ");
            switch (laskutoimitus)
            {
                case Asetus.Summa:
                    Console.Write('+');
                    ratkaisu = luvut[0] + luvut[1];
                    break;
                case Asetus.Erotus:
                    Console.Write('-');
                    ratkaisu = luvut[0] - luvut[1];
                    break;
                case Asetus.Tulo:
                    Console.Write('*');
                    ratkaisu = luvut[0] * luvut[1];
                    break;
                case Asetus.Osamaara:
                    Console.Write('/');
                    ratkaisu = luvut[0] / luvut[1];
                    break;
            }
            Console.WriteLine($" {luvut[1]:0.##}");
            return ratkaisu;
        }
    }
}