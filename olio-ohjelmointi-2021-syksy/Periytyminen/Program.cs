using System;
using ElainLuokat;

namespace Periytyminen
{
    class Program
    {
        static void Main(string[] args)
        {
            Kissa kissa = new Kissa();
            kissa.AsetaElaimenNimi("Mirri");
            kissa.AsetaElaimenIka(5);
            kissa.AsetaOnLihanSyoja(true);
            Console.WriteLine(kissa.PalautaElaimenNimi() + ", ikä " + kissa.PalautaElaimenIka() + ", " +
                (kissa.PalautaOnLihanSyoja() ? "" : "ei ") + "syö lihaa.");
            kissa.Kehraa();

            Koira koira = new Koira();
            koira.AsetaElaimenNimi("Musti");
            koira.AsetaElaimenIka(16);
            koira.AsetaOnLihanSyoja(false);
            Console.WriteLine(koira.PalautaElaimenNimi() + ", ikä " + koira.PalautaElaimenIka() + ", " +
                (koira.PalautaOnLihanSyoja() ? "" : "ei ") + "syö lihaa.");
            // koira.Kehraa();

            // Elain elain = new Elain();
            // Nisakkaat nisakas = new Nisakkaat();
            // Console.WriteLine("Eläin ääntelee:");
            // elain.Aantele();
            // Console.WriteLine("Nisäkäs ääntelee:");
            // nisakas.Aantele();
            Console.WriteLine("Kissa ääntelee:");
            kissa.Aantele();
            Console.WriteLine("Koira ääntelee:");
            koira.Aantele();

            Koira hauva = new Koira();
            hauva.AsetaElaimenNimi("Hauva");
            hauva.AsetaElaimenIka(2);
            hauva.OnKarvapeite = true;
            Console.WriteLine(hauva.PalautaElaimenNimi() + ", ikä " + hauva.PalautaElaimenIka() + ", " +
                (hauva.OnKarvapeite ? "on" : "ei ole") + " karvapeitteinen.");

            Papukaija kaija = new Papukaija();
            kaija.AsetaElaimenNimi("Kaija");
            kaija.AsetaElaimenIka(5);
            kaija.AsetaSiipivali(104.14);
            Console.WriteLine(kaija.PalautaElaimenNimi() + ", ikä " + kaija.PalautaElaimenIka() + 
                ", siipiväli " + kaija.PalautaSiipivali() + " cm.");
        }
    }
}
