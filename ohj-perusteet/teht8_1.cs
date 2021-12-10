using System;

namespace Tehtava8_1
{
    class Program
    {
        static void Main(string[] args)
        {
            // Vaihe1();
            Vaihe2();
        }
        public static void Vaihe1()
        {
            string[] hedelmat = { "omena", "banaani", "mango" };
            for (int i = 0; i < hedelmat.Length; i++)
                Console.WriteLine(hedelmat[i]);
        }
        public static void Vaihe2()
        {
            string[] hedelmat = { "omena", "banaani", "mango" };
            Console.Write("Montako hedelmää haluat nähdä?\n> ");
            int maara = Math.Min(int.Parse(Console.ReadLine()), hedelmat.Length);
            for (int i = 0; i < maara; i++)
                Console.WriteLine(hedelmat[i]);
        }
    }
}
