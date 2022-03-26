using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Puupeli.model;

namespace Puupeli.controller
{
    internal class PelaajaValintaKasittelija
    {
        private TietokantaHallinta _tietokantaHallinta = new(
                "Data Source=(localdb)\\MSSQLLocalDB;Initial Catalog=puupeli;" +
                "Integrated Security=True;" +
                "Connect Timeout=30;" +
                "Encrypt=False;" +
                "TrustServerCertificate=False;" +
                "ApplicationIntent=ReadWrite;" +
                "MultiSubnetFailover=False");
        // Tarvitaanko lista pelaajista vai pelkistä nimistä?
        internal List<string> PelaajaNimet { get => _tietokantaHallinta.HaePelaajaNimet(); }
        internal List<Pelaaja> Pelaajat { get => _tietokantaHallinta.HaePelaajat(); }
    }
}
