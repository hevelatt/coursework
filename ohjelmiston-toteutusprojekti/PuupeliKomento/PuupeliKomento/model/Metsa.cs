namespace Puupeli.model
{
    internal class Metsa : Alue
    {
        private const int PolttopuitaKorkeintaan = 5;
        private const int PolttopuitaVahintaan = 2;

        internal override string Tyyppi { get; }
        internal int Puita { get; private set; }

        internal Metsa(int sijaintiX, int sijaintiY, int puita) : base(sijaintiX, sijaintiY)
        {
            Tyyppi = "Metsä";
            Puita = puita;
        }

        internal int KaadaPuu(Random satunnaisluku)
        {
            if (Puita == 0)
            {
                return 0;
            }
            --Puita;
            return satunnaisluku.Next(PolttopuitaVahintaan, PolttopuitaKorkeintaan + 1);
        }
    }
}
