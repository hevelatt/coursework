using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Puupeli.model;

namespace Puupeli.controller
{
    // TODO: Tarvitseeko alueiden tietää koordinaattejaan?
    internal class AlueKasittelija
    {
        /// <summary>Ladattujen alueiden säde.</summary>
        private const int _r = 4;
        /// <summary>Ladattujen alueiden halkaisija.</summary>
        private const int _d = 2 * _r + 1;
        /// <summary>Ladatut alueet.</summary>
        private Alue?[,] _a = new Alue?[_d, _d];
        /// <summary>Ladattujen alueiden origo x.</summary>
        private int _ox;
        /// <summary>Ladattujen alueiden origo y.</summary>
        private int _oy;

        /// <summary>Tee f määrätyille kaksiulotteisen taulukon alkioille.</summary>
        /// <param name="f">Alkioille tehtävä toiminta.</param>
        /// <param name="a">1. ulottuvuuden aloitusindeksi.</param>
        /// <param name="b">1. ulottovuuden lopetusindeksi.</param>
        /// <param name="c">2. ulottuvuuden aloitusindeksi.</param>
        /// <param name="d">2. ulottovuuden lopetusindeksi.</param>
        private static void TeeAlkioille(Action<int, int> f, int a, int b, int c, int d)
        {
            for (int i = a; i < b; ++i)
            {
                for (int j = c; j < d; ++j)
                {
                    f(i, j);
                }
            }  
        }

        /// <summary>Alusta aluekäsittelijä koordinaateilla.</summary>
        internal AlueKasittelija(int x, int y)
        {
            _ox = x;
            _oy = y;
            // TODO: Lataa alueet.
        }

        /// <summary>Palauttaa alueen annetuissa koordinaateista.</summary>
        internal Alue HaeAlue(int x, int y)
        {
            int i = _r + y - _oy;
            int j = _r + x - _ox;
            Alue? alue;
            if (i < 0 || i >= _d || j < 0 || j >= _d)
            {
                alue = HaeTietokannasta(x, y);
            }
            else
            {
                alue = _a[i, j];
            }
            return alue ?? LuoAlue(x, y);
        }

        /// <summary>Siirry uudelle alueelle.</summary>
        /// <param name="x">Uuden alueen x-koordinaatti.</param>
        /// <param name="y">Uuden alueen y-koordinaatti.</param>
        internal void Siirry(int x, int y)
        {
            int i = _r + y - _oy;
            int j = _r + x - _ox;
            if (i < 0 || i >= _d || j < 0 || j >= _d)
            {
                VaihdaAlueet(x, y);
            }
        }
        
        /// <summary>
        ///  dX>0, dY<0     dX>0, dY=0       dX>0, dY>0       dX=0, dY>0  dX<0, dY>0     dX<0, dY=0       dX<0, dY<0       dX=0, dY<0
        /// .........................................................................................................................
        /// ....CCCCCCCCC..................................................................................................CCCCCCCCC.
        /// ....CCCCCCCCC.................................................................................CCCCCCCCC........CCCCCCCCC.
        /// ....CCCCCCCCC.................................................................................CCCCCBBBBAAAAA...CCCCCCCCC.
        /// ....CCCCCCCCC.................................................................................CCCCCBBBBAAAAA...CCCCCCCCC.
        /// ....CCCCXCCCC.................................................................................CCCCCBBBBAAAAA...CCCCXCCCC.
        /// .AAABBBBBBCCC...AAAAABBBBCCCCC...AAAAAAAAA........AAAAAAAAA......AAAAAAAAA...CCCCCBBBBAAAAA...CCCCXBBBBAAAAA...BBBBBBBBB.
        /// .AAABBBBBBCCC...AAAAABBBBCCCCC...AAAAAAAAA........AAAAAAAAA......AAAAAAAAA...CCCCCBBBBAAAAA...CCCCCBBBB#AAAA...BBBBBBBBB.
        /// .AAABBBBBBCCC...AAAAABBBBCCCCC...AAAAABBBBCCCCC...AAAAAAAAA......AAAAAAAAA...CCCCCBBBBAAAAA...CCCCCBBBBAAAAA...BBBBBBBBB.
        /// .AAABBBBBBCCC...AAAAABBBBCCCCC...AAAAABBBBCCCCC...AAAAAAAAA......AAAAAAAAA...CCCCCBBBBAAAAA...CCCCCBBBBAAAAA...BBBBBBBBB.
        /// .AAAA#AAAA......AAAA#BBBBXCCCC...AAAA#BBBBCCCCC...AAAA#AAAA......AAAA#AAAA...CCCCXBBBB#AAAA...CCCCCBBBBAAAAA...AAAA#AAAA.
        /// .AAAAAAAAA......AAAAABBBBCCCCC...AAAAABBBBCCCCC...BBBBBBBBB...CCCBBBBBBAAA...CCCCCBBBBAAAAA........AAAAAAAAA...AAAAAAAAA.
        /// .AAAAAAAAA......AAAAABBBBCCCCC...AAAAABBBBXCCCC...BBBBBBBBB...CCCBBBBBBAAA...CCCCCBBBBAAAAA....................AAAAAAAAA.
        /// .AAAAAAAAA......AAAAABBBBCCCCC...AAAAABBBBCCCCC...BBBBBBBBB...CCCBBBBBBAAA...CCCCCBBBBAAAAA....................AAAAAAAAA.
        /// .AAAAAAAAA......AAAAABBBBCCCCC...AAAAABBBBCCCCC...BBBBBBBBB...CCCBBBBBBAAA...CCCCCBBBBAAAAA....................AAAAAAAAA.
        /// ......................................CCCCCCCCC...CCCCXCCCC...CCCCXCCCC..................................................
        /// ......................................CCCCCCCCC...CCCCCCCCC...CCCCCCCCC..................................................
        /// ..................................................CCCCCCCCC...CCCCCCCCC..................................................
        /// ..................................................CCCCCCCCC...CCCCCCCCC..................................................
        /// ..................................................CCCCCCCCC...CCCCCCCCC..................................................
        /// .........................................................................................................................
        /// </summary>
        private void VaihdaAlueet(int x, int y)
        {
            // Ladattujen alueiden siirtymä.
            int dx = x - _ox;
            int dy = x - _oy;
            // Tallenna alueet A.
            TallennaAlueet(dx, dy);
            // Siirrä alueet B.
            SiirraAlueet(dx, dy);
            // Lataa alueet C.
            LataaAlueet(dx, dy);
            // Uusi origo.
            _ox = x;
            _oy = y;
        }

