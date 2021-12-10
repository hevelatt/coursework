using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _12_Keyboard
{
    public partial class MainForm : Form
    {
        private void MainForm_KeyUp(object sender, KeyEventArgs e)
        {
            Invalidate();
        }

        private void MainForm_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode.Equals(Keys.W))
            {
                piste.Y -= 1;
            }
            else if (e.KeyCode.Equals(Keys.A))
            {
                piste.X -= 1;
            }
            else if (e.KeyCode.Equals(Keys.S))
            {
                piste.Y += 1;
            }
            else if (e.KeyCode.Equals(Keys.D))
            {
                piste.X += 1;
            }

        }

        public MainForm()
        {
            InitializeComponent();
        }

        // MainFrom:n alkuun määritellään koordinaattipiste(x,y), joka on käytössä koko lomakkeella. 
        //Tällä pisteellä määritämme mistä aloitamme piirtämisen. Formin vasen ylänurkka on 0,0 
        //piste  
        Point piste = new Point(0, 0);
        bool raahaa = false;

        private void MainForm_Paint(object sender, PaintEventArgs e)
        {
            Graphics Graf = e.Graphics;
            // - Pää 
            Graf.FillEllipse(Brushes.IndianRed, piste.X - 4, piste.Y - 8, 49, 49);
            Graf.DrawEllipse(Pens.Black, piste.X - 4, piste.Y - 8, 49, 49);
            // - Selkä 
            Graf.DrawLine(Pens.Black, piste.X + 21, piste.Y + 41,
                piste.X + 21, piste.Y + 131);
            // - Kädet 
            Graf.DrawLine(Pens.Black, piste.X - 30, piste.Y + 60,
                piste.X + 70, piste.Y + 60);
            // - Jalat 
            Graf.DrawLine(Pens.Black, piste.X + 21, piste.Y + 131,
                piste.X - 30, piste.Y + 181);
            Graf.DrawLine(Pens.Black, piste.X + 21, piste.Y + 131,
                piste.X + 70, piste.Y + 181);

            // Kutsutaan DrawCoordinates()-metodia. 
            DrawCoordinates(Graf);
        }

        // DrawCoordinates() -metodi PIIRTÄÄ pisteen koordinaatit lomakkeelle.  
        private void DrawCoordinates(Graphics Graf)
        {
            // Piirretään piirtokoordinaattien arvot näytölle. 
            Graf.DrawString("(" + piste.X + ", " + piste.Y + ")",
                new Font("Arial", 14, System.Drawing.FontStyle.Regular),
                new SolidBrush(Color.Black), Width - 140, Height - 80);
        }

        private void MainForm_MouseDown(object sender, MouseEventArgs e)
        {
            if (e.Button == MouseButtons.Left)
            {
                // Talletetaan hiiren klikkauskohdan koordinaatit. Piste (0, 0) 
                // on formin työalueen vasemmassa ylä nurkassa 
                piste = e.Location;

                // Merkitään formin työalue epäkelvoksi, jolloin saadaan aikaiseksi 
                // paint-eventin signalointi ja tämän jälkeen Paint()-metodin kutsu. 
                Invalidate();
                raahaa = true;
            }
        }

        private void MainForm_MouseUp(object sender, MouseEventArgs e)
        {
            if (e.Button == MouseButtons.Left)
            {
                raahaa = false;
            }
        }

        private void MainForm_MouseMove(object sender, MouseEventArgs e)
        {
            if (raahaa)
            {
                // Talletetaan hiiren klikkauskohdan koordinaatit. Piste (0, 0) 
                // on formin työalueen vasemmassa ylä nurkassa 
                piste = e.Location;

                // Merkitään formin työalue epäkelvoksi, jolloin saadaan aikaiseksi 
                // paint-eventin signalointi ja tämän jälkeen Paint()-metodin kutsu. 
                Invalidate();
            }
        }

        private void MainForm_Resize(object sender, EventArgs e)
        {
            Invalidate();
        }
    }
}
