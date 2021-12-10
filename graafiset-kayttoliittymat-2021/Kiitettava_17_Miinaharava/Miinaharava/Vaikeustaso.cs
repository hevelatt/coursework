using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Miinaharava
{
    class Vaikeustaso
    {
        public int Leveys { get; }
        public int Korkeus { get; }
        public int Ruutuja { get; }
        public int Miinoja { get; }
        public int ParasAika { get; set; }
        public string Nimi { get; }

        public Vaikeustaso(string nimi, int leveys, int korkeus, int miinoja)
        {
            Leveys = leveys;
            Korkeus = korkeus;
            Ruutuja = leveys * korkeus;
            Miinoja = miinoja;
            ParasAika = 0;
            Nimi = nimi;
        }
    }
}
