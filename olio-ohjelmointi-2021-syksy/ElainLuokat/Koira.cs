using System;

namespace ElainLuokat
{
    public class Koira : Nisakkaat
    {
        public override void Aantele()
        {
            Console.WriteLine("Hau!");
        }

        public override void MiltaNaytan()
        {
            throw new NotImplementedException();
        }

        // Varmistetaan yhteensopivuus tehtävän Elaimet kanssa.

        private const int Oletusika = 0;
        private const string Oletusnimi = "Haukkuli";

        public Koira()
        {
            base.AsetaElaimenIka(Oletusika);
            base.AsetaElaimenNimi(Oletusnimi);
        }

        public Koira(int ika, string nimi)
        {
            if (!this.AsetaKoiranIka(ika))
            {
                base.AsetaElaimenIka(Oletusika);
            }
            if (!this.AsetaKoiranNimi(nimi))
            {
                base.AsetaElaimenNimi(Oletusnimi);
            }
        }

        public string PalautaKoiranNimi()
        {
            return base.PalautaElaimenNimi();
        }

        public int PalautaKoiranIka()
        {
            return base.PalautaElaimenIka();
        }

        /// <summary>
        /// Asettaa koiran iän. Jos ikä on negatiivinen, ikää ei aseteta.
        /// </summary>
        /// <param name="ika">Ikä, joka koiralle asetetaan.</param>
        /// <returns>True, jos ikä asetettiin ja se on positiivinen.</returns>
        public bool AsetaKoiranIka(int ika)
        {
            return base.AsetaElaimenIka(ika);
        }

        /// <summary>
        /// Asettaa koiran nimen. Jos nimi on "Musti", nimeä ei aseteta.
        /// </summary>
        /// <param name="uusiNimi">Nimi, joka koiralle asetetaan.</param>
        /// <returns>True, jos nimi asetettiin ja se ei ole "Musti".</returns>
        public bool AsetaKoiranNimi(string uusiNimi)
        {
            if (uusiNimi.Equals("Musti", StringComparison.OrdinalIgnoreCase))
            {
                return false;
            }
            base.AsetaElaimenNimi(uusiNimi);
            return true;
        }

        public override string ToString()
        {
            return $"Koira: {this.PalautaKoiranNimi()}, ikä {this.PalautaKoiranIka()}.";
        }
    }
}
