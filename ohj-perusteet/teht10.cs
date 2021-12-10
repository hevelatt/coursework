using System;

namespace Tehtava10
{
    class Program
    {
        static void Main(string[] args)
        {
            Vaihe1();
            // Vaihe2();
        }
        public static void Vaihe1()
        {
            int raja = 100;
            int summa = 0;
            while (summa <= raja)
            {
                Console.WriteLine($"Summa tällä hetkellä: {summa}/{raja}");
                int syote;
                Console.Write("Syötä kokonaisluku:\n> ");
                if (int.TryParse(Console.ReadLine(), out syote))
                {
                    summa += syote;
                }
            }
            Console.WriteLine($"Raja saavutettu: {summa}/{raja}");
        }
        public static void Vaihe2()
        {
            int arvonta = 45;
            string lopeta = "L";
            Console.WriteLine($"({lopeta}) lopettaa ajon.");
            Console.Write("Syötä kokonaisluku:\n> ");
            string syote = Console.ReadLine();
            while (!syote.Equals(lopeta, comparisonType: StringComparison.OrdinalIgnoreCase))
            {
                int syoteLuku;
                if (int.TryParse(syote, out syoteLuku))
                {
                    if (syoteLuku == arvonta)
                    {
                        Console.WriteLine("Onneksi olkoon, sama luku!");
                        return;
                    }
                }
                Console.Write("Syötä kokonaisluku:\n> ");
                syote = Console.ReadLine();
            }
        }
    }
}
