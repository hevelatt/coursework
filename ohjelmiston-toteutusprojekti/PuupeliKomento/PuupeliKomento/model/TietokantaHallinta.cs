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

        internal bool TallennaPelaaja(Pelaaja pelaaja)
        {
            return KaytaYhteytta(SuoritaKysely,
                "INSERT INTO Pelaaja (PelaajaNimi, AlueX, AlueY, Polttopuita) VALUES " +
                $"('{pelaaja.Nimi}', {pelaaja.AlueX}, {pelaaja.AlueY}, {pelaaja.Polttopuita})"
                ) > 0;
        }
        internal bool PaivitaPelaaja(Pelaaja pelaaja)
        {
            return KaytaYhteytta(SuoritaKysely,
                $"UPDATE Pelaaja SET AlueX = {pelaaja.AlueX}, AlueY = {pelaaja.AlueY}, " +
                $"Polttopuita = {pelaaja.Polttopuita} WHERE PelaajaNimi = '{pelaaja.Nimi}'"
                ) > 0;
        }
        internal Pelaaja HaePelaaja(string nimi)
        {
            var pelaaja = KaytaYhteytta(HaePelaajaKysely, $"Pelaajanimi = '{nimi}'");
            if (pelaaja == null)
            {
                return new Pelaaja(nimi, 0, 0, 0);
            }
            return pelaaja;
        }
        internal List<Pelaaja> HaePelaajat(string hakuehto = "")
        {
            return KaytaYhteytta(HaePelaajatKysely, hakuehto);
        }
        // TODO: nullable kyselyparametri
        // TODO: Hae pelaaja nullable?

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
        /// Suorittaa kyselyn.
        /// </summary>
        /// <param name="yhteys">Käytettävä yhteys.</param>
        /// <param name="kysely">Suoritettava kysely.</param>
        /// <returns>Vaikuttuneiden rivien lukumäärä.</returns>
        /// <exception cref="InvalidOperationException">Jos yhteys on suljettu.</exception>
        private static int SuoritaKysely(SqlConnection yhteys, string kysely)
        {
            if (yhteys.State == System.Data.ConnectionState.Closed)
            {
                throw new InvalidOperationException("Yhteys ei voi olla suljettu");
            }
            var sijoituskomento = new SqlCommand(kysely, yhteys);
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
        private static Pelaaja? HaePelaajaKysely(SqlConnection yhteys, string hakuehto)
        {
            var lukija = HaeSqlLukija(yhteys, "Pelaaja", hakuehto, true);
            if (lukija.Read())
            {
                return LuePelaaja(lukija);
            }
            return null;
        }

        private static List<Pelaaja> HaePelaajatKysely(SqlConnection yhteys, string hakuehto)
        {
            var pelaajat = new List<Pelaaja>();
            var lukija = HaeSqlLukija(yhteys, "Pelaaja", hakuehto);
            while (lukija.Read())
            {
                pelaajat.Add(LuePelaaja(lukija));
            }
            return pelaajat;
        }


        //    /// <summary>
        //    /// Hakee pelaajan nimen perusteella. Tallentaa uuden pelaajan jos nimeä ei löydy.
        //    /// </summary>
        //    /// <param name="yhteys">Avattu SQL-yhteys, jota käytetään.</param>
        //    /// <param name="nimi">Haettavan pelaajan nimi.</param>
        //    /// <returns>Haetun niminen pelaaja.</returns>
        //    private static Pelaaja HaeTaiTallennaPelaaja(SqlConnection yhteys, string nimi)
        //    {
        //        Pelaaja? pelaaja = HaePelaaja(yhteys, $"Pelaajanimi = '{nimi}'");
        //        if (pelaaja == null)
        //        {
        //            pelaaja = new Pelaaja(nimi, 0, 0, 0);
        //            TallennaPelaaja(yhteys, pelaaja);
        //        }
        //        return pelaaja;
        //    }
        //}
    }
