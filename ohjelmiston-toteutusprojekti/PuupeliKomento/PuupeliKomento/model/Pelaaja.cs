namespace Puupeli.model
{
    internal enum Suunta { Ita, Etela, Lansi, Pohjoinen }

    internal class Pelaaja
    {
        internal string Nimi { get; }
        internal int X { get; private set; }
        internal int Y { get; private set; }
        private int _polttopuita;

        internal Pelaaja(string nimi, int x, int y, int polttopuita)
        {
            Nimi = nimi;
            X = x;
            Y = y;
            _polttopuita = polttopuita;
        }

        internal void Liiku(Suunta suunta)
        {
            switch (suunta)
            {
                case Suunta.Ita:
                    X += 1;
                    break;
                case Suunta.Etela:
                    Y += 1;
                    break;
                case Suunta.Lansi:
                    X -= 1;
                    break;
                case Suunta.Pohjoinen:
                    Y -= 1;
                    break;
            }
        }

        internal void TeeAlueella(Action<int, int> teeAlueella)
        {
            teeAlueella(X, Y);
        }

        internal void LisaaPolttopuita(int polttopuita)
        {
            if (polttopuita <= 0) return;
            _polttopuita += polttopuita;
        }
    }
}
