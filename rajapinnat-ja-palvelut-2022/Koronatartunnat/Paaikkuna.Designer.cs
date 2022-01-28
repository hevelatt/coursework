
namespace Koronatartunnat
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
            this.comboBoxAlue = new System.Windows.Forms.ComboBox();
            this.dateTimePickerTartuntapaiva = new System.Windows.Forms.DateTimePicker();
            this.labelTartunnat = new System.Windows.Forms.Label();
            this.buttonNayta = new System.Windows.Forms.Button();
            this.menuStripPaavalikko = new System.Windows.Forms.MenuStrip();
            this.tiedostoToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.päivitäVerkkoyhteysToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.lopetaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.asetuksetToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.päivämäärävalintaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.textBoxArvaus = new System.Windows.Forms.TextBox();
            this.labelVastaus = new System.Windows.Forms.Label();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.tableLayoutPanel2 = new System.Windows.Forms.TableLayoutPanel();
            this.buttonArvaus = new System.Windows.Forms.Button();
            this.labelVirhe = new System.Windows.Forms.Label();
            this.menuStripPaavalikko.SuspendLayout();
            this.tableLayoutPanel1.SuspendLayout();
            this.tableLayoutPanel2.SuspendLayout();
            this.SuspendLayout();
            // 
            // comboBoxAlue
            // 
            this.comboBoxAlue.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Left | System.Windows.Forms.AnchorStyles.Right)));
            this.comboBoxAlue.FormattingEnabled = true;
            this.comboBoxAlue.Location = new System.Drawing.Point(12, 12);
            this.comboBoxAlue.Margin = new System.Windows.Forms.Padding(12);
            this.comboBoxAlue.Name = "comboBoxAlue";
            this.comboBoxAlue.Size = new System.Drawing.Size(200, 23);
            this.comboBoxAlue.TabIndex = 0;
            this.comboBoxAlue.SelectedIndexChanged += new System.EventHandler(this.comboBoxAlue_SelectedIndexChanged);
            // 
            // dateTimePickerTartuntapaiva
            // 
            this.dateTimePickerTartuntapaiva.Enabled = false;
            this.dateTimePickerTartuntapaiva.Location = new System.Drawing.Point(12, 47);
            this.dateTimePickerTartuntapaiva.Margin = new System.Windows.Forms.Padding(12, 0, 12, 12);
            this.dateTimePickerTartuntapaiva.Name = "dateTimePickerTartuntapaiva";
            this.dateTimePickerTartuntapaiva.Size = new System.Drawing.Size(200, 23);
            this.dateTimePickerTartuntapaiva.TabIndex = 1;
            this.dateTimePickerTartuntapaiva.ValueChanged += new System.EventHandler(this.dateTimePickerTartuntapaiva_ValueChanged);
            // 
            // labelTartunnat
            // 
            this.labelTartunnat.AutoSize = true;
            this.labelTartunnat.Location = new System.Drawing.Point(553, 53);
            this.labelTartunnat.Margin = new System.Windows.Forms.Padding(12, 6, 12, 12);
            this.labelTartunnat.Name = "labelTartunnat";
            this.labelTartunnat.Size = new System.Drawing.Size(0, 15);
            this.labelTartunnat.TabIndex = 2;
            // 
            // buttonNayta
            // 
            this.buttonNayta.Location = new System.Drawing.Point(553, 12);
            this.buttonNayta.Margin = new System.Windows.Forms.Padding(12);
            this.buttonNayta.Name = "buttonNayta";
            this.buttonNayta.Size = new System.Drawing.Size(75, 23);
            this.buttonNayta.TabIndex = 3;
            this.buttonNayta.Text = "Näytä";
            this.buttonNayta.UseVisualStyleBackColor = true;
            this.buttonNayta.Click += new System.EventHandler(this.buttonNayta_Click);
            // 
            // menuStripPaavalikko
            // 
            this.menuStripPaavalikko.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tiedostoToolStripMenuItem,
            this.asetuksetToolStripMenuItem});
            this.menuStripPaavalikko.Location = new System.Drawing.Point(0, 0);
            this.menuStripPaavalikko.Name = "menuStripPaavalikko";
            this.menuStripPaavalikko.Size = new System.Drawing.Size(640, 24);
            this.menuStripPaavalikko.TabIndex = 4;
            this.menuStripPaavalikko.Text = "menuStrip1";
            // 
            // tiedostoToolStripMenuItem
            // 
            this.tiedostoToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.päivitäVerkkoyhteysToolStripMenuItem,
            this.lopetaToolStripMenuItem});
            this.tiedostoToolStripMenuItem.Name = "tiedostoToolStripMenuItem";
            this.tiedostoToolStripMenuItem.Size = new System.Drawing.Size(64, 20);
            this.tiedostoToolStripMenuItem.Text = "Tiedosto";
            // 
            // päivitäVerkkoyhteysToolStripMenuItem
            // 
            this.päivitäVerkkoyhteysToolStripMenuItem.Name = "päivitäVerkkoyhteysToolStripMenuItem";
            this.päivitäVerkkoyhteysToolStripMenuItem.Size = new System.Drawing.Size(181, 22);
            this.päivitäVerkkoyhteysToolStripMenuItem.Text = "Päivitä verkkoyhteys";
            this.päivitäVerkkoyhteysToolStripMenuItem.Click += new System.EventHandler(this.päivitäVerkkoyhteysToolStripMenuItem_Click);
            // 
            // lopetaToolStripMenuItem
            // 
            this.lopetaToolStripMenuItem.Name = "lopetaToolStripMenuItem";
            this.lopetaToolStripMenuItem.Size = new System.Drawing.Size(181, 22);
            this.lopetaToolStripMenuItem.Text = "Lopeta";
            this.lopetaToolStripMenuItem.Click += new System.EventHandler(this.lopetaToolStripMenuItem_Click);
            // 
            // asetuksetToolStripMenuItem
            // 
            this.asetuksetToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.päivämäärävalintaToolStripMenuItem});
            this.asetuksetToolStripMenuItem.Name = "asetuksetToolStripMenuItem";
            this.asetuksetToolStripMenuItem.Size = new System.Drawing.Size(70, 20);
            this.asetuksetToolStripMenuItem.Text = "Asetukset";
            // 
            // päivämäärävalintaToolStripMenuItem
            // 
            this.päivämäärävalintaToolStripMenuItem.Name = "päivämäärävalintaToolStripMenuItem";
            this.päivämäärävalintaToolStripMenuItem.Size = new System.Drawing.Size(170, 22);
            this.päivämäärävalintaToolStripMenuItem.Text = "Päivämäärävalinta";
            this.päivämäärävalintaToolStripMenuItem.Click += new System.EventHandler(this.päivämäärävalintaToolStripMenuItem_Click);
            // 
            // textBoxArvaus
            // 
            this.textBoxArvaus.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Left | System.Windows.Forms.AnchorStyles.Right)));
            this.textBoxArvaus.Location = new System.Drawing.Point(12, 12);
            this.textBoxArvaus.Margin = new System.Windows.Forms.Padding(12);
            this.textBoxArvaus.Name = "textBoxArvaus";
            this.textBoxArvaus.Size = new System.Drawing.Size(206, 23);
            this.textBoxArvaus.TabIndex = 5;
            // 
            // labelVastaus
            // 
            this.labelVastaus.AutoSize = true;
            this.labelVastaus.Location = new System.Drawing.Point(236, 53);
            this.labelVastaus.Margin = new System.Windows.Forms.Padding(12, 6, 12, 12);
            this.labelVastaus.Name = "labelVastaus";
            this.labelVastaus.Size = new System.Drawing.Size(0, 15);
            this.labelVastaus.TabIndex = 6;
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 3;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanel1.Controls.Add(this.comboBoxAlue, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.dateTimePickerTartuntapaiva, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.labelVastaus, 1, 1);
            this.tableLayoutPanel1.Controls.Add(this.buttonNayta, 2, 0);
            this.tableLayoutPanel1.Controls.Add(this.labelTartunnat, 2, 1);
            this.tableLayoutPanel1.Controls.Add(this.tableLayoutPanel2, 1, 0);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 39);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 2;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 20F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(640, 87);
            this.tableLayoutPanel1.TabIndex = 7;
            // 
            // tableLayoutPanel2
            // 
            this.tableLayoutPanel2.AutoSize = true;
            this.tableLayoutPanel2.ColumnCount = 2;
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanel2.Controls.Add(this.textBoxArvaus, 0, 0);
            this.tableLayoutPanel2.Controls.Add(this.buttonArvaus, 1, 0);
            this.tableLayoutPanel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel2.Location = new System.Drawing.Point(224, 0);
            this.tableLayoutPanel2.Margin = new System.Windows.Forms.Padding(0);
            this.tableLayoutPanel2.Name = "tableLayoutPanel2";
            this.tableLayoutPanel2.RowCount = 1;
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel2.Size = new System.Drawing.Size(317, 47);
            this.tableLayoutPanel2.TabIndex = 7;
            // 
            // buttonArvaus
            // 
            this.buttonArvaus.Location = new System.Drawing.Point(230, 12);
            this.buttonArvaus.Margin = new System.Windows.Forms.Padding(0, 12, 12, 12);
            this.buttonArvaus.Name = "buttonArvaus";
            this.buttonArvaus.Size = new System.Drawing.Size(75, 23);
            this.buttonArvaus.TabIndex = 6;
            this.buttonArvaus.Text = "Arvio";
            this.buttonArvaus.UseVisualStyleBackColor = true;
            this.buttonArvaus.Click += new System.EventHandler(this.buttonArvaus_Click);
            // 
            // labelVirhe
            // 
            this.labelVirhe.Dock = System.Windows.Forms.DockStyle.Top;
            this.labelVirhe.ForeColor = System.Drawing.SystemColors.HighlightText;
            this.labelVirhe.Location = new System.Drawing.Point(0, 24);
            this.labelVirhe.Name = "labelVirhe";
            this.labelVirhe.Size = new System.Drawing.Size(640, 15);
            this.labelVirhe.TabIndex = 8;
            this.labelVirhe.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // Paaikkuna
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(640, 126);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Controls.Add(this.labelVirhe);
            this.Controls.Add(this.menuStripPaavalikko);
            this.MainMenuStrip = this.menuStripPaavalikko;
            this.Name = "Paaikkuna";
            this.Text = "Form1";
            this.menuStripPaavalikko.ResumeLayout(false);
            this.menuStripPaavalikko.PerformLayout();
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            this.tableLayoutPanel2.ResumeLayout(false);
            this.tableLayoutPanel2.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox comboBoxAlue;
        private System.Windows.Forms.DateTimePicker dateTimePickerTartuntapaiva;
        private System.Windows.Forms.Label labelTartunnat;
        private System.Windows.Forms.Button buttonNayta;
        private System.Windows.Forms.MenuStrip menuStripPaavalikko;
        private System.Windows.Forms.ToolStripMenuItem tiedostoToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem asetuksetToolStripMenuItem;
        private System.Windows.Forms.TextBox textBoxArvaus;
        private System.Windows.Forms.Label labelVastaus;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.Label labelVirhe;
        private System.Windows.Forms.ToolStripMenuItem päivitäVerkkoyhteysToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem lopetaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem päivämäärävalintaToolStripMenuItem;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel2;
        private System.Windows.Forms.Button buttonArvaus;
    }
}

