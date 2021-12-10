using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _02_pilli
{
    class Pilli
    {
        private string aani;

        public Pilli(string pillinAani)
        {
            this.aani = pillinAani;
        }

        public void Soi()
        {
            Console.WriteLine(this.aani);
        }
    }
}
