using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _10_Dictionary
{
    public partial class Form1 : Form
    {
        Dictionary<string, string> dictionary;
        public Form1()
        {
            InitializeComponent();
            button2.Enabled = false;
            button3.Enabled = false;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            dictionary = new Dictionary<string, string>();
            button2.Enabled = true;
            button3.Enabled = true;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            dictionary[textBoxKeyLisaa.Text] = textBoxValueLisaa.Text;
        }

        private void button3_Click(object sender, EventArgs e)
        {
            if (dictionary.TryGetValue(textBoxKeyHae.Text, out string value))
            {
                labelValueHae.Text = value;
            }
        }
    }
}
