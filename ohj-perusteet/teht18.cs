using System;

namespace Tehtava18
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Write("syöte: ");
            string syote = Console.ReadLine();
            // Taulukko sanoista syötteessä (välilyönnillä erotetut merkkijonot).
            string[] sanat = syote.Split(' ');

            // Syöte sellaisenaan.
            foreach (string sana in sanat)
            {
                Console.WriteLine(sana);
            }

            // Syöte harvasti.
            foreach (string sana in sanat)
            {
                foreach (char merkki in sana)
                {
                    Console.Write(merkki + " ");
                }
                Console.WriteLine();
            }

            // Syöte takaperin harvasti.
            for (int i = 0; i < sanat.Length; i++)
            {
                for (int j = sanat[i].Length; j > 0;)
                {
                    Console.Write(sanat[i][--j] + " ");
                }
                Console.WriteLine();
            }

            // Monesko merkki on a?
            char kirjain = 'a';
            // int indeksi = syote.IndexOf(merkki);
            int indeksi = MoneskoMerkki(syote, kirjain);
            if (indeksi > 0)
            {
                Console.WriteLine($"{kirjain} on {indeksi + 1}. merkki (indeksi {indeksi}) sanassa {syote}");
            }
            else
            {
                Console.WriteLine($"{kirjain} ei esiinny sanassa {syote}");
            }
        }

        /// <summary>
        /// Palauttaa merkin ensimmäisen esiintymän indeksin merkkijonossa, -1 jos merkkiä ei esiinny.
        /// </summary>
        /// <param name="jono">Merkkijono, josta indeksiä etsitään.</param>
        /// <param name="merkki">Merkki, jonka indeksiä etsitään.</param>
        /// <returns>Merkin ensimmäisen esiintymän indeksi, -1 jos merkkiä ei esiinny.</returns>
        private static int MoneskoMerkki(string jono, char merkki)
        {
            for (int i = 0; i < jono.Length; i++)
            {
                if (jono[i] == merkki)
                {
                    return i;
                }
            }
            return -1;
        }
    }
}
