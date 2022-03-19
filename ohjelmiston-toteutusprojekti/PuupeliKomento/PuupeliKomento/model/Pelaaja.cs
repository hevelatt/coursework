namespace Puupeli.model
{
    internal class Pelaaja
    {
        internal string Nimi { get; }
        internal int AlueX { get; set; }
        internal int AlueY { get; set; }
        internal int Polttopuita { get; set; }

        internal Pelaaja(string nimi, int alueX, int alueY, int polttopuita)
        {
            Nimi = nimi;
            AlueX = alueX;
            AlueY = alueY;
            Polttopuita = polttopuita;
        }
    }
}
