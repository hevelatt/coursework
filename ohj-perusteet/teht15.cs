using System;

namespace Tehtava15
{
    class Program
    {
        static void Main(string[] args)
        {
            // käyttäen kokonaislukuja, niin päästään testaamaan nollalla jakamista
            int jaettava, jakaja, osamaara, jakojaannos;
            Console.WriteLine("~~~ Jakolasku ~~~");
            try
            {
                Console.Write("Syötä jaettava luku:\n> ");
                jaettava = int.Parse(Console.ReadLine());
                Console.Write("Syötä jakaja:\n> ");
                jakaja = int.Parse(Console.ReadLine());
                osamaara = jaettava / jakaja;
                jakojaannos = jaettava % jakaja;
                Console.WriteLine($"Osamäärä on {osamaara}" +
                    (jakojaannos == 0 ? "." : $" {jakojaannos}/{jakaja}."));
            }
            // Virhe nollalla jakaminen
            catch (DivideByZeroException)
            {
                Console.WriteLine("Nollalla ei saa jakaa!");
            }
            // Mahdolliset int.Parse(String) virheet
            catch (FormatException)
            {
                Console.WriteLine("Väärä tyyppi! Pitäisi olla kokonaisluku.");
            }
            catch (OverflowException)
            {
                Console.WriteLine("Luku liian suuri!");
            }
            catch (ArgumentNullException)
            {
                Console.WriteLine("Ei syötettä!");
            }
            // Mahdolliset Console.ReadLine() virheet
            catch (ArgumentOutOfRangeException)
            {
                Console.WriteLine("Liian suuri syöte!");
            }
            catch (OutOfMemoryException)
            {
                Console.WriteLine("Muisti loppui!");
            }
            // Kaikki loput virheet, huom. virheilmoitus välitetään parametrina
            catch (Exception e)
            {
                Console.WriteLine($"Tapahtui virhe! {e}");
            }
            finally
            {
                Console.WriteLine("Kiitos ohjelman käytöstä!");
            }
        }
    }
}
