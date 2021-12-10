using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _06_Musiikkikappale
{
    class Musiikkikappale
    {
        private string nimi;
        private int pituus;

        public Musiikkikappale(string kappaleenNimi, int kappaleenPituus)
        {
            this.nimi = kappaleenNimi;
            this.pituus = kappaleenPituus;
        }

        public string Nimi()
        {
            return this.nimi;
        }

        public int Pituus()
        {
            return this.pituus;
        }
    }
}
