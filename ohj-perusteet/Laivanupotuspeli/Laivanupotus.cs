using System;

namespace Laivanupotuspeli
{
    class Laivanupotus
    {
        const int KentanLeveys = 5;
        const int KentanKorkeus = 5;

        private static void Main()
        {
            Random satunnaisluku = new Random();
            Console.WriteLine("Sijoita laivasi:");
            int[] pelaajanLaiva = KysyKoordinaatit();
            int[] tietokoneenLaiva = arvoKoordinaatit(satunnaisluku);
            char[,] pelaajanKentta = UusiKentta();
            SijoitaLaiva(pelaajanKentta, pelaajanLaiva);
            char[,] tietokoneenKentta = UusiKentta();
            Pelisilmukka(satunnaisluku, pelaajanLaiva, tietokoneenLaiva, pelaajanKentta, tietokoneenKentta);
        }

        private static void Pelisilmukka(Random satunnaisluku, int[] pelaajanLaiva, int[] tietokoneenLaiva, char[,] pelaajanKentta, char[,] tietokoneenKentta)
        {
            bool lopeta = false;
            bool pelaajanVuoro = true;
            int[] koordinaatitArvaus;

            while (!lopeta)
            {
                if (pelaajanVuoro)
                {
                    Console.WriteLine("Sinun vuorosi arvata:");
                    NaytaKentta(tietokoneenKentta);
                    koordinaatitArvaus = KysyKoordinaatit();
                    if (Osuma(koordinaatitArvaus, tietokoneenLaiva))
                    {
                        SijoitaUponnut(tietokoneenKentta, koordinaatitArvaus);
                        NaytaKentta(tietokoneenKentta);
                        Console.WriteLine("Osuma! Voitit pelin!");
                        lopeta = true;
                    }
                    else
                    {
                        SijoitaOsuma(tietokoneenKentta, koordinaatitArvaus);
                        pelaajanVuoro = false;
                    }
                }
                else
                {
                    Console.WriteLine("Tietokone arvaa:");
                    koordinaatitArvaus = arvoKoordinaatit(satunnaisluku);
                    SijoitaOsuma(pelaajanKentta, koordinaatitArvaus);
                    if (Osuma(koordinaatitArvaus, pelaajanLaiva))
                    {
                        SijoitaUponnut(pelaajanKentta, koordinaatitArvaus);
                        NaytaKentta(pelaajanKentta);
                        Console.WriteLine("Osuma! Hävisit pelin!");
                        lopeta = true;
                    }
                    else
                    {
                        NaytaKentta(pelaajanKentta);
                        pelaajanVuoro = true;
                    }
                }
            }
        }

        private static char[,] UusiKentta()
        {
            char[,] kentta = new char[KentanKorkeus, KentanLeveys];
            for (int i = 0; i < KentanKorkeus; i++)
            {
                for (int j = 0; j < KentanLeveys; j++)
                {
                    kentta[i, j] = '~';
                }
            }
            return kentta;
        }

        private static void NaytaKentta(char[,] kentta)
        {
            for (int i = 0; i < KentanKorkeus; i++)
            {
                for (int j = 0; j < KentanLeveys; j++)
                {
                    Console.Write(kentta[i, j]);
                }
                Console.WriteLine();
            }
        }

        private static void SijoitaKenttaan(char[,] kentta, int[] koordinaatit, char merkki)
        {
            kentta[koordinaatit[1], koordinaatit[0]] = merkki;
        }

        private static void SijoitaLaiva(char[,] kentta, int[] laiva)
        {
            SijoitaKenttaan(kentta, laiva, 'o');
        }

        private static void SijoitaOsuma(char[,] kentta, int[] koordinaatit)
        {
            SijoitaKenttaan(kentta, koordinaatit, 'X');
        }

        private static void SijoitaUponnut(char[,] kentta, int[] koordinaatit)
        {
            SijoitaKenttaan(kentta, koordinaatit, '@');
        }

        private static int[] KysyKoordinaatit()
        {
            int x = KysyLuku($"X-koordinaatti (1-{KentanLeveys}): ") - 1;
            while (x < 0 || x >= KentanLeveys)
            {
                Console.WriteLine($"Luku ei ole oikealta väliltä (1-{KentanLeveys})!");
                x = KysyLuku($"X-koordinaatti (1-{KentanLeveys}): ") - 1;
            }
            int y = KysyLuku($"Y-koordinaatti (1-{KentanKorkeus}): ") - 1;
            while (y < 0 || y >= KentanKorkeus)
            {
                Console.WriteLine($"Luku ei ole oikealta väliltä (1-{KentanKorkeus})!");
                y = KysyLuku($"Y-koordinaatti (1-{KentanKorkeus}): ") - 1;
            }
            return new int[2] { x, y };
        }

        private static int KysyLuku(string kysymys)
        {
            int syoteLuku = 0;
            while (!int.TryParse(KysySyote(kysymys), out syoteLuku))
            {
                Console.WriteLine("Syöte ei ole luku!");
            }
            return syoteLuku;
        }

        private static string KysySyote(string kysymys)
        {
            Console.Write($"{kysymys}\n> ");
            return Console.ReadLine();
        }

        private static int[] arvoKoordinaatit(Random satunnaisluku)
        {
            int[] koordinaatit = new int[2];
            koordinaatit[0] = satunnaisluku.Next(KentanLeveys);
            koordinaatit[1] = satunnaisluku.Next(KentanKorkeus);
            return koordinaatit;
        }

        private static bool Osuma(int[] arvatutKoordinaatit, int[] laivanKoordinaatit)
        {
            if (arvatutKoordinaatit[0] == laivanKoordinaatit[0] && arvatutKoordinaatit[1] == laivanKoordinaatit[1])
            {
                return true;
            }
            return false;
        }
    }
}
