using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _06_While
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        { 
            while (DialogResult.Yes == MessageBox.Show(
                    "Valitse YES jatkaaksesi tai NO lopettaaksesi.", "Huom !", 
                    MessageBoxButtons.YesNo));
        }
    }
}
