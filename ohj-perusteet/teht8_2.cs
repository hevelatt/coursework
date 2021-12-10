using System;

namespace Tehtava8_2
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;
            // Vaihe1();
            // Vaihe2();
            // Vaihe3();
            // Vaihe4();
            Vaihe5();
        }
        public static void Vaihe1()
        {
            string[] nimet = new string[3] { "Omena", "Ananas", "Banaani" };
            double[] hinnat = new double[3] { 0.7, 2.3, 1.85 };
            for (int i = 0; i < nimet.Length; i++)
                Console.WriteLine("{0} {1:0.0}€.", nimet[i], hinnat[i]);
        }
        public static void Vaihe2()
        {
            decimal[] tuoteHinta = new decimal[3] { 0.7m, 2.3m, 1.85m };
            int[] tuoteLkm = new int[3] { 500, 60, 45 };
            string[] tuoteNimi = new string[3] { "Omena", "Ananas", "Banaani" };
            for (int i = 0; i < tuoteNimi.Length; i++)
                Console.WriteLine("{0} yhteishinta on {1:0.0}€.", tuoteNimi[i], tuoteHinta[i] * tuoteLkm[i]);
        }
        public static void Vaihe3()
        {
            int tuotteita = 3;
            decimal[] tuoteHinta = new decimal[tuotteita];
            int[] tuoteLkm = new int[tuotteita];
            string[] tuoteNimi = new string[tuotteita];
            for (int i = 0; i < tuotteita; i++)
            {
                Console.Write("Anna {0}. tuotteen nimi:\n> ", i + 1);
                tuoteNimi[i] = Console.ReadLine();
                Console.Write("Anna {0}. tuotteen hinta:\n> ", i + 1);
                tuoteHinta[i] = decimal.Parse(Console.ReadLine());
                Console.Write("Anna {0}. tuotteen lukumäärä:\n> ", i + 1);
                tuoteLkm[i] = int.Parse(Console.ReadLine());
            }
            for (int i = 0; i < tuoteNimi.Length; i++)
                Console.WriteLine("{0} yhteishinta on {1:0.0}€.", tuoteNimi[i], tuoteHinta[i] * tuoteLkm[i]);
        }
        public static void Vaihe4()
        {
            Console.Write("Kuinka monta tuotetta lisätään?\n> ");
            int tuotteita = int.Parse(Console.ReadLine());
            decimal[] tuoteHinta = new decimal[tuotteita];
            int[] tuoteLkm = new int[tuotteita];
            string[] tuoteNimi = new string[tuotteita];
            for (int i = 0; i < tuotteita; i++)
            {
                Console.Write("Anna {0}. tuotteen nimi:\n> ", i + 1);
                tuoteNimi[i] = Console.ReadLine();
                Console.Write("Anna {0}. tuotteen hinta:\n> ", i + 1);
                tuoteHinta[i] = decimal.Parse(Console.ReadLine());
                Console.Write("Anna {0}. tuotteen lukumäärä:\n> ", i + 1);
                tuoteLkm[i] = int.Parse(Console.ReadLine());
            }
            for (int i = 0; i < tuoteNimi.Length; i++)
                Console.WriteLine("{0} yhteishinta on {1:0.0}€.", tuoteNimi[i], tuoteHinta[i] * tuoteLkm[i]);
        }
        public static void Vaihe5()
        {
            string[,] heroes =
            {
                // HERO        ,  ROLE    ,  HEALTH,ARMOR
                { "Ana"        , "Support", "200", "0"   },
                { "Bastion"    , "Defense", "200", "100" },
                { "Brigitte"   , "Support", "200", "50"  },
                { "D.Va (Mech)", "Tank"   , "400", "200" },
            };
            Console.WriteLine("Choose Hero: ");
            for (int i = 0; i < heroes.GetLength(0); i++)
                Console.WriteLine(heroes[i, 0]);
            Console.Write("> ");
            string inputHero = Console.ReadLine();
            for (int i = 0; i < heroes.GetLength(0); i++)
                if (heroes[i, 0] == inputHero)
                    for (int j = 1; j < heroes.GetLength(1); j++)
                        Console.Write(heroes[i, j] + " ");
        }
    }
}
