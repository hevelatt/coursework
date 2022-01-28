using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Koronatartunnat
{
    public partial class Paaikkuna : Form
    {
        HttpClient client = new HttpClient();

        string apiOsoite =
            @"https://sampo.thl.fi/pivot/prod/fi/epirapo/covid19case/fact_epirapo_covid19case.json";
        string paivatRajoite = 
            @"?row=dateweek20200101-509093L";
        string tartunnatRajoiteAlku = 
            @"?row=hcdmunicipality2020-445222&column=measure-444833&filter=dateweek20200101-";
        string tartunnatRajoiteLoppu;

        Dictionary<string, string> paivat;
        string jsonTartunnat;
        Dictionary<string, string> tartuntatapaukset;

        public static string HaeVerkosta(HttpClient c, string osoite)
        {
            Task<string> t = c.GetStringAsync(osoite);
            try
            {
                return t.Result;
            }
            catch (AggregateException ae)
            {
                // https://docs.microsoft.com/en-us/dotnet/api/system.aggregateexception?view=net-6.0#examples
                ae.Handle((x) =>
                {
                    if (x is HttpRequestException) // This we know how to handle.
                    {
                        MessageBox.Show("Osoitetta ei pystytty tulkitsemaan. Tarkista verkkoyhteys."); // TODO: Yhtenäistä virheviestit.
                        return true;
                    }
                    return false; // Let anything else stop the application.
                });

                return "";
            }
        }

        public static string ErotaOlio(string jsonYlaOlio, string jsonOlioAvain, string jsonData)
        {
            // Olion avaimen etsintä alkaa ylä-olion sijainnista.
            int ylaOlionSijainti = jsonData.IndexOf(jsonYlaOlio);
            if (ylaOlionSijainti < 0) return "";

            int haetunOlionSijainti = jsonData.IndexOf(jsonOlioAvain, ylaOlionSijainti) + jsonOlioAvain.Length;
            if (haetunOlionSijainti < ylaOlionSijainti) return "";

            // Olion esittely loppuu oikeaan aaltosulkeeseen.
            // Otetaan myös aaltosulku mukaan (+1).
            int haetunOlionLoppuSijainti = jsonData.IndexOf('}', haetunOlionSijainti) + 1;
            if (haetunOlionLoppuSijainti < haetunOlionSijainti) return "";

            return jsonData[haetunOlionSijainti..haetunOlionLoppuSijainti];
        }

        public Paaikkuna()
        {
            InitializeComponent();
            // Haetaan päivät ja niiden solmutunnisteet ja sijoitetaan sanakirjaan paivat.
            AlustaPaivat();
            // Päivitetään tartuntatapaukset päivävalinnan perusteella (oletuksena tänään).
            PaivitaTartuntatapaustiedot();
            AlustaAluevalikko();
        }

        private void AlustaPaivat()
        {
            paivat = JsonSerializer.Deserialize<Dictionary<string, string>>(
                ErotaOlio(@"""dateweek20200101"" : {", @"""label"":", 
                HaeVerkosta(client, apiOsoite + paivatRajoite)));
        }

        private void PaivitaTartuntatapaustiedot()
        {
            string tartuntapaiva = dateTimePickerTartuntapaiva.Value.ToString("yyyy-MM-dd");
            tartunnatRajoiteLoppu = paivat.FirstOrDefault(x => x.Value == tartuntapaiva).Key;

            jsonTartunnat = HaeVerkosta(client,
                apiOsoite + tartunnatRajoiteAlku + tartunnatRajoiteLoppu);
            try
            {
                tartuntatapaukset = JsonSerializer.Deserialize<Dictionary<string, string>>(
                    ErotaOlio(@"""measure"" : {", @"""value"":", jsonTartunnat));
            }
            catch
            {
                // TODO: Virheviesti, virheellinen päivämäärävalinta.
            }
        }

        private void AlustaAluevalikko()
        {
            // Luodaan sanakirjat alueindekseistä ja -nimistä.
            // Tiedetään että niissä on samat avaimet.
            var alueIndeksit = JsonSerializer.Deserialize<Dictionary<string, int>>(
                ErotaOlio(@"""hcdmunicipality2020"" : {", @"""index"":", jsonTartunnat));
            var alueNimet = JsonSerializer.Deserialize<Dictionary<string, string>>(
                ErotaOlio(@"""hcdmunicipality2020"" : {", @"""label"":", jsonTartunnat));
            // Merkkijonotaulukko, johon varastoidaan alueiden nimet alueindeksien osoittamiin paikkoihin.
            string[] alueet = new string[alueIndeksit.Count];
            foreach (string sid in alueIndeksit.Keys)
            {
                alueet[alueIndeksit[sid]] = alueNimet[sid];
            }
            // Alustetaan aluevalikko alueiden nimillä.
            // Myöhemmin voidaan hakea tartuntamäärät indeksin avulla.
            comboBoxAlue.Items.AddRange(alueet);
            // Valitaan viimeinen indeksi (Kaikki Alueet).
            // Kutsuu comboBoxAlue_SelectedIndexChanged().
            comboBoxAlue.SelectedIndex = comboBoxAlue.Items.Count - 1;
        }

        private void PaivitaTartuntaluku()
        {
            labelTartunnat.Text = tartuntatapaukset[comboBoxAlue.SelectedIndex.ToString()];
        }

        private void dateTimePickerTartuntapaiva_ValueChanged(object sender, EventArgs e)
        {
            PaivitaTartuntatapaustiedot();
            PaivitaTartuntaluku();
        }

        private void comboBoxAlue_SelectedIndexChanged(object sender, EventArgs e)
        {
            PaivitaTartuntaluku();
        }
    }
}
