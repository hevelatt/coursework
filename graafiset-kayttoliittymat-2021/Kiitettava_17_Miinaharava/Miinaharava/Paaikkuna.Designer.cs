
namespace Miinaharava
{
    partial class Paaikkuna
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.valinnatToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.uusiPeliToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.vaikeustasoToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.helppoToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.keskivaikeaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.vaikeaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.lopetaPeliToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.tietojaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.tietojaToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.tableLayoutPanelTila = new System.Windows.Forms.TableLayoutPanel();
            this.tableLayoutPanel2 = new System.Windows.Forms.TableLayoutPanel();
            this.labelParas = new System.Windows.Forms.Label();
            this.labelAika = new System.Windows.Forms.Label();
            this.labelTila = new System.Windows.Forms.Label();
            this.tableLayoutPanel3 = new System.Windows.Forms.TableLayoutPanel();
            this.labelMiinoja = new System.Windows.Forms.Label();
            this.labelMerkitty = new System.Windows.Forms.Label();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.pictureBoxPelialue = new System.Windows.Forms.PictureBox();
            this.timerAjastin = new System.Windows.Forms.Timer(this.components);
            this.menuStrip1.SuspendLayout();
            this.tableLayoutPanelTila.SuspendLayout();
            this.tableLayoutPanel2.SuspendLayout();
            this.tableLayoutPanel3.SuspendLayout();
            this.tableLayoutPanel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxPelialue)).BeginInit();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.valinnatToolStripMenuItem,
            this.tietojaToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(217, 24);
            this.menuStrip1.TabIndex = 0;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // valinnatToolStripMenuItem
            // 
            this.valinnatToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.uusiPeliToolStripMenuItem,
            this.vaikeustasoToolStripMenuItem,
            this.lopetaPeliToolStripMenuItem});
            this.valinnatToolStripMenuItem.Name = "valinnatToolStripMenuItem";
            this.valinnatToolStripMenuItem.Size = new System.Drawing.Size(61, 20);
            this.valinnatToolStripMenuItem.Text = "&Valinnat";
            // 
            // uusiPeliToolStripMenuItem
            // 
            this.uusiPeliToolStripMenuItem.Name = "uusiPeliToolStripMenuItem";
            this.uusiPeliToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.N)));
            this.uusiPeliToolStripMenuItem.Size = new System.Drawing.Size(173, 22);
            this.uusiPeliToolStripMenuItem.Text = "&Uusi peli";
            this.uusiPeliToolStripMenuItem.Click += new System.EventHandler(this.uusiPeliToolStripMenuItem_Click);
            // 
            // vaikeustasoToolStripMenuItem
            // 
            this.vaikeustasoToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.helppoToolStripMenuItem,
            this.keskivaikeaToolStripMenuItem,
            this.vaikeaToolStripMenuItem});
            this.vaikeustasoToolStripMenuItem.Name = "vaikeustasoToolStripMenuItem";
            this.vaikeustasoToolStripMenuItem.Size = new System.Drawing.Size(173, 22);
            this.vaikeustasoToolStripMenuItem.Text = "V&aikeustaso...";
            // 
            // helppoToolStripMenuItem
            // 
            this.helppoToolStripMenuItem.Checked = true;
            this.helppoToolStripMenuItem.CheckOnClick = true;
            this.helppoToolStripMenuItem.CheckState = System.Windows.Forms.CheckState.Checked;
            this.helppoToolStripMenuItem.Name = "helppoToolStripMenuItem";
            this.helppoToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.E)));
            this.helppoToolStripMenuItem.Size = new System.Drawing.Size(179, 22);
            this.helppoToolStripMenuItem.Text = "Helppo";
            this.helppoToolStripMenuItem.Click += new System.EventHandler(this.helppoToolStripMenuItem_Click);
            // 
            // keskivaikeaToolStripMenuItem
            // 
            this.keskivaikeaToolStripMenuItem.CheckOnClick = true;
            this.keskivaikeaToolStripMenuItem.Name = "keskivaikeaToolStripMenuItem";
            this.keskivaikeaToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.M)));
            this.keskivaikeaToolStripMenuItem.Size = new System.Drawing.Size(179, 22);
            this.keskivaikeaToolStripMenuItem.Text = "Keskivaikea";
            this.keskivaikeaToolStripMenuItem.Click += new System.EventHandler(this.keskivaikeaToolStripMenuItem_Click);
            // 
            // vaikeaToolStripMenuItem
            // 
            this.vaikeaToolStripMenuItem.CheckOnClick = true;
            this.vaikeaToolStripMenuItem.Name = "vaikeaToolStripMenuItem";
            this.vaikeaToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.H)));
            this.vaikeaToolStripMenuItem.Size = new System.Drawing.Size(179, 22);
            this.vaikeaToolStripMenuItem.Text = "Vaikea";
            this.vaikeaToolStripMenuItem.Click += new System.EventHandler(this.vaikeaToolStripMenuItem_Click);
            // 
            // lopetaPeliToolStripMenuItem
            // 
            this.lopetaPeliToolStripMenuItem.Name = "lopetaPeliToolStripMenuItem";
            this.lopetaPeliToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.X)));
            this.lopetaPeliToolStripMenuItem.Size = new System.Drawing.Size(173, 22);
            this.lopetaPeliToolStripMenuItem.Text = "&Lopeta peli";
            this.lopetaPeliToolStripMenuItem.Click += new System.EventHandler(this.lopetaPeliToolStripMenuItem_Click);
            // 
            // tietojaToolStripMenuItem
            // 
            this.tietojaToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tietojaToolStripMenuItem1});
            this.tietojaToolStripMenuItem.Name = "tietojaToolStripMenuItem";
            this.tietojaToolStripMenuItem.Size = new System.Drawing.Size(54, 20);
            this.tietojaToolStripMenuItem.Text = "&Tietoja";
            // 
            // tietojaToolStripMenuItem1
            // 
            this.tietojaToolStripMenuItem1.Name = "tietojaToolStripMenuItem1";
            this.tietojaToolStripMenuItem1.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.I)));
            this.tietojaToolStripMenuItem1.Size = new System.Drawing.Size(203, 22);
            this.tietojaToolStripMenuItem1.Text = "&Ohjeet ja tietoja...";
            this.tietojaToolStripMenuItem1.Click += new System.EventHandler(this.tietojaToolStripMenuItem1_Click);
            // 
            // tableLayoutPanelTila
            // 
            this.tableLayoutPanelTila.AutoSize = true;
            this.tableLayoutPanelTila.ColumnCount = 3;
            this.tableLayoutPanelTila.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.tableLayoutPanelTila.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.tableLayoutPanelTila.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.tableLayoutPanelTila.Controls.Add(this.tableLayoutPanel2, 0, 0);
            this.tableLayoutPanelTila.Controls.Add(this.labelTila, 1, 0);
            this.tableLayoutPanelTila.Controls.Add(this.tableLayoutPanel3, 2, 0);
            this.tableLayoutPanelTila.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanelTila.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanelTila.Margin = new System.Windows.Forms.Padding(0);
            this.tableLayoutPanelTila.Name = "tableLayoutPanelTila";
            this.tableLayoutPanelTila.RowCount = 1;
            this.tableLayoutPanelTila.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanelTila.Size = new System.Drawing.Size(217, 39);
            this.tableLayoutPanelTila.TabIndex = 1;
            // 
            // tableLayoutPanel2
            // 
            this.tableLayoutPanel2.AutoSize = true;
            this.tableLayoutPanel2.ColumnCount = 1;
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.Controls.Add(this.labelParas, 0, 0);
            this.tableLayoutPanel2.Controls.Add(this.labelAika, 0, 1);
            this.tableLayoutPanel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel2.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel2.Margin = new System.Windows.Forms.Padding(0);
            this.tableLayoutPanel2.Name = "tableLayoutPanel2";
            this.tableLayoutPanel2.RowCount = 2;
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.Size = new System.Drawing.Size(72, 39);
            this.tableLayoutPanel2.TabIndex = 3;
            // 
            // labelParas
            // 
            this.labelParas.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.labelParas.AutoSize = true;
            this.labelParas.Font = new System.Drawing.Font("Lucida Console", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.labelParas.Location = new System.Drawing.Point(8, 2);
            this.labelParas.Name = "labelParas";
            this.labelParas.Size = new System.Drawing.Size(55, 14);
            this.labelParas.TabIndex = 2;
            this.labelParas.Text = "label1";
            // 
            // labelAika
            // 
            this.labelAika.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.labelAika.AutoSize = true;
            this.labelAika.Font = new System.Drawing.Font("Lucida Console", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.labelAika.Location = new System.Drawing.Point(8, 22);
            this.labelAika.Name = "labelAika";
            this.labelAika.Size = new System.Drawing.Size(55, 14);
            this.labelAika.TabIndex = 1;
            this.labelAika.Text = "label1";
            // 
            // labelTila
            // 
            this.labelTila.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.labelTila.AutoSize = true;
            this.labelTila.BackColor = System.Drawing.Color.LightGray;
            this.labelTila.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.labelTila.Font = new System.Drawing.Font("Segoe UI", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.labelTila.Location = new System.Drawing.Point(80, 0);
            this.labelTila.Name = "labelTila";
            this.labelTila.Size = new System.Drawing.Size(56, 39);
            this.labelTila.TabIndex = 0;
            this.labelTila.Text = "😶";
            this.labelTila.Click += new System.EventHandler(this.labelTila_Click);
            // 
            // tableLayoutPanel3
            // 
            this.tableLayoutPanel3.AutoSize = true;
            this.tableLayoutPanel3.ColumnCount = 1;
            this.tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel3.Controls.Add(this.labelMiinoja, 0, 0);
            this.tableLayoutPanel3.Controls.Add(this.labelMerkitty, 0, 1);
            this.tableLayoutPanel3.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel3.Location = new System.Drawing.Point(144, 0);
            this.tableLayoutPanel3.Margin = new System.Windows.Forms.Padding(0);
            this.tableLayoutPanel3.Name = "tableLayoutPanel3";
            this.tableLayoutPanel3.RowCount = 2;
            this.tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel3.Size = new System.Drawing.Size(73, 39);
            this.tableLayoutPanel3.TabIndex = 3;
            // 
            // labelMiinoja
            // 
            this.labelMiinoja.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.labelMiinoja.AutoSize = true;
            this.labelMiinoja.Font = new System.Drawing.Font("Lucida Console", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.labelMiinoja.Location = new System.Drawing.Point(9, 2);
            this.labelMiinoja.Name = "labelMiinoja";
            this.labelMiinoja.Size = new System.Drawing.Size(55, 14);
            this.labelMiinoja.TabIndex = 0;
            this.labelMiinoja.Text = "label1";
            // 
            // labelMerkitty
            // 
            this.labelMerkitty.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.labelMerkitty.AutoSize = true;
            this.labelMerkitty.Font = new System.Drawing.Font("Lucida Console", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.labelMerkitty.Location = new System.Drawing.Point(9, 22);
            this.labelMerkitty.Name = "labelMerkitty";
            this.labelMerkitty.Size = new System.Drawing.Size(55, 14);
            this.labelMerkitty.TabIndex = 0;
            this.labelMerkitty.Text = "label1";
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 1;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.Controls.Add(this.tableLayoutPanelTila, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.pictureBoxPelialue, 0, 1);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 24);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 2;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(217, 209);
            this.tableLayoutPanel1.TabIndex = 3;
            // 
            // pictureBoxPelialue
            // 
            this.pictureBoxPelialue.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.pictureBoxPelialue.Location = new System.Drawing.Point(58, 42);
            this.pictureBoxPelialue.Name = "pictureBoxPelialue";
            this.pictureBoxPelialue.Size = new System.Drawing.Size(100, 50);
            this.pictureBoxPelialue.TabIndex = 2;
            this.pictureBoxPelialue.TabStop = false;
            this.pictureBoxPelialue.Paint += new System.Windows.Forms.PaintEventHandler(this.pictureBoxPelialue_Paint);
            this.pictureBoxPelialue.MouseDown += new System.Windows.Forms.MouseEventHandler(this.pictureBoxPelialue_MouseDown);
            // 
            // timerAjastin
            // 
            this.timerAjastin.Interval = 1000;
            this.timerAjastin.Tick += new System.EventHandler(this.timerAjastin_Tick);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(217, 233);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "Form1";
            this.Text = "Miinaharava";
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.tableLayoutPanelTila.ResumeLayout(false);
            this.tableLayoutPanelTila.PerformLayout();
            this.tableLayoutPanel2.ResumeLayout(false);
            this.tableLayoutPanel2.PerformLayout();
            this.tableLayoutPanel3.ResumeLayout(false);
            this.tableLayoutPanel3.PerformLayout();
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxPelialue)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem valinnatToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem uusiPeliToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem vaikeustasoToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem tietojaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem helppoToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem keskivaikeaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem vaikeaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem lopetaPeliToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem tietojaToolStripMenuItem1;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelTila;
        private System.Windows.Forms.Label labelTila;
        private System.Windows.Forms.Label labelParas;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.Label labelAika;
        private System.Windows.Forms.Timer timerAjastin;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel2;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel3;
        private System.Windows.Forms.Label labelMerkitty;
        private System.Windows.Forms.Label labelMiinoja;
        private System.Windows.Forms.PictureBox pictureBoxPelialue;
    }
}

