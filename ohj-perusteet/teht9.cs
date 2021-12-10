using System;

namespace Tehtava9
{
    class Program
    {
        static void Main(string[] args)
        {
            int lkm = 5;
            double[] luvut = new double[lkm];
            double summa = 0;
            Console.WriteLine($"Syötä {lkm} lukua, joista lasketaan keskiarvo.");
            for (int i = 0; i < lkm; i++)
            {
                do
                {
                    Console.Write($"Syötä {i + 1}. luku:\n> ");
                } while (!double.TryParse(Console.ReadLine(), out luvut[i]));
            }
            Console.Write("Lukujen ");
            string erotin = "";
            foreach (double luku in luvut)
            {
                Console.Write($"{erotin}{luku}");
                erotin = ", ";
                summa += luku;
            }
            Console.Write($" keskiarvo on {summa/lkm}.");
        }
    }
}
