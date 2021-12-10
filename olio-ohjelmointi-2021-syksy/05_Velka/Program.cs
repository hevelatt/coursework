using System;
using System.Text;

namespace _05_Velka
{
    class Program
    {
        static void Main(string[] args)
        {
            // Otetaan konsolitulosteelle käyttöön UTF8-merkistökoodaus,
            // saadaan käyttöön mm. euromerkki.
            Console.OutputEncoding = Encoding.UTF8;

            Velka velka = new Velka(100, 0.05);
            velka.TulostaSaldo();
            velka.OdotaVuosi();
            velka.TulostaSaldo();
            velka.OdotaVuosi();
            velka.TulostaSaldo();
        }
    }
}
