using System;
using ElainLuokat;

namespace PennutJaEmot
{
    class Program
    {
        public static void Main()
        {
            Kissa emo = new Kissa("Emo", 5);
            emo.LisaaPentu("Eka");
            emo.LisaaPentu("Toka");
            int pentuja = emo.LisaaPentu("Kolmas");
            foreach (Kissa pentu in emo.Pennut())
            {
                Console.WriteLine(pentu);
            }
            Console.WriteLine("Pentuja " + pentuja);

            Kissa emo2 = new Kissa("Emo2", 5);
            Kissa[] emot = new Kissa[] { emo, emo2 };
            emo2.LisaaPentu("Neljäs");
            emo2.LisaaPentu("Viides");
            emo2.LisaaPentu("Kuudes");
            foreach (Kissa taulukkoemo in emot)
            {
                foreach (Kissa pentu in taulukkoemo.Pennut())
                {
                    Console.WriteLine(pentu.PalautaElaimenNimi() + 
                        " on kissan " + pentu.Emo.PalautaElaimenNimi() + " pentu.");
                }
            }
        }
    }
}
