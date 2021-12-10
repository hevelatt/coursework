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
    public partial class TietojaIkkuna : Form
    {
        internal TietojaIkkuna(Vaikeustaso[] vaikeaustasot)
        {
            InitializeComponent();
            AlustaTiedot(vaikeaustasot);
        }

        private void AlustaTiedot(Vaikeustaso[] vaikeustasot)
        {
            StringBuilder tekstiSb = new StringBuilder();
            tekstiSb.AppendLine("Laudalla on piilossa miinoja, joiden paikat tulee merkitä lipuilla.");
            tekstiSb.AppendLine();
            tekstiSb.Append("Pelaaja klikkaa hiiren vasemmalla näppäimellä paljastamattomia ruutuja: ");
            tekstiSb.Append("jos ruudussa on miina, se räjähtää ja peli päättyy. ");
            tekstiSb.AppendLine("Jos ruudussa ei ole miinaa, ruudussa näkyy, monessako ruudun kahdeksasta naapuriruudusta on miina.");
            tekstiSb.AppendLine();
            tekstiSb.Append("Pelaaja voi merkitä paljastamattoman ruudun, jossa päättelee olevan miinan. ");
            tekstiSb.AppendLine("Kun kaikki miinattomat ruudut on paljastettu, pelaaja on onnistunut ja hänen tuloksensa on käytetty aika.");
            tekstiSb.AppendLine();
            tekstiSb.AppendLine("Käyttäjä voi valita");
            foreach (Vaikeustaso vaikeustaso in vaikeustasot)
            {
                tekstiSb.Append($"- vaikeustason {vaikeustaso.Nimi}");
                tekstiSb.Append($", jolloin laudan koko on {vaikeustaso.Leveys} x {vaikeustaso.Korkeus} ruutua");
                tekstiSb.AppendLine($" ja miinoja on {vaikeustaso.Miinoja}.");
            }

            labelTietoja.Text = tekstiSb.ToString();
        }

        private void buttonOK_Click(object sender, EventArgs e)
        {
            Close();
        }
    }
}
