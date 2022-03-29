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


        internal AlueKasittelija(int x, int y)
        {
            _x = x;
            _y = y;
            //_ladatutAlueet = new Alue?[9, 9];
        }

        internal Alue? HaeAlue(int x, int y)
        {
            // _x, _y = _ladatutAlueet[4,4]
            _dx = _x - x;
            _dy = _y - y;
            return _a[_r + _dy, _r + _dx];
        }

        private int _x; // Kuvassa X.x
        private int _y; // Kuvassa X.y
        private int _dx; // delta X on X.x-#.x (siis muutos)
        private int _dy; // delta Y
        private const int _r = 4;
        private const int _d = 2 * _r + 1;
        private Alue?[,] _a = new Alue?[_d, _d];
        //  dX>0, dY<0     dX>0, dY=0       dX>0, dY>0       dX=0, dY>0  dX<0, dY>0     dX<0, dY=0       dX<0, dY<0    dX=0, dY<0
        // .........................................................................................................................
        // ....CCCCCCCCC..................................................................................................CCCCCCCCC.
        // ....CCCCCCCCC.................................................................................CCCCCCCCC........CCCCCCCCC.
        // ....CCCCCCCCC.................................................................................CCCCCBBBBAAAAA...CCCCCCCCC.
        // ....CCCCCCCCC.................................................................................CCCCCBBBBAAAAA...CCCCCCCCC.
        // ....CCCCXCCCC.................................................................................CCCCCBBBBAAAAA...CCCCXCCCC.
        // .AAABBBBBBCCC...AAAAABBBBCCCCC...AAAAAAAAA........AAAAAAAAA......AAAAAAAAA...CCCCCBBBBAAAAA...CCCCXBBBBAAAAA...BBBBBBBBB.
        // .AAABBBBBBCCC...AAAAABBBBCCCCC...AAAAAAAAA........AAAAAAAAA......AAAAAAAAA...CCCCCBBBBAAAAA...CCCCCBBBB#AAAA...BBBBBBBBB.
        // .AAABBBBBBCCC...AAAAABBBBCCCCC...AAAAABBBBCCCCC...AAAAAAAAA......AAAAAAAAA...CCCCCBBBBAAAAA...CCCCCBBBBAAAAA...BBBBBBBBB.
        // .AAABBBBBBCCC...AAAAABBBBCCCCC...AAAAABBBBCCCCC...AAAAAAAAA......AAAAAAAAA...CCCCCBBBBAAAAA...CCCCCBBBBAAAAA...BBBBBBBBB.
        // .AAAA#AAAA......AAAA#BBBBXCCCC...AAAA#BBBBCCCCC...AAAA#AAAA......AAAA#AAAA...CCCCXBBBB#AAAA...CCCCCBBBBAAAAA...AAAA#AAAA.
        // .AAAAAAAAA......AAAAABBBBCCCCC...AAAAABBBBCCCCC...BBBBBBBBB...CCCBBBBBBAAA...CCCCCBBBBAAAAA........AAAAAAAAA...AAAAAAAAA.
        // .AAAAAAAAA......AAAAABBBBCCCCC...AAAAABBBBXCCCC...BBBBBBBBB...CCCBBBBBBAAA...CCCCCBBBBAAAAA....................AAAAAAAAA.
        // .AAAAAAAAA......AAAAABBBBCCCCC...AAAAABBBBCCCCC...BBBBBBBBB...CCCBBBBBBAAA...CCCCCBBBBAAAAA....................AAAAAAAAA.
        // .AAAAAAAAA......AAAAABBBBCCCCC...AAAAABBBBCCCCC...BBBBBBBBB...CCCBBBBBBAAA...CCCCCBBBBAAAAA....................AAAAAAAAA.
        // ......................................CCCCCCCCC...CCCCXCCCC...CCCCXCCCC..................................................
        // ......................................CCCCCCCCC...CCCCCCCCC...CCCCCCCCC..................................................
        // ..................................................CCCCCCCCC...CCCCCCCCC..................................................
        // ..................................................CCCCCCCCC...CCCCCCCCC..................................................
        // ..................................................CCCCCCCCC...CCCCCCCCC..................................................
        // .........................................................................................................................
        // 1. Tallenna A (ja #)
        private void Tallenna()
        {
            int a, b;
            if (_dy < 0)
            {
                a = _d + _dy;
                b = _d;
            }
            else
            {
                a = 0;
                b = _dy;
            }
            for (int i = a; i < b; ++i)
            {
                for (int j = 0; j < _d; ++j)
                {
                    //
                }
            }

            int c, d;
            if (_dx < 0)
            {
                // huom. vastakkaiset
                a = 0;
                b = _d + _dy;
                // huom. sama kuin dy
                c = _d + _dx;
                d = _d;
            }
            else
            {
                // huom. vastakkaiset
                a = _dy;
                b = _d;
                // huom. sama kuin dy
                c = 0;
                d = _dx;
            }
            for (int i = a; i < b; ++i)
            {
                for (int j = c; j < d; ++j)
                {
                    //
                }
            }
        }

        // ================ B ===============
        private void Siirra()
        {
            int a, b, c, d;
            if (_dy < 0)
            {
                a = 0;
                b = _d + _dy;
            }
            else
            {
                a = _dy;
                b = _d;
            }
            if (_dx < 0)
            {
                c = 0;
                d = _d + _dx;
            }
            else
            {
                c = _dx;
                d = _d;
            }
            for (int i = a; i < b; ++i)
            {
                for (int j = c; j < d; ++j)
                {
                    _a[i - _dy, j - _dx] = _a[i, j];
                }
            }
        }

        // ======================= C ====================
        private void Lataa()
        {
            int a, b;
            if (_dy < 0)
            {
                a = 0;
                b = -_dy;
            }
            else
            {
                a = _d - _dy;
                b = _d;
            }
            for (int i = a; i < b; ++i)
            {
                for (int j = 0; j < _d; ++j)
                {
                    //
                }
            }

            int c, d;
            if (_dx < 0)
            {
                // huom. vastakkaiset
                a = -_dy;
                b = _d;
                // huom. sama kuin dy
                c = 0;
                d = -_dx;
            }
            else
            {
                // huom. vastakkaiset
                a = 0;
                b = _d - _dy;
                // huom. sama kuin dy
                c = _d - _dx;
                d = _d;
            }
            for (int i = a; i < b; ++i)
            {
                for (int j = c; j < d; ++j)
                {
                    //
                }
            }
        }
    }
}
