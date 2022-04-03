using System.Data.SqlClient;

/// <summary>
/// Puupelin malli.
/// </summary>
namespace Puupeli.model
{
    /// <summary>
    /// Luokka, jonka avulla hallitaan tietokantaa.
    /// </summary>
    internal class TietokantaHallinta
    {
        /// <summary>
        /// Tietokantayhteys.
        /// </summary>
        private readonly string _yhteysmerkkijono;

        /// <summary>
        /// Hallitsee tietokantaa.
        /// </summary>
        /// <param name="yhteysmerkkijono">Tietokantayhteys.</param>
        internal TietokantaHallinta(string yhteysmerkkijono)
        {
            _yhteysmerkkijono = yhteysmerkkijono;
        }
        
        #region PelaajaRajapinta

        /// <summary>
        /// Tallentaa uuden pelaajan tietokantaan.
        /// </summary>
        /// <param name="pelaaja">Pelaaja, joka tallennetaan.</param>
        /// <returns>True, jos tallennus onnistui.</returns>
        internal bool TallennaPelaaja(Pelaaja pelaaja)
        {
            return KaytaYhteytta(SuoritaKysely,
                "INSERT INTO Pelaaja (PelaajaNimi, AlueX, AlueY, Polttopuita) VALUES " +
                $"('{pelaaja.Nimi}', {pelaaja.X}, {pelaaja.Y}, {pelaaja._polttopuita})"
                ) > 0;
        }

        /// <summary>
        /// Päivittää pelaajan tiedot tietokantaan.
        /// </summary>
        /// <param name="pelaaja">Pelaaja, jonka tiedot päivitetään.</param>
        /// <returns>True, jos päivittäminen onnistui.</returns>
        internal bool PaivitaPelaaja(Pelaaja pelaaja)
        {
            return KaytaYhteytta(SuoritaKysely,
                $"UPDATE Pelaaja SET AlueX = {pelaaja.X}, AlueY = {pelaaja.Y}, " +
                $"Polttopuita = {pelaaja._polttopuita} WHERE PelaajaNimi = '{pelaaja.Nimi}'"
                ) > 0;
        }

        /// <summary>
        /// Hakee pelaajan tietokannasta.
        /// </summary>
        /// <param name="nimi">Nimi, jonka perusteella pelaaja haetaan.</param>
        /// <returns>Haettu pelaaja, null jos pelaajaa ei ole.</returns>
        internal Pelaaja? HaePelaaja(string nimi)
        {
            return KaytaYhteytta(HaePelaajaKysely, $"Pelaajanimi = '{nimi}'");
        }

        /// <summary>
        /// Hakee kaikki pelaajat tietokannasta.
        /// </summary>
        /// <returns>Lista kaikista pelaajista.</returns>
        internal List<Pelaaja> HaePelaajat()
        {
            return KaytaYhteytta(HaePelaajatKysely, "");
        }

        internal List<string> HaePelaajaNimet()
        {
            return KaytaYhteytta(HaePelaajaNimetKysely, "");
        }

        #endregion

        #region AlueRajapinta

        internal Alue? HaeAlue(int x, int y)
        {
            // TODO
            return KaytaYhteytta(HaeAlueKysely, $"SijaintiX = {x} AND SijaintiY = {y}");
        }













        // Tallenna uusi alue

        // Päivitä alueen puut jos siis metsä

        // Hae tietty alue (esim x +-10 y +-10)
        // Lista: koska sitten voi lisätä ja vähentää siitä sitä mukaa kuin on tarvis
        // Array: koska ladattava alue aina samankokoinen, ei tarvitse tarkastaa koordinaatteja löytääkseen tarvittavan alueen
        internal void HaeAlueet(Range x, Range y, Alue?[,] alueet)
        {
            // x x x x x x x x
            for (var i = x.Start.Value;  i < x.End.Value; ++i)
            {

            }
        }

        // ei nähdä ehtoja.. 
        internal void TallennaAlueet(Func<int, int, bool> ehdot)
        {
            if (ehdot(1, 2)) ;
        }

        #endregion

        #region PelaajaApufunktiot

        /// <summary>
        /// Lukee pelaajatietueen tietokannasta.
        /// </summary>
        /// <param name="lukija">Tietueen lukija.</param>
        /// <returns>Luettu pelaaja.</returns>
        private static Pelaaja LuePelaaja(SqlDataReader lukija)
        {
            return new Pelaaja(
                lukija.GetString(1), // Nimi
                lukija.GetInt32(2),  // AlueX
                lukija.GetInt32(3),  // AlueY
                lukija.GetInt32(4)); // Polttopuita
        }

