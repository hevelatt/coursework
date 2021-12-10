using System;
using System.Collections.Generic;

namespace ElainLuokat
{
    public class Kissa : Nisakkaat
    {
        // ==== Pennut ja emot ====
        private List<Kissa> pennut = new List<Kissa>();
        private Kissa emo;
        public Kissa Emo
        {
            get
            {
                return emo;
            }
        }

        public int LisaaPentu(string nimi, int ika = 0)
        {
            pennut.Add(new Kissa(nimi, this, ika));
            return pennut.Count;
        }

        public List<Kissa> Pennut()
        {
            return pennut;
        }

        public Kissa(string nimi, int ika = 0)
        {
            AsetaElaimenNimi(nimi);
            AsetaElaimenIka(ika);
        }

        public Kissa(string nimi, Kissa emo, int ika = 0) : this(nimi, ika)
        {
            this.emo = emo;
        }
        // ========================

        public void Kehraa()
        {
            Console.WriteLine("hrrrr");
        }

        public override void Aantele()
        {
            Console.WriteLine("Miau!");
        }

        public override void MiltaNaytan()
        {
            throw new NotImplementedException();
        }

        // Varmistetaan yhteensopivuus tehtävän 01_kissa kanssa.

        private const int Oletusika = 0;
        private const string Oletusnimi = "Miuku";
        public Kissa()
        {
            base.AsetaElaimenIka(Oletusika);
            base.AsetaElaimenNimi(Oletusnimi);
        }

        public Kissa(int ika, string nimi)
        {
            if (!this.AsetaKissanIka(ika))
            {
                base.AsetaElaimenIka(Oletusika);
            }
            if (!this.AsetaKissanNimi(nimi))
            {
                base.AsetaElaimenNimi(Oletusnimi);
            }
        }

        public string PalautaKissanNimi()
        {
            return base.PalautaElaimenNimi();
        }

        public int PalautaKissanIka()
        {
            return base.PalautaElaimenIka();
        }

        /// <summary>
        /// Asettaa kissan iän. Jos ikä on negatiivinen, ikää ei aseteta.
        /// </summary>
        /// <param name="ika">Ikä, joka kissalle asetetaan.</param>
        /// <returns>True, jos ikä asetettiin ja se on positiivinen.</returns>
        public bool AsetaKissanIka(int ika)
        {
            return base.AsetaElaimenIka(ika);
        }

        /// <summary>
        /// Asettaa kissan nimen. Jos nimi on "Hilda", nimeä ei aseteta.
        /// </summary>
        /// <param name="uusiNimi">Nimi, joka kissalle asetetaan.</param>
        /// <returns>True, jos nimi asetettiin ja se ei ole "Hilda".</returns>
        public bool AsetaKissanNimi(string uusiNimi)
        {
            if (uusiNimi.Equals("Hilda", StringComparison.OrdinalIgnoreCase))
            {
                return false;
            }
            base.AsetaElaimenNimi(uusiNimi);
            return true;
        }

        public override string ToString()
        {
            return $"Kissa: {this.PalautaKissanNimi()}, ikä {this.PalautaKissanIka()}.";
        }
    }
}
