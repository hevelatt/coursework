/// <summary>
/// Puupelin käsittelijä.
/// </summary>
namespace Puupeli.controller
{
    using Puupeli.model;
    internal class PeliLogiikka
    {
        private static readonly Random s_satunnaisluku = new();
        private readonly Pelaaja _pelaaja = new ("", 0, 0, 0);
        internal void run()
        {
            _pelaaja.TeeAlueella(KaadaPuuAlueella);
            Console.WriteLine(_pelaaja.Polttopuita);
            //p.PuutaKaadettaessa += KaadaPuu;
            //p.PuutaKaadettaessa = KaadaPuu;
            //p.KaadaPuu();
        }

        private Alue? HaeAlue(int x, int y)
        {
            // TODO: Hae äläkä vain luo.
            return new Metsa(x, y, 20);
        }


        //private int KaadaPuuAlueella(int x, int y)
        //{
        //    if (HaeAlue(x, y) is not Metsa m) return 0;
        //    m.PuuKaatuu += m_PuuKaatuu;
        //    int polttopuita = 0;
        //    if (m.KaadaPuu())
        //    {
        //        polttopuita = s_satunnaisluku.Next(5);
        //    }
        //    m.PuuKaatuu -= m_PuuKaatuu;
        //    return polttopuita;
        //}

        private void KaadaPuuAlueella(int x, int y)
        {
            if (HaeAlue(x, y) is not Metsa m) return;
            m.PuuKaatui += m_PuuKaatui;
            m.KaadaPuu();
            m.PuuKaatui -= m_PuuKaatui;
        }

        private void m_PuuKaatui(object? _, EventArgs __)
        {
            //Metsa? m = lahettaja as Metsa;
            //if (m!.SijaintiX == _pelaaja.AlueX && m!.SijaintiY == _pelaaja.AlueY)
            //{
            _pelaaja.LisaaPolttopuita(s_satunnaisluku.Next(1, 5));
            //}
        }
    }
}
