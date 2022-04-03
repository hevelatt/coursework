/// <summary>
/// Puupelin käsittelijä.
/// </summary>
namespace Puupeli.controller
{
    using Puupeli.model;
    internal class PeliLogiikka
    {
        private static readonly Random s_satunnaisluku = new();
        private readonly TietokantaHallinta _tietokantaHallinta;
        private readonly AlueKasittelija _alueKasittelija;
        private readonly Pelaaja _pelaaja;
        private Alue?[,] _ladatutAlueet;

        internal PeliLogiikka(TietokantaHallinta kaytettyTietokanta, Pelaaja valittuPelaaja)
        {
            _tietokantaHallinta = kaytettyTietokanta;
            _pelaaja = valittuPelaaja;
            _ladatutAlueet = new Alue[9,9];
        }
        // Wrappaa alueet bool pareiksi: eli flag muuttuiko alue? jotta tiedetään unloadatessa että alue muuttui
        // ja tarvii tallentaa

        // Ladataan neliönmuotoinen alue
        // kun tiputaan alueelta ladataan uusi neliö
        // kun uusi neliö ladataan on kai sama että querutaan kaikki? vai jollain tapaa
        // määritellään mitkä pitää ladata ja mitkä ei?
        
        // 1. Tallenna putoavat alueet
        // 2. Siirrä pidettävät alueet uudelle paikalleen
        // 3. Lataa uudet alueet

        internal void Liiku(Suunta suunta)
        {
            _pelaaja.Liiku(suunta);
            _alueKasittelija.Siirry(_pelaaja.AlueX, _pelaaja.AlueY);
        }

        internal void KaadaPuu()
        {
            _pelaaja.TeeAlueella(KaadaPuuAlueella);
        }
        private Alue? HaeAlue(int x, int y)
        {
            // TODO: Hae äläkä vain luo.
            return _alueKasittelija.HaeAlue(x, y);
        }
        private void KaadaPuuAlueella(int x, int y)
        {
            if (HaeAlue(x, y) is not Metsa m) return;
            m.PuuKaatui += m_PuuKaatui;
            m.KaadaPuu();
            m.PuuKaatui -= m_PuuKaatui;
        }
        private void m_PuuKaatui(object? sender, EventArgs _)
        {
            // merkitse sender muuttui!
            _pelaaja.LisaaPolttopuita(s_satunnaisluku.Next(1, 5));
        }
    }
}
