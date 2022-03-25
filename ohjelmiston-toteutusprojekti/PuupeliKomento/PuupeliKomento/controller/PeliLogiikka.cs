/// <summary>
/// Puupelin käsittelijä.
/// </summary>
namespace Puupeli.controller
{
    using Puupeli.model;
    internal class PeliLogiikka
    {
        internal void run()
        {
            var p = new Pelaaja("",0,0,0);
            //p.PuutaKaadettaessa += KaadaPuu;
            //p.PuutaKaadettaessa = KaadaPuu;
            //p.KaadaPuu();
        }

        internal int KaadaPuu(int x, int y)
        {
            Console.WriteLine("abc");
            return 0;
        }

        internal void EventHandlerPuuKaatuu(object? sender, EventArgs e)
        {
            //var p = (PeliLogiikka)sender;
            Console.WriteLine("Pelaaja kaataa puun");
        }
    }
}
