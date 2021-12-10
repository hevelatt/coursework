using System;
using System.Collections.Generic;
using ElainLuokat;

namespace Oliolista
{
    class Program
    {
        public static void Main()
        {
            int lkm = PyydaLuku("Kuinka monta eläintä luodaan?"); 
            List<Elain> elaimet = TeeElainlista(lkm);
            TulostaElaimet(elaimet);
            // TulostaElaimet(TeeElainlista(PyydaLuku("Kuinka monta eläintä luodaan?")));
        }

        /// <summary>
        /// Tulostaa kysymyksen ja pyytää lukua kunnes syötetään luku.
        /// </summary>
        /// <param name="kysymys">Tulostettava kysymys.</param>
        /// <returns>Syötetty luku.</returns>
        private static int PyydaLuku(string kysymys)
        {
            int luku;
            while (!int.TryParse(PyydaSyote(kysymys), out luku))
            {
                Console.WriteLine("Virheellinen syöte!");
            }
            return luku;
        }

        /// <summary>
        /// Tulostaa kysymyksen ja pyytää syötteen.
        /// </summary>
        /// <param name="kysymys">Tulostettava kysymys.</param>
        /// <returns>Syöte.</returns>
        private static string PyydaSyote(string kysymys)
        {
            Console.WriteLine(kysymys);
            Console.Write("> ");
            return Console.ReadLine();
        }

        /// <summary>
        /// Tekee listan eläimistä siten että joka kolmas listan jäsen on kissa, joka kolmas on koira ja 
        /// joka kolmas on papukaija, mutta joka neljäs jäsen on aina hevonen.
        /// </summary>
        /// <param name="lkm">Kuinka monta eläintä luodaan.</param>
        /// <returns>Lista eläimistä.</returns>
        private static List<Elain> TeeElainlista(int lkm)
        {
            List<Elain> elaimet = new List<Elain>(lkm);
            Random satunnaisluku = new Random();

            for (int i = 1; i <= lkm; i++)
            {
                Elain elain;

                if (i % 4 == 0)
                {
                    elain = new Hevonen();
                }
                else
                {
                    switch (i % 3)
                    {
                        case 1:
                            elain = new Kissa();
                            break;
                        case 2:
                            elain = new Koira();
                            break;
                        default:
                            elain = new Papukaija();
                            break;
                    }
                }
                GeneroiArvot(satunnaisluku, elain);
                elaimet.Add(elain);
            }
            return elaimet;
        }

        /// <summary>
        /// Generoi satunnaiset arvot annetulle eläimelle.
        /// </summary>
        /// <param name="satunnaisluku">Satunnaislukuolio, jota käytetään arvonnassa.</param>
        /// <param name="elain">Eläin, jolle generoidaan arvot.</param>
        private static void GeneroiArvot(Random satunnaisluku, Elain elain)
        {
            string[] kissaNimet = { "Luna", "Pörri", "Viiru", "Minttu", "Misu", "Paavo", "Papu", "Siiri" };
            string[] koiraNimet = { "Luna", "Alma", "Sulo", "Hertta", "Helmi", "Martta", "Onni", "Bella", 
                "Hugo", "Elli", "Kerttu", "Hilma", "Elsa", "Hilla", "Lilli", "Nelli", "Peppi", "Sisu", "Milo", 
                "Sissi", "Taika", "Luca", "Siiri", "Maisa", "Elmo", "Rico", "Nemo", "Kaapo", "Koda", "Remu" };
            string[] papukaijaNimet = { "Kaija", "Keijo", "Papu", "Polli", "Sini" };
            string[] hevonenNimet = { "Filina", "Tiitus", "Hytteli", "Assi", "Lasse", "Veera", "Rosa", "Lumous", 
                "Polle", "Nuoli", "Nappi", "Lahja", "Lady", "Poju", "Soihtu", "Tytti", "Rölli", "Mustahelmi" };

            const int kissaSuurinIka = 16;
            const int koiraSuurinIka = 13;
            const int papukaijaSuurinIka = 50;
            const int hevonenSuurinIka = 30;

            const double papukaijaPieninSiipivali = 24; 
            const double papukaijaSuurinSiipivali = 130;
            const int karvattomiaKissojaProsentti = 20;
            const int karvattomiaKoiriaProsentti = 10;

            switch (elain)
            {
                case Kissa kissa:
                    kissa.AsetaElaimenNimi(kissaNimet[satunnaisluku.Next(kissaNimet.Length)]);
                    kissa.AsetaElaimenIka(satunnaisluku.Next(kissaSuurinIka));
                    kissa.AsetaOnLihanSyoja(true);
                    kissa.OnKarvapeite = karvattomiaKissojaProsentti <= satunnaisluku.Next(100);
                    break;
                case Koira koira:
                    koira.AsetaElaimenNimi(koiraNimet[satunnaisluku.Next(koiraNimet.Length)]);
                    koira.AsetaElaimenIka(satunnaisluku.Next(koiraSuurinIka));
                    koira.AsetaOnLihanSyoja(true);
                    koira.OnKarvapeite = karvattomiaKoiriaProsentti <= satunnaisluku.Next(100);
                    break;
                case Papukaija papukaija:
                    papukaija.AsetaElaimenNimi(papukaijaNimet[satunnaisluku.Next(papukaijaNimet.Length)]);
                    papukaija.AsetaElaimenIka(satunnaisluku.Next(papukaijaSuurinIka));
                    papukaija.AsetaOnLihanSyoja(false);
                    papukaija.AsetaSiipivali(papukaijaPieninSiipivali + 
                        satunnaisluku.NextDouble() * (papukaijaSuurinSiipivali - papukaijaPieninSiipivali));
                    break;
                case Hevonen hevonen:
                    hevonen.AsetaElaimenNimi(hevonenNimet[satunnaisluku.Next(hevonenNimet.Length)]);
                    hevonen.AsetaElaimenIka(satunnaisluku.Next(hevonenSuurinIka));
                    hevonen.AsetaOnLihanSyoja(false);
                    hevonen.OnKarvapeite = true;
                    break;
            }
        }

        /// <summary>
        /// Tulostaa eläinlistan tiedot.
        /// </summary>
        /// <param name="elaimet">Eläinlista, jonka tiedot tulostetaan.</param>
        private static void TulostaElaimet(List<Elain> elaimet)
        {
            foreach (Elain elain in elaimet)
            {
                switch (elain)
                {
                    case Kissa _:
                        Console.Write("Kissa: ");
                        break;
                    case Koira _:
                        Console.Write("Koira: ");
                        break;
                    case Papukaija _:
                        Console.Write("Papukaija: ");
                        break;
                    case Hevonen _:
                        Console.Write("Hevonen: ");
                        break;
                }
                Console.Write("{0}, ikä {1} {2} lihansyöjä.",
                    elain.PalautaElaimenNimi(), elain.PalautaElaimenIka(), 
                    elain.PalautaOnLihanSyoja() ? "on" : "ei ole");
                switch (elain)
                {
                    case Linnut lintu:
                        Console.WriteLine(" Linnun siipiväli on {0:F} cm.",
                            lintu.PalautaSiipivali());
                        break;
                    case Nisakkaat nisakas:
                        Console.WriteLine(" Nisäkäs {0} karvainen.",
                            nisakas.OnKarvapeite ? "on" : "ei ole");
                        break;
                    default:
                        Console.WriteLine();
                        break;
                }
            }
        }
    }
}
