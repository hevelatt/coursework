using System;

namespace Tehtava20
{
    class Program
    {
        static void Main(string[] args)
        {
            // Tee ohjelma, joka pyytää käyttäjää syöttämään merkin.
            // Mikäli käyttäjä syöttää välilyönnin ohjelma ilmoittaa käyttäjän antaneen tyhjän merkin.  
            // Jos käyttäjä syöttää numeron ohjelma ilmoittaa käyttäjän syöttäneen numeron ja tulostaa mainitun numeron.
            // Jos käyttäjä syöttää ison kirjaimen, ilmoitetaan merkin olevan iso kirjain.
            // Jos käyttäjä syöttää pienen kirjaimen, ilmoitetaan merkin olevan pieni kirjain.
            // Mikäli käyttäjä antaa erikoismerkin, eli jokin muu kuin numero tai kirjain, ilmoittaa ohjelma myös sen.

            Console.WriteLine("Syötä merkki:");
            Console.Write("> ");
            char syote = Console.ReadKey().KeyChar;
            Console.WriteLine();

            if (char.IsWhiteSpace(syote))
            {
                Console.WriteLine("Syötit tyhjän merkin.");
            }
            if (char.IsNumber(syote))
            {
                Console.WriteLine($"Syötit numeron {syote}.");
            }
            if (char.IsUpper(syote))
            {
                Console.WriteLine("Syötit ison kirjaimen.");
            }
            if (char.IsLower(syote))
            {
                Console.WriteLine("Syötit pienen kirjaimen.");
            }
            if (!char.IsLetterOrDigit(syote))
            {
                Console.WriteLine("Syötit erikoismerkin.");
            }
        }
    }
}
