using System;

namespace _02_pilli
{
    class Program
    {
        static void Main(string[] args)
        {
            Pilli sorsapilli = new Pilli("Kääk");
            Pilli kukkopilli = new Pilli("Kukko-kiekuu");
            sorsapilli.Soi();
            kukkopilli.Soi();
            sorsapilli.Soi();
        }
    }
}
