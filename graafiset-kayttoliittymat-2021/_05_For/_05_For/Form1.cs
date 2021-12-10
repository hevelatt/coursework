using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Threading;

namespace _05_For
{
    public partial class Form1 : Form
    {
        private delegate void TurvallinenKutsuDelegaatti(string teksti, Label label);
        private bool laskuriKaynnissa = false;
        private bool poistuLaskurista = false;

        public Form1()
        {
            InitializeComponent();
            FormClosing += Form1_FormClosing;
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            // Jos laskuri on käynnissä, ohjelma ei sulkeudu.
            // Muuten on mahdollista, että käyttöliittymä sulkeutuu ennen laskurisäiettä, aiheuttaen ongelmia.
            if (laskuriKaynnissa)
            {
                poistuLaskurista = true;
                e.Cancel = true;
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (laskuriKaynnissa)
            {
                poistuLaskurista = true;
            }
            else
            {
                laskuriKaynnissa = true;
                // Laskuri on metodi, joka ajetaan luotavassa säikeessä.
                Thread laskuriSaie = new Thread(new ThreadStart(Laskuri));
                laskuriSaie.Start();
            }
        }

        private void Laskuri()
        {
            int.TryParse(textBox1.Text, out int luku);
            for (int i = 0; i <= luku; i++)
            {
                if (poistuLaskurista)
                {
                    poistuLaskurista = false;
                    break;
                }
                AsetaTekstiLabeliin(i.ToString(), label1);
                Thread.Sleep(100);
            }
            laskuriKaynnissa = false;
        }

        private void AsetaTekstiLabeliin(string teksti, Label label)
        {
            // Jos ollaan eri säikeessä kuin missä label on luotu
            if (label.InvokeRequired)
            {
                TurvallinenKutsuDelegaatti turvallinenKutsu = new TurvallinenKutsuDelegaatti(AsetaTekstiLabeliin);
                // niin ajetaan määritelty delegaatti säikeessä, jossa teksti on luotu ("rekursiivinen" kutsu).
                Invoke(turvallinenKutsu, new object[] { teksti, label });
            }
            else
            {
                // Muussa tapauksessa ollaan oikeassa säikeessä, jolloin voidaan asettaa teksti labeliin.
                label.Text = teksti;
            }
        }
    }
}
