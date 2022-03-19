using Puupeli.model;

namespace Puupeli
{
    internal class Program
    {
        private static TietokantaHallinta tietokantaHallinta = new(
            "Data Source=(localdb)\\MSSQLLocalDB;Initial Catalog=puupeli;" +
            "Integrated Security=True;" +
            "Connect Timeout=30;" +
            "Encrypt=False;" +
            "TrustServerCertificate=False;" +
            "ApplicationIntent=ReadWrite;" +
            "MultiSubnetFailover=False");
        // TODO: ei staattisia, vaan pelilogiikka luokka
        // Selvitä tarvitseeko käyttiksen olla yhteydessä modeliin
        // Lataa alueet pelaajan ympäriltä! (esim 10 joka suuntaan)
        // eikä koko tietokantaa muistiin!!!
        private static void Main()
        {
            Console.WriteLine("Syötä nimi:\n>");
            string? syote = Console.ReadLine();
            if (string.IsNullOrEmpty(syote)) return;
            Pelaaja pelaaja = tietokantaHallinta.HaePelaaja(syote);
        }
    }
}