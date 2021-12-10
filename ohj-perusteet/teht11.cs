using System;
using System.Collections.Generic;

namespace Tehtava11
{
    class Program
    {
        static void Main(string[] args)
        {
            Vaihe1();
            // Vaihe2();
            // Vaihe3();
        }
        public static void Vaihe1()
        {
            List<string> oppilaat = new List<string>();
            string syote;
            string lopetus = "L";
            Console.WriteLine("Syötä luokan oppilaiden nimiä.");
            Console.WriteLine("Lopeta syöttämällä " + lopetus + ".");
            Console.Write("> ");
            while (!(syote = Console.ReadLine()).Equals(lopetus, comparisonType: StringComparison.OrdinalIgnoreCase))
            {
                oppilaat.Add(syote);
                Console.Write("> ");
            }
            Console.WriteLine("Oppilaat:");
            foreach (string oppilas in oppilaat)
            {
                Console.WriteLine(oppilas);
            }
        }
        public static void Vaihe2()
        {
            List<string> oppilaat = new List<string>();
            string syote;
            string lopetus = "L";
            Console.WriteLine("Syötä luokan oppilaiden nimiä.");
            Console.WriteLine("Lopeta syöttämällä " + lopetus + ".");
            Console.Write("> ");
            while (!(syote = Console.ReadLine()).Equals(lopetus, comparisonType: StringComparison.OrdinalIgnoreCase))
            {
                oppilaat.Add(syote);
                Console.Write("> ");
            }
            int lkm;
            Console.WriteLine("Kuinka monta oppilasta haluat nähdä?");
            Console.Write("> ");
            while (!int.TryParse(Console.ReadLine(), out lkm))
            {
                Console.Write("Virheellinen syöte!\n> ");
            }
            if (lkm > oppilaat.Count) lkm = oppilaat.Count;
            foreach (string oppilas in oppilaat.GetRange(0, lkm))
            {
                Console.WriteLine(oppilas);
            }
        }
        public static void Vaihe3()
        {
            List<string> oppilaat = new List<string>();
            string syote;
            string lopetus = "L";
            Console.WriteLine("Syötä luokan oppilaiden nimiä.");
            Console.WriteLine("Lopeta syöttämällä " + lopetus + ".");
            Console.Write("> ");
            while (!(syote = Console.ReadLine()).Equals(lopetus, comparisonType: StringComparison.OrdinalIgnoreCase))
            {
                oppilaat.Add(syote);
                Console.Write("> ");
            }
            // Lukumäärä
            int lkm;
            Console.WriteLine("Kuinka monta oppilasta haluat nähdä?");
            Console.Write("> ");
            while (!int.TryParse(Console.ReadLine(), out lkm))
            {
                Console.Write("Virheellinen syöte!\n> ");
            }
            if (lkm > oppilaat.Count) lkm = oppilaat.Count;
            // aakosjärjestys vai käänteinen aakkosjärjestys
            oppilaat.Sort();
            string kaanteinen = "b";
            Console.WriteLine("Näytetäänkö oppilaat aakkosjärjestyksessä (a) vai käänteisessä aakkosjärjestyksessä (b)?");
            Console.Write("> ");
            if (Console.ReadLine().Equals(kaanteinen, comparisonType: StringComparison.OrdinalIgnoreCase))
            {
                oppilaat.Reverse();
            }
            // tulostus
            foreach (string oppilas in oppilaat.GetRange(0, lkm))
            {
                Console.WriteLine(oppilas);
            }
        }
    }
}
