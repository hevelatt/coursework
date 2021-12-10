using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Elokuvasovellus
{
    public partial class Paaikkuna : Form
    {
        private List<TextBoxWithDefaultText> tekstilaatikot;

        public Paaikkuna()
        {
            InitializeComponent();

            tekstilaatikot = new List<TextBoxWithDefaultText>();
            tekstilaatikot.Add(new TextBoxWithDefaultText(textBoxId, "ID", "0", true));
            tekstilaatikot.Add(new TextBoxWithDefaultText(textBoxNimi, "nimi", "[Syötä elokuvan nimi]"));
            tekstilaatikot.Add(new TextBoxWithDefaultText(textBoxVuosi, "julkaisuvuosi", DateTime.Now.Year.ToString(), true));
            tekstilaatikot.Add(new TextBoxWithDefaultText(textBoxKesto, "elokuvan kesto", "0", true));
            tekstilaatikot.Add(new TextBoxWithDefaultText(richTextBoxArvio, "arvio", "[Kirjoita arvio tähän]"));
            numericUpDownArvosana1.Value = 5;

            buttonUusi.Select();
        }

        private void infoToolStripMenuItem_Click(object sender, EventArgs e)
        {
            MessageBox.Show("Tämän sovelluksen on tehnyt Herman Lätti!");
        }

        private void buttonUusi_Click(object sender, EventArgs e)
        {
            foreach (var tekstilaatikko in tekstilaatikot)
            {
                tekstilaatikko.SetDefault();
            }
            numericUpDownArvosana1.Value = 5;
        }

        private void poistuToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void tekstilaatikko_Enter(object sender, EventArgs e)
        {
            switch (sender)
            {
                case TextBoxBase tekstilaatikko:
                    foreach (var oletusTekstilaatikko in tekstilaatikot)
                    {
                        if (tekstilaatikko == oletusTekstilaatikko.TextBox)
                        {
                            if (oletusTekstilaatikko.IsDefault)
                            {
                                tekstilaatikko.Clear();
                            }
                        }
                    }
                    break;
            }
        }

        private void tekstilaatikko_Leave(object sender, EventArgs e)
        {
            switch (sender)
            {
                case TextBoxBase tekstilaatikko:
                    if (string.IsNullOrWhiteSpace(tekstilaatikko.Text))
                    {
                        foreach (var oletusTekstilaatikko in tekstilaatikot)
                        {
                            if (tekstilaatikko == oletusTekstilaatikko.TextBox)
                            {
                                oletusTekstilaatikko.SetDefault();
                            }
                        }
                    }
                    break;
            }
        }

        private void buttonTallenna_Click(object sender, EventArgs e)
        {
            bool ok = true;
            foreach (var tekstilaatikko in tekstilaatikot)
            {
                // Numeroiden kohdalla saatetaan haluta pitää oletusnumero (esim. vuosi)
                if (tekstilaatikko.IsEmpty || (tekstilaatikko.IsDefault && !tekstilaatikko.IsNumber))
                {
                    ok = false;
                    MessageBox.Show($"Et ole syöttänyt mitään {tekstilaatikko}-kenttään!");
                }
                else if (tekstilaatikko.IsNumber)
                {
                    if (!int.TryParse(tekstilaatikko.TextBox.Text, out int _))
                    {
                        ok = false;
                        string nimiIsolla = char.ToUpper(tekstilaatikko.Name[0]) + tekstilaatikko.Name.Substring(1);
                        if (tekstilaatikko.TextBox.Text.Any(char.IsDigit))
                        {
                            MessageBox.Show($"{nimiIsolla} ei ole vain numeroita!");
                        }
                        else
                        {
                            MessageBox.Show($"{nimiIsolla} ei ole numeroita!");
                        }
                    }
                }
            }
            if (ok)
            {
                MessageBox.Show("Check OK!");
            }
        }
    }
}
