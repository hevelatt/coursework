using System;
using ElainLuokat;

namespace _01_kissa
{
    class Program
    {
        static void Main(string[] args)
        {
            // 1. Ota Uusi Kissa-luokka Program.cs:ssä käyttöön luomalla uusi kissa-olio parametrittomalla konstruktorilla. 
            Kissa kisu = new Kissa();
            // 2. Anna sen jälkeen kissalle nimi suoraan public muuttujalla. 
            kisu.nimi = "Mirri";
            // 3. Tulosta kissan nimi. 
            Console.WriteLine(kisu.nimi);
            // 4. Anna kissalle uusi nimi public -metodilla 
            kisu.AsetaKissanNimi("Miuku");
            // 5. Tulosta kissan nimi. 
            Console.WriteLine(kisu.PalautaKissanNimi());
            // 6. Tulosta kissan ikä. 
            Console.WriteLine(kisu.PalautaKissanIka());
            // 7. Tee uusi kissa-olio ja anna ikä ja nimi konstruktorissa. 
            Kissa katti = new Kissa(5, "Karvinen");
            // 8. Tulosta uuden olion tiedot nimi ja ikä. 
            Console.WriteLine(katti.PalautaKissanNimi() + " " + katti.PalautaKissanIka());

            // Kissa: Luokan arvon palauttaminen
            Console.WriteLine("================================");
            
            // Luo uusi Kissa-olio ja laita muuttujan nimeksi katti.
            katti = new Kissa();

            // Testaa ohjelmalla että oletusnimi ja -ikä toimivat ja saat muutettua niitä.
            // Console.WriteLine(katti.PalautaKissanNimi() + " " + katti.PalautaKissanIka());

            // Aseta katin nimeksi ensin "Anneli" ja tulosta onnistuiko nimen asetus vai ei.
            TulostaOnnistuikoNimenAsetus(katti.AsetaKissanNimi("Anneli"));
            // Tulosta perään olion katti sen hetkinen nimi.
            Console.WriteLine(katti.PalautaKissanNimi());
            // Aseta katin nimeksi "Hilda" ja tulosta onnistuiko se vai ei.
            TulostaOnnistuikoNimenAsetus(katti.AsetaKissanNimi("Hilda"));
            // Tulosta perään katin sen hetkinen nimen.
            Console.WriteLine(katti.PalautaKissanNimi());

            // Kissa: Override ToString()
            Console.WriteLine("================================");

            katti = new Kissa();
            Console.WriteLine(katti);
        }

        private static void TulostaOnnistuikoNimenAsetus(bool onnistui)
        {
            if (onnistui)
            {
                Console.WriteLine("Nimen asetus onnistui!");
            }
            else
            {
                Console.WriteLine("Nimen asetus ei onnistunut!");
            }
        }
    }
}
