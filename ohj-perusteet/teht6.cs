using System;

namespace Tehtava6
{
    class Program
    {
        static void Main(string[] args)
        {
            /*
            Tee ohjelma joka kysyy käyttäjältä seuraavat asiat:
            Paistaako aurinko(K/E):
            Sataako(K/E):
            Onko lämpötila >0(K/E):
            Jos kaikkiin vastattiin K, ohjelma tulostaa "Aurinko paistaa ja vettä sataa taitaa tulla kesä."
            Jos vastaus on E,K,K, ohjelma tulostaa "Syksy saapui lehdet vei tuuli menneessään."
            Jos vastaus on E,E,K  tai K,E,K "Jo joutui armas aika ja suvi suloinen."
            Keksi vielä ainakin kolmeen vastausvaihtoehtoon omat vastaukset.
            */
            Console.Write("Paistaako aurinko(K/E):\n> ");
            bool paistaako = Console.ReadLine().Equals("K", comparisonType: StringComparison.OrdinalIgnoreCase);
            Console.Write("Sataako(K/E):\n> ");
            bool sataako = Console.ReadLine().Equals("K", comparisonType: StringComparison.OrdinalIgnoreCase);
            Console.Write("Onko lämpötila >0(K/E):\n> ");
            bool plussalla = Console.ReadLine().Equals("K", comparisonType: StringComparison.OrdinalIgnoreCase);

            if (paistaako && sataako && plussalla)
                Console.WriteLine("Aurinko paistaa ja vettä sataa taitaa tulla kesä.");
            if (!paistaako && sataako && plussalla)
                Console.WriteLine("Syksy saapui lehdet vei tuuli menneessään.");
            if (!sataako && plussalla)
                Console.WriteLine("Jo joutui armas aika ja suvi suloinen.");
            if (!plussalla)
                Console.WriteLine("Taitaa olla talvi.");
            if (!paistaako && sataako && !plussalla)
                Console.WriteLine("Lumimyrskyn kourissa.");
            if (paistaako && !sataako && !plussalla)
                Console.WriteLine("Kaunis aurinkoinen talvipäivä.");
        }
	/// <summary>
        /// Kysyy käyttäjältä kyllä/ei -kysymyksen ja pyytää vastauksen (K/E).
        /// </summary>
        /// <param name="kysymys">Käyttäjältä kysyttävä kysymys.</param>
        /// <returns>True jos vastaus on kyllä.</returns>
        public static bool KysyKyllaEi(string kysymys)
        {
            Console.Write($"{kysymys} (K/E):\n> ");
            return Console.ReadLine()
                .Equals("K", comparisonType: StringComparison.OrdinalIgnoreCase);
        }
    }
}
