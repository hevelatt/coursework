using System;

namespace Satunnaisluvut
{
    class Nopanheitto
    {
        static void Main()
        {
            Random satunnaisluku = new Random();
            int noppia = 2;
            int heittoja = 5;
            int summa = 0;
            for (int i = 0; i < noppia; i++)
            {
                Console.WriteLine("{0}. noppa:", i + 1);
                for (int j = 0; j < heittoja; j++)
                {
                    int nopanheitto = satunnaisluku.Next(1, 7);
                    summa += nopanheitto;
                    Console.WriteLine("\t{0}. heitto: {1}", j + 1, nopanheitto);
                }
            }
            Console.WriteLine("Silmälukujen summa on {0}.", summa);
        }
    }
}