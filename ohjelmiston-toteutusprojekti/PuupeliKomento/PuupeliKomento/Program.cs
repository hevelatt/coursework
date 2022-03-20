using Puupeli.model;

namespace Puupeli
{
    internal class Program
    {
        private static void Main()
        {
            TietokantaHallinta tietokantaHallinta = new(
                "Data Source=(localdb)\\MSSQLLocalDB;Initial Catalog=puupeli;" +
                "Integrated Security=True;" +
                "Connect Timeout=30;" +
                "Encrypt=False;" +
                "TrustServerCertificate=False;" +
                "ApplicationIntent=ReadWrite;" +
                "MultiSubnetFailover=False");
        }
    }
}