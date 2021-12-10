using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Miinaharava
{
    public partial class Paaikkuna : Form
    {
        private Vaikeustaso helppo = new Vaikeustaso("helppo", 9, 9, 10);
        private Vaikeustaso keskivaikea = new Vaikeustaso("keskivaikea", 16, 16, 40);
        private Vaikeustaso vaikea = new Vaikeustaso("vaikea", 30, 16, 99);
        private Vaikeustaso vaikeustaso;
        private Ruutu[,] ruudukko;
        private TietojaIkkuna tietojaIkkuna;
        private Size ruudukonKoko;
        private bool ensimmainenPaljastus;
        private bool alustaPainettaessa;
        private int paljastettuja;
        private int merkittyja;
        private int kaytettyAika;

        private delegate void TeeRuudulle(int rivi, int sarake);

        #region Tapahtumakäsittelijät

        private void uusiPeliToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Alusta();
        }

        private void labelTila_Click(object sender, EventArgs e)
        {
            Alusta();
        }

        private void helppoToolStripMenuItem_Click(object sender, EventArgs e)
        {
            AsetaVaikeustasoValinta((ToolStripMenuItem)sender, helppo);
        }

        private void keskivaikeaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            AsetaVaikeustasoValinta((ToolStripMenuItem)sender, keskivaikea);
        }

        private void vaikeaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            AsetaVaikeustasoValinta((ToolStripMenuItem)sender, vaikea);
        }

        private void lopetaPeliToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void tietojaToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            tietojaIkkuna.ShowDialog();
        }

        private void pictureBoxPelialue_Paint(object sender, PaintEventArgs e)
        {
            foreach (Ruutu ruutu in ruudukko)
            {
                e.Graphics.DrawImage(ruutu.Kuva, ruutu.X, ruutu.Y);
            }
        }

        private void pictureBoxPelialue_MouseDown(object sender, MouseEventArgs e)
        {
            // Pelin päättymisen jälkeen alustetaan painettaessa.
            if (alustaPainettaessa)
            {
                Alusta();
                return;
            }

            int rivi = e.Location.Y / Ruutu.Koko;
            int sarake = e.Location.X / Ruutu.Koko;

            // Paljasta ruutu.
            if (e.Button == MouseButtons.Left)
            {
                if (ensimmainenPaljastus)
                {
                    // Arvonta ei kohdistu ensimmäiseen paljastukseen.
                    ruudukko[rivi, sarake].OnMiina = true;
                    ArvoMiinat(1);
                    ruudukko[rivi, sarake].OnMiina = false;
                    LaskeMiinojaYmparilla();

                    // Käynnistetään ajastin.
                    timerAjastin.Start();

                    ensimmainenPaljastus = false;
                }

                PaljastaRuutu(rivi, sarake);

                // Miinallinen ruutu.
                if (ruudukko[rivi, sarake].OnMiina)
                {
                    PaataPeli();
                    labelTila.Text = "🙁";
                    labelTila.BackColor = Color.Red;
                }
                // Viimeinen miinaton ruutu.
                else if (vaikeustaso.Ruutuja - paljastettuja <= vaikeustaso.Miinoja)
                {
                    PaataPeli();
                    PaivitaParasAika();
                    NaytaParasAika();
                    labelTila.Text = "🙂";
                    labelTila.BackColor = Color.LightGreen;
                }
            }
            // Merkitse ruutu.
            else if (e.Button == MouseButtons.Right)
            {
                if (ruudukko[rivi, sarake].Merkitse(ref merkittyja))
                {
                    labelMerkitty.Text = merkittyja.ToString();
                    pictureBoxPelialue.Invalidate();
                }
            }
        }

        private void timerAjastin_Tick(object sender, EventArgs e)
        {
            kaytettyAika++;
            labelAika.Text = AikaTekstina(kaytettyAika);
        }

        #endregion

        public Paaikkuna()
        {
            InitializeComponent();
            AlustaVaikeustaso(helppo);
            tietojaIkkuna = new TietojaIkkuna(new Vaikeustaso[] { helppo, keskivaikea, vaikea });
        }

        private void AsetaVaikeustasoValinta(ToolStripMenuItem sender, Vaikeustaso vaikeustaso)
        {
            foreach (ToolStripMenuItem valinta in vaikeustasoToolStripMenuItem.DropDownItems)
            {
                if (sender == valinta)
                {
                    if (this.vaikeustaso == vaikeustaso)
                    {
                        valinta.Checked = true;
                    }
                    else
                    {
                        AlustaVaikeustaso(vaikeustaso);
                    }
                }
                else
                {
                    valinta.Checked = false;
                }
            }
        }

        private void AlustaVaikeustaso(Vaikeustaso vaikeustaso)
        {
            this.vaikeustaso = vaikeustaso;

            ruudukonKoko.Width = this.vaikeustaso.Leveys * Ruutu.Koko;
            ruudukonKoko.Height = this.vaikeustaso.Korkeus * Ruutu.Koko;

            Alusta();
            AlustaIkkunanKoko();
        }

        private void AlustaIkkunanKoko()
        {
            int minLeveys = ruudukonKoko.Width + pictureBoxPelialue.Margin.Horizontal;
            int minKorkeus = tableLayoutPanel1.Location.Y + pictureBoxPelialue.Location.Y + 
                ruudukonKoko.Height + pictureBoxPelialue.Margin.Bottom;
            AutoScrollMinSize = new Size(minLeveys, minKorkeus);
            // Muutetaan ikkunan kokoa vain, jos ruudukko (pelialue) marginaaleineen ei mahdu siihen.
            ClientSize = new Size(
                Math.Max(minLeveys, ClientSize.Width), 
                Math.Max(minKorkeus, ClientSize.Height));
        }

        private void Alusta()
        {
            AlustaPelialue();
            ensimmainenPaljastus = true;
            alustaPainettaessa = false;
            paljastettuja = 0;
            merkittyja = 0;

            timerAjastin.Stop();
            kaytettyAika = 0;

            labelAika.Text = AikaTekstina(kaytettyAika);
            NaytaParasAika();
            labelMiinoja.Text = vaikeustaso.Miinoja.ToString();
            labelMerkitty.Text = merkittyja.ToString();
            labelTila.Text = "😶";
            labelTila.BackColor = Color.LightGray;
        }

        private void AlustaPelialue()
        {
            ruudukko = new Ruutu[vaikeustaso.Korkeus, vaikeustaso.Leveys];
            for (int rivi = 0; rivi < vaikeustaso.Korkeus; rivi++)
            {
                for (int sarake = 0; sarake < vaikeustaso.Leveys; sarake++)
                {
                    int x = sarake * Ruutu.Koko;
                    int y = rivi * Ruutu.Koko;
                    Ruutu ruutu = new Ruutu(x, y);
                    ruudukko[rivi, sarake] = ruutu;
                }
            }
            pictureBoxPelialue.Size = ruudukonKoko;
            pictureBoxPelialue.Invalidate();
        }

        private void ArvoMiinat(int varattuja = 0)
        {
            Random satunnaisluku = new Random();
            for (int i = 0; i < vaikeustaso.Miinoja; i++)
            {
                // Arvotaan vapaana olevien (miinattomien) ruutujen joukosta.
                int arvottuIndeksi = satunnaisluku.Next(vaikeustaso.Ruutuja - varattuja - i);
                int j = 0;
                foreach (Ruutu ruutu in ruudukko)
                {
                    // Jokaista vastaan tullutta varattua ruutua kohden täytyy arvottuun indeksiin (järjestysluku) lisätä yksi.
                    if (ruutu.OnMiina)
                    {
                        arvottuIndeksi++;
                    }
                    // Koska tarkistettiin ensin onko ruutu varattu, niin ehto ei voi täyttyä varatun ruudun kohdalla.
                    if (++j > arvottuIndeksi)
                    {
                        // Kun ollaan lisätty arvottuun indeksiin välissä olleiden varattujen ruutujen lukumäärä,
                        // ollaan saavuttu arvottuun indeksiin vapaana olevien ruutujen joukosta.
                        ruutu.OnMiina = true;
                        break;
                    }
                }
            }
        }

        private void TeeYmparoivilleRuuduille(int rivi, int sarake, TeeRuudulle teeRuudulle)
        {
            if (rivi > 0)
            {
                // x--
                // -o-
                // ---
                if (sarake > 0)
                {
                    teeRuudulle(rivi - 1, sarake - 1);
                }
                // -x-
                // -o-
                // ---
                teeRuudulle(rivi - 1, sarake);
                // --x
                // -o-
                // ---
                if (sarake < vaikeustaso.Leveys - 1)
                {
                    teeRuudulle(rivi - 1, sarake + 1);
                }
            }
            // ---
            // xo-
            // ---
            if (sarake > 0)
            {
                teeRuudulle(rivi, sarake - 1);
            }
            // ---
            // -ox
            // ---
            if (sarake < vaikeustaso.Leveys - 1)
            {
                teeRuudulle(rivi, sarake + 1);
            }
            if (rivi < vaikeustaso.Korkeus - 1)
            {
                // ---
                // -o-
                // x--
                if (sarake > 0)
                {
                    teeRuudulle(rivi + 1, sarake - 1);
                }
                // ---
                // -o-
                // -x-
                teeRuudulle(rivi + 1, sarake);
                // ---
                // -o-
                // --x
                if (sarake < vaikeustaso.Leveys - 1)
                {
                    teeRuudulle(rivi + 1, sarake + 1);
                }
            }
        }

        private void LisaaMiinojaYmparilla(int rivi, int sarake)
        {
            ruudukko[rivi, sarake].MiinojaYmparilla++;
        }

        /// <summary>
        /// Laskee jokaiselle ruudulle ympäröivien miinojen lukumäärän.
        /// </summary>
        private void LaskeMiinojaYmparilla()
        {
            // Käydään läpi kaikki ruudut.
            for (int rivi = 0; rivi < vaikeustaso.Korkeus; rivi++)
            {
                for (int sarake = 0; sarake < vaikeustaso.Leveys; sarake++)
                {
                    if (ruudukko[rivi, sarake].OnMiina)
                    {
                        // Miinaa ympäröiviin ruutuihin listätään tieto ympärillä olevasta miinasta.
                        TeeYmparoivilleRuuduille(rivi, sarake, LisaaMiinojaYmparilla);
                    }
                }
            }
        }

        private void PaljastaRuutu(int rivi, int sarake)
        {
            if (ruudukko[rivi, sarake].Paljasta(ref merkittyja))
            {
                paljastettuja++;
                if (ruudukko[rivi, sarake].MiinojaYmparilla == 0)
                {
                    // Jos ympärillä ei ole miinoja, paljastetaan ympäröivät ruudut (rekursiivinen kutsu).
                    TeeYmparoivilleRuuduille(rivi, sarake, PaljastaRuutu);
                }
                // Paljastaminen voi vähentää merkittyjen ruutujen määrää.
                labelMerkitty.Text = merkittyja.ToString();
                pictureBoxPelialue.Invalidate();
            }
        }

        private void PaataPeli()
        {
            PaljastaKaikki();
            alustaPainettaessa = true;
            timerAjastin.Stop();
        }

        private void PaljastaKaikki()
        {
            foreach (Ruutu ruutu in ruudukko)
            {
                ruutu.Paljasta(ref merkittyja);
            }
        }

        private void NaytaParasAika()
        {
            if (vaikeustaso.ParasAika > 0)
            {
                labelParas.Text = AikaTekstina(vaikeustaso.ParasAika);
            }
            else
            {
                labelParas.Text = "--:--";
            }
        }

        private void PaivitaParasAika()
        {
            if (vaikeustaso.ParasAika == 0 || kaytettyAika < vaikeustaso.ParasAika)
            {
                vaikeustaso.ParasAika = kaytettyAika;
            }
        }

        private static string AikaTekstina(int aikaSekunteina)
        {
            return $"{aikaSekunteina / 60:00}:{aikaSekunteina % 60:00}";
        }
    }
}
