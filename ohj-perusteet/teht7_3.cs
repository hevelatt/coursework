using System;

namespace Tehtava7_3
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Write("Syötä luku:\n> ");
            int rivit = int.Parse(Console.ReadLine());
            for (int i = 1; i < rivit + 1; i++)
            {
                for (int j = 1; j < i + 1; j++)
                    Console.Write(j + " ");
                Console.WriteLine();
            }
        }
    }
}
