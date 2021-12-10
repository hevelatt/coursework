using System;
using ElainLuokat;

namespace Ika
{
    class Program
    {
        static void Main(string[] args)
        {
            Random satunnaisluku = new Random();
            for (int i = 0; i < 10; i++)
            {
                Koira koira = new Koira();
                Kissa kissa = new Kissa();
                int koiranIka = satunnaisluku.Next(1, 16);
                int kissanIka = satunnaisluku.Next(1, 11);
                koira.AsetaKoiranIka(koiranIka);
                kissa.AsetaKissanIka(kissanIka);
                Console.WriteLine("Koira on: {0} vuotta, Kissa on: {1} vuotta",
                    // Ota ikä koiran ja kissan metodeilta, palautaKissanIka() ja palautaKoiranIka() 
                    koiranIka = koira.PalautaKoiranIka(), kissanIka = kissa.PalautaKissanIka());
                if (koiranIka > kissanIka)
                {
                    Console.WriteLine("Koira on vanhempi");
                }
                else if (kissanIka > koiranIka)
                {
                    Console.WriteLine("Kissa on vanhempi");
                }
            }
        }
    }
}
