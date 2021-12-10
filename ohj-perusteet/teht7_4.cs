using System;

namespace Tehtava7_4
{
    class Program
    {
        static void Main(string[] args)
        {
            int arvonta = new Random().Next(1, 101);
            int kierrokset = 5;
            for (int i = 0; i < kierrokset; i++)
            {
                Console.Write("Syötä luku:\n> ");
                if (int.Parse(Console.ReadLine()) == arvonta)
                {
                    Console.WriteLine("Onneksi olkoon, sama luku!");
                    return;
                }
            }
            Console.WriteLine("Kierroksia {0}, lopetetaan ohjelma.", kierrokset);
        }
    }
}
