using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Puupeli.model;

namespace Puupeli.controller
{
    internal class AlueKasittelija
    {
        private int _x;
        private int _y;
        private Alue?[,] _ladatutAlueet;

        internal AlueKasittelija(int x, int y)
        {
            _x = x;
            _y = y;
            _ladatutAlueet = new Alue?[9, 9];
        }

        internal Alue? HaeAlue(int x, int y)
        {
            // _x, _y = _ladatutAlueet[4,4]
            int deltaX = _x - x;
            int deltaY = _y - y;
            return _ladatutAlueet[4 + deltaY, 4 + deltaX];
        }
        // Ehdot Func<bool>:na tietokantahallinnalle
        internal void LataaAlueet(int x, int y)
        {
            int deltaX = _x - x;
            int deltaY = _y - y;
            _x = x;
            _y = y;
            if (x < x && x < x || y < y && y < y) ;
        }
    }
}
