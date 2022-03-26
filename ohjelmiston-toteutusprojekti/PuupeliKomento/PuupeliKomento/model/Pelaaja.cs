namespace Puupeli.model
{
    internal enum Suunta { Ita, Etela, Lansi, Pohjoinen }

    internal class Pelaaja
    {
        internal string Nimi { get; }
        internal int AlueX { get; private set; }
        internal int AlueY { get; private set; }
        internal int Polttopuita { get; private set; }

        internal Pelaaja(string nimi, int alueX, int alueY, int polttopuita)
        {
            Nimi = nimi;
            AlueX = alueX;
            AlueY = alueY;
            Polttopuita = polttopuita;
        }

        internal bool Liiku(Suunta suunta)
        {
            switch (suunta)
            {
                case Suunta.Ita:
                    AlueX += 1;
                    break;
                case Suunta.Etela:
                    AlueY += 1;
                    break;
                case Suunta.Lansi:
                    AlueX -= 1;
                    break;
                case Suunta.Pohjoinen:
                    AlueY -= 1;
                    break;
                default:
                    return false;
            }
            return true;
        }


        //internal delegate T TeeAlueella<T>(int x, int y);
        //internal int HankiPolttopuita(TeeAlueella<int> keraaPuita)
        //{
        //    return Polttopuita += keraaPuita(AlueX, AlueY);
        //}
        //internal Action<int, int> TeeAlueella = (x, y) => {};
        internal void TeeAlueella(Action<int, int> teeAlueella)
        {
            teeAlueella(AlueX, AlueY);
        }
        internal void LisaaPolttopuita(int polttopuita)
        {
            Polttopuita += polttopuita;
        }
    }
}