        /// <summary>
        /// Hakee pelaajan tietokannasta.
        /// </summary>
        /// <param name="yhteys">Yhteys, jota käytetään.</param>
        /// <param name="hakuehto">SQL-konditionaali, jota käytetään haussa.</param>
        /// <returns>Haettu pelaaja, null jos hakuehdon täyttävää pelaajaa ei ole.</returns>
        private static Pelaaja? HaePelaajaKysely(SqlConnection yhteys, string hakuehto)
        {
            var lukija = HaeSqlLukija(yhteys, "Pelaaja", hakuehto, true);
            if (lukija.Read())
            {
                return LuePelaaja(lukija);
            }
            return null;
        }

        /// <summary>
        /// Hakee pelaajia tietokannasta.
        /// </summary>
        /// <param name="yhteys">Yhteys, jota käytetään.</param>
        /// <param name="hakuehto">SQL-konditionaali, jota käytetään haussa.</param>
        /// <returns>Lista kaikista hakuehdon täyttävistä pelaajista.</returns>
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

        private static List<string> HaePelaajaNimetKysely(SqlConnection yhteys, string hakuehto)
        {
            var pelaajaNimet = new List<string>();
            var lukija = HaeSqlLukija(yhteys, "Pelaaja", hakuehto);
            while (lukija.Read())
            {
                pelaajaNimet.Add(lukija.GetString(1));
            }
            return pelaajaNimet;
        }

        #endregion

        #region AlueApufunktiot
        //TODO
        private static Alue LueAlue(SqlDataReader lukija)
        {
            // TODO: alueen ei tarvitse tietää koordinaatteja
            return new Metsa(
                lukija.GetInt32(1),  // SijaintiX
                lukija.GetInt32(2),  // SijaintiY
                lukija.GetInt32(3)); // Puita
        }
        //TODO
        private static Alue? HaeAlueKysely(SqlConnection yhteys, string hakuehto)
        {
            var lukija = HaeSqlLukija(yhteys, "Alue", hakuehto, true);
            if (lukija.Read())
            {
                return LueAlue(lukija);
            }
            return null;
        }

        #endregion

        #region YhteysJaKomennot

        /// <summary>
        /// Tekee kyselyitä kyselyparametrin avulla.
        /// </summary>
        /// <typeparam name="T">Kyselyiden palautustyyppi.</typeparam>
        /// <param name="yhteys">Yhteys, jota käytetään.</param>
        /// <param name="kyselyparametri">Kyselyiden tarvitsema tieto.</param>
        /// <returns>Kyselyiden palautusarvo.</returns>
        private delegate T TeeKysely<T>(SqlConnection yhteys, string kyselyparametri);

        /// <summary>
        /// Luo yhteyden ja suorittaa kyselyitä.
        /// </summary>
        /// <typeparam name="T">Kyselyiden palautustyyppi.</typeparam>
        /// <param name="kyselyt">Kyselyt, jotka suoritetaan.</param>
        /// <param name="kyselyparametri">Kyselyiden tarvitsema tieto.</param>
        /// <returns>Kyselyiden palautusarvo.</returns>
        private T KaytaYhteytta<T>(TeeKysely<T> kyselyt, string kyselyparametri)
        {
            using var yhteys = new SqlConnection(_yhteysmerkkijono);
            yhteys.Open();
            return kyselyt(yhteys, kyselyparametri);
        }

        /// <summary>
        /// Suorittaa kyselyn.
        /// </summary>
        /// <param name="yhteys">Yhteys, jota käytetään.</param>
        /// <param name="kysely">Kysely, joka suoritetaan.</param>
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
        /// <param name="yhteys">Yhteys, jota käytetään.</param>
        /// <param name="taulu">Taulu, jota luetaan.</param>
        /// <param name="hakuehto">SQL-konditionaali, jonka täyttävät rivit luetaan.</param>
        /// <param name="lueYksi">True, niin luetaan vain yksi rivi.</param>
        /// <returns>SQL-lukija, joka lukee hakuehdon täyttävät rivit taulusta.</returns>
        /// <exception cref="InvalidOperationException">Jos yhteys on suljettu.</exception>
        private static SqlDataReader HaeSqlLukija(SqlConnection yhteys, string taulu, 
            string? hakuehto, bool lueYksi = false)
        {
            if (yhteys.State == System.Data.ConnectionState.Closed)
            {
                throw new InvalidOperationException("Yhteys ei voi olla suljettu");
            }
            string hakukysely = $"SELECT * FROM {taulu}" +
                // Hae kaikki, jos hakuehto on tyhjä.
                (string.IsNullOrEmpty(hakuehto) ? "" : $" WHERE {hakuehto}");
            var hakukomento = new SqlCommand(hakukysely, yhteys);
            return hakukomento.ExecuteReader(lueYksi
                ? System.Data.CommandBehavior.SingleRow : System.Data.CommandBehavior.Default);
        }

        #endregion
    }
}