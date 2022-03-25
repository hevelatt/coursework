/// <summary>
/// Puupelin käsittelijä.
/// </summary>
namespace Puupeli.controller
{
    using Puupeli.model;
    internal class PeliLogiikka
    {
        internal void run()
        {
            var p = new Pelaaja("",0,0,0);
            p.HankiPolttopuita(KaadaPuuAlueella);
            Console.WriteLine(p.Polttopuita);
            //p.PuutaKaadettaessa += KaadaPuu;
            //p.PuutaKaadettaessa = KaadaPuu;
            //p.KaadaPuu();
        }

        private Metsa? HaeMetsa(int x, int y)
        {
            // TODO: Hae äläkä vain luo.
            return new Metsa(x, y, 20);
        }

        private static readonly Random satunnaisluku = new();
        private int KaadaPuuAlueella(int x, int y)
        {
            Metsa? metsa = HaeMetsa(x, y);
            if (metsa == null) return 0;
            metsa.PuuKaatuu += PuuKaatunut;
            int polttopuita = 0;
            if (metsa.KaadaPuu())
            {
                polttopuita = satunnaisluku.Next(5);
            }
            metsa.PuuKaatuu -= PuuKaatunut;
            return polttopuita;
        }
        
        private void PuuKaatunut(object? sender, EventArgs _)
        {
            Console.WriteLine("Rumpsis");
        }
    }
}
