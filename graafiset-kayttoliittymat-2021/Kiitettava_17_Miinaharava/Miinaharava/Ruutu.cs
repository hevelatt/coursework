using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace Miinaharava
{
    class Ruutu
    {
        public static readonly int Koko = Properties.Resources.ruutu.Width;

        private static readonly Bitmap kuvaRuutu = new Bitmap(Properties.Resources.ruutu);
        private static readonly Bitmap kuvaRuutuPaljastettu = new Bitmap(Properties.Resources.paljastettu);
        private static readonly Bitmap kuvaRuutuPaljastettu1 = new Bitmap(Properties.Resources.paljastettu_1);
        private static readonly Bitmap kuvaRuutuPaljastettu2 = new Bitmap(Properties.Resources.paljastettu_2);
        private static readonly Bitmap kuvaRuutuPaljastettu3 = new Bitmap(Properties.Resources.paljastettu_3);
        private static readonly Bitmap kuvaRuutuPaljastettu4 = new Bitmap(Properties.Resources.paljastettu_4);
        private static readonly Bitmap kuvaRuutuPaljastettu5 = new Bitmap(Properties.Resources.paljastettu_5);
        private static readonly Bitmap kuvaRuutuPaljastettu6 = new Bitmap(Properties.Resources.paljastettu_6);
        private static readonly Bitmap kuvaRuutuPaljastettu7 = new Bitmap(Properties.Resources.paljastettu_7);
        private static readonly Bitmap kuvaRuutuPaljastettu8 = new Bitmap(Properties.Resources.paljastettu_8);
        private static readonly Bitmap kuvaMerkitty = new Bitmap(Properties.Resources.merkitty);
        private static readonly Bitmap kuvaMiina = new Bitmap(Properties.Resources.miina);

        public bool OnMiina { get; set; }
        public int X { get; }
        public int Y { get; }
        public Bitmap Kuva { get; private set; }
        public int MiinojaYmparilla { get; set; }

        private bool paljastettu;
        private bool merkitty;

        public Ruutu(int x, int y)
        {
            X = x;
            Y = y;
            Kuva = kuvaRuutu;
        }

        /// <param name = "merkittyja" > Muutetaan merkittyjen miinojen lukumäärän muutoksella.</param>
        /// <returns>True, jos paljastettiin ruutu.</returns>
        public bool Paljasta(ref int merkittyja)
        {
            if (paljastettu)
            {
                return false;
            }
            if (merkitty)
            {
                merkittyja--;
            }
            if (OnMiina)
            {
                Kuva = kuvaMiina;
            }
            else
            {
                switch (MiinojaYmparilla)
                {
                    case 1:
                        Kuva = kuvaRuutuPaljastettu1;
                        break;
                    case 2:
                        Kuva = kuvaRuutuPaljastettu2;
                        break;
                    case 3:
                        Kuva = kuvaRuutuPaljastettu3;
                        break;
                    case 4:
                        Kuva = kuvaRuutuPaljastettu4;
                        break;
                    case 5:
                        Kuva = kuvaRuutuPaljastettu5;
                        break;
                    case 6:
                        Kuva = kuvaRuutuPaljastettu6;
                        break;
                    case 7:
                        Kuva = kuvaRuutuPaljastettu7;
                        break;
                    case 8:
                        Kuva = kuvaRuutuPaljastettu8;
                        break;
                    default:
                        Kuva = kuvaRuutuPaljastettu;
                        break;
                }
            }
            return paljastettu = true;
        }

        /// <param name="merkittyja">Muutetaan merkittyjen miinojen lukumäärän muutoksella.</param>
        /// <returns>True, jos merkittiin ruutu tai poistettiin merkintä.</returns>
        public bool Merkitse(ref int merkittyja)
        {
            if (paljastettu)
            {
                return false;
            }
            if (merkitty)
            {
                Kuva = kuvaRuutu;
                merkitty = false;
                merkittyja--;
            }
            else
            {
                Kuva = kuvaMerkitty;
                merkitty = true;
                merkittyja++;
            }
            return true;
        }
    }
}
