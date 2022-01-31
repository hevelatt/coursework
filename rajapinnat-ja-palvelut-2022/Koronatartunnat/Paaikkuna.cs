using System;
using System.Collections.Generic;
using System.Drawing;
using System.Net.Http;
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
        int tartuntojenLkm;

        #region Tapahtumakäsittelijät

        private void lopetaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void päivitäVerkkoyhteysToolStripMenuItem_Click(object sender, EventArgs e)
        {
            AlustaVirhe();

            if (paivat == null) AlustaPaivat();
            if (string.IsNullOrEmpty(jsonTartunnat)) PaivitaTartuntatapaustiedot();
            if (comboBoxAlue.Items.Count == 0) AlustaAluevalikko();
        }

        private void päivämäärävalintaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (päivämäärävalintaToolStripMenuItem.Checked)
            {
                päivämäärävalintaToolStripMenuItem.Checked = false;
                dateTimePickerTartuntapaiva.Enabled = false;
            }
            else
            {
                päivämäärävalintaToolStripMenuItem.Checked = true;
                dateTimePickerTartuntapaiva.Enabled = true;
            }
        }

        private void comboBoxAlue_SelectedIndexChanged(object sender, EventArgs e)
        {
            AlustaVirhe();
            AlustaTartuntaluku();
        }

        private void dateTimePickerTartuntapaiva_ValueChanged(object sender, EventArgs e)
        {
            AlustaVirhe();
            PaivitaTartuntatapaustiedot();
            AlustaTartuntaluku();
        }

        private void buttonArvaus_Click(object sender, EventArgs e)
        {
            AlustaVirhe();
            if (int.TryParse(textBoxArvaus.Text, out int arvaus))
            {
                if (arvaus < tartuntojenLkm)
                    labelVastaus.Text = "Tartuntojen lukumäärä suurempi kuin arvio";
                if (arvaus > tartuntojenLkm)
                    labelVastaus.Text = "Tartuntojen lukumäärä pienempi kuin arvio";
                if (arvaus == tartuntojenLkm)
                    labelVastaus.Text = "Tartuntojen lukumäärä sama kuin arvio";
            }
            else
            {
                NaytaVirhe("Arvio ei kokonaisluku", false);
            }
        }

        private void buttonNayta_Click(object sender, EventArgs e)
        {
            NaytaTartuntaluku();
        }

        #endregion

        public Paaikkuna()
        {
            InitializeComponent();
            // Haetaan päivät ja niiden solmutunnisteet ja sijoitetaan sanakirjaan paivat.
            AlustaPaivat();
            // Päivitetään tartuntatapaukset päivävalinnan perusteella (oletuksena tänään).
            PaivitaTartuntatapaustiedot();
            // Alustaa myös virheen ja tartuntaluvun.
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
            // Tarkasteltava päivä määräytyy päivävalinnan mukaan.
            string tartuntapaiva = dateTimePickerTartuntapaiva.Value.ToString("yyyy-MM-dd");
            // Nollataan osoitteen loppuosa.
            tartunnatRajoiteLoppu = "";
            foreach (string sid in paivat.Keys)
            {
                // Jos avainta vastaava päivä on tartuntapäivä,
                // niin asetetaan osoitteen loppuosaksi avain (solmutunniste).
                if (paivat[sid].Equals(tartuntapaiva))
                    tartunnatRajoiteLoppu = sid;
            }
            // Tilanne, jossa ei löytynyt päivää vastaavaa solmutunnistetta.
            if (string.IsNullOrEmpty(tartunnatRajoiteLoppu))
            {
                NaytaVirhe("Virheellinen päivämäärä: ei pandemian ajalta", true);
                return;
            }

            jsonTartunnat = HaeVerkosta(client,
                apiOsoite + tartunnatRajoiteAlku + tartunnatRajoiteLoppu);
            try
            {
                tartuntatapaukset = JsonSerializer.Deserialize<Dictionary<string, string>>(
                    ErotaOlio(@"""measure"" : {", @"""value"":", jsonTartunnat));
            }
            catch (JsonException)
            {
                NaytaVirhe("Virheellinen päivämäärä: ei tietoja", true);
            }
        }

        private void AlustaAluevalikko()
        {
            if (string.IsNullOrEmpty(jsonTartunnat)) return;
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
        
        private void NaytaTartuntaluku()
        {
            labelTartunnat.Text = tartuntojenLkm.ToString();
        }

        private void AlustaTartuntaluku()
        {
            if (tartuntatapaukset == null)
            {
                NaytaVirhe("Virheellinen päivämäärä: ei tietoja", true);
            }
            else
            {
                tartuntojenLkm = int.Parse(tartuntatapaukset[comboBoxAlue.SelectedIndex.ToString()]);
            }
            labelTartunnat.Text = "";
            labelVastaus.Text = "";
        }

        private void NaytaVirhe(string virheviesti, bool estaOhjaimet)
        {
            labelVirhe.Size = new Size(labelVirhe.Size.Width, 21);
            labelVirhe.Text = virheviesti;
            labelVirhe.BackColor = Color.Red;
            if (estaOhjaimet) VaihdaOhjaimienTila(false);
        }

        private void AlustaVirhe()
        {
            labelVirhe.Size = new Size(labelVirhe.Size.Width, 0);
            labelVirhe.Text = "";
            labelVirhe.BackColor = Color.Transparent;
            VaihdaOhjaimienTila(true);
        }

        private void VaihdaOhjaimienTila(bool aktiivinen)
        {
            textBoxArvaus.Enabled = aktiivinen;
            buttonArvaus.Enabled = aktiivinen;
            buttonNayta.Enabled = aktiivinen;
        }

        private string HaeVerkosta(HttpClient c, string osoite)
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
                        NaytaVirhe("Osoitetta ei pystytty tulkitsemaan. Tarkista verkkoyhteys.", true);
                        return true;
                    }
                    return false; // Let anything else stop the application.
                });

                return "";
            }
        }

        private string ErotaOlio(string jsonYlaOlio, string jsonOlioAvain, string jsonData)
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
    }
}
