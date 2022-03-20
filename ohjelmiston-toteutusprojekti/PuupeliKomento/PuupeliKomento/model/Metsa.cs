namespace Puupeli.model
{
    internal class Metsa : Alue
    {
        internal override string Tyyppi { get; }
        internal int Puita { get; }

        internal Metsa(int sijaintiX, int sijaintiY, int puita) : base(sijaintiX, sijaintiY)
        {
            Tyyppi = "Metsä";
            Puita = puita;
        }
    }
}
