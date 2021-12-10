using System;

namespace Tehtava4
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;
            double eurToUsd = 1.19;
            double eurToGbp = 0.86;
            double eur;

            if (args.Length > 0)
            {
                eur = double.Parse(args[0]); //Euromäärän voi antaa myös argumenttina ohjelmaa ajettaessa
            }
            else
            {
                Console.Write("Syötä rahamäärä (€): ");
                eur = double.Parse(Console.ReadLine());
            }
            Console.WriteLine("Euroina:\t{0:n} €\nDollareina:\t{1:n} $\nPuntina:\t{2:n} £",
                eur, eur * eurToUsd, eur * eurToGbp);
        }
    }
}