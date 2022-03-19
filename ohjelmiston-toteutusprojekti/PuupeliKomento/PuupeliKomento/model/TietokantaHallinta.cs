using System.Data.SqlClient;

namespace Puupeli.model
{
    internal class TietokantaHallinta
    {
        private readonly string yhteysmerkkijono;

        internal TietokantaHallinta(string yhteysmerkkijono)
        {
            this.yhteysmerkkijono = yhteysmerkkijono;
        }

        internal Pelaaja LuoPelaaja(string nimi)
        {
            return KaytaYhteytta<Pelaaja>(HaeTaiTallennaPelaaja, nimi);
        }

        /// <summary>
        /// Suorittaa kyselyitä yhteyden avulla käyttäen hyväksi kyselyparametria.
        /// </summary>
        /// <typeparam name="T">Kyselyiden palautustyyppi.</typeparam>
        /// <param name="yhteys">Avattu SQL-yhteys, jolla kyselyt tehdään.</param>
        /// <param name="kyselyparametri">Kyselyissä käytettävä tieto.</param>
        /// <returns>Kyselyiden palautusarvo.</returns>
        private delegate T TeeKysely<T>(SqlConnection yhteys, string kyselyparametri);

        /// <summary>
        /// Luo yhteyden ja suorittaa kyselyitä.
        /// </summary>
        /// <typeparam name="T">Kyselyiden palautustyyppi.</typeparam>
        /// <param name="kyselyt">Suoritettavat kyselyt.</param>
        /// <param name="kyselyparametri">Kyselyissä käytettävä tieto.</param>
        /// <returns>Kyselyiden palautusarvo.</returns>
        private T KaytaYhteytta<T>(TeeKysely<T> kyselyt, string kyselyparametri)
        {
            using var yhteys = new SqlConnection(yhteysmerkkijono);
            yhteys.Open();
            return kyselyt(yhteys, kyselyparametri);
        }

        /// <summary>
        /// Suorittaa sijoituskyselyn.
        /// </summary>
        /// <param name="yhteys">Avattu SQL-yhteys, jolla sijoituskysely tehdään.</param>
        /// <param name="sijoituskysely">Suoritettava sijoituskysely.</param>
        /// <returns>Sijoitettujen rivien lukumäärä.</returns>
        /// <exception cref="InvalidOperationException">Jos yhteys on suljettu.</exception>
        private static int SijoitaTauluun(SqlConnection yhteys, string sijoituskysely)
        {
            if (yhteys.State == System.Data.ConnectionState.Closed)
            {
                throw new InvalidOperationException("Yhteys ei voi olla suljettu");
            }
            var sijoituskomento = new SqlCommand(sijoituskysely, yhteys);
            return sijoituskomento.ExecuteNonQuery();
        }

        /// <summary>
        /// Hakee SQL-lukijan, joka lukee hakuehdon täyttävät rivit taulusta.
        /// </summary>
        /// <param name="yhteys">Avattu SQL-yhteys, jolla SQL-lukija haetaan.</param>
        /// <param name="taulu">Taulu, josta haetaan.</param>
        /// <param name="hakuehto">Ehto, jonka täyttävät rivit haetaan.</param>
        /// <param name="lueYksi">Arvolla true luetaan vain yksi rivi.</param>
        /// <returns>SQL-lukija, joka lukee hakuehdon täyttävät rivit taulusta.</returns>
        /// <exception cref="InvalidOperationException">Jos yhteys on suljettu.</exception>
        private static SqlDataReader HaeSqlLukija(SqlConnection yhteys, string taulu, string? hakuehto, bool lueYksi = false)
        {
            if (yhteys.State == System.Data.ConnectionState.Closed)
            {
                throw new InvalidOperationException("Yhteys ei voi olla suljettu");
            }
            string hakukysely = $"SELECT * FROM {taulu}" +
                // Hae kaikki, jos hakuehto on tyhjä.
                (string.IsNullOrEmpty(hakuehto) ? "" : $" WHERE {hakuehto}");
            var hakukomento = new SqlCommand(hakukysely, yhteys);
            return hakukomento.ExecuteReader(
                lueYksi ? System.Data.CommandBehavior.SingleRow : System.Data.CommandBehavior.Default);
        }

        /// <summary>
        /// Palauttaa lukijan lukeman pelaajan.
        /// </summary>
        /// <param name="lukija">SQL-lukija, joka lukee pelaajan.</param>
        /// <returns>Pelaaja, jonka lukija luki.</returns>
        private static Pelaaja LuePelaaja(SqlDataReader lukija)
        {
            return new Pelaaja(
                lukija.GetString(1), // Nimi
                lukija.GetInt32(2),  // AlueX
                lukija.GetInt32(3),  // AlueY
                lukija.GetInt32(4)); // Polttopuita
        }

        /// <summary>
        /// Palauttaa hakuehdon täyttävän pelaajan.
        /// </summary>
        /// <param name="yhteys">Avattu SQL-yhteys, jolla pelaajaa haetaan.</param>
        /// <param name="hakuehto">SQL-muotoinen hakuehto (WHERE jälkeen), jolla pelaajaa haetaan.</param>
        /// <returns>Pelaaja, joka täyttää hakuehdon, null jos hakuehdon täyttävää pelaajaa ei ole.</returns>
        private static Pelaaja? HaePelaaja(SqlConnection yhteys, string hakuehto)
        {
            var lukija = HaeSqlLukija(yhteys, "Pelaaja", hakuehto, true);
            if (lukija.Read())
            {
                return LuePelaaja(lukija);
            }
            return null;
        }

        /// <summary>
        /// Tallentaa pelaajan tietokantaan.
        /// </summary>
        /// <param name="yhteys">Avattu SQL-yhteys, jonka avulla pelaaja tallennetaan.</param>
        /// <param name="pelaaja"></param>
        /// <returns>True, jos tallennus onnistui.</returns>
        private static bool TallennaPelaaja(SqlConnection yhteys, Pelaaja pelaaja)
        {
            string sijoituskysely = "INSERT INTO Pelaaja (PelaajaNimi, AlueX, AlueY, Polttopuita) " +
                $"VALUES ('{pelaaja.Nimi}', {pelaaja.AlueX}, {pelaaja.AlueY}, {pelaaja.Polttopuita})";
            if (SijoitaTauluun(yhteys, sijoituskysely) > 0)
            {
                return true;
            }
            return false;
        }

        /// <summary>
        /// Hakee pelaajan nimen perusteella. Tallentaa uuden pelaajan jos nimeä ei löydy.
        /// </summary>
        /// <param name="yhteys">Avattu SQL-yhteys, jota käytetään.</param>
        /// <param name="nimi">Haettavan pelaajan nimi.</param>
        /// <returns>Haetun niminen pelaaja.</returns>
        private static Pelaaja HaeTaiTallennaPelaaja(SqlConnection yhteys, string nimi)
        {
            Pelaaja? pelaaja = HaePelaaja(yhteys, $"Pelaajanimi = '{nimi}'");
            if (pelaaja == null)
            {
                pelaaja = new Pelaaja(nimi, 0, 0, 0);
                TallennaPelaaja(yhteys, pelaaja);
            }
            return pelaaja;
        }
    }
}
