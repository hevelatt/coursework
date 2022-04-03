namespace Puupeli.model
{
    internal class Metsa : Alue
    {
        private int _puita;

        internal Metsa(int puita)
        {
            _puita = puita;
        }

        internal event EventHandler? PuuKaatui;
        private protected virtual void OnPuuKaatui(EventArgs e)
        {
            PuuKaatui?.Invoke(this, e);
        }
        internal void KaadaPuu()
        {
            if (_puita == 0)
            {
                return;
            }
            --_puita;
            OnPuuKaatui(EventArgs.Empty);
        }
    }
}
