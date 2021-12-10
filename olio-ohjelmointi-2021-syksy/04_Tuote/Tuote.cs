using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _04_Tuote
{
    class Tuote
    {
        private double hinta;
        private int lkm;
        private string nimi;

        public Tuote(string nimiAlussa, double hintaAlussa, int maaraAlussa)
        {
            this.hinta = hintaAlussa;
            this.lkm = maaraAlussa;
            this.nimi = nimiAlussa;
        }

        public void TulostaTuote()
        {
            if (!string.IsNullOrEmpty(this.nimi))
            {
                Console.Write($"{char.ToUpper(this.nimi[0])}{this.nimi.Substring(1)}, ");
            }
            Console.WriteLine($"hinta {this.hinta.ToString(System.Globalization.CultureInfo.InvariantCulture)}, {this.lkm} kpl");
        }
    }
}
