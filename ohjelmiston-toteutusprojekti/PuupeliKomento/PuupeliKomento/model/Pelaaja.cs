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

        ////internal event Action<int, int>? PelaajaKaataaPuun;


        //internal delegate void PuunKaataminen(object sender, EventArgs args);
        //// avainsana event **ainoastaan** estää sijoituksen
        //internal event PuunKaataminen? PuuKaatuu;
        //// sama kuin
        //// internal event Action<object, EventArgs>? PuuKaatuu;
        //// sama kuin
        //// internal event EventHandler? PuuKaatuu;
        //// delegaattiin voi lisäksi aina lisätä uusia funktioita
        //// EventArgs tai peritty tyyppi
        ////protected virtual void OnPelaajaKaataaPuun(EventArgs e)
        //// RIITTÄÄKÖ FUNC?





        //internal virtual void OnPelaajaKaataaPuun()
        //{
        //    //PuuKaatuu(this, EventArgs.Empty);
        //    PuuKaatuu?.Invoke(this, EventArgs.Empty);
        //}

        ////vai sittenkin callback

        //internal Func<int, int, int>? PuutaKaadettaessa;

        //internal delegate int PuuKaatuu(int x, int y);

        // Pelaaja haluaa vain tietää millä tavalla saa polttopuunsa joten ei riippuvainen metsästä
        // Pelaaja voi kertoa sijaintinsa (mutta toisaalta miksi)
        // Ongelmat: Pelaaja ei voi kaataa puuta muuten vaan ja ainoastaan yksi asia tapahtuu kun tämä kaataa puun
        // Miksi puun kaatumisen pitää välttämättä johtaa polttopuihin? Ehkä kyseessä pitäisi olla "hanki polttopuita"
        // ja erillinen event raiser (onpuukaada) joka ilmoittaa muille että nyt kaadettiin puita
        // eli voidaan logiikassa määritellä että kun pelaaja kaataa puun niin tämä hankkii polttopuita sen verran kuin saa metsästä
        // näin puita voi hankkia monella tapaa (mutta vain yhdellä tapaa kerrallaan)
        //internal void KaadaPuu(PuuKaatuu p)
        //{
            //Polttopuita += p(AlueX, AlueY);
        //}

        //// Ongelmat: 
        //// Pelaajan kuuluisi tietää missä on, sitä ei pitäisi kertoa pelaajalle
        //// Pelaajan täytyy ottaa huomioon KaadaPuun toteutus satunnaisluvun tuomisessa
        //// Pelaaja tulee riippuvaiseksi metsän olemassaolosta
        //internal void KaadaPuu(Metsa m)
        //{
            //Polttopuita += m.KaadaPuu(new Random());
        //}

        internal event EventHandler? KaadaPuu;
        protected virtual void OnKaadaPuu(EventArgs e) 
        {
            KaadaPuu?.Invoke(this, e);
        }
        internal delegate T TeeAlueella<T>(int x, int y);
        internal int HankiPolttopuita(TeeAlueella<int> keraaPuita)
        {
            return Polttopuita += keraaPuita(AlueX, AlueY);
        }
    }
}
