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

        //internal event EventHandler? KaadaPuu;
        //private protected virtual void OnKaadaPuu(EventArgs e) 
        //{
        //    KaadaPuu?.Invoke(this, e);
        //}

        //internal void HeilutaKirvesta()
        //{
            // internal void KaadaPuu()
            // Mistä tietää minkä puun? Mitä tämä edes tarkoittaa? Pelaajaa siis pyydetään kaatamaan puu ilman mitään kontekstia
            // Mitä jos alueella ei ole puita? Pitääkö pelaajalle kertoa miten puu kaadetaan? Koska ei se osaa...
            // Ei se voi osata koska sillä ei ole mitään käsitystä... Se voi heiluttaa kirvestä mutta ei pelaaja tunne puun konseptia
            // se pitäisi siis antaa pelaajalle käsiteltäväksi, mutta silloin pelaaja muuttuu riippuvaiseksi puu-luokasta.
        //}

        internal delegate T TeeAlueella<T>(int x, int y);
        internal int HankiPolttopuita(TeeAlueella<int> keraaPuita)
        {
            return Polttopuita += keraaPuita(AlueX, AlueY);
        }
    }
}