        private void TallennaAlueet(int dx, int dy)
        {
            List<Alue> tallenna = new();
            void LisaaTallennus(int i, int j)
            {
                Alue? alue = _a[i, j];
                if (alue == null) return;
                tallenna.Add(alue);
            }
            int a, b, c, d;
            if (dy < 0)
            {
                a = _d + dy;
                b = _d;
            }
            else
            {
                a = 0;
                b = dy;
            }
            TeeAlkioille(LisaaTallennus, a, b, 0, _d);
            if (dy < 0)
            {
                a = 0;
                b = _d + dy;
            }
            else
            {
                a = dy;
                b = _d;
            }
            if (dx < 0)
            {
                c = _d + dx;
                d = _d;
            }
            else
            {
                c = 0;
                d = dx;
            }
            TeeAlkioille(LisaaTallennus, a, b, c, d);
            TallennaAlueet(tallenna);
        }

        private void SiirraAlueet(int dx, int dy)
        {
            int a, b, c, d;
            if (dy < 0)
            {
                a = 0;
                b = _d + dy;
            }
            else
            {
                a = dy;
                b = _d;
            }
            if (dx < 0)
            {
                c = 0;
                d = _d + dx;
            }
            else
            {
                c = dx;
                d = _d;
            }
            TeeAlkioille((i, j) => _a[i - dy, j - dx] = _a[i, j], a, b, c, d);
        }

        private void LataaAlueet(int dx, int dy)
        {
            void LisaaLataus(int i, int j)
            {
                int x = _ox - _r + i;
                int y = _oy - _r + j;
                // _a[i, j] = 
                // TODO
            }
            int a, b, c, d;
            if (dy < 0)
            {
                a = 0;
                b = -dy;
            }
            else
            {
                a = _d - dy;
                b = _d;
            }
            TeeAlkioille(LisaaLataus, a, b, 0, _d);
            if (dy < 0)
            {
                a = -dy;
                b = _d;
            }
            else
            {
                a = 0;
                b = _d - dy;
            }
            if (dx < 0)
            {
                c = 0;
                d = -dx;
            }
            else
            {
                c = _d - dx;
                d = _d;
            }
            TeeAlkioille(LisaaLataus, a, b, c, d);
        }

        
        private void TallennaAlueet(List<Alue> alueet) { }
        private void LataaAlueet(Alue?[,] alueet) { }
        //====================
        internal void Liiku(Suunta suunta, int x, int y) { }

        private Alue LuoAlue(int x, int y)
        {
            // TODO: Luo ja tallenna
            return new Metsa(x, y, 0);
        }
        private Alue? HaeTietokannasta(int x, int y)
        {
            throw new NotImplementedException();
        }
    }
}
