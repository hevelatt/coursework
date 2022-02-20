namespace Sarjakuvarekisteri
{
    partial class FormPaaikkuna
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
            this.menuStripPaavalikko = new System.Windows.Forms.MenuStrip();
            this.tableLayoutPanelPohja = new System.Windows.Forms.TableLayoutPanel();
            this.tableLayoutPanelHaku = new System.Windows.Forms.TableLayoutPanel();
            this.groupBoxHakuehto = new System.Windows.Forms.GroupBox();
            this.panelHakuehto = new System.Windows.Forms.Panel();
            this.buttonHae = new System.Windows.Forms.Button();
            this.tableLayoutPanelTiedot = new System.Windows.Forms.TableLayoutPanel();
            this.tableLayoutPanelJulkaisuOhjaimet = new System.Windows.Forms.TableLayoutPanel();
            this.buttonLisaaJulkaisu = new System.Windows.Forms.Button();
            this.buttonMuokkaaJulkaisu = new System.Windows.Forms.Button();
            this.buttonPoistaJulkaisu = new System.Windows.Forms.Button();
            this.tableLayoutPanelTarinaOhjaimet = new System.Windows.Forms.TableLayoutPanel();
            this.buttonLisaaTarina = new System.Windows.Forms.Button();
            this.buttonMuokkaaTarina = new System.Windows.Forms.Button();
            this.buttonPoistaTarina = new System.Windows.Forms.Button();
            this.panelJulkaisu = new System.Windows.Forms.Panel();
            this.panelTarina = new System.Windows.Forms.Panel();
            this.tableLayoutPanelPohja.SuspendLayout();
            this.tableLayoutPanelHaku.SuspendLayout();
            this.panelHakuehto.SuspendLayout();
            this.tableLayoutPanelTiedot.SuspendLayout();
            this.tableLayoutPanelJulkaisuOhjaimet.SuspendLayout();
            this.tableLayoutPanelTarinaOhjaimet.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStripPaavalikko
            // 
            this.menuStripPaavalikko.Location = new System.Drawing.Point(0, 0);
            this.menuStripPaavalikko.Name = "menuStripPaavalikko";
            this.menuStripPaavalikko.Size = new System.Drawing.Size(800, 24);
            this.menuStripPaavalikko.TabIndex = 0;
            this.menuStripPaavalikko.Text = "menuStrip1";
            // 
            // tableLayoutPanelPohja
            // 
            this.tableLayoutPanelPohja.ColumnCount = 2;
            this.tableLayoutPanelPohja.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanelPohja.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanelPohja.Controls.Add(this.tableLayoutPanelHaku, 0, 0);
            this.tableLayoutPanelPohja.Controls.Add(this.tableLayoutPanelTiedot, 1, 0);
            this.tableLayoutPanelPohja.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanelPohja.Location = new System.Drawing.Point(0, 24);
            this.tableLayoutPanelPohja.Name = "tableLayoutPanelPohja";
            this.tableLayoutPanelPohja.RowCount = 1;
            this.tableLayoutPanelPohja.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanelPohja.Size = new System.Drawing.Size(800, 426);
            this.tableLayoutPanelPohja.TabIndex = 1;
            // 
            // tableLayoutPanelHaku
            // 
            this.tableLayoutPanelHaku.ColumnCount = 1;
            this.tableLayoutPanelHaku.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanelHaku.Controls.Add(this.panelHakuehto, 0, 0);
            this.tableLayoutPanelHaku.Controls.Add(this.buttonHae, 0, 1);
            this.tableLayoutPanelHaku.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanelHaku.Location = new System.Drawing.Point(3, 3);
            this.tableLayoutPanelHaku.Name = "tableLayoutPanelHaku";
            this.tableLayoutPanelHaku.RowCount = 2;
            this.tableLayoutPanelHaku.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanelHaku.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanelHaku.Size = new System.Drawing.Size(394, 420);
            this.tableLayoutPanelHaku.TabIndex = 0;
            // 
            // groupBoxHakuehto
            // 
            this.groupBoxHakuehto.Dock = System.Windows.Forms.DockStyle.Fill;
            this.groupBoxHakuehto.Location = new System.Drawing.Point(0, 0);
            this.groupBoxHakuehto.Name = "groupBoxHakuehto";
            this.groupBoxHakuehto.Size = new System.Drawing.Size(388, 385);
            this.groupBoxHakuehto.TabIndex = 0;
            this.groupBoxHakuehto.TabStop = false;
            this.groupBoxHakuehto.Text = "groupBox1";
            // 
            // panelHakuehto
            // 
            this.panelHakuehto.AutoScroll = true;
            this.panelHakuehto.Controls.Add(this.groupBoxHakuehto);
            this.panelHakuehto.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelHakuehto.Location = new System.Drawing.Point(3, 3);
            this.panelHakuehto.Name = "panelHakuehto";
            this.panelHakuehto.Size = new System.Drawing.Size(388, 385);
            this.panelHakuehto.TabIndex = 1;
            // 
            // buttonHae
            // 
            this.buttonHae.Location = new System.Drawing.Point(3, 394);
            this.buttonHae.Name = "buttonHae";
            this.buttonHae.Size = new System.Drawing.Size(75, 23);
            this.buttonHae.TabIndex = 2;
            this.buttonHae.Text = "button1";
            this.buttonHae.UseVisualStyleBackColor = true;
            // 
            // tableLayoutPanelTiedot
            // 
            this.tableLayoutPanelTiedot.ColumnCount = 1;
            this.tableLayoutPanelTiedot.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanelTiedot.Controls.Add(this.tableLayoutPanelJulkaisuOhjaimet, 0, 1);
            this.tableLayoutPanelTiedot.Controls.Add(this.tableLayoutPanelTarinaOhjaimet, 0, 3);
            this.tableLayoutPanelTiedot.Controls.Add(this.panelJulkaisu, 0, 0);
            this.tableLayoutPanelTiedot.Controls.Add(this.panelTarina, 0, 2);
            this.tableLayoutPanelTiedot.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanelTiedot.Location = new System.Drawing.Point(403, 3);
            this.tableLayoutPanelTiedot.Name = "tableLayoutPanelTiedot";
            this.tableLayoutPanelTiedot.RowCount = 4;
            this.tableLayoutPanelTiedot.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanelTiedot.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanelTiedot.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50.00001F));
            this.tableLayoutPanelTiedot.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanelTiedot.Size = new System.Drawing.Size(394, 420);
            this.tableLayoutPanelTiedot.TabIndex = 1;
            // 
            // tableLayoutPanelJulkaisuOhjaimet
            // 
            this.tableLayoutPanelJulkaisuOhjaimet.AutoSize = true;
            this.tableLayoutPanelJulkaisuOhjaimet.ColumnCount = 3;
            this.tableLayoutPanelJulkaisuOhjaimet.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelJulkaisuOhjaimet.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelJulkaisuOhjaimet.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanelJulkaisuOhjaimet.Controls.Add(this.buttonLisaaJulkaisu, 0, 0);
            this.tableLayoutPanelJulkaisuOhjaimet.Controls.Add(this.buttonMuokkaaJulkaisu, 1, 0);
            this.tableLayoutPanelJulkaisuOhjaimet.Controls.Add(this.buttonPoistaJulkaisu, 2, 0);
            this.tableLayoutPanelJulkaisuOhjaimet.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanelJulkaisuOhjaimet.Location = new System.Drawing.Point(3, 177);
            this.tableLayoutPanelJulkaisuOhjaimet.Name = "tableLayoutPanelJulkaisuOhjaimet";
            this.tableLayoutPanelJulkaisuOhjaimet.RowCount = 1;
            this.tableLayoutPanelJulkaisuOhjaimet.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanelJulkaisuOhjaimet.Size = new System.Drawing.Size(388, 29);
            this.tableLayoutPanelJulkaisuOhjaimet.TabIndex = 0;
            // 
            // buttonLisaaJulkaisu
            // 
            this.buttonLisaaJulkaisu.Location = new System.Drawing.Point(3, 3);
            this.buttonLisaaJulkaisu.Name = "buttonLisaaJulkaisu";
            this.buttonLisaaJulkaisu.Size = new System.Drawing.Size(75, 23);
            this.buttonLisaaJulkaisu.TabIndex = 0;
            this.buttonLisaaJulkaisu.Text = "button1";
            this.buttonLisaaJulkaisu.UseVisualStyleBackColor = true;
            // 
            // buttonMuokkaaJulkaisu
            // 
            this.buttonMuokkaaJulkaisu.Location = new System.Drawing.Point(84, 3);
            this.buttonMuokkaaJulkaisu.Name = "buttonMuokkaaJulkaisu";
            this.buttonMuokkaaJulkaisu.Size = new System.Drawing.Size(75, 23);
            this.buttonMuokkaaJulkaisu.TabIndex = 1;
            this.buttonMuokkaaJulkaisu.Text = "button2";
            this.buttonMuokkaaJulkaisu.UseVisualStyleBackColor = true;
            // 
            // buttonPoistaJulkaisu
            // 
            this.buttonPoistaJulkaisu.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.buttonPoistaJulkaisu.Location = new System.Drawing.Point(310, 3);
            this.buttonPoistaJulkaisu.Name = "buttonPoistaJulkaisu";
            this.buttonPoistaJulkaisu.Size = new System.Drawing.Size(75, 23);
            this.buttonPoistaJulkaisu.TabIndex = 2;
            this.buttonPoistaJulkaisu.Text = "button3";
            this.buttonPoistaJulkaisu.UseVisualStyleBackColor = true;
            // 
            // tableLayoutPanelTarinaOhjaimet
            // 
            this.tableLayoutPanelTarinaOhjaimet.AutoSize = true;
            this.tableLayoutPanelTarinaOhjaimet.ColumnCount = 3;
            this.tableLayoutPanelTarinaOhjaimet.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelTarinaOhjaimet.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelTarinaOhjaimet.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanelTarinaOhjaimet.Controls.Add(this.buttonLisaaTarina, 0, 0);
            this.tableLayoutPanelTarinaOhjaimet.Controls.Add(this.buttonMuokkaaTarina, 1, 0);
            this.tableLayoutPanelTarinaOhjaimet.Controls.Add(this.buttonPoistaTarina, 2, 0);
            this.tableLayoutPanelTarinaOhjaimet.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanelTarinaOhjaimet.Location = new System.Drawing.Point(3, 387);
            this.tableLayoutPanelTarinaOhjaimet.Name = "tableLayoutPanelTarinaOhjaimet";
            this.tableLayoutPanelTarinaOhjaimet.RowCount = 1;
            this.tableLayoutPanelTarinaOhjaimet.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanelTarinaOhjaimet.Size = new System.Drawing.Size(388, 30);
            this.tableLayoutPanelTarinaOhjaimet.TabIndex = 1;
            // 
            // buttonLisaaTarina
            // 
            this.buttonLisaaTarina.Location = new System.Drawing.Point(3, 3);
            this.buttonLisaaTarina.Name = "buttonLisaaTarina";
            this.buttonLisaaTarina.Size = new System.Drawing.Size(75, 23);
            this.buttonLisaaTarina.TabIndex = 0;
            this.buttonLisaaTarina.Text = "button1";
            this.buttonLisaaTarina.UseVisualStyleBackColor = true;
            // 
            // buttonMuokkaaTarina
            // 
            this.buttonMuokkaaTarina.Location = new System.Drawing.Point(84, 3);
            this.buttonMuokkaaTarina.Name = "buttonMuokkaaTarina";
            this.buttonMuokkaaTarina.Size = new System.Drawing.Size(75, 23);
            this.buttonMuokkaaTarina.TabIndex = 1;
            this.buttonMuokkaaTarina.Text = "button2";
            this.buttonMuokkaaTarina.UseVisualStyleBackColor = true;
            // 
            // buttonPoistaTarina
            // 
            this.buttonPoistaTarina.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.buttonPoistaTarina.Location = new System.Drawing.Point(310, 3);
            this.buttonPoistaTarina.Name = "buttonPoistaTarina";
            this.buttonPoistaTarina.Size = new System.Drawing.Size(75, 23);
            this.buttonPoistaTarina.TabIndex = 2;
            this.buttonPoistaTarina.Text = "button3";
            this.buttonPoistaTarina.UseVisualStyleBackColor = true;
            // 
            // panelJulkaisu
            // 
            this.panelJulkaisu.AutoScroll = true;
            this.panelJulkaisu.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelJulkaisu.Location = new System.Drawing.Point(3, 3);
            this.panelJulkaisu.Name = "panelJulkaisu";
            this.panelJulkaisu.Size = new System.Drawing.Size(388, 168);
            this.panelJulkaisu.TabIndex = 2;
            // 
            // panelTarina
            // 
            this.panelTarina.AutoScroll = true;
            this.panelTarina.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelTarina.Location = new System.Drawing.Point(3, 212);
            this.panelTarina.Name = "panelTarina";
            this.panelTarina.Size = new System.Drawing.Size(388, 169);
            this.panelTarina.TabIndex = 3;
            // 
            // FormPaaikkuna
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.tableLayoutPanelPohja);
            this.Controls.Add(this.menuStripPaavalikko);
            this.MainMenuStrip = this.menuStripPaavalikko;
            this.Name = "FormPaaikkuna";
            this.Text = "Form1";
            this.tableLayoutPanelPohja.ResumeLayout(false);
            this.tableLayoutPanelHaku.ResumeLayout(false);
            this.panelHakuehto.ResumeLayout(false);
            this.tableLayoutPanelTiedot.ResumeLayout(false);
            this.tableLayoutPanelTiedot.PerformLayout();
            this.tableLayoutPanelJulkaisuOhjaimet.ResumeLayout(false);
            this.tableLayoutPanelTarinaOhjaimet.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private MenuStrip menuStripPaavalikko;
        private TableLayoutPanel tableLayoutPanelPohja;
        private TableLayoutPanel tableLayoutPanelHaku;
        private GroupBox groupBoxHakuehto;
        private Panel panelHakuehto;
        private Button buttonHae;
        private TableLayoutPanel tableLayoutPanelTiedot;
        private TableLayoutPanel tableLayoutPanelJulkaisuOhjaimet;
        private Button buttonLisaaJulkaisu;
        private Button buttonMuokkaaJulkaisu;
        private Button buttonPoistaJulkaisu;
        private TableLayoutPanel tableLayoutPanelTarinaOhjaimet;
        private Button buttonLisaaTarina;
        private Button buttonMuokkaaTarina;
        private Button buttonPoistaTarina;
        private Panel panelJulkaisu;
        private Panel panelTarina;
    }
}