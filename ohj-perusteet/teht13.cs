using System;

namespace Tehtava13
{
    class Program
    {
        private static int Pienin(int eka, int toka, int kolmas)
        {
            return Math.Min(eka, Math.Min(toka, kolmas));
        }

        private static int Suurin(int eka, int toka, int kolmas)
        {
            return eka > toka ? (eka > kolmas ? eka : kolmas) : (toka > kolmas ? toka : kolmas);
        }

        static void Main(string[] args)
        {
            int luku1, luku2, luku3, suurinLuku, pieninLuku;
            string syote;
            Console.WriteLine("Anna eka luku:");
            syote = Console.ReadLine();
            luku1 = int.Parse(syote);
            Console.WriteLine("Anna toka luku:");
            syote = Console.ReadLine();
            luku2 = int.Parse(syote);
            Console.WriteLine("Anna kolmas luku:");
            syote = Console.ReadLine();
            luku3 = int.Parse(syote);
            // Kutsu tässä Pienin ja Suurin ohjelmia ja anna niille parametreina luku1, luku2 ja luku3.
            // Ota aliohjelmien tulokset talteen suurinLuku ja pieninLuku muuttujiin. Tulosta lopuksi tulokset 
            suurinLuku = Suurin(luku1, luku2, luku3);
            pieninLuku = Pienin(luku1, luku2, luku3);
            Console.WriteLine($"Suurin luku on {suurinLuku} ja pienin luku on {pieninLuku}");
        }
    }
}
