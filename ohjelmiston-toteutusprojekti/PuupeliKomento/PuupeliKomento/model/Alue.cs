namespace Puupeli.model
{
    internal abstract class Alue
    {
        internal int SijaintiX { get; }
        internal int SijaintiY { get; }
        internal abstract string Tyyppi { get; }

        private protected Alue(int sijaintiX, int sijaintiY)
        {
            SijaintiX = sijaintiX;
            SijaintiY = sijaintiY;
        }
    }
}
