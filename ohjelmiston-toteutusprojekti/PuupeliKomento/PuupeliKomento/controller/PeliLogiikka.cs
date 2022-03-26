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

        private List<Alue> LataaAlueet(Range x, Range y)
        {
            _ladatutAlueet.GetLength(1);
            return null;
        }

        internal void KaadaPuu()
        {
            _pelaaja.TeeAlueella(KaadaPuuAlueella);
        }
        private Alue? HaeAlue(int x, int y)
        {
            // TODO: Hae äläkä vain luo.
            return new Metsa(x, y, 20);
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
