namespace Puupeli.model
{
    internal class Metsa : Alue
    {
        internal override string Tyyppi { get; }
        internal int Puita { get; private set; }

        internal Metsa(int sijaintiX, int sijaintiY, int puita) : base(sijaintiX, sijaintiY)
        {
            Tyyppi = "Metsä";
            Puita = puita;
        }

        internal event EventHandler? PuuKaatuu;
        private protected virtual void OnPuuKaatuu(EventArgs e)
        {
            PuuKaatuu?.Invoke(this, e);
        }
        internal bool KaadaPuu()
        {
            if (Puita == 0)
            {
                return false;
            }
            --Puita;
            OnPuuKaatuu(EventArgs.Empty);
            return true;
        }
    }
}
