using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _08_Array_Lotto
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            const int lukuja = 40;
            const int arvotaanNumeroita = 7;
            const int arvotaanLisanumeroita = 2;
            Random satunnaisluku = new Random();
            // Arvotaan numerot.
            bool[] arvotutIndeksit = new bool[lukuja];
            for (int i = 0; i < arvotaanNumeroita; i++)
            {
                int arvottuIndeksi = satunnaisluku.Next(lukuja - i);
                for (int j = 0; j < arvottuIndeksi + 1; j++)
                {
                    if (arvotutIndeksit[j])
                    {
                        arvottuIndeksi++;
                    }
                }
                arvotutIndeksit[arvottuIndeksi] = true;
            }
            // Arvotaan lisänumerot.
            bool[] lisanumerot = new bool[lukuja];
            for (int i = 0; i < arvotaanLisanumeroita; i++)
            {
                int arvottuIndeksi = satunnaisluku.Next(lukuja - arvotaanNumeroita - i);
                for (int j = 0; j < arvottuIndeksi + 1; j++)
                {
                    if (arvotutIndeksit[j] || lisanumerot[j])
                    {
                        arvottuIndeksi++;
                    }
                }
                lisanumerot[arvottuIndeksi] = true;
            }
            // Näytetään numerot.
            labelLottorivi.Text = ArvontaTekstiksi(arvotutIndeksit);
            labelLisanumerot.Text = ArvontaTekstiksi(lisanumerot);
            // Muutetaan arvaukset kokonaislukutaulukoksi.
            int[] arvaukset = new int[tableLayoutPanelArvaukset.Controls.Count];
            for (int i = 0; i < tableLayoutPanelArvaukset.Controls.Count; i++)
            {
                TextBox tekstilaatikko = (TextBox)tableLayoutPanelArvaukset.Controls[i];
                int.TryParse(tekstilaatikko.Text, out int arvaus);
                arvaukset[i] = arvaus;
            }
            // Näytetään kuinka monta arvattu oikein.
            labelOikein.Text = OikeinArvattuja(arvotutIndeksit, arvaukset).ToString();
            labelOikeinLisa.Text = OikeinArvattuja(lisanumerot, arvaukset).ToString();
        }

        public static string ArvontaTekstiksi(bool[] arvonta)
        {
            StringBuilder numerot = new StringBuilder();
            string valistys = "";
            for (int i = 0; i < arvonta.Length; i++)
            {
                if (arvonta[i])
                {
                    numerot.Append(valistys);
                    numerot.Append(i + 1);
                    valistys = ", ";
                }
            }
            return numerot.ToString();
        }

        public static int OikeinArvattuja(bool[] arvonta, int[] arvaukset)
        {
            int lukuja = arvonta.Length;
            bool[] arvontaKopio = new bool[lukuja];
            arvonta.CopyTo(arvontaKopio, 0);

            int oikein = 0;

            foreach (int arvaus in arvaukset)
            {
                if (0 < arvaus && arvaus <= lukuja)
                {
                    if (arvontaKopio[arvaus - 1])
                    {
                        oikein++;
                        arvontaKopio[arvaus - 1] = false;
                    }
                }
            }

            return oikein;
        }
    }
}
