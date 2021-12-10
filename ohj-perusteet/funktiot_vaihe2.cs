using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace funktiot
{
    class Program
    {
        private static void Tervehdys(string kayttaja)
        {
            Console.WriteLine($"Tervetuloa ohjelmaan {kayttaja}!");
        }

        static void Main(string[] args)
        {
            Console.WriteLine("Anna nimesi:");
            string kayttaja = Console.ReadLine();
            Tervehdys(kayttaja);
        }
    }
}
