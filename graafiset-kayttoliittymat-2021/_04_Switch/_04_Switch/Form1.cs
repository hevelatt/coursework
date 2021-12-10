using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _04_Switch
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void ButtonClick(object sender, EventArgs e)
        {
            string viesti = "Ei nappi";
            if (sender is Button painettuNappi)
            {
                //MessageBox.Show(painettuNappi.Text, "Huom");
                switch (painettuNappi.Name)
                {
                    case "button1":
                        viesti = "Valinta 1";
                        break;
                    case "button2":
                        viesti = "Valinta 2";
                        break;
                    case "button3":
                        viesti = "Valinta 3";
                        break;
                    case "button4":
                        viesti = "Valinta 4";
                        break;
                    case "button5":
                    default:
                        viesti = "Default";
                        break;
                }
            }
            MessageBox.Show(viesti, "Huom");
        }
    }
}
