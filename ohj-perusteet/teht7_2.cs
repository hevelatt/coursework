using System;

namespace Tehtava7_2
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Write("Syötä luku (0-10):\n> ");
            int j = Convert.ToInt32(Console.ReadLine());
            if (0 < j && j <= 10)
                for (int i = 1; i <= 10; i++)
                    Console.WriteLine("{0} * {1} = {2}\t{0} / {1} = {3}\t{0} % {1} = {4}",
                        i, j, i * j, i / j, i % j);
        }
    }
}
