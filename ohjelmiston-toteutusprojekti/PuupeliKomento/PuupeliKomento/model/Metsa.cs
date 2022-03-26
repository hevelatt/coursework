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

        internal event EventHandler? PuuKaatui;
        private protected virtual void OnPuuKaatui(EventArgs e)
        {
            PuuKaatui?.Invoke(this, e);
        }
        internal void KaadaPuu()
        {
            if (Puita == 0)
            {
                return;
            }
            --Puita;
            OnPuuKaatui(EventArgs.Empty);
        }
    }
}
