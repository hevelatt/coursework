using System;

namespace Tehtava5_2
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Write("Oletko mies (m) vai nainen (n)?\n> ");
            string gender = Console.ReadLine();
            Console.Write("Syötä ikäsi:\n> ");
            int age = int.Parse(Console.ReadLine());

            if (age < 13)
            {
                if (gender == "m")
                {
                    Console.WriteLine("Mitä poitsu");
                }
                else if (gender == "n")
                {
                    Console.WriteLine("Mitä tytsy");
                }
            }
            else if (age < 26)
            {
                if (gender == "m")
                {
                    Console.WriteLine("Mitä nuorimies");
                }
                else if (gender == "n")
                {
                    Console.WriteLine("Mitä nuorinainen");
                }
            }
            else if (age < 57)
            {
                if (gender == "m")
                {
                    Console.WriteLine("Olet mies parhaassa iässä");
                }
                else if (gender == "n")
                {
                    Console.WriteLine("Olet nainen parhaassa iässä");
                }
            }
            else
            {
                Console.WriteLine("Olet vanha");
            }
        }
    }
}
