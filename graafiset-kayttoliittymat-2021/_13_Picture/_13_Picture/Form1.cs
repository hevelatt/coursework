using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _13_Picture
{
    public partial class Form1 : Form
    {
        private Point piste = new Point(0, 0);
        Bitmap bittikartta = new Bitmap(Properties.Resources.HessuHopo);
        public Form1()
        {
            InitializeComponent();
            piste.X = Size.Width / 2;
            piste.Y = Size.Height / 2;
            bittikartta.MakeTransparent();
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            e.Graphics.DrawImage(bittikartta, piste.X - bittikartta.Width / 2, piste.Y - bittikartta.Height / 2);
        }

        private void Form1_MouseDown(object sender, MouseEventArgs e)
        {
            if (e.Button == MouseButtons.Left)
            {
                piste = e.Location;
                Invalidate();
            }
        }

        private void Form1_MouseMove(object sender, MouseEventArgs e)
        {
            if (e.Button == MouseButtons.Left)
            {
                piste = e.Location;
                Invalidate();
            }
        }
    }
}
