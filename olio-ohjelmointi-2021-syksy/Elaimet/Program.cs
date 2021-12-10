using System;
using ElainLuokat;

namespace Elaimet
{
    class Program
    {
        static void Main(string[] args)
        {
            Koira koira = new Koira();
            Console.WriteLine("Onnistuiko nimen vaihto: " + koira.AsetaKoiranNimi("Max"));
            Console.WriteLine("Koiran nimi: " + koira.PalautaKoiranNimi());
            Console.WriteLine("Onnistuiko nimen vaihto: " + koira.AsetaKoiranNimi("musti"));
            Console.WriteLine("Koiran nimi: " + koira.PalautaKoiranNimi());
        }
    }
}
