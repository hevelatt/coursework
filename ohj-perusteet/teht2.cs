using System;

namespace Tehtava2
{
    class Program
    {
        static void Main(string[] args)
        {
            /*
            1 * 5 = 5
            2 * 5 = 10
            3 * 5 = 15
            4 * 5 = 20
            5 * 5 = 25 
            */
            int j = 5;
            while (0 < j && j <= 10)
            {
                for (int i = 1; i < j + 1; i++)
                {
                    Console.WriteLine("{0} * {1} = {2}\t{0} / {1} = {3}\t{0} % {1} = {4}", 
                        i, j, i * j, i / j, i % j);
                }
                Console.Write("Syötä luku (0-10): ");
                j = Convert.ToInt32(Console.ReadLine());
            }
        }
    }
}
