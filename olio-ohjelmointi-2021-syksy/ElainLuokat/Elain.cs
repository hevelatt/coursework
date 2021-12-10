using System;

namespace ElainLuokat
{
    public abstract class Elain
    {
        private int ika;
        public string nimi;
        private bool onLihanSyoja;

        public bool AsetaElaimenIka(int ika)
        {
            if (ika < 0)
            {
                return false;
            }
            this.ika = ika;
            return true;
        }

        public bool AsetaElaimenNimi(string nimi)
        {
            this.nimi = nimi;
            return true;
        }

        public int PalautaElaimenIka()
        {
            return this.ika;
        }

        public string PalautaElaimenNimi()
        {
            return this.nimi;
        }

        public void AsetaOnLihanSyoja(bool onLihanSyoja)
        {
            this.onLihanSyoja = onLihanSyoja;
        }

        public bool PalautaOnLihanSyoja()
        {
            return this.onLihanSyoja;
        }

        public virtual void Aantele()
        {
            Console.WriteLine("Umph!");
        }
    }
}
