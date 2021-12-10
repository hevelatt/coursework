using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace funktiot
{
    class Program
    {
        private static int Tervehdys(string kayttaja)
        {
            int ika;
            Console.WriteLine($"Tervetuloa ohjelmaan {kayttaja}!");
            do
            {
                Console.WriteLine("Anna ikäsi:");
            } while (!int.TryParse(Console.ReadLine(), out ika));
            return ika;
        }

        static void Main(string[] args)
        {
            int ika;
            Console.WriteLine("Anna nimesi:");
            string kayttaja = Console.ReadLine();
            ika = Tervehdys(kayttaja);
        }
    }
}
