using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _07_Array
{
    public partial class Form1 : Form
    {
        // Esitellään ja alustetaan taulukko, jotta se ei olisi null missään vaiheessa.
        private int[] taulukko = new int[0];

        public Form1()
        {
            InitializeComponent();
            int minX = panelPohja.Location.X + tableLayoutPanelPohja.Location.X + 
                tableLayoutPanelPohja.Padding.Left + tableLayoutPanelSisalto.Margin.Left + 
                tableLayoutPanelSisalto.Size.Width + tableLayoutPanelSisalto.Margin.Right + 
                tableLayoutPanelPohja.Padding.Right + panelPohja.Padding.Right;
            int minY = panelPohja.Location.Y + tableLayoutPanelPohja.Location.Y + 
                tableLayoutPanelSisalto.Margin.Top + tableLayoutPanelSisalto.Margin.Top + 
                tableLayoutPanelSisalto.Size.Height + tableLayoutPanelSisalto.Margin.Bottom + 
                tableLayoutPanelPohja.Padding.Bottom + panelPohja.Padding.Bottom;
            Size = new Size(minX + 25, minY + 42);
            AutoScrollMinSize = new Size(minX, minY);
        }

        private void PoikkeusKasittelija(Action heittaja)
        {
            try
            {
                heittaja();
            }
            catch (OverflowException)
            {
                MessageBox.Show("Luku on liian suuri tai liian pieni.");
            }
            catch (IndexOutOfRangeException)
            {
                MessageBox.Show("Indeksi ei ole taulukon sisältä." + Environment.NewLine +
                    "Indeksin tulee olla väliltä 0 - " + (taulukko.Length - 1) + ".");
            }
            catch (FormatException)
            {
                MessageBox.Show("Syöte ei ole oikeaa muotoa.");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void buttonLuoTaulukko_Click(object sender, EventArgs e)
        {
            PoikkeusKasittelija(() => taulukko = new int[uint.Parse(textBoxLuontiKoko.Text)]);
        }

        private void buttonTallenna_Click(object sender, EventArgs e)
        {
            PoikkeusKasittelija(() => taulukko[uint.Parse(textBoxTallennusIndeksi.Text)] = int.Parse(textBoxTallennusNumero.Text));
        }

        private void buttonHae_Click(object sender, EventArgs e)
        {
            PoikkeusKasittelija(() => labelHakutulos.Text = taulukko[uint.Parse(textBoxHakuIndeksi.Text)].ToString());
        }
    }
}
