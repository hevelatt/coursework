using System;

namespace Tehtava7_1
{
    class Program
    {
        static void Main(string[] args)
        {
            // Vaihe 1. Toteuta ohjelma joka kirjoittaa numerot 1 - 10 jokaisen omalle rivilleen.Käytä toteutuksessa for silmukkaa.
            Console.WriteLine("~~~ Vaihe 1 ~~~");
            for (int i = 1; i <= 10; i++)
                Console.WriteLine(i);
            // Vaihe 2. Muuta ohjelmaa että se kysyy aloituslukua käyttäjältä ja tulostaa sen ja seuraavat 9 lukua omille riveilleen.
            Console.WriteLine("~~~ Vaihe 2 ~~~");
            Console.Write("Syötä aloitusluku:\n> ");
            int aloitus = int.Parse(Console.ReadLine());
            for (int i = aloitus; i < aloitus + 10; i++)
                Console.WriteLine(i);
            // Vaihe 3. Muuta ohjelmaa että se tulostaa vain joka toisen luvun.
            Console.WriteLine("~~~ Vaihe 3 ~~~");
            for (int i = aloitus; i < aloitus + 10; i+=2)
                Console.WriteLine(i);
            // Vaihe 4. Muuta tehtävää että se tulostaa  annetusta luvusta yhden pienempiä lukuja nollaan asti.
            Console.WriteLine("~~~ Vaihe 4 ~~~");
            for (int i = aloitus; i >= 0; i--)
                Console.WriteLine(i);
        }
    }
}
