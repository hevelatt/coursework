using System;

namespace Tehtava3
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Write("Syötä pituutesi (cm): ");
            double pituus = double.Parse(Console.ReadLine());
            double metrit = pituus / 100;
            double mailit = metrit / 1609.344;
            Console.WriteLine("{0:0.00} cm\n{1:0.00} m\n{2:0.00} mailia\n{3:0.00} jaardia\n{4:0.00} tuumaa", 
                pituus, metrit, mailit, mailit * 1760, mailit * 63360);
        }
    }
}
